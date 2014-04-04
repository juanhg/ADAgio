package com.adagio.language.statements;

import org.modelcc.Constraint;
import org.modelcc.Multiplicity;
import org.modelcc.Optional;
import org.modelcc.Prefix;
import org.modelcc.IModel;

import com.adagio.events.MusicEventListener;
import com.adagio.events.channels.ChannelIdentifierEvent;
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

	@Multiplicity(minimum = 1)
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



	public void run(MusicEventListener listener) {

		// If channel doesn't exist && there is not a destroy command
		// Creates the new channel in DB

		if (listener.existsChannel(new ChannelIdentifierEvent(this,id))){ 
			if (listener.isErasedChannel(new ChannelIdentifierEvent(this,id))) {
				if (options != null) {
					if (options[0].getClass() != DestroyCommand.class) {
						listener.recoverChannel(new ChannelIdentifierEvent(this,id));
						listener.enableChannel(new ChannelIdentifierEvent(this,id));
					}
				}
			}
			
			if (options != null) {
				for (ChannelOption current : this.options) {
					current.Apply(id, listener);
				}
			}
			
		} else {
			if (options != null) {
				if (options[0].getClass() != DestroyCommand.class) {
					listener.createChannel(new ChannelIdentifierEvent(this, id));
				}
				for (ChannelOption current : this.options) {
					current.Apply(id, listener);
				}
			}
			else{
				listener.createChannel(new ChannelIdentifierEvent(this, id));
			}

		}
	}
		
	
}