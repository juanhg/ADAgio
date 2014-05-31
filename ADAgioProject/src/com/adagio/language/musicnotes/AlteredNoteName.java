package com.adagio.language.musicnotes;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Priority;

import com.adagio.language.musicnotes.notealterations.Alteration;


@Priority(value = 1)
public class AlteredNoteName extends MusicNoteName implements IModel {

	private BasicNoteName basicNoteName;
	private Alteration alteration;
	
	@Constraint
	boolean basicNoteIsNotASilence(){
		if(basicNoteName.isSilence()){
			return false;
		}
		return true;
	}
	
	public AlteredNoteName(){}
	
	public AlteredNoteName(BasicNoteName bName, Alteration alteration){
		this.basicNoteName = bName;
		this.alteration = alteration;
	}
	
	public BasicNoteName getBasicNoteName() {
		return basicNoteName;
	}

	public void setBasicNoteName(BasicNoteName basicNoteName) {
		this.basicNoteName = basicNoteName;
	}

	public Alteration getAlteration() {
		return alteration;
	}

	public void setAlteration(Alteration alteration) {
		this.alteration = alteration;
	}

	@Override
	public BasicNoteName getBaseNoteName() {
		// TODO Auto-generated method stub
		return this.basicNoteName;
	}

	@Override
	public MusicNoteName clone() {
		AlteredNoteName aNoteName = new AlteredNoteName();
		aNoteName.setAlteration(this.alteration.clone());
		aNoteName.setBasicNoteName((BasicNoteName)this.basicNoteName.clone());
		return aNoteName;
	}

	@Override
	public String toString() {
		return this.getBasicNoteName().toString() + this.getAlteration().toString();
	}

	@Override
	public boolean isSilence() {
		return basicNoteName.isSilence();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlteredNoteName other = (AlteredNoteName) obj;
		if (alteration == null) {
			if (other.alteration != null)
				return false;
		} else if (!alteration.equals(other.alteration))
			return false;
		if (basicNoteName == null) {
			if (other.basicNoteName != null)
				return false;
		} else if (!basicNoteName.equals(other.basicNoteName))
			return false;
		return true;
	}
}
