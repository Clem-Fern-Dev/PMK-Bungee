package fr.mrfern.pumpmycord.porg;

import javax.security.auth.login.LoginException;

import fr.mrfern.pumpmycord.Main;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class MisterPorg {

	private JDA jda;
	private boolean isOK;
	private Main main;

	public MisterPorg(Main m,String token) {
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
			main.getLogger().info("[ PumpMyCord ] JDA communication ... succes !");
		}else {
			main.getLogger().warning("[ PumpMyCord ] JDA communication ... echec !");
		}
		
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
	
}
