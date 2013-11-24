package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.io.lilypond.RunData;
import com.adagio.language.channels.ChannelIdentifier;

@Pattern(regExp = "(?i)DESTROY")
public class DestroyCommand extends Command implements IModel {

	@Value
	String value;
	
	public static final String PATTERN = "DESTROY";
	
	@Override
	public String getValue() {
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
