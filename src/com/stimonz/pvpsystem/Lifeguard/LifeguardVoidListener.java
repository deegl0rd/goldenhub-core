package com.stimonz.pvpsystem.Lifeguard;

import com.stimonz.pvpsystem.Main;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class LifeguardVoidListener
  implements Listener
{
  public static Main m;
  
  public LifeguardVoidListener(Main ins)
  {
    m = ins;
  }
  
  public static String conv(String msg)
  {
    String newMsg = ChatColor.translateAlternateColorCodes('&', msg);
    return newMsg;
  }
  
  public static String getName()
  {
    return m.getConfig().getString("name");
  }
  
  public static String getLore()
  {
    return m.getConfig().getString("lore");
  }
  
  public static String getPrefix()
  {
    return m.getConfig().getString("void-prefix");
  }
  
  public static String getMessage()
  {
    return m.getConfig().getString("void-savemessage");
  }
  
  public static String getDenyMessage()
  {
    return m.getConfig().getString("void-notool-message");
  }
  
  public static boolean getState()
  {
    return m.getConfig().getBoolean("void-enabled");
  }
  
  public static boolean getStateLg()
  {
    return m.getConfig().getBoolean("lg-hookup-state");
  }
  
  public static Location getLocation(World world)
  {
    double x = m.getConfig().getDouble("x");
    double y = m.getConfig().getDouble("y");
    double z = m.getConfig().getDouble("z");
    float pitch = (float)m.getConfig().getDouble("pitch");
    float yaw = (float)m.getConfig().getDouble("yaw");
    return new Location(world, x + 0.5D, y, z + 0.5D, yaw, pitch);
  }
  
  @EventHandler(priority=EventPriority.HIGH)
  public void onVoidReach(PlayerMoveEvent e)
  {
    if (getState())
    {
      Player player = e.getPlayer();
      Location lp = e.getPlayer().getLocation();
      PlayerInventory inv = e.getPlayer().getInventory();
      Inventory topinv = e.getPlayer().getOpenInventory().getTopInventory();
      
      ItemStack saveRod = new ItemStack(Material.BLAZE_ROD);
      ItemMeta meta = saveRod.getItemMeta();
      meta.setDisplayName(conv(getName()));
      ArrayList<String> lore = new ArrayList<String>();
      lore.add(conv(getLore()));
      meta.setLore(lore);
      saveRod.setItemMeta(meta);
      if (player.getLocation().getY() < 0.0D) {
        if (getStateLg())
        {
          if (inv.containsAtLeast(saveRod, 1))
          {
            inv.removeItem(saveRod);
            player.setFallDistance(0.0F);
            player.teleport(getLocation(lp.getWorld()));
            player.sendMessage(conv(getPrefix() + " " + getMessage()));
          }
          else
          {
            inv.clear();
            inv.setArmorContents(null);
            topinv.clear();
            player.setFallDistance(0.0F);
            player.teleport(getLocation(lp.getWorld()));
            player.sendMessage(conv(getPrefix() + " " + getDenyMessage()));
          }
        }
        else
        {
          player.setFallDistance(0.0F);
          player.teleport(getLocation(lp.getWorld()));
          player.sendMessage(conv(getPrefix() + " " + getDenyMessage()));
        }
      }
    }
  }
}
