package me.samkio.lcdexterity;

import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LCPlayerListener extends PlayerListener{
	public LCDexterity plugin;

	public LCPlayerListener(LCDexterity instance) {
		plugin = instance;
	}
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(!plugin.levels.containsKey(player)) plugin.levels.put(player, LevelFunctions.getLevel(player,plugin.thisPlug));
		if (!Whitelist.hasLevel(player, plugin.thisPlug))
			return;
		if (event.getFrom().getBlockY() < event.getTo().getBlockY()) {
			LevelFunctions.addExp(player, plugin.thisPlug, plugin.LCConfiguration.ExpPerJump);
			int blockOn = player
					.getWorld()
					.getBlockAt(player.getLocation().getBlockX(),
							player.getLocation().getBlockY() - 2,
							player.getLocation().getBlockZ()).getTypeId();

			if (blockOn == 0 || blockOn == 8 || blockOn == 9 || blockOn == 50
					|| blockOn == 65)
				return;
			if (plugin.LCConfiguration.AllowJump && !player.isSneaking()) {
				double y = 0;
				int x = -2;
				int z = -2;
				int Level = plugin.levels.get(player);
				if (player.getInventory().getBoots().getType() == Material.GOLD_BOOTS
						&& Level >= plugin.LCConfiguration.GoldBoots) {
					y = 0.8 * plugin.LCConfiguration.jumpMultiplier;
				}else
				if (player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS
						&& Level >= plugin.LCConfiguration.DiamondBoots) {
					y = 1 * plugin.LCConfiguration.jumpMultiplier;
				}
				if (y > 0) {
					Vector vector = new Vector();
					vector.setX(x * 0 / 10);
					vector.setY(y);
					vector.setZ(z * 0 / 10);
					player.setVelocity(vector);
				}
			}
		}
		
		if (!player.isSneaking() || !plugin.LCConfiguration.AllowRun)
			return;
		int blockOn = player
				.getWorld()
				.getBlockAt(player.getLocation().getBlockX(),
						player.getLocation().getBlockY() - 1,
						player.getLocation().getBlockZ()).getTypeId();
		if (blockOn == 0 || blockOn == 8 || blockOn == 9 || blockOn == 50
				|| blockOn == 65)
			return;
		int Level = plugin.levels.get(player);
		if (player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS
				&& Level >= plugin.LCConfiguration.LeatherBoots) {
			Vector dir = player.getLocation().getDirection()
					.multiply(0.6 * plugin.LCConfiguration.runMultiplier);
			player.setVelocity(dir);
			return;
		}else
		if (player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS
				&& Level >= plugin.LCConfiguration.ChainBoots) {
			Vector dir = player.getLocation().getDirection()
					.multiply(0.7 * plugin.LCConfiguration.runMultiplier);
			player.setVelocity(dir);
			return;
		}else
		if (player.getInventory().getBoots().getType() == Material.IRON_BOOTS
				&& Level >= plugin.LCConfiguration.IronBoots) {
			Vector dir = player.getLocation().getDirection()
					.multiply(0.8 * plugin.LCConfiguration.runMultiplier);
			player.setVelocity(dir);
			return;
		}else
		if (player.getInventory().getBoots().getType() == Material.GOLD_BOOTS
				&& Level >= plugin.LCConfiguration.GoldBoots) {
			Vector dir = player.getLocation().getDirection()
					.multiply(0.9 * plugin.LCConfiguration.runMultiplier);
			player.setVelocity(dir);
			return;
		}else
		if (player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS
				&& Level >= plugin.LCConfiguration.DiamondBoots) {
			Vector dir = player.getLocation().getDirection()
					.multiply(1 * plugin.LCConfiguration.runMultiplier);
			player.setVelocity(dir);
			return;
		}
	}
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p1 = e.getPlayer();

		for(Player p:plugin.levels.keySet()){
			if(p==p1){
				plugin.levels.remove(p1);
				break;
			}
			
		}
		
plugin.levels.put(p1, LevelFunctions.getLevel(p1, plugin.thisPlug));
		
		return;
	}
}
