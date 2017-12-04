package fr.mrfern.pumpmycord.porg;

import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

import fr.mrfern.pumpmycord.Main;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class MisterPorg {

	private JDA jda ;
	private static MisterPorg misterPorg = new MisterPorg();
	private static Main main;
	
	private  HashMap<String, TextChannel> channelMap;
	private  TextChannel defaultChannel;
	
	public static MisterPorg getBorg(Main mainClass) {	
		main = mainClass;
		return misterPorg;
	}
	
	public MisterPorg buildBot(String token) {
		try {
			
			jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
			System.out.println("Bot connected !");
			return misterPorg;
			
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public MisterPorg addEventListener(Object listeners) {
		jda.addEventListener(listeners);
		return misterPorg;
		
	}
	
	public MisterPorg buildChannelMap() {
		
		List<TextChannel> textChannelsList = jda.getTextChannels();
		HashMap<String, TextChannel> channelMap = new HashMap<String, TextChannel>();
		for (TextChannel textChannel : textChannelsList) {
			channelMap.put(textChannel.getName(), textChannel);
		}
		
		return misterPorg;		
	}
	
	public MisterPorg setDefaultChannel(TextChannel defaultChannel) {
		this.defaultChannel = defaultChannel;	
		return misterPorg;
	}
	
	public void InitPorg() {
		TextChannel borgChannel = getDefaultChannel();
		
		EmbedBuilder embedInit = new EmbedBuilder();
		
		embedInit.setAuthor(main.getProxy().getName(), "http://62.4.16.222/", "https://media-elerium.cursecdn.com/avatars/108/599/636373699312638191.png");
		
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public HashMap<String, TextChannel> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(HashMap<String, TextChannel> channelMap) {
		this.channelMap = channelMap;
	}

	public TextChannel getDefaultChannel() {
		return defaultChannel;
	}
	
}
