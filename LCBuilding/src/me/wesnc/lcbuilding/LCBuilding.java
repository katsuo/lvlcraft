package me.wesnc.lcbuilding;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LCBuilding extends JavaPlugin
{
  public final Logger logger = Logger.getLogger("Minecraft");

  private final LCBlockListener blockListener = new LCBlockListener(this);

  final LCConfiguration LCConfiguration = new LCConfiguration(this);
  public String CFGFileString = "plugins/LevelCraftCore/Configs/Building.cfg";
  public File CFGFile = new File("plugins/LevelCraftCore/Configs/Building.cfg");
  public Plugin thisPlug;

  public void onDisable()
  {
    this.logger.log(Level.INFO, "[LC] Level Building unloaded");
  }

  public void onEnable()
  {
    new File("plugins/LevelCraftCore/Configs").mkdirs();
    try
    {
      this.CFGFile.createNewFile();
    } catch (IOException localIOException) {
    }
    this.LCConfiguration.loadConfig();

    String[] Args = { "Bu", "Building", "Build" };
    getConfiguration().setProperty("ReferenceKeys", Args);

    String[] Unlocks = { 
      "Tier 0 =" + this.LCConfiguration.LEVELNEEDEDFOR_TIER0, 
      "Tier 1 =" + this.LCConfiguration.LEVELNEEDEDFOR_TIER1, 
      "Tier 2 =" + this.LCConfiguration.LEVELNEEDEDFOR_TIER2, 
      "Tier 2.5 =" + this.LCConfiguration.LEVELNEEDEDFOR_TIER2point5, 
      "Tier 3 =" + this.LCConfiguration.LEVELNEEDEDFOR_TIER3, 
      "Tier 4 =" + this.LCConfiguration.LEVELNEEDEDFOR_TIER4, 
      "Tier 5 =" + this.LCConfiguration.LEVELNEEDEDFOR_TIER5 };
    int[] UnlocksLevel = { this.LCConfiguration.LEVELNEEDEDFOR_TIER0, 
    		this.LCConfiguration.LEVELNEEDEDFOR_TIER1, 
    		this.LCConfiguration.LEVELNEEDEDFOR_TIER2, 
    		this.LCConfiguration.LEVELNEEDEDFOR_TIER2point5, 
    		this.LCConfiguration.LEVELNEEDEDFOR_TIER3, 
    		this.LCConfiguration.LEVELNEEDEDFOR_TIER4, 
    		this.LCConfiguration.LEVELNEEDEDFOR_TIER5 };
    String[] Exp = { 
      "Exp Per Placed Dirt (Nongrassed) " + this.LCConfiguration.PLACED_DIRT_NONGRASSED, 
      "Exp Per Placed Dirt (Grassed) " + this.LCConfiguration.PLACED_DIRT_GRASSED, 
      "Exp Per Placed Stone " + this.LCConfiguration.PLACED_STONE };

    getConfiguration().setProperty("LevelExpPer", Exp);
    getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
    getConfiguration().setProperty("LevelUnlocks", Unlocks);
    getConfiguration().setProperty("LevelName", "Building");
    getConfiguration().setProperty("ReferenceIndex", "Bu");
    getConfiguration().setProperty("Author", "Wesnc");

    this.thisPlug = getServer().getPluginManager().getPlugin("LCBuilding");
    Plugin LevelCraftCore = getServer().getPluginManager().getPlugin("LevelCraftCore");

    if (LevelCraftCore == null)
    {
      this.logger.log(Level.SEVERE, "[LC] Could not find LLC, disabling");
      getServer().getPluginManager().disablePlugin(this);
    } else {
      this.logger.log(Level.INFO, "[LC] Level Building Loaded. Private Release");
      registerEvents();
    }
  }

  public void registerEvents()
  {
    PluginManager pm = getServer().getPluginManager();

    pm.registerEvent(Event.Type.BLOCK_PLACE, this.blockListener, Event.Priority.Highest, this);
  }
}