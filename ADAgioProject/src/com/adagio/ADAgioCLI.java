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
			//TODO Modify to read the input argument
			String fileName = "D:\\ETSIIT\\PFC\\entrada.adg";
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
			MusicPiece result = parser.parse(new BufferedReader(new FileReader(fileName)));

			// Print output.
			System.out.print(result.toString());
			
			//TODO Modify to change the extension of input argument, and create the output
			PrintWriter out = (new PrintWriter("D:\\ETSIIT\\PFC\\salida.ly"));
			out.print(result);
			out.close();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
