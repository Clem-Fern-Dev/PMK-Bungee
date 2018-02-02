package fr.mrfern.pumpmycord;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.config.PlayerConfigurationService;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import fr.mrfern.pumpmycord.porg.PorgListener;
import fr.mrfern.pumpmycord.porg.PorgServerEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

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
       
        Configuration bddConf;
		try {
			// get instance configuration config.yml
			bddConf = conf.getConfiguration("config.yml");
			
			//get url / user / mdp dans config.yml
			String url = bddConf.getString("ban.bdd_url");
	        String user = bddConf.getString("ban.bdd_user");
	        String mdp = bddConf.getString("ban.bdd_mdp");
	        String base = bddConf.getString("ban.bdd_base");
	        
	        //initialisation de la class MySQLConnector
	        conf.initMySQLConnect(url,user,mdp,base);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        // event Messaging plugin
        getProxy().getPluginManager().registerListener(this, new MessagingService());
        getProxy().getPluginManager().registerListener(this, new PlayerConfigurationService());
		
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

	public static Main getMain() {
		return main;
	}

	public static void setMain(Main main) {
		Main.main = main;
	}
}
