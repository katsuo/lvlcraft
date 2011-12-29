package me.samkio.lcdexterity;

import me.samkio.levelcraftcore.LevelFunctions;

import org.bukkit.entity.Player;

public class CheckThread implements Runnable{
	private LCDexterity plugin;
	
	public CheckThread(LCDexterity plugin) {
		this.plugin = plugin;

	}
	@Override
	public void run() {
		plugin.levels.clear();
		for(Player p:plugin.getServer().getOnlinePlayers()){
			plugin.levels.put(p, LevelFunctions.getLevel(p, plugin.thisPlug));
		}
	}

}
