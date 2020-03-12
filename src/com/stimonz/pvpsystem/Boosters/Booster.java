package com.stimonz.pvpsystem.Boosters;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.stimonz.pvpsystem.BarAPI;


@SuppressWarnings("unused")
public abstract class Booster{
	
	public abstract void runEvent(int secs, Player player);
	
	public abstract String getName();
	
	public abstract String getExecutor();
	
	/*public void updateBar(Player player, Booster b){
		BarAPI.setBar(player, "§eAktív áldások: §r" + b.getName() + " (§e" + this.getExecutor() + "§r)", 100);
	}
	
	public void clearBar(Player p){
		BarAPI.removeBar(p);
	}*/
	
	public void printBooster(String player, Booster b){
		Bukkit.broadcastMessage("§3[§dÁldás§3] §e" + this.getExecutor() + " §raktiválta a(z) " + b.getName() + " §aáldást!");
	}
}
