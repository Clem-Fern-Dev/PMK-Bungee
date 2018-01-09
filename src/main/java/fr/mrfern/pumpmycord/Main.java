package fr.mrfern.pumpmycord;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import fr.mrfern.pumpmycord.porg.PorgServerEvent;
import net.dv8tion.jda.core.entities.TextChannel;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class Main extends Plugin{
    
	private static MisterPorg misterP;
	private static Config conf;	
	
	@Override
	public void onLoad() {
		setMisterP(new MisterPorg(this, "MzgyNTc4Mzg4MDY3NTQ5MTg0.DQdApA.zxYqzecf2pn3HMt6rRZGbcibggs","387326167499276292"));		
	}
	
	@Override
    public void onEnable() {
		
		conf = Config.getConfig(this);
        
        conf.initDataFolder();
        
        conf.initAndGetFile("config.yml");
        conf.initAndGetFile("reboot_message_history.yml");
        conf.initAndGetFile("reboot_message.yml");
        
        // event Messaging plugin
        getProxy().getPluginManager().registerListener(this, new MessagingService());
        
        //init discord debug mod
        try {
			Configuration config = conf.getConfiguration("config.yml");
			
			boolean b = config.getBoolean("discord.debug_mod");
			
			if(b) {
				new PorgServerEvent().OnProxyStartEvent(misterP);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	public static MisterPorg getMisterP() {
		return misterP;
	}

	public void setMisterP(MisterPorg misterP) {
		Main.misterP = misterP;
	}
	
	@Override
	public void onDisable() {
		try {
			Configuration config = conf.getConfiguration("config.yml");
			
			boolean b = config.getBoolean("discord.debug_mod");
			
			if(b) {
				new PorgServerEvent().OnProxyStopEvent(misterP);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getLogger().info(" OnDisable method call !");
		getMisterP().close();
	}

	public static Config getConf() {
		return conf;
	}
}
