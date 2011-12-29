package me.samkio.lcexplosives;

import me.samkio.levelcraftcore.Whitelist;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class LCBlockListener extends BlockListener {
	public LCExplosives plugin;

	public LCBlockListener(LCExplosives instance) {
		plugin = instance;
	}
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if(!Whitelist.worldCheck(event.getBlock().getWorld()))return;
		Player player = event.getPlayer();
		//int iih = player.getItemInHand().getTypeId();
		Material m = event.getBlock().getType();
		
		if(!Whitelist.hasLevel(player, plugin.thisPlug)) return;
		if(m == Material.TNT){
			if(plugin.LCConfiguration.BlockTnt){
				event.getBlock().setType(Material.AIR);
				event.setCancelled(true);
				return;
			}
		 plugin.primers.put( event.getBlock().getLocation(),player);
		}
		
	}
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.isCancelled()) {
			return;
		}
	}
	
}
