package com.stimonz.pvpsystem.Bounty;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.stimonz.pvpsystem.Main;
import com.stimonz.pvpsystem.Boosters.TripleBountyBooster;

public class BountyListener implements Listener {
	
	public static Main m;
	public BountyListener(Main ins){
		m = ins;
	}
	
	public static TripleBountyBooster tbb = new TripleBountyBooster(Main.ins);
	
	public static String conv(String msg){
		String newMsg = ChatColor.translateAlternateColorCodes('&', msg);
		return newMsg;
	}
	
	public static int getKillReward(){
		return m.getConfig().getInt("killReward");
	}
	
	public static int getRewardPercent(){
		return m.getConfig().getInt("reward-percent");
	}
	
	public static String getPrefix(){
		return conv(m.getConfig().getString("bt-prefix"));
	}
	
	public static String getVictimTextIns(){
		String msg = m.getConfig().getString(conv("victim"));
		msg = msg.replaceAll("%money%", new String (Integer.toString(getKillReward())));
		msg = msg.replaceAll("%killer%", "GYILKOS");
		return conv(msg);
	}
	public static String getKillerTextIns(){
		String msg = m.getConfig().getString(conv("killer"));
		msg = msg.replaceAll("%money%", new String (Integer.toString(getKillReward())));
		msg = msg.replaceAll("%victim%", "ÁLDOZAT");
		return conv(msg);
	}
	
	public static String getVictimText(Player killer, int gotMoney){
		String msg1 = m.getConfig().getString(conv("victim"));
		msg1 = msg1.replaceAll("%money%", new String (Integer.toString(gotMoney)));
		msg1 = msg1.replaceAll("%killer%", new String (killer.getName()));
		return conv(msg1);
	}
	
	public static String getKillerText(Player victim, int gotMoney){
		String msg2 = m.getConfig().getString(conv("killer"));
		msg2 = msg2.replaceAll("%money%", new String (Integer.toString(gotMoney)));
		msg2 = msg2.replaceAll("%victim%", new String (victim.getName()));
		return conv(msg2);
	}
	
	public static String getKillerRandomText(Player victim, int donerandom){
		String msg3 = m.getConfig().getString(conv("killer"));
		msg3 = msg3.replaceAll("%money%", new String (Integer.toString(donerandom)));
		msg3 = msg3.replaceAll("%victim%", new String (victim.getName()));
		return conv(msg3);
	}
	
	public static String getVictimRandomText(Player killer, int donerandom){
		String msg3 = m.getConfig().getString(conv("victim"));
		msg3 = msg3.replaceAll("%money%", new String (Integer.toString(donerandom)));
		msg3 = msg3.replaceAll("%killer%", new String (killer.getName()));
		return conv(msg3);
	}
	
	public int nextInt (int min, int max)
	{
		int eqt = new Random().nextInt((max + 1) - min) + min;
		return eqt;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerKill(PlayerDeathEvent e){
		if (m.getConfig().getBoolean("bounty-enabled") == true){
			if (e.getEntity().getKiller().getLocation().getWorld().getName().equalsIgnoreCase(m.getConfig().getString("world")) || e.getEntity().getLocation().getWorld().getName().equalsIgnoreCase(m.getConfig().getString("world"))){
				try{
				if (e.getEntity().getKiller() != e.getEntity()){
					if (e.getEntity() != null){
						Player victim = (Player) e.getEntity();
						Player killer = (Player) e.getEntity().getKiller();
						double victimmoney = Main.economy.getBalance(victim);
							if (m.getConfig().getBoolean("reward-by-percent") == false){
								if (m.getConfig().getBoolean("random-reward") == false){
										int money = getKillReward();
										if (tbb.isEnabled){
											money = money * 3;
										}
										if (victimmoney >= getKillReward()){
											Main.economy.withdrawPlayer(victim, money);
											Main.economy.depositPlayer(killer, money);
										}else{
											Main.economy.withdrawPlayer(victim, victimmoney);
											Main.economy.depositPlayer(killer, money);
										}
										victim.sendMessage(conv(getPrefix() + " " + getVictimText(killer, money)));
										killer.sendMessage(conv(getPrefix() + " " + getKillerText(victim, money)));
								}else{
										int random = nextInt(m.getConfig().getInt("random-min"), m.getConfig().getInt("random-max"));
										if (tbb.isEnabled){
											random = random * 3;
										}
										if (victimmoney >= random){
											Main.economy.withdrawPlayer(victim, random);
											Main.economy.depositPlayer(killer, random);
										}else{
											Main.economy.withdrawPlayer(victim, victimmoney);
											Main.economy.depositPlayer(killer, random);
										}
										victim.sendMessage(conv(getPrefix() + " " + getVictimRandomText(killer, random)));
										killer.sendMessage(conv(getPrefix() + " " + getKillerRandomText(victim, random)));
								}
							}else{
								if (m.getConfig().getBoolean("random-reward") == false){
									double percent_money = (victimmoney/100) * getRewardPercent();
									if (victimmoney >= percent_money){
										Main.economy.withdrawPlayer(victim, percent_money);
										Main.economy.depositPlayer(killer, percent_money);
									}else{
										Main.economy.withdrawPlayer(victim, victimmoney);
										Main.economy.depositPlayer(killer, percent_money);
									}
									victim.sendMessage(conv(getPrefix() + " " + getVictimText(killer, (int) percent_money)));
									killer.sendMessage(conv(getPrefix() + " " + getKillerText(victim, (int) percent_money)));
								}else{
									victim.sendMessage("Jelenleg a random mód nincs bekonfigurálva.");
									killer.sendMessage("Jelenleg a random mód nincs bekonfigurálva.");
								}
							}
					}
				}
				}catch(Exception ex){}
			}
		}
	}
}
