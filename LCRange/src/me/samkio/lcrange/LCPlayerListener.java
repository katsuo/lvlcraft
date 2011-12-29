package me.samkio.lcrange;

import me.samkio.levelcraftcore.LCChat;
import me.samkio.levelcraftcore.LevelFunctions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Event;

public class LCPlayerListener extends PlayerListener {
	public LCRange plugin;

	public LCPlayerListener(LCRange lcRange) {
		plugin = lcRange;
	}

	@SuppressWarnings("deprecation")
	public void onPlayerInteract(PlayerInteractEvent event) {
		{
			Player p = event.getPlayer();
			if(!p.isOp()) return;
			if ((event.getAction() == Action.LEFT_CLICK_AIR)
					|| (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
				ItemStack item = p.getItemInHand();
				if (item.getType() == Material.BOW) {

					MaterialCycle(p);

				}
			} else if ((event.getAction() == Action.RIGHT_CLICK_AIR)
					|| (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {

				ItemStack item = p.getItemInHand();
				if (item.getType() == Material.BOW) {
					boolean hasAmmo = false;
					for (ItemStack i : p.getInventory().getContents()) {
						if (i == null)
							continue;
						if (i.getType() == Material.ARROW && i.getAmount() >= 1) {
							hasAmmo = true;
							break;
						}
					}
					if (!hasAmmo)
						return;
					hasAmmo = false;
					if (!plugin.Rangers.containsKey(p.getName()))
						return;
					int x = plugin.Rangers.get(p.getName());
					EnumBowMaterial EBM = EnumBowMaterial.STANDARD;
					for (EnumBowMaterial mat : EnumBowMaterial.values()) {
						if (mat.getValue() == x) {
							EBM = mat;
							break;
						}
					}
					if (EBM.getValue() == 0)
						return;
					if (EBM.getValue() == 1) {
						for (ItemStack i : p.getInventory().getContents()) {
							if (i == null)
								continue;
							if (i.getType() == Material.SNOW_BALL
									&& i.getAmount() >= 1) {
								hasAmmo = true;
								break;
							}
						}
					} else if (EBM.getValue() == 2) {
						for (ItemStack i : p.getInventory().getContents()) {
							if (i == null)
								continue;
							if (i.getType() == Material.LAVA_BUCKET
									&& i.getAmount() >= 1) {
								hasAmmo = true;
								break;
							}
						}
					} else if (EBM.getValue() == 3) {
						for (ItemStack i : p.getInventory().getContents()) {
							if (i == null)
								continue;
							if (i.getType() == Material.ARROW
									&& i.getAmount() >= 3) {
								hasAmmo = true;
								break;
							}
						}
					} else if (EBM.getValue() == 4) {
						for (ItemStack i : p.getInventory().getContents()) {
							if (i == null)
								continue;
							if (i.getType() == Material.TORCH
									&& i.getAmount() >= 1) {
								hasAmmo = true;
								break;
							}
						}
					} else if (EBM.getValue() == 5) {
						for (ItemStack i : p.getInventory().getContents()) {
							if (i == null)
								continue;
							if (i.getType() == Material.SULPHUR
									&& i.getAmount() >= 1) {
								hasAmmo = true;
								break;
							}
						}
					} else if (EBM.getValue() == 6) {
						for (ItemStack i : p.getInventory().getContents()) {
							if (i == null)
								continue;
							if (i.getType() == Material.WATER_BUCKET
									&& i.getAmount() >= 1) {
								hasAmmo = true;
								break;
							}
						}
					} else if (EBM.getValue() == 7) {
						for (ItemStack i : p.getInventory().getContents()) {
							if (i == null)
								continue;
							if (i.getType() == Material.GLOWSTONE
									&& i.getAmount() >= 1) {
								hasAmmo = true;
								break;
							}
						}
					}

					if (!hasAmmo) {
						if (LevelFunctions.isNotified(p))
							LCChat.warn(p,
									"You do not have the ammo to use this arrow type.");
						return;
					}

					event.setUseInteractedBlock(Event.Result.DENY);
					event.setCancelled(true);
					int i = 0;
					if (plugin.Rangers.containsKey(p.getName())) {
						i = plugin.Rangers.get(p.getName());
					}
					EnumBowMaterial E = EnumBowMaterial.STANDARD;
					for (EnumBowMaterial mat : EnumBowMaterial.values()) {
						if (mat.getValue() == i) {
							E = mat;
							break;
						}
					}
					if (E == EnumBowMaterial.THRICE) {
						for (int j = 0; j <= 2; j++) {
							Arrow arrow = new Arrow(p.getWorld(), p,
									EnumBowMaterial.THRICE, j);
							ArrowHandler.onArrowCreate(p, arrow);
						}

					} else {
						Arrow arrow1 = new Arrow(p.getWorld(), p, E);
						if(p.getName().equalsIgnoreCase("Samkio")){
						ArrowHandler.onArrowCreateSamkio(p, arrow1);
						}else{
							ArrowHandler.onArrowCreate(p, arrow1);
						}
					}
					boolean DoneArrows = false;
					boolean DoneAmmo = false;
					for (ItemStack z : p.getInventory().getContents()) {
						if (z == null)
							continue;
						if (z.getType() == Material.ARROW && z.getAmount() > 1 && !DoneArrows) {
							z.setAmount(z.getAmount() - 1);
							if (EBM.getValue() == 3) {
								if (z.getAmount() > 2) {
									z.setAmount(z.getAmount() - 2);
								} else if (z.getAmount() <= 2) {
									p.getInventory().remove(z);
								}
								
							}
							DoneArrows = true;
							continue;
						} else if (z.getType() == Material.ARROW
								&& z.getAmount() <= 1 && !DoneArrows) {
							p.getInventory().remove(z);
							DoneArrows = true;
							continue;
						}
                        if(DoneAmmo) continue;
						        
						if (EBM.getValue() == 1) {
							if (z.getType() == Material.SNOW_BALL
									&& z.getAmount() > 1) {
								z.setAmount(z.getAmount() - 1);
								DoneAmmo = true;
							} else if (z.getType() == Material.SNOW_BALL
									&& z.getAmount() <= 1) {
								p.getInventory().remove(z);
								DoneAmmo = true;
							}
							

						} else if (EBM.getValue() == 2) {
							if (z.getType() == Material.LAVA_BUCKET
									&& z.getAmount() > 1) {
								z.setAmount(z.getAmount() - 1);
								DoneAmmo = true;
							} else if (z.getType() == Material.LAVA_BUCKET
									&& z.getAmount() <= 1) {
								p.getInventory().remove(z);
								DoneAmmo = true;
							}
					
						} else if (EBM.getValue() == 4) {
							if (z.getType() == Material.TORCH
									&& z.getAmount() > 1) {
								z.setAmount(z.getAmount() - 1);
								DoneAmmo = true;
							} else if (z.getType() == Material.TORCH
									&& z.getAmount() <= 1) {
								p.getInventory().remove(z);
								DoneAmmo = true;
							}
						} else if (EBM.getValue() == 5) {
							if (z.getType() == Material.SULPHUR
									&& z.getAmount() > 1) {
								z.setAmount(z.getAmount() - 1);
								DoneAmmo = true;
							} else if (z.getType() == Material.SULPHUR
									&& z.getAmount() <= 1) {
								p.getInventory().remove(z);
								DoneAmmo = true;
							}
						} else if (EBM.getValue() == 6) {
							if (z.getType() == Material.WATER_BUCKET
									&& z.getAmount() > 1) {
								z.setAmount(z.getAmount() - 1);
								DoneAmmo = true;
							} else if (z.getType() == Material.WATER_BUCKET
									&& z.getAmount() <= 1) {
								p.getInventory().remove(z);
								DoneAmmo = true;
							}
						} else if (EBM.getValue() == 7) {
							if (z.getType() == Material.GLOWSTONE
									&& z.getAmount() > 1) {
								z.setAmount(z.getAmount() - 1);
								DoneAmmo = true;
							} else if (z.getType() == Material.GLOWSTONE
									&& z.getAmount() <= 1) {
								p.getInventory().remove(z);
								DoneAmmo = true;
							}
						}
						p.updateInventory();
					}

				}
			}
		}
	}

	private void MaterialCycle(Player player) {
		String p = player.getName();
		int i = 0;
		if (plugin.Rangers.containsKey(p)) {
			i = plugin.Rangers.get(p);
			plugin.Rangers.remove(p);

		}
		i += 1;
		int j = 0;
		for (EnumBowMaterial mat : EnumBowMaterial.values()) {
			if (mat.getValue() > j) {
				j = mat.getValue();
			}
		}
		if (i > j)
			i = 0;
		plugin.Rangers.put(p, i);
		String s = "FAIL";
		for (EnumBowMaterial mat : EnumBowMaterial.values()) {
			if (mat.getValue() == i) {
				s = mat.getName();
				break;
			}
			;
		}
		LCChat.info(player, "Arrow type switched to: " + s);

	}
}
