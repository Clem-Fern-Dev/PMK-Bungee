package fr.mrfern.pumpmycord;

import fr.mrfern.pumpmycord.porg.MisterPorg;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
    @Override
    public void onEnable() {
    	
    	MisterPorg.getBorg(this)
    	.buildBot(token)
    	
        getLogger().info("Yay! It loads!");
        
    }
}
