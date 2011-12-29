package me.samkio.lcexplosives;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LCExplosives extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	private final LCBlockListener blockListener = new LCBlockListener(this);
	private final LCEntityListener entityListener = new LCEntityListener(this);
	private final LCPlayerListener playerListener = new LCPlayerListener(this);
	public final Grenade Grenade = new Grenade(this);
	final LCConfiguration LCConfiguration = new LCConfiguration(this);
	public String ConfigurationFileString =  "plugins/LevelCraftCore/Configs/Explosives.cfg";
	public  File ConfigurationFile = new File("plugins/LevelCraftCore/Configs/Explosives.cfg");
	public HashMap<Location,Player> primers = new HashMap<Location,Player>();
	public HashMap<Block,Integer> flashing = new HashMap<Block,Integer>();
	public HashMap<Player,Integer> TNTRange = new HashMap<Player,Integer>();
	public HashMap<Player,Integer> DynamiteRange = new HashMap<Player,Integer>();
	public HashMap<Player,Integer> EnableGrenade = new HashMap<Player,Integer>();
	//public HashMap<Location,Player> dynamite = new HashMap<Location,Player>();
    public Plugin thisPlug;
	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		new File("plugins/LevelCraftCore/Configs/").mkdirs();
		try {
			ConfigurationFile.createNewFile();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "[LC] "+e);
		}
		LCConfiguration.loadConfig();
		// Set Reference Keys.
		String[] Args = { "Ex", "Explode", "Tnt" };
		getConfiguration().setProperty("ReferenceKeys", Args);
		// Set Unlocks Data
		String[] Unlocks = { "TNTLevel "+LCConfiguration.tntLevel,"DynamiteLvl "+LCConfiguration.dynamiteLevel ,"GrenadeLevel "+LCConfiguration.grenadeLevel };
		getConfiguration().setProperty("LevelUnlocks", Unlocks);
		String[] Exp = { "Exp Per TNT "+LCConfiguration.expPerExplosion,"Exp Per Dynamite "+LCConfiguration.expPerDynamite,"Exp Per Grenade "+LCConfiguration.expPerGrenade};
		int[] UnlocksLevel = { LCConfiguration.tntLevel, LCConfiguration.grenadeLevel,
				LCConfiguration.grenadeLevel };
		
		getConfiguration().setProperty("LevelExpPer", Exp);
		getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
		// Set Level Name.
		getConfiguration().setProperty("LevelName", "Explosives");
		// Set Reference Index (1-2 Characters).
		getConfiguration().setProperty("ReferenceIndex", "Ex");
		// Set Author :3.
		getConfiguration().setProperty("Author", "Samkio");
		// Log that it has loaded.
        this.thisPlug = this.getServer().getPluginManager().getPlugin("LCExplosives");
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin(
				"LevelCraftCore");
		if (LevelCraftCore == null) {
			logger.log(Level.SEVERE,
					"[LC] Could not fine LevelCraftCore. Disabling.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			registerEvents();
			logger.log(Level.INFO, "[LC] Level Explosives "+getDescription().getVersion()+" Loaded");
			
		}
	}

	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener,
				Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener,
				Event.Priority.Low, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener,
				Event.Priority.Low, this);
		pm.registerEvent(Event.Type.PLAYER_ANIMATION, playerListener,
				Event.Priority.Low, this);
		pm.registerEvent(Event.Type.PLAYER_EGG_THROW, playerListener,
				Event.Priority.Low, this);
		//pm.registerEvent(Event.Type.BLOCK_PHYSICS, blockListener,Event.Priority.Low, this);
		pm.registerEvent(Event.Type.EXPLOSION_PRIME, entityListener,
				Event.Priority.Low, this);
		pm.registerEvent(Event.Type.ENTITY_EXPLODE, entityListener,
				Event.Priority.Low, this);
		
	}
}
