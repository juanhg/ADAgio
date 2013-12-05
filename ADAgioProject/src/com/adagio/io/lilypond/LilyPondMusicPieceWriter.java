package com.adagio.io.lilypond;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.MusicChannelIdentifierEvent;
import com.adagio.events.channels.MusicChannelInstrumentEvent;
import com.adagio.events.channels.MusicChannelVolumeEvent;
import com.adagio.events.chords.MusicChordAddEvent;
import com.adagio.events.chords.MusicChordEvent;
import com.adagio.events.notes.MusicNoteNameEvent;
import com.adagio.events.notes.MusicNoteToAbsoluteEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.events.statements.MusicRelativeStatementEvent;
import com.adagio.events.tempos.MusicTempoDefinitionEvent;
import com.adagio.io.MusicPieceWriter;
import com.adagio.language.MusicPiece;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.chords.Chord;
import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.chords.intervals.Interval;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.notealterations.Alteration;

public class LilyPondMusicPieceWriter extends MusicPieceWriter implements MusicEventListener {

	// Mode relative	
	private AbsoluteMusicNote relative;
		
	// Clefe (bass,treble...)
	private String clef;
		
	// Time (4/4, 6/8,...)
	private Time time;
	
	// Data Base of defined chords
	Map<ChordIdentifier,List<Interval>> chordsDB;
	
	// Data base of tempos
	TemposDB temposDB;
	
	// Data base of channels
	ChannelsDB channelsDB;
	
	
	public LilyPondMusicPieceWriter(){
		relative = new AbsoluteMusicNote(2, "C");
		clef = "treble";
		time = new Time(4,4);
		chordsDB = new HashMap<ChordIdentifier,List<Interval>>();
		temposDB = new TemposDB();
		channelsDB = new ChannelsDB();
	}
	
	public static void writeMusicPiece(MusicPiece m,PrintWriter out){
	    LilyPondMusicPieceWriter writer = new LilyPondMusicPieceWriter();
	    writer.write(m,out);
	}
	
	@Override
	/**
	 * Makes the constructor's work
	 */
	public void write(MusicPiece m, PrintWriter out) {
		
		String composition = "";
		
		relative = new AbsoluteMusicNote(2, "C");
		clef = "treble";
		time = new Time(4,4);
		chordsDB = new HashMap<ChordIdentifier,List<Interval>>();
		channelsDB = new ChannelsDB();
		
		m.run(this);
		
		composition = this.translate();
		System.out.print(composition);
		out.print(composition);
		
	}
		
	/**
	 * Translates all the main program
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String translate(){
		String composition = "";
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		composition += this.version() + "\n";
		composition += "\\score {\n";
		composition += " <<\n";
		
		it = this.channelsDB.getChannelMap().entrySet().iterator();
		
		while (it.hasNext()) {
			e = (Map.Entry) it.next();
			composition += ((ChannelInfo) e.getValue()).getMusic();
		}

		composition += ">> \n";
		composition += this.midiTail();
		return composition;
	}
	
	/**
	 * Receives a chord with an absolute-fundamental-note and translates it 
	 * using its definition in "data".
	 * Note: Need that the bassNote as a AbsoluteMusicNote
	 */
	public String translateChord(Chord chord){
		String composition = "";
		List<Interval> intervals = this.chordsDB.get(chord.getIdentifier());
		List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();
		AbsoluteMusicNote bassNote = (AbsoluteMusicNote) chord.getBassNote();
		
	    //Recollects the notes result to apply the interval to the fundamental note
		for(int i = 0; i < intervals.size();i++){
			aNotes.add(intervals.get(i).Apply(chord.getNote(), this));		
		}
		
		if (bassNote != null) {
			// If the bass note is higher than any other, reduces its octave
			for (int i = 0; i < aNotes.size(); i++) {
				if (bassNote.isHigher(aNotes.get(i))) {
					bassNote.setOctave(bassNote.getOctave().intValue() - 1);
				}
			}

			// If bassNote NoteName is equal than any in the interval, this last
			// is removed.
			for (int i = 0; i < aNotes.size(); i++) {
				if (bassNote.getMusicNoteName().equals(
						aNotes.get(i).getMusicNoteName())) {
					aNotes.remove(i);
				}
			}
			
			aNotes.add(bassNote);
		}
		
		
		composition += "<";
		for(int i = 0; i < aNotes.size(); i++){
			composition += translateAbsoluteMusicNote(aNotes.get(i));
			
			if(i != aNotes.size()-1){
				composition += " ";
			}
		}
		composition += ">";
		
		return composition;
	}
	
	/**
	 * Receives and AbsoluteNote and translates it to lilyPond syntax.
	 * @param aNote Note to be translated
	 * @return A String that contains the translation
	 */
	private static String translateAbsoluteMusicNote(AbsoluteMusicNote aNote){
		String composition = aNote.getBasicNoteNameString().toLowerCase();
		int intOctave = aNote.getOctave().intValue();
		Alteration alteration = aNote.getMusicNoteName().getAlteration();
		
		if(alteration != null){
			composition += translateAlteration(alteration);
		}
		
		for(int i = 0; i < Math.abs(intOctave); i++){
			if(intOctave > 0){
				composition += "'";
			}
			else{
				composition += ",";
			}
		}
				
		return composition;
	}
	
	/**
	 * Receive an alteration and translates it to LilyPond syntax.
	 * @param alteration Alteration to be translated
	 * @return A string with the translation
	 */
	private static String translateAlteration(Alteration alteration){
		String composition = "";
		String value = alteration.getValue();
		
		if(value.equals("#")){
			composition = "is";
		}
		else if(value.equals("x")){
			composition = "isis";
		}
		else if (value.equals("b")){
			composition = "es";
		}
		else if (value.equals("bb")){
			composition = "eses";
		}
		return composition;
	}
	
	/**
	 * @return String-tail needed to create the midi file
	 */
	private String midiTail(){
		String composition = "";
		
		composition += "\\layout{ }\n";
		composition += "\\midi {\n";
		composition += "\\context {\n";
		composition += "\\Score \n";
		composition += "tempoWholesPerMinute = #(ly:make-moment 72 2)\n";
		composition += "}\n";
		composition += "}\n";
		composition += "}";
		
		return composition;
	}
	
	private String version(){
		return "\\version \"2.16.2\"";
	}

	/** ----- EVENTS ----- **/
	
	@SuppressWarnings("rawtypes")
	@Override
	public void musicPlay(MusicPlayStatementEvent e) {
		
		AbsoluteMusicNote aNote = null;
		AbsoluteMusicNote bassNote = null;

		Vector<Chord> chords = e.getChords();
		Vector<Chord> absoluteChords = new Vector<Chord>();
		String composition = "";

		// Translates to absoluteMusicNote
		for (int i = 0; i < chords.size(); i++) {
			if (this.chordsDB.containsKey(chords.get(i).getIdentifier())) {
				aNote = chords.get(i).getNote().toAbsoluteMusicNote(this);
				this.setRelative(aNote);

				// We add the bassNote as an absoluteMusicNote
				if (chords.get(i).getBassNote() != null) {
					bassNote = chords.get(i).getBassNote()
							.toAbsoluteMusicNote(this);
				}

				absoluteChords.add(new Chord(aNote, chords.get(i)
						.getIdentifier(), bassNote));
			} else {
				System.err.println("Error 1: The chord identifier \""
						+ chords.get(i).getIdentifier().getValue()
						+ "\" is not defined");
				System.exit(1);
			}

		}

		//Prepare staffs to play (Staff.maximunVolume...)
		this.channelsDB.prepareStaffToPlay(clef, time);
		this.channelsDB.fillEnabledWithSilences(clef, time);
		
		composition = "";
		int duration = 0;

		Map.Entry x = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;

		it = this.getChannelDB().getChannelMap().entrySet().iterator();
		
		this.channelsDB.fillEnabledWithSilences(clef, time);
		
			while (it.hasNext()) {
				x = (Map.Entry) it.next();
				if (((ChannelInfo) x.getValue()).getChannel().isEnable()) {

					for (int i = 0; i < absoluteChords.size(); i++) {
						composition += translateChord(absoluteChords.get(i));
						duration += this.time.defaultNoteDuration();
						composition += Integer.toString(this.time
								.defaultNoteDuration());
						if (((ChannelInfo) x.getValue()).isVolumeChanged()) {
							composition += "\\mf";
							((ChannelInfo) x.getValue()).setVolumeChanged(false);
						}
						composition += " ";
					}
					composition += "\n";

					this.channelsDB.addMusic((ChannelIdentifier) x.getKey(),composition, duration, clef, time);
					duration = 0;
					composition = "";
				}

			}
		} 
	
	/**
	 * Event that occurs when a channel is going to be created.
	 * DISABLES the default channel.
	 */
	@Override
	public void createChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.add(e.getId());
		if(!e.getId().getValue().equals(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER)){
			this.channelsDB.disable(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER));
		}
	}

	/**
	 * Event that occurs when a channel is going to be destroyed.
	 */
	@Override
	public void destroyChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.destroy(e.getId());
	}

	
	/**
	 * Event that occurs when a translation to AbsoluteMusicNote is needed
	 */
	@Override
	public AbsoluteMusicNote toAbsolute(MusicNoteToAbsoluteEvent e) {
		return e.getNote().toAbsoluteMusicNote(this);
	}

	/**
	 * Event that look for a chord in chordsDB
	 * @return true if is defined. False in other case
	 */
	@Override
	public boolean isChordDefined(MusicChordEvent e) {
		return this.chordsDB.containsKey(e.getChord().getIdentifier());
		
	}

	/**
	 * Event that happens when it's needed to know if a channel has existed anytime in DB
	 * @return true if has existed. False in other case.
	 */
	@Override
	public boolean existsChannel(MusicChannelIdentifierEvent e) {
		return this.channelsDB.exists(e.getId());
	}

	/**
	 * Event that happens when it's needed to know if a channel has been erased (logically destroyed)
	 * @return true if is erased. False in other case.
	 */
	@Override
	public boolean isErasedChannel(MusicChannelIdentifierEvent e) {
		return this.channelsDB.isErased(e.getId());
	}

	/**
	 * Event that happens when it's needed to recover a previously destroyed channel
	 */
	@Override
	public void recoverChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.getChannelMap().get(e.getId()).setErased(false);
		//this.channelDB.fillWithSilences(e.getId(), this.clef, this.time);
		
		
	}

	/**
	 * Event that happens when it's needed to add a chord in ChordsDB
	 */
	@Override
	public void addChord(MusicChordAddEvent e) {
		this.chordsDB.put(e.getId(), e.getIntervals());
	}
	
	/**
	 * Event that happens when a channel is going to be neabled. DISABLES the
	 * default channel (unless it was the enabled one).
	 */
	@Override
	public void enableChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.enable(e.getId());
		if (!e.getId().getValue()
				.equals(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER)) {
			this.channelsDB.disable(new ChannelIdentifier(
					ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER));
		}
	}
	
	/**
	 * Event that happens when it's needed to disable a channel
	 */
	@Override
	public void disableChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.disable(e.getId());
	}
	
	/**
	 * Event that happens when it's needed change the volume of a channel
	 */
	@Override
	public void setChannelVolume(MusicChannelVolumeEvent e) {
		this.channelsDB.setVolume(e.getId(), e.getVolume());
		this.channelsDB.getChannelMap().get(e.getId()).setVolumeChanged(true);
	}

	/**
	 * Event that happens when it's needed to change the instrument of a channel
	 */
	@Override
	public void setChannelInstrument(MusicChannelInstrumentEvent e) {
		this.channelsDB.setInstrument(e.getId(), e.getInstrument());
	}
	
	/**
	 * Event that happens when it's needed to change the relative note
	 */
	@Override
	public void setRelative(MusicRelativeStatementEvent e) {
		this.relative = (e.getaNote());
	}
	
	/**
	 * Event that happens when a tempo is defined.
	 */
	@Override
	public void tempoDefinition(MusicTempoDefinitionEvent e) {
		this.temposDB.add(e.getId(), e.getTempo());
	}

	
	/** ----- GETTERS & SETTERS ----- **/
	
	public AbsoluteMusicNote getRelative() {
		return relative;
	}

	public void setRelative(AbsoluteMusicNote relative) {
		this.relative = relative;
	}

	public String getClef() {
		return clef;
	}

	public void setClef(String clef) {
		this.clef = clef;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Map<ChordIdentifier, List<Interval>> getChordsDB() {
		return chordsDB;
	}

	public void setChordsDB(Map<ChordIdentifier, List<Interval>> chordsDB) {
		this.chordsDB = chordsDB;
	}

	public ChannelsDB getChannelDB() {
		return channelsDB;
	}

	public void setChannelDB(ChannelsDB channelsDB) {
		this.channelsDB = channelsDB;
	}

	
	/** ----- OTHERS ----- **/
	
	/**
	 * Obtains the alteration produces in a MusicNoteName because of the reference
	 * @return A integer value, that means the octave-alteration produces.
	 */
	@Override
	public int alterationFromReference(MusicNoteNameEvent e) {
		boolean up = false;
		boolean down = false;
			
		String rNoteName = this.relative.getBasicNoteNameString();
		int octave = this.relative.getOctave().intValue();
		
		int distance = this.relative.getMusicNoteName().getBaseNoteName().shortestDistance(e.getMusicNoteName().getBaseNoteName());
		
		if(distance == 3 && (rNoteName.equals("A") || rNoteName.equals("B")|| rNoteName.equals("C"))){
			up = true;
		}
		else if(distance == 2 && (rNoteName.equals("A") || rNoteName.equals("B"))){
			up = true;
		}
		else if(distance == 1 && (rNoteName.equals("B"))){
			up = true;
		}
		else if(distance == -3 && (rNoteName.equals("C") || rNoteName.equals("D") || rNoteName.equals("E"))){
			down = true;
		}
		else if(distance == -2 && (rNoteName.equals("C") || rNoteName.equals("D"))){
			down = true;
		}
		else if(distance == -1 && (rNoteName.equals("C"))){
			down = true;
		}
		
		if(up){ octave++;}
		else if(down){ octave--;}
		
		return octave;
	}
}
