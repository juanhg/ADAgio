package com.adagio.language.instruments;

import java.util.Vector;

import org.modelcc.IModel;

import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class LimitedPolyphonicInstrument extends PolyphonicInstrument implements IModel {

	public LimitedPolyphonicInstrument(){
		super();
	}
	
	public LimitedPolyphonicInstrument(Timbre timbre, Register registers[]){
		super(timbre,registers);
	}
	
	@Override
	public Vector<AbsoluteMusicNote> transportToRegister() {
		// TODO Auto-generated method stub
		return null;
	}
}
