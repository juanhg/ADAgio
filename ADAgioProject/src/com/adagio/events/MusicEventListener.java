package com.adagio.events;

import java.util.EventListener;

public interface MusicEventListener extends EventListener {
	public void musicPlay(MusicPlayEvent e);
}
