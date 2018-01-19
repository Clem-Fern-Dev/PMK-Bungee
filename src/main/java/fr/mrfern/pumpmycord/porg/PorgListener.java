package fr.mrfern.pumpmycord.porg;

import fr.mrfern.pumpmycord.server.ServerManager;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class PorgListener implements EventListener{

	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			OnMessageReceivedEvent((MessageReceivedEvent) event);			
		}	
	}

	private void OnMessageReceivedEvent(MessageReceivedEvent e) {
		System.out.println("message");
		if(e.getAuthor().equals(e.getJDA().getSelfUser())) {
			// server manager
			String msg = e.getMessage().getContent();
			String header = msg.substring(0, 3);
			
			switch (header) {
			case "�1o":
				// ragna1 online
				break;
			case "�1n":
				// ragna1 offline
				break;
			case "�2o":
				// ragna2 online
				break;
			case "�2n":
				// ragn2 offline
				break;
			case "�3o":
				// dev online
				break;
			case "�3n":
				// dev offline
				break;

			default:
				break;
			}
		}
	}

}
