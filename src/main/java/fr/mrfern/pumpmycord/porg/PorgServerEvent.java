package fr.mrfern.pumpmycord.porg;

import fr.mrfern.pumpmycord.Main;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PorgServerEvent implements Listener {

	// Appelle de l'event quand serveur connect� et op�rationnel
	@EventHandler
	public void onServeurConnected(ServerConnectedEvent e) {
		
		PorgTextChannel porgChan = Main.getMisterP().getPorgTextChannel();
		
		porgChan.sendPorgMessage("@everyone " + e.getServer().getInfo().getName() + " est en ligne et op�rationnel ! \n Rejoingnez nous en vous connectant via pumpmykins.eu").complete();
		
	}
	
	// Appelle de l'event quand serveur connect� et op�rationnel
	@EventHandler
	public void onServeurDisconnected(ServerDisconnectEvent e) {
			
		PorgTextChannel porgChan = Main.getMisterP().getPorgTextChannel();
			
		porgChan.sendPorgMessage("@everyone Le r�seau PumpMyKins est en ligne et op�rationnel ! \n Rejoingnez nous en vous connectant via pumpmykins.eu , nous vous remercions pour votre patience !").complete();
			
	}
	
	public void OnProxyStartEvent(MisterPorg misterP) {
		
	}
	
	public void OnProxyStopEvent(MisterPorg misterP) {
		
		PorgTextChannel porgChan = misterP.getPorgTextChannel();
		
		porgChan.sendPorgMessage("@everyone Le r�seau PumpMyKins est hors-ligne ! \n Toute l'�quipe PMK fait son possible pour vous redonnez l'acc�s � ces services au plus vite. Restez inform� des nouveaut�s et mise � jour sur le forum/discord/site !").complete();
	}
	
	
	
}
