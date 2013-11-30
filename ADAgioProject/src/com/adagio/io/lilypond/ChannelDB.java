package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.instruments.Instrument;

public class ChannelDB {


	private Map<ChannelIdentifier, ChannelInfo> channelMap;

	/**
	 * Initializes the channelDB and creates the default channel empty 
	 */
	public ChannelDB() {
		channelMap = new HashMap<ChannelIdentifier, ChannelInfo>();
		channelMap.put(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER), new ChannelInfo());
		channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER)).getChannel().setEnable(false);
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
		ChannelInfo info;

		if (this.isErased(id)) {
			channelMap.get(id).setErased(false);
		} else {
			info = new ChannelInfo();
			channelMap.put(id, info);
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
				channelMap.get(id).getChannel().setEnable(false);
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
			channelMap.get(id).getChannel().setEnable(true);
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
			channelMap.get(id).getChannel().setEnable(false);
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
			channelMap.get(id).getChannel().setVolume(volume);
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
			channelMap.get(id).getChannel().setInstrument(instrument);
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
	public void addMusic(ChannelIdentifier id, String music, int duration, String clef, Time time){
		String composition = "";
		
		if(!this.isErased(id)){
			if(!(channelMap.get(id).getMusic().equals(""))){
				//Deletes the last "}"
				channelMap.get(id).setMusic(channelMap.get(id).getMusic().substring(0, channelMap.get(id).getMusic().length()-2));
			}
			composition += music;
			composition += "\n}\n";
			
			channelMap.get(id).addMusic(composition);
			channelMap.get(id).addDuration(duration);
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
	public void addMusicToEnabled(String music, int duration, String clef, Time time){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((ChannelInfo)e.getValue()).getChannel().isEnable()){
					this.addMusic((ChannelIdentifier)e.getKey(), music, duration, clef, time);
				}
			}
		}
		else{
			
			this.addMusic(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER),music,duration,clef, time);
		}
	}
	
	/**
	 * Fills a channel with silences until reaches the maxDuration
	 * @param id
	 * @param data
	 */
	public void fillWithSilences(ChannelIdentifier id, String clef, Time time) {
		int maxDuration = this.maxDuration();
		int auxDuration = 0;
		int difference = 0;

		String composition = "";

		if (this.channelMap.containsKey(id)) {
			auxDuration = this.channelMap.get(id).getDuration();
			difference = maxDuration - auxDuration;

			if (difference > 0) {
				for (int i = difference; i > 0; i--) {
					composition += "s1 ";
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
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((ChannelInfo)e.getValue()).getChannel().isEnable()){
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
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(!((ChannelInfo)e.getValue()).getChannel().isEnable()){
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
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;

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
	 * Create the block \Staf{ options + music} for enabled channels.
	 * If \Staff has been created yet, it adds {options + music} to the current 
	 * staff. You need to call to this function in PlaySentence-events.
	 * If there is no enable channels, enables the default-one and use it.
	 * @param clef
	 * @param time
	 */
	@SuppressWarnings("rawtypes")
	public void prepareStaffToPlay(String clef, Time time) {
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		String composition = "";

		it = this.channelMap.entrySet().iterator();
		if (this.isDefaultChannelNeeded()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if (((ChannelInfo) e.getValue()).getChannel().isEnable()) {

					if (!((ChannelInfo) e.getValue()).getMusic().equals("")) {
						// Deletes the last "}"
						((ChannelInfo) e.getValue()).setMusic(((ChannelInfo) e.getValue()).getMusic().substring(0,((ChannelInfo) e.getValue()).getMusic().length() - 2));
					} else {
						composition += "\\new Staff {";
					}

					composition += ("\n\\clef " + clef + "\n");
					composition += "\\time " + time.toString() + "\n";
					composition += "\\set Staff.midiInstrument = #\"" + ((ChannelInfo) e.getValue()).getChannel().getInstrument().getValue() + "\"\n";
					composition += "\\set Staff.midiMinimumVolume = #" + 0 + "\n";
					composition += "\\set Staff.midiMaximumVolume = #"+ ((ChannelInfo) e.getValue()).getChannel().getVolume() / 100 + "\n";
					composition += "\n}\n";

					((ChannelInfo) e.getValue()).addMusic(composition);
				}
				composition = "";
			}
		} else {
			//enable default
			this.enable(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER));
		
			if (!(this.channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER))).getMusic().equals("")) {
				// Deletes the last "}"
				(this.channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER))).setMusic((this.channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER))).getMusic().substring(0,(this.channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER))).getMusic().length() - 2));
			} else {
				composition += "\\new Staff {";
			}

			
			composition += ("\n\\clef " + clef + "\n");
			composition += "\\time " + time.toString() + "\n";
			composition += "\\set Staff.midiInstrument = #\""+ this.channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER)).getChannel().getInstrument().getValue() + "\"\n";
			composition += "\\set Staff.midiMinimumVolume = #" + 0 + "\n";
			composition += "\\set Staff.midiMaximumVolume = #" + this.channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER)).getChannel().getVolume()/ 100 + "\n";
			composition += "\n}\n";

			this.channelMap.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER)).addMusic(composition);

		}
	}
	
	/**
	 * Obtains the max duration of the channels in the DB
	 * @return A int value with the duration
	 */
	@SuppressWarnings("rawtypes")
	public int maxDuration() {
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;

		int max = 0;
		int auxDuration = 0;

		it = this.channelMap.entrySet().iterator();

		while (it.hasNext()) {
			e = (Map.Entry) it.next();
			auxDuration = ((ChannelInfo) e.getValue()).getDuration();
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
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		it = this.channelMap.entrySet().iterator();
	
			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((ChannelInfo)e.getValue()).getChannel().isEnable()){
					return true;
				}
			}
			
			return false;
	}
	
	
	/** ----- GETTERS & SETTERS ----- **/

	public Map<ChannelIdentifier, ChannelInfo> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(Map<ChannelIdentifier, ChannelInfo> channelMap) {
		this.channelMap = channelMap;
	}
	
	
}
