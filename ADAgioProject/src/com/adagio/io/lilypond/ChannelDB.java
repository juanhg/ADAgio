package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Map;

import com.adagio.language.channels.Channel;
import com.adagio.language.channels.ChannelIdentifier;

public class ChannelDB {

	private class ChannelInfo {
		private Channel channel;
		private boolean erased;
		private String music;

		public ChannelInfo() {
			channel = new Channel();
			erased = false;
			music = null;
		}

		public Channel getChannel() {
			return channel;
		}

		public void setChannel(Channel channel) {
			this.channel = channel;
		}

		public boolean isErased() {
			return erased;
		}

		public void setErased(boolean erased) {
			this.erased = erased;
		}

		public String getMusic() {
			return music;
		}

		public void setMusic(String music) {
			this.music = music;
		}
		
		public void addMusic(String music){
			this.music += music;
		}
		
	}

	private Map<ChannelIdentifier, ChannelInfo> channelDB;

	public ChannelDB() {
		channelDB = new HashMap<ChannelIdentifier, ChannelInfo>();
	}

	/**
	 * @param id
	 *            Channel identifier
	 * @return True if channel has existed anytime in the database.
	 */
	boolean exists(ChannelIdentifier id) {
		return channelDB.containsKey(id);
	}

	/**
	 * @param id
	 *            Channel identifier
	 * @return True if channel has existed anytime & has been erased.
	 */
	boolean erased(ChannelIdentifier id) {
		if (this.exists(id)) {
			return channelDB.get(id).isErased();
		}
		return false;
	}

	/**
	 * Add a new channel with the id given. If the channel has been "erased",
	 * actives it again.
	 * 
	 * @param id
	 *            Channel identifier
	 */
	void add(ChannelIdentifier id) {
		ChannelInfo info;

		if (this.erased(id)) {
			channelDB.get(id).setErased(false);
		} else {
			info = new ChannelInfo();
			channelDB.put(id, info);
		}
	}

	/**
	 * Destroys logically the channel
	 * 
	 * @param id Channel identifier
	 */
	void destroy(ChannelIdentifier id) {
		if (this.exists(id)) {
			if (this.erased(id)) {
				System.err.println("Warning: Channel \"" + id.toString()
						+ "\" doesn't exist. It can't be destroyed.");
			} else {
				channelDB.get(id).setErased(true);
			}
		} else {
			System.err.println("Warning: Channel \"" + id.toString()
					+ "\" doesn't exist. It can't be destroyed.");
		}
	}

	void enable(ChannelIdentifier id) {
		if(!this.erased(id)){
			channelDB.get(id).channel.setEnable(true);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"enable\" can't be applied.");
		}
	}

	void disable(ChannelIdentifier id) {
		if(!this.erased(id)){
			channelDB.get(id).channel.setEnable(false);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"disable\" can't be applied.");
		}
	}

	void setVolume(ChannelIdentifier id, int volume) {
		if(!this.erased(id)){
			channelDB.get(id).channel.setVolume(volume);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"volume\" can't be applied.");
		}
	}
	
	void addMusic(ChannelIdentifier id, String music, int times, RunData data){
		String composition = "";
		
		if(!this.erased(id)){
			if(!channelDB.get(id).getMusic().equals(null)){
				//Deletes the last "}"
				channelDB.get(id).setMusic(channelDB.get(id).getMusic().substring(0, channelDB.get(id).getMusic().length()-2));
			}
			composition += "\\new Staff { \n";
			composition += ("\\clef " + data.getClef() + "\n");
			composition += "\\time " + data.getTime().toString() + "\n";
			composition += "\\set Staff.midiInstrument = #\""+ channelDB.get(id).getChannel().getInstrument().getValue() + "\"\n";
			composition += "\\set Staff.midiMinimumVolume = #" + 0+ "\n";
			composition += "\\set Staff.midiMaximumVolume = #"+ channelDB.get(id).getChannel().getVolume() / 100 + "\n";
			composition += "\\new Voice {\n";
			composition += "music";
			composition += "\n}\n";
			composition += "}\n";
			
			channelDB.get(id).addMusic(composition);
		}
		else{
			System.err.println("Error X: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Music can't be added");
		}
	}
	

}
