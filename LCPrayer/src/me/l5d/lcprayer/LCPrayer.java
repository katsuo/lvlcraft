//Declare the package name
package me.l5d.lcprayer;
//Add all the necessary imports.
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//Start the class.
public class LCPrayer extends JavaPlugin {
	//Assign a logger with the value 'logger'
	public final Logger logger = Logger.getLogger("Minecraft");
	//Create a Block Listener.
	private final LCEntityListener entityListener = new LCEntityListener(this);
	private final LCPlayerListener playerListener = new LCPlayerListener(this);
	//Create a configuration.
	final LCConfiguration LCConfiguration = new LCConfiguration(this);
	//Create a Configuration File (String).
	public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Prayer.cfg";
    //Create a Configuration File(File).
	public File ConfigurationFile = new File(ConfigurationFileString);
	//Create a Plugin variable 'thisPlug'
	public Plugin thisPlug;
	
	public PlayerCacheManager playerCacheManager = null ;

	
	//onDisable. Called when the plugin is disabled.
	@Override
	public void onDisable() {
		//Log that the plugin has been disabled.
		logger.log(Level.INFO, "[LC] Level Prayer Unloaded");
		playerCacheManager.onDisable();
	}

	//onEnable. Called when the plugin is enabled.
	@Override
	public void onEnable() {
		//Create a new dirctory structure if not exsits.
		new File("plugins/LevelCraftCore/Configs/").mkdirs();
		try {
			//Create a new configuration file.
			ConfigurationFile.createNewFile();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "[LC] " + e);
		}
		//Load this configuration file.
		LCConfiguration.loadConfig();
		// Set Reference Keys. 1-4 good.
		String[] Args = { "P", "Prayer" };
		getConfiguration().setProperty("ReferenceKeys", Args);
        //Set data to be shown on /lvl unlocks <ref>
		String[] Unlocks = {LCConfiguration.unlocksHeal.replaceAll("VALUE", ""+LCConfiguration.HealByLevel*2.0),
							LCConfiguration.unlocksHealer.replaceAll("VALUE", ""+LCConfiguration.HealByLevel*2.0),
							LCConfiguration.unlocksProtect.replaceAll("VALUE", ""+LCConfiguration.timeProtectByLevel),
							LCConfiguration.unlocksProtecter.replaceAll("VALUE", ""+LCConfiguration.timeProtectByLevel),
							LCConfiguration.unlocksBoostC.replaceAll("VALUE", ""+LCConfiguration.timeBoostCombatByLevel),
							LCConfiguration.unlocksBoostR.replaceAll("VALUE", ""+LCConfiguration.timeBoostRangeByLevel),
							LCConfiguration.unlocksBoostD.replaceAll("VALUE", ""+LCConfiguration.timeBoostDefenseByLevel),
							LCConfiguration.unlocksZoneProtecter.replaceAll("VALUE", ""+LCConfiguration.timeZoneProtectByLevel)};
		
		//help
		String[] Help = {LCConfiguration.help};
		
		//Set data in parallel with Unlocks. (This denotes wethere the user has the unlock or not.)
		int[] UnlocksLevel = { 	LCConfiguration.HealNeedLevel,
								LCConfiguration.HealerNeedLevel,
								LCConfiguration.ProtectNeedLevel,
								LCConfiguration.ProtecterNeedLevel,
								LCConfiguration.BoostNeedLevel,
								LCConfiguration.BoostNeedLevel,
								LCConfiguration.BoostNeedLevel,
								LCConfiguration.ZoneProtecterNeedLevel,};
		
		//Sets the data for /lvl exp <ref>
		String[] Exp = {"Exp per bone " + LCConfiguration.ExpByBone,
						"Exp per heal " + LCConfiguration.ExpByHeal,
						"Exp per protect " + LCConfiguration.ExpByProtect,
						"Exp per boost " + LCConfiguration.ExpByBoost,
						"Exp per protect mass " + LCConfiguration.ExpByZoneProtect};
		
		
		//Set the LevelExpPer to the Exp Array.
		getConfiguration().setProperty("LevelExpPer", Exp);
		//Set the LevelUnlocksLevel to the UnlocksLevel int array.
		getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
		//Set the LevelUnlocks to the Unlocks string array.
		getConfiguration().setProperty("LevelUnlocks", Unlocks);
		// Set Level Name.
		getConfiguration().setProperty("LevelName", "Prayer");
		// Set Reference Index (1-2 Characters).
		getConfiguration().setProperty("ReferenceIndex", "P");
		// Set Author :3.
		getConfiguration().setProperty("Author", "L5D");
		// Set help 
		getConfiguration().setProperty("LevelHelp",Help );
		// Get the plugin that this one is.
		this.thisPlug = this.getServer().getPluginManager().getPlugin("LCPrayer");
		//Get the plugin that LevelCraftCore is.
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin("LevelCraftCore");
		//If the plugin for LevelCraftCore is null then disable the plugin. If not register the events and state that it is loaded. 
		if (LevelCraftCore == null) {
			logger.log(Level.SEVERE,
					"[LC] Could not fine LevelCraftCore. Disabling.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			registerEvents();
			logger.log(Level.INFO, "[LC] Level Prayer Loaded");

			if(playerCacheManager == null)
				playerCacheManager = new PlayerCacheManager(this);
			playerCacheManager.onEnable();
		}
	}

	private void registerEvents() {
		//Register the events necessary 
		PluginManager pm = getServer().getPluginManager();
		//event.
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener,Event.Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener,Event.Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_ITEM_HELD, playerListener,Event.Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.High, this);

	}
}
