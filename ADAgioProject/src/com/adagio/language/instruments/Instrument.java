package com.adagio.language.instruments;

import java.util.Vector;

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
	
	public abstract Vector<AbsoluteMusicNote> transportToRegister();

	public Timbre getTimbre() {
		return timbre;
	}

	public Register[] getRegisters() {
		return registers;
	}
	
	
	
	
}
