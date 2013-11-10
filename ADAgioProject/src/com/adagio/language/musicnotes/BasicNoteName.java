package com.adagio.language.musicnotes;

import java.util.Vector;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Priority;
import org.modelcc.Value;

import com.adagio.language.musicnotes.notealterations.Alteration;

@Priority(value = 2)
@Pattern(regExp="A|B|C|D|E|F|G")
public class BasicNoteName extends MusicNoteName implements IModel {

	@Value
	String value;
	
	public static final int NUM_NOTES = 7;

	//TODO change this to map
	private static Vector<String> pattern;
	static {
		pattern = new Vector<String>();
		pattern.add("A");
		pattern.add("B");
		pattern.add("C");
		pattern.add("D");
		pattern.add("E");
		pattern.add("F");
		pattern.add("G");
	}
	

	public static final int A = 0;
	public static final int B = 1;
	public static final int C = 2;
	public static final int D = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int G = 6;
	
	
	public BasicNoteName(){
		
	}
	
	public BasicNoteName(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	@Override
	public BasicNoteName getBaseNoteName() {
		return this;
	}

	@Override
	public Alteration getAlteration() {
		return null;
	}

	@Override
	public MusicNoteName clone() {
		BasicNoteName bName = new BasicNoteName();
		bName.setValue(this.value);
		return bName;
	}

	
	
	public static Vector<String> getPattern() {
		return pattern;
	}
	
	/**
	 * Return the int value of a BasicNoteName
	 * @param bName
	 * @return
	 */
	public static int nameToInt(BasicNoteName bName){
		
		String value = bName.getValue();
		
		if(value.equals("A")){
			return A;
		}
		else if (value.equals("B")){
			return B;
		}
		else if (value.equals("C")){
			return C;
		}
		else if (value.equals("D")){
			return D;
		}
		else if (value.equals("E")){
			return E;
		}
		else if (value.equals("F")){
			return F;
		}
		else if (value.equals("G")){
			return G;
		}
		return -1;
	}
	
	/**
	 * Return a BasicNoteName 
	 * @param number
	 * @return
	 */
	public static BasicNoteName intToName(int number){
		BasicNoteName bName = new BasicNoteName();
		
		if(number == A){
			bName.setValue("A");
		}
		else if (number == B){
			bName.setValue("B");
		}
		else if (number == C){
			bName.setValue("C");
		}
		else if (number == D){
			bName.setValue("D");
		}
		else if (number == E){
			bName.setValue("E");
		}
		else if (number == F){
			bName.setValue("F");
		}
		else if (number == G){
			bName.setValue("G");
		}
		return bName;
	}
	
	/*
	 * Calculate the number of semitones between a note and the next
	 */
	public int semitonesToNextNote(){
		if(this.getValue().equals("E") || this.getValue().equals("B")){
			return 1;
		}
		else{
			return 2;
		}
			
	}
	
	public int semitonesToPreviousNote(){
		if(this.getValue().equals("C") || this.getValue().equals("F")){
			return 1;
		}
		else{
			return 2;
		}
	}
	
	/**
	 * Calculate the immediate superior note Name
	 * @return The next BasicNoteName
	 */
	public BasicNoteName next(){
		int intValue = nameToInt(this.getBaseNoteName());
		intValue++;
		intValue = intValue%7;
		return intToName(intValue);
	}
	
	/**
	 * Calculate the immediate inferior note Name
	 * @return The next BasicNoteName
	 */
	public BasicNoteName previous(){
		int intValue = nameToInt(this.getBaseNoteName());
		intValue--;
		if(intValue < 0){
			intValue = 7 + intValue;
		}
		return intToName(intValue);
	}
	
	/**
	 * Calculate the shortest distance between two notes
	 * @param note1 Previous note
	 * @param note2 Next note
	 * @return Int value in the range [-3,3]
	 */
	public int shortestDistance(BasicNoteName note2){
				
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		int data1 = 0;
		int data2 = 0;
				
		data1 = nameToInt(this);
		data2 = nameToInt(note2);
		
		result1 = Math.abs(data1 - data2);
		result2 = Math.abs(7 - result1)%7;
		
		if(result1 < result2){
			if(data1 > data2){
				result = -result1;
			}
			else{
				result = result1;
			}
		}
		else{
			if(data1 < data2){
				result = -result2;
			}
			else{
				result = result2;
			}
		}
		
		return result;
	}

	/**
	 * Distance between two notes in the scale C-D-E-F-G-A-B (same octave)
	 * @param name
	 * @return Integer with positive or negative distance
	 */
	int distance(BasicNoteName name){
		int data1 = 0;
		int data2 = 0;
				
		data1 = nameToInt(this);
		data2 = nameToInt(name);
		
		if(data1 == A || data1 == B){
			data1 +=5;
		}
		else{
			data1 -= 2;
		}
		
		if(data2 == A || data2 == B){
			data2 += 5;
		}
		else{
			data2 -= 2;
		}
		return data2-data1;
	}

	@Override
	public boolean equals(MusicNoteName name) {
		if(this.getClass().equals(name.getClass())){
			return this.value.equals(name.getBaseNoteName().value);
		}
		return false;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
