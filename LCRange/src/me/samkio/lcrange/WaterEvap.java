package me.samkio.lcrange;


import org.bukkit.Material;
import org.bukkit.block.Block;



public class WaterEvap implements Runnable{
	private Block block;
	public WaterEvap(Block block) {
		this.block = block;
	}
	@Override
	public void run() {
	
			block.setType(Material.AIR);


	}

}
