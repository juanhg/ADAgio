package com.adagio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.*;

import org.modelcc.io.ModelReader;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.lexer.recognizer.PatternRecognizer;
import org.modelcc.lexer.recognizer.regexp.RegExpPatternRecognizer;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

public class ADAgioCLI {

	public static void main(String [] args){
		try {
	
			String inFileName = args[4];
			String outFileName = inFileName.replace(".adg", ".ly");
			ModelReader reader = new JavaModelReader(MusicPiece.class);

			// Read the language model.
			Model model = reader.read();

			Set<PatternRecognizer> ignore = new HashSet<PatternRecognizer>();
			ignore.add(new RegExpPatternRecognizer("#.*\n"));
			ignore.add(new RegExpPatternRecognizer("( |\n|\t|\r)+"));

			// Generate a parser from the model.
			@SuppressWarnings("unchecked")
			Parser<MusicPiece> parser = ParserFactory.create(model,ignore);

			// Parse an input string.
			MusicPiece result = parser.parse(new BufferedReader(new FileReader(inFileName)));
			
			System.out.println("Fichero: " + inFileName);
			PrintWriter out = (new PrintWriter(outFileName));
			LilyPondMusicPieceWriter.writeMusicPiece(result,out);
			out.close();
			
			System.out.println("\n\nProgram: " + inFileName);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
