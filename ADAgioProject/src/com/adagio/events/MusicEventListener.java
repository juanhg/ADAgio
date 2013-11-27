package com.adagio.events;

import java.util.EventListener;

public interface MusicEventListener extends EventListener {
	public void musicPlay(MusicPlayEvent e);
	public void createChannel(MusicChannelCreateEvent e);
	public void destroyChannel(MusicChannelDestroyEvent e);
	public void enableChannel(MusicChannelEnableEvent e);
	public void disableChannel(MusicChannelDisableEvent e);
	public void setChannelVolume(MusicChannelVolumeEvent e);
	public void setChannelInstrument(MusicChannelInstrumentEvent e);
}
