package com.adagio.io.lilypond;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.instruments.Instrument;

public class ChannelDB {


	private Map<ChannelIdentifier, ChannelInfo> channelDB;

	/**
	 * Initializes the channelDB and creates the default channel empty 
	 */
	public ChannelDB() {
		channelDB = new HashMap<ChannelIdentifier, ChannelInfo>();
		channelDB.put(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER), new ChannelInfo());
		channelDB.get(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER)).getChannel().setEnable(false);
	}

	/**
	 * @param id
	 *            Channel identifier
	 * @return True if channel has existed anytime in the database.
	 */
	public boolean exists(ChannelIdentifier id) {
		return channelDB.containsKey(id);
	}

	/**
	 * @param id
	 *            Channel identifier
	 * @return True if channel has existed anytime & has been erased.
	 */
	public boolean erased(ChannelIdentifier id) {
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
	public void add(ChannelIdentifier id) {
		ChannelInfo info;

		if (this.erased(id)) {
			channelDB.get(id).setErased(false);
		} else {
			info = new ChannelInfo();
			channelDB.put(id, info);
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
				channelDB.get(id).setErased(true);
				channelDB.get(id).getChannel().setEnable(false);
			}
		} else {
			System.err.println("Warning: Channel \"" + id.toString()
					+ "\" doesn't exist. It can't be destroyed.");
		}
	}

	public void enable(ChannelIdentifier id) {
		if(!this.erased(id)){
			channelDB.get(id).getChannel().setEnable(true);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"enable\" can't be applied.");
		}
	}

	public void disable(ChannelIdentifier id) {
		if(!this.erased(id)){
			channelDB.get(id).getChannel().setEnable(false);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"disable\" can't be applied.");
		}
	}

	public void setVolume(ChannelIdentifier id, int volume) {
		if(!this.erased(id)){
			channelDB.get(id).getChannel().setVolume(volume);
		}
		else{
			System.err.println("Error 7: Channel \"" + id.toString() + "\" doesn't exist. "
					+ "Modifier \"volume\" can't be applied.");
		}
	}
	
	public void setInstrument(ChannelIdentifier id, Instrument instrument){
		if(!this.erased(id)){
			channelDB.get(id).getChannel().setInstrument(instrument);
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
	public void addMusic(ChannelIdentifier id, String music, int duration, RunData data){
		String composition = "";
		
		if(!this.erased(id)){
			if(!(channelDB.get(id).getMusic().equals(""))){
				//Deletes the last "}"
				channelDB.get(id).setMusic(channelDB.get(id).getMusic().substring(0, channelDB.get(id).getMusic().length()-2));
			}
			else{
				composition += "\\new Staff { \n";
			}
			
			composition += ("\\clef " + data.getClef() + "\n");
			composition += "\\time " + data.getTime().toString() + "\n";
			composition += "\\set Staff.midiInstrument = #\""+ channelDB.get(id).getChannel().getInstrument().getValue() + "\"\n";
			composition += "\\set Staff.midiMinimumVolume = #" + 0+ "\n";
			composition += "\\set Staff.midiMaximumVolume = #"+ channelDB.get(id).getChannel().getVolume() / 100 + "\n";
			composition += "\\new Voice {\n";
			composition += music;
			composition += "\n}\n";
			composition += "}\n";
			
			channelDB.get(id).addMusic(composition);
			channelDB.get(id).addDuration(duration);
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
	public void addMusicToEnabled(String music, int duration, RunData data){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		it = this.channelDB.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((ChannelInfo)e.getValue()).getChannel().isEnable()){
					this.addMusic((ChannelIdentifier)e.getKey(), music, duration, data);
				}
			}
		}
		else{
			this.addMusic(new ChannelIdentifier(ChannelInfo.DEFAULT_CHANNEL_IDENTIFIER),music,duration,data);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void fillEnabledWithSilences(RunData data){
		Map.Entry e = null;
		Iterator<Entry<ChannelIdentifier, ChannelInfo>> it;
		
		it = this.channelDB.entrySet().iterator();
		if (it.hasNext()) {

			while (it.hasNext()) {
				e = (Map.Entry) it.next();
				if(((ChannelInfo)e.getValue()).getChannel().isEnable()){
					this.fillWithSilences((ChannelIdentifier)e.getKey(), data);
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
		
		it = this.channelDB.entrySet().iterator();
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
	public void fillWithSilences(ChannelIdentifier id, RunData data) {
		int maxDuration = this.maxDuration();
		int auxDuration = 0;
		int difference = 0;

		String composition = "";

		if (this.channelDB.containsKey(id)) {
			auxDuration = this.channelDB.get(id).getDuration();
			difference = maxDuration - auxDuration;

			if (difference > 0) {
				for (int i = difference; i > 0; i--) {
					composition += "s1 ";
				}
				this.addMusic(id, composition, difference, data);
			}
		} else {
			System.err.println("Error 10: Channel \"" + id.toString()
					+ "\" doesn't exist. " + "Can't be filled with zeros.");

		}
	}

	public Map<ChannelIdentifier, ChannelInfo> getChannelDB() {
		return channelDB;
	}

	public void setChannelDB(Map<ChannelIdentifier, ChannelInfo> channelDB) {
		this.channelDB = channelDB;
	}
}
