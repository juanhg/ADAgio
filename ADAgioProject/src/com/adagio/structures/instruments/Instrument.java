package com.adagio.structures.instruments;

import java.util.Arrays;
import java.util.List;

import com.adagio.language.instruments.Register;
import com.adagio.language.instruments.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;


public abstract class Instrument{

	Timbre timbre;
	Register registers[];
	
	public Instrument(){
		timbre = new Timbre();
		this.registers = new Register[1];
		//Initializes with piano grand register
		registers[0] = new Register();
	}
	
	public Instrument(Timbre timbre, Register registers[]){
		this.timbre = timbre;
		this.registers = registers;
	}
	
	public Instrument(Timbre timbre){
		this.timbre = timbre;
		this.registers = new Register[1];
		registers[0] = new Register();
	}
	
	public Timbre getTimbre() {
		return timbre;
	}

	public Register[] getRegisters() {
		return registers;
	}
	
	/**
	 * @return The number of register not used
	 */
	public int notUsedRegisters(){
		int count = 0;
		for(Register current: registers){
			if(current.isUsed() == false){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Reset to "false" all used-fields of the registers
	 */
	public void resetRegisters(){
		for(Register current: registers){
			current.setUsed(false);
		}
	}
	
	/** -- ABSTRACTS -- **/
		
	/**
	 * Transport and AbsoluteMusicNote list that represent a chord to the register of the instrument
	 * @pre The first note must be the lower note.
	 * @param aNotes List of AbsoluteMusicNote(s) to transport. 
	 * @return A List of AbsoluteMusicNotes inside (or close, if is not posible inside) the registers of the instrument
	 */
	public abstract List<AbsoluteMusicNote> apply(List<AbsoluteMusicNote> aNotes);
	
	/**
	 * Checks if an AbsoluteNote belong to the registers of the instrument
	 * @param aNote AbsoluteNote to check
	 * @return True if the AbsoluteNote belong to a register. False in other case.
	 */
	public boolean belong(AbsoluteMusicNote aNote){
		for(Register current: registers){
			if(current.belong(aNote)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		String composition = "";
		composition += timbre.toString() + ": ";
		
		for(Register current: registers){
			composition += current.toString() + " ";
		}
		return composition;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instrument other = (Instrument) obj;
		if (!Arrays.equals(registers, other.registers))
			return false;
		if (timbre == null) {
			if (other.timbre != null)
				return false;
		} else if (!timbre.equals(other.timbre))
			return false;
		return true;
	}
}
