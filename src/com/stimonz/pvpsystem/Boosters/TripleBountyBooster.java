package com.stimonz.pvpsystem.Boosters;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.stimonz.pvpsystem.Main;
import com.stimonz.pvpsystem.TitleAPI;

public class TripleBountyBooster extends Booster{
	
	public static Main m;
	public TripleBountyBooster(Main ins){
		m = ins;
	}

	public String executor;
	
	public boolean isEnabled = false;

	public String getName() {
		return "h�romszoros v�rd�j";
	}

	public String getExecutor() {
		return executor;
	}

	public void runEvent(int secs, Player player) {
		secs = secs * 20;
		this.executor = player.getName();
		printBooster(getExecutor(), new TripleBountyBooster(m));
		Bukkit.broadcastMessage("�cMostant�l minden gyilkoss�g�rt 3x annyi p�nzt kapsz!");
		isEnabled = true;
		for(Player p : Bukkit.getOnlinePlayers()){
			TitleAPI.sendTitle(p, "�eEgy �rv�rd�j �e�ld�st aktiv�ltak!", "�aAktiv�l�: �r" + getExecutor(), 1, 5, 1);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0);
			//this.updateBar(player, this);
			Runnable r = new Runnable(){
				public void run(){
					isEnabled = false;
					Bukkit.broadcastMessage("�cA �rh�romszoros v�rd�j �c�ld�s v�get �rt!");
					TitleAPI.sendTitle(p, "�cA �r3x-os v�rd�j �c�ld�s v�get �rt!", "", 1, 5, 1);
					//new TripleBountyBooster(m).clearBar(p);
				}
			};
			Bukkit.getScheduler().runTaskLater(m, r, secs);
		}
	}
}
