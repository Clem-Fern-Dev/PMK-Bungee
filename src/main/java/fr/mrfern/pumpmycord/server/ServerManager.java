package fr.mrfern.pumpmycord.server;

import java.util.HashMap;

import fr.mrfern.pumpmycord.Response;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerManager {
	
	private static ServerManager serverManager = new ServerManager();
	private ProxiedPlayer p;
	
	private HashMap<String, ServerState> hashServerState = new HashMap<>();

	public static void initConfig() {
		
	}

	public static ServerManager getManager(ProxiedPlayer p) {
		serverManager.setP(p);
		return serverManager;
	}

	public boolean getServerState(String serverName) {
		
		
		return true;
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
		// TODO Auto-generated method stub
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
		return 0;
	}

	public HashMap<String, ServerState> getHashServerState() {
		return hashServerState;
	}

	public void setHashServerState(HashMap<String, ServerState> hashServerState) {
		this.hashServerState = hashServerState;
	}
	
}
