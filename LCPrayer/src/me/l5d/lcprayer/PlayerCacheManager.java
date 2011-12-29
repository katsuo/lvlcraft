package me.l5d.lcprayer;

import java.util.HashMap;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerCacheManager extends TimerTask{
	
	HashMap<String,PlayerCache> playersCache;
	ConcurrentHashMap<String, PlayerCache> playersPersistantCache;
	
	LCPrayer plugin;

	Plugin combatPlugin = null;
	Plugin rangeplugin = null;
	Plugin defensePlugin = null;
	
	public int updateTask = -1;
	public static Integer outdateCache = 120; //Temps ou le cache du joueur reste en mémoire.
	
	public PlayerCacheManager(LCPrayer plugin){
		playersCache = new HashMap<String,PlayerCache>();
		playersPersistantCache = new ConcurrentHashMap<String,PlayerCache>();
		this.plugin = plugin;
		enableTimer();
	}
	
	public void onEnable(){
		enableTimer();
		combatPlugin = plugin.getServer().getPluginManager().getPlugin("LCCombat");
		rangeplugin = plugin.getServer().getPluginManager().getPlugin("LCRange");
		defensePlugin = plugin.getServer().getPluginManager().getPlugin("LCDefence");
		if (!plugin.getServer().getPluginManager().isPluginEnabled(combatPlugin)) {
			plugin.getServer().getPluginManager().enablePlugin(combatPlugin);
		}
		if (!plugin.getServer().getPluginManager().isPluginEnabled(rangeplugin)) {
			plugin.getServer().getPluginManager().enablePlugin(rangeplugin);
		}
		if (!plugin.getServer().getPluginManager().isPluginEnabled(defensePlugin)) {
			plugin.getServer().getPluginManager().enablePlugin(defensePlugin);
		}
	}
	
	public void onDisable(){
		for(String p : playersPersistantCache.keySet()){
			playersPersistantCache.get(p).stop();
		}
		for(String p : playersPersistantCache.keySet()){
			playersPersistantCache.get(p).stop();
		}	
		clear();
		disableTimer();
		combatPlugin = null;
		rangeplugin = null;
		defensePlugin = null;
	}
	
	public void clear(){
		playersCache.clear();
		playersPersistantCache.clear();
	}	
	
	public void toPersistant(String player){

			if(playersCache.containsKey(player)){
				PlayerCache pc = playersCache.get(player);
				playersCache.remove(pc);
				playersPersistantCache.put(player, pc);
				pc.setTimer(0);
			}
		
	}

	public void fromPersistant(String player){

			if(playersPersistantCache.containsKey(player)){
				PlayerCache pc = playersPersistantCache.get(player);
				playersPersistantCache.remove(pc);
				playersCache.put(player, pc);
				pc.setTimer(0);
			}
		
	}
	
	public PlayerCache getPlayerCache(Player player) throws NullPointerException{
		PlayerCache playerCache = playersCache.get(player.getName());
		
		//Création du playerCache
		if( playerCache == null ){
			playerCache = new PlayerCache(player,plugin,combatPlugin,rangeplugin,defensePlugin);
			playersCache.put(player.getName(), playerCache);
		}
		return playerCache;
	}

	public void enableTimer(){
		updateTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, 20);	//20 ticks = 1 seconde ?
		if (updateTask == -1)
			plugin.logger.log(Level.INFO, "[LC] Echec de l'activation du timer de cache persistant.");
	}

	public void disableTimer(){
		if (updateTask != -1)
			plugin.getServer().getScheduler().cancelTask(updateTask);
	}	
	
	@Override
	public void run(){
		for(String p : playersPersistantCache.keySet()){
			
			 playersPersistantCache.get(p).update(1);
			 
			 if( playersPersistantCache.get(p).getTimer()>outdateCache )
				 playersPersistantCache.remove(p);
		}
		 
		for(PlayerCache pc:playersCache.values()){
			pc.update(1);
		}
		for(PlayerCache pc:playersPersistantCache.values()){
			pc.update(1);
		}
	}
	
}
