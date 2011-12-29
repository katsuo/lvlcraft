package me.samkio.lctheiving;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LCTheiving extends JavaPlugin{
    public final Logger logger = Logger.getLogger("Minecraft");
    final LCEntityListener  entityListener = new LCEntityListener(this);
    final LCPlayerListener  playerListener = new LCPlayerListener(this);
    public final TheivingProcess  TheivingProcess = new TheivingProcess(this);
    public final LCConfiguration LCConfiguration = new LCConfiguration(this);
    public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Theiving.cfg";
    public File ConfigurationFile = new File(ConfigurationFileString);
    public Plugin thisPlug;
	@Override
	public void onDisable() {
		logger.log(Level.INFO, "[LC] Level Theiving Unloaded");
	}

	@Override
	public void onEnable() {
		new File("plugins/LevelCraftCore/Configs/").mkdirs();
		try{
			ConfigurationFile.createNewFile();
		}catch (IOException e){
			logger.log(Level.SEVERE, "[LC] "+e);
		}
		LCConfiguration.loadConfig();
		String[] Unlocks = {"Range 0.5 Damage ="+LCConfiguration.Range0p5,"Range 1.0 Damage ="+LCConfiguration.Range1p0,"Range 1.5 Damage ="+LCConfiguration.Range1p5,"Range 2.0 Damage ="+LCConfiguration.Range2p0,"Range 2.5 Damage ="+LCConfiguration.Range2p5,"Range 3.0 Damage ="+LCConfiguration.Range3p0};
		int[] UnlocksLevel = {LCConfiguration.Range0p5,LCConfiguration.Range1p0,LCConfiguration.Range1p5,LCConfiguration.Range2p0,LCConfiguration.Range2p5,LCConfiguration.Range3p0};
		String[] Exp = { "Exp Per Damage "+LCConfiguration.PerDamageExp};
		getConfiguration().setProperty("LevelExpPer", Exp);
		getConfiguration().setProperty("LevelUnlocks",Unlocks);
		getConfiguration().setProperty("LevelUnlocksLevel",UnlocksLevel);
		getConfiguration().setProperty("LevelName","Theiving");
		getConfiguration().setProperty("ReferenceIndex","T");
		getConfiguration().setProperty("Author", "Samkio");
		String[] Args = {"T","Theiving","Theive"};
		getConfiguration().setProperty("ReferenceKeys",Args);
		
		this.thisPlug = this.getServer().getPluginManager().getPlugin("LCTheiving");
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin("LevelCraftCore");
		if(LevelCraftCore == null){
			logger.log(Level.SEVERE,"[LC] Could not find LevelCraftCore. Disabling");
			this.getServer().getPluginManager().disablePlugin(this);
		}else{
			registerEvents();
			logger.log(Level.INFO,"[LC] Level Theiving Loaded.");
		}
		
	}

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Highest,this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Highest,this);
		pm.registerEvent(Event.Type.PLAYER_ANIMATION, playerListener, Event.Priority.Monitor, this);
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Monitor, this);
		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Event.Priority.Monitor, this);


		
	}



}
