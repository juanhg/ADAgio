package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.adagio.language.channels.Channel;
import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.instruments.Instrument;
import com.adagio.language.times.Time;

public class ChannelsDB {


	private Map<ChannelIdentifier, Channel> channelMap;

	/**
	 * Initializes the channelsDB and creates the default channel empty 
	 */
	public ChannelsDB() {
		channelMap = new HashMap<ChannelIdentifier, Channel>();
		channelMap.put(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER), new Channel());
		channelMap.get(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER)).setEnable(false);
	}

	/**
	 * @param idChannel identifier
	 * @return True if channel has existed anytime in the database.
	 */
	public boolean exists(ChannelIdentifier id) {
		return channelMap.containsKey(id);
	}

	/**
	 * @param id Channel identifier
	 * @return True if channel has existed anytime & has been erased.
	 */
	public boolean isErased(ChannelIdentifier id) {
		if (this.exists(id)) {
			return channelMap.get(id).isErased();
		}
		return false;
	}

	/**
	 * Add a new channel with the id given. If the channel has been "erased",
	 * actives it again.
	 * 
	 * @param id Channel identifier
	 */
	public void add(ChannelIdentifier id) {
		Channel channel;

		if (this.isErased(id)) {
			channelMap.get(id).setErased(false);
		} else {
			channel = new Channel();
			channelMap.put(id, channel);
		}
	}

	/**
	 * Destroys logically the channel, and put it as disabled
	 * @param id Channel identifier
	 */
	public void destroy(ChannelIdentifier id) {
		if (this.exists(id)) {
			if (this.isErased(id)) {
				System.err.println("Warning: Channel \"" + id.toString()
						+ "\" doesn't exist. It can't be destroyed.");
			} else {
				channelMap.get(id).setErased(true);
				channelMap.get(id).setEnable(false);
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
			channelMap.get(id).setEnable(true);
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
			channelMap.get(id).setEnable(false);
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
			channelMap.get(id).setVolume(volume);
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
			channelMap.get(id).setInstrument(instrument);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"volume\" can't be applied.");
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
	public void addMusic(ChannelIdentifier id, String music, int numBars, String clef, Time time){
		String composition = "";
		
		if(!this.isErased(id)){
			if(!(channelMap.get(id).getMusic().equals(""))){
				//Deletes the last "}"
				channelMap.get(id).setMusic(LilyPondMusicPieceWriter.deleteLastBracket(channelMap.get(id).getMusic()));
			}
			composition += music;
			composition += "\n}\n";
			
			channelMap.get(id).addMusic(composition);
			channelMap.get(id).addNumBars(numBars);
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
	public void addMusicToEnabled(String music, int numBars, String clef, Time time){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		
		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((Channel)e.getValue()).isEnable()){
					this.addMusic((ChannelIdentifier)e.getKey(), music, numBars, clef, time);
				}
			}
		}
		else{
			
			this.addMusic(new ChannelIdentifier(Channel.DEFAULT_CHANNEL_IDENTIFIER),music,numBars,clef, time);
		}
	}
	
	/**
	 * Fills a channel with silences until reaches the maxDuration
	 * @param id
	 * @param data
	 */
	public void fillWithSilences(ChannelIdentifier id, String clef, Time time) {
		int maxDuration = this.maxNumBars();
		int auxDuration = 0;
		int difference = 0;

		String composition = "";

		if (this.channelMap.containsKey(id)) {
			auxDuration = this.channelMap.get(id).getNumBars();
			difference = maxDuration - auxDuration;

			if (difference > 0) {
				for (int i = difference; i > 0; i--) {
					composition += "s" + time.defaultDuration() + " ";
				}
				this.addMusic(id, composition, difference, clef, time);
			}
		} else {
			System.err.println("Error 10: Channel \"" + id.toString()
					+ "\" doesn't exist. " + "Can't be filled with zeros.");

		}
	}
	
	/**
	 * Fills all the enabled channels with silences until reach the maxDuration
	 * @param id
	 * @param data
	 */
	@SuppressWarnings("rawtypes")
	public void fillEnabledWithSilences(String clef, Time time){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		
		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((Channel)e.getValue()).isEnable()){
					this.fillWithSilences((ChannelIdentifier)e.getKey(), clef, time);
				}
			}
		}
	}
	
	/**
	 * Fills all the disabled channels with silences until reach the maxDuration
	 * @param id
	 * @param data
	 */
	@SuppressWarnings("rawtypes")
	public void fillDisabledWithSilences(String clef, Time time){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;
		
		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(!((Channel)e.getValue()).isEnable()){
					this.fillWithSilences((ChannelIdentifier)e.getKey(), clef, time);
				}
			}
		}
	}
	
	/**
	 * Fills all the channels with silences until reach the maxDuration
	 * @param id
	 * @param data
	 */
	@SuppressWarnings("rawtypes")
	public void fillAllWithSilences(String clef, Time time) {
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, Channel>> it;

		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				this.fillWithSilences((ChannelIdentifier) e.getKey(), clef,
						time);
			}
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

		it = this.channelMap.entrySet().iterator();

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
		
		it = this.channelMap.entrySet().iterator();
	
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
		return channelMap;
	}

	public void setChannelMap(Map<ChannelIdentifier, Channel> channelMap) {
		this.channelMap = channelMap;
	}
	
	
}
