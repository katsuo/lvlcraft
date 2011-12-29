package me.samkio.lcmedicine;


import java.io.IOException;
import java.util.logging.Level;
import me.samkio.levelcraftcore.util.Properties;
/* This class sets up the configuration for the variables.
 * It also sets up the configuration file with the variables.
 * Important to note it imports the properties functions from LevelCraftCore
 */

public class LCConfiguration {
	public LCMedicine plugin;
	//TOOLS
	public int AntidoteLevel;
	public int BoneLevel;
	public int HealLevel;
	public int DiseaseLevel;

	//EXP PER
	public double ExpPerAntidote;
	public double ExpPerBoneFix;
	public double ExpPerDisease;
	public double ExpPerHeal;

	public boolean EnablePoison;
	public boolean EnableBones;
	public boolean EnableDisease;
	
	public int bandageID;
	public int boneID;
	public int medicine;
	public int antidote;
	public int potion;
	public LCConfiguration(LCMedicine instance) {
		plugin = instance;
	}
    //loadConfig. Called onEnable in main class.
	public void loadConfig() {
        //Create new properties file with the file string.
		Properties properties = new Properties(plugin.ConfigurationFileString);
		try {
			//Try to load it. If not return an error.
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE,"[LC] "+e);
		}
		//Set variables for TOOLS
		this.DiseaseLevel = properties.getInteger("HealDiseaseLevel", 0);
		this.BoneLevel = properties.getInteger("HealBrokenBoneLevel", 5);
		this.AntidoteLevel = properties.getInteger("HealPoisonLevel", 10);
		this.HealLevel = properties.getInteger("HealGeneralLevel", 15);
		
		//Set variables for EXP PER
		this.ExpPerAntidote = properties.getDouble("ExpPerAntidote", 20);
		this.ExpPerBoneFix = properties.getDouble("ExpPerBoneFix", 20);
		this.ExpPerDisease = properties.getDouble("ExpPerDisease", 20);
		this.ExpPerHeal = properties.getDouble("ExpPerHeal", 30);
		//
		this.EnablePoison = properties.getBoolean("EnablePoision",true);
		this.EnableBones = properties.getBoolean("EnableBrokenBones",true);
		this.EnableDisease = properties.getBoolean("EnableDisease",true);
		this.bandageID = properties.getInteger("BandageID",338);
		this.boneID = properties.getInteger("BoneID",352);
		this.medicine = properties.getInteger("MedicineID",353);
		this.antidote = properties.getInteger("AntidoteID", 282);
		this.potion = properties.getInteger("PotionID",335);



	}
}
