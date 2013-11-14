package com.adagio.language.statements;

import org.modelcc.Constraint;
import org.modelcc.Optional;
import org.modelcc.Prefix;
import org.modelcc.IModel;

import com.adagio.language.RunData;
import com.adagio.language.channels.Channel;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.channels.channeloptions.ChannelOption;
import com.adagio.language.channels.channeloptions.DestroyCommand;

@Prefix("(?i)CHANNEL")
public class ChannelStatement extends Statement implements IModel {

	ChannelIdentifier id;

	@Optional
	ChannelOption [] options;

	@Constraint
	boolean destroyAlone() {
		if(options != null){
			for(ChannelOption current: this.options){
				if((current.getClass() == DestroyCommand.class) && options.length > 1){
					System.err.println("Error 5: The channel option \"destroy\" must apear alone.");
					return false;
				}
			}
		}
		return true;
	}

	public void run(RunData data) {
		// If channel doesn't exist && there is not a destroy command
		// Creates the new channel in DB

		if (!data.getChannelsDB().containsKey(id)) {
			if (options != null) {
				if (options[0].getClass() != DestroyCommand.class) {
					data.getChannelsDB().put(id, new Channel());
				}
				for (ChannelOption current : this.options) {
					current.Apply(id, data);
				}
			} else {
				data.getChannelsDB().put(id, new Channel());
			}
		}
	}
	
}