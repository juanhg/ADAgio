package com.adagio.language.channels.channeloptions;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.IntegerModel;

import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.MusicChannelVolumeEvent;
import com.adagio.language.channels.ChannelIdentifier;

public class VolumeModifier extends StatusModifier implements IModel{

	@Prefix({"(?i)VOLUME","="})
	IntegerModel volume;
	
	public static final String PATTERN = "VOLUME";
	
	public final static int MAX_VOLUME = 100;
	public final static int MIN_VOLUME = 0;
	
	@Constraint
	boolean volumeInRange(){
		if(volume.intValue() >= MIN_VOLUME && volume.intValue() <= MAX_VOLUME){
			return true;
		}
		else{
			System.err.println("Error 6: volume = " + volume + ". Volume value must be in the range [0,127].");
			return false;
		}
	}

	@Override
	public String getValue() {
		return "volume";
	}

	@Override
	public void Apply(ChannelIdentifier id, MusicEventListener listener) {
		listener.setChannelVolume(new MusicChannelVolumeEvent(this,id,volume.intValue()));
	}
	
	@Override
	public String toString(){
		return "volume";
	}
}

