package com.adagio.instruments;

import java.util.List;

import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;
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
	 * @param aNotes List of AbsoluteMusicNote(s) to transport
	 * @return A List of AbsoluteMusicNotes inside (or close, if is not posible inside) the registers of the instrument
	 */
	public abstract List<AbsoluteMusicNote> aNotesToInstrumentRegister(List<AbsoluteMusicNote> aNotes);
	
	
	
}
