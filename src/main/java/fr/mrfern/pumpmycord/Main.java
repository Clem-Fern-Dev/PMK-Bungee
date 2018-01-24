package fr.mrfern.pumpmycord;

import java.util.Map.Entry;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.config.PlayerConfigurationService;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import fr.mrfern.pumpmycord.porg.PorgListener;
import fr.mrfern.pumpmycord.porg.PorgServerEvent;
import fr.mrfern.pumpmycord.server.ServerManager;
import fr.mrfern.pumpmycord.server.ServerState;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin{
    
	private static MisterPorg misterP;
	private static Config conf;	
	private static ProxyServer proxy;
	private static Main main;
	
	@Override
	public void onLoad() {
		setMisterP(new MisterPorg(this, "MzgyNTc4Mzg4MDY3NTQ5MTg0.DQdApA.zxYqzecf2pn3HMt6rRZGbcibggs","387326167499276292"));	
		getMisterP().addListener(new PorgListener());
	}
	
	@Override
    public void onEnable() {
		main = this;
		conf = Config.getConfig(this);
        
        conf.initDataFolder();
        
        conf.initAndGetFile("config.yml");
        conf.initAndGetFile("reboot_message_history.yml");
        conf.initAndGetFile("reboot_message.yml");
        
        // event Messaging plugin
        getProxy().getPluginManager().registerListener(this, new MessagingService());
        getProxy().getPluginManager().registerListener(this, new PlayerConfigurationService());
		
        new PorgServerEvent().OnProxyStartEvent(misterP);
        
        for (Entry<String, ServerInfo> entryServer : getProxy().getServers().entrySet()) {
			ServerState serverState = new ServerState();
			serverState.setName(entryServer.getValue().getName());
			serverState.setServerInfo(entryServer.getValue());
			serverState.setState(true);
			
			ServerManager.addServer(entryServer.getKey(), serverState);
			
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

	public static Main getMain() {
		return main;
	}

	public static void setMain(Main main) {
		Main.main = main;
	}
}
