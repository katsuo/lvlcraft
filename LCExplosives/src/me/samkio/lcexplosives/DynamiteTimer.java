package me.samkio.lcexplosives;

import me.samkio.levelcraftcore.LevelFunctions;
import net.minecraft.server.EntityTNTPrimed;
import net.minecraft.server.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class DynamiteTimer implements Runnable{
	private LCExplosives plugin;
	private Location loc;
	private World world;
	private int i;
	private Player player;
	private Block block;
	public DynamiteTimer(LCExplosives plugin, Location loc, World world, int i,Player player,Block block) {
		this.plugin = plugin;
		this.loc = loc;
		this.world = world;
		this.i = i;
		this.player = player;
		this.block = block;
	}
	@Override
	public void run() {
		if(block.getType()!=Material.REDSTONE_TORCH_OFF && block.getType()!=Material.REDSTONE_TORCH_ON ) {
			plugin.getServer().getScheduler().cancelTask(plugin.flashing.get(block));
			plugin.flashing.remove(block);
			return;
		}
		block.setType(Material.AIR);
		EntityTNTPrimed tnt = new EntityTNTPrimed((net.minecraft.server.World) world, loc.getX(), loc.getY(), loc.getZ());
		world.a(tnt, loc.getX(), loc.getY(), loc.getZ(), i);
		LevelFunctions.addExp(player, plugin.thisPlug, plugin.LCConfiguration.expPerDynamite);

	}

}
