package fr.mrfern.pumpmycord.porg;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class PorgListener implements EventListener{

	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			MessageReceivedEvent e = (MessageReceivedEvent) event;
			OnMessageReceivedEvent(e);			
		}	
	}

	private void OnMessageReceivedEvent(MessageReceivedEvent e) {
		if(e.getAuthor().equals(e.getJDA().getSelfUser())) {
			// server manager
			String msg = e.getMessage().getContent();
		}
	}

}
