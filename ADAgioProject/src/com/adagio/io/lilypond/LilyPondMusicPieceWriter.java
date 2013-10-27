package com.adagio.io.lilypond;

import java.io.PrintWriter;
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
		for(int i = 0; i < data.notesBar.size(); i++){
			composition += this.translateAbsoluteMusicNote(data.notesBar.elementAt(i)) + " ";
		}
		composition += "\n}\n";
		
		composition += "{\n";
		composition += ("\\clef " + data.getClef() + "\n");
		for(int i = 0; i < data.chordsBar.size(); i++){
			composition += this.translateChord(data.chordsBar.elementAt(i),data) + " ";
		}
		composition += "\n}\n";
		return composition;
	}
	
	private String translateChord(Chord chord, RunData data){
		String composition = "";
		List<Interval> intervals = data.getChordsDB().get(chord.getIdentifier());
		AbsoluteMusicNote aNote;
		
		composition += "<";
		for(int i = 0; i < intervals.size();i++){
			aNote = intervals.get(i).Apply(chord.getNote(), data);
			composition += this.translateAbsoluteMusicNote(aNote);
			
			if(i != intervals.size()-1){
				composition += " ";
			}
		}
		composition += ">";
		
		return composition;
	}
	
	private String translateAbsoluteMusicNote(AbsoluteMusicNote aNote){
		String composition = aNote.getBasicNoteNameString().toLowerCase();
		int intOctave = aNote.getOctave().intValue();
		Alteration alteration = aNote.getMusicNoteName().getAlteration();
		
		if(alteration != null){
			composition += this.translateAlteration(alteration);
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

}
