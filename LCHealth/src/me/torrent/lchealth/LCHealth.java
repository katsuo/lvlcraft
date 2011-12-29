package me.torrent.lchealth;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LCHealth extends JavaPlugin
{
  public final Logger logger = Logger.getLogger("Minecraft");

  final LCEntityListener entListener = new LCEntityListener(this);

  final LCConfiguration LCConfiguration = new LCConfiguration(this);

  public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Health.cfg";

  public File ConfigurationFile = new File("plugins/LevelCraftCore/Configs/Health.cfg");
  public Plugin thisPlug;

  public void onDisable()
  {
    this.logger.log(Level.INFO, "[LC] Level Health Unloaded");
  }

  public void onEnable()
  {
    new File("plugins/LevelCraftCore/Configs/").mkdirs();
    try
    {
      this.ConfigurationFile.createNewFile();
    } catch (IOException e) {
      this.logger.log(Level.SEVERE, "[LC] " + e);
    }

    this.LCConfiguration.loadConfig();

    String[] Args = { "h", "Health", "heal" };
    getConfiguration().setProperty("ReferenceKeys", Args);

    String[] Unlocks = { "Wooden Sword = " + this.LCConfiguration.WoodSword, 
      "Stone Sword = " + this.LCConfiguration.StoneSword, 
      "Iron Sword = " + this.LCConfiguration.IronSword, 
      "Gold Sword = " + this.LCConfiguration.GoldSword, 
      "Diamond Sword = " + this.LCConfiguration.DiamondSword };

    int[] UnlocksLevel = { this.LCConfiguration.WoodSword, 
      this.LCConfiguration.StoneSword, this.LCConfiguration.IronSword, 
      this.LCConfiguration.GoldSword, this.LCConfiguration.DiamondSword };

    String[] Exp = { "Exp Per Damage " + this.LCConfiguration.ExpPerDamage };

    getConfiguration().setProperty("LevelExpPer", Exp);

    getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);

    getConfiguration().setProperty("LevelUnlocks", Unlocks);

    getConfiguration().setProperty("LevelName", "Health");

    getConfiguration().setProperty("ReferenceIndex", "H");

    getConfiguration().setProperty("Author", "Torrent");

    this.thisPlug = getServer().getPluginManager().getPlugin("LCHealth");

    Plugin LevelCraftCore = getServer().getPluginManager().getPlugin("LevelCraftCore");

    if (LevelCraftCore == null) {
      this.logger.log(Level.SEVERE, 
        "[LC] Could not fine LevelCraftCore. Disabling.");
      getServer().getPluginManager().disablePlugin(this);
    } else {
      registerEvents();
      this.logger.log(Level.INFO, "[LC] Level Health  Loaded");
    }
  }

  private void registerEvents()
  {
    PluginManager pm = getServer().getPluginManager();

    pm.registerEvent(Event.Type.ENTITY_DAMAGE, this.entListener, 
      Event.Priority.Highest, this);
  }
}