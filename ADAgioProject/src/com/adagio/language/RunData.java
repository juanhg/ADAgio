package com.adagio.language;


/**
 * Storages the knowledge to create the Lilypond's file
 * @author Wungo
 */
public class RunData {
	
	// Notes that will be played
	public String notes;
	
	// Mode relative
	public String relative;

	
	/**
	 * Class constructor
	 */
	public RunData(){
		
		notes = "";
		relative = null;
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


	


	

}
