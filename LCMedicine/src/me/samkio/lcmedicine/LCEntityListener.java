package me.samkio.lcmedicine;

import java.util.Random;

import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.Whitelist;

import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityListener;

public class LCEntityListener extends EntityListener {
	public LCMedicine plugin;
	public LCEntityListener(LCMedicine instance) {
		plugin = instance;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		if (event.isCancelled()) {
			return;
		}

		if (event instanceof EntityDamageByEntityEvent && event.getEntity() instanceof Player) {
			if (Whitelist.worldCheck(event.getEntity().getWorld())
					&& Whitelist.hasLevel(
							(Player) ((EntityDamageByEntityEvent) event)
									.getEntity(), plugin.thisPlug)) {
					plugin.entListener
							.onEDamageByE((EntityDamageByEntityEvent) event);
					return;

			}
		}
	}

	public void onEDamageByE(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		if(event.getCause() == DamageCause.ENTITY_ATTACK && event.getDamager() instanceof Spider && plugin.LCConfiguration.EnablePoison && !plugin.Poisoned.containsKey((Player) event.getEntity())){
			Random randomGenerator = new Random();
			int random = randomGenerator.nextInt(5);
			if (random == 1) {
				int severity = randomGenerator.nextInt(2);
				if(severity == 0){
					LCChat.warn((Player) event.getEntity(), "You have been lightly poisoned.");
					plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new PoisnedThread(plugin, (Player) event.getEntity()), 600, 600);
					plugin.Poisoned.put((Player) event.getEntity(), 1);
					return;
				}else if(severity == 1){
					LCChat.warn((Player) event.getEntity(), "You have been moderatly poisoned.");
					plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new PoisnedThread(plugin, (Player) event.getEntity()), 600, 600);
					plugin.Poisoned.put((Player) event.getEntity(), 2);
					return;
				}else if(severity == 2){
					LCChat.warn((Player) event.getEntity(), "You have been badly poisoned.");
					plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new PoisnedThread(plugin, (Player) event.getEntity()), 600, 600);
					plugin.Poisoned.put((Player) event.getEntity(), 3);
					return;
				}
				
			}
		}

	}

}
