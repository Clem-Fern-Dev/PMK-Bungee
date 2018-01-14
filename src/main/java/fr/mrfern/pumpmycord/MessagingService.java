package fr.mrfern.pumpmycord;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MessagingService implements Listener {
	
	@EventHandler
	public void OnMessagingServiceReceive(PluginMessageEvent e) throws Exception {
		String tag = e.getTag();
		ProxiedPlayer p = Main.getProxyServer().getPlayer(e.getReceiver().toString());
		
		if(tag.equals("BungeeCord")) {
			
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
			String subchannel = in.readUTF();
			
			switch (subchannel) {
			case "prejoinrequest":
				
				String serverName = in.readUTF();
				
				// check server state
				
				p.sendData("BungeeCord", null);
				
				
				break;

			default:
				throw new Exception("OnMessagingService subchannel error ( " + subchannel + " inconnu )");
			}
			
			
		}
	}
	
}
