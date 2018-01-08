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
			
		porgChan.sendPorgMessage("@everyone " + e.getTarget().getName() + " est hors-ligne ! \n Il sera � nouveau disponible dans les plus brefs d�lais. Restez inform� des nouveaut�s et mise � jour sur le forum/discord/site !").complete();
			
	}
	
	
	
}
