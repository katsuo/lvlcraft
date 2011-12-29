package me.samkio.lcforgery;



import java.util.ArrayList;
import java.util.HashMap;

import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class LCPlayerListener extends PlayerListener {
	public LCForgery plugin;
	public  ArrayList<Integer> tasks = new ArrayList<Integer>();
	public  HashMap<Integer, net.minecraft.server.ItemStack[]> previousResult = new HashMap<Integer, net.minecraft.server.ItemStack[]>();
	public  HashMap<Integer, net.minecraft.server.ItemStack> previousResultCraft = new HashMap<Integer, net.minecraft.server.ItemStack>();

	public LCPlayerListener(LCForgery instance) {
		plugin = instance;
	}
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction()!=Action.RIGHT_CLICK_BLOCK) return;
		Player p = e.getPlayer();
		if(!Whitelist.hasLevel(p, plugin.thisPlug)) return;
		if(!Whitelist.worldCheck(p.getWorld())) return;
		if (e.getClickedBlock().getTypeId() == 58) {
				// start task
				BenchWorkThread task = new BenchWorkThread(e.getPlayer(), plugin);
				int id = plugin
				.getServer()
				.getScheduler()
				.scheduleSyncRepeatingTask(plugin, task, 0,
				2);
				tasks.add(id);
				task.addID(id);}
		else
		if ((e.getClickedBlock().getTypeId() == 61 || e.getClickedBlock()
				.getTypeId() == 62)) {
			FurnaceWorkThread task = new FurnaceWorkThread(e.getPlayer(),
					plugin,LevelFunctions.getLevel(e.getPlayer(), plugin.thisPlug));
			int id = plugin.getServer().getScheduler()
					.scheduleSyncRepeatingTask(plugin, task, 0, 2);
			tasks.add(Integer.valueOf(id));
			task.addID(id);
		}
	

	}
}
