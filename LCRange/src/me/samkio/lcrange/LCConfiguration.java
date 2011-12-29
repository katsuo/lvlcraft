package me.samkio.lcrange;

import java.io.IOException;
import java.util.logging.Level;

import me.samkio.levelcraftcore.util.Properties;

public class LCConfiguration {

	public LCRange plugin;
	int Range0p5;
	int Range1p0;
	int Range1p5;
	int Range2p0;
	int Range2p5;
	int Range3p0;
	boolean EnableIce;
	boolean EnableTrice;
	boolean EnableFire;
	boolean EnableLight;
	boolean EnableTorch;
	boolean EnableExplosion;
	boolean EnableWater;

	double PerDamageExp;

	int IceLevel;
	int TriceLevel;
	int FireLevel;
	int LightLevel;
	int TorchLevel;
	int ExplosionLevel;
	int WaterLevel;

	double ExpPerIce;
	double ExpPerTrice;
	double ExpPerFire;
	double ExpPerLight;
	double ExpPerTorch;
	double ExpPerExplosion;
	double ExpPerWater;
	boolean EnableElemental;
	int ExpRad;

	public LCConfiguration(LCRange lcRange) {
		plugin = lcRange;
	}

	public void loadConfig() {
		Properties properties = new Properties(plugin.ConfigurationFileString);
		try {
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE, "[LC] " + e);
		}
		this.Range0p5 = properties.getInteger("Range-0.5-Level", 0);
		this.Range1p0 = properties.getInteger("Range-1.0-Level", 5);
		this.Range1p5 = properties.getInteger("Range-1.5-Level", 10);
		this.Range2p0 = properties.getInteger("Range-2.0-Level", 20);
		this.Range2p5 = properties.getInteger("Range-2.5-Level", 30);
		this.Range3p0 = properties.getInteger("Range-3.0-Level", 50);
		this.PerDamageExp = properties.getDouble("ExpPerDamage", 5);
		this.EnableElemental = properties.getBoolean("EnableElementalArrows",
				true);
		this.EnableFire = properties.getBoolean("EnableFireArrow", true);
		this.EnableTrice = properties.getBoolean("EnableTriceArrow", true);
		this.EnableLight = properties.getBoolean("EnableLightningArrow", true);
		this.EnableTorch = properties.getBoolean("EnableTorchArrow", true);
		this.EnableExplosion = properties.getBoolean("EnableExplosionArrow",
				true);
		this.EnableWater = properties.getBoolean("EnableWaterArrow", true);
		this.ExpRad = properties.getInteger("ExplosionRadius", 3);
		this.IceLevel = properties.getInteger("LevelForIceArrows", 10);
		this.TriceLevel = properties.getInteger("LevelForTriceArrows", 5);
		this.FireLevel = properties.getInteger("LevelForFireArrows", 40);
		this.TorchLevel = properties.getInteger("LevelForTorchArrows", 0);
		this.ExplosionLevel = properties.getInteger("LevelForExplosiveArrows",
				50);
		this.ExpPerWater = properties.getInteger("LevelForWaterArrows", 30);
		this.ExpPerLight = properties.getInteger("LevelForLightningArrows", 70);
	}
}
