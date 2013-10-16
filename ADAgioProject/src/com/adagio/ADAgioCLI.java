package com.adagio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

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
	
			String inFileName = args[0];
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

			// Print output.
			System.out.print(result.toString());
			
			PrintWriter out = (new PrintWriter(outFileName));
			out.print(result);
			out.close();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
