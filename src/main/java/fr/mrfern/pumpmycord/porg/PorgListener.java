package fr.mrfern.pumpmycord.porg;

import java.util.List;

import fr.mrfern.pumpmycord.Main;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class PorgListener implements EventListener{

	private Main main;

	public PorgListener(Main mainClass) {
		this.main = mainClass;
	}
	
	@Override
	public void onEvent(Event event) {
		if(event instanceof ReadyEvent) {
			
			List<TextChannel> chanList = event.getJDA().getTextChannels();	
			for (TextChannel textChannel : chanList) {
				main.hashChannel.put(textChannel.getName(),textChannel);
				main.getLogger().info(textChannel.getName());
			}
			
			main.defaultBorgChan = main.hashChannel.get("");
			
			//main.InitBungeeMessage();
			
		}		
	}

}
