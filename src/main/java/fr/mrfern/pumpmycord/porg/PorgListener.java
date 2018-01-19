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
			case "µ1o":
				// ragna1 online
				break;
			case "µ1n":
				// ragna1 offline
				break;
			case "µ2o":
				// ragna2 online
				break;
			case "µ2n":
				// ragn2 offline
				break;
			case "µ3o":
				// dev online
				break;
			case "µ3n":
				// dev offline
				break;

			default:
				break;
			}
		}
	}

}
