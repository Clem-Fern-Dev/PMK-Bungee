package fr.mrfern.pumpmycord.porg;

import fr.mrfern.pumpmycord.Main;
import net.dv8tion.jda.core.MessageBuilder;
import net.md_5.bungee.config.Configuration;

public class PorgServerEvent {
	
	public void OnProxyStartEvent(MisterPorg misterP) {		
		try {
			Configuration config = Main.getConf().getConfiguration("config.yml");
			
			PorgTextChannel porgChan = Main.getMisterP().getPorgTextChannel();
			
			if(config.getBoolean("discord.debug_mod")) {				
				porgChan.sendPorgMessage(new MessageBuilder().append("�0o").append(misterP.getJda().getTextChannelById("375790951081181187")).append(" __debug_mod__ **Le r�seau PumpMyKins est en ligne et op�rationnel !** \nRejoingnez nous en vous connectant via pumpmykins.eu , nous vous remercions pour votre patience !").build())
				.complete();				
			}else {			
				porgChan.sendPorgMessage("�0o @everyone **Le r�seau PumpMyKins est en ligne et op�rationnel !** \nRejoingnez nous en vous connectant via pumpmykins.eu , nous vous remercions pour votre patience !").complete();	
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
				porgChan.sendPorgMessage(new MessageBuilder().append("�0n").append( misterP.getJda().getTextChannelById("375790951081181187")).append(" __debug_mod__ **Le r�seau PumpMyKins est hors-ligne !** \nToute l'�quipe PMK fait son possible pour vous redonnez l'acc�s � ces services au plus vite. Restez inform� des nouveaut�s et mises � jours sur le forum/discord/site !").build())
				.complete();
			}else {
				porgChan.sendPorgMessage("�0n @everyone **Le r�seau PumpMyKins est hors-ligne !** \nToute l'�quipe PMK fait son possible pour vous redonnez l'acc�s � ces services au plus vite. Restez inform� des nouveaut�s et mises � jours sur le forum/discord/site !").complete();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void OnDonationRecord() {
		
	}
	
	public void OnDonationGameBonus() {
		
	}
	
}
