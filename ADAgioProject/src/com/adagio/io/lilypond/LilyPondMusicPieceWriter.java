package com.adagio.io.lilypond;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.modelcc.types.IntegerModel;

import com.adagio.channels.Channel;
import com.adagio.duration.Duration;
import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.MusicChannelIdentifierEvent;
import com.adagio.events.channels.MusicChannelInstrumentEvent;
import com.adagio.events.channels.MusicChannelVolumeEvent;
import com.adagio.events.chords.MusicChordAddEvent;
import com.adagio.events.chords.MusicChordEvent;
import com.adagio.events.definitions.InstrumentDefinitionEvent;
import com.adagio.events.definitions.MusicTempoDefinitionEvent;
import com.adagio.events.definitions.RhythmDefinitionEvent;
import com.adagio.events.notes.MusicNoteNameEvent;
import com.adagio.events.statements.MusicDefinedTempoStatementEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.events.statements.MusicRelativeStatementEvent;
import com.adagio.events.statements.MusicTimeStatementEvent;
import com.adagio.events.statements.MusicUndefinedTempoStatementEvent;
import com.adagio.instruments.Instrument;
import com.adagio.instruments.LimitedPolyphonicInstrument;
import com.adagio.instruments.MonophonicInstrument;
import com.adagio.instruments.PolyphonicInstrument;
import com.adagio.io.MusicPieceWriter;
import com.adagio.language.MusicPiece;
import com.adagio.language.bars.chords.Chord;
import com.adagio.language.bars.chords.intervals.Interval;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.figures.Figure;
import com.adagio.language.instruments.features.LimitedPolyphonicType;
import com.adagio.language.instruments.features.MonophonicType;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.tempos.Tempo;
import com.adagio.language.times.Time;
import com.adagio.rhythms.Rhythm;

public class LilyPondMusicPieceWriter extends MusicPieceWriter implements MusicEventListener {

	//Mode relative	
	private AbsoluteMusicNote relative;
		
	//Clefe (bass,treble...)
	private String clef;
		
	//Time (4/4, 6/8,...)
	private Time time;
	
	//Tempo 4=90bpm...
	private Tempo tempo;
	
	//Data Base of defined chords
	private ChordsDB chordsDB;
	
	//Data base of tempos
	private TemposDB temposDB;
	
	//Data base of channels
	private ChannelsDB channelsDB;
	
	//Data base of instruments
	private InstrumentsDB instrumentsDB;
	
	//Data base of rhythms
	private RhythmDB rhythmDB;
	
	private boolean hasTempoChanged;
	private boolean hasTimeChanged;
	private boolean hasClefChanged;
	
	public static final ChannelIdentifier DEFAULT_CHANNEL_IDENTIFIER = new ChannelIdentifier("defaultChannelIdentifier");
	public static final ChannelIdentifier SILENCES_PATTERN_CHANNEL_IDENTIFIER = new ChannelIdentifier("silencesPatternChannelIdentifier");
	
	public LilyPondMusicPieceWriter(){
		relative = new AbsoluteMusicNote(2, "C");
		clef = "treble";
		time = new Time(new IntegerModel(4), new Figure(4,0));
		tempo = new Tempo(new Figure(4,0),90);
		chordsDB = new ChordsDB();
		temposDB = new TemposDB();
		channelsDB = new ChannelsDB();
		instrumentsDB = new InstrumentsDB();
		rhythmDB = new RhythmDB();
		hasTempoChanged = hasTimeChanged = hasClefChanged = true;
		
		//add the default channel
		channelsDB.addChannel(DEFAULT_CHANNEL_IDENTIFIER);
		//add the pattern channe, disabled
		channelsDB.addChannel(SILENCES_PATTERN_CHANNEL_IDENTIFIER);
		channelsDB.disable(SILENCES_PATTERN_CHANNEL_IDENTIFIER);
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
		
		composition += this.getVersion() + "\n";
		composition += "\\score {\n";
		composition += " <<\n";
		
		it = this.channelsDB.getChannelMap().entrySet().iterator();
		
		while (it.hasNext()) {
			e = (Map.Entry) it.next();
			//Only print the channel if has been used.
			if(((Channel) e.getValue()).isUsed()){
				composition += "\\new Staff{\n";
				composition += ((Channel) e.getValue()).getMusic();
				composition += "\n}\n";
			}
		}
		
		composition += ">> \n";
		composition += this.getMidiTail();
		return composition;
	}
	
	
	/**
	 * Display the chord, applying all the intervals to the base note.
	 * @param chord Chord to be displayed
	 * @return List of AbsoluteMusicNotes that are the result of apply the
	 * intervals of the chord. List of one silence-AbsoluteMusicNote if
	 * the the chord is a silence-Chord
	 */
	private List<AbsoluteMusicNote> displayChord(Chord chord){

		List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();

		if(chord.isSilence()){
			aNotes.add(AbsoluteMusicNote.genSilence(chord.getDuration()));
		}
		else{

			List<Interval> intervals = this.chordsDB.getIntervals(chord.getIdentifier());
			AbsoluteMusicNote bassNote = (AbsoluteMusicNote) chord.getBassNote();

			//Recollects the notes result to apply the interval to the fundamental note
			for(int i = 0; i < intervals.size();i++){
				aNotes.add(intervals.get(i).apply(chord.getNote(), this));
				if(chord.getDuration() != null){
					aNotes.get(i).setDuration(chord.getDuration().clone());
				}
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
				aNotes.add(0,bassNote);
			}
		}
		return aNotes;
	}
	
	/**
	 * Receives and AbsoluteNote and translates it to lilyPond syntax.
	 * @param aNote Note to be translated
	 * @return A String that contains the translation
	 */
	private String translateAbsoluteMusicNote(AbsoluteMusicNote aNote){
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
		composition += translateFigure(aNote.getDuration().getFigure());		
		return composition;
	}
	
	/**
	 * Receive an alteration and translates it to LilyPond syntax.
	 * @param alteration Alteration to be translated
	 * @return A string with the translation
	 */
	private String translateAlteration(Alteration alteration){
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
	
	public String translateSilence(AbsoluteMusicNote silence){
		String composition = "";
		
		if(silence.isSilence()){
			composition = "s";
		}
		else{
			System.err.println("Error 17: The note is not a silence");
			System.exit(-17);
		}
		
		return composition;
	}
	
	public String translateFigure(Figure figure){
		String composition = "";
		composition += Integer.toString(figure.getShape().intValue());
		if(figure.getDots() != null){
			for(int i = 0; i < figure.getDots().length; i++){
				composition += ".";
			}
		}
		return composition;
	}
	
	public String translateTime(Time time){
		String composition = "";
		composition += "\\time ";
		composition += time.getBeats().intValue();
		composition += "/";
		composition += translateFigure(time.getFigure());
		
		return composition;
	}
	
	public String translateClef(String clef){
		String composition = "\\clef " + clef;
		return composition;
	}
	
	public String translateInstrument(Instrument instrument){
		
		String composition = "\\set Staff.midiInstrument = #\"";
		String timbre = instrument.getTimbre().getValue();
		if(timbre.equals("piano")){
			composition += "acoustic grand";
		}
		else if(timbre.equals("violin")){
			composition += "violin";
		}
		else if(timbre.equals("flute")){
			composition += "flute";
		}
		else if(timbre.equals("acousticguitar")){
			composition += "acoustic guitar (nylon)";
		}
		else if(timbre.equals("voice")){
			composition += "synth voice";
		}
		else if(timbre.equals("clarinet")){
			composition += "clarinet";
		}
		
		composition += "\"";
		return composition;
	}
	
	public String translateVolume(int volume){
		String composition = "\\set Staff.midiMinimumVolume = #" + 0 + "\n";
		composition += "\\set Staff.midiMaximumVolume = #" + ((double)volume)/100;
		return composition;
	}
	
	public String translateTempo(Tempo tempo){
		String composition = "\\tempo ";
		composition += translateFigure(tempo.getFigure());
		composition += "=" + this.tempo.getBps().intValue();
		return composition;
	}
	
	/**
	 * Receives a chord with an absolute-fundamental-note and translates it. Depend of
	 * the instrument that will play the chord, the translation is different.
	 * The instrument try to brings the chord to his register.
	 * Note: Need that the bassNote as a AbsoluteMusicNote
	 */
	public String translateChord(Chord chord, Instrument instrument){

		String composition = "";

		if(chord.isSilence() == false){


			List<Interval> intervals = this.chordsDB.getIntervals(chord.getIdentifier());
			List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();
			AbsoluteMusicNote bassNote = (AbsoluteMusicNote) chord.getBassNote();

			//Recollects the notes result to apply the interval to the fundamental note
			for(int i = 0; i < intervals.size();i++){
				aNotes.add(intervals.get(i).apply(chord.getNote(), this));		
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
				aNotes.add(0,bassNote);
			}

			//The instrument transports the notes to his register
			aNotes = instrument.apply(aNotes);

			composition += "<";
			for(int i = 0; i < aNotes.size(); i++){
				composition += translateAbsoluteMusicNote(aNotes.get(i));

				if(i != aNotes.size()-1){
					composition += " ";
				}
			}
			composition += ">";
		}
		else{
			composition = BasicNoteName.silencePattern.toLowerCase();
		}
		return composition;
	}
	
		
	/**
	 * @return String-tail needed to create the midi file
	 */
	private String getMidiTail(){
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
	
	
	private String getChannelHeaders(Channel actualChannel){
		String composition = "";
		// To format composition...
		if(this.hasClefChanged 
				|| this.hasTempoChanged || this.hasTimeChanged
				|| actualChannel.hasVolumeChanged()
				|| actualChannel.hasInstrumentChanged())
		{
			composition += "\n";
		}

		// \Tempo...
		if(this.hasTempoChanged){
			composition += this.translateTempo(this.tempo) + "\n";
			this.hasTempoChanged = false;
		}
		// \Clef...
		if(this.hasClefChanged){
			composition += this.translateClef(clef) + "\n";
			this.hasClefChanged = false;
		}
		// \Time...
		if(this.hasTimeChanged){
			composition += this.translateTime(this.time) + "\n";
			this.hasTimeChanged = false;
		}
		// \set midiMaximunVolumen...
		if(actualChannel.hasVolumeChanged()){
			composition += this.translateVolume(actualChannel.getVolume()) + "\n";
		}
		// \set Staff.midiInstrument...
		if(actualChannel.hasInstrumentChanged()){
			composition += this.translateInstrument(actualChannel.getInstrument()) + "\n"; 
			actualChannel.setInstrumentChanged(false);
		}
		return composition;
	}
	
	private String getVersion(){
		return "\\version \"2.16.2\"";
	}

	/** ----- EVENTS ----- **/
	
	@SuppressWarnings("rawtypes")
	@Override
	public void musicPlay(MusicPlayStatementEvent e) {

		List<AbsoluteMusicNote> chordDisplayed, chordInstrument;
		List<List<AbsoluteMusicNote>> listChordsDisplayed, listChordsInstrument, listChordsVoices;
		Instrument channelInstrument;
		
		Channel actualChannel;

		Vector<Chord> barItems = new Vector<Chord>(Arrays.asList(e.getBar().getBarChords()));
		int numBarsAdded = barFigures(barItems); 

		Vector<Chord> absoluteChords = new Vector<Chord>(); 
		String composition = "";

		//We save the relative before play statement
		//AbsoluteMusicNote relativeBeforePlay = this.relative;

		// Translates to absoluteMusicNote
		for (int i = 0; i < barItems.size(); i++) {
			if (this.chordsDB.exists(((Chord)barItems.get(i)).getIdentifier())) {
				Chord auxChord = ((Chord)barItems.get(i)).toAbsoluteChord(this);
				absoluteChords.add(auxChord);
				if(auxChord.isSilence() == false){
					this.relative = (AbsoluteMusicNote) absoluteChords.get(absoluteChords.size()-1).getNote();
				}
			} else {
				System.err.println("Error 1: The chord identifier \""
						+ ((Chord)barItems.get(i)).getIdentifier().getValue()
						+ "\" is not defined");
				System.exit(1);
			}

		}

		//Display the chords
		listChordsDisplayed = new ArrayList<List<AbsoluteMusicNote>>();
		for(Chord current: absoluteChords){
			chordDisplayed = new ArrayList<AbsoluteMusicNote>();
			chordDisplayed = this.displayChord(current);
			listChordsDisplayed.add(chordDisplayed);
		}

		// (Erase this if don't want to reset relative after play)
		//we recover the relative
		//this.relative = relativeBeforePlay;

		composition = "";


		Map.Entry x = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;

		it = this.channelsDB.getChannelMap().entrySet().iterator();

		//For each channel
		while (it.hasNext()) {
			x = (Map.Entry) it.next();
			//If the channel is enabled
			
			actualChannel = ((Channel) x.getValue());
			if (actualChannel.isEnable()) {

				composition += getChannelHeaders(actualChannel);
				
				//The channel is used
				actualChannel.setUsed(true);
				
				
				//Apply the instrument to the notes chords displayed
				channelInstrument = actualChannel.getInstrument();
				listChordsInstrument = new ArrayList<List<AbsoluteMusicNote>>();
				for(List<AbsoluteMusicNote> current: listChordsDisplayed){
					chordInstrument = new ArrayList<AbsoluteMusicNote>();
					chordInstrument = channelInstrument.apply(current);
					listChordsInstrument.add(chordInstrument);
				}
				
				listChordsVoices = reorderforVoices(listChordsInstrument);
				
				composition += "<<\n";
				
				for(List<AbsoluteMusicNote> currentList: listChordsVoices){
					composition += "{";
					for(int i = 0; i < currentList.size(); i++){
						composition += " ";
						composition += translateAbsoluteMusicNote(currentList.get(i));
						
						if (i == 0 && actualChannel.hasVolumeChanged()){
							composition += "\\mf ";
						}
					}
					composition += " } \\\\ ";
				}
				composition += ">>\n";
				
				actualChannel.setVolumeChanged(false);
								
				this.channelsDB.addMusic((ChannelIdentifier) x.getKey(),composition, numBarsAdded);
				composition = "";

			}
		}
		this.fillDisabledChannelsWithSilences();
	}

	
	public List<List<AbsoluteMusicNote>> reorderforVoices(List<List<AbsoluteMusicNote>> listANotes ){
		List<List<AbsoluteMusicNote>> notesReady = new ArrayList<List<AbsoluteMusicNote>>();
		int maxSize = 0;
		
		Duration duration = listANotes.get(0).get(0).getDuration();

		for(List<AbsoluteMusicNote> current: listANotes){
			if(current.size() > maxSize){
				maxSize = current.size();
			}
		}

		for(List<AbsoluteMusicNote> current: listANotes){
			while(current.size() < maxSize){
				current.add(AbsoluteMusicNote.genSilence(duration));
			}
		}

		List<AbsoluteMusicNote> auxANotes;


		for(int i = 0; i < listANotes.get(0).size(); i++){
			auxANotes = new ArrayList<AbsoluteMusicNote>();
			for(int j = 0; j < listANotes.size(); j++){
				auxANotes.add(listANotes.get(j).get(i));
			}
			notesReady.add(auxANotes);
		}
	
		return notesReady;
	}
	
	/**
	 * Event that occurs when a channel is going to be created.
	 * DISABLES the default channel.
	 */
	@Override
	public void createChannel(MusicChannelIdentifierEvent e) {
		boolean newChannelAdded = !this.channelsDB.exists(e.getId());
		boolean existsPattern = this.channelsDB.exists(LilyPondMusicPieceWriter.SILENCES_PATTERN_CHANNEL_IDENTIFIER);
				
		this.channelsDB.addChannel(e.getId());
		if(!e.getId().getValue().equals(DEFAULT_CHANNEL_IDENTIFIER.getValue())){
			this.channelsDB.disable(DEFAULT_CHANNEL_IDENTIFIER);
		}
	
		//if the channel added is new in the DB, copy the music and duration
		// silences pattern
		if(existsPattern && newChannelAdded){
			this.channelsDB.getChannel(e.getId())
			        .setMusic(this.channelsDB.getChannelMap()
					.get(LilyPondMusicPieceWriter.SILENCES_PATTERN_CHANNEL_IDENTIFIER)
					.getMusic());
			this.channelsDB.getChannel(e.getId())
			         .setNumBars(this.channelsDB.getChannelMap()
					.get(LilyPondMusicPieceWriter.SILENCES_PATTERN_CHANNEL_IDENTIFIER)
					.getNumBars());
		}
	}
	
	/**
	 * Fills a channel with silences until reaches the maxDuration of channelsDB
	 * @param id Identifier of the channel
	 */
	private void fillChannelWithSilences(ChannelIdentifier id) {
		int maxDuration = this.channelsDB.maxNumBars();
		int auxDuration = 0;
		int difference = 0;
		Vector<Figure> silenceFigures = barFigures(this.time.getBeats().intValue(), time);

		String composition = "";

		if (this.channelsDB.exists(id)) {
			auxDuration = this.channelsDB.getChannel(id).getNumBars();
			difference = maxDuration - auxDuration;

			if (difference > 0) {
				for (int i = difference; i > 0; i--) {
					for(int j = 0; j < silenceFigures.size(); j++){
						composition += "s" + translateFigure(silenceFigures.elementAt(j)) + " ";
					}
				}
				this.channelsDB.addMusic(id, composition, difference);
			}
		} else {
			System.err.println("Error 10: Channel \"" + id.toString()
					+ "\" doesn't exist. " + "Can't be filled with zeros.");

		}
	}
	
	/**
	 * Fills all the disabled channels with silences until reach the maxDuration
	 * @param id Identifier of the DB
	 */
	@SuppressWarnings("rawtypes")
	private void fillDisabledChannelsWithSilences(){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		
		it = this.channelsDB.getChannelMap().entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(!((Channel)e.getValue()).isEnable()){
					this.fillChannelWithSilences((ChannelIdentifier)e.getKey());
				}
			}
		}
	}

	/**
	 * Event that occurs when a channel is going to be destroyed.
	 * Destroy the channel, and if is needed, activates the default one.
	 */
	@Override
	public void destroyChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.destroy(e.getId());
		if(this.channelsDB.isDefaultChannelNeeded() && !e.getId().getValue().equals(DEFAULT_CHANNEL_IDENTIFIER.getValue())){
			this.channelsDB.enable(DEFAULT_CHANNEL_IDENTIFIER);
		}
	}

	
//	/**
//	 * Event that occurs when a translation to AbsoluteMusicNote is needed
//	 */
//	@Override
//	public AbsoluteMusicNote toAbsolute(MusicNoteToAbsoluteEvent e) {
//		return e.getNote().toAbsoluteMusicNote(this);
//	}

	/**
	 * Event that look for a chord in chordsDB
	 * @return true if is defined. False in other case
	 */
	@Override
	public boolean isChordDefined(MusicChordEvent e) {
		return this.chordsDB.exists(e.getChord().getIdentifier());
		
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
	 * Disable the default channel
	 */
	@Override
	public void recoverChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.getChannel(e.getId()).setErased(false);
		if (!e.getId().getValue().equals(DEFAULT_CHANNEL_IDENTIFIER.getValue())) {
			this.channelsDB.disable(DEFAULT_CHANNEL_IDENTIFIER);
		}
	}

	/**
	 * Event that happens when it's needed to add a chord in ChordsDB
	 */
	@Override
	public void addChord(MusicChordAddEvent e) {
		this.chordsDB.addChord(e.getId(), e.getIntervals());
	}
	
	/**
	 * Event that happens when a channel is going to be enabled. 
	 * DISABLES the default channel (unless it was the enabled one).
	 */
	@Override
	public void enableChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.enable(e.getId());
		if (!e.getId().getValue().equals(DEFAULT_CHANNEL_IDENTIFIER.getValue())) {
			this.channelsDB.disable(DEFAULT_CHANNEL_IDENTIFIER);
		}
	}
	
	/**
	 * Event that happens when it's needed to disable a channel.
	 * Disable the channel, and if is needed, activates the default one.
	 */
	@Override
	public void disableChannel(MusicChannelIdentifierEvent e) {
		this.channelsDB.disable(e.getId());
		if(this.channelsDB.isDefaultChannelNeeded() && !e.getId().getValue().equals(DEFAULT_CHANNEL_IDENTIFIER.getValue())){
			this.channelsDB.enable(DEFAULT_CHANNEL_IDENTIFIER);
		}
	}
	
	/**
	 * Event that happens when it's needed change the volume of a channel
	 */
	@Override
	public void setChannelVolume(MusicChannelVolumeEvent e) {
		this.channelsDB.setVolume(e.getId(), e.getVolume());
		this.channelsDB.getChannel(e.getId()).setVolumeChanged(true);
	}

	/**
	 * Event that happens when it's needed to change the instrument of a channel
	 */
	@Override
	public void setChannelInstrument(MusicChannelInstrumentEvent e) {
	
		if(this.instrumentsDB.exists(e.getInstrumentID())){
			this.channelsDB.setInstrument(e.getId(), this.instrumentsDB.getInstrument(e.getInstrumentID()));
			this.channelsDB.getChannel(e.getId()).setInstrumentChanged(true);
		}
		else{
			System.err.println("Error 11: The Instrument identifier \""
					+ e.getInstrumentID().getValue()
					+ "\" is not defined");
			System.exit(11);
		}
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
		this.hasTimeChanged = true;
	}
	
	@Override
	public void setTempo(MusicDefinedTempoStatementEvent e) {
		if(this.temposDB.exists(e.getIdentifier())){
			this.tempo = this.temposDB.getTempo(e.getIdentifier()).clone();
		}
		this.hasTempoChanged = true;
	}

	@Override
	public void setTempo(MusicUndefinedTempoStatementEvent e) {
		this.tempo = e.getTempo().clone();
		this.hasTempoChanged = true;
	}	
	
	/**
	 * Event that happens when a tempo is defined.
	 */
	@Override
	public void tempoDefinition(MusicTempoDefinitionEvent e) {
		this.temposDB.addTempo(e.getId(), e.getTempo());
	}
	
	
	/**
	 * Event that happens when an instrument is defined.
	 */
	@Override
	public void instrumentDefinition(InstrumentDefinitionEvent e) {
		
		Instrument instrument;
		
		
		if(e.getPhType().getClass().equals(MonophonicType.class)){
			if(e.getRegisters() != null && e.getRegisters().length > 0){
				instrument = new MonophonicInstrument(e.getTimbre(), e.getRegisters());
			}
			else{
				instrument = new MonophonicInstrument(e.getTimbre());
			}
		}
		else if(e.getPhType().getClass().equals(LimitedPolyphonicType.class)){
			if(e.getRegisters() != null && e.getRegisters().length > 0){
				instrument = new LimitedPolyphonicInstrument(e.getTimbre(), e.getRegisters());
			}
			else{
				instrument = new LimitedPolyphonicInstrument(e.getTimbre());
			}
		}
		else{
			if(e.getRegisters() != null && e.getRegisters().length > 0){
				instrument = new PolyphonicInstrument(e.getTimbre(), e.getRegisters());
			}
			else{
				instrument = new PolyphonicInstrument(e.getTimbre());
			}
		}
		
		this.instrumentsDB.addInstrument(e.getIdentifier(), instrument);
	}
	
	/**
	 * Event that is launched when an instrument is defined.
	 */
	@Override
	public void rhythmDefinition(RhythmDefinitionEvent e) {
		Rhythm rhythm = new Rhythm(e.getComponents());
		this.rhythmDB.addRhythm(e.getIdentifier(), rhythm);
	}
	
	/** ----- GETTERS & SETTERS (Not events) ----- **/
	

	public ChordsDB getChordsDB() {
		return chordsDB;
	}
	
	public ChannelsDB getChannelsDB() {
		return channelsDB;
	}
	
	
	/** ----- OTHERS ----- **/

	/**
	 * Obtains the alteration produces in a MusicNoteName because of the reference
	 * @return A integer value, that means the octave-alteration produces.
	 * If is a silence, returns the relative-octave alteration
	 */
	@Override
	public int alterationFromReference(MusicNoteNameEvent e) {

		boolean up = false;
		boolean down = false;

		String rNoteName = this.relative.getBasicNoteNameString();
		int octave = this.relative.getOctave().intValue();

		if(e.getMusicNoteName().isSilence() == false){

			int distance = this.relative.getMusicNoteName().getBaseNoteName().shortestDistance(e.getMusicNoteName().getBaseNoteName());

			if(distance == 3 && (rNoteName.equals("A") || rNoteName.equals("B")|| rNoteName.equals("G"))){
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

		}

		return octave;
	}
		
	//TODO remove this function
	
	/**
	 * Return a string with the figures that correspond 
	 * to the chords in the bars. If the vector is bigger
	 * than the number of chords, the rest of the figures 
	 * will be silences.
	 * @param bar Bar with chords
	 * @param numChords Number of chords in the bar
	 * @return
	 */

	public Vector<Figure> barFigures(int numChords, Time time){
		Vector<Figure> figures = new Vector<Figure>();
		double factor = time.getBeats().doubleValue()/numChords;
		double figuresDuration = factor * time.getFigure().duration();
		
		if(Figure.validDuration(figuresDuration)){
			for(int i = 0; i < numChords; i++){
				figures.add(new Figure(figuresDuration));
			}
		}
		else{
			for(int i = 0; i < time.getBeats().intValue(); i++){
				figures.add(time.getFigure().clone());
			}
		}
		return figures;
	}
	
	/**
	 * Fill the barItem duration field of the items of the vector.
	 * The duration depends of the actual time & number of items in a bar.
	 * @param bar Bar with chords & silences
	 */
	public int barFigures(Vector<Chord> items){
		
		int barsAdded = 1;
		
		double factor = time.getBeats().doubleValue()/items.size();
		double figuresDuration = factor * time.getFigure().duration();
		
		if(Figure.validDuration(figuresDuration)){
			for(int i = 0; i < items.size(); i++){
				items.elementAt(i).setDuration(new Duration((new Figure(figuresDuration))));
			}
		}
		else{
			for(int i = 0; (i < items.size()) || (i%time.getBeats().intValue() != 0); i++){
				if(i >= items.size()){
					items.add(Chord.genSilence());
					barsAdded = items.size()/time.getBeats().intValue();
				}
				items.elementAt(i).setDuration(new Duration(time.getFigure().clone()));
			}
		}
		return barsAdded;
	}

}
