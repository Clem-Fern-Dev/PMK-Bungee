package fr.mrfern.pumpmycord;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import net.dv8tion.jda.core.entities.TextChannel;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    
	public TextChannel defaultBorgChan;
	private MisterPorg misterP;
	
	
	@Override
	public void onLoad() {
		setMisterP(new MisterPorg(this, "MzgyNTc4Mzg4MDY3NTQ5MTg0.DQdApA.zxYqzecf2pn3HMt6rRZGbcibggs","387326167499276292"));
		
		getMisterP();
		
		
		
		defaultBorgChan.sendMessage("#�proxy-server�#").complete().pin().complete();
		defaultBorgChan.sendMessage("__                                                                                                                                                                                                                                                                                              __").complete();
		defaultBorgChan.sendMessage("#�lobby�#").complete().pin().complete();
		defaultBorgChan.sendMessage("__                                                                                                                                                                                                                                                                                              __").complete();
		defaultBorgChan.sendMessage("#�ragna1�#").complete().pin().complete();
		defaultBorgChan.sendMessage("__                                                                                                                                                                                                                                                                                              __").complete();
		defaultBorgChan.sendMessage("#�snooze�# @everyone").complete().pin().complete();
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
	
	@Override
	public void onDisable() {
		getMisterP().close();
	}
}
