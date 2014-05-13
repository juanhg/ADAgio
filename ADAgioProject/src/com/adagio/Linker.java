package com.adagio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Linker {

	List<Map<String,String>> definitions;

	String library;
	String program;

	String[] defType = {
			"chord", 
			"tempo", 
			"instrument", 
			"rhythm" 		 
	};

	private final int CHORD = 0;
	private final int TEMPO = 1;
	private final int INSTRUMENT = 2;
	private final int RHYTHM = 3;

	public Linker(String libraryPath, String programPath) throws IOException{
		InputStream inStream = Linker.class.getResourceAsStream(libraryPath);
		library = streamToString(inStream);
		program = fileToString(programPath, StandardCharsets.UTF_8);

		Map<String, String> instruments = new HashMap<String, String>();
		Map<String, String> tempos = new HashMap<String, String>();
		Map<String, String> rhythms = new HashMap<String, String>();
		Map<String, String> chords = new HashMap<String, String>();

		definitions = new ArrayList<Map<String, String>>();
		definitions.add(chords);
		definitions.add(tempos);
		definitions.add(instruments);
		definitions.add(rhythms);

		for(int i = 0; i < defType.length; i++){
			Pattern defInstrument = Pattern.compile("(?i)(Define( |\t|\\s)+" +defType[i]+"[^;]*;)", Pattern.CASE_INSENSITIVE
					| Pattern.DOTALL);

			Matcher m = defInstrument.matcher(library);
			while (m.find()) {
				String definition = m.group();	 

				Pattern idPattern = Pattern.compile("\"[^\"]*\"");
				Matcher idMatcher = idPattern.matcher(definition);
				if(idMatcher.find()){
					String id = idMatcher.group().toLowerCase();
					id = id.replaceAll("\"", "");
					definitions.get(i).put(id, definition);
				}
			}
		}
	}

	public String link(){
		String linkedProgram = "";

		Map<String, String> acordes = definitions.get(CHORD);
		Iterator it = acordes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			linkedProgram  += e.getValue() + "\n\n";
		}

		Pattern instrumentPattern = Pattern.compile("(?i)(instrument)[\\s]*=[\\s]*[\\S]+", Pattern.CASE_INSENSITIVE);
		Matcher m = instrumentPattern.matcher(program);
		while (m.find()) {
			String instrument = m.group();
			instrument = instrument.replaceAll("\\s", "");
			instrument = instrument.replaceAll(";", "");
			instrument = instrument.replaceAll("(?i)instrument=", "");
			instrument = instrument.toLowerCase();
			Map<String, String> mapa = definitions.get(INSTRUMENT);
			if(mapa.containsKey(instrument)){
				linkedProgram += mapa.get(instrument) + "\n\n";
				mapa.remove(instrument);
			}

		}

		Pattern rhythmPattern = Pattern.compile("(?i)(rhythm)[\\s]*=[\\s]*[\\S]+", Pattern.CASE_INSENSITIVE);
		Matcher rhythmMatcher = rhythmPattern.matcher(program);
		while (rhythmMatcher.find()) {
			String rhythm = rhythmMatcher.group();
			rhythm = rhythm.replaceAll("\\s", "");
			rhythm = rhythm.replaceAll(";", "");
			rhythm = rhythm.replaceAll("(?i)rhythm=", "");
			rhythm = rhythm.toLowerCase();
			Map<String, String> mapa = definitions.get(RHYTHM);
			if(mapa.containsKey(rhythm)){
				linkedProgram += mapa.get(rhythm) + "\n\n";
				mapa.remove(rhythm);
			}

		}
		if(definitions.get(RHYTHM).containsKey("defaultrhythm")){
			linkedProgram += definitions.get(RHYTHM).get("defaultrhythm") + "\n\n";
		}
		
		Pattern tempoPattern = Pattern.compile("(?i)(tempo)[^;]*;", Pattern.CASE_INSENSITIVE);
		Matcher tempoMatcher = tempoPattern.matcher(program);
		while (tempoMatcher.find()) {
			String tempo = tempoMatcher.group();
			tempo = tempo.replaceAll("\\s", "");
			tempo = tempo.replaceAll(";", "");
			tempo = tempo.replaceAll("(?i)tempo", "");
			tempo = tempo.toLowerCase();
			Map<String, String> mapa = definitions.get(TEMPO);
			if(mapa.containsKey(tempo)){
				linkedProgram += mapa.get(tempo) + "\n\n";
				mapa.remove(tempo);
			}

		}

		linkedProgram += "\n\n" + program;
		return linkedProgram;
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
