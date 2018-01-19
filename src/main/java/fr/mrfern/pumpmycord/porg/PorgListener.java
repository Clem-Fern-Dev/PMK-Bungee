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
				ServerManager.getServerStateClass("ragna1").setState(true);
				break;
			case "µ1n":
				// ragna1 offline
				ServerManager.getServerStateClass("ragna1").setState(false);
				break;
			case "µ2o":
				// ragna2 online
				ServerManager.getServerStateClass("ragna2").setState(true);
				break;
			case "µ2n":
				// ragn2 offline
				ServerManager.getServerStateClass("ragna2").setState(false);
				break;
			case "µ3o":
				// dev online
				ServerManager.getServerStateClass("dev").setState(true);
				break;
			case "µ3n":
				// dev offline
				ServerManager.getServerStateClass("dev").setState(false);
				break;

			default:
				break;
			}
		}
	}

}
