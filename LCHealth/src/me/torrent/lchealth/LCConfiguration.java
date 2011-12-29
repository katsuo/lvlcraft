package me.torrent.lchealth;

import java.io.IOException;
import java.util.logging.Level;
import me.samkio.levelcraftcore.util.Properties;

public class LCConfiguration
{
  public LCHealth plugin;
  public int WoodSword;
  public int StoneSword;
  public int IronSword;
  public int GoldSword;
  public int DiamondSword;
  public double ExpPerDamage;
  public double HealthLevel;
  public double dMultiplier;

  public LCConfiguration(LCHealth instance)
  {
    this.plugin = instance;
  }

  public void loadConfig()
  {
    Properties properties = new Properties(this.plugin.ConfigurationFileString);
    try
    {
      properties.load();
    } catch (IOException e) {
      this.plugin.logger.log(Level.SEVERE, "[LC] " + e);
    }

    this.ExpPerDamage = properties.getDouble("ExpPerHealth", 5.0D);

    this.HealthLevel = properties.getInteger("HealthLevel", 0);
    this.dMultiplier = properties.getDouble("Damage-Modifier", 1.0D);
  }
}