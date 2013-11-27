package com.adagio.language.channels;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

@Pattern(regExp = "[a-zA-Z][a-zA-Z[0-9]_]*")
public class ChannelIdentifier implements IModel {

	@Value
	String value;

	public ChannelIdentifier(){}
	
	public ChannelIdentifier(String name){
		this.value = name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelIdentifier other = (ChannelIdentifier) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return this.value;
	}
	
	public ChannelIdentifier clone(){
		ChannelIdentifier newID = new ChannelIdentifier();
		newID.value = this.value;
		return newID;
	}
	
}
