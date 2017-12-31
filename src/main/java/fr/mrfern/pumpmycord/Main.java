package fr.mrfern.pumpmycord;

import java.util.HashMap;

import fr.mrfern.pumpmycord.config.Config;
import fr.mrfern.pumpmycord.porg.MisterPorg;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.TextChannel;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    
	public JDA jda;
	public TextChannel defaultBorgChan;
	public HashMap<String,TextChannel> hashChannel = new HashMap<>();
	private MisterPorg misterP;
	
	
	@Override
	public void onLoad() {
		setMisterP(new MisterPorg(this, "MzgyNTc4Mzg4MDY3NTQ5MTg0.DQdApA.zxYqzecf2pn3HMt6rRZGbcibggs"));	
	}
	
	@Override
    public void onEnable() {
        Config conf = Config.getConfig(this);
        
        conf.initFile("");
    }

	public MisterPorg getMisterP() {
		return misterP;
	}

	public void setMisterP(MisterPorg misterP) {
		this.misterP = misterP;
	}
}
