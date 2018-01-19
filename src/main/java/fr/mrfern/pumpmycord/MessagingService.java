package fr.mrfern.pumpmycord;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import fr.mrfern.pumpmycord.server.ServerManager;
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
			String serverName, subchannel = in.readUTF();
			ProxiedPlayer p;
			String playerName;
			boolean serverState;
			
			
			switch (subchannel) {
			case "prejoinrequest":
				serverName = in.readUTF();
				p = Main.getMain().getProxy().getPlayer(e.getReceiver().toString());
				playerName = p.getDisplayName();
				
				serverState = ServerManager.getManager(p).getServerState(serverName);
				
				if(!serverState) {
					// serveur offline
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					DataOutputStream outMessage = new DataOutputStream(out);
					outMessage.writeUTF("prejoinresponse");
		            outMessage.writeUTF(serverName);
		            outMessage.writeBoolean(false);
				
					p.getServer().sendData("BungeeCord", out.toByteArray());
				}else {
					boolean isBan = ServerManager.getManager(p).isBan(serverName);
					
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					DataOutputStream outMessage = new DataOutputStream(out);
					outMessage.writeUTF("prejoinresponse");
		            outMessage.writeUTF(serverName);
		            outMessage.writeBoolean(true);
					
					if(isBan) {	
						// ban , envoie des infos sur le bans
			            outMessage.writeBoolean(true);
			            outMessage.writeUTF(ServerManager.getManager(p).getAuthor(serverName));
			            outMessage.writeUTF(ServerManager.getManager(p).getAuthorUUID(serverName));
			            
			            outMessage.writeBoolean(ServerManager.getManager(p).getBanIsGlobal());
			            
			            outMessage.writeUTF(ServerManager.getManager(p).getRaison());
			            
			            outMessage.writeInt(ServerManager.getManager(p).getDay());
			            outMessage.writeInt(ServerManager.getManager(p).getHour());
			            outMessage.writeInt(ServerManager.getManager(p).getMinute());
			            
			            outMessage.writeInt(ServerManager.getManager(p).getYear_end());
			            outMessage.writeInt(ServerManager.getManager(p).getMonth_end());
			            outMessage.writeInt(ServerManager.getManager(p).getDay_end());
			            outMessage.writeInt(ServerManager.getManager(p).getHour_end());
			            outMessage.writeInt(ServerManager.getManager(p).getMinute_end());
			            
			            
			            p.getServer().sendData("BungeeCord", out.toByteArray());
					}else {
						// Non ban, envoie de la confirmation
						outMessage.writeBoolean(false);						
						p.getServer().sendData("BungeeCord", out.toByteArray());
					}
				}
				break;
				
			case "joinreq":
				
				serverName = in.readUTF();
				p = Main.getMain().getProxy().getPlayer(e.getReceiver().toString());
				playerName = p.getDisplayName();
				
				serverState = ServerManager.getManager(p).getServerState(serverName);
				
				if(serverState) {
					
					int plyNb = ServerManager.getManager(p).getPlayerNumber(serverName);
					// serveur online, envoit du nombre de joueur
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					DataOutputStream outMessage = new DataOutputStream(out);
					outMessage.writeUTF("joinresponse");
				    outMessage.writeUTF(serverName);
				    outMessage.writeBoolean(true);
				    outMessage.writeInt(plyNb);
						
				    p.getServer().sendData("BungeeCord", out.toByteArray());
				}else {
					// serveur offline
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					DataOutputStream outMessage = new DataOutputStream(out);
					outMessage.writeUTF("joinresponse");
		            outMessage.writeUTF(serverName);
		            outMessage.writeBoolean(false);
				
					p.getServer().sendData("BungeeCord", out.toByteArray());
				}
				break;
			
				
			default:
				throw new Exception("OnMessagingService subchannel error ( " + subchannel + " inconnu )");
			}
			
			
		}
	}
	
}
