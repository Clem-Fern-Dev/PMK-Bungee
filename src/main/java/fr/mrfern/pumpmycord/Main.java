package fr.mrfern.pumpmycord;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import fr.mrfern.pumpmycord.porg.PorgServerEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin{
    
	private static MisterPorg misterP;
	private static Config conf;	
	private static ProxyServer proxy;
	
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
        
        @SuppressWarnings("unused")
		ServerManager serverM = new ServerManager(this);
        
        // event Messaging plugin
        getProxy().getPluginManager().registerListener(this, new MessagingService());
		
        new PorgServerEvent().OnProxyStartEvent(misterP);
        
    }

	public static MisterPorg getMisterP() {
		return misterP;
	}

	public void setMisterP(MisterPorg misterP) {
		Main.misterP = misterP;
	}
	
	@Override
	public void onDisable() {
		
		new PorgServerEvent().OnProxyStopEvent(misterP);
		
		getLogger().info(" OnDisable method call !");
		getMisterP().close();
	}

	public static Config getConf() {
		return conf;
	}

	public static ProxyServer getProxyServer() {
		return proxy;
	}

	public static void setProxyServer(ProxyServer proxy) {
		Main.proxy = proxy;
	}
}
