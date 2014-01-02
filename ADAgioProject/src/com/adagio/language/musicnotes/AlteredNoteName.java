package com.adagio.language.musicnotes;

import org.modelcc.IModel;
import org.modelcc.Priority;

import com.adagio.language.musicnotes.notealterations.Alteration;


@Priority(value = 1)
public class AlteredNoteName extends MusicNoteName implements IModel {

	BasicNoteName basicNoteName;
	Alteration alteration;
	
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
	public boolean equals(Object o) {

		if(o instanceof AlteredNoteName){
			AlteredNoteName name = (AlteredNoteName) o;
			if (this.getClass().equals(name.getClass())) {
				if (this.getBaseNoteName().equals(name.getBaseNoteName())
						&& this.alteration.getValue().equals(
								name.getAlteration().getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getBasicNoteName().toString() + this.getAlteration().toString();
	}
	
	

}
