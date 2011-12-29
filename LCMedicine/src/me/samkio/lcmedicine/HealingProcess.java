package me.samkio.lcmedicine;

import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;

import org.bukkit.entity.Player;

public class HealingProcess {
private LCMedicine plugin;
	public HealingProcess(LCMedicine lcMedicine) {
		this.plugin = lcMedicine;
	}
	public void heal(Player doctor, Player patient) {
		if(doctor.getItemInHand().getTypeId() == plugin.LCConfiguration.antidote && plugin.Poisoned.containsKey(patient)){
			int level = LevelFunctions.getLevel(doctor, plugin.thisPlug);
			if(level<plugin.LCConfiguration.AntidoteLevel){
				LCChat.warn(doctor, "You cannot aminisiser Antidote. Required level: "+plugin.LCConfiguration.AntidoteLevel);
				return;
			}else{
				plugin.Poisoned.remove(patient);
				LCChat.good(patient, doctor.getName()+" has healed your poison.");
				LCChat.good(doctor, "You have successfully healed "+patient.getName());
				LevelFunctions.addExp(doctor, plugin.thisPlug, plugin.LCConfiguration.ExpPerAntidote);
				doctor.getItemInHand().setType(null);

			}
		}
	}

}
