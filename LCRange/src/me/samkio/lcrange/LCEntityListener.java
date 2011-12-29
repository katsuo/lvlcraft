package me.samkio.lcrange;

import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class LCEntityListener extends EntityListener{
	public LCRange plugin;

	public LCEntityListener(LCRange lcRange) {
		plugin = lcRange;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		if(event.isCancelled()) return;
		if (!Whitelist.worldCheck(event.getEntity().getWorld()))
			return;
		if (!(event instanceof EntityDamageByEntityEvent)) return;
	    EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
		if (! (e.getDamager() instanceof Arrow)) return;
	    Arrow arrow = (Arrow)e.getDamager();
	    if(!(arrow.getShooter() instanceof Player)) return;
	    Player p = (Player) arrow.getShooter();
			if(Whitelist.hasLevel(p, plugin.thisPlug)){
				
				
				if(event.getEntity() instanceof Player){
					Player Damagee = (Player) event.getEntity();
					if(p==Damagee) return;
					}
				int level = LevelFunctions.getLevel(p, plugin.thisPlug);
				if (level >= plugin.LCConfiguration.Range0p5 && level < plugin.LCConfiguration.Range1p0) {
					event.setDamage(1);
				}else if(level >= plugin.LCConfiguration.Range1p0 && level < plugin.LCConfiguration.Range1p5){
					event.setDamage(2);
				}else if(level >= plugin.LCConfiguration.Range1p5 && level < plugin.LCConfiguration.Range2p0){
					event.setDamage(3);
				}else if(level >= plugin.LCConfiguration.Range2p0 && level < plugin.LCConfiguration.Range2p5){
					event.setDamage(4);
				}else if(level >= plugin.LCConfiguration.Range2p5 && level < plugin.LCConfiguration.Range3p0){
					event.setDamage(5);
				}else if(level >= plugin.LCConfiguration.Range3p0){
					event.setDamage(6);
				}
				LevelFunctions.addExp(p, plugin.thisPlug,plugin.LCConfiguration.PerDamageExp * event.getDamage() );
			//plugin.entityListener.EntityDamageByProj((EntityDamageByProjectileEvent) event);
			return;
		}
		
	    
	}

	
}
