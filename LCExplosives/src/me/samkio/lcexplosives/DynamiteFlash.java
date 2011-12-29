package me.samkio.lcexplosives;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class DynamiteFlash implements Runnable {
	private LCExplosives plugin;
	private Block block;

	public DynamiteFlash(LCExplosives plugin, Block block) {
		this.plugin = plugin;
		this.block = block;
	}

	@Override
	public void run() {
		Material m = block.getType();
		if (m != Material.REDSTONE_TORCH_OFF && m != Material.REDSTONE_TORCH_ON) {
			plugin.getServer().getScheduler().cancelTask(plugin.flashing.get(block));
			return;
		}
		if (m == Material.REDSTONE_TORCH_OFF)
			block.setType(Material.REDSTONE_TORCH_ON);
		if (m == Material.REDSTONE_TORCH_ON)
			block.setType(Material.REDSTONE_TORCH_OFF);
	}

}
