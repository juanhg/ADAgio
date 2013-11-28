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
	
	ChannelDB channelDB;
	
	public LilyPondMusicPieceWriter(){
		relative = new AbsoluteMusicNote(2, "C");
		clef = "treble";
		time = new Time(4,4);
		chordsDB = new HashMap<ChordIdentifier,List<Interval>>();
		channelDB = new ChannelDB();
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
		channelDB = new ChannelDB();
		
		m.run(this);
		
		composition = this.translate();
		System.out.print(composition);
		out.print(composition);
		
	}
			
	@SuppressWarnings("rawtypes")
	private String translate(){
		String composition = "";
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		composition += this.version() + "\n";
		composition += "\\score {\n";
		composition += " <<\n";
		
		it = this.channelDB.getChannelMap().entrySet().iterator();
		
		while (it.hasNext()) {
			e = (Map.Entry) it.next();
			composition += ((ChannelInfo) e.getValue()).getMusic();
		}

		composition += ">> \n";
		composition += this.midiTail();
		return composition;
	}
	
	/*
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

	@Override
	public void musicPlay(MusicPlayStatementEvent e) {
		
		Vector<Chord> chords = e.getChords();
		String composition = "";
		int duration = 0;
		
		for (int i = 0; i < chords.size(); i++) {
			composition += translateChord(chords.elementAt(i));
			duration += this.time.defaultNoteDuration();
			composition += Integer.toString(this.time.defaultNoteDuration());
			if (i == 0) {
				composition += "\\mf";
			}
			composition += " ";
		}
		
		this.channelDB.fillEnabledWithSilences(this.clef, this.time);
		this.channelDB.addMusicToEnabled(composition, duration, this.clef, this.time);
	}
	
	@Override
	public void createChannel(MusicChannelIdentifierEvent e) {
		this.channelDB.add(e.getId());
		this.channelDB.fillWithSilences(e.getId(), this.clef, this.time);
	}

	@Override
	public void destroyChannel(MusicChannelIdentifierEvent e) {
		this.channelDB.destroy(e.getId());
	}

	@Override
	public void enableChannel(MusicChannelIdentifierEvent e) {
		this.channelDB.enable(e.getId());
		this.channelDB.fillWithSilences(e.getId(), this.clef, this.time);
	}

	@Override
	public void disableChannel(MusicChannelIdentifierEvent e) {
		this.channelDB.disable(e.getId());
	}

	@Override
	public void setChannelVolume(MusicChannelVolumeEvent e) {
		this.channelDB.setVolume(e.getId(), e.getVolume());
	}

	@Override
	public void setChannelInstrument(MusicChannelInstrumentEvent e) {
		this.channelDB.setInstrument(e.getId(), e.getInstrument());
	}
	
	@Override
	public void setRelative(MusicRelativeStatementEvent e) {
		this.relative = (e.getaNote());
	}

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

	public ChannelDB getChannelDB() {
		return channelDB;
	}

	public void setChannelDB(ChannelDB channelDB) {
		this.channelDB = channelDB;
	}

	
	/** ----- EVENTS ----- **/
	
	@Override
	public AbsoluteMusicNote toAbsolute(MusicNoteToAbsoluteEvent e) {
		return e.getNote().toAbsoluteMusicNote(this);
	}

	@Override
	public boolean chordIsDefined(MusicChordEvent e) {
		return this.chordsDB.containsKey(e.getChord().getIdentifier());
		
	}

	@Override
	public boolean existsChannel(MusicChannelIdentifierEvent e) {
		return this.channelDB.exists(e.getId());
	}

	@Override
	public boolean isErasedChannel(MusicChannelIdentifierEvent e) {
		return this.channelDB.erased(e.getId());
	}

	@Override
	public void recoverChannel(MusicChannelIdentifierEvent e) {
		this.channelDB.getChannelMap().get(e.getId()).setErased(false);
		
	}

	@Override
	public void addChord(MusicChordAddEvent e) {
		this.chordsDB.put(e.getId(), e.getIntervals());
	}

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
