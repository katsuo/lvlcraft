package me.samkio.lcexplosives;

import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;
import net.minecraft.server.World;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class LCPlayerListener extends PlayerListener {
	private LCExplosives plugin;

	public LCPlayerListener(LCExplosives lcExplosives) {
		this.plugin = lcExplosives;
	}

	/*
	 * public void onPlayerAnimation(PlayerAnimationEvent e) {
	 * if(!Whitelist.worldCheck(e.getPlayer().getWorld()))return;
	 * 
	 * if(e.getAnimationType()==PlayerAnimationType.ARM_SWING){ Player p =
	 * e.getPlayer(); if(!Whitelist.hasLevel(p, plugin.thisPlug)) return;
	 * Material iih = p.getItemInHand().getType();
	 * if(iih==Material.REDSTONE_TORCH_OFF ||iih==Material.REDSTONE_TORCH_ON){
	 * int prevRange = 0; if(plugin.DynamiteRange.containsKey(p)) { prevRange =
	 * plugin.DynamiteRange.get(p); plugin.DynamiteRange.remove(p); }
	 * if(prevRange==plugin.LCConfiguration.DynamiteRadiusCap){
	 * plugin.DynamiteRange.put(p, 1); LCChat.info(p,"Dynamite Range set to:1");
	 * return; } plugin.DynamiteRange.put(p, prevRange+1);
	 * LCChat.info(p,"Dynamite Range set to:"+(prevRange+1)); return;
	 * 
	 * } } }
	 */
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK
				&& e.getAction() != Action.LEFT_CLICK_AIR)
			return;
		if (!Whitelist.worldCheck(e.getPlayer().getWorld()))
			return;

		Player player = e.getPlayer();
		if (!Whitelist.hasLevel(player, plugin.thisPlug))
			return;
	
		int iih = player.getItemInHand().getTypeId();
		Material iihm = player.getItemInHand().getType();

		if ((iihm == Material.REDSTONE_TORCH_OFF || iihm == Material.REDSTONE_TORCH_ON)
				&& e.getAction() == Action.LEFT_CLICK_AIR && !plugin.LCConfiguration.BlockDynamite) {
			int prevRange = 0;
			int level = LevelFunctions.getLevel(player, plugin.thisPlug);
			if(level<plugin.LCConfiguration.dynamiteLevel) return;
			if (plugin.DynamiteRange.containsKey(player)) {
				prevRange = plugin.DynamiteRange.get(player);
				plugin.DynamiteRange.remove(player);
			}
			if (prevRange >= plugin.LCConfiguration.DynamiteRadiusCap || prevRange >= Math.round(level/3)) {
				plugin.DynamiteRange.put(player, 1);
				LCChat.info(player, "Dynamite Range set to:1");
				return;
			}
			plugin.DynamiteRange.put(player, prevRange + 1);
			LCChat.info(player, "Dynamite Range set to:" + (prevRange + 1));
			return;

		}
		if (iihm == Material.TNT
				&& e.getAction() == Action.LEFT_CLICK_AIR && !plugin.LCConfiguration.BlockTnt) {
			int prevRange = 0;
			int level = LevelFunctions.getLevel(player, plugin.thisPlug);
			if(level<plugin.LCConfiguration.tntLevel) return;
			if (plugin.TNTRange.containsKey(player)) {
				prevRange = plugin.TNTRange.get(player);
				plugin.TNTRange.remove(player);
			}
			if (prevRange == plugin.LCConfiguration.TntRadiusCap || prevRange >= Math.round(level/3)) {
				plugin.TNTRange.put(player, 1);
				LCChat.info(player, "TNTRange set to:1");
				return;
			}
			plugin.TNTRange.put(player, prevRange + 1);
			LCChat.info(player, "TNT Range set to:" + (prevRange + 1));
			return;

		}
		if (iihm == Material.EGG
				&& e.getAction() == Action.LEFT_CLICK_AIR && !plugin.LCConfiguration.BlockGrenade) {
			int level = LevelFunctions.getLevel(player, plugin.thisPlug);
			if(level<plugin.LCConfiguration.grenadeLevel) return;
			if (plugin.EnableGrenade.containsKey(player)) {
				plugin.EnableGrenade.remove(player);
				LCChat.info(player, "Grenades Off.");
				return;
			}else{
				plugin.EnableGrenade.put(player,0);
				LCChat.info(player, "Grenades On.");
				return;
			}
		

		}
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		Material m = e.getClickedBlock().getType();
		if ((m == Material.REDSTONE_TORCH_OFF || m == Material.REDSTONE_TORCH_ON)
				&& !plugin.LCConfiguration.BlockDynamite && iih == 259) {
			int level = LevelFunctions.getLevel(player, plugin.thisPlug);
			if (level < plugin.LCConfiguration.dynamiteLevel)
				return;
			int task = plugin
					.getServer()
					.getScheduler()
					.scheduleSyncRepeatingTask(plugin,
							new DynamiteFlash(plugin, e.getClickedBlock()), 10,
							10);
			plugin.flashing.put(e.getClickedBlock(), task);
			e.setCancelled(true);
			Location loc = e.getClickedBlock().getLocation();
			World world = ((CraftWorld) loc.getWorld()).getHandle();
			int range = plugin.LCConfiguration.DefaultRadius;
			if (plugin.DynamiteRange.containsKey(player)) {
				range = plugin.DynamiteRange.get(player);
			}
			int i = (int) (range * plugin.LCConfiguration.explosionMultiplier);
			if (i < 1) {
				i = 1;
			}
			if (i > plugin.LCConfiguration.DynamiteRadiusCap) {
				i = plugin.LCConfiguration.DynamiteRadiusCap;
			}
			plugin.getServer()
					.getScheduler()
					.scheduleSyncDelayedTask(
							plugin,
							new DynamiteTimer(plugin, loc, world, i, e
									.getPlayer(), e.getClickedBlock()), 60);

		}
		
	}
	  public void onPlayerEggThrow(PlayerEggThrowEvent event) {
         Egg egg = event.getEgg();
         event.isHatching();
	     Location loc = egg.getLocation();
	     World world = ((CraftWorld)loc.getWorld()).getHandle();
	     Player player = event.getPlayer();
	     plugin.Grenade.eggThrown(loc, player, world, egg, event);
	     event.setHatching(plugin.Grenade.isHatching);

	    }
}
