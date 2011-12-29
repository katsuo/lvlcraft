package me.samkio.lcmedicine;

import me.samkio.levelcraftcore.LCChat;
import org.bukkit.entity.Player;

public class PoisnedThread implements Runnable {
	private LCMedicine plugin;
	private Player p;

	public PoisnedThread(LCMedicine plugin, Player p) {
		this.plugin = plugin;
		this.p = p;
	}

	@Override
	public void run() {
		if(!p.isOnline()) return;
		int h = p.getHealth() - plugin.Poisoned.get(p);
		p.setHealth(h);
		LCChat.warn(p, "You are hurt by poison see a doctor.");
	}

}
