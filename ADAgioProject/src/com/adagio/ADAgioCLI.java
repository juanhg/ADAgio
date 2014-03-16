package com.adagio;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

public class ADAgioCLI {

	public static void main(String [] args){
		int i = 0;
		
		try {
			
			System.out.println();
			
			if(args.length == 0){
				System.out.println(".adg file is required: ");
				System.out.println("java -jar ADAgio.jar <file.adg>");
				System.out.println("java -jar ADAgio.jar <file1.adg> ... <fileN.adg>");
				System.exit(0);
			}
			
			ModelReader reader = new JavaModelReader(MusicPiece.class);
			
			// Read the language model.
			Model model = reader.read();

			Set<PatternRecognizer> ignore = new HashSet<PatternRecognizer>();
			ignore.add(new RegExpPatternRecognizer("#.*\n"));
			ignore.add(new RegExpPatternRecognizer("( |\n|\t|\r)+"));

			// Generate a parser from the model.
			@SuppressWarnings("unchecked")
			Parser<MusicPiece> parser = ParserFactory.create(model,ignore);
			

			//Read the Music Lore information and mix it with input.
			InputStream inStream = ADAgioCLI.class.getResourceAsStream("MusicTheory.mth");
			if(inStream == null){
				System.err.println("Error reading MusicTheory");
			}
			String musicTheory = streamToString(inStream);
			
			for(String current: args){
				if( i == 9){
					System.out.println();
				}
				i++;
				
				System.out.println("Processing " +  relativePath(current) + "...");
				String outFileName = current.replace(".adg", ".ly");

				String inputProgram = fileToString(current, StandardCharsets.UTF_8);
				String finalInput = musicTheory + inputProgram;
				
				// Parse an input string.
				MusicPiece result = parser.parse(finalInput);

				PrintWriter out = (new PrintWriter(outFileName));
				LilyPondMusicPieceWriter.writeMusicPiece(result,out);
				out.close();

				System.out.println("Generated " + relativePath(outFileName) + "\n");

			}	

		} catch (Exception e) {
			System.err.println("Syntax error");
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	static String streamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	public static String fileToString(String path, Charset encoding) 
			throws IOException 
			{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
			}
	
	public static String relativePath(String path){
		
		if(path.lastIndexOf("\\") > -1 && path.lastIndexOf("\\") < path.length()){
			return path.substring(path.lastIndexOf("\\")+1, path.length());
		}
		else if(path.lastIndexOf("/") > -1 && path.lastIndexOf("/") < path.length()){
			return path.substring(path.lastIndexOf("/")+1, path.length());
		}
		
		return path;
	}

}
