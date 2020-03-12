package com.stimonz.pvpsystem.Boosters;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.stimonz.pvpsystem.Main;
import com.stimonz.pvpsystem.TitleAPI;

public class UnbreakBooster extends Booster{

	public static Main m;
	public UnbreakBooster(Main ins){
		m = ins;
	}
	
	public String executor;
	
	public boolean isEnabled = false;
	
	public String getName() {
		return "törhetetlenség";
	}

	public String getExecutor() {
		return executor;
	}
	
	@SuppressWarnings("deprecation")
	public void runEvent(int secs, Player player) {
		secs = secs * 20;
		this.executor = player.getName();
		printBooster(getExecutor(), new UnbreakBooster(m));
		Bukkit.broadcastMessage("§cMostantól fél óráig minden nálad lévö dolog megjavul!");
		isEnabled = true;
		for (Player p : Bukkit.getOnlinePlayers()){
			TitleAPI.sendTitle(p, "§eEgy §rtörhet. §eáldást aktiváltak!", "§aAktiváló: §r" + getExecutor(), 1, 5, 1);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0);
			//this.updateBar(player, this);
			Runnable event = new Runnable(){
				public void run(){
					if (isEnabled = true){
						for (Player newCall : Bukkit.getOnlinePlayers()){
							repairAll(newCall);
						}
					}
				}
			};
			Bukkit.getScheduler().scheduleAsyncRepeatingTask(m, event, 0L, 100L);
			Runnable r = new Runnable(){
				public void run(){
					isEnabled = false;
					Bukkit.broadcastMessage("§cA törhetetlenség áldás véget ért!");
					TitleAPI.sendTitle(p, "§cA törhet. áldás véget ért!", "", 1, 5, 1);
					//new UnbreakBooster(m).clearBar(p);
				}
			};
			Bukkit.getScheduler().runTaskLater(m, r, secs);
		}
	}
	
    public void repairAll(Player p) {
        for(int i = 0; i <= 36; i++)
        {
            try
            {
                p.getInventory().getItem(i).setDurability((short) 0);
                p.updateInventory();
            }
            catch(Exception e)
            {
                
            }
        }
    	for(int i = 0; i <= 4; i++) {
    		try
    		{
    			ItemStack[] armor = p.getInventory().getArmorContents();
    			armor[i].setDurability((short) 0);
    			p.updateInventory();
    		}
    		catch(Exception e){
    			
    		}
    	}
    }
}
