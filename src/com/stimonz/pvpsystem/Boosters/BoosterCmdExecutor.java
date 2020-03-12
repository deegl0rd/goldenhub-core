package com.stimonz.pvpsystem.Boosters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.stimonz.pvpsystem.Main;
import com.stimonz.pvpsystem.TitleAPI;

public class BoosterCmdExecutor implements CommandExecutor {

	public static Main m;
	public BoosterCmdExecutor(Main ins) {
		m = ins;
	}
	
	public static BoosterCmdExecutor getInstance(){
		return new BoosterCmdExecutor(m);
	}
	
	public double getKitMoney(){
		return 20000.0;
	}
	
	static UnbreakBooster ub = new UnbreakBooster(Main.ins);
	static KitBooster kb = new KitBooster(Main.ins);
	static TripleBountyBooster tbb = new TripleBountyBooster(Main.ins);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		PluginDescriptionFile pdf = m.getDescription();
		if (cmd.getName().equalsIgnoreCase("aldas")){
			if (sender instanceof Player){
				if (args.length == 0){
					sender.sendMessage("§e§lÁldások");
					if (sender.hasPermission("aldas.admin")){
						sender.sendMessage("§7§oPlugin neve: " + pdf.getName());
						sender.sendMessage("§7§oVerzió: " + pdf.getVersion());
						sender.sendMessage("§7§oSzerzõ: " + pdf.getAuthors());
						sender.sendMessage("§c/aldas stop: §rÖsszes áldás leállítása.");
					}
					sender.sendMessage("§c/aldas activate: §rÁldás aktiválása pénzért cserébe.");
					sender.sendMessage("§c/aldas kit: §rÁldás kit lehívása.");
				}else{
					if (args[0].equalsIgnoreCase("stop")){
						if (p.hasPermission("aldas.admin")){
							if (kb.isEnabled == false && tbb.isEnabled == false && ub.isEnabled == false){
								p.sendMessage("§cEgy áldás se fut.");
							}else{
								kb.isEnabled = false;
								kb.players.clear();
								tbb.isEnabled = false;
								ub.isEnabled = false;
								TitleAPI.sendTitle(p, "§cAz áldások véget értek!", "", 1, 5, 1);
								Bukkit.broadcastMessage("§3[§dÁldás§3] §eMinden áldás le lett állítva.");
								p.sendMessage("§aÁldások leállítva.");
							}
						}else{
							p.sendMessage("§cEhhez a parancshoz nincs jogod!");
						}
					}else if (args[0].equalsIgnoreCase("activate")){
						if (args.length == 1){
							p.sendMessage("§cAktiválható áldások:");
							p.sendMessage("kit: §a20000$");
							p.sendMessage("bounty: §a25000$");
							p.sendMessage("torhetetlenseg: §a30000$");
						}else{
								if (args[1].equalsIgnoreCase("kit")){
									if (Main.economy.getBalance(p) >= this.getKitMoney()){
										if(kb.isEnabled == false){
											p.sendMessage("§aA kit áldást sikeresen megvásároltad!");
											Main.economy.withdrawPlayer(p, this.getKitMoney());
											kb.runEvent(1800, p);
										}else{
											p.sendMessage("§aMár fut egy ilyen áldás! Próbáld meg késöbb!");
										}
									}else{
										p.sendMessage("§cNincs elég pénzed Szükséges mennyiség: §a20000 dollár§c!");
									}
								}else if (args[1].equalsIgnoreCase("bounty")){
									p.sendMessage("§cEz az áldás le van tiltva!");
										/*if (Main.economy.getBalance(p) >= 25000){
											if(tbb.isEnabled == false){
												p.sendMessage("§aA háromszoros vérdíj áldást sikeresen megvásároltad!");
												Main.economy.withdrawPlayer(p, 25000);
												tbb.runEvent(1800, p);
											}else{
												p.sendMessage("§aMár fut egy ilyen áldás! Próbáld meg késöbb!");
											}
										}else{
											p.sendMessage("§cNincs elég pénzed Szükséges mennyiség: §a25000 dollár§c!");
										}
									}else{
										p.sendMessage("§cIlyen áldás nem létezik!");
									}*/
							   }else if (args[1].equalsIgnoreCase("torhetetlenseg")){
									if (Main.economy.getBalance(p) >= 30000){
										if(ub.isEnabled == false){
											p.sendMessage("§aA törhetetlenség áldást sikeresen megvásároltad!");
											Main.economy.withdrawPlayer(p, 30000);
											ub.runEvent(1800, p);
										}else{
											p.sendMessage("§aMár fut egy ilyen áldás! Próbáld meg késöbb!");
										}
									}else{
										p.sendMessage("§cNincs elég pénzed Szükséges mennyiség: §a30000 dollár§c!");
									}
							}
						}
					}
					else if (args[0].equalsIgnoreCase("kit")){
						if (kb.isEnabled == true){
							if(!kb.players.contains(p.getName())){
									kb.players.add(p.getName());
									kb.addItems(p);
									p.sendMessage("§eAz áldás kited megérkezett!");
							}else{
								p.sendMessage("§cMár lehívtad az áldás kitet!");
							}
						}else{
							p.sendMessage("§cJelenleg nem fut kit áldás!");
						}
					}
				}
			}
		}
		return false;
	}

}
