package me.wesnc.lcbuilding;

import java.io.IOException;
import java.util.logging.Level;
import me.samkio.levelcraftcore.util.Properties;

public class LCConfiguration
{
  public LCBuilding plugin;
  double PLACED_DIRT_NONGRASSED = 1.0D;
  double PLACED_DIRT_GRASSED = 1.0D;

  double PLACED_STONE = 2.0D;
  double PLACED_COBBLESTONE = 2.0D;
  double PLACED_MOSSY_COBBLESTONE = 2.0D;

  double PLACED_STAIRS = 2.0D;

  double PLACED_HALFBLOCK = 2.0D;

  double PLACED_BOOKCASE = 3.0D;

  double PLACED_SNOWBLOCKS = 2.0D;

  double PLACED_PUMPKINS = 2.0D;
  double PLACED_FURNACE = 2.0D;
  double PLACED_PLANK = 2.0D;
  double PLACED_TRUNK_REG = 2.0D;
  double PLACED_TRUNK_RED = 2.0D;
  double PLACED_TRUNK_BIRCH = 2.0D;

  double PLACED_SAND = 2.0D;
  double PLACED_SANDSTONE = 2.0D;

  double PLACED_GLASS = 6.0D;

  double PLACED_ORE_GOLD = 5.0D;
  double PLACED_ORE_IRON = 4.0D;

  double PLACED_DIAMOND = 6.0D;
  double PLACED_IRON = 4.0D;
  double PLACED_GOLD = 4.0D;

  double PLACED_OBSIDIAN = 300.0D;

  double PLACED_CRAFTING_BENCH = 2.0D;
  double PLACED_CRAFTING_FORGE = 3.0D;
  double PLACED_CRAFTING_CHEST = 2.0D;

  double PLACED_BRICK = 4.0D;

  double PLACED_NETHER_NETHERRACK = 8.0D;
  double PLACED_NETHER_SOULSAND = 6.0D;
  double PLACED_NETHER_GLOWSTONE = 4.0D;

  double PLACED_LADDER = 4.0D;
  double PLACED_WOOL = 2.0D;
  
  double PLACED_FENCE = 3.0D;
  double PLACED_FENCE_GATE = 4.0D;
  double PLACED_TRAP = 4.0D;
  
  double PLACED_SMOOTH_BRICK = 2.0D;
  double PLACED_GLASS_PANEL = 4.0D;
  double PLACED_LAPIS_BLOCK = 5.0D;
  
  double PLACED_PISTON = 2.0D;
  double PLACED_STICKY_PISTON = 3.0D;
  double PLACED_RAILS = 2.0D;
  
  double PLACED_BOOSTER_RAIL = 3.0D;
  double PLACED_DETECTOR_RAIL = 4.0D;
  
  double PLACED_NETHER_FENCE = 3.0D;
  double PLACED_NETHER_BRICK = 2.0D;
  
  double PLACED_TORCH = 1.0D;
  double PLACED_SIGN = 1.0D;
  double PLACED_TREE_SEED = 1.0D;
  double PLACED_WOOD_DOOR = 1.0D;
  double PLACED_CLAY_BLOCK = 1.0D;
  double PLACED_BED = 1.0D;
  double PLACED_CAKE = 1.0D;
  double PLACED_MELON = 1.0D;
  double PLACED_BREW_STAND = 1.0D;
  double PLACED_CHAULDRON = 1.0D;
  double PLACED_BOTTOM = 1.0D;
  double PLACED_METAL_FENCE = 1.0D;
  double PLACED_LEAF = 1.0D;
  double PLACED_LEVER = 1.0D;
  double PLACED_DISPENSER = 1.0D;
  double PLACED_STONE_PLATE = 1.0D;
  double PLACED_WOOD_PLATE = 1.0D;
  double PLACED_BROWN_CHAMP = 1.0D;
  double PLACED_RED_CHAMP = 1.0D;
  double PLACED_CANE = 1.0D;
  double PLACED_WEB = 1.0D;
  double PLACED_NOTE = 1.0D;
  double PLACED_JUKE = 1.0D;
  double PLACED_RED_TORCH = 1.0D;
  double PLACED_YELLOW_FLOWER = 1.0D;
  double PLACED_RED_FLOWER =1.0D;
  double PLACED_LILY = 1.0D;
  double PLACED_VINE = 1.0D;
  double PLACED_END_PORTAL = 1.0D;
  double PLACED_REDSTONE = 1.0D;
  double PLACED_DELAY = 1.0D;
  double PLACED_ENCHANT = 1.0D;
  double PLACED_TNT = 1.0;
  double PLACED_PAINT = 1.0D;
  
  
  
  
  int LEVELNEEDEDFOR_TIER0 = 1;
  int LEVELNEEDEDFOR_TIER1 = 10;
  int LEVELNEEDEDFOR_TIER2 = 20;
  int LEVELNEEDEDFOR_TIER2point5 = 26;
  int LEVELNEEDEDFOR_TIER3 = 40;
  int LEVELNEEDEDFOR_TIER4 = 60;
  int LEVELNEEDEDFOR_TIER5 = 80;

  final String LEVELNEEDEDFOR_TIER1_MESSAGE = "Cannot place this block, Level needed " + this.LEVELNEEDEDFOR_TIER1 + ". That item is Tier 1.";
  final String LEVELNEEDEDFOR_TIER2_MESSAGE = "Cannot place this block, Level needed " + this.LEVELNEEDEDFOR_TIER2 + ". That item is Tier 2.";
  final String LEVELNEEDEDFOR_TIER2point5_MESSAGE = "Cannot place this block, Level needed " + this.LEVELNEEDEDFOR_TIER2point5 + ". That item is Tier 2.5.";
  final String LEVELNEEDEDFOR_TIER3_MESSAGE = "Cannot place this block, Level needed " + this.LEVELNEEDEDFOR_TIER3 + ". That item is Tier 3.";
  final String LEVELNEEDEDFOR_TIER4_MESSAGE = "Cannot place this block, Level needed " + this.LEVELNEEDEDFOR_TIER4 + ". That item is Tier 4.";
  final String LEVELNEEDEDFOR_TIER5_MESSAGE = "Cannot place this block, Level needed " + this.LEVELNEEDEDFOR_TIER5 + ". That item is Tier 5.";

  public LCConfiguration(LCBuilding instance)
  {
    this.plugin = instance;
  }

  public void loadConfig()
  {
    Properties properties = new Properties(this.plugin.CFGFileString);
    try
    {
      properties.load(); } catch (IOException ex) {
      this.plugin.logger.log(Level.SEVERE, "[LC] " + ex);
    }
    this.PLACED_DIRT_NONGRASSED = properties.getDouble("PLACED_DIRT_NONGRASSED", 1.0D);
    this.PLACED_DIRT_GRASSED = properties.getDouble("PLACED_DIRT_GRASSED", 1.0D);

    this.PLACED_STONE = properties.getDouble("PLACED_STONE", 2.0D);
    this.PLACED_COBBLESTONE = properties.getDouble("PLACED_COBBLESTONE", 2.0D);
    this.PLACED_MOSSY_COBBLESTONE = properties.getDouble("PLACED_MOSSY_COBBLESTONE", 2.0D);

    this.PLACED_STAIRS = properties.getDouble("PLACED_STAIRS", 2.0D);

    this.PLACED_HALFBLOCK = properties.getDouble("PLACED_HALFBLOCK", 2.0D);

    this.PLACED_BOOKCASE = properties.getDouble("PLACED_BOOKCASE", 3.0D);

    this.PLACED_SNOWBLOCKS = properties.getDouble("PLACED_SNOWBLOCKS", 2.0D);

    this.PLACED_PUMPKINS = properties.getDouble("PLACED_PUMPKINS", 2.0D);

    this.PLACED_PLANK = properties.getDouble("PLACED_PLANK", 2.0D);
    this.PLACED_TRUNK_REG = properties.getDouble("PLACED_TRUCK_REG", 2.0D);
    this.PLACED_TRUNK_RED = properties.getDouble("PLACED_TRUCK_RED", 2.0D);
    this.PLACED_TRUNK_BIRCH = properties.getDouble("PLACED_TRUCK_BIRCH", 2.0D);

    this.PLACED_SAND = properties.getDouble("PLACED_SAND", 2.0D);
    this.PLACED_SANDSTONE = properties.getDouble("PLACED_SANDSTONE", 2.0D);

    this.PLACED_GLASS = properties.getDouble("PLACED_GLASS", 6.0D);

    this.PLACED_ORE_GOLD = properties.getDouble("PLACED_ORE_GOLD", 5.0D);
    this.PLACED_ORE_IRON = properties.getDouble("PLACED_ORE_IRON", 4.0D);

    this.PLACED_DIAMOND = properties.getDouble("PLACED_DIAMOND", 6.0D);
    this.PLACED_GOLD = properties.getDouble("PLACED_GOLD", 4.0D);
    this.PLACED_IRON = properties.getDouble("PLACED_IRON", 4.0D);

    this.PLACED_OBSIDIAN = properties.getDouble("PLACED_OBSIDIAN", 300.0D);

    this.PLACED_CRAFTING_BENCH = properties.getDouble("PLACED_CRAFTING_BENCH", 2.0D);
    this.PLACED_CRAFTING_FORGE = properties.getDouble("PLACED_CRAFTING_FORGE", 3.0D);
    this.PLACED_CRAFTING_CHEST = properties.getDouble("PLACED_CRAFTING_CHEST", 2.0D);

    this.PLACED_BRICK = properties.getDouble("PLACED_BRICK", 4.0D);

    this.PLACED_NETHER_NETHERRACK = properties.getDouble("PLACED_NETHER_NETHERRACK", 8.0D);
    this.PLACED_NETHER_SOULSAND = properties.getDouble("PLACED_NETHER_SOULSAND", 6.0D);
    this.PLACED_NETHER_GLOWSTONE = properties.getDouble("PLACED_NETHER_GLOWSTONE", 4.0D);

    this.PLACED_LADDER = properties.getDouble("PLACED_LADDER", 4.0D);

    this.PLACED_WOOL = properties.getDouble("PLACED_WOOL", 3.0D);
    
    this.PLACED_FENCE = properties.getDouble("PLACED_FENCE", 3.0D);
    this.PLACED_FENCE_GATE = properties.getDouble("PLACED_FENCE_GATE", 4.0D);
    this.PLACED_TRAP = properties.getDouble("PLACED_TRAP", 4.0D);
    
    this.PLACED_SMOOTH_BRICK = properties.getDouble("PLACED_SMOOTH_BRICK", 2.0D);
    this.PLACED_GLASS_PANEL = properties.getDouble("PLACED_GLASS_PANEL", 4.0D);
    this.PLACED_LAPIS_BLOCK = properties.getDouble("PLACED_LAPIS_BLOCK", 5.0D);
    
    this.PLACED_PISTON = properties.getDouble("PLACED_PISTON", 2.0D);
    this.PLACED_STICKY_PISTON = properties.getDouble("PLACED_STICKY_PISTON", 3.0D);
    this.PLACED_RAILS = properties.getDouble("PLACED_RAILS", 2.0D);
    
    this.PLACED_BOOSTER_RAIL = properties.getDouble("PLACED_BOOSTER_RAIL", 3.0D);
    this.PLACED_DETECTOR_RAIL = properties.getDouble("PLACED_DETECTOR_RAIL", 4.0D);
    
    this.PLACED_NETHER_FENCE = properties.getDouble("PLACED_NETHER_FENCE", 3.0D);
    this.PLACED_NETHER_BRICK = properties.getDouble("PLACED_NETHER_BRICK", 2.0D);
    
    this.PLACED_TORCH = properties.getDouble("PLACED_TORCH", 1.0D);
    this.PLACED_SIGN = properties.getDouble("PLACED_SIGN", 1.0D);
    this.PLACED_TREE_SEED = properties.getDouble("PLACED_TREE_SEED", 1.0D);
    this.PLACED_WOOD_DOOR = properties.getDouble("PLACED_WOOD_DOOR", 1.0D);
    this.PLACED_CLAY_BLOCK = properties.getDouble("PLACED_CLAY_BLOCK", 1.0D);
    this.PLACED_BED = properties.getDouble("PLACED_BED", 1.0D);
    this.PLACED_BOTTOM = properties.getDouble("PLACED_BOTTOM", 1.0D);
    this.PLACED_METAL_FENCE = properties.getDouble("PLACED_METAL_FENCE", 1.0D);
    this.PLACED_BOTTOM = properties.getDouble("PLACED_BOTTOM", 1.0D);
    this.PLACED_METAL_FENCE = properties.getDouble("PLACED_METAL_FENCE", 1.0D);
    this.PLACED_LEAF = properties.getDouble("PLACED_LEAF", 1.0D);
    this.PLACED_LEVER = properties.getDouble("PLACED_LEVER", 1.0D);
    this.PLACED_DISPENSER = properties.getDouble("PLACED_DISPENSER", 1.0D);
    this.PLACED_STONE_PLATE = properties.getDouble("PLACED_STONE_PLATE", 1.0D);
    this.PLACED_RED_CHAMP = properties.getDouble("PLACED_RED_CHAMP", 1.0D);
    this.PLACED_CANE = properties.getDouble("PLACED_CANE", 1.0D);
    this.PLACED_WEB = properties.getDouble("PLACED_BROWN_CHAMP", 1.0D);
    this.PLACED_NOTE = properties.getDouble("PLACED_NOTE", 1.0D);
    this.PLACED_JUKE = properties.getDouble("PLACED_JUKE", 1.0D);
    this.PLACED_RED_TORCH = properties.getDouble("PLACED_RED_TORCH", 1.0D);
    this.PLACED_YELLOW_FLOWER = properties.getDouble("PLACED_YELLOW_FLOWER", 1.0D);
    this.PLACED_RED_FLOWER = properties.getDouble("PLACED_RED_FLOWER", 1.0D);
    this.PLACED_LILY = properties.getDouble("PLACED_LILY", 1.0D);
    this.PLACED_VINE = properties.getDouble("PLACED_VINE", 1.0D);
    this.PLACED_END_PORTAL = properties.getDouble("PLACED_END_PORTAL", 1.0D);
    this.PLACED_REDSTONE = properties.getDouble("PLACED_REDSTONE", 1.0D);
    this.PLACED_DELAY = properties.getDouble("PLACED_DELAY", 1.0D);
    this.PLACED_ENCHANT = properties.getDouble("PLACED_ENCHANT", 1.0D);
    this.PLACED_TNT = properties.getDouble("PLACED_TNT", 1.0D);
    this.PLACED_PAINT = properties.getDouble("PLACED_PAINt", 1.0D);
    
    
  }
}