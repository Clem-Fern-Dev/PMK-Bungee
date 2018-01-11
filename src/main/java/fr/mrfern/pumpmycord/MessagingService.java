package fr.mrfern.pumpmycord;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MessagingService implements Listener {
	
	@EventHandler
	public void OnMessagingServiceReceive(PluginMessageEvent e) throws IOException {
		String tag = e.getTag();
		System.out.println(" Message receive " + tag);
		
		switch (tag) {
		case "BungeeCord":
			
			Main.getMisterP().getPorgTextChannel().sendPorgMessage(" BungeeCord Messaging recieve !").complete();
			
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
		    String subchannel = in.readUTF();
		    if (subchannel.equals("servermanager")) {
		      
		    }
			
			break;

		default:
			return;
		}
	}
	
}
