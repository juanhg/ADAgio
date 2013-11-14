package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.language.RunData;
import com.adagio.language.channels.ChannelIdentifier;

@Pattern(regExp = "(?i)destroy")
public class DestroyCommand extends Command implements IModel {

	@Value
	String value;
	
	@Override
	public String getPattern() {
		return value;
	}

	@Override
	public void Apply(ChannelIdentifier id, RunData data) {
		if(data.getChannelsDB().containsKey(id)){
			data.getChannelsDB().remove(id);
		}
		else{
			System.err.println("Warning: Channel \""+ id.toString()+"\" doesn't exist. It can't be destroyed.");
		}
	}
}
