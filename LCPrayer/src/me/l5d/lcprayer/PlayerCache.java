package me.l5d.lcprayer;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import me.samkio.levelcraftcore.LevelFunctions;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerCache {
	private Player player = null;
	private LCPrayer plugin = null;

	private Plugin combatPlugin = null;
	private Plugin rangePlugin = null;
	private Plugin defensePlugin = null;
	
	private ConcurrentHashMap<Block, Integer> altarUsed;

	private int timer=0;
	private int prayerPoint=0;
	private int ProtectTime=0;
	private int DefTime=0;
	private int RangeTime=0;
	private int CombatTime=0;
	private double DefExpBoost=-1;
	private double RangeExpBoost=-1;
	private double CombatExpBoost=-1;
	private PrayerSelect select;

	public enum PrayerSelect {
		HEAL,
		HEALER,
		PROTECT,
		PROTECTER,
		BOOSTR,
		BOOSTC,
		BOOSTD,
		ZONEPROTECTER;
	};	
	
	public PlayerCache(Player player,LCPrayer plugin,Plugin combatPlugin,Plugin rangePlugin,Plugin defensePlugin) {
		this.player = player;
		this.plugin = plugin;
		this.combatPlugin = combatPlugin;
		this.rangePlugin = rangePlugin;
		this.defensePlugin = defensePlugin;
		select = PrayerSelect.HEAL;
		altarUsed = new ConcurrentHashMap<Block,Integer>();
	}
	
	public PrayerSelect getActivePerk(){
		return select;
	}
	
	public void nextPerk(){
		int level = LevelFunctions.getLevel(player, plugin.thisPlug);
		boolean valid = true;
		
		while(valid){
			
			if(select==PrayerSelect.HEAL)
				select = PrayerSelect.HEALER;
			else if(select == PrayerSelect.HEALER)
				select = PrayerSelect.PROTECT;
			else if(select == PrayerSelect.PROTECT)
				select = PrayerSelect.PROTECTER;
			else if(select == PrayerSelect.PROTECTER)
				select = PrayerSelect.BOOSTC;
			else if(select == PrayerSelect.BOOSTC)
				select = PrayerSelect.BOOSTD;
			else if(select == PrayerSelect.BOOSTD)
				select = PrayerSelect.BOOSTR;
			else if(select == PrayerSelect.BOOSTR)
				select = PrayerSelect.ZONEPROTECTER;
			else if(select == PrayerSelect.ZONEPROTECTER)
				select = PrayerSelect.HEAL;
		
			if(select==PrayerSelect.HEAL)//stop while default
				valid=false;
			else if(select == PrayerSelect.HEALER && level>=plugin.LCConfiguration.HealerNeedLevel)
				valid=false;
			else if(select == PrayerSelect.PROTECT && level>=plugin.LCConfiguration.ProtectNeedLevel)
				valid=false;
			else if(select == PrayerSelect.PROTECTER && level>=plugin.LCConfiguration.ProtecterNeedLevel)
				valid=false;
			else if(select == PrayerSelect.BOOSTC && level>=plugin.LCConfiguration.BoostNeedLevel)
				valid=false;
			else if(select == PrayerSelect.BOOSTD && level>=plugin.LCConfiguration.BoostNeedLevel)
				valid=false;
			else if(select == PrayerSelect.BOOSTR && level>=plugin.LCConfiguration.BoostNeedLevel)
				valid=false;
			else if(select == PrayerSelect.ZONEPROTECTER && level>=plugin.LCConfiguration.ZoneProtecterNeedLevel)
				valid=false;
		}

	}

	public void update(int time){
		
		this.timer +=time;
		
		if(ProtectTime>0){
			ProtectTime-=time;
		}
		if(DefTime>0){
			DefTime-=time;
			if(DefTime<=0 && DefExpBoost!=-1){
				LevelFunctions.addExp(player, defensePlugin, -DefExpBoost);
				DefExpBoost = -1;
			}
		}
		if(RangeTime>0){
			RangeTime-=time;
			if(RangeTime<=0 && RangeExpBoost!=-1){
				LevelFunctions.addExp(player, rangePlugin, -RangeExpBoost);
				RangeExpBoost = -1;
			}
		}
		if(CombatTime>0){
			CombatTime-=time;
			if(CombatTime<=0 && CombatExpBoost!=-1){
				LevelFunctions.addExp(player, combatPlugin, -CombatExpBoost);
				CombatExpBoost = -1;
			}
		}
		for(Block b : altarUsed.keySet()){
			Integer timer = altarUsed.get(b);
			timer-=time;

			altarUsed.remove(b);
			if(timer>0)
				altarUsed.put(b,timer);
		}
	}
	
	public void stop(){
		if(CombatExpBoost!=-1){
			LevelFunctions.addExp(player, combatPlugin, -CombatExpBoost);
			CombatExpBoost = -1;
		}		
		if(RangeExpBoost!=-1){
			LevelFunctions.addExp(player, rangePlugin, -RangeExpBoost);
			RangeExpBoost = -1;
		}
		if(DefExpBoost!=-1){
			LevelFunctions.addExp(player, defensePlugin, -DefExpBoost);
			DefExpBoost = -1;
		}
	}
	
	public void addPrayerPoint(int prayerPoint) {
		this.prayerPoint += prayerPoint;
	}
	
	public boolean hasAndRemovePrayerPoint(int prayerPoint) {
		if(this.prayerPoint>=prayerPoint){
			this.prayerPoint-=prayerPoint;
			return true;
		}
		return false;
	}
	
	public void setPrayerPoint(int prayerPoint) {
		this.prayerPoint = prayerPoint;
	}

	public int getPrayerPoint() {
		return prayerPoint;
	}

	public void startProtectTime(int protectTime) {
		this.ProtectTime = protectTime;
	}

	public boolean isProtected() {
		return ProtectTime>0;
	}

	public void startDefTime(int defTime) {
		DefTime = defTime;
		if(DefExpBoost==-1 && defensePlugin!=null){
			DefExpBoost = (int)LevelFunctions.getExp(player, defensePlugin) * plugin.LCConfiguration.percentBoostDefenseByLevel;
			LevelFunctions.addExp(player, defensePlugin, DefExpBoost);
		}
	}

	public boolean isDefBoost() {
		return DefTime>0;
	}

	public void startRangeTime(int rangeTime) {
		RangeTime = rangeTime;
		if(RangeExpBoost==-1 && rangePlugin!=null){
			RangeExpBoost = (int)LevelFunctions.getExp(player, rangePlugin) * plugin.LCConfiguration.percentBoostRangeByLevel;
			LevelFunctions.addExp(player, rangePlugin, RangeExpBoost);
		}
	}

	public boolean isRangeBoost() {
		return RangeTime>0;
	}

	public void startCombatTime(int combatTime) {
		CombatTime = combatTime;
		if(CombatExpBoost==-1 && combatPlugin!=null){
			CombatExpBoost = (int)LevelFunctions.getExp(player, combatPlugin) * plugin.LCConfiguration.percentBoostCombatByLevel;
			LevelFunctions.addExp(player, combatPlugin, CombatExpBoost);
		}
	}

	public boolean isCombatCombat() {
		return CombatTime>0;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getTimer() {
		return timer;
	}
	
	public boolean isValidAltar(Block b){
		return !altarUsed.containsKey(b);
	}
	
	public void addAltar(ArrayList<Block> liste){
		for(Block b : liste){
			altarUsed.put(b,new Integer(plugin.LCConfiguration.timeToRechargeAltar));
		}
	}
	
}
