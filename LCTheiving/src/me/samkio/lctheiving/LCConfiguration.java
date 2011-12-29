package me.samkio.lctheiving;

import java.io.IOException;
import java.util.logging.Level;

import me.samkio.levelcraftcore.util.Properties;
public class LCConfiguration {
public LCTheiving plugin;
int Range0p5;
int Range1p0;
int Range1p5;
int Range2p0;
int Range2p5;
int Range3p0;
double PerDamageExp;
	public LCConfiguration(LCTheiving lcRange) {
		plugin = lcRange;
	}
public void loadConfig(){
	Properties properties = new Properties(plugin.ConfigurationFileString);
	try{
		properties.load();
	}catch (IOException e){
		plugin.logger.log(Level.SEVERE,"[LC] "+e);
	}
	this.Range0p5 = properties.getInteger("Range-0.5-Level",0);
	this.Range1p0 = properties.getInteger("Range-1.0-Level",5);
	this.Range1p5 = properties.getInteger("Range-1.5-Level",10);
	this.Range2p0 = properties.getInteger("Range-2.0-Level",20);
	this.Range2p5 = properties.getInteger("Range-2.5-Level",30);
	this.Range3p0 = properties.getInteger("Range-3.0-Level",50);
	this.PerDamageExp = properties.getDouble("ExpPerDamage",5);
	
}
}
