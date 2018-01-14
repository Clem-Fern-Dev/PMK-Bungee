package fr.mrfern.pumpmycord;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MessagingService implements Listener {
	
	@EventHandler
	public void OnMessagingServiceReceive(PluginMessageEvent e) throws Exception {
		String tag = e.getTag();
		
		if(tag.equals("BungeeCord")) {
			System.out.println("bungee cord channel");
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
			String subchannel = in.readUTF();
			
			switch (subchannel) {
			case "prejoinrequest":
				System.out.println("prejoinrequest");
				String serverName = in.readUTF();
				ProxiedPlayer p = Main.getMain().getProxy().getPlayer(e.getReceiver().toString());
				String playerName = p.getDisplayName();
				
				// check server state
				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				//DataOutputStream outMessage = new DataOutputStream(out);
	            //outMessage.writeUTF("prejoinresponse");
	            //outMessage.writeUTF(serverName);
	           // outMessage.writeBoolean(false);
				
	            out.write(165);
			
				p.getServer().sendData("BungeeCord", out.toByteArray());
				
				
				break;

			default:
				throw new Exception("OnMessagingService subchannel error ( " + subchannel + " inconnu )");
			}
			
			
		}
	}
	
}
