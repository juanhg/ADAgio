package com.adagio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Preprocessor of Adagio. 
 * Makes Alterations in the input program to support the translation process.
 * @author Juan Hernández 
 */
public class AdagioPreprocessor {
	static String library;
	static String program;
	
	/**
	 * 
	 * @param libraryPath Path of the main library of the system. (MusicTheory.thm)
	 * @param programPath Original input program path.
	 * @return the input program processed.
	 * @throws IOException
	 */
	public static String [] preprocess(String libraryPath, String programPath) throws IOException{
		
		InputStream inStream = AdagioLinker.class.getResourceAsStream(libraryPath);
		library = streamToString(inStream);
		program = fileToString(programPath, StandardCharsets.UTF_8);
		String [] processedFiles = new String[2];
		String regexp = "(?i)(Include)[\\s]*\"[^\"]+\"";
		
		Pattern includePattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);

		Matcher matcher = includePattern.matcher(program);
		while (matcher.find()) {
			String include = matcher.group();
			
			Pattern pathPattern = Pattern.compile("\"[^\"]*\"");
			Matcher pathMatcher = pathPattern.matcher(include);
			if(pathMatcher.find()){
				String path = pathMatcher.group();
				path = path.replaceAll("\"", "");
				path = path.replaceAll("\\./", "");
				path = path.replaceAll("\\.\\\\", "");
				library += fileToString(path, StandardCharsets.UTF_8);
				program = program.replaceFirst(regexp, "");
			}
		}
		processedFiles[0] = library;
		processedFiles[1] = program;
		return processedFiles;
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
}
