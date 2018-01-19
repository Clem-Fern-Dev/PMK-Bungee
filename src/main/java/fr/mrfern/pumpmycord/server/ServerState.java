package fr.mrfern.pumpmycord.server;

import net.md_5.bungee.api.config.ServerInfo;

public class ServerState {

	private String name;
	private int playerCount;
	private boolean state;
	private ServerInfo serverInfo;
	
	public ServerState() {
		state = false;
		serverInfo = null;
		playerCount = 0;
	}
	
	public void updatePlayerCount() {
		if (serverInfo != null) {
			playerCount = serverInfo.getPlayers().size();
		}
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public ServerInfo getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(ServerInfo serverInfo) {
		this.serverInfo = serverInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
