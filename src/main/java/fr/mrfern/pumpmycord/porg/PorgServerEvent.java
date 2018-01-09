package fr.mrfern.pumpmycord.porg;

import fr.mrfern.pumpmycord.Main;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PorgServerEvent {
	
	public void OnProxyStartEvent(MisterPorg misterP) {
		PorgTextChannel porgChan = Main.getMisterP().getPorgTextChannel();
		
		porgChan.sendPorgMessage("@everyone **Le réseau PumpMyKins est en ligne et opérationnel !** \nRejoingnez nous en vous connectant via pumpmykins.eu , nous vous remercions pour votre patience !").complete();
	}
	
	public void OnProxyStopEvent(MisterPorg misterP) {
		
		PorgTextChannel porgChan = misterP.getPorgTextChannel();
		
		porgChan.sendPorgMessage("@everyone **Le réseau PumpMyKins est hors-ligne !** \nToute l'équipe PMK fait son possible pour vous redonnez l'accès à ces services au plus vite. Restez informé des nouveautés et mises à jours sur le forum/discord/site !").complete();
	}
	
	
	
}
