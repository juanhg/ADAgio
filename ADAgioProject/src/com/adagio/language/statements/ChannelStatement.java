package com.adagio.language.statements;

import org.modelcc.Constraint;
import org.modelcc.Optional;
import org.modelcc.Prefix;
import org.modelcc.IModel;

import com.adagio.events.MusicChannelCreateEvent;
import com.adagio.events.MusicEventListener;
import com.adagio.io.lilypond.RunData;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.channels.channeloptions.ChannelOption;
import com.adagio.language.channels.channeloptions.DestroyCommand;
import com.adagio.language.channels.channeloptions.DisableModifier;
import com.adagio.language.channels.channeloptions.EnableModifier;
import com.adagio.language.channels.channeloptions.InstrumentModifier;
import com.adagio.language.channels.channeloptions.VolumeModifier;

@Prefix("(?i)CHANNEL")
public class ChannelStatement extends Statement implements IModel {

	private ChannelIdentifier id;

	@Optional
	private ChannelOption [] options;

	@Constraint
	boolean destroyAlone() {
		if(options != null){
			for(ChannelOption current: this.options){
				if((current.getClass() == DestroyCommand.class) && options.length > 1){
					System.err.println("Error 5: The channel option \"destroy\" must appear alone.");
					return false;
				}
			}
		}
		return true;
	}
	
	@Constraint
	//Warning 1. Always returns true.
	boolean checkIdentifier(){
		
		String identifier = this.id.getValue();
		
		if(identifier.toUpperCase().equals(DestroyCommand.PATTERN)
				|| identifier.toUpperCase().equals(EnableModifier.PATTERN)
				|| identifier.toUpperCase().equals(DisableModifier.PATTERN)
				|| identifier.toUpperCase().equals(InstrumentModifier.PATTERN)
				|| identifier.toUpperCase().equals(VolumeModifier.PATTERN)){
					System.err.println("Warning 1: Your ChannelIdentifier \""+ identifier +"\" is named as a channel option");
		}
		return true;
	}

	
	public ChannelIdentifier getId() {
		return id;
	}



	public void setId(ChannelIdentifier id) {
		this.id = id;
	}



	public ChannelOption[] getOptions() {
		return options;
	}



	public void setOptions(ChannelOption[] options) {
		this.options = options;
	}



	public void run(RunData data, MusicEventListener listener) {

		// If channel doesn't exist && there is not a destroy command
		// Creates the new channel in DB

		if (data.getChannelDB2().exists(id)) {
			if (data.getChannelDB2().erased(id)) {
				if (options != null) {
					if (options[0].getClass() != DestroyCommand.class) {
						data.getChannelDB2().getChannelDB().get(id)
								.setErased(false);
						data.getChannelDB2().getChannelDB().get(id)
								.getChannel().setEnable(true);
					}
				}
			}
			
			for (ChannelOption current : this.options) {
				current.Apply(id, listener);
			}
			
		} else {
			if (options != null) {
				if (options[0].getClass() != DestroyCommand.class) {
					listener.createChannel(new MusicChannelCreateEvent(this, id));
				}
				for (ChannelOption current : this.options) {
					current.Apply(id, listener);
				}
			}

		}
	}
		
	
}