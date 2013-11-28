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
	 * @param id
	 *            Channel identifier
	 * @return True if channel has existed anytime in the database.
	 */
	public boolean exists(ChannelIdentifier id) {
		return channelMap.containsKey(id);
	}

	/**
	 * @param id
	 *            Channel identifier
	 * @return True if channel has existed anytime & has been erased.
	 */
	public boolean erased(ChannelIdentifier id) {
		if (this.exists(id)) {
			return channelMap.get(id).isErased();
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
	public void add(ChannelIdentifier id) {
		ChannelInfo info;

		if (this.erased(id)) {
			channelMap.get(id).setErased(false);
		} else {
			info = new ChannelInfo();
			channelMap.put(id, info);
		}
	}

	/**
	 * Destroys logically the channel, and put it as disabled
	 * 
	 * @param id Channel identifier
	 */
	public void destroy(ChannelIdentifier id) {
		if (this.exists(id)) {
			if (this.erased(id)) {
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

	public void enable(ChannelIdentifier id) {
		if(!this.erased(id)){
			channelMap.get(id).getChannel().setEnable(true);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"enable\" can't be applied.");
		}
	}

	public void disable(ChannelIdentifier id) {
		if(!this.erased(id)){
			channelMap.get(id).getChannel().setEnable(false);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"disable\" can't be applied.");
		}
	}

	public void setVolume(ChannelIdentifier id, int volume) {
		if(!this.erased(id)){
			channelMap.get(id).getChannel().setVolume(volume);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"volume\" can't be applied.");
		}
	}
	
	public void setInstrument(ChannelIdentifier id, Instrument instrument){
		if(!this.erased(id)){
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
	 * @param data
	 */
	public void addMusic(ChannelIdentifier id, String music, int duration, String clef, Time time){
		String composition = "";
		
		if(!this.erased(id)){
			if(!(channelMap.get(id).getMusic().equals(""))){
				//Deletes the last "}"
				channelMap.get(id).setMusic(channelMap.get(id).getMusic().substring(0, channelMap.get(id).getMusic().length()-2));
			}
			else{
				composition += "\\new Staff { \n";
			}
			
			composition += ("\\clef " + clef + "\n");
			composition += "\\time " + time.toString() + "\n";
			composition += "\\set Staff.midiInstrument = #\""+ channelMap.get(id).getChannel().getInstrument().getValue() + "\"\n";
			composition += "\\set Staff.midiMinimumVolume = #" + 0+ "\n";
			composition += "\\set Staff.midiMaximumVolume = #"+ channelMap.get(id).getChannel().getVolume() / 100 + "\n";
			composition += "\\new Voice {\n";
			composition += music;
			composition += "\n}\n";
			composition += "}\n";
			
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
	 * Obtains the max duration of the channels in the DB
	 * @return A int value with the duration
	 */
	@SuppressWarnings("rawtypes")
	public int maxDuration(){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		int max = 0;
		int auxDuration = 0;
		
		it = this.channelMap.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				auxDuration = ((ChannelInfo)e.getValue()).getDuration();
				if(auxDuration > max){
					max = auxDuration;
				}
			}
		}
		
		return max;
	}
	
	/**
	 * Fills a channel with silences until reach the maxDuration
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

	public Map<ChannelIdentifier, ChannelInfo> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(Map<ChannelIdentifier, ChannelInfo> channelMap) {
		this.channelMap = channelMap;
	}
}
