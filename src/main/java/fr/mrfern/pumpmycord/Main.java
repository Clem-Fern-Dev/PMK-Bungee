package fr.mrfern.pumpmycord;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import fr.mrfern.pumpmycord.porg.PorgServerEvent;
import net.dv8tion.jda.core.entities.TextChannel;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin{
    
	public TextChannel defaultBorgChan;
	private static MisterPorg misterP;	
	
	@Override
	public void onLoad() {
		setMisterP(new MisterPorg(this, "MzgyNTc4Mzg4MDY3NTQ5MTg0.DQdApA.zxYqzecf2pn3HMt6rRZGbcibggs","387326167499276292"));		
	}
	
	@Override
    public void onEnable() {
		
        Config conf = Config.getConfig(this);
        
        conf.initDataFolder();
        
        conf.initAndGetFile("reboot_message_history.yml");
        conf.initAndGetFile("reboot_message.yml");
        
        // event discord        
        getProxy().getPluginManager().registerListener(this, new PorgServerEvent());
        // event Messaging plugin
        getProxy().getPluginManager().registerListener(this, new MessagingService());
        
        new PorgServerEvent().OnProxyStartEvent(getMisterP());
        
    }

	public static MisterPorg getMisterP() {
		return misterP;
	}

	public void setMisterP(MisterPorg misterP) {
		Main.misterP = misterP;
	}
	
	@Override
	public void onDisable() {
		new PorgServerEvent().OnProxyStopEvent(getMisterP());
		
		getLogger().info(" OnDisable method call !");
		getMisterP().close();
	}
}
