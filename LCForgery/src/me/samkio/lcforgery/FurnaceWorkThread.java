package me.samkio.lcforgery;

import java.lang.reflect.Field;

import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;
import net.minecraft.server.ContainerFurnace;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.PlayerInventory;;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntityFurnace;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryListener;

public class FurnaceWorkThread implements Runnable {
	private CraftPlayer craftPlayer;
	private EntityPlayer entityPlayer;
	private LCForgery plugin;
	private int id;
	private int level;
	private PlayerInventory inventory;

	public FurnaceWorkThread(Player p, LCForgery plugin,int level) {
		this.craftPlayer = (CraftPlayer) p;
		this.entityPlayer = craftPlayer.getHandle();
		this.plugin = plugin;
		this.inventory = entityPlayer.inventory; // get its inventory.
        this.level = level;
	}

	public void addID(int id) {
		this.id = id;
	}


	
	public void run() {
		
		if (entityPlayer == null) {
			kill();
			return;
		}
		ContainerFurnace furnace = null;
		try {
			furnace = (ContainerFurnace) entityPlayer.activeContainer;
		} catch (Exception ex) {
			kill();
			return;
		}
		Field privateTileEntity;
		try {
			privateTileEntity = ContainerFurnace.class.getDeclaredField("a");
		} catch (Exception ex) {

			return;
		}
		privateTileEntity.setAccessible(true);
		TileEntityFurnace tileEntity;

		try {
			tileEntity = (TileEntityFurnace) privateTileEntity.get(furnace);
		} catch (Exception ex) {
			return;
		}

		ItemStack[] Furnace = tileEntity.getContents();
       
        if( plugin.playerListener.previousResult.containsKey(id)){

       
        ItemStack[] Prev = plugin.playerListener.previousResult.get(id);
        if(Furnace[2]!=Prev[2] && Furnace[2]==null){ 
        	int result = Prev[2].id;
        	//int level = LevelFunctions.getLevel(craftPlayer, plugin.thisPlug);
        	if(result==336 && level >= plugin.LCConfiguration.ClayLevel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ExpPerClay * Prev[2].count));
			}else if(result==265&& level >= plugin.LCConfiguration.IronBarLevel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ExpPerIronBar * Prev[2].count));

			}else if(result==266 && level >= plugin.LCConfiguration.GoldBarLevel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ExpPerGoldBar * Prev[2].count));

			}else if(result==20 && level >= plugin.LCConfiguration.GlassLevel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ExpPerGlass * Prev[2].count));

			}else if(result==1 && level >= plugin.LCConfiguration.StoneLevel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ExpPerStone * Prev[2].count));

			}else if(result==256 && level >= plugin.LCConfiguration.RepairIronShovel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronShovel * Prev[2].count));

			}else if(result==257 && level >= plugin.LCConfiguration.RepairIronPick){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronPick * Prev[2].count));

			}else if(result==258 && level >= plugin.LCConfiguration.RepairIronAxe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronAxe * Prev[2].count));

			}else if(result==267 && level >= plugin.LCConfiguration.RepairIronSword){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronSword* Prev[2].count));

			}else if(result==272 && level >= plugin.LCConfiguration.RepairStoneSword){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpStoneSword * Prev[2].count));

			}else if(result==273 && level >= plugin.LCConfiguration.RepairStoneShovel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpStoneShovel * Prev[2].count));

			}else if(result==274 && level >= plugin.LCConfiguration.RepairStonePick){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpStonePick * Prev[2].count));

			}else if(result==275 && level >= plugin.LCConfiguration.RepairStoneAxe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpStoneAxe * Prev[2].count));

			}else if(result==276 && level >= plugin.LCConfiguration.RepairDiamondSword){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondSword * Prev[2].count));

			}else if(result==277 && level >= plugin.LCConfiguration.RepairDiamondShovel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondShovel * Prev[2].count));

			}else if(result==278 && level >= plugin.LCConfiguration.RepairDiamondPick){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondPick * Prev[2].count));

			}else if(result==279 && level >= plugin.LCConfiguration.RepairDiamondAxe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondAxe * Prev[2].count));

			}else if(result==283 && level >= plugin.LCConfiguration.RepairGoldSword){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldSword * Prev[2].count));

			}else if(result==284 && level >= plugin.LCConfiguration.RepairGoldShovel){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldShovel * Prev[2].count));

			}else if(result==285 && level >= plugin.LCConfiguration.RepairGoldPick){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldPick * Prev[2].count));

			}else if(result==286 && level >= plugin.LCConfiguration.RepairGoldAxe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldAxe * Prev[2].count));

			}else if(result==291 && level >= plugin.LCConfiguration.RepairStoneHoe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpStoneHoe * Prev[2].count));

			}else if(result==292 && level >= plugin.LCConfiguration.RepairIronHoe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronHoe * Prev[2].count));

			}else if(result==293 && level >= plugin.LCConfiguration.RepairDiamondHoe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondHoe * Prev[2].count));

			}else if(result==294 && level >= plugin.LCConfiguration.RepairGoldHoe){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldHoe * Prev[2].count));

			}else if(result==302 && level >= plugin.LCConfiguration.RepairChainHelm){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpChainHelm * Prev[2].count));

			}else if(result==303 && level >= plugin.LCConfiguration.RepairChainChest){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpChainChest * Prev[2].count));

			}else if(result==304 && level >= plugin.LCConfiguration.RepairChainLeg){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpChainLeg * Prev[2].count));

			}else if(result==305 && level >= plugin.LCConfiguration.RepairChainBoot){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpChainBoot * Prev[2].count));

			}else if(result==306 && level >= plugin.LCConfiguration.RepairIronHelm){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronHelm * Prev[2].count));

			}else if(result==307 && level >= plugin.LCConfiguration.RepairIronChest){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronChest * Prev[2].count));

			}else if(result==308 && level >= plugin.LCConfiguration.RepairIronLeg){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronLeg * Prev[2].count));

			}else if(result==309 && level >= plugin.LCConfiguration.RepairIronBoot){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpIronBoot * Prev[2].count));

			}else if(result==310 && level >= plugin.LCConfiguration.RepairDiamondHelm){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondHelm * Prev[2].count));

			}else if(result==311 && level >= plugin.LCConfiguration.RepairDiamondChest){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondChest * Prev[2].count));

			}else if(result==312 && level >= plugin.LCConfiguration.RepairDiamondLeg){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondLeg * Prev[2].count));

			}else if(result==313 && level >= plugin.LCConfiguration.RepairDiamondBoot){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpDiamondBoot * Prev[2].count));

			}else if(result==314 && level >= plugin.LCConfiguration.RepairGoldHelm){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldHelm * Prev[2].count));

			}else if(result==315 && level >= plugin.LCConfiguration.RepairGoldChest){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldChest* Prev[2].count));

			}else if(result==316 && level >= plugin.LCConfiguration.RepairGoldLeg){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldLeg * Prev[2].count));

			}else if(result==317 && level >= plugin.LCConfiguration.RepairGoldBoot){
				LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.RepairExpGoldBoot * Prev[2].count));

			}
        }
        
       // if((Furnace[0]!=Prev[0] && Prev[0]==null) || (Furnace[0]!=Prev[0] && Prev[0]==null )){
        	if(Furnace[0] != null){
        	int ingredient = Furnace[0].id;
			//int level = LevelFunctions.getLevel(craftPlayer, plugin.thisPlug);
			if(ingredient==337 && level < plugin.LCConfiguration.ClayLevel){
				LCChat.warn(craftPlayer, "Cannot Forge Clay - Required level: "+plugin.LCConfiguration.ClayLevel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==15&& level < plugin.LCConfiguration.IronBarLevel){
				LCChat.warn(craftPlayer, "Cannot Forge IronBar - Required level: "+plugin.LCConfiguration.IronBarLevel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==14 && level < plugin.LCConfiguration.GoldBarLevel){
				LCChat.warn(craftPlayer, "Cannot Forge GoldBar - Required level: "+plugin.LCConfiguration.GoldBarLevel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==12 && level < plugin.LCConfiguration.GlassLevel){
				LCChat.warn(craftPlayer, "Cannot Forge Glass - Required level: "+plugin.LCConfiguration.GlassLevel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==4 && level < plugin.LCConfiguration.StoneLevel){
				LCChat.warn(craftPlayer, "Cannot Forge Stone - Required level: "+plugin.LCConfiguration.StoneLevel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==256 && level < plugin.LCConfiguration.RepairIronShovel){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Shovel - Required level: "+plugin.LCConfiguration.RepairIronShovel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==257 && level < plugin.LCConfiguration.RepairIronPick){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Pickaxe - Required level: "+plugin.LCConfiguration.RepairIronPick);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==258 && level < plugin.LCConfiguration.RepairIronAxe){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Axe - Required level: "+plugin.LCConfiguration.RepairIronAxe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==267 && level < plugin.LCConfiguration.RepairIronSword){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Sword - Required level: "+plugin.LCConfiguration.RepairIronSword);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==272 && level < plugin.LCConfiguration.RepairStoneSword){
				LCChat.warn(craftPlayer, "Cannot Repair Stone Sword - Required level: "+plugin.LCConfiguration.RepairStoneSword);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==273 && level < plugin.LCConfiguration.RepairStoneShovel){
				LCChat.warn(craftPlayer, "Cannot Repair Stone Shovel - Required level: "+plugin.LCConfiguration.RepairStoneShovel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==274 && level < plugin.LCConfiguration.RepairStonePick){
				LCChat.warn(craftPlayer, "Cannot Repair Stone Pickaxe - Required level: "+plugin.LCConfiguration.RepairStonePick);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==275 && level < plugin.LCConfiguration.RepairStoneAxe){
				LCChat.warn(craftPlayer, "Cannot Repair Stone Axe - Required level: "+plugin.LCConfiguration.RepairStoneAxe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==276 && level < plugin.LCConfiguration.RepairDiamondSword){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Sword - Required level: "+plugin.LCConfiguration.RepairDiamondSword);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==277 && level < plugin.LCConfiguration.RepairDiamondShovel){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Shovel - Required level: "+plugin.LCConfiguration.RepairDiamondShovel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==278 && level < plugin.LCConfiguration.RepairDiamondPick){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Pickaxe - Required level: "+plugin.LCConfiguration.RepairDiamondPick);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==279 && level < plugin.LCConfiguration.RepairDiamondAxe){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Axe - Required level: "+plugin.LCConfiguration.RepairDiamondAxe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==283 && level < plugin.LCConfiguration.RepairGoldSword){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Sword - Required level: "+plugin.LCConfiguration.RepairGoldSword);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==284 && level < plugin.LCConfiguration.RepairGoldShovel){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Shovel - Required level: "+plugin.LCConfiguration.RepairGoldShovel);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==285 && level < plugin.LCConfiguration.RepairGoldPick){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Pickaxe - Required level: "+plugin.LCConfiguration.RepairGoldPick);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==286 && level < plugin.LCConfiguration.RepairGoldAxe){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Axe - Required level: "+plugin.LCConfiguration.RepairGoldAxe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==291 && level < plugin.LCConfiguration.RepairStoneHoe){
				LCChat.warn(craftPlayer, "Cannot Repair Stone Hoe - Required level: "+plugin.LCConfiguration.RepairStoneHoe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==292 && level < plugin.LCConfiguration.RepairIronHoe){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Hoe - Required level: "+plugin.LCConfiguration.RepairIronHoe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==293 && level < plugin.LCConfiguration.RepairDiamondHoe){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Hoe - Required level: "+plugin.LCConfiguration.RepairDiamondHoe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==294 && level < plugin.LCConfiguration.RepairGoldHoe){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Hoe - Required level: "+plugin.LCConfiguration.RepairGoldHoe);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==302 && level < plugin.LCConfiguration.RepairChainHelm){
				LCChat.warn(craftPlayer, "Cannot Repair Chainmail Helmet - Required level: "+plugin.LCConfiguration.RepairChainHelm);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==303 && level < plugin.LCConfiguration.RepairChainChest){
				LCChat.warn(craftPlayer, "Cannot Repair Chainmail Chestplate - Required level: "+plugin.LCConfiguration.RepairChainChest);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==304 && level < plugin.LCConfiguration.RepairChainLeg){
				LCChat.warn(craftPlayer, "Cannot Repair Chainmail Leggings - Required level: "+plugin.LCConfiguration.RepairChainLeg);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==305 && level < plugin.LCConfiguration.RepairChainBoot){
				LCChat.warn(craftPlayer, "Cannot Repair Chainmail Boots - Required level: "+plugin.LCConfiguration.RepairChainBoot);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==306 && level < plugin.LCConfiguration.RepairIronHelm){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Helmet - Required level: "+plugin.LCConfiguration.RepairIronHelm);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==307 && level < plugin.LCConfiguration.RepairIronChest){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Chestplate - Required level: "+plugin.LCConfiguration.RepairIronChest);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==308 && level < plugin.LCConfiguration.RepairIronLeg){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Leggings - Required level: "+plugin.LCConfiguration.RepairIronLeg);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==309 && level < plugin.LCConfiguration.RepairIronBoot){
				LCChat.warn(craftPlayer, "Cannot Repair Iron Boots - Required level: "+plugin.LCConfiguration.RepairIronBoot);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==310 && level < plugin.LCConfiguration.RepairDiamondHelm){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Helmet - Required level: "+plugin.LCConfiguration.RepairDiamondHelm);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==311 && level < plugin.LCConfiguration.RepairDiamondChest){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Chestplate - Required level: "+plugin.LCConfiguration.RepairDiamondChest);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==312 && level < plugin.LCConfiguration.RepairDiamondLeg){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Leggings - Required level: "+plugin.LCConfiguration.RepairDiamondLeg);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==313 && level < plugin.LCConfiguration.RepairDiamondBoot){
				LCChat.warn(craftPlayer, "Cannot Repair Diamond Boots - Required level: "+plugin.LCConfiguration.RepairDiamondBoot);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==314 && level < plugin.LCConfiguration.RepairGoldHelm){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Helmet - Required level: "+plugin.LCConfiguration.RepairGoldHelm);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==315 && level < plugin.LCConfiguration.RepairGoldChest){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Chestplate - Required level: "+plugin.LCConfiguration.RepairGoldChest);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==316 && level < plugin.LCConfiguration.RepairGoldLeg){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Leggings - Required level: "+plugin.LCConfiguration.RepairGoldLeg);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}else if(ingredient==317 && level < plugin.LCConfiguration.RepairGoldBoot){
				LCChat.warn(craftPlayer, "Cannot Repair Gold Boots - Required level: "+plugin.LCConfiguration.RepairGoldBoot);
				resetIngredient(craftPlayer,tileEntity,Furnace[0]);
			}
        }
        }
		
		
	
		ItemStack[] Current = {Furnace[0],Furnace[1],Furnace[2], InventoryListener()};
		plugin.playerListener.previousResult.put(id, Current);
	
		if (!craftPlayer.isOnline())
			kill();
	}

	
	private ItemStack InventoryListener() {
		// TODO Auto-generated method stub
		return null;
	}

	public void kill() {
		plugin.getServer().getScheduler().cancelTask(id);
		plugin.playerListener.previousResult.remove(id);
		int index = plugin.playerListener.tasks.indexOf(id);
		if (index != -1)
			plugin.playerListener.tasks.remove(plugin.playerListener.tasks.indexOf(id));
		
	}
	public void resetIngredient(CraftPlayer p,TileEntityFurnace t,ItemStack F){
		if(p.getInventory().firstEmpty()==-1){
	        p.getWorld().dropItem(p.getLocation(),new org.bukkit.inventory.ItemStack(F.id, F.count));
		}else{
		p.getInventory().setItem(p.getInventory().firstEmpty(),(org.bukkit.inventory.ItemStack) new org.bukkit.inventory.ItemStack(F.id,F.count));
		}
		t.setItem(0, null);
		return;
	}
}
