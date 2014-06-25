package com.adagio;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.modelcc.io.ModelReader;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.lexer.recognizer.PatternRecognizer;
import org.modelcc.lexer.recognizer.regexp.RegExpPatternRecognizer;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.MusicPiece;

/**
 * The entry point of the translation process. 
 */

public class ADAgioCLI {

	public static void main(String [] args){
		try {
			
		
			if(args.length == 0){
				System.out.println();
				System.out.println(".adg file is required: ");
				System.out.println("java -jar ADAgio.jar <file.adg>");
				System.out.println("java -jar ADAgio.jar <file1.adg> ... <fileN.adg>");
				System.exit(0);
			}
			
			ModelReader reader = new JavaModelReader(MusicPiece.class);
			
			// Read the language model.
			Model model = reader.read();

			Set<PatternRecognizer> ignore = new HashSet<PatternRecognizer>();
			ignore.add(new RegExpPatternRecognizer("//[^\n]*(\n|$)"));
			ignore.add(new RegExpPatternRecognizer("( |\n|\t|\r)+"));

			// Generate a parser from the model.
			@SuppressWarnings("unchecked")
			Parser<MusicPiece> parser = ParserFactory.create(model,ignore);
			

			//Read the Music Lore information and mix it with input.
			InputStream inStream = ADAgioCLI.class.getResourceAsStream("MusicTheory.mth");
			if(inStream == null){
				System.err.println("Error reading MusicTheory");
			}

			for(String current: args){
				String [] preprocessedFiles = AdagioPreprocessor.preprocess("MusicTheory.mth", current);
				String finalInput = AdagioLinker.link(preprocessedFiles[0], preprocessedFiles[1]);
				String outFileName = current.replaceAll("\\.adg$", ".ly");
				if (outFileName.equals(current)) {
					outFileName = current + ".ly";
				}
				
				// Parse an input string.
				MusicPiece result = parser.parse(finalInput);

				PrintWriter out = (new PrintWriter(outFileName));
				LilyPondMusicPieceWriter.writeMusicPiece(result,out);
				out.close();

				System.out.println(outFileName);
				System.gc();
			}	

		} catch (Exception e) {
			System.err.println("Syntax error");
			e.printStackTrace();
			System.exit(1);
		}

	}
	
}
