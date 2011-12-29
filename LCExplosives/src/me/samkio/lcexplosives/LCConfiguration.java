package me.samkio.lcexplosives;

import java.io.IOException;
import java.util.logging.Level;

import me.samkio.levelcraftcore.util.Properties;
public class LCConfiguration {
public LCExplosives plugin;
public int tntLevel;
public int dynamiteLevel;
public int fuseLevel;
public int grenadeLevel;
public double expPerExplosion;
public double expPerDynamite;
public double expPerGrenade;
public int explosionMultiplier;
public int TntRadiusCap;
public int DefaultRadius;
public boolean BlockTnt;
public boolean BlockDynamite;
public boolean BlockGrenade;
public int DynamiteRadiusCap;
public int GrenadeRadius;
	public LCConfiguration(LCExplosives lcRange) {
		plugin = lcRange;
	}
public void loadConfig(){
	Properties properties = new Properties(plugin.ConfigurationFileString);
	try{
		properties.load();
	}catch (IOException e){
		plugin.logger.log(Level.SEVERE,"[LC] "+e);
	}
this.tntLevel = properties.getInteger("TNTLevel",5);
this.dynamiteLevel = properties.getInteger("DynamiteLevel",0);
this.grenadeLevel = properties.getInteger("GrenadeLevel",10);
//this.fuseLevel = properties.getInteger("FuseLevel",10);
this.expPerExplosion = properties.getDouble("ExpPerTnt",50);
this.expPerDynamite = properties.getDouble("ExpPerDynamite",20);
this.expPerGrenade = properties.getDouble("ExpPerGrenade",70);;
this.explosionMultiplier = properties.getInteger("ExplosionMultiplier",1);
this.TntRadiusCap = properties.getInteger("MaximumTntRadius",10);
this.DynamiteRadiusCap = properties.getInteger("MaximumDynamiteRadius",3);
this.DefaultRadius = properties.getInteger("DefaultRadius",1); 
this.BlockTnt = properties.getBoolean("BlockTnt",false); 
this.BlockDynamite = properties.getBoolean("BlockDynamite",false);
this.BlockDynamite = properties.getBoolean("BlockGrenade",false);
this.GrenadeRadius = properties.getInteger("GrenadeRadius",3);

}
}
