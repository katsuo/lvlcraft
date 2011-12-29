package me.torrent.lchealth;

import java.util.HashMap;
import me.samkio.levelcraftcore.LevelFunctions;
import me.samkio.levelcraftcore.Whitelist;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class LCEntityListener extends EntityListener
{

public LCHealth plugin;
  public HashMap<Entity, Block> doneBefore = new HashMap<Entity, Block>();

  public LCEntityListener(LCHealth instance) {
    this.plugin = instance;
  }

  public void onEntityDamage(EntityDamageEvent event) {
    if (event.isCancelled())
      return;
      if (!(event.getEntity() instanceof Player))return; 
  
          if (!Whitelist.worldCheck(event.getEntity().getWorld())) return;
          if (!(event.getEntity() instanceof Player)) return; 
          if (!Whitelist.hasLevel((Player)event.getEntity(), this.plugin.thisPlug)) return;
          Player p = (Player)event.getEntity();
          int lvl = LevelFunctions.getLevel(p, this.plugin.thisPlug);
          int damageReduction = (int)(lvl / 10 * this.plugin.LCConfiguration.dMultiplier);
          if (event.getDamage() <= damageReduction){
            event.setDamage(0);}
          else{
            event.setDamage(event.getDamage() - damageReduction);
          }
          LevelFunctions.addExp((Player) event.getEntity(),plugin.thisPlug,event.getDamage());
          }
      }
      
    


 


 
