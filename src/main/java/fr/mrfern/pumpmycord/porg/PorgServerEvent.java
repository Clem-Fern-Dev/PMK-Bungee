package fr.mrfern.pumpmycord.porg;

import fr.mrfern.pumpmycord.Main;
import net.md_5.bungee.config.Configuration;

public class PorgServerEvent {
	
	public void OnProxyStartEvent(MisterPorg misterP) {		
		try {
			Configuration config = Main.getConf().getConfiguration("config.yml");
			
			PorgTextChannel porgChan = Main.getMisterP().getPorgTextChannel();
			
			if(config.getBoolean("discord.debug_mod")) {
				
				porgChan.sendPorgMessage("#fix __debug_mod__ **Le réseau PumpMyKins est en ligne et opérationnel !** \nRejoingnez nous en vous connectant via pumpmykins.eu , nous vous remercions pour votre patience !").complete();
				
			}else {
			
				porgChan.sendPorgMessage("@everyone **Le réseau PumpMyKins est en ligne et opérationnel !** \nRejoingnez nous en vous connectant via pumpmykins.eu , nous vous remercions pour votre patience !").complete();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void OnProxyStopEvent(MisterPorg misterP) {
		
		try {
			Configuration config = Main.getConf().getConfiguration("config.yml");
			PorgTextChannel porgChan = misterP.getPorgTextChannel();
			
			if(config.getBoolean("discord.debug_mod")) {
				porgChan.sendPorgMessage("#fix __debug_mod__ **Le réseau PumpMyKins est hors-ligne !** \nToute l'équipe PMK fait son possible pour vous redonnez l'accès à ces services au plus vite. Restez informé des nouveautés et mises à jours sur le forum/discord/site !").complete();
			}else {
				porgChan.sendPorgMessage("@everyone **Le réseau PumpMyKins est hors-ligne !** \nToute l'équipe PMK fait son possible pour vous redonnez l'accès à ces services au plus vite. Restez informé des nouveautés et mises à jours sur le forum/discord/site !").complete();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
