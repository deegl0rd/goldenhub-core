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
					sender.sendMessage("�e�l�ld�sok");
					if (sender.hasPermission("aldas.admin")){
						sender.sendMessage("�7�oPlugin neve: " + pdf.getName());
						sender.sendMessage("�7�oVerzi�: " + pdf.getVersion());
						sender.sendMessage("�7�oSzerz�: " + pdf.getAuthors());
						sender.sendMessage("�c/aldas stop: �r�sszes �ld�s le�ll�t�sa.");
					}
					sender.sendMessage("�c/aldas activate: �r�ld�s aktiv�l�sa p�nz�rt cser�be.");
					sender.sendMessage("�c/aldas kit: �r�ld�s kit leh�v�sa.");
				}else{
					if (args[0].equalsIgnoreCase("stop")){
						if (p.hasPermission("aldas.admin")){
							if (kb.isEnabled == false && tbb.isEnabled == false && ub.isEnabled == false){
								p.sendMessage("�cEgy �ld�s se fut.");
							}else{
								kb.isEnabled = false;
								kb.players.clear();
								tbb.isEnabled = false;
								ub.isEnabled = false;
								TitleAPI.sendTitle(p, "�cAz �ld�sok v�get �rtek!", "", 1, 5, 1);
								Bukkit.broadcastMessage("�3[�d�ld�s�3] �eMinden �ld�s le lett �ll�tva.");
								p.sendMessage("�a�ld�sok le�ll�tva.");
							}
						}else{
							p.sendMessage("�cEhhez a parancshoz nincs jogod!");
						}
					}else if (args[0].equalsIgnoreCase("activate")){
						if (args.length == 1){
							p.sendMessage("�cAktiv�lhat� �ld�sok:");
							p.sendMessage("kit: �a20000$");
							p.sendMessage("bounty: �a25000$");
							p.sendMessage("torhetetlenseg: �a30000$");
						}else{
								if (args[1].equalsIgnoreCase("kit")){
									if (Main.economy.getBalance(p) >= this.getKitMoney()){
										if(kb.isEnabled == false){
											p.sendMessage("�aA kit �ld�st sikeresen megv�s�roltad!");
											Main.economy.withdrawPlayer(p, this.getKitMoney());
											kb.runEvent(1800, p);
										}else{
											p.sendMessage("�aM�r fut egy ilyen �ld�s! Pr�b�ld meg k�s�bb!");
										}
									}else{
										p.sendMessage("�cNincs el�g p�nzed Sz�ks�ges mennyis�g: �a20000 doll�r�c!");
									}
								}else if (args[1].equalsIgnoreCase("bounty")){
									p.sendMessage("�cEz az �ld�s le van tiltva!");
										/*if (Main.economy.getBalance(p) >= 25000){
											if(tbb.isEnabled == false){
												p.sendMessage("�aA h�romszoros v�rd�j �ld�st sikeresen megv�s�roltad!");
												Main.economy.withdrawPlayer(p, 25000);
												tbb.runEvent(1800, p);
											}else{
												p.sendMessage("�aM�r fut egy ilyen �ld�s! Pr�b�ld meg k�s�bb!");
											}
										}else{
											p.sendMessage("�cNincs el�g p�nzed Sz�ks�ges mennyis�g: �a25000 doll�r�c!");
										}
									}else{
										p.sendMessage("�cIlyen �ld�s nem l�tezik!");
									}*/
							   }else if (args[1].equalsIgnoreCase("torhetetlenseg")){
									if (Main.economy.getBalance(p) >= 30000){
										if(ub.isEnabled == false){
											p.sendMessage("�aA t�rhetetlens�g �ld�st sikeresen megv�s�roltad!");
											Main.economy.withdrawPlayer(p, 30000);
											ub.runEvent(1800, p);
										}else{
											p.sendMessage("�aM�r fut egy ilyen �ld�s! Pr�b�ld meg k�s�bb!");
										}
									}else{
										p.sendMessage("�cNincs el�g p�nzed Sz�ks�ges mennyis�g: �a30000 doll�r�c!");
									}
							}
						}
					}
					else if (args[0].equalsIgnoreCase("kit")){
						if (kb.isEnabled == true){
							if(!kb.players.contains(p.getName())){
									kb.players.add(p.getName());
									kb.addItems(p);
									p.sendMessage("�eAz �ld�s kited meg�rkezett!");
							}else{
								p.sendMessage("�cM�r leh�vtad az �ld�s kitet!");
							}
						}else{
							p.sendMessage("�cJelenleg nem fut kit �ld�s!");
						}
					}
				}
			}
		}
		return false;
	}

}
