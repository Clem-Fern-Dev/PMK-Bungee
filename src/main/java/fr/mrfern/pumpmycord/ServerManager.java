package fr.mrfern.pumpmycord;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerManager {
	
	private static ServerManager serverManager = new ServerManager();
	private ProxiedPlayer p;

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
}
