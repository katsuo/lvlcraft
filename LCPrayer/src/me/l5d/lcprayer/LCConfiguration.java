package me.l5d.lcprayer;


import java.io.IOException;
import java.util.logging.Level;
import me.samkio.levelcraftcore.util.Properties;
/* This class sets up the configuration for the variables.
 * It also sets up the configuration file with the variables.
 * Important to note it imports the properties functions from LevelCraftCore
 */

public class LCConfiguration {

	public LCPrayer plugin;

	//help
	public String help;
	
	//Unlocks help
	public String unlocksHeal;
	public String unlocksHealer;
	public String unlocksProtect;
	public String unlocksProtecter;
	public String unlocksBoostC;
	public String unlocksBoostR;
	public String unlocksBoostD;
	public String unlocksZoneProtecter;
	
	//Message
	public String NoEnoughPP;
	public String InfoPP;
	public String AltarAlreadyUse;
	
	//ExpPerHeal
	public int itemPrayer;

	//PrayerPoint
	public int MaxPrayerPointPerLevel;
	public boolean SneakToBless;
	public int PrayerPointByAltarSize;
	public int AltarSizeMax;
	public int AltarBlock;
	public int timeToRechargeAltar;
	public boolean fullRechargeAltar;

	
	public double ZPLen = 0;
	
	//exp bonus
	public double ExpByBone;
	public double ExpByHeal;
	public double ExpByProtect;
	public double ExpByBoost;
	public double ExpByZoneProtect;
	
	//exp boost
	public int HealByLevel;
	public double percentBoostCombatByLevel;
	public double percentBoostRangeByLevel;
	public double percentBoostDefenseByLevel;
	
	//exp time
	public double timeProtectByLevel;
	public double timeBoostCombatByLevel;
	public double timeBoostRangeByLevel;
	public double timeBoostDefenseByLevel;
	public double timeZoneProtectByLevel;
	
	//Prayer cost
	public int HealCost;
	public int ProtectCost;
	public int HealerCost;
	public int ProtecterCost;
	public int BoostCost;
	public int ZoneProtecterCost;

	//Needed level
	public int HealNeedLevel;
	public int ProtectNeedLevel;
	public int HealerNeedLevel;
	public int ProtecterNeedLevel;
	public int BoostNeedLevel;
	public int ZoneProtecterNeedLevel;








	public LCConfiguration(LCPrayer instance) {
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
		
		//unlocks help
		this.unlocksHeal  = properties.getString("unlocksHeal", "Probability of heal VALUE heart by level");
		this.unlocksHealer = properties.getString("unlocksHealer", "Probability of heal other VALUE heart by level");
		this.unlocksProtect = properties.getString("unlocksProtect", "Probability of protect VALUE second by level");
		this.unlocksProtecter = properties.getString("unlocksProtecter", "Probability of protect other VALUE second by level");
		this.unlocksBoostC = properties.getString("unlocksBoostC", "Probability of boost VALUE percent of Combat skill");
		this.unlocksBoostR = properties.getString("unlocksBoostR", "Probability of boost VALUE percent of Range skill");
		this.unlocksBoostD = properties.getString("unlocksBoostD", "Probability of boost VALUE percent of Defense skill");
		this.unlocksZoneProtecter = properties.getString("unlocksZoneProtecter", "Probability of protect zone VALUE second by level");
		
		//help
		this.help = properties.getString("help","Use a clock for select and launch bless.");
		
		//Item for bless
		this.itemPrayer = properties.getInteger("itemPrayer", 347);

		//Set variables for ExpPerHeal
		this.ExpByBone = properties.getDouble("ExpByBone", 1);
		this.ExpByHeal = properties.getDouble("ExpByHeal", 2);
		this.ExpByProtect = properties.getDouble("ExpByProtect", 2);
		this.ExpByBoost = properties.getDouble("ExpByBoost", 3);
		this.ExpByZoneProtect = properties.getDouble("ExpByZoneProtect", 5);
		
		//Message
		this.NoEnoughPP = properties.getString("NoEnoughPP", "Not enough points of faith");
		this.InfoPP = properties.getString("InfoPP", "VALUE points of faith");
		//this.NoEnoughPP = properties.getString("NoEnoughPP", "Pas assez de point de foi.");
		this.AltarAlreadyUse = properties.getString("AltarAlreadyUse", "Altar already used");
		
		//
		this.PrayerPointByAltarSize = properties.getInteger("PrayerPointByAltarSize", 1);
		this.AltarSizeMax = properties.getInteger("AltarSizeMax", 4);
		this.MaxPrayerPointPerLevel = properties.getInteger("MaxPrayerPointPerLevel", 5);
		this.SneakToBless = properties.getBoolean("SneakToBless", false);
		this.AltarBlock = properties.getInteger("AltarBlock", 41);
		this.timeToRechargeAltar = properties.getInteger("timeToRechargeAltar", 10);
		this.fullRechargeAltar = properties.getBoolean("fullRechargeAltar", true);
		//
		this.ZPLen = properties.getDouble("ZoneProtectorLength", 0);
		
		
		//Force by level
		this.HealByLevel = properties.getInteger("HealByLevel", 1);
		
		//Prayer cost
		HealNeedLevel = properties.getInteger("HealNeedLevel", 1);
		ProtectNeedLevel = properties.getInteger("ProtectNeedLevel", 5);
		HealerNeedLevel = properties.getInteger("HealerNeedLevel", 10);
		ProtecterNeedLevel = properties.getInteger("ProtecterNeedLevel", 15);
		BoostNeedLevel = properties.getInteger("BoostNeedLevel", 30);
		ZoneProtecterNeedLevel = properties.getInteger("ZoneProtecterNeedLevel", 50);
		
		//Needed level
		HealCost = properties.getInteger("HealCost", 2);
		ProtectCost = properties.getInteger("ProtectCost", 5);
		HealerCost = properties.getInteger("HealerCost", 5);
		ProtecterCost = properties.getInteger("ProtecterCost", 7);
		BoostCost = properties.getInteger("BoostCost", 20);
		ZoneProtecterCost = properties.getInteger("ZoneProtecterCost", 30);
		
		//exp boost
		percentBoostCombatByLevel = properties.getDouble("percentBoostCombatByLevel", 0.1);
		percentBoostRangeByLevel = properties.getDouble("percentBoostRangeByLevel", 0.1);
		percentBoostDefenseByLevel = properties.getDouble("percentBoostDefenseByLevel", 0.1);
		
		//exp time
		timeProtectByLevel = properties.getDouble("timeProtectByLevel", 1);
		timeBoostCombatByLevel = properties.getDouble("timeBoostCombatByLevel", 1);
		timeBoostRangeByLevel = properties.getDouble("timeBoostRangeByLevel", 1);
		timeBoostDefenseByLevel = properties.getDouble("timeBoostDefenseByLevel", 1);
		timeZoneProtectByLevel = properties.getDouble("timeZoneProtectByLevel", 1);
	}
}
