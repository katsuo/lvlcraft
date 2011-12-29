package me.samkio.lcdexterity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LCDexterity extends JavaPlugin{
    public final Logger logger = Logger.getLogger("Minecraft");
    final LCEntityListener  entityListener = new LCEntityListener(this);
    final LCPlayerListener  playerListener = new LCPlayerListener(this);
    public final LCConfiguration LCConfiguration = new LCConfiguration(this);
    public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Dexterity.cfg";
    public File ConfigurationFile = new File(ConfigurationFileString);
    public Plugin thisPlug;
    public HashMap<Player,Integer> levels = new HashMap<Player,Integer>();
	@Override
	public void onDisable() {
		logger.log(Level.INFO, "[LC] Level Dexterity Unloaded");
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
		String[] Unlocks = {"LeatherBoots = "+LCConfiguration.LeatherBoots,"ChainmailBoots = "+LCConfiguration.ChainBoots,"IronBoots = "+LCConfiguration.IronBoots,"GoldBoots = "+LCConfiguration.GoldBoots,"DiamondBoots = "+LCConfiguration.DiamondBoots};
		int[] UnlocksLevel = {LCConfiguration.LeatherBoots,LCConfiguration.ChainBoots,LCConfiguration.IronBoots,LCConfiguration.GoldBoots,LCConfiguration.DiamondBoots};
		String[] Exp = { "Exp Per Jump "+LCConfiguration.ExpPerJump};
		getConfiguration().setProperty("LevelExpPer", Exp);
		getConfiguration().setProperty("LevelUnlocks",Unlocks);
		getConfiguration().setProperty("LevelUnlocksLevel",UnlocksLevel);
		getConfiguration().setProperty("LevelName","Dexterity");
		getConfiguration().setProperty("ReferenceIndex","Dx");
		getConfiguration().setProperty("Author", "Samkio");
		String[] Args = {"Dx","Dex","Dexterity"};
		getConfiguration().setProperty("ReferenceKeys",Args);
		
		this.thisPlug = this.getServer().getPluginManager().getPlugin("LCDexterity");
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin("LevelCraftCore");
		if(LevelCraftCore == null){
			logger.log(Level.SEVERE,"[LC] Could not find LevelCraftCore. Disabling");
			this.getServer().getPluginManager().disablePlugin(this);
		}else{
			registerEvents();
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new CheckThread(this), 0, 1200);
			logger.log(Level.INFO,"[LC] Level Dexterity Loaded.");
		}
		
	}

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Highest,this);
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Highest,this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal,this);
	}

}
