package me.samkio.lcexplosives;

import java.util.List;

import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class LCEntityListener extends EntityListener {
	final LCExplosives plugin;

	public LCEntityListener(LCExplosives lcExplosives) {
		plugin = lcExplosives;
	}

	public void onExplosionPrime(ExplosionPrimeEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if ((event.getEntity() instanceof LivingEntity))
			return;
		Player primer = null;
		Location l2 = event.getEntity().getLocation();
		for(Location l:plugin.primers.keySet()){
			if(Math.round(l2.getX()) == l.getX()  && Math.round(l2.getZ()) == l.getZ()) {		
				primer = plugin.primers.get(l);
				break;
			}
		}
		if(primer==null) {event.setRadius(plugin.LCConfiguration.DefaultRadius);
		return;
		}
		int level = LevelFunctions.getLevel(primer, plugin.thisPlug);
		if(level<plugin.LCConfiguration.tntLevel){
			LCChat.warn(primer, "You cannot use tnt. Required Level:"+plugin.LCConfiguration.tntLevel);
			event.setCancelled(true);
			return;
		}
		int range = plugin.LCConfiguration.DefaultRadius;
		if (plugin.TNTRange.containsKey(primer)) {
			range = plugin.TNTRange.get(primer);
		}
		int m = (int) (range * plugin.LCConfiguration.explosionMultiplier);
		if(m<1){
			m=1;
		}
		if(m>plugin.LCConfiguration.TntRadiusCap){
			m=plugin.LCConfiguration.TntRadiusCap;
		}
		event.setRadius(m);
		LevelFunctions.addExp(primer, plugin.thisPlug, plugin.LCConfiguration.expPerExplosion);

	}
	public void onEntityExplode(EntityExplodeEvent e) {
		if ((e.getEntity() instanceof LivingEntity))
			return;
		Player primer = null;
		Location l2 = e.getEntity().getLocation();
		for(Location l:plugin.primers.keySet()){
			if(Math.round(l2.getX()) == l.getX()  && Math.round(l2.getZ()) == l.getZ()) {		
				primer = plugin.primers.get(l);
				plugin.primers.remove(l);
				break;
			}
		}
		List<Block> b = e.blockList();
		if(primer!=null){
		for(Block block:b){
			if(block.getType() == Material.TNT){
				plugin.primers.put(block.getLocation(), primer);
			}
		}}
		
		
	}
}
