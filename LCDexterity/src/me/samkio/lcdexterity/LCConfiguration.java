package me.samkio.lcdexterity;

import java.io.IOException;
import java.util.logging.Level;

import me.samkio.levelcraftcore.util.Properties;
public class LCConfiguration {
public LCDexterity plugin;
public int LeatherBoots;
public int ChainBoots;
public int IronBoots;
public int GoldBoots;
public int DiamondBoots;

public double ExpPerJump;
public boolean AllowJump;
public boolean AllowRun;
public boolean AllowFD;
public double jumpMultiplier;
public double runMultiplier;
public double fdMultiplier;

	public LCConfiguration(LCDexterity lcRange) {
		plugin = lcRange;
	}
public void loadConfig(){
	Properties properties = new Properties(plugin.ConfigurationFileString);
	try{
		properties.load();
	}catch (IOException e){
		plugin.logger.log(Level.SEVERE,"[LC] "+e);
	}
	this.LeatherBoots = properties.getInteger("LeatherBootsLevel", 0);
	this.ChainBoots = properties.getInteger("ChainBootsLevel", 5);
	this.IronBoots = properties.getInteger("IronBootsLevel", 10);
	this.GoldBoots = properties.getInteger("GoldBoot-Level", 20);
	this.DiamondBoots = properties.getInteger("DiamondBootsLevel", 30);
	this.ExpPerJump = properties.getDouble("ExperiencePerJump", 0.5);
	this.AllowJump = properties.getBoolean("AllowJump",false);
	this.AllowRun = properties.getBoolean("AllowRun",true);
	this.AllowFD = properties.getBoolean("AllowReducedFD",true);
	this.jumpMultiplier = properties.getDouble("JumpModifier", 1); 
	this.runMultiplier = properties.getDouble("RunModifier", 1); 
	this.fdMultiplier = properties.getDouble("FallDamage-Modifier", 1); 
	
}
}
