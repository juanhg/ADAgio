package com.adagio.language.channels.channeloptions;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.language.RunData;
import com.adagio.language.channels.ChannelIdentifier;

@Pattern(regExp="(?i)DISABLE")
public class DisableModifier extends StatusModifier implements IModel{

	@Value
	String value;
	
	public static final String PATTERN = "DISABLE";

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void Apply(ChannelIdentifier id, RunData data) {
		if(data.getChannelsDB().containsKey(id)){
			data.getChannelsDB().get(id).setEnable(false);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"" + this.toString() + "\" can't be applied.");
		}
	}
	
	@Override
	public String toString(){
		return value;
	}

}

