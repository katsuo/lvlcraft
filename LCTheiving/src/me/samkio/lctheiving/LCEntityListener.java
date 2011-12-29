package me.samkio.lctheiving;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class LCEntityListener extends EntityListener{
	public LCTheiving plugin;

	public LCEntityListener(LCTheiving lc) {
		plugin = lc;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		if (event.isCancelled())
			return;
		if(event.getEntity() instanceof Player){
		plugin.playerListener.closeDialogs((Player) event.getEntity());
		}

	}

	
	
}
