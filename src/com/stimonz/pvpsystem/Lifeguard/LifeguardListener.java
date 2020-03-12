package com.stimonz.pvpsystem.Lifeguard;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.stimonz.pvpsystem.Main;

public class LifeguardListener implements Listener {

	public static Main m;
	public LifeguardListener(Main ins) {
		m = ins;
	}
	
	public static String conv(String msg){
		String newMsg = ChatColor.translateAlternateColorCodes('&', msg);
		return newMsg;
	}
	
	public static String getName(){
		return m.getConfig().getString("name");
	}
	
	public static String getLore(){
		return m.getConfig().getString("lore");
	}
	
	public static String getPrefix(){
		return m.getConfig().getString("lg-prefix");
	}
	
	public static String getMessage(){
		return m.getConfig().getString("savemessage");
	}

	 @EventHandler
	  public void playerDeath(PlayerDeathEvent e)
	  {
	    Player player = e.getEntity().getPlayer();
	    PlayerInventory inv = player.getInventory();
	    
	    ItemStack saveRod = new ItemStack(Material.BLAZE_ROD);
	    ItemMeta meta = saveRod.getItemMeta();
	    meta.setDisplayName(conv(getName()));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(conv(getLore()));
	    meta.setLore(lore);
	    saveRod.setItemMeta(meta);
	      try
	      {
	          if(inv.containsAtLeast(saveRod, 1)){
		        e.setKeepInventory(true);
     			inv.removeItem(saveRod);
	            player.sendMessage(conv(getPrefix() + " " + getMessage()));
	            e.getDrops().clear();
	          }
	      }
	      catch (Exception localException) {}
	  }  
}
