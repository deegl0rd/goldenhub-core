package com.stimonz.pvpsystem.Features;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.stimonz.pvpsystem.Main;

public class CSPY implements Listener{
	
	public static Main m;
	public CSPY(Main ins){
		m = ins;
	}
	
	public static ArrayList<Player> spying = new ArrayList<Player>();
	public static ArrayList<Player> override = new ArrayList<Player>();
	
	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent e){
		for (Player p : spying){
			if (e.getPlayer() != p){
				if (!override.contains(e.getPlayer())){
					p.sendMessage("§6[GHUB] [SPY] §e" + e.getPlayer().getName() + " §7» §r" + e.getMessage());
				}
			}
		}
	}

}
