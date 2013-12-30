package com.adagio.language.instruments.features;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class Register implements IModel {

	private AbsoluteMusicNote lowerNote;
	@Prefix("(?i)to")
	private AbsoluteMusicNote higherNote;
	
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
	}
	
	/**
	 * Parameters constructor
	 * @param lower
	 * @param higher
	 */
	public Register(AbsoluteMusicNote lower, AbsoluteMusicNote higher){
		this.lowerNote = lower;
		this.higherNote = higher;
	}
	
	/**
	 * Copy constructor
	 * @param other
	 */
	public Register(Register other){
		this.lowerNote = other.higherNote.clone();
		this.higherNote = other.lowerNote.clone();
	}
	
	/**
	 * Checks if an AbsoluteNote belong to the register
	 * @param aNote AbsoluteNote to check
	 * @return True if the AbsoluteNote belong to the register. False in other case.
	 */
	public boolean belong(AbsoluteMusicNote aNote){
		
		int semitonesToHigher = aNote.semitonesTill(this.higherNote);
		int semitonesToLower = aNote.semitonesTill(this.lowerNote);
		
		if(semitonesToHigher >= 0 && semitonesToLower >= 0){
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
	
}
