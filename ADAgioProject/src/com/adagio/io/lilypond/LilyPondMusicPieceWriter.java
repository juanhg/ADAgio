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
import com.adagio.events.definitions.MusicTempoDefinitionEvent;
import com.adagio.events.notes.MusicNoteNameEvent;
import com.adagio.events.notes.MusicNoteToAbsoluteEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.events.statements.MusicRelativeStatementEvent;
import com.adagio.events.statements.MusicTimeStatementEvent;
import com.adagio.io.MusicPieceWriter;
import com.adagio.language.MusicPiece;
import com.adagio.language.channels.Channel;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.chords.Chord;
import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.chords.intervals.Interval;
import com.adagio.language.figures.Figure;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.times.Time;

public class LilyPondMusicPieceWriter extends MusicPieceWriter implements MusicEventListener {

	// Mode relative	
	private AbsoluteMusicNote relative;
		
	// Clefe (bass,treble...)
	private String clef;
		
	// Time (4/4, 6/8,...)
	private Time time;
	
	// Data Base of defined chords
	private Map<ChordIdentifier,List<Interval>> chordsDB;
	
	// Data base of tempos
	private TemposDB temposDB;
	
	// Data base of channels
	private ChannelsDB channelsDB;
	
	private boolean defaultUsed;
	
	
	public LilyPondMusicPieceWriter(){
		relative = new AbsoluteMusicNote(2, "C");
		clef = "treble";
		time = new Time(4,4);
		chordsDB = new HashMap<ChordIdentifier,List<Interval>>();
		temposDB = new TemposDB();
		channelsDB = new ChannelsDB();
		defaultUsed = false;
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
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		
		composition += this.version() + "\n";
		composition += "\\score {\n";
		composition += " <<\n";
		
		it = this.channelsDB.getChannelMap().entrySet().iterator();
		
		while (it.hasNext()) {
			e = (Map.Entry) it.next();
			//Only print the default channel if has been used.
			if(((ChannelIdentifier) e.getKey()).getValue().equals(Channel.DEFAULT_CHANNEL_IDENTIFIER)){
				if(this.defaultUsed){
					composition += ((Channel) e.getValue()).getMusic();
				}
			}
			else{
				composition += ((Channel) e.getValue()).getMusic();
			}
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
	
	public String translateFigure(Figure figure){
		String composition = "";
		composition += Integer.toString(figure.getShapeDuration().intValue());
		if(figure.getDots() != null){
			for(int i = 0; i < figure.getDots().length; i++){
				composition += ".";
			}
		}
		return composition;
	}
	
	public String translateTime(Time time){
		String composition = "";
		composition += time.getBeats().intValue();
		composition += "/";
		composition += this.translateFigure(time.getFigure());
		return composition;
	}
	
	/**
	 * Create the block \Staff{ options + music} for enabled channels.
	 * If \Staff has been created yet, it adds {options + music} to the current 
	 * staff. You need to call to this function in PlaySentence-events.
	 * If there is no enable channels, enables the default-one and use it.
	 * @param clef
	 * @param time
	 */
	@SuppressWarnings("rawtypes")
	public void prepareStaffsToPlay(String clef, Time time) {
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		String composition = "";

		it = this.channelsDB.getChannelMap().entrySet().iterator();
		if (!this.channelsDB.isDefaultChannelNeeded()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
					if (!((Channel) e.getValue()).getMusic().equals("")) {
						// Deletes the last "}"
						((Channel) e.getValue()).setMusic(((Channel) e.getValue()).getMusic().substring(0,((Channel) e.getValue()).getMusic().length() - 2));
					} else {
						composition += "\n\\new Staff{\n";
					}

					if (((Channel) e.getValue()).isEnable()) {
						composition += ("\n\\clef " + clef + "\n");
						composition += "\\time " + this.translateTime(this.time) + "\n";
						composition += "\\set Staff.midiInstrument = #\"" + ((Channel) e.getValue()).getInstrument().getValue() + "\"\n";
						composition += "\\set Staff.midiMinimumVolume = #" + 0 + "\n";
						composition += "\\set Staff.midiMaximumVolume = #"+ ((Channel) e.getValue()).getVolume() / 100 + "\n";
					}
					composition += "}\n";
					
					((Channel) e.getValue()).addMusic(composition);
					composition = "";
			}
		} else {
			this.defaultUsed = true;
			//enable default
			this.channelsDB.enable(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER));
		
			if (!(this.channelsDB.getChannelMap().get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER))).getMusic().equals("")) {
				// Deletes the last "}"
				(this.channelsDB.getChannelMap().get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER))).setMusic((this.channelsDB.getChannelMap().get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER))).getMusic().substring(0,(this.channelsDB.getChannelMap().get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER))).getMusic().length() - 2));
			} else {
				composition += "\n\\new Staff{";
			}

			
			composition += ("\n\\clef " + clef + "\n");
			composition += "\\time " + this.translateTime(this.time) + "\n";
			composition += "\\set Staff.midiInstrument = #\""+ this.channelsDB.getChannelMap().get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER)).getInstrument().getValue() + "\"\n";
			composition += "\\set Staff.midiMinimumVolume = #" + 0 + "\n";
			composition += "\\set Staff.midiMaximumVolume = #" + this.channelsDB.getChannelMap().get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER)).getVolume()/ 100 + "\n";
			composition += "\n}\n";

			this.channelsDB.getChannelMap().get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER)).addMusic(composition);

		}
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

		//We save the relative before play statement
		AbsoluteMusicNote relativeBeforePlay = this.relative;
		
		// Translates to absoluteMusicNote
		for (int i = 0; i < chords.size(); i++) {
			if (this.chordsDB.containsKey(chords.get(i).getIdentifier())) {
				aNote = chords.get(i).getNote().toAbsoluteMusicNote(this);
				this.relative = aNote;

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
		
		// (Erase this if don't want to reset relative after play)
		//we recover the relative
		this.relative = relativeBeforePlay;

		//Prepare staffs to play (Staff.maximunVolume...)
		this.prepareStaffsToPlay(clef, time);
		
		composition = "";
		int numBarsAdded = 0;

		Map.Entry x = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;

		it = this.channelsDB.getChannelMap().entrySet().iterator();
		
			while (it.hasNext()) {
				x = (Map.Entry) it.next();
				if (((Channel) x.getValue()).isEnable()) {

					for (int i = 0; i < absoluteChords.size(); i++) {
						composition += translateChord(absoluteChords.get(i));
						numBarsAdded ++;
						composition += Integer.toString((int)this.time
								.defaultDuration());
						if (((Channel) x.getValue()).isVolumeChanged()) {
							composition += "\\mf";
							((Channel) x.getValue()).setVolumeChanged(false);
						}
						composition += " ";
					}
					//composition += "\n";

					this.channelsDB.addMusic((ChannelIdentifier) x.getKey(),composition, numBarsAdded, clef, time);
					numBarsAdded = 0;
					composition = "";
				}

			}
			this.channelsDB.fillAllWithSilences(clef, time);
		} 
	
	/**
	 * Event that occurs when a channel is going to be created.
	 * DISABLES the default channel.
	 */
	@Override
	public void createChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.add(e.getId());
		if(!e.getId().getValue().equals(Channel.DEFAULT_CHANNEL_IDENTIFIER)){
			this.channelsDB.disable(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER));
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
				.equals(Channel.DEFAULT_CHANNEL_IDENTIFIER)) {
			this.channelsDB.disable(new ChannelIdentifier(
					Channel.DEFAULT_CHANNEL_IDENTIFIER));
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
	
	@Override
	public void setTime(MusicTimeStatementEvent e) {
		this.time = (e.getTime());
	}
	
	/**
	 * Event that happens when a tempo is defined.
	 */
	@Override
	public void tempoDefinition(MusicTempoDefinitionEvent e) {
		this.temposDB.add(e.getId(), e.getTempo());
	}
	
	/** ----- GETTERS & SETTERS ----- **/
	

	public Map<ChordIdentifier, List<Interval>> getChordsDB() {
		return chordsDB;
	}
	
	
	public ChannelsDB getChannelsDB() {
		return channelsDB;
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
