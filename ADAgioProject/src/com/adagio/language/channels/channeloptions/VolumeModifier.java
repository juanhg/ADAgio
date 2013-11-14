package com.adagio.language.channels.channeloptions;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.IntegerModel;

import com.adagio.language.RunData;
import com.adagio.language.channels.ChannelIdentifier;

public class VolumeModifier extends StatusModifier implements IModel{

	@Prefix({"(?i)volume","="})
	IntegerModel volume;
	
	@Constraint
	boolean volumeInRange(){
		if(volume.intValue() >= 0 && volume.intValue() <= 127){
			return true;
		}
		else{
			System.err.println("Error 6: volume = " + volume + "\nVolume value must be in the range [0,127].");
			return false;
		}
	}

	@Override
	public String getPattern() {
		return "volume =" + volume.toString();
	}

	@Override
	public void Apply(ChannelIdentifier id, RunData data) {
		if(data.getChannelsDB().containsKey(id)){
			data.getChannelsDB().get(id).setVolume(volume.intValue());
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"" + this.toString() + "\" can't be applied.");
		}
	}
	
	@Override
	public String toString(){
		return "volume";
	}
}

