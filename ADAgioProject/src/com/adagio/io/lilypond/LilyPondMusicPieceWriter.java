package com.adagio.io.lilypond;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.adagio.io.MusicPieceWriter;
import com.adagio.language.MusicPiece;
import com.adagio.language.RunData;
import com.adagio.language.chords.Chord;
import com.adagio.language.chords.intervals.Interval;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.notealterations.Alteration;

public class LilyPondMusicPieceWriter extends MusicPieceWriter {

	@Override
	public void write(MusicPiece m, PrintWriter out) {
		String composition = "";
		RunData data = new RunData();
		
		m.run(data);
		
		composition = this.translate(data);
		System.out.print(composition);
		out.print(composition);
		
	}
	
	public static void writeMusicPiece(MusicPiece m,PrintWriter out){
	    LilyPondMusicPieceWriter writer = new LilyPondMusicPieceWriter();
	    writer.write(m,out);
	}
	
	private String translate(RunData data){
		String composition = "";
		
		composition += "{\n";
		composition += ("\\clef " + data.getClef() + "\n");
		composition += ("\\time " + data.getTime().toString() + "\n");
		for(int i = 0; i < data.notesBar.size(); i++){
			composition += translateAbsoluteMusicNote(data.notesBar.elementAt(i));
			composition += Integer.toString(data.getTime().defaultNoteDuration());
			composition += " ";
		}
		composition += "\n}\n";
		
		composition += "{\n";
		composition += ("\\clef " + data.getClef() + "\n");
		composition += ("\\time " + data.getTime().toString() + "\n");
		for(int i = 0; i < data.chordsBar.size(); i++){
			composition += translateChord(data.chordsBar.elementAt(i),data);
			composition += Integer.toString(data.getTime().defaultNoteDuration());
			composition += " ";
		}
		composition += "\n}\n";
		return composition;
	}
	
	/*
	 * Receives a chord with an absolute-fundamental-note and translates it 
	 * using its definition in "data".
	 * Note: Need that the bassNote as a AbsoluteMusicNote
	 */
	public static String translateChord(Chord chord, RunData data){
		String composition = "";
		List<Interval> intervals = data.getChordsDB().get(chord.getIdentifier());
		List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();
		AbsoluteMusicNote bassNote = (AbsoluteMusicNote) chord.getBassNote();
		
	    //Recollects the notes result to apply the interval to the fundamental note
		for(int i = 0; i < intervals.size();i++){
			aNotes.add(intervals.get(i).Apply(chord.getNote(), data));		
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

}
