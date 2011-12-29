package me.samkio.lcforgery;


import java.io.IOException;
import java.util.logging.Level;

import me.samkio.levelcraftcore.util.Properties;


public class LCConfiguration {
	public LCForgery plugin;
	//EXP PER
	public double ExpPerIronBar;
	public double ExpPerGoldBar;
	public double ExpPerGlass;
	public double ExpPerStone;
	public double ExpPerClay;
	//LEVEL BLOCK
	public int IronBarLevel;
	public int GoldBarLevel;
	public int GlassLevel;
	public int StoneLevel;
    public int ClayLevel;
    public boolean Repair;
	
    
    
    //Repair Levels
    public int RepairIronShovel;
    public int RepairIronPick;
    public int RepairIronAxe;
    public int RepairIronSword;
    public int RepairStoneSword;
    public int RepairStoneShovel;
    public int RepairStonePick;
    public int RepairStoneAxe;
    public int RepairDiamondSword;
    public int RepairDiamondShovel;
    public int RepairDiamondPick;
    public int RepairDiamondAxe;
    public int RepairGoldSword;
    public int RepairGoldShovel;
    public int RepairGoldPick;
    public int RepairGoldAxe;
    public int RepairStoneHoe;
    public int RepairIronHoe;
    public int RepairDiamondHoe;
    public int RepairGoldHoe;
    public int RepairChainHelm;
    public int RepairChainChest;
    public int RepairChainLeg;
    public int RepairChainBoot;
    public int RepairIronHelm;
    public int RepairIronChest;
    public int RepairIronLeg;
    public int RepairIronBoot;
    public int RepairDiamondHelm;
    public int RepairDiamondChest;
    public int RepairDiamondLeg;
    public int RepairDiamondBoot;
    public int RepairGoldHelm;
    public int RepairGoldChest;
    public int RepairGoldLeg;
    public int RepairGoldBoot;
    
    
    
    
    //ExpPerRepair
    public double RepairExpIronShovel;
    public double RepairExpIronPick;
    public double RepairExpIronAxe;
    public double RepairExpIronSword;
    public double RepairExpStoneSword;
    public double RepairExpStoneShovel;
    public double RepairExpStonePick;
    public double RepairExpStoneAxe;
    public double RepairExpDiamondSword;
    public double RepairExpDiamondShovel;
    public double RepairExpDiamondPick;
    public double RepairExpDiamondAxe;
    public double RepairExpGoldSword;
    public double RepairExpGoldShovel;
    public double RepairExpGoldPick;
    public double RepairExpGoldAxe;
    public double RepairExpStoneHoe;
    public double RepairExpIronHoe;
    public double RepairExpDiamondHoe;
    public double RepairExpGoldHoe;
    public double RepairExpChainHelm;
    public double RepairExpChainChest;
    public double RepairExpChainLeg;
    public double RepairExpChainBoot;
    public double RepairExpIronHelm;
    public double RepairExpIronChest;
    public double RepairExpIronLeg;
    public double RepairExpIronBoot;
    public double RepairExpDiamondHelm;
    public double RepairExpDiamondChest;
    public double RepairExpDiamondLeg;
    public double RepairExpDiamondBoot;
    public double RepairExpGoldHelm;
    public double RepairExpGoldChest;
    public double RepairExpGoldLeg;
    public double RepairExpGoldBoot;
    
    
    //ForgeLevels
    public int ForgeIronShovel;
    public int ForgeIronPick;
    public int ForgeIronAxe;
    public int ForgeIronSword;
    public int ForgeStoneSword;
    public int ForgeStoneShovel;
    public int ForgeStonePick;
    public int ForgeStoneAxe;
    public int ForgeDiamondSword;
    public int ForgeDiamondShovel;
    public int ForgeDiamondPick;
    public int ForgeDiamondAxe;
    public int ForgeGoldSword;
    public int ForgeGoldShovel;
    public int ForgeGoldPick;
    public int ForgeGoldAxe;
    public int ForgeStoneHoe;
    public int ForgeIronHoe;
    public int ForgeDiamondHoe;
    public int ForgeGoldHoe;
    public int ForgeChainHelm;
    public int ForgeChainChest;
    public int ForgeChainLeg;
    public int ForgeChainBoot;
    public int ForgeIronHelm;
    public int ForgeIronChest;
    public int ForgeIronLeg;
    public int ForgeIronBoot;
    public int ForgeDiamondHelm;
    public int ForgeDiamondChest;
    public int ForgeDiamondLeg;
    public int ForgeDiamondBoot;
    public int ForgeGoldHelm;
    public int ForgeGoldChest;
    public int ForgeGoldLeg;
    public int ForgeGoldBoot;
    
    
    //ExpPerForge
    public double ForgeExpIronShovel;
    public double ForgeExpIronPick;
    public double ForgeExpIronAxe;
    public double ForgeExpIronSword;
    public double ForgeExpStoneSword;
    public double ForgeExpStoneShovel;
    public double ForgeExpStonePick;
    public double ForgeExpStoneAxe;
    public double ForgeExpDiamondSword;
    public double ForgeExpDiamondShovel;
    public double ForgeExpDiamondPick;
    public double ForgeExpDiamondAxe;
    public double ForgeExpGoldSword;
    public double ForgeExpGoldShovel;
    public double ForgeExpGoldPick;
    public double ForgeExpGoldAxe;
    public double ForgeExpStoneHoe;
    public double ForgeExpIronHoe;
    public double ForgeExpDiamondHoe;
    public double ForgeExpGoldHoe;
    public double ForgeExpChainHelm;
    public double ForgeExpChainChest;
    public double ForgeExpChainLeg;
    public double ForgeExpChainBoot;
    public double ForgeExpIronHelm;
    public double ForgeExpIronChest;
    public double ForgeExpIronLeg;
    public double ForgeExpIronBoot;
    public double ForgeExpDiamondHelm;
    public double ForgeExpDiamondChest;
    public double ForgeExpDiamondLeg;
    public double ForgeExpDiamondBoot;
    public double ForgeExpGoldHelm;
    public double ForgeExpGoldChest;
    public double ForgeExpGoldLeg;
    public double ForgeExpGoldBoot;
    
	public LCConfiguration(LCForgery instance) {
		plugin = instance;
	}

	public void loadConfig() {

		Properties properties = new Properties(plugin.ConfigurationFileString);
		try {
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE,"[LC] "+e);
		}
		//TOOLS
		this.IronBarLevel = properties.getInteger("IronBarLevel", 10);
		this.GoldBarLevel = properties.getInteger("GoldBarLevel", 15);
		this.GlassLevel = properties.getInteger("GlassLevel", 5);
		this.StoneLevel = properties.getInteger("StoneLevel", 0);
		this.ClayLevel = properties.getInteger("BrickLevel", 0);
		this.Repair = properties.getBoolean("AllowRepair", true);
		this.ExpPerClay= properties.getDouble("ExpPerBrick", 5);
		this.ExpPerGlass= properties.getDouble("ExpPerGlass", 10);
		this.ExpPerGoldBar = properties.getDouble("ExpPerGoldBar", 30);
		this.ExpPerIronBar = properties.getDouble("ExpPerIronBar", 20);
		this.ExpPerStone = properties.getDouble("ExpPerStone", 3);
	
	    //Repair Levels
	    this.RepairIronShovel  = properties.getInteger("RepairIronShovelLevel", 10);
	    this.RepairIronPick = properties.getInteger("RepairIronPickLevel", 10);
	    this.RepairIronAxe = properties.getInteger("RepairIronAxeLevel", 10);
	    this.RepairIronSword = properties.getInteger("RepairIronSwordLevel", 10);
	    
	    this.RepairStoneSword = properties.getInteger("RepairStoneSwordLevel", 5);
	    this.RepairStoneShovel = properties.getInteger("RepairStoneShovelLevel", 5);
	    this.RepairStonePick = properties.getInteger("RepairStonePickLevel", 5);
	    this.RepairStoneAxe = properties.getInteger("RepairStoneAxeLevel", 5);
	    
	    this.RepairDiamondSword = properties.getInteger("RepairDiamondSwordLevel", 30);
	    this.RepairDiamondShovel = properties.getInteger("RepairDiamondShovelLevel", 30);
	    this.RepairDiamondPick = properties.getInteger("RepairDiamondPickLevel", 30);
	    this.RepairDiamondAxe = properties.getInteger("RepairDiamondAxeLevel", 30);
	    
	    this.RepairGoldSword = properties.getInteger("RepairGoldSwordLevel", 20);
	    this.RepairGoldShovel = properties.getInteger("RepairGoldShovelLevel", 20);
	    this.RepairGoldPick = properties.getInteger("RepairGoldPickLevel", 20);
	    this.RepairGoldAxe = properties.getInteger("RepairGoldAxeLevel", 20);
	    
	    this.RepairStoneHoe = properties.getInteger("RepairStoneHoeLevel", 5);
	    this.RepairIronHoe = properties.getInteger("RepairIronHoeLevel", 10);
	    this.RepairDiamondHoe = properties.getInteger("RepairDiamondHoeLevel", 30);
	    this.RepairGoldHoe = properties.getInteger("RepairGoldHoeLevel", 20);
	    
	    this.RepairChainHelm = properties.getInteger("RepairChainHelmLevel", 5);
	    this.RepairChainChest = properties.getInteger("RepairChainChestLevel", 5);
	    this.RepairChainLeg = properties.getInteger("RepairChainLegLevel", 5);
	    this.RepairChainBoot = properties.getInteger("RepairChainBootLevel", 5);
	    
	    this.RepairIronHelm = properties.getInteger("RepairIronHelmLevel", 10);
	    this.RepairIronChest = properties.getInteger("RepairIronChestLevel", 10);
	    this.RepairIronLeg = properties.getInteger("RepairIronLegLevel", 10);
	    this.RepairIronBoot = properties.getInteger("RepairIronBootLevel", 10);
	    
	    this.RepairDiamondHelm = properties.getInteger("RepairDiamondHelmLevel", 30);
	    this.RepairDiamondChest = properties.getInteger("RepairDiamondChestLevel", 30);
	    this.RepairDiamondLeg = properties.getInteger("RepairDiamondLegLevel", 30);
	    this.RepairDiamondBoot = properties.getInteger("RepairDiamondBootLevel", 30);
	    
	    this.RepairGoldHelm = properties.getInteger("RepairGoldHelmLevel", 20);
	    this.RepairGoldChest = properties.getInteger("RepairGoldChestLevel", 20);
	    this.RepairGoldLeg = properties.getInteger("RepairGoldLegLevel", 20);
	    this.RepairGoldBoot = properties.getInteger("RepairGoldBootLevel", 20);
         //RepairExpPer
	    this.RepairExpIronShovel  = properties.getDouble("ExpPerRepairIronShovelLevel", 15);
	    this.RepairExpIronPick = properties.getDouble("ExpPerRepairIronPickLevel", 15);
	    this.RepairExpIronAxe = properties.getDouble("ExpPerRepairIronAxeLevel", 15);
	    this.RepairExpIronSword = properties.getDouble("ExpPerRepairIronSwordLevel", 15);
	    
	    this.RepairExpStoneSword = properties.getDouble("ExpPerRepairStoneSwordLevel", 5);
	    this.RepairExpStoneShovel = properties.getDouble("ExpPerRepairStoneShovelLevel", 5);
	    this.RepairExpStonePick = properties.getDouble("ExpPerRepairStonePickLevel", 5);
	    this.RepairExpStoneAxe = properties.getDouble("ExpPerRepairStoneAxeLevel", 5);
	    
	    this.RepairExpDiamondSword = properties.getDouble("ExpPerRepairDiamondSwordLevel", 50);
	    this.RepairExpDiamondShovel = properties.getDouble("ExpPerRepairDiamondShovelLevel", 50);
	    this.RepairExpDiamondPick = properties.getDouble("ExpPerRepairDiamondPickLevel", 50);
	    this.RepairExpDiamondAxe = properties.getDouble("ExpPerRepairDiamondAxeLevel", 50);
	    
	    this.RepairExpGoldSword = properties.getDouble("ExpPerRepairGoldSwordLevel", 40);
	    this.RepairExpGoldShovel = properties.getDouble("ExpPerRepairGoldShovelLevel", 40);
	    this.RepairExpGoldPick = properties.getDouble("ExpPerRepairGoldPickLevel", 40);
	    this.RepairExpGoldAxe = properties.getDouble("ExpPerRepairGoldAxeLevel", 40);
	    
	    this.RepairExpStoneHoe = properties.getDouble("ExpPerRepairStoneHoeLevel", 5);
	    this.RepairExpIronHoe = properties.getDouble("ExpPerRepairIronHoeLevel", 15);
	    this.RepairExpDiamondHoe = properties.getDouble("ExpPerRepairDiamondHoeLevel", 50);
	    this.RepairExpGoldHoe = properties.getDouble("ExpPerRepairGoldHoeLevel", 40);
	    
	    this.RepairExpChainHelm = properties.getDouble("ExpPerRepairChainHelmLevel", 5);
	    this.RepairExpChainChest = properties.getDouble("ExpPerRepairChainChestLevel", 5);
	    this.RepairExpChainLeg = properties.getDouble("ExpPerRepairChainLegLevel", 5);
	    this.RepairExpChainBoot = properties.getDouble("ExpPerRepairChainBootLevel", 5);
	    
	    this.RepairExpIronHelm = properties.getDouble("ExpPerRepairIronHelmLevel", 15);
	    this.RepairExpIronChest = properties.getDouble("ExpPerRepairIronChestLevel", 15);
	    this.RepairExpIronLeg = properties.getDouble("ExpPerRepairIronLegLevel", 15);
	    this.RepairExpIronBoot = properties.getDouble("ExpPerRepairIronBootLevel", 15);
	    
	    this.RepairExpDiamondHelm = properties.getDouble("ExpPerRepairDiamondHelmLevel", 50);
	    this.RepairExpDiamondChest = properties.getDouble("ExpPerRepairDiamondChestLevel", 50);
	    this.RepairExpDiamondLeg = properties.getDouble("ExpPerRepairDiamondLegLevel", 50);
	    this.RepairExpDiamondBoot = properties.getDouble("ExpPerRepairDiamondBootLevel", 50);
	    
	    this.RepairExpGoldHelm = properties.getDouble("ExpPerRepairGoldHelmLevel", 40);
	    this.RepairExpGoldChest = properties.getDouble("ExpPerRepairGoldChestLevel", 40);
	    this.RepairExpGoldLeg = properties.getDouble("ExpPerRepairGoldLegLevel", 40);
	    this.RepairExpGoldBoot = properties.getDouble("ExpPerRepairGoldBootLevel", 40);
	    
	    
	    //ForgeLevel
	    this.ForgeIronShovel  = properties.getInteger("ForgeIronShovelLevel", 10);
	    this.ForgeIronPick = properties.getInteger("ForgeIronPickLevel", 10);
	    this.ForgeIronAxe = properties.getInteger("ForgeIronAxeLevel", 10);
	    this.ForgeIronSword = properties.getInteger("ForgeIronSwordLevel", 10);
	    
	    this.ForgeStoneSword = properties.getInteger("ForgeStoneSwordLevel", 5);
	    this.ForgeStoneShovel = properties.getInteger("ForgeStoneShovelLevel", 5);
	    this.ForgeStonePick = properties.getInteger("ForgeStonePickLevel", 5);
	    this.ForgeStoneAxe = properties.getInteger("ForgeStoneAxeLevel", 5);
	    
	    this.ForgeDiamondSword = properties.getInteger("ForgeDiamondSwordLevel", 30);
	    this.ForgeDiamondShovel = properties.getInteger("ForgeDiamondShovelLevel", 30);
	    this.ForgeDiamondPick = properties.getInteger("ForgeDiamondPickLevel", 30);
	    this.ForgeDiamondAxe = properties.getInteger("ForgeDiamondAxeLevel", 30);
	    
	    this.ForgeGoldSword = properties.getInteger("ForgeGoldSwordLevel", 20);
	    this.ForgeGoldShovel = properties.getInteger("ForgeGoldShovelLevel", 20);
	    this.ForgeGoldPick = properties.getInteger("ForgeGoldPickLevel", 20);
	    this.ForgeGoldAxe = properties.getInteger("ForgeGoldAxeLevel", 20);
	    
	    this.ForgeStoneHoe = properties.getInteger("ForgeStoneHoeLevel", 5);
	    this.ForgeIronHoe = properties.getInteger("ForgeIronHoeLevel", 10);
	    this.ForgeDiamondHoe = properties.getInteger("ForgeDiamondHoeLevel", 30);
	    this.ForgeGoldHoe = properties.getInteger("ForgeGoldHoeLevel", 20);
	    
	    this.ForgeChainHelm = properties.getInteger("ForgeChainHelmLevel", 5);
	    this.ForgeChainChest = properties.getInteger("ForgeChainChestLevel", 10);
	    this.ForgeChainLeg = properties.getInteger("ForgeChainLegLevel", 8);
	    this.ForgeChainBoot = properties.getInteger("ForgeChainBootLevel", 5);
	    
	    this.ForgeIronHelm = properties.getInteger("ForgeIronHelmLevel", 15);
	    this.ForgeIronChest = properties.getInteger("ForgeIronChestLevel", 20);
	    this.ForgeIronLeg = properties.getInteger("ForgeIronLegLevel", 18);
	    this.ForgeIronBoot = properties.getInteger("ForgeIronBootLevel", 15);
	    
	    this.ForgeDiamondHelm = properties.getInteger("ForgeDiamondHelmLevel", 35);
	    this.ForgeDiamondChest = properties.getInteger("ForgeDiamondChestLevel", 40);
	    this.ForgeDiamondLeg = properties.getInteger("ForgeDiamondLegLevel", 38);
	    this.ForgeDiamondBoot = properties.getInteger("ForgeDiamondBootLevel", 35);
	    
	    this.ForgeGoldHelm = properties.getInteger("ForgeGoldHelmLevel", 25);
	    this.ForgeGoldChest = properties.getInteger("ForgeGoldChestLevel", 30);
	    this.ForgeGoldLeg = properties.getInteger("ForgeGoldLegLevel", 28);
	    this.ForgeGoldBoot = properties.getInteger("ForgeGoldBootLevel", 25);
	  //ForgeExpPer
	    this.ForgeExpIronShovel  = properties.getDouble("ExpPerForgeIronShovelLevel", 15);
	    this.ForgeExpIronPick = properties.getDouble("ExpPerForgeIronPickLevel", 15);
	    this.ForgeExpIronAxe = properties.getDouble("ExpPerForgeIronAxeLevel", 15);
	    this.ForgeExpIronSword = properties.getDouble("ExpPerForgeIronSwordLevel", 15);
	    
	    this.ForgeExpStoneSword = properties.getDouble("ExpPerForgeStoneSwordLevel", 5);
	    this.ForgeExpStoneShovel = properties.getDouble("ExpPerForgeStoneShovelLevel", 5);
	    this.ForgeExpStonePick = properties.getDouble("ExpPerForgeStonePickLevel", 5);
	    this.ForgeExpStoneAxe = properties.getDouble("ExpPerForgeStoneAxeLevel", 5);
	    
	    this.ForgeExpDiamondSword = properties.getDouble("ExpPerForgeDiamondSwordLevel", 50);
	    this.ForgeExpDiamondShovel = properties.getDouble("ExpPerForgeDiamondShovelLevel", 50);
	    this.ForgeExpDiamondPick = properties.getDouble("ExpPerForgeDiamondPickLevel", 50);
	    this.ForgeExpDiamondAxe = properties.getDouble("ExpPerForgeDiamondAxeLevel", 50);
	    
	    this.ForgeExpGoldSword = properties.getDouble("ExpPerForgeGoldSwordLevel", 40);
	    this.ForgeExpGoldShovel = properties.getDouble("ExpPerForgeGoldShovelLevel", 40);
	    this.ForgeExpGoldPick = properties.getDouble("ExpPerForgeGoldPickLevel", 40);
	    this.ForgeExpGoldAxe = properties.getDouble("ExpPerForgeGoldAxeLevel", 40);
	    
	    this.ForgeExpStoneHoe = properties.getDouble("ExpPerForgeStoneHoeLevel", 5);
	    this.ForgeExpIronHoe = properties.getDouble("ExpPerForgeIronHoeLevel", 15);
	    this.ForgeExpDiamondHoe = properties.getDouble("ExpPerForgeDiamondHoeLevel", 50);
	    this.ForgeExpGoldHoe = properties.getDouble("ExpPerForgeGoldHoeLevel", 40);
	    
	    this.ForgeExpChainHelm = properties.getDouble("ExpPerForgeChainHelmLevel", 5);
	    this.ForgeExpChainChest = properties.getDouble("ExpPerForgeChainChestLevel", 10);
	    this.ForgeExpChainLeg = properties.getDouble("ExpPerForgeChainLegLevel", 8);
	    this.ForgeExpChainBoot = properties.getDouble("ExpPerForgeChainBootLevel", 5);
	    
	    this.ForgeExpIronHelm = properties.getDouble("ExpPerForgeIronHelmLevel", 15);
	    this.ForgeExpIronChest = properties.getDouble("ExpPerForgeIronChestLevel", 20);
	    this.ForgeExpIronLeg = properties.getDouble("ExpPerForgeIronLegLevel", 18);
	    this.ForgeExpIronBoot = properties.getDouble("ExpPerForgeIronBootLevel", 15);
	    
	    this.ForgeExpDiamondHelm = properties.getDouble("ExpPerForgeDiamondHelmLevel", 50);
	    this.ForgeExpDiamondChest = properties.getDouble("ExpPerForgeDiamondChestLevel", 60);
	    this.ForgeExpDiamondLeg = properties.getDouble("ExpPerForgeDiamondLegLevel", 55);
	    this.ForgeExpDiamondBoot = properties.getDouble("ExpPerForgeDiamondBootLevel", 50);
	    
	    this.ForgeExpGoldHelm = properties.getDouble("ExpPerForgeGoldHelmLevel", 40);
	    this.ForgeExpGoldChest = properties.getDouble("ExpPerForgeGoldChestLevel", 50);
	    this.ForgeExpGoldLeg = properties.getDouble("ExpPerForgeGoldLegLevel", 45);
	    this.ForgeExpGoldBoot = properties.getDouble("ExpPerForgeGoldBootLevel", 40);
	    
	}
}
