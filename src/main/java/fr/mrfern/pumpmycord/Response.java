package fr.mrfern.pumpmycord;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Response {

	private byte[] buff;
	private ProxiedPlayer sender;
	private String channel;
	
	public Response(ProxiedPlayer sen, String chan, byte[] bytes) {
		setBuff(bytes);
		setSender(sen);
		setChannel(chan);		
	}

	public byte[] getBuff() {
		return buff;
	}

	public void setBuff(byte[] buff) {
		this.buff = buff;
	}

	public ProxiedPlayer getSender() {
		return sender;
	}

	public void setSender(ProxiedPlayer sender) {
		this.sender = sender;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
