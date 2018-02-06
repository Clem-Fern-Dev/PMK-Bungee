package fr.mrfern.pumpmycord;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

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
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));	// d�claration du data input
			String serverName, subchannel = in.readUTF();	// d�claration dela variable du nom de serveur / r�cup�ration du nom du channel
			ProxiedPlayer p;
			String playerName;			
			
			switch (subchannel) {
			case "prejoinrequest":
				serverName = in.readUTF();	//get server name
				p = Main.getMain().getProxy().getPlayer(e.getReceiver().toString());		// get du Proxy player
				playerName = p.getDisplayName();	//nom du joueur
				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				DataOutputStream outMessage = new DataOutputStream(out);
				outMessage.writeUTF("prejoinresponse");	// nom de la r�ponse
		        outMessage.writeUTF(serverName);	// nom du serveur vis� par la requete
		        
		        ServerManager man = ServerManager.getManager(p);	// r�cup�ration de l'instance du manager li� au joueur
		        
		        boolean isBan = man.isBan(serverName); 	// r�cup�ration de ban ou pas du joueur				
		        if(isBan) {
		        	//si ban
		        	outMessage.writeBoolean(true);	// true pour ban
		        	// INUTILE outMessage.writeBoolean(ServerManager.getManager(p).getBanIsGlobal()); 	// envoie du boolean ban all ou non
		        	
		        	// r�cup�ration des informations sur l'auteur du ban (nom/UUID)
		        	outMessage.writeUTF(man.getAuthor(serverName));
		        	outMessage.writeUTF(man.getAuthorUUID(serverName));
		        	
		        	//r�cup�ration de la raison
		        	outMessage.writeUTF(man.getRaison(serverName));
		        	
		        	// r�cup�ration des informations sur la dur�e du ban
		        	outMessage.writeInt(man.getDay(serverName));
		        	outMessage.writeInt(man.getHour(serverName));
		        	outMessage.writeInt(man.getMinute(serverName));
		        	
		        	// r�cup�ration des informations sur la date de fin du ban
		        	outMessage.writeInt(man.getYear_end(serverName));
		        	outMessage.writeInt(man.getMonth_end(serverName));
		        	outMessage.writeInt(man.getDay_end(serverName));
		        	outMessage.writeInt(man.getHour_end(serverName));
		        	outMessage.writeInt(man.getMinute_end(serverName));
		        	
		        }else {
		        	//si pas ban
		        	outMessage.writeBoolean(false);		//false pour non ban        	
		        }
		        
		        p.getServer().sendData("BungeeCord", out.toByteArray());	// envoie des datas
		        // optimization RAM
		        outMessage.close();
		        out.close();
				break;
				
			/*case "joinreq":
				
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
			
			*/	
			default:
				throw new Exception("OnMessagingService subchannel error ( " + subchannel + " inconnu )");
			}
			
			
		}
	}
	
}
