package com.adagio.language.statements;
import java.util.ArrayList;
import java.util.List;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Setup;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.MelodyLyricsEvent;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.language.bars.Bar;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.structures.Lyrics;
import com.adagio.structures.Melody;

@Prefix("(?i)play")
public class PlayStatement extends Statement implements IModel {


	@Prefix("\\|?")
	@Suffix("\\|?")
	@Separator("\\|")
	@Multiplicity(minimum = 1)
	Bar [] bars;

	PlayComponent [] playComponents;

	//Aux Structures
	List<Melody> melodies;
	List<Lyrics> lyricsList;

	/**
	 * Loads in aux Structures the melodies and the lyrics
	 */
	@Setup
	public void setup() {
		melodies = new ArrayList<Melody>();
		lyricsList = new ArrayList<Lyrics>();

		if(playComponents != null){
			for(PlayComponent current: playComponents){
				if(current.isMelody()){
					MelodyComponent component = (MelodyComponent) current;
					Melody melody = new Melody(component.getIdentifier(), component.getMBars());
					melodies.add(melody);
				}
				else{
					LyricsComponent component = (LyricsComponent) current;
					Lyrics lyrics = new Lyrics(component.getIdentifier(), component.getVerses());
					lyricsList.add(lyrics);
				}
			}
		}
		
		for(Lyrics current: lyricsList){
			Verse [] verses = current.getVerses();
			Verse [] aux;
			int count = 0;
			
			for(int i = 0; i < verses.length; i++){
				if(verses[i] != null){
					count++;
				}
			}
			
			aux = new Verse[count];
			count = 0;
			
			for(int i = 0; i < verses.length; i++){
				if(verses[i] != null){
					aux[count] = verses[i];
					count++;
				}
			}
			
			current.setVerses(aux);
			
		}
	}

	@Constraint 
	public boolean noRepeatedVoices(){
		
		if(lyricsList != null){
			if(melodies != null){
				if(lyricsList.size() > melodies.size()){
					System.err.println("Error (Play Statement): More Lyrics component than Melody components");
					return false;
				}
			}
			else{
				System.err.println("Error (Play Statement): More Lyrics component than Melody components");
				return false;
			}
		}
		
		if(melodies != null && melodies.size() > 1){
			for(int i = 0; i < melodies.size(); i++){
				for(int j = i+1; j < melodies.size(); j++ ){
					if(melodies.get(i).getIdentifier().equals(melodies.get(j).getIdentifier())){
						System.err.println("Error (Play Statement): Melody Channel replied");
						return false;
					}
				}
			}
		}
		
		if(lyricsList != null && lyricsList.size() > 1){
			for(int i = 0; i < lyricsList.size(); i++){
				for(int j = i+1; j < melodies.size(); j++ ){
					if(lyricsList.get(i).getIdentifier().equals(lyricsList.get(j).getIdentifier())){
						System.err.println("Error (Play Statement): Lyrics Channel replied");
						return false;
					}
				}
			}
		}
		
		boolean contained = false;
		for(Lyrics currentL: lyricsList){
			for(Melody currentM: melodies){
				if(currentL.getIdentifier().equals(currentM.getIdentifier())){
					contained = true;
				}
			}
			if(contained == false){
				System.err.println("Error (Play Statement): Trying to insert lyrics whithout play melody");
				return false;
			}
			contained = false;
		}
		
		return true;
	}

	@Override
	/**
	 * Takes chords, transforms the fundamental note in AbsoluteNote, and stores them
	 * in the attribute "chords". Each absolute-fundamental-note updates the Relative note.
	 */
	public void run(MusicEventListener listener) {

		ChannelIdentifier idMelody, idLyrics;
		Lyrics selectedLyrics = null;
		
		// Melody & Lyrics
		if(playComponents != null){
			for(Melody currentMelody: melodies){
				for(Lyrics currentLyrics: lyricsList){
					idMelody = currentMelody.getIdentifier();
					idLyrics = currentLyrics.getIdentifier();
					
					if(idMelody.equals(idLyrics)){	
						selectedLyrics = currentLyrics;
						break;
					}
				}
				if(selectedLyrics == null){
					listener.melodyPlay(new MelodyLyricsEvent(this, currentMelody, null));
				}
				else{
					listener.melodyLyricsPlay(new MelodyLyricsEvent(this, currentMelody, selectedLyrics));
					selectedLyrics = null;
				}
			}
		}
		
		
		// Harmony
		if(bars != null){
			for(int i = 0; i <bars.length; i++){
				listener.harmonyPlay(new MusicPlayStatementEvent(this, bars[i]));
			}
		}
	}

}
