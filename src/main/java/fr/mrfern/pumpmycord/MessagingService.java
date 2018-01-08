package fr.mrfern.pumpmycord;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MessagingService implements Listener {
	
	@EventHandler
	public void OnMessagingServiceReceive(PluginMessageEvent e) {
		String tag = e.getTag();
		System.out.println(" Message receive " + tag);
		
		switch (tag) {
		case "BungeeCord":
			
			Main.getMisterP().getPorgTextChannel().sendPorgMessage(" BungeeCord Messaging recieve !").complete();
			
			break;

		default:
			return;
		}
	}
	
}
