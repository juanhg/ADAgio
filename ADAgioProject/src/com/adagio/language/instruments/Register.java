package com.adagio.language.instruments;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class Register implements IModel {

	private AbsoluteMusicNote lowerNote;
	@Prefix({"[ |\n|\r|\t]+","(?i)to", "[ |\n|\r|\t]+"})
	private AbsoluteMusicNote higherNote;
	
	//True if register has been used to play an AbsoluteMusicNote
	//Useful with LimitedPolyphonicInstruments
	boolean used = false;
	
	@Constraint
	boolean validRange(){
		if(lowerNote.semitonesTill(higherNote) >= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
		
	/**
	 * Default Constructor. Piano register.
	 */
	public Register(){
		this.lowerNote = new AbsoluteMusicNote(0, "A");
		this.higherNote = new AbsoluteMusicNote(8, "C");
		this.used = false;
	}
	
	/**
	 * Parameters constructor
	 * @param lower
	 * @param higher
	 */
	public Register(AbsoluteMusicNote lower, AbsoluteMusicNote higher){
		this.lowerNote = lower;
		this.higherNote = higher;
		this.used = false;
	}
	
	/**
	 * Copy constructor
	 * @param other
	 */
	public Register(Register other){
		this.lowerNote = other.higherNote.clone();
		this.higherNote = other.lowerNote.clone();
		this.used = false;
	}
	
	/**
	 * Checks if an AbsoluteNote belong to the register
	 * @param aNote AbsoluteNote to check
	 * @return True if the AbsoluteNote belong to the register. False in other case.
	 */
	public boolean belong(AbsoluteMusicNote aNote){
		
		if(aNote.isSilence()){ return true; }
		
		int semitonesToHigher = aNote.semitonesTill(this.higherNote);
		int semitonesToLower = aNote.semitonesTill(this.lowerNote);
		
		if(semitonesToHigher >= 0 && semitonesToLower <= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Transport the AbsoluteMusicNote to the register. If is no possible, an
	 * estimated AbsoluteMusicNote is generated.
	 * @param aNote Absolute note to transport to the register.
	 * @return The absolute note transported if is possible. An estimated one
	 * in other case.
	 */
	public AbsoluteMusicNote aNoteToRegister(AbsoluteMusicNote aNote){
		AbsoluteMusicNote aNoteTransported = aNote.clone();
		
		if(aNote.isSilence()){
			return aNoteTransported;
		}
		
		int semitonesToHigher = aNoteTransported.semitonesTill(this.higherNote);
		int semitonesToLower = aNoteTransported.semitonesTill(this.lowerNote);
		
		
		while(semitonesToLower > 0){
			aNoteTransported.increaseOctave();
			semitonesToLower = aNoteTransported.semitonesTill(this.lowerNote);
		}
		
		semitonesToHigher = aNoteTransported.semitonesTill(this.higherNote);
		
		while(semitonesToHigher < 0){
			aNoteTransported.decreaseOctave();
			semitonesToHigher = aNoteTransported.semitonesTill(this.higherNote);
		}
		
		semitonesToLower = aNoteTransported.semitonesTill(this.lowerNote);
		
		if(semitonesToLower > 0){
			
			System.err.println("Warning 5: The note " + aNote.toString() +
					" can't be transported into the register. "
					+ "An estimated one has been generated.");
		}
		
		return aNoteTransported;
	}
	
	public Register clone(){
		return new Register(this);
	}


	public boolean isUsed() {
		return used;
	}


	public void setUsed(boolean used) {
		this.used = used;
	}


	public AbsoluteMusicNote getLowerNote() {
		return lowerNote;
	}
	
	@Override
	public String toString(){
		return "" + lowerNote + "-" + higherNote;
	}

	//TODO cuidao, equal de absoluteMusicNotes!!
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		if (higherNote == null) {
			if (other.higherNote != null)
				return false;
		} else if (!higherNote.equals(other.higherNote))
			return false;
		if (lowerNote == null) {
			if (other.lowerNote != null)
				return false;
		} else if (!lowerNote.equals(other.lowerNote))
			return false;
		if (used != other.used)
			return false;
		return true;
	}
	
}
