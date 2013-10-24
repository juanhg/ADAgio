package com.adagio.io.lilypond;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import com.adagio.io.MusicPieceWriter;
import com.adagio.language.MusicPiece;
import com.adagio.language.RunData;
import com.adagio.language.Sentence;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.notealterations.Alteration;

public class LilyPondMusicPieceWriter extends MusicPieceWriter {

	@Override
	public void write(MusicPiece m, PrintWriter out) {
		String composition = "";
		RunData data = new RunData();
		
		for(Sentence current: m.getSentences()){
			current.run(data);
		}
		
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
		for(int i = 0; i < data.bars.size(); i++){
			composition += this.translateAbsoluteMusicNote(data.bars.elementAt(i)) + " ";
		}
		composition += "\n}\n";
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
