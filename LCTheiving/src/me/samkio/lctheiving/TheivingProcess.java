package me.samkio.lctheiving;

import net.minecraft.server.ItemStack;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.server.ContainerPlayer;
import net.minecraft.server.InventoryPlayer;

import org.bukkit.Server;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class TheivingProcess {
	static LCTheiving plugin;
	public static Logger log = Logger.getLogger("Minecraft");
	public static Server server;
	public static PluginDescriptionFile description;
	public static HashMap<String, String> playersRobbing = new HashMap<String, String>();
	public static HashMap<String, String> playersBeingRobbed = new HashMap<String, String>();
	public static HashMap<String, Runnable> playersInInventoryDialogUpdater = new HashMap<String, Runnable>();
	public static ConcurrentHashMap<String, Integer> timer = new ConcurrentHashMap<String, Integer>();
	public static boolean tradeWithRightClick = true;
	public static final int DELAY_TIME = 15;

	public TheivingProcess(LCTheiving instance) {
		plugin = instance;
	}

	public static boolean theive(final Player attacker, final Player defender) {
		if (attacker.isSneaking()) {
				try {
					final ItemStack[] attackerInvBackup = ((CraftPlayer) attacker).getHandle().inventory.getContents().clone();			
					final ItemStack[] defenderInvBackup = ((CraftPlayer) defender).getHandle().inventory.getContents().clone();
					ItemStack[] attackerPage1 = new ItemStack[36];
					ItemStack[] defenderPage1 = new ItemStack[36];
					// only care about first page of inventory
					for (int i = 0; i < 36; i++) {
						attackerPage1[i] = attackerInvBackup[i];
						defenderPage1[i] = defenderInvBackup[i];
					}

					InventoryPlayer attackerInv = ((CraftPlayer) attacker)
							.getHandle().inventory;
					InventoryPlayer defenderInv = ((CraftPlayer) defender)
							.getHandle().inventory;
					attackerInv.items = attackerPage1;
					defenderInv.items = defenderPage1;

					((CraftPlayer) attacker).getHandle().activeContainer = new ContainerPlayer(
							((CraftPlayer) attacker).getHandle().inventory,
							!((CraftPlayer) attacker).getHandle().world.isStatic);
					((CraftPlayer) attacker).getHandle().defaultContainer = ((CraftPlayer) attacker)
							.getHandle().activeContainer;
				/*	((CraftPlayer) defender).getHandle().activeContainer = new ContainerPlayer(
							((CraftPlayer) defender).getHandle().inventory,
							!((CraftPlayer) defender).getHandle().world.isStatic);
					((CraftPlayer) defender).getHandle().defaultContainer = ((CraftPlayer) defender)
							.getHandle().activeContainer;

				*/
					//((CraftPlayer) defender).getHandle().a(attackerInv); 
	                ((CraftPlayer) attacker).getHandle().a(defenderInv);
	                Runnable restore = new Runnable() {
	                	@SuppressWarnings("deprecation")
	                	public void run() {
	                	ItemStack[] attackerInvNew = ((CraftPlayer)attacker).getHandle().inventory.items.clone();
	                	ItemStack[] defenderInvNew = ((CraftPlayer)defender).getHandle().inventory.items.clone();
	                	((CraftPlayer)attacker).getHandle().inventory.items = attackerInvBackup;
	                	((CraftPlayer)defender).getHandle().inventory.items = defenderInvBackup;
	                	for (int i = 0; i < 36; i++) {
	                	((CraftPlayer)attacker).getHandle().inventory.items[i] = attackerInvNew[i];
	                	((CraftPlayer)defender).getHandle().inventory.items[i] = defenderInvNew[i];
	                	}

	                	((CraftPlayer)attacker).getHandle().activeContainer = new ContainerPlayer(((CraftPlayer)attacker).getHandle().inventory, !((CraftPlayer)attacker).getHandle().world.isStatic);
	                	((CraftPlayer)attacker).getHandle().defaultContainer = ((CraftPlayer)attacker).getHandle().activeContainer;
	                	((CraftPlayer)defender).getHandle().activeContainer = new ContainerPlayer(((CraftPlayer)defender).getHandle().inventory, !((CraftPlayer)defender).getHandle().world.isStatic);
	                	((CraftPlayer)defender).getHandle().defaultContainer = ((CraftPlayer)defender).getHandle().activeContainer;

	                	attacker.updateInventory();
	                	defender.updateInventory();
	                	}
	                	};

					TheivingProcess.playersRobbing.put(attacker.getName(), defender.getName());
					TheivingProcess.playersRobbing.put(defender.getName(), attacker.getName());
					TheivingProcess.playersInInventoryDialogUpdater.put(attacker.getName(), restore);
					TheivingProcess.playersInInventoryDialogUpdater.put(defender.getName(), restore);
				} catch (Exception e) {
					plugin.logger.log(Level.SEVERE, "[LC]" + e);
				}
			} 
		
		return false;
	}
}
