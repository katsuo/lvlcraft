package me.l5d.lcprayer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import me.l5d.lcprayer.PlayerCache.PrayerSelect;
import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

//LevelCraft BlockListener Handles the Events.
public class LCPlayerListener extends PlayerListener {
	public LCPrayer plugin;

	public LCPlayerListener(LCPrayer instance) {
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		plugin.playerCacheManager.fromPersistant(player.getName());
	}

	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		plugin.playerCacheManager.toPersistant(player.getName());
	}
	
	public void onItemHeldChange(PlayerItemHeldEvent event){
		Player player = event.getPlayer();
		PlayerCache playerCache = plugin.playerCacheManager.getPlayerCache(player);
		
		if(player.getItemInHand().getTypeId() == plugin.LCConfiguration.itemPrayer)
			LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()));
		
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		//If the event isCancelled don't bother and return.
		//if (event.isCancelled()) return;//bug
		
		Player player = event.getPlayer();
		
		//Get cache and level
		PlayerCache playerCache = plugin.playerCacheManager.getPlayerCache(player);
		int level = LevelFunctions.getLevel(player, plugin.thisPlug);
		int pp = 0;
		
		//If the player dosn't have the effects of this level. Don't bother and return.
		if(!Whitelist.hasLevel(player, plugin.thisPlug)) return;
		//if the event is in a NonLC world don't bother and return.
		if(!Whitelist.worldCheck(player.getWorld()))return;
		
		//No item -> useless
		if(event.getItem()==null)return;
		
		//PrayerPoint
		if(event.getClickedBlock()!=null && (event.getClickedBlock().getType()==Material.DIRT || event.getClickedBlock().getType()==Material.GRASS)){
			if(event.getItem().getType()==Material.BONE && event.getAction()==Action.RIGHT_CLICK_BLOCK){
				
				//Decrement bones
				if(event.getItem().getAmount()>1) event.getItem().setAmount(event.getItem().getAmount()-1);
				else player.setItemInHand(null);
								
				//Add exp
				LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBone);
			}
		}
		
		if(event.getItem().getType().getId() != plugin.LCConfiguration.itemPrayer) return;
		
		List<Block> list = player.getLineOfSight(null, 4);//list contain air, wtf whith javadoc lineofsight
		
		if(list.size()>0){
			
			Block altar=null;
			
			//Search altar in the line of sight, first solid block (not air)
			ListIterator<Block> it = list.listIterator();
			while(it.hasNext()){
				altar = it.next();
				if(altar.getTypeId()!=0) break;
			}
			
			//Block altar = list.(0);
			ArrayList<Block> liste = new ArrayList<Block>();
			int size = 0;
			try{
				size = getSizeAltar(0,altar,liste,playerCache);
			}
			catch(AltarException e){
				LCChat.info(player, plugin.LCConfiguration.AltarAlreadyUse);
				return;
			}
			
			if(size>0){
				//altar is used
				playerCache.addAltar(liste);
				
				//Add pp
				if(plugin.LCConfiguration.fullRechargeAltar){
					pp = plugin.LCConfiguration.MaxPrayerPointPerLevel*level;
				}
				else{
					pp = playerCache.getPrayerPoint()+size*plugin.LCConfiguration.PrayerPointByAltarSize;
					if(pp>plugin.LCConfiguration.MaxPrayerPointPerLevel*level) pp = plugin.LCConfiguration.MaxPrayerPointPerLevel*level;
				}
				playerCache.setPrayerPoint(pp);
	
				//Config translate to eng friend
				//LCChat.info(player, "Vous avez "+playerCache.getPrayerPoint()+" points de prière;");
				LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
				
				list.clear();
				return; //prayer cannot launch bless if gold in the line of sight
			}
		}
		list.clear();		
		
		//Switch perk
		if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK){
			playerCache.nextPerk();
			if(playerCache.getActivePerk()==PrayerSelect.HEAL)
				LCChat.info(player,"Heal");
			else if(playerCache.getActivePerk() == PrayerSelect.HEALER)
				LCChat.info(player,"Healer");
			else if(playerCache.getActivePerk() == PrayerSelect.PROTECT)
				LCChat.info(player,"Protect");
			else if(playerCache.getActivePerk() == PrayerSelect.PROTECTER)
				LCChat.info(player,"Protecter");
			else if(playerCache.getActivePerk() == PrayerSelect.BOOSTC)
				LCChat.info(player,"Boost Combat");
			else if(playerCache.getActivePerk() == PrayerSelect.BOOSTD)
				LCChat.info(player,"Boost Defense");
			else if(playerCache.getActivePerk() == PrayerSelect.BOOSTR)
				LCChat.info(player,"Boost Range");
			else if(playerCache.getActivePerk() == PrayerSelect.ZONEPROTECTER)
				LCChat.info(player,"Zone Protecter");
			return;
		}
		
		//Launch myself perk
		if(event.getAction()==Action.LEFT_CLICK_AIR || event.getAction()==Action.LEFT_CLICK_BLOCK){
			//Heal myself		
			if(playerCache.getActivePerk() == PrayerSelect.HEAL  && level>=plugin.LCConfiguration.HealNeedLevel){
				if(playerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.HealCost)){
					int life = Math.min(player.getHealth()+level*plugin.LCConfiguration.HealByLevel,20);
					LCChat.info(player, "Heal "+(life-player.getHealth())/2+" Hearths");
					LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
					player.setHealth(life);
					LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByHeal);
				}
				else
					LCChat.warn(player, plugin.LCConfiguration.NoEnoughPP);
				return;
			}
			
			//Protect myself
			if(playerCache.getActivePerk() == PrayerSelect.PROTECT && level>=plugin.LCConfiguration.ProtectNeedLevel){
				if(playerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.ProtectCost)){
					//not accumulate protect time
					int time = (int)(level*plugin.LCConfiguration.timeProtectByLevel);
					playerCache.startProtectTime(time);
					
					
					LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByProtect);
					LCChat.info(player, "Invincible "+time+" seconds");
					LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
				}
				else
					LCChat.warn(player, plugin.LCConfiguration.NoEnoughPP);
				return;
			}
			
			//BoostCombat myself
			if(playerCache.getActivePerk() == PrayerSelect.BOOSTC && level>=plugin.LCConfiguration.BoostNeedLevel){
				if(playerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.BoostCost)){
					//not accumulate protect time
					int time = (int)(level*plugin.LCConfiguration.timeBoostCombatByLevel);
					playerCache.startCombatTime(time);
					LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBoost);
					LCChat.info(player, "Boost Combat "+time+" seconds");
					LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
				}
				else
					LCChat.warn(player,plugin.LCConfiguration.NoEnoughPP);
				return;
			}
			
			//BoostRange myself
			if(playerCache.getActivePerk() == PrayerSelect.BOOSTR && level>=plugin.LCConfiguration.BoostNeedLevel){
				if(playerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.BoostCost)){
					//not accumulate protect time
					int time = (int)(level*plugin.LCConfiguration.timeBoostRangeByLevel);
					playerCache.startRangeTime(time);
					LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBoost);
					LCChat.info(player, "Boost Range "+time+" seconds");
					LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
				}
				else
					LCChat.warn(player, plugin.LCConfiguration.NoEnoughPP);
				return;
			}
			
			//BoostDefense myself
			if(playerCache.getActivePerk() == PrayerSelect.BOOSTD && level>=plugin.LCConfiguration.BoostNeedLevel){
				if(playerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.BoostCost)){
					//not accumulate protect time
					int time = (int)(level*plugin.LCConfiguration.timeBoostDefenseByLevel);
					playerCache.startDefTime(time);
					LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBoost);
					LCChat.info(player, "Boost Defense "+time+" seconds");
					LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
				}
				else
					LCChat.warn(player, plugin.LCConfiguration.NoEnoughPP);
				return;
			}
	
			//ZoneProtecter
			if(playerCache.getActivePerk() == PrayerSelect.ZONEPROTECTER && level>=plugin.LCConfiguration.ZoneProtecterNeedLevel){
				if(playerCache.hasAndRemovePrayerPoint(plugin.LCConfiguration.ZoneProtecterCost)){
					//not accumulate protect time
					int time = (int)(level*plugin.LCConfiguration.timeZoneProtectByLevel);
					playerCache.startDefTime(time);
					LevelFunctions.addExp(player, plugin, plugin.LCConfiguration.ExpByBoost);
					for(Entity e :player.getNearbyEntities(plugin.LCConfiguration.ZPLen, plugin.LCConfiguration.ZPLen, plugin.LCConfiguration.ZPLen)){
						if(e instanceof Player){
							Player cible = (Player)e;
							PlayerCache pcible = plugin.playerCacheManager.getPlayerCache(cible);
							pcible.startProtectTime(time);
							LCChat.info(cible, "Invincible "+time+" seconds");
						}
					}
					LCChat.info(player, plugin.LCConfiguration.InfoPP.replaceFirst("VALUE", ""+playerCache.getPrayerPoint()+"/"+plugin.LCConfiguration.MaxPrayerPointPerLevel*level));
				}
				else
					LCChat.warn(player, plugin.LCConfiguration.NoEnoughPP);
				return;
			}	
			
		}
	}	
	
	private int getSizeAltar(int size,Block altar,ArrayList<Block> liste, PlayerCache playerCache) throws AltarException {
		if(altar.getTypeId()!=plugin.LCConfiguration.AltarBlock)
			return size;
		
		if(!playerCache.isValidAltar(altar))
			throw new AltarException();
		
		size++;
		if(size>=plugin.LCConfiguration.AltarSizeMax)//Security for stop if altar is big big big
			return plugin.LCConfiguration.AltarSizeMax;
		
		liste.add(altar);

		if(!liste.contains(altar.getFace(BlockFace.DOWN)))
			size=getSizeAltar(size,altar.getFace(BlockFace.DOWN),liste,playerCache);
		if(!liste.contains(altar.getFace(BlockFace.UP)))
			size=getSizeAltar(size,altar.getFace(BlockFace.UP),liste,playerCache);
		if(!liste.contains(altar.getFace(BlockFace.NORTH)))
			size=getSizeAltar(size,altar.getFace(BlockFace.NORTH),liste,playerCache);
		if(!liste.contains(altar.getFace(BlockFace.SOUTH)))
			size=getSizeAltar(size,altar.getFace(BlockFace.SOUTH),liste,playerCache);
		if(!liste.contains(altar.getFace(BlockFace.WEST)))
			size=getSizeAltar(size,altar.getFace(BlockFace.WEST),liste,playerCache);
		if(!liste.contains(altar.getFace(BlockFace.EAST)))
			size=getSizeAltar(size,altar.getFace(BlockFace.EAST),liste,playerCache);
		
		return size;
	}
	
}
