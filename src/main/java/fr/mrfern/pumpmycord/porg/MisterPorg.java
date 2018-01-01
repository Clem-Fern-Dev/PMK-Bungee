package fr.mrfern.pumpmycord.porg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.login.LoginException;

//this.getJda().getTextChannelById("387326167499276292")

import fr.mrfern.pumpmycord.Main;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.md_5.bungee.api.config.ServerInfo;

public class MisterPorg {

	private JDA jda;
	private boolean isOK;
	private Main main;
	private PorgTextChannel porgTextChannel;
	
	

	public MisterPorg(Main m,String token,String channelID) {
		isOK = false;
		main = m;
		
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
			isOK = true;
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RateLimitedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isOK) {
			main.getLogger().info(" JDA communication ... succes !");	
			
			porgTextChannel = new PorgTextChannel(this, channelID);
			porgTextChannel.sendMessage("Bungee started").complete();
			
			if(!porgTextChannel.isOK()) {
				main.getLogger().severe(" JDA PorgTextChannel initialisation ... echec !");
				forceClose();
			}else {
				main.getLogger().info(" JDA PorgTextChannel initialisation ... succes !");
				initDefaultChannel(porgTextChannel);
			}
		}else {
			main.getLogger().severe(" JDA communication ... echec !");
			forceClose();
		}		
	}
	
	private void initDefaultChannel(PorgTextChannel porgTextChannel) {
		
		/*List<Message> messageList = porgTextChannel.getListPinnedMessages();
		
		if(messageList.size() < 1) {
		
		}
			// creation du message bungee
			
			// création du message par serveur
			Map<String, ServerInfo> hashServer = main.getProxy().getServers();
			
			if(!(hashServer.size() < 1)) {
			
				for (Entry<String, ServerInfo> server : hashServer.entrySet()) {
					
					boolean messageOK = false;
					messageList = porgTextChannel.getListPinnedMessages();
					
					if(messageList.size() < 1) {
						// création du message par serveur
					}
					
					for (Message message : messageList) {
						
						if(message.getContent().startsWith("#µ" + server.getKey() + "#µ")){
							
							messageOK = true;
							
							//porgTextChannel.addPorgMessage(message);
							break;
						}
					}
					
					if(!messageOK) {
						porgTextChannel.sendMessage("#µ" + server.getKey() + "#µ").complete();
					}			
				}
			}
		}*/
		
	}
	
	public void close() {
		main.getLogger().warning(" JDA shutdown !");
		jda.shutdown();		
	}
	
	private void forceClose() {
		main.getLogger().severe(" JDA force shutodown !");
		jda.shutdownNow();		
	}


	public MisterPorg addListener(Object... listeners) {
		jda.addEventListener(listeners);
		return this;
	}

	public JDA getJda() {
		return jda;
	}

	public void setJda(JDA jda) {
		this.jda = jda;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public PorgTextChannel getPorgTextChannel() {
		return porgTextChannel;
	}

	public void setPorgTextChannel(PorgTextChannel porgTextChannel) {
		this.porgTextChannel = porgTextChannel;
	}
	
}
