package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.adagio.channels.Channel;
import com.adagio.instruments.Instrument;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.rhythms.Rhythm;

public class ChannelsDB {


	private Map<ChannelIdentifier, Channel> channelsMap;

	/**
	 * Initializes the channelsDB and creates the default channel empty 
	 */
	public ChannelsDB() {
		channelsMap = new HashMap<ChannelIdentifier, Channel>();
	}

	/**
	 * @param idChannel identifier
	 * @return True if channel has existed anytime in the database.
	 */
	public boolean exists(ChannelIdentifier id) {
		return channelsMap.containsKey(id);
	}

	/**
	 * @param id Channel identifier
	 * @return True if channel has existed anytime & has been erased.
	 */
	public boolean isErased(ChannelIdentifier id) {
		if (this.exists(id)) {
			return channelsMap.get(id).isErased();
		}
		return false;
	}

	/**
	 * Add a new channel with the id given. If the channel has been "erased",
	 * activates it again.
	 * 
	 * @param id Channel identifier
	 */
	public void addChannel(ChannelIdentifier id) {
		Channel channel;

		if (this.isErased(id)) {
			channelsMap.get(id).setErased(false);
		} else {
			channel = new Channel();
			if(!channelsMap.containsKey(id)){
				channelsMap.put(new ChannelIdentifier(id.getValue()), channel);
			}
		}
	}
	
	/**
	 * Obtain the channel with the specific identifier
	 * @param id Identifier of the channel
	 * @return The channel if exists. Null in other case.
	 */
	public Channel getChannel(ChannelIdentifier id){
		if(exists(id)){
			return channelsMap.get(id);
		}
		else{
			return null;
		}
	}

	/**
	 * Destroys logically the channel, and put it as disabled.
	 * @param id Channel identifier
	 */
	public void destroy(ChannelIdentifier id) {
		if (this.exists(id)) {
			if (this.isErased(id)) {
				System.err.println("Warning: Channel \"" + id.toString()
						+ "\" doesn't exist. It can't be destroyed.");
			} else {
				channelsMap.get(id).setErased(true);
				channelsMap.get(id).setEnable(false);
			}
		} else {
			System.err.println("Warning: Channel \"" + id.toString()
					+ "\" doesn't exist. It can't be destroyed.");
		}
	}

	/**
	 * Enables the channel
	 * @param id
	 */
	public void enable(ChannelIdentifier id) {
		if(!this.isErased(id)){
			channelsMap.get(id).setEnable(true);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"enable\" can't be applied.");
		}
	}

	/**
	 * Disables the channel
	 * @param id
	 */
	public void disable(ChannelIdentifier id) {
		if(!this.isErased(id)){
			channelsMap.get(id).setEnable(false);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"disable\" can't be applied.");
		}
	}

	/**
	 * Change the volume of the channel
	 * @param id
	 */
	public void setVolume(ChannelIdentifier id, int volume) {
		if(!this.isErased(id)){
			channelsMap.get(id).setVolume(volume);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"volume\" can't be applied.");
		}
	}
	
	/**
	 * Change the instrument
	 * @param id
	 */
	public void setInstrument(ChannelIdentifier id, Instrument instrument){
		if(!this.isErased(id)){
			channelsMap.get(id).setInstrument(instrument);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"instrument\" can't be applied.");
		}
	}
	
	/**
	 * Change the rhythm of the channel
	 * @param channelID Identifier of the channel
	 */
	public void setRhythm(ChannelIdentifier channelID, Rhythm rhythm){
		if(!this.isErased(channelID)){
			channelsMap.get(channelID).setRhythm(rhythm);
		}
		else{
			System.err.println("Error 7: Channel \"" + channelID.toString() + "\" doesn't exist. "
					+ "Modifier \"rhythm\" can't be applied.");
		}
	}
	
	

	
	/**
	 * Add a music string to the channel, and increments the duration of the channel;
	 * @param id
	 * @param music
	 * @param duration
	 * @param clef
	 * @param time
	 */
	public void addMusic(ChannelIdentifier id, String music, int numBars){
		String composition = "";
		
		if(!this.isErased(id)){
			composition += music;
			
			channelsMap.get(id).addMusic(composition);
			channelsMap.get(id).addNumBars(numBars);
		}
		else{
			System.err.println("Error X: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Music can't be added");
		}
	}
	
	/**
	 * Adds the music string to the enabled channels. If there is no one active,
	 * adds music to the default channel 
	 * @param music
	 * @param duration
	 * @param data
	 */
	@SuppressWarnings("rawtypes")
	public void addMusicToEnabled(String music, int numBars){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		
		it = this.channelsMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((Channel)e.getValue()).isEnable()){
					this.addMusic((ChannelIdentifier)e.getKey(), music, numBars);
				}
			}
		}
		else{
			
			this.addMusic(LilyPondMusicPieceWriter.DEFAULT_CHANNEL_IDENTIFIER,music,numBars);
		}
	}
	
	
	/**
	 * Obtains the max duration (in bars) of the channels in the DB
	 * @return A int value with the duration
	 */
	@SuppressWarnings("rawtypes")
	public int maxNumBars() {
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;

		int max = 0;
		int auxDuration = 0;

		it = this.channelsMap.entrySet().iterator();

		while (it.hasNext()) {
			e = (Map.Entry) it.next();
			auxDuration = ((Channel) e.getValue()).getNumBars();
			if (auxDuration > max) {
				max = auxDuration;
			}
		}

		return max;
	}
	
	
	/**
	 * @return True if there is not an enabled-channel and Default is required. Else in other case.
	 */
	@SuppressWarnings("rawtypes")
	public boolean isDefaultChannelNeeded(){
		
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		
		it = this.channelsMap.entrySet().iterator();
	
			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((Channel)e.getValue()).isEnable()){
					return false;
				}
			}
			
			return true;
	}
	
	
	/** ----- GETTERS & SETTERS ----- **/

	public Map<ChannelIdentifier, Channel> getChannelMap() {
		return channelsMap;
	}

	public void setChannelMap(Map<ChannelIdentifier, Channel> channelMap) {
		this.channelsMap = channelMap;
	}
	
	
}
