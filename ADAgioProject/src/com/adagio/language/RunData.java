package com.adagio.language;


/**
 * Storages the knowledge to create the Lilypond's file
 * @author Wungo
 */
public class RunData {
	
	private static final int A = 0;
	private static final int B = 1;
	private static final int C = 2;
	private static final int D = 3;
	private static final int E = 4;
	private static final int F = 5;
	private static final int G = 6;
	
	// Notes that will be played
	public String notes;
	
	// Mode relative
	public String relative;
	
	// Clefe (bass,treble...)
	public String clef;
		
	/**
	 * Class constructor
	 */
	public RunData(){
		
		notes = "";
		relative = null;
		clef = "treble";
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getRelative() {
		return relative;
	}


	public void setRelative(String relative) {
		this.relative = relative;
	}


	public String getClef() {
		return clef;
	}


	public void setClef(String clef) {
		this.clef = clef;
	}

	/**
	 * Calculate the shortest distance between two notes
	 * @param note1 Previous note
	 * @param note2 Next note
	 * @return Int value in the range [-3,3]
	 */
	public int noteDistance(String note1, String note2){

		int result = 0;
		int result1 = 0;
		int result2 = 0;
		int data1 = 0;
		int data2 = 0;
		
		if(note1.equals("A")){
			data1 = A;
		}
		else if(note1.equals("B")){
			data1 = B;
		}
		else if(note1.equals("C")){
			data1 = C;
		}
		else if(note1.equals("D")){
			data1 = D;
		}
		else if(note1.equals("E")){
			data1 = E;
		}
		else if(note1.equals("F")){
			data1 = F;
		}
		else if(note1.equals("G")){
			data1 = G;
		}
		
		if(note2.equals("A")){
			data2 = A;
		}
		else if(note2.equals("B")){
			data2 = B;
		}
		else if(note2.equals("C")){
			data2 = C;
		}
		else if(note2.equals("D")){
			data2 = D;
		}
		else if(note2.equals("E")){
			data2 = E;
		}
		else if(note2.equals("F")){
			data2 = F;
		}
		else if(note2.equals("G")){
			data2 = G;
		}
	
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
	
	/*
	 * Obtain the change in octave introduced by the RELATIVE sentence
	 * @return Int value in the range [-5,5]
	 */
	public int octaveFromRelative(){
		if(this.relative == "c"){ return 0;}
		else if(this.relative.equals("c'")){ return 1;}
		else if(this.relative.equals("c''")){ return 2;}
		else if(this.relative.equals("c'''")){ return 3;}
		else if(this.relative.equals("c''''")){ return 4;}
		else if(this.relative.equals("c'''''")){ return 5;}
		else if(this.relative.equals("c,")){ return -1;}
		else if(this.relative.equals("c,,")){ return -2;}
		else if(this.relative.equals("c,,,")){ return -3;}
		else if(this.relative.equals("c,,,,")){ return -4;}
		else if(this.relative.equals("c,,,,,")){ return -5;}
		return 0;
	}
}
