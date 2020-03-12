package com.stimonz.pvpsystem.Boosters;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.stimonz.pvpsystem.Main;
import com.stimonz.pvpsystem.TitleAPI;

public class KitBooster extends Booster{
	
	public static Main m;
	public KitBooster(Main ins){
		m = ins;
	}

	public String executor;
	
	public boolean isEnabled = false;
	
	public ArrayList<String> players = new ArrayList<String>();
	

	public String getName() {
		return "kit";
	}

	public String getExecutor() {
		return executor;
	}
	
	public void runEvent(int secs, Player player) {
		secs = secs * 20;
		this.executor = player.getName();
		printBooster(getExecutor(), new KitBooster(m));
		Bukkit.broadcastMessage("§cA kitet a §r/aldas kit §cparanccsal hívhatod le!");
		isEnabled = true;
		for (Player p : Bukkit.getOnlinePlayers()){
			TitleAPI.sendTitle(p, "§eEgy §rkit §eáldást aktiváltak!", "§aAktiváló: §r" + getExecutor(), 1, 5, 1);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0);
			//this.updateBar(player, this);
			Runnable r = new Runnable(){
				public void run(){
					isEnabled = false;
					players.clear();
					Bukkit.broadcastMessage("§cA kit áldás véget ért!");
					TitleAPI.sendTitle(p, "§cA kit áldás véget ért!", "", 1, 5, 1);
					//new KitBooster(m).clearBar(p);
				}
			};
			Bukkit.getScheduler().runTaskLater(m, r, secs);
		}
	}
	
	public void addItems(Player p){
		HashMap<Enchantment,Integer> armorench = new HashMap<>();
		HashMap<Enchantment,Integer> swordench = new HashMap<>();
		armorench.put(Enchantment.PROTECTION_ENVIRONMENTAL, 30);
		swordench.put(Enchantment.DAMAGE_ALL, 39);
		swordench.put(Enchantment.DAMAGE_UNDEAD, 39);
		swordench.put(Enchantment.FIRE_ASPECT, 10);
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 32, (short) 1);
		
		ItemMeta swordMeta = sword.getItemMeta();
		ItemMeta helmetMeta = helmet.getItemMeta();
		ItemMeta chestMeta = chest.getItemMeta();
		ItemMeta leggingsMeta = leggings.getItemMeta();
		ItemMeta bootsMeta = boots.getItemMeta();
		
		swordMeta.setDisplayName("§aÁldás kard");
		helmetMeta.setDisplayName("§aÁldás sisak");
		chestMeta.setDisplayName("§aÁldás mellvért");
		leggingsMeta.setDisplayName("§aÁldás lábszárvédö");
		bootsMeta.setDisplayName("§aÁldás csizma");
		
		sword.setItemMeta(swordMeta);
		helmet.setItemMeta(helmetMeta);
		chest.setItemMeta(chestMeta);
		leggings.setItemMeta(leggingsMeta);
		boots.setItemMeta(bootsMeta);
		
		sword.addUnsafeEnchantments(swordench);
		helmet.addUnsafeEnchantments(armorench);
		chest.addUnsafeEnchantments(armorench);
		leggings.addUnsafeEnchantments(armorench);
		boots.addUnsafeEnchantments(armorench);
		
		ItemStack[] items = new ItemStack[]{sword, gapple, helmet, chest, leggings, boots};
		
		p.getInventory().addItem(items);
	}
}
