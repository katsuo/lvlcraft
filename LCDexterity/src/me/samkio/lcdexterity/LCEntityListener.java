package me.samkio.lcdexterity;

import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityListener;

public class LCEntityListener extends EntityListener{
	public LCDexterity plugin;

	public LCEntityListener(LCDexterity lcRange) {
		plugin = lcRange;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		if (event.isCancelled())
			return;
		if (!Whitelist.worldCheck(event.getEntity().getWorld()))
			return;
		if(!(event.getEntity() instanceof Player)) return;
		if(!Whitelist.hasLevel((Player) event.getEntity(), plugin.thisPlug)) return;
		if(event.getCause() != DamageCause.FALL) return;
		Player p = (Player) event.getEntity();
		int lvl = LevelFunctions.getLevel(p, plugin.thisPlug);
		int damageReduction = (int) ((lvl / 10) * plugin.LCConfiguration.fdMultiplier);

		if (event.getDamage() <= damageReduction) {
			event.setDamage(0);
		} else {
			event.setDamage(event.getDamage() - damageReduction);
		}
		
		
		}

	}

	
	

