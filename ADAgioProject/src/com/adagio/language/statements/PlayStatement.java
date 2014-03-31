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
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.language.bars.Bar;
import com.adagio.structures.Lyrics;
import com.adagio.structures.Melody;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

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

		if(bars != null){
			for(int i = 0; i <bars.length; i++){
				listener.musicPlay(new MusicPlayStatementEvent(this, bars[i]));
			}
		}
	}

}
