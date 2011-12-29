package me.samkio.lcforgery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import net.minecraft.server.FurnaceRecipes;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LCForgery extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	final LCPlayerListener playerListener = new LCPlayerListener(this);
	final LCConfiguration LCConfiguration = new LCConfiguration(this);
	public String ConfigurationFileString =  "plugins/LevelCraftCore/Configs/Forgery.cfg";
	public  File ConfigurationFile = new File(ConfigurationFileString);
    public Plugin thisPlug;
    public  ArrayList<FurnaceRecipe> furnaceRecipeObjects = new ArrayList<FurnaceRecipe>();
	
	@Override
	public void onDisable() {

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		new File("plugins/LevelCraftCore/Configs/").mkdirs();
		try {
			ConfigurationFile.createNewFile();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "[LC] "+e);
		}
		LCConfiguration.loadConfig();
		// Set Reference Keys.
		String[] Args = { "F", "Forge", "Repair", "Smelt" };
		getConfiguration().setProperty("ReferenceKeys", Args);
		// Set Unlocks Data
		
		
		String[] Unlocks = {
				"Forge Iron Shovel = " + this.LCConfiguration.ForgeIronShovel,
				"Forge Iron Pick = " + this.LCConfiguration.ForgeIronPick,
				"Forge Iron Axe = " + this.LCConfiguration.ForgeIronAxe,
				"Forge Iron Sword = " + this.LCConfiguration.ForgeIronSword,
				"Forge Iron Hoe = " + this.LCConfiguration.ForgeIronHoe,
				"Forge Stone Shovel = " + this.LCConfiguration.ForgeStoneShovel,
				"Forge Stone Pick = " + this.LCConfiguration.ForgeStonePick,
				"Forge Stone Axe = " + this.LCConfiguration.ForgeStoneAxe,
				"Forge Stone Sword = " + this.LCConfiguration.ForgeStoneSword,
				"Forge Stone Hoe = " + this.LCConfiguration.ForgeStoneHoe,
				"Forge Gold Shovel = " + this.LCConfiguration.ForgeGoldShovel,
				"Forge Gold Pick = " + this.LCConfiguration.ForgeGoldPick,
				"Forge Gold Axe = " + this.LCConfiguration.ForgeGoldAxe,
				"Forge Gold Sword = " + this.LCConfiguration.ForgeGoldSword,
				"Forge Gold Hoe = " + this.LCConfiguration.ForgeGoldHoe,
				"Forge Diamond Shovel = " + this.LCConfiguration.ForgeDiamondShovel,
				"Forge Diamond Pick = " + this.LCConfiguration.ForgeDiamondPick,
				"Forge Diamond Axe = " + this.LCConfiguration.ForgeDiamondAxe,
				"Forge Diamond Sword = " + this.LCConfiguration.ForgeDiamondSword,
				"Forge Diamond Hoe = " + this.LCConfiguration.ForgeDiamondHoe,
				"Forge Chain Helm = " + this.LCConfiguration.ForgeChainHelm,
				"Forge Chain Chest = " + this.LCConfiguration.ForgeChainChest,
				"Forge Chain Leg = " + this.LCConfiguration.ForgeChainLeg,
				"Forge Chain Boot = " + this.LCConfiguration.ForgeChainBoot,
				"Forge Gold Helm = " + this.LCConfiguration.ForgeGoldHelm,
				"Forge Gold Chest = " + this.LCConfiguration.ForgeGoldChest,
				"Forge Gold Leg = " + this.LCConfiguration.ForgeGoldLeg,
				"Forge Gold Boot = " + this.LCConfiguration.ForgeGoldBoot,
				"Forge Iron Helm = " + this.LCConfiguration.ForgeIronHelm,
				"Forge Iron Chest = " + this.LCConfiguration.ForgeIronChest,
				"Forge Iron Leg = " + this.LCConfiguration.ForgeIronLeg,
				"Forge Iron Boot = " + this.LCConfiguration.ForgeIronBoot,
				"Forge Diamond Helm = " + this.LCConfiguration.ForgeDiamondHelm,
				"Forge Diamond Chest = " + this.LCConfiguration.ForgeDiamondChest,
				"Forge Diamond Leg = " + this.LCConfiguration.ForgeDiamondLeg,
				"Forge Diamond Boot = " + this.LCConfiguration.ForgeDiamondBoot,
				"Repair Iron Shovel = " + this.LCConfiguration.RepairIronShovel,
				"Repair Iron Pick = " + this.LCConfiguration.RepairIronPick,
				"Repair Iron Axe = " + this.LCConfiguration.RepairIronAxe,
				"Repair Iron Sword = " + this.LCConfiguration.RepairIronSword,
				"Repair Iron Hoe = " + this.LCConfiguration.RepairIronHoe,
				"Repair Stone Shovel = " + this.LCConfiguration.RepairStoneShovel,
				"Repair Stone Pick = " + this.LCConfiguration.RepairStonePick,
				"Repair Stone Axe = " + this.LCConfiguration.RepairStoneAxe,
				"Repair Stone Sword = " + this.LCConfiguration.RepairStoneSword,
				"Repair Stone Hoe = " + this.LCConfiguration.RepairStoneHoe,
				"Repair Gold Shovel = " + this.LCConfiguration.RepairGoldShovel,
				"Repair Gold Pick = " + this.LCConfiguration.RepairGoldPick,
				"Repair Gold Axe = " + this.LCConfiguration.RepairGoldAxe,
				"Repair Gold Sword = " + this.LCConfiguration.RepairGoldSword,
				"Repair Gold Hoe = " + this.LCConfiguration.RepairGoldHoe,
				"Repair Diamond Shovel = " + this.LCConfiguration.RepairDiamondShovel,
				"Repair Diamond Pick = " + this.LCConfiguration.RepairDiamondPick,
				"Repair Diamond Axe = " + this.LCConfiguration.RepairDiamondAxe,
				"Repair Diamond Sword = " + this.LCConfiguration.RepairDiamondSword,
				"Repair Diamond Hoe = " + this.LCConfiguration.RepairDiamondHoe,
				"Repair Chain Helm = " + this.LCConfiguration.RepairChainHelm,
				"Repair Chain Chest = " + this.LCConfiguration.RepairChainChest,
				"Repair Chain Leg = " + this.LCConfiguration.RepairChainLeg,
				"Repair Chain Boot = " + this.LCConfiguration.RepairChainBoot,
				"Repair Gold Helm = " + this.LCConfiguration.RepairGoldHelm,
				"Repair Gold Chest = " + this.LCConfiguration.RepairGoldChest,
				"Repair Gold Leg = " + this.LCConfiguration.RepairGoldLeg,
				"Repair Gold Boot = " + this.LCConfiguration.RepairGoldBoot,
				"Repair Iron Helm = " + this.LCConfiguration.RepairIronHelm,
				"Repair Iron Chest = " + this.LCConfiguration.RepairIronChest,
				"Repair Iron Leg = " + this.LCConfiguration.RepairIronLeg,
				"Repair Iron Boot = " + this.LCConfiguration.RepairIronBoot,
				"Repair Diamond Helm = " + this.LCConfiguration.RepairDiamondHelm,
				"Repair Diamond Chest = " + this.LCConfiguration.RepairDiamondChest,
				"Repair Diamond Leg = " + this.LCConfiguration.RepairDiamondLeg,
				"Repair Diamond Boot = " + this.LCConfiguration.RepairDiamondBoot,
				"Gold Bar = " + this.LCConfiguration.GoldBarLevel,
				"Iron Bar = " + this.LCConfiguration.IronBarLevel,
				"Stone = " + this.LCConfiguration.StoneLevel,
				"Glass = " + this.LCConfiguration.GlassLevel,
				"Clay = " + this.LCConfiguration.ClayLevel,
				
		};

		int[] UnlocksLevel = {
				this.LCConfiguration.ForgeIronShovel,
				this.LCConfiguration.ForgeIronPick,
				this.LCConfiguration.ForgeIronAxe,
				this.LCConfiguration.ForgeIronSword,
				this.LCConfiguration.ForgeIronHoe,
				this.LCConfiguration.ForgeStoneShovel,
				this.LCConfiguration.ForgeStonePick,
				this.LCConfiguration.ForgeStoneAxe,
				this.LCConfiguration.ForgeStoneSword,
				this.LCConfiguration.ForgeStoneHoe,
				this.LCConfiguration.ForgeGoldShovel,
				this.LCConfiguration.ForgeGoldPick,
				this.LCConfiguration.ForgeGoldAxe,
				this.LCConfiguration.ForgeGoldSword,
				this.LCConfiguration.ForgeGoldHoe,
				this.LCConfiguration.ForgeDiamondShovel,
				this.LCConfiguration.ForgeDiamondPick,
				this.LCConfiguration.ForgeDiamondAxe,
				this.LCConfiguration.ForgeDiamondSword,
				this.LCConfiguration.ForgeDiamondHoe,
				this.LCConfiguration.ForgeChainHelm,
				this.LCConfiguration.ForgeChainChest,
				this.LCConfiguration.ForgeChainLeg,
				this.LCConfiguration.ForgeChainBoot,
				this.LCConfiguration.ForgeGoldHelm,
				this.LCConfiguration.ForgeGoldChest,
				this.LCConfiguration.ForgeGoldLeg,
				this.LCConfiguration.ForgeGoldBoot,
				this.LCConfiguration.ForgeIronHelm,
				this.LCConfiguration.ForgeIronChest,
				this.LCConfiguration.ForgeIronLeg,
				this.LCConfiguration.ForgeIronBoot,
				this.LCConfiguration.ForgeDiamondHelm,
				this.LCConfiguration.ForgeDiamondChest,
				this.LCConfiguration.ForgeDiamondLeg,
				this.LCConfiguration.ForgeDiamondBoot,
				this.LCConfiguration.RepairIronShovel,
				this.LCConfiguration.RepairIronPick,
				this.LCConfiguration.RepairIronAxe,
				this.LCConfiguration.RepairIronSword,
				this.LCConfiguration.RepairIronHoe,
				this.LCConfiguration.RepairStoneShovel,
				this.LCConfiguration.RepairStonePick,
				this.LCConfiguration.RepairStoneAxe,
				this.LCConfiguration.RepairStoneSword,
				this.LCConfiguration.RepairStoneHoe,
				this.LCConfiguration.RepairGoldShovel,
				this.LCConfiguration.RepairGoldPick,
				this.LCConfiguration.RepairGoldAxe,
				this.LCConfiguration.RepairGoldSword,
				this.LCConfiguration.RepairGoldHoe,
				this.LCConfiguration.RepairDiamondShovel,
				this.LCConfiguration.RepairDiamondPick,
				this.LCConfiguration.RepairDiamondAxe,
				this.LCConfiguration.RepairDiamondSword,
				this.LCConfiguration.RepairDiamondHoe,
				this.LCConfiguration.RepairChainHelm,
				this.LCConfiguration.RepairChainChest,
				this.LCConfiguration.RepairChainLeg,
				this.LCConfiguration.RepairChainBoot,
				this.LCConfiguration.RepairGoldHelm,
				this.LCConfiguration.RepairGoldChest,
				this.LCConfiguration.RepairGoldLeg,
				this.LCConfiguration.RepairGoldBoot,
				this.LCConfiguration.RepairIronHelm,
				this.LCConfiguration.RepairIronChest,
				this.LCConfiguration.RepairIronLeg,
				this.LCConfiguration.RepairIronBoot,
				this.LCConfiguration.RepairDiamondHelm,
				this.LCConfiguration.RepairDiamondChest,
				this.LCConfiguration.RepairDiamondLeg,
				this.LCConfiguration.RepairDiamondBoot,
				this.LCConfiguration.GoldBarLevel,
				this.LCConfiguration.IronBarLevel,
				this.LCConfiguration.StoneLevel,
				this.LCConfiguration.GlassLevel,
				this.LCConfiguration.ClayLevel,
		};
		String[] Exp = { 
			      "Exp Per Forge Iron Shovel " + this.LCConfiguration.ForgeExpIronShovel, 
			      "Exp Per Forge Iron Pick " + this.LCConfiguration.ForgeExpIronPick, 
			      "Exp Per Forge Iron Axe " + this.LCConfiguration.ForgeExpIronAxe, 
			      "Exp Per Forge Iron Sword " + this.LCConfiguration.ForgeExpIronSword, 
			      "Exp Per Forge Iron Hoe " + this.LCConfiguration.ForgeExpIronHoe,
			      "Exp Per Forge Stone Shovel " + this.LCConfiguration.ForgeExpStoneShovel, 
			      "Exp Per Forge Stone Pick " + this.LCConfiguration.ForgeExpStonePick, 
			      "Exp Per Forge Stone Axe " + this.LCConfiguration.ForgeExpStoneAxe, 
			      "Exp Per Forge Stone Sword " + this.LCConfiguration.ForgeExpStoneSword, 
			      "Exp Per Forge Stone Hoe " + this.LCConfiguration.ForgeExpStoneHoe,
			      "Exp Per Forge Gold Shovel " + this.LCConfiguration.ForgeExpGoldShovel,
			      "Exp Per Forge Gold Pick " + this.LCConfiguration.ForgeExpGoldPick,
			      "Exp Per Forge Gold Axe " + this.LCConfiguration.ForgeExpGoldAxe,
			      "Exp Per Forge Gold Sword " + this.LCConfiguration.ForgeExpGoldSword,
			      "Exp Per Forge Gold Hoe " + this.LCConfiguration.ForgeExpGoldHoe,
			      "Exp Per Forge Diamond Shovel " + this.LCConfiguration.ForgeExpDiamondShovel,
			      "Exp Per Forge Diamond Pick " + this.LCConfiguration.ForgeExpDiamondPick,
			      "Exp Per Forge Diamond Axe " + this.LCConfiguration.ForgeExpDiamondAxe,
			      "Exp Per Forge Diamond Sword " + this.LCConfiguration.ForgeExpDiamondSword,
			      "Exp Per Forge Diamond Hoe " + this.LCConfiguration.ForgeExpDiamondHoe,
			      "Exp Per Forge Chain Helmet " + this.LCConfiguration.ForgeExpChainHelm,
			      "Exp Per Forge Chain Chestplate " + this.LCConfiguration.ForgeExpChainChest,
			      "Exp Per Forge Chain Leggings " + this.LCConfiguration.ForgeExpChainLeg,
			      "Exp Per Forge Chain Boots " + this.LCConfiguration.ForgeExpChainBoot,
			      "Exp Per Forge Gold Helmet " + this.LCConfiguration.ForgeExpGoldHelm,
			      "Exp Per Forge Gold Chestplate " + this.LCConfiguration.ForgeExpGoldChest,
			      "Exp Per Forge Gold Leggings " + this.LCConfiguration.ForgeExpGoldLeg,
			      "Exp Per Forge Gold Boots " + this.LCConfiguration.ForgeExpGoldBoot,
			      "Exp Per Forge Iron Helmet " + this.LCConfiguration.ForgeExpIronHelm,
			      "Exp Per Forge Iron Chestplate " + this.LCConfiguration.ForgeExpIronChest,
			      "Exp Per Forge Iron Leggings " + this.LCConfiguration.ForgeExpIronLeg,
			      "Exp Per Forge Iron Boots " + this.LCConfiguration.ForgeExpIronBoot,
			      "Exp Per Forge Diamond Helmet " + this.LCConfiguration.ForgeExpDiamondHelm,
			      "Exp Per Forge Diamond Chestplate " + this.LCConfiguration.ForgeExpDiamondChest,
			      "Exp Per Forge Diamond Leggings " + this.LCConfiguration.ForgeExpDiamondLeg,
			      "Exp Per Forge Diamond Boots " + this.LCConfiguration.ForgeExpDiamondBoot,
			      "Exp Per Repair Iron Shovel " + this.LCConfiguration.RepairExpIronShovel, 
			      "Exp Per Repair Iron Pick " + this.LCConfiguration.RepairExpIronPick, 
			      "Exp Per Repair Iron Axe " + this.LCConfiguration.RepairExpIronAxe, 
			      "Exp Per Repair Iron Sword " + this.LCConfiguration.RepairExpIronSword, 
			      "Exp Per Repair Iron Hoe " + this.LCConfiguration.RepairExpIronHoe,
			      "Exp Per Repair Stone Shovel " + this.LCConfiguration.RepairExpStoneShovel, 
			      "Exp Per Repair Stone Pick " + this.LCConfiguration.RepairExpStonePick, 
			      "Exp Per Repair Stone Axe " + this.LCConfiguration.RepairExpStoneAxe, 
			      "Exp Per Repair Stone Sword " + this.LCConfiguration.RepairExpStoneSword, 
			      "Exp Per Repair Stone Hoe " + this.LCConfiguration.RepairExpStoneHoe,
			      "Exp Per Repair Gold Shovel " + this.LCConfiguration.RepairExpGoldShovel,
			      "Exp Per Repair Gold Pick " + this.LCConfiguration.RepairExpGoldPick,
			      "Exp Per Repair Gold Axe " + this.LCConfiguration.RepairExpGoldAxe,
			      "Exp Per Repair Gold Sword " + this.LCConfiguration.RepairExpGoldSword,
			      "Exp Per Repair Gold Hoe " + this.LCConfiguration.RepairExpGoldHoe,
			      "Exp Per Repair Diamond Shovel " + this.LCConfiguration.RepairExpDiamondShovel,
			      "Exp Per Repair Diamond Pick " + this.LCConfiguration.RepairExpDiamondPick,
			      "Exp Per Repair Diamond Axe " + this.LCConfiguration.RepairExpDiamondAxe,
			      "Exp Per Repair Diamond Sword " + this.LCConfiguration.RepairExpDiamondSword,
			      "Exp Per Repair Diamond Hoe " + this.LCConfiguration.RepairExpDiamondHoe,
			      "Exp Per Repair Chain Helmet " + this.LCConfiguration.RepairExpChainHelm,
			      "Exp Per Repair Chain Chestplate " + this.LCConfiguration.RepairExpChainChest,
			      "Exp Per Repair Chain Leggings " + this.LCConfiguration.RepairExpChainLeg,
			      "Exp Per Repair Chain Boots " + this.LCConfiguration.RepairExpChainBoot,
			      "Exp Per Repair Gold Helmet " + this.LCConfiguration.RepairExpGoldHelm,
			      "Exp Per Repair Gold Chestplate " + this.LCConfiguration.RepairExpGoldChest,
			      "Exp Per Repair Gold Leggings " + this.LCConfiguration.RepairExpGoldLeg,
			      "Exp Per Repair Gold Boots " + this.LCConfiguration.RepairExpGoldBoot,
			      "Exp Per Repair Iron Helmet " + this.LCConfiguration.RepairExpIronHelm,
			      "Exp Per Repair Iron Chestplate " + this.LCConfiguration.RepairExpIronChest,
			      "Exp Per Repair Iron Leggings " + this.LCConfiguration.RepairExpIronLeg,
			      "Exp Per Repair Iron Boots " + this.LCConfiguration.RepairExpIronBoot,
			      "Exp Per Repair Diamond Helmet " + this.LCConfiguration.RepairExpDiamondHelm,
			      "Exp Per Repair Diamond Chestplate " + this.LCConfiguration.RepairExpDiamondChest,
			      "Exp Per Repair Diamond Leggings " + this.LCConfiguration.RepairExpDiamondLeg,
			      "Exp Per Repair Diamond Boots " + this.LCConfiguration.RepairExpDiamondBoot,
			      "Exp Per Repair Iron Bar " + this.LCConfiguration.ExpPerIronBar,
			      "Exp Per Repair Gold Bar " + this.LCConfiguration.ExpPerGoldBar,
			      "Exp Per Repair Stone " + this.LCConfiguration.ExpPerStone,
			      "Exp Per Repair Glass " + this.LCConfiguration.ExpPerGlass,
			      "Exp Per Repair Clay " + this.LCConfiguration.ExpPerClay,
			      
			      
		};
			      
		String[] help = {"Forge items by making them on a work bench.","Smelt items by placing items in a furnace.","Repair items by placing a broken tool in a furnace."};
		
		
		getConfiguration().setProperty("LevelUnlocks", Unlocks);
		
		getConfiguration().setProperty("LevelExpPer", Exp);
		getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
		// Set Level Name.
		getConfiguration().setProperty("LevelName", "Forgery");
		// Set Reference Index (1-2 Characters).
		getConfiguration().setProperty("ReferenceIndex", "F");
		// Set Author :3.
		getConfiguration().setProperty("Author", "Samkio");
		// Log that it has loaded.
		

		
		
		
		getConfiguration().setProperty("LevelHelp", help);
        this.thisPlug = this.getServer().getPluginManager().getPlugin("LCForgery");
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin(
				"LevelCraftCore");
		if (LevelCraftCore == null) {
			logger.log(Level.SEVERE,
					"[LC] Could not fine LevelCraftCore. Disabling.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			registerEvents();
			if(this.LCConfiguration.Repair){
				populateFurnaceRecipes();
				addFurnaceRecipe(furnaceRecipeObjects);
			}
			logger.log(Level.INFO, "[LC] Level Forgery "+getDescription().getVersion()+" Loaded");
			
		}
	}

	

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener,
				Event.Priority.Highest, this);

		
	}
	private void populateFurnaceRecipes() {


		int[] ingredientID = { 276,277,278,279,256,257,258,267,272,273,274,275,283,284,285,286,291,292,293,294,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,268,269,270,271,290,298,299,300,301 };
		int[] resultID =     { 276,277,278,279,256,257,258,267,272,273,274,275,283,284,285,286,291,292,293,294,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,263,263,263,263,263,334,334,334,334 };
		int[] resultData = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0};
		double cooktime =  0.0D;
		

		for (int i = 0; i < ingredientID.length; i++) {
			net.minecraft.server.ItemStack temp = new net.minecraft.server.ItemStack(resultID[i], 1, resultData[i]);
			CraftItemStack result = new CraftItemStack(temp);
			furnaceRecipeObjects.add(new FurnaceRecipe(ingredientID[i], result,cooktime, resultData[i]));
		}

	}

	public  void addFurnaceRecipe(ArrayList<FurnaceRecipe> recipes) {
		for (FurnaceRecipe fr : recipes) {
			net.minecraft.server.ItemStack result = null;
			if (fr.getResult().getTypeId() != 0) {
				result = new net.minecraft.server.ItemStack(fr.getResult()
						.getTypeId(), fr.getResult().getAmount(), fr.getData());
			}
			FurnaceRecipes.getInstance().registerRecipe(fr.getIngredient(), result);	
		}
	}

}
