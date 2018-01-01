package fr.mrfern.pumpmycord;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class ReceiveMessageEvent implements EventListener {

	@Override
	public void onEvent(Event event) {
		if(event instanceof MessageReceivedEvent) {
			MessageReceiveEvent((MessageReceivedEvent) event);
		}
	}

	private void MessageReceiveEvent(MessageReceivedEvent e) {
		System.out.println(e.getAuthor().getId() + "   " + e.getChannel().getId() + "   " + e.getMessage());		
	}

}
