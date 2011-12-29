package me.samkio.lctheiving;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public class LCPlayerListener extends PlayerListener {
	public LCTheiving plugin;
	private static HashSet<Byte> transparentBlockList = null;

	public LCPlayerListener(LCTheiving lcTheiving) {
		plugin = lcTheiving;
	}

	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		closeDialogs(event.getPlayer());
	}

	@Override
	public void onPlayerAnimation(PlayerAnimationEvent event) {
		closeDialogs(event.getPlayer());
	}

	@Override
	public void onPlayerMove(PlayerMoveEvent event) {
		closeDialogs(event.getPlayer());
	}

	public void onPlayerInteract(PlayerInteractEvent event) {
		closeDialogs(event.getPlayer());
		if (event.getAction() == Action.RIGHT_CLICK_AIR
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			List<Block> list = event.getPlayer().getLineOfSight(
					getTransparentBlockIdList(), 6);
			ArrayList<Player> playerList = new ArrayList<Player>();
			for (Block block : list) {
				for (Player p : block.getWorld().getPlayers()) {
					if (p.getEntityId() != event.getPlayer().getEntityId()) {
						if (p.getLocation()
								.toVector()
								.distanceSquared(block.getLocation().toVector()) < 1) {
							playerList.add(p);

						}
					}
				}
			}
			Player best = null;
			double distance = Double.MAX_VALUE;
			for (Player p : playerList) {
				if (p.getLocation()
						.toVector()
						.distanceSquared(
								event.getPlayer().getLocation().toVector()) < distance) {
					distance = p
							.getLocation()
							.toVector()
							.distanceSquared(
									event.getPlayer().getLocation().toVector());
					best = p;
				}
			}
			if (best != null) {
				TheivingProcess.theive(event.getPlayer(), best);
			}
		}
	}

	@SuppressWarnings("static-access")
	public void closeDialogs(Player player) {
		if (plugin.TheivingProcess.playersBeingRobbed.containsKey(player
				.getName())) {
			Player other = plugin.getServer().getPlayer(
					plugin.TheivingProcess.playersBeingRobbed.get(player
							.getName()));
			try {
				((CraftPlayer) other).getHandle().u();
				((CraftPlayer) player).getHandle().u();

				Runnable update = plugin.TheivingProcess.playersInInventoryDialogUpdater
						.get(player.getName());
				update.run();
				Runnable update2 = plugin.TheivingProcess.playersInInventoryDialogUpdater
				.get(other.getName());
		        update2.run();
				plugin.TheivingProcess.playersBeingRobbed.remove(player
						.getName());
				plugin.TheivingProcess.playersRobbing.remove(other.getName());
			} catch (Exception e) {
			}
		} else if (plugin.TheivingProcess.playersRobbing.containsKey(player
				.getName())) {
			Player other = plugin.getServer().getPlayer(
					plugin.TheivingProcess.playersRobbing.get(player
							.getName()));
			try {
				((CraftPlayer) other).getHandle().u();
				((CraftPlayer) player).getHandle().u();

				Runnable update = plugin.TheivingProcess.playersInInventoryDialogUpdater
						.get(other.getName());
				update.run();
				Runnable update2 = plugin.TheivingProcess.playersInInventoryDialogUpdater
				.get(player.getName());
		        update2.run();
				plugin.TheivingProcess.playersBeingRobbed.remove(other
						.getName());
				plugin.TheivingProcess.playersRobbing.remove(player.getName());
			} catch (Exception e) {
			}
		}else{
			for(String s:plugin.TheivingProcess.playersRobbing.keySet()){
				if(plugin.TheivingProcess.playersRobbing.get(s).equalsIgnoreCase(player.getName())){
					Player other = plugin.getServer().getPlayer(
							plugin.TheivingProcess.playersRobbing.get(s));
					try {
						((CraftPlayer) other).getHandle().u();
						((CraftPlayer) player).getHandle().u();

						Runnable update = plugin.TheivingProcess.playersInInventoryDialogUpdater
								.get(other.getName());
						update.run();
						Runnable update2 = plugin.TheivingProcess.playersInInventoryDialogUpdater
						.get(player.getName());
				        update2.run();
						plugin.TheivingProcess.playersBeingRobbed.remove(other
								.getName());
						plugin.TheivingProcess.playersRobbing.remove(player.getName());
					} catch (Exception e) {
					}
				}
			}
		}
	}

	/*
	 * @SuppressWarnings("static-access") void closeDialogs(Player player) {
	 * 
	 * if (plugin.TheivingProcess.playersBeingRobbed.containsKey(player
	 * .getName())) { Player other = plugin.getServer().getPlayer(
	 * plugin.TheivingProcess.playersBeingRobbed.get(player .getName())); try {
	 * 
	 * //((CraftPlayer) other).getHandle().activeContainer = new
	 * ContainerPlayer(); //((CraftPlayer) other).getHandle().defaultContainer =
	 * ((CraftPlayer) other).getHandle().activeContainer;
	 * 
	 * Runnable update =
	 * plugin.TheivingProcess.playersInInventoryDialogUpdater.get
	 * (other.getName()); update.run();
	 * plugin.TheivingProcess.playersInInventoryDialogUpdater
	 * .remove(player.getName());
	 * plugin.TheivingProcess.playersInInventoryDialogUpdater
	 * .remove(other.getName());
	 * plugin.TheivingProcess.playersBeingRobbed.remove(player .getName());
	 * plugin.TheivingProcess.playersRobbing.remove(other.getName());
	 * //((CraftPlayer) other).getHandle().u(); } catch (Exception e) { } }
	 * /*else if
	 * (plugin.TheivingProcess.playersRobbing.containsKey(player.getName())) {
	 * Player other = plugin.getServer()
	 * .getPlayer(plugin.TheivingProcess.playersRobbing .get(player.getName()));
	 * try {
	 * 
	 * 
	 * // ((CraftPlayer)other).getHandle().u(); //((CraftPlayer)
	 * player).getHandle().u();
	 * 
	 * Runnable update = plugin.TheivingProcess.playersInInventoryDialogUpdater
	 * .get(player.getName()); update.run();
	 * plugin.TheivingProcess.playersInInventoryDialogUpdater
	 * .remove(player.getName());
	 * plugin.TheivingProcess.playersInInventoryDialogUpdater
	 * .remove(other.getName());
	 * plugin.TheivingProcess.playersRobbing.remove(player.getName());
	 * plugin.TheivingProcess.playersBeingRobbed.remove(other.getName()); } //
	 * ((CraftPlayer) other).getHandle().u();}
	 * 
	 * catch (Exception e) { } }
	 * 
	 * 
	 * 
	 * }
	 */

	public static HashSet<Byte> getTransparentBlockIdList() {
		if (transparentBlockList == null) {
			HashSet<Byte> list = new HashSet<Byte>();
			for (Material m : Material.values()) {
				if (m.isBlock() && !isSolidMaterial(m)) {
					list.add((byte) m.getId());
				}
			}
			transparentBlockList = list;
			return list;
		}
		return transparentBlockList;
	}

	public static boolean isSolidMaterial(Material m) {
		return m == Material.STONE || m == Material.GRASS || m == Material.DIRT
				|| m == Material.COBBLESTONE || m == Material.WOOD
				|| m == Material.BEDROCK || m == Material.SAND
				|| m == Material.GRAVEL || m == Material.GOLD_ORE
				|| m == Material.IRON_ORE || m == Material.COAL_ORE
				|| m == Material.LOG || m == Material.LEAVES
				|| m == Material.SPONGE || m == Material.LAPIS_ORE
				|| m == Material.LAPIS_BLOCK || m == Material.DISPENSER
				|| m == Material.SANDSTONE || m == Material.NOTE_BLOCK
				|| m == Material.WOOL || m == Material.GOLD_BLOCK
				|| m == Material.IRON_BLOCK || m == Material.DOUBLE_STEP
				|| m == Material.STEP || m == Material.BRICK
				|| m == Material.TNT || m == Material.BOOKSHELF
				|| m == Material.MOSSY_COBBLESTONE || m == Material.OBSIDIAN
				|| m == Material.MOB_SPAWNER || m == Material.WOOD_STAIRS
				|| m == Material.CHEST || m == Material.DIAMOND_ORE
				|| m == Material.DIAMOND_BLOCK || m == Material.WORKBENCH
				|| m == Material.SOIL || m == Material.FURNACE
				|| m == Material.BURNING_FURNACE
				|| m == Material.COBBLESTONE_STAIRS
				|| m == Material.REDSTONE_ORE
				|| m == Material.GLOWING_REDSTONE_ORE || m == Material.ICE
				|| m == Material.SNOW_BLOCK || m == Material.CACTUS
				|| m == Material.CLAY || m == Material.JUKEBOX
				|| m == Material.FENCE || m == Material.PUMPKIN
				|| m == Material.NETHERRACK || m == Material.SOUL_SAND
				|| m == Material.GLOWSTONE || m == Material.JACK_O_LANTERN
				|| m == Material.CAKE_BLOCK

		;
	}
}
