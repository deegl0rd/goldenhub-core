package com.stimonz.pvpsystem.API;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.stimonz.pvpsystem.Lifeguard.LifeguardListener;

public class LifeguardItem {
	
	public String conv(String msg)
	{
	    String newMsg = ChatColor.translateAlternateColorCodes('&', msg);
	    return newMsg;
	}
	
	public ItemStack getItem(int Amount){
	    ItemStack saveRod = new ItemStack(Material.BLAZE_ROD, Amount);
	    ItemMeta meta = saveRod.getItemMeta();
	    meta.setDisplayName(conv(LifeguardListener.getName()));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(conv(LifeguardListener.getLore()));
	    meta.setLore(lore);
	    saveRod.setItemMeta(meta);
	    return saveRod;
	}
}
