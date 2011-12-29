package me.samkio.lcrange;

import org.bukkit.craftbukkit.entity.CraftArrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;

public class ArrowHandler {
	public static short lastData = 0;

	public static void onArrowCreate(Player p, Arrow arrow) {
		try {
			org.bukkit.entity.Arrow ea = (org.bukkit.entity.Arrow) arrow
					.getBukkitEntity();
			arrow.speed = 2;
			if (arrow.material == EnumBowMaterial.FIRE) {
				ea.setFireTicks(300);
			}

			arrow.world.addEntity(arrow);
		} catch (Exception localException) {
		}
	}
	public static void onArrowCreateSamkio(Player p, Arrow arrow) {
		try {
			org.bukkit.entity.Arrow ea = (org.bukkit.entity.Arrow) arrow
					.getBukkitEntity();
			arrow.speed = 100;
			if (arrow.material == EnumBowMaterial.FIRE) {
				ea.setFireTicks(300);
			}

			arrow.world.addEntity(arrow);
		} catch (Exception localException) {
		}
	}
	public static void onArrowDestroy(EntityDamageByProjectileEvent event) {
		try {
			Arrow arrow = (Arrow) ((CraftArrow) event.getProjectile())
					.getHandle();

			if (arrow.material == EnumBowMaterial.FIRE) {
				event.getEntity().setFireTicks(80);
				event.setDamage(event.getDamage() * 2);
			}

		} catch (Exception localException) {
		}
	}
}