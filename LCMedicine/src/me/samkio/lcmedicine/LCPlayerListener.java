package me.samkio.lcmedicine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class LCPlayerListener extends PlayerListener{
	public LCMedicine plugin;
	private static HashSet<Byte> transparentBlockList = null;
	public  LCPlayerListener(LCMedicine instance) {
		plugin = instance;
	}
	public void onPlayerInteract(PlayerInteractEvent event){
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
				if(event.getPlayer().getItemInHand().getTypeId()!=plugin.LCConfiguration.antidote) return;
				if(best.isInsideVehicle()) plugin.HealingProcess.heal(event.getPlayer(),best);
				event.setCancelled(true);
			}}
	}
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
