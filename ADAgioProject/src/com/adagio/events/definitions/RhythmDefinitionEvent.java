package com.adagio.events.definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;

import com.adagio.language.rhythm.RhythmComponent;
import com.adagio.language.rhythm.RhythmIdentifier;

public class RhythmDefinitionEvent extends EventObject {

	private static final long serialVersionUID = -7344477196261079955L;
	
	private RhythmIdentifier identifier;
	private List<RhythmComponent> components; 
	
	public RhythmDefinitionEvent(Object arg0, RhythmIdentifier identifier, RhythmComponent [] components) {
		super(arg0);
		this.identifier = identifier;
		this.components = new ArrayList<RhythmComponent>(Arrays.asList(components));
	}

	public RhythmIdentifier getIdentifier() {
		return identifier;
	}

	public List<RhythmComponent> getComponents() {
		return components;
	}
}
