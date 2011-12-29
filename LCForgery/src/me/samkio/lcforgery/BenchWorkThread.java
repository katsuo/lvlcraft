package me.samkio.lcforgery;

import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;
import net.minecraft.server.ContainerWorkbench;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Packet103SetSlot;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class BenchWorkThread implements Runnable {
	private CraftPlayer craftPlayer;
	private EntityPlayer entityPlayer;
	private LCForgery plugin;
	private int id;

	public BenchWorkThread(Player p, LCForgery plugin) {
		this.craftPlayer = (CraftPlayer) p;
		this.entityPlayer = craftPlayer.getHandle();
		this.plugin = plugin;
	}

	public void addID(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		try {
			if (entityPlayer == null
					|| entityPlayer.activeContainer == entityPlayer.defaultContainer) {
				kill();
				return;
			}
			ContainerWorkbench containerBench = null;
			try {
				containerBench = (ContainerWorkbench) entityPlayer.activeContainer;
			} catch (Exception ex) {
				kill();
				return;
			}
			ItemStack Current = (ItemStack) containerBench.d.get(0);
			if (plugin.playerListener.previousResultCraft.containsKey(id)) {
				ItemStack Prev = plugin.playerListener.previousResultCraft.get(id);
				if(Prev!=Current && Prev == null){
					//craftPlayer.sendMessage("Something was crafted");
					int ingredient = Current.id;
					int level = LevelFunctions.getLevel(craftPlayer, plugin.thisPlug);
					if(ingredient==256 && level < plugin.LCConfiguration.ForgeIronShovel){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Shovel - Required level: "+plugin.LCConfiguration.ForgeIronShovel);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==257 && level < plugin.LCConfiguration.ForgeIronPick){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Pickaxe - Required level: "+plugin.LCConfiguration.ForgeIronPick);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==258 && level < plugin.LCConfiguration.ForgeIronAxe){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Axe - Required level: "+plugin.LCConfiguration.ForgeIronAxe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==267 && level < plugin.LCConfiguration.ForgeIronSword){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Sword - Required level: "+plugin.LCConfiguration.ForgeIronSword);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==272 && level < plugin.LCConfiguration.ForgeStoneSword){
						LCChat.warn(craftPlayer, "Cannot Forge Stone Sword - Required level: "+plugin.LCConfiguration.ForgeStoneSword);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==273 && level < plugin.LCConfiguration.ForgeStoneShovel){
						LCChat.warn(craftPlayer, "Cannot Forge Stone Shovel - Required level: "+plugin.LCConfiguration.ForgeStoneShovel);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==274 && level < plugin.LCConfiguration.ForgeStonePick){
						LCChat.warn(craftPlayer, "Cannot Forge Stone Pickaxe - Required level: "+plugin.LCConfiguration.ForgeStonePick);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==275 && level < plugin.LCConfiguration.ForgeStoneAxe){
						LCChat.warn(craftPlayer, "Cannot Forge Stone Axe - Required level: "+plugin.LCConfiguration.ForgeStoneAxe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==276 && level < plugin.LCConfiguration.ForgeDiamondSword){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Sword - Required level: "+plugin.LCConfiguration.ForgeDiamondSword);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==277 && level < plugin.LCConfiguration.ForgeDiamondShovel){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Shovel - Required level: "+plugin.LCConfiguration.ForgeDiamondShovel);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==278 && level < plugin.LCConfiguration.ForgeDiamondPick){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Pickaxe - Required level: "+plugin.LCConfiguration.ForgeDiamondPick);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==279 && level < plugin.LCConfiguration.ForgeDiamondAxe){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Axe - Required level: "+plugin.LCConfiguration.ForgeDiamondAxe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==283 && level < plugin.LCConfiguration.ForgeGoldSword){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Sword - Required level: "+plugin.LCConfiguration.ForgeGoldSword);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==284 && level < plugin.LCConfiguration.ForgeGoldShovel){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Shovel - Required level: "+plugin.LCConfiguration.ForgeGoldShovel);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==285 && level < plugin.LCConfiguration.ForgeGoldPick){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Pickaxe - Required level: "+plugin.LCConfiguration.ForgeGoldPick);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==286 && level < plugin.LCConfiguration.ForgeGoldAxe){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Axe - Required level: "+plugin.LCConfiguration.ForgeGoldAxe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==291 && level < plugin.LCConfiguration.ForgeStoneHoe){
						LCChat.warn(craftPlayer, "Cannot Forge Stone Hoe - Required level: "+plugin.LCConfiguration.ForgeStoneHoe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==292 && level < plugin.LCConfiguration.ForgeIronHoe){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Hoe - Required level: "+plugin.LCConfiguration.ForgeIronHoe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==293 && level < plugin.LCConfiguration.ForgeDiamondHoe){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Hoe - Required level: "+plugin.LCConfiguration.ForgeDiamondHoe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==294 && level < plugin.LCConfiguration.ForgeGoldHoe){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Hoe - Required level: "+plugin.LCConfiguration.ForgeGoldHoe);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==302 && level < plugin.LCConfiguration.ForgeChainHelm){
						LCChat.warn(craftPlayer, "Cannot Forge Chainmail Helmet - Required level: "+plugin.LCConfiguration.ForgeChainHelm);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==303 && level < plugin.LCConfiguration.ForgeChainChest){
						LCChat.warn(craftPlayer, "Cannot Forge Chainmail Chestplate - Required level: "+plugin.LCConfiguration.ForgeChainChest);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==304 && level < plugin.LCConfiguration.ForgeChainLeg){
						LCChat.warn(craftPlayer, "Cannot Forge Chainmail Leggings - Required level: "+plugin.LCConfiguration.ForgeChainLeg);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==305 && level < plugin.LCConfiguration.ForgeChainBoot){
						LCChat.warn(craftPlayer, "Cannot Forge Chainmail Boots - Required level: "+plugin.LCConfiguration.ForgeChainBoot);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==306 && level < plugin.LCConfiguration.ForgeIronHelm){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Helmet - Required level: "+plugin.LCConfiguration.ForgeIronHelm);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==307 && level < plugin.LCConfiguration.ForgeIronChest){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Chestplate - Required level: "+plugin.LCConfiguration.ForgeIronChest);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==308 && level < plugin.LCConfiguration.ForgeIronLeg){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Leggings - Required level: "+plugin.LCConfiguration.ForgeIronLeg);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==309 && level < plugin.LCConfiguration.ForgeIronBoot){
						LCChat.warn(craftPlayer, "Cannot Forge Iron Boots - Required level: "+plugin.LCConfiguration.ForgeIronBoot);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==310 && level < plugin.LCConfiguration.ForgeDiamondHelm){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Helmet - Required level: "+plugin.LCConfiguration.ForgeDiamondHelm);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==311 && level < plugin.LCConfiguration.ForgeDiamondChest){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Chestplate - Required level: "+plugin.LCConfiguration.ForgeDiamondChest);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==312 && level < plugin.LCConfiguration.ForgeDiamondLeg){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Leggings - Required level: "+plugin.LCConfiguration.ForgeDiamondLeg);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==313 && level < plugin.LCConfiguration.ForgeDiamondBoot){
						LCChat.warn(craftPlayer, "Cannot Forge Diamond Boots - Required level: "+plugin.LCConfiguration.ForgeDiamondBoot);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==314 && level < plugin.LCConfiguration.ForgeGoldHelm){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Helmet - Required level: "+plugin.LCConfiguration.ForgeGoldHelm);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==315 && level < plugin.LCConfiguration.ForgeGoldChest){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Chestplate - Required level: "+plugin.LCConfiguration.ForgeGoldChest);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==316 && level < plugin.LCConfiguration.ForgeGoldLeg){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Leggings - Required level: "+plugin.LCConfiguration.ForgeGoldLeg);
						setNull(craftPlayer,containerBench);
					}else if(ingredient==317 && level < plugin.LCConfiguration.ForgeGoldBoot){
						LCChat.warn(craftPlayer, "Cannot Forge Gold Boots - Required level: "+plugin.LCConfiguration.ForgeGoldBoot);
						setNull(craftPlayer,containerBench);
					}
				}else 
				if(Prev!=Current && Current == null){
					//craftPlayer.sendMessage("Something was taken");
					int result = Prev.id;
		        	int level = LevelFunctions.getLevel(craftPlayer, plugin.thisPlug);
		        	if(result==256 && level >= plugin.LCConfiguration.ForgeIronShovel){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronShovel * Prev.count));

					}else if(result==257 && level >= plugin.LCConfiguration.ForgeIronPick){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronPick * Prev.count));

					}else if(result==258 && level >= plugin.LCConfiguration.ForgeIronAxe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronAxe * Prev.count));

					}else if(result==267 && level >= plugin.LCConfiguration.ForgeIronSword){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronSword* Prev.count));

					}else if(result==272 && level >= plugin.LCConfiguration.ForgeStoneSword){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpStoneSword * Prev.count));

					}else if(result==273 && level >= plugin.LCConfiguration.ForgeStoneShovel){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpStoneShovel * Prev.count));

					}else if(result==274 && level >= plugin.LCConfiguration.ForgeStonePick){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpStonePick * Prev.count));

					}else if(result==275 && level >= plugin.LCConfiguration.ForgeStoneAxe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpStoneAxe * Prev.count));

					}else if(result==276 && level >= plugin.LCConfiguration.ForgeDiamondSword){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondSword * Prev.count));

					}else if(result==277 && level >= plugin.LCConfiguration.ForgeDiamondShovel){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondShovel * Prev.count));

					}else if(result==278 && level >= plugin.LCConfiguration.ForgeDiamondPick){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondPick * Prev.count));

					}else if(result==279 && level >= plugin.LCConfiguration.ForgeDiamondAxe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondAxe * Prev.count));

					}else if(result==283 && level >= plugin.LCConfiguration.ForgeGoldSword){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldSword * Prev.count));

					}else if(result==284 && level >= plugin.LCConfiguration.ForgeGoldShovel){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldShovel * Prev.count));

					}else if(result==285 && level >= plugin.LCConfiguration.ForgeGoldPick){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldPick * Prev.count));

					}else if(result==286 && level >= plugin.LCConfiguration.ForgeGoldAxe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldAxe * Prev.count));

					}else if(result==291 && level >= plugin.LCConfiguration.ForgeStoneHoe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpStoneHoe * Prev.count));

					}else if(result==292 && level >= plugin.LCConfiguration.ForgeIronHoe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronHoe * Prev.count));

					}else if(result==293 && level >= plugin.LCConfiguration.ForgeDiamondHoe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondHoe * Prev.count));

					}else if(result==294 && level >= plugin.LCConfiguration.ForgeGoldHoe){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldHoe * Prev.count));

					}else if(result==302 && level >= plugin.LCConfiguration.ForgeChainHelm){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpChainHelm * Prev.count));

					}else if(result==303 && level >= plugin.LCConfiguration.ForgeChainChest){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpChainChest * Prev.count));

					}else if(result==304 && level >= plugin.LCConfiguration.ForgeChainLeg){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpChainLeg * Prev.count));

					}else if(result==305 && level >= plugin.LCConfiguration.ForgeChainBoot){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpChainBoot * Prev.count));

					}else if(result==306 && level >= plugin.LCConfiguration.ForgeIronHelm){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronHelm * Prev.count));

					}else if(result==307 && level >= plugin.LCConfiguration.ForgeIronChest){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronChest * Prev.count));

					}else if(result==308 && level >= plugin.LCConfiguration.ForgeIronLeg){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronLeg * Prev.count));

					}else if(result==309 && level >= plugin.LCConfiguration.ForgeIronBoot){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpIronBoot * Prev.count));

					}else if(result==310 && level >= plugin.LCConfiguration.ForgeDiamondHelm){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondHelm * Prev.count));

					}else if(result==311 && level >= plugin.LCConfiguration.ForgeDiamondChest){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondChest * Prev.count));

					}else if(result==312 && level >= plugin.LCConfiguration.ForgeDiamondLeg){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondLeg * Prev.count));

					}else if(result==313 && level >= plugin.LCConfiguration.ForgeDiamondBoot){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpDiamondBoot * Prev.count));

					}else if(result==314 && level >= plugin.LCConfiguration.ForgeGoldHelm){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldHelm * Prev.count));

					}else if(result==315 && level >= plugin.LCConfiguration.ForgeGoldChest){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldChest* Prev.count));

					}else if(result==316 && level >= plugin.LCConfiguration.ForgeGoldLeg){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldLeg * Prev.count));

					}else if(result==317 && level >= plugin.LCConfiguration.ForgeGoldBoot){
						LevelFunctions.addExp(craftPlayer, plugin.thisPlug, (plugin.LCConfiguration.ForgeExpGoldBoot * Prev.count));

					}
				}
			}
			
			if (!craftPlayer.isOnline())
				kill();

			plugin.playerListener.previousResultCraft.put(id,
					(ItemStack) containerBench.d.get(0));

		} catch (Exception ex) {
			plugin.logger
					.info("[LCForgery]: Error in workbench task. Error is: "
							+ ex.getMessage() + ". Stack trace: "
							+ ex.getStackTrace()[0]);
			return;
		}
	}

	public void kill() {
		plugin.getServer().getScheduler().cancelTask(id);
		int index = plugin.playerListener.tasks.indexOf(id);
		if (index != -1)
			plugin.playerListener.tasks.remove(plugin.playerListener.tasks
					.indexOf(id));
	}
	public void setNull(CraftPlayer p,ContainerWorkbench c){
		//p.sendMessage(c.d.get(0)+"");
		//c.d.set(0, null);
		//p.sendMessage(c.d.get(0)+"");
		//c.b.a(0, (Integer) null);
		//Packet103SetSlot packet = new Packet103SetSlot(
		//		entityPlayer.activeContainer.f, 0, null);
		//entityPlayer.a.b(packet);
		
		ItemStack is = new ItemStack(0,0,0);
        if (is.id == 0)
          is = null;
        c.resultInventory.setItem(0, is);
        Packet103SetSlot packet = new Packet103SetSlot(
        this.entityPlayer.activeContainer.windowId, 0, is);
        this.entityPlayer.netServerHandler.sendPacket(packet);
		
	}
}
