package fr.mrfern.pumpmycord.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.mrfern.pumpmycord.Response;
import fr.mrfern.pumpmycord.config.MySQLConnector;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerManager {
	
	private static ServerManager serverManager = new ServerManager();
	private ProxiedPlayer p;
	
	private static HashMap<String, ServerState> hashServerState = new HashMap<>();

	public static void initConfig() {
		
	}
	
	public static ServerState getServerStateClass(String serverName) {
		return hashServerState.get(serverName);
	}
	
	public static void addServer(String serverName, ServerState serverState) {
		hashServerState.put(serverName, serverState);
	}

	public static ServerManager getManager(ProxiedPlayer p) {
		serverManager.setP(p);
		return serverManager;
	}

	public boolean getServerState(String serverName) {
		return hashServerState.get(serverName).isState();
	}

	public ProxiedPlayer getP() {
		return p;
	}

	public void setP(ProxiedPlayer p) {
		this.p = p;
	}
	
	public void sendResponse(Response Resp) {
		p.sendData(Resp.getChannel(), Resp.getBuff());
	}

	public boolean isBan(String serverName) {
		System.out.println(p.getUniqueId());
		ResultSet listRS = new MySQLConnector().sendQuery("SELECT `ban_ID` FROM `player_ban` WHERE `player_UUID`='" + p.getUniqueId() +"'");	// commande pour récupérer les ids de ban correspondant à ce UUID
		List<Integer> banIDList = new ArrayList<>();
		
		try {
			// récupération du contenu de la table
			while(listRS.next()) {
				try {
					// ajout à la liste de banID
					System.out.println(listRS.getInt("ban_ID"));
					banIDList.add(listRS.getInt("ban_ID"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
			if(banIDList.isEmpty()) {
				// si pas de ban avec cette uuid alors return false
				System.out.println("pas de ban, liste vide");
				return false;
			}else {
				for (int banID : banIDList) {
					ResultSet banRS = new MySQLConnector().sendQuery("SELECT `author_UUID`, `author_name`, `ban_type`, `end` FROM `ban_list` WHERE id=" + banID);
					banRS.next();
					
					if(banRS.getBoolean("end")) {	// calcul end fait toutes les minutes
						ResultSet removeRS = new MySQLConnector().sendQuery("SELECT `player_UUID` FROM `player_ban` WHERE `ban_ID`=" + banID); // récupération de l'UUID du joueur banni
						removeRS.next();
						String removeUUID = removeRS.getString("player_UUID");						
						new MySQLConnector().sendUpdate("DELETE FROM `player_ban` WHERE `ban_ID`=" + banID);	// suppresion de la ligne correspondante à cette UUID dans la table player_ban
						new MySQLConnector().sendUpdate("UPDATE `ban_list` SET `player_UUID`='" + removeUUID + "' WHERE `id`=" + banID);	// update ligne dans ban_list
					
						// donc deban / vérification du nom du serveur
						if(serverName.equals(banRS.getString("ban_type")) | banRS.getString("ban_type").equals("global")) {
							return false;
						}
					}else {
						// sinon ban
						if(serverName.equals(banRS.getString("ban_type")) | banRS.getString("ban_type").equals("global")) {
							return true;
						}
					}					
				}
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getAuthor(String serverName) {
		// TODO Auto-generated method stub
		return "CrazyDoC";
	}
	
	public String getAuthorUUID(String serverName) {
		// TODO Auto-generated method stub
		return "CrazyDoC";
	}

	public boolean getBanIsGlobal() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getRaison() {
		// TODO Auto-generated method stub
		return "no raison";
	}

	public int getDay() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHour() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMinute() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getYear_end() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMonth_end() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDay_end() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHour_end() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMinute_end() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getPlayerNumber(String serverName) {
		hashServerState.get(serverName).updatePlayerCount();
		return hashServerState.get(serverName).getPlayerCount();
	}

	public HashMap<String, ServerState> getHashServerState() {
		return hashServerState;
	}

	public void setHashServerState(HashMap<String, ServerState> hashServerState) {
		this.hashServerState = hashServerState;
	}
	
}
