package fr.mrfern.pumpmycord.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import fr.mrfern.pumpmycord.config.MySQLConnector;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerManager {
	
	private ProxiedPlayer p;
	private HashMap<String,BanData> banDataList;

	public ServerManager(ProxiedPlayer player) {
		setP(player);
		ResultSet listRS = new MySQLConnector().sendQuery("SELECT `ban_ID` FROM `player_ban` WHERE `player_UUID`='" + p.getUniqueId() +"'");	// commande pour récupérer les ids de ban correspondant à ce UUID
		List<Integer> banIDList = new ArrayList<>();	// Instanciation de la liste de ban
		
		// récupération du contenu de la table
		try {
			while(listRS.next()) {
				try {
					// ajout à la liste de banID
					banIDList.add(listRS.getInt("ban_ID"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(!banIDList.isEmpty()) {
				// si pas de ban avec cette uuid alors return false
				for (int banID : banIDList) {
					ResultSet banRS = new MySQLConnector().sendQuery("SELECT `author_UUID`, `author_name`, `ban_type`, `raison`, `bantime_day`, `bantime_hour`, `bantime_minut`, `end_bantime_year`, `end_bantime_month`, `end_bantime_day`, `end_bantime_hour`, `end_bantime_minut`, `player_UUID`, `end` FROM `ban_list` WHERE `id`=" + banID);
					banRS.next();
					
					boolean end = banRS.getBoolean("end");
					
					if(!end) {
						BanData banData = new BanData();
						
						String banType = banRS.getString("ban_type");
						
						banData.setAuthor(banRS.getString("author_name")); 	// set author name dans ban Data
						banData.setAuthor(banRS.getString("author_UUID")); 	// set author UUID dans ban Data
						
						banData.setBanType(banType); 	// set ban type dans ban Data
						
						banData.setRaison(banRS.getString("raison")); 	// set raison dans ban Data
						
						// set temps de ban dans ban Data
						banData.setDay(banRS.getInt("bantime_day"));
						banData.setHour(banRS.getInt("bantime_hour"));
						banData.setMinute(banRS.getInt("bantime_minut"));
						
						//set date de fin de ban dans ban Data
						banData.setYear_end(banRS.getInt("end_bantime_year"));
						banData.setMonth_end(banRS.getInt("end_bantime_month"));
						banData.setDay_end(banRS.getInt("end_bantime_day"));
						banData.setHour_end(banRS.getInt("end_bantime_hour"));
						banData.setMinute_end(banRS.getInt("end_bantime_minut"));
						
						banDataList.put(banType, banData); // ajout à la map les infos sur le ban
					}							
				}
				
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static ServerManager getManager(ProxiedPlayer p) {	
		return new ServerManager(p);
	}

	public ProxiedPlayer getP() {
		return p;
	}

	public void setP(ProxiedPlayer p) {
		this.p = p;
	}
	
	public boolean isBan(String serverName) {
		for (Entry<String, BanData> entry : banDataList.entrySet()) {
			
		}
	}
	
	public boolean getBanIsGlobal() {
		ResultSet listRS = new MySQLConnector().sendQuery("SELECT `ban_ID` FROM `player_ban` WHERE `player_UUID`='" + p.getUniqueId() +"'");	// commande pour récupérer les ids de ban correspondant à ce UUID
		List<Integer> banIDList = new ArrayList<>();	// Instanciation de la liste de ban
		
		try {
			while(listRS.next()) {
				try {
					// ajout à la liste de banID
					System.out.println(listRS.getInt("ban_ID"));
					banIDList.add(listRS.getInt("ban_ID"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if(banIDList.isEmpty()) {
					// si pas de ban avec cette uuid alors return false
					return false;
				}else {
					for (int banID : banIDList) {
						ResultSet banRS = new MySQLConnector().sendQuery("SELECT `ban_type` FROM `ban_list` WHERE id=" + banID);	// récupération du type de ban
						banRS.next();
						if(banRS.getString("ban_type").equals("global")) {
							// si global alors return true isglobal sinon on continue de check
							return true;
						}
					}
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	public String getAuthor(String serverName) {
		
		ResultSet listRS = new MySQLConnector().sendQuery("SELECT `ban_ID` FROM `player_ban` WHERE `player_UUID`='" + p.getUniqueId() +"'");	// commande pour récupérer les ids de ban correspondant à ce UUID
		try {
			// récupération du contenu de la table
			while(listRS.next()) {
				try {
					// ajout à la liste de banID
					int banID = listRS.getInt("ban_ID");
					ResultSet rs = new MySQLConnector().sendQuery("SELECT `author_UUID`, `author_name`, `ban_type`, `raison`, `end` FROM `ban_list` WHERE `id`=" + banID); // récupération de l'UUID du joueur banni
					rs.next();
					
					//String author_UUID = rs.getString("author_UUID");
					String author_name = rs.getString("author_name");
					String ban_type = rs.getString("ban_type");
					//String raison = rs.getString("raison");
					boolean end = rs.getBoolean("end");
					
					if(end) {	// calcul end fait toutes les minutes
						ResultSet removeRS = new MySQLConnector().sendQuery("SELECT `player_UUID` FROM `player_ban` WHERE `ban_ID`=" + banID); // récupération de l'UUID du joueur banni
						removeRS.next();
						String removeUUID = removeRS.getString("player_UUID");						
						new MySQLConnector().sendUpdate("DELETE FROM `player_ban` WHERE `ban_ID`=" + banID);	// suppresion de la ligne correspondante à cette UUID dans la table player_ban
						new MySQLConnector().sendUpdate("UPDATE `ban_list` SET `player_UUID`='" + removeUUID + "' WHERE `id`=" + banID);	// update ligne dans ban_list
					
						// donc deban / vérification du nom du serveur
						if(serverName.equals(ban_type) | "global".equals(ban_type)) {
							return "none";
						}
					}else {
						// sinon ban / vérification du nom du serveur
						if(serverName.equals(ban_type) | "global".equals(ban_type)) {
							return author_name;
						}
					}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "none";
	}
	
	public String getAuthorUUID(String serverName) {
		ResultSet listRS = new MySQLConnector().sendQuery("SELECT `ban_ID` FROM `player_ban` WHERE `player_UUID`='" + p.getUniqueId() +"'");	// commande pour récupérer les ids de ban correspondant à ce UUID
		try {
			// récupération du contenu de la table
			while(listRS.next()) {
				try {
					// ajout à la liste de banID
					int banID = listRS.getInt("ban_ID");
					ResultSet rs = new MySQLConnector().sendQuery("SELECT `author_UUID`, `author_name`, `ban_type`, `raison`, `end` FROM `ban_list` WHERE `id`=" + banID); // récupération de l'UUID du joueur banni
					rs.next();
					
					String author_UUID = rs.getString("author_UUID");
					//String author_name = rs.getString("author_name");
					String ban_type = rs.getString("ban_type");
					//String raison = rs.getString("raison");
					boolean end = rs.getBoolean("end");
					
					if(end) {	// calcul end fait toutes les minutes
						ResultSet removeRS = new MySQLConnector().sendQuery("SELECT `player_UUID` FROM `player_ban` WHERE `ban_ID`=" + banID); // récupération de l'UUID du joueur banni
						removeRS.next();
						String removeUUID = removeRS.getString("player_UUID");						
						new MySQLConnector().sendUpdate("DELETE FROM `player_ban` WHERE `ban_ID`=" + banID);	// suppresion de la ligne correspondante à cette UUID dans la table player_ban
						new MySQLConnector().sendUpdate("UPDATE `ban_list` SET `player_UUID`='" + removeUUID + "' WHERE `id`=" + banID);	// update ligne dans ban_list
					
						// donc deban / vérification du nom du serveur
						if(serverName.equals(ban_type) | "global".equals(ban_type)) {
							return "none";
						}
					}else {
						// sinon ban / vérification du nom du serveur
						if(serverName.equals(ban_type) | "global".equals(ban_type)) {
							return author_UUID;
						}
					}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "none";
	}

	public String getRaison(String serverName) {
		// TODO Auto-generated method stub
		return "no raison";
	}

	public int getDay(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHour(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMinute(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getYear_end(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMonth_end(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDay_end(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHour_end(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMinute_end(String serverName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public HashMap<String,BanData> getBanDataList() {
		return banDataList;
	}

	public void setBanDataList(HashMap<String,BanData> banDataList) {
		this.banDataList = banDataList;
	}	
}
