package fr.mrfern.pumpmycord;

import java.util.HashMap;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import net.dv8tion.jda.core.entities.TextChannel;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    
	public TextChannel defaultBorgChan;
	public HashMap<String,TextChannel> hashChannel = new HashMap<>();
	private MisterPorg misterP;
	
	
	@Override
	public void onLoad() {
		setMisterP(new MisterPorg(this, "MzgyNTc4Mzg4MDY3NTQ5MTg0.DQdApA.zxYqzecf2pn3HMt6rRZGbcibggs"));
		getMisterP().addListener(new ReceiveMessageEvent());
		
		defaultBorgChan = misterP.getJda().getTextChannelById("387326167499276292");
		
		defaultBorgChan.sendMessage("bungee proxy started").complete().pin().complete();
		defaultBorgChan.sendMessage("bungee proxy started").complete().pin().complete();
		defaultBorgChan.sendMessage("bungee proxy started").complete().pin().complete();
	}
	
	@Override
    public void onEnable() {
		
        Config conf = Config.getConfig(this);
        
        conf.initDataFolder();
        
        conf.initAndGetFile("reboot_message_history.yml");
        conf.initAndGetFile("reboot_message.yml");
    }

	public MisterPorg getMisterP() {
		return misterP;
	}

	public void setMisterP(MisterPorg misterP) {
		this.misterP = misterP;
	}
}
