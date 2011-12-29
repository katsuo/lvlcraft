package me.l5d.lcprayer;
import java.util.Random;

import me.l5d.lcprayer.PlayerCache.PrayerSelect;
import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

//LevelCraft BlockListener Handles the Events.
public class LCEntityListener extends EntityListener {
	public LCPrayer plugin;
	public Random rand;

	public LCEntityListener(LCPrayer instance) {
		plugin = instance;
		rand = new Random();
	}
	
	public void onEntityDamage(EntityDamageEvent event) {
		//If the event isCancelled don't bother and return.
		//if (event.isCancelled()) return;  //If no pvp area, you can't heal and other bless other
		
		//if the event is in a NonLC world don't bother and return.
		Entity entity =  event.getEntity();
		if(!(entity instanceof Player)) return;
		Player player = (Player)entity;
		PlayerCache playerCache = plugin.playerCacheManager.getPlayerCache(player);
		
		//If the player dosn't have the effects of this level. Don't bother and return.
		if(!Whitelist.hasLevel(player, plugin.thisPlug)) return;
		//if the event is in a NonLC world don't bother and return.
		if(!Whitelist.worldCheck(player.getWorld()))return;
		
        //Protect !
        if(playerCache.isProtected()) event.setCancelled(true);
		
        //Heal player
        if (event instanceof EntityDamageByEntityEvent){
        	EntityDamageByEntityEvent eventDbE = (EntityDamageByEntityEvent) event;

        	if(eventDbE.getDamager() instanceof Player){
        		Player priest = (Player) eventDbE.getDamager();
        		PlayerCache prayerCache = plugin.playerCacheManager.getPlayerCache(priest);
                int level = LevelFunctions.getLevel(priest, plugin.thisPlug);

                if (priest.isSneaking() || !plugin.LCConfiguration.SneakToBless){
                
        		if(priest.getItemInHand().getTypeId() == plugin.LCConfiguration.itemPrayer){
        			event.setCancelled(true);
	        		if(prayerCache.getActivePerk() == PrayerSelect.HEALER && level>=plugin.LCConfiguration.HealerNeedLevel)
        				if( prayerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.HealerCost)){
        					int life = Math.min(player.getHealth()+level*plugin.LCConfiguration.HealByLevel,20);
        					LCChat.info(player, "Heal "+(life-player.getHealth())/2+" Hearts");
        					LCChat.info(priest, "Healed target "+(life-player.getHealth())/2+" Hearts");
        					LCChat.info(priest, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+prayerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
		                	player.setHealth( Math.min(level*plugin.LCConfiguration.HealByLevel+player.getHealth(),20));
		    				LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByHeal);
							//LCChat.info(player, "Heal "+life/2+" hearths");
        				}
        				else LCChat.warn(priest ,plugin.LCConfiguration.NoEnoughPP);
	        		else if(prayerCache.getActivePerk() == PrayerSelect.PROTECTER && level>=plugin.LCConfiguration.ProtecterNeedLevel)
	        			if( prayerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.ProtecterCost)){
		        			int protectTime = (int)(level*plugin.LCConfiguration.timeProtectByLevel);
		        			LCChat.info(player, "Protected "+protectTime+" seconds");
		        			LCChat.info(priest, "Protected target "+protectTime+" seconds");
        					LCChat.info(priest, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+prayerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
		        			playerCache.startProtectTime(protectTime);
		    				LevelFunctions.addExp(priest, plugin, plugin.LCConfiguration.ExpByProtect);
	        			}
	        			else LCChat.warn(priest, plugin.LCConfiguration.NoEnoughPP);
	        		//BOOSTER
	        		/*else if(prayerCache.getActivePerk() == PrayerSelect.BOOSTC && level>=plugin.LCConfiguration.BoosterNeedLevel)
        				if(prayerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.BoostCost)){
		        			playerCache.startCombatTime(level*plugin.LCConfiguration.timeBoostCombatByLevel);
		    				LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBoost);
        				}
		    			else LCChat.warn(priest, plugin.LCConfiguration.NoEnoughPP);	
	        		else if(prayerCache.getActivePerk() == PrayerSelect.BOOSTR && level>=plugin.LCConfiguration.BoosterNeedLevel)
	        			if(prayerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.BoostCost)){
		        			playerCache.startRangeTime(level*plugin.LCConfiguration.timeBoostRangeByLevel);
		    				LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBoost);
	        			}
	    				else LCChat.warn(priest, plugin.LCConfiguration.NoEnoughPP);
	        		else if(prayerCache.getActivePerk() == PrayerSelect.BOOSTD && level>=plugin.LCConfiguration.BoosterNeedLevel)
	        			if(prayerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.BoostCost)){
		        			playerCache.startDefTime(level*plugin.LCConfiguration.timeBoostDefenseByLevel);
		    				LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBoost);
	        			}
	    				else LCChat.warn(priest, plugin.LCConfiguration.NoEnoughPP);*/
        			}
                }
        	}
        }  
	}
	
}
