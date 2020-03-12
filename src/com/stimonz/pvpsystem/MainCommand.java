package com.stimonz.pvpsystem;

import com.stimonz.pvpsystem.Bounty.BountyListener;
import com.stimonz.pvpsystem.Features.CSPY;
import com.stimonz.pvpsystem.Lifeguard.LifeguardListener;
import com.stimonz.pvpsystem.Lifeguard.LifeguardVoidListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;

public class MainCommand
  implements CommandExecutor
{
  Main m;
  
  public MainCommand(Main ins)
  {
    this.m = ins;
  }
  
  public static String conv(String msg)
  {
    String newMsg = ChatColor.translateAlternateColorCodes('&', msg);
    return newMsg;
  }
  
  public ItemStack saveRod(int Amount)
  {
    ItemStack saveRod = new ItemStack(Material.BLAZE_ROD, Amount);
    ItemMeta meta = saveRod.getItemMeta();
    meta.setDisplayName(conv(LifeguardListener.getName()));
    ArrayList<String> lore = new ArrayList<String>();
    lore.add(conv(LifeguardListener.getLore()));
    meta.setLore(lore);
    saveRod.setItemMeta(meta);
    return saveRod;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    PluginDescriptionFile pdf = this.m.getDescription();
    if (((sender instanceof Player)) && 
      ((cmd.getName().equalsIgnoreCase("ghub") || cmd.getName().equalsIgnoreCase("goldenhub")) && sender.hasPermission("ghub.command.execute"))) {
      if (args.length == 0)
      {
		    sender.sendMessage("§6§lGHUB RENDSZER §7(korábban PvP Eszközök)");
		    sender.sendMessage("");
		    sender.sendMessage("§e)§6============================§e(");
		    sender.sendMessage("");
		    sender.sendMessage("§7§oPlugin neve: " + pdf.getName());
		    sender.sendMessage("§7§oVerzió: " + pdf.getVersion());
		    sender.sendMessage("§7§oSzerző: " + pdf.getAuthors());
		    sender.sendMessage("");
		    sender.sendMessage("§e)§6============================§e(");
		    sender.sendMessage("");
		    sender.sendMessage("§8§l● §6/" + cmd.getName() + " bounty §7» §rVérdíj szerzése, játékosok megölése után.");
		    sender.sendMessage("      §7Alias: §6/" + cmd.getName() + " bt");
		    sender.sendMessage("§8§l● §6/" + cmd.getName() + " lifeguard §7» §rAz életmentő tárgy beállításai.");
		    sender.sendMessage("      §7Alias: §6/" + cmd.getName() + " lg");
		    sender.sendMessage("§8§l● §6/" + cmd.getName() + " void §7» §rVoid beállítások.");
		    sender.sendMessage("§8§l● §6/" + cmd.getName() + " cspy §7» §rParancs-figyelö mód.");
		    sender.sendMessage("§8§l● §6/" + cmd.getName() + " override §7» §rParancs láthatóság állítása.");
		    sender.sendMessage("");
		    sender.sendMessage("§e)§6============================§e(");
      }
      else if ((args[0].equalsIgnoreCase("bounty") || args[0].equalsIgnoreCase("bt")) && (sender.hasPermission("ghubpvp.command.bounty")))
      {
        if (args.length == 1)
        {
          sender.sendMessage("§6A jelenlegi vérdíj beállítások:");
          sender.sendMessage("§6Állapot: §r" + this.m.getConfig().getBoolean("bounty-enabled"));
          sender.sendMessage("§6Százalékos nyeremény: §r" + this.m.getConfig().getBoolean("reward-by-percent"));
          sender.sendMessage("§6Százalék: §r" + BountyListener.getRewardPercent());
          sender.sendMessage("§6Véletlenszerü nyeremény: §r" + this.m.getConfig().getBoolean("random-reward"));
          sender.sendMessage("§6Prefix: §r" + conv(BountyListener.getPrefix()));
          sender.sendMessage("§6Pénz (nem véletlenszerü jutalom esetén): §r" + conv(Integer.toString(BountyListener.getKillReward())));
          sender.sendMessage("§6Alsó határ: §r" + Integer.toString(this.m.getConfig().getInt("random-min")));
          sender.sendMessage("§6Felsö határ: §r" + Integer.toString(this.m.getConfig().getInt("random-max")));
          sender.sendMessage("§6Gyilkos üzenet példa: §r" + conv(BountyListener.getKillerTextIns()));
          sender.sendMessage("§6Áldozat üzenet példa: §r" + conv(BountyListener.getVictimTextIns()));
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " state: §rVérdíj szerzés lehetőségének be/ki kapcsolása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " prefix: §rVérdíj prefix beállítása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " victim: §rAz áldozatnak kiírt szöveg szerkesztése.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " killer: §rA gyilkosnak kiírt szöveg szerkesztése.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " percentmode: §rSzázalék-alapú pénz szerzés be/ki kapcsolása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " percent: §rSzázalék beállítása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " money <érték>: §rGyilkosságnál kapott/vesztett pénz értékének változtatása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " random: §rVérdíj véletlenszerü értékben való szerzésének be/ki kapcsolása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " min <érték>: §rVéletlenszerü vérdíj szerzés alsó határának beállítása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " max <érték>: §rVéletlenszerü vérdíj szerzés felsö határának beállítása.");
        }
        else if (args[1].equalsIgnoreCase("money"))
        {
          if (args.length == 2) {
            sender.sendMessage("§cKérlek adj meg egy értéket!");
          } else {
            try
            {
              this.m.getConfig().set("killReward", Integer.valueOf(args[2]));
              this.m.saveConfig();
              this.m.reloadConfig();
              sender.sendMessage("§6Az új beállítások: §a" + BountyListener.getKillReward() + " §apénz");
            }
            catch (InputMismatchException e)
            {
              sender.sendMessage("§cA beírt érték nem szám.");
            }
          }
        }
        else if (args[1].equalsIgnoreCase("state"))
        {
          if (this.m.getConfig().getBoolean("bounty-enabled"))
          {
            this.m.getConfig().set("bounty-enabled", Boolean.valueOf(false));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("bounty-enabled"));
          }
          else
          {
            this.m.getConfig().set("bounty-enabled", Boolean.valueOf(true));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("bounty-enabled"));
          }
        }
        else if (args[1].equalsIgnoreCase("random"))
        {
          if (this.m.getConfig().getBoolean("random-reward"))
          {
            this.m.getConfig().set("random-reward", Boolean.valueOf(false));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("random-reward"));
          }
          else
          {
            this.m.getConfig().set("random-reward", Boolean.valueOf(true));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("random-reward"));
          }
        }
        else if (args[1].equalsIgnoreCase("min"))
        {
          if (args.length == 2) {
            sender.sendMessage("§cKérem adja meg az alsó határ értékét!");
          } else {
            try
            {
              this.m.getConfig().set("random-min", Integer.valueOf(args[2]));
              this.m.saveConfig();
              this.m.reloadConfig();
              sender.sendMessage("§6Az új beállítások: §a" + this.m.getConfig().getInt("random-min"));
            }
            catch (InputMismatchException e)
            {
              sender.sendMessage("§cA beírt érték nem szám.");
            }
          }
        }
        else if (args[1].equalsIgnoreCase("max"))
        {
          if (args.length == 2) {
            sender.sendMessage("§cKérem adja meg az felsö határ értékét!");
          } else {
            try
            {
              this.m.getConfig().set("random-max", Integer.valueOf(args[2]));
              this.m.saveConfig();
              this.m.reloadConfig();
              sender.sendMessage("§6Az új beállítások: §a" + this.m.getConfig().getInt("random-max"));
            }
            catch (InputMismatchException e)
            {
              sender.sendMessage("§cA beírt érték nem szám.");
            }
          }
        }
        else if (args[1].equalsIgnoreCase("prefix"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem adja meg a kívánt prefixet!");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String prefix = sb.toString().trim();
            this.m.getConfig().set("bt-prefix", conv(prefix));
            this.m.saveConfig();
            this.m.reloadConfig();
          }
        }
        else if (args[1].equalsIgnoreCase("victim"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem adja meg a kívánt üzenetet. Használhatja a §r%killer% §ckitöltöt, ha szeretné a gyilkos nevét kiíratni, és a §r%money% §ckitöltöt, a pénzérték megszerzéséhez.");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String msg = sb.toString().trim();
            this.m.getConfig().set("victim", conv(msg));
            this.m.saveConfig();
            this.m.reloadConfig();
            String donemsg = conv(this.m.getConfig().getString("victim"));
            donemsg = donemsg.replaceAll("%killer%", conv("GYILKOS"));
            donemsg = donemsg.replaceAll("%money%", conv("PÉNZ"));
            sender.sendMessage("§6Az új üzenet: " + donemsg);
          }
        }
        else if (args[1].equalsIgnoreCase("killer"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem adja meg a kívánt üzenetet. Használhatja a §r%victim% §ckitöltöt, ha szeretné az áldozat nevét kiíratni, és a §r%money% §ckitöltöt, a pénzérték megszerzéséhez.");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String msg = sb.toString().trim();
            this.m.getConfig().set("killer", conv(msg));
            this.m.saveConfig();
            this.m.reloadConfig();
            String donemsg = conv(this.m.getConfig().getString("killer"));
            donemsg = donemsg.replaceAll("%victim%", conv("ÁLDOZAT"));
            donemsg = donemsg.replaceAll("%money%", conv("PÉNZ"));
            sender.sendMessage("§6Az új üzenet: " + donemsg);
          }
        }
        else if (args[1].equalsIgnoreCase("percentmode")){
            if (this.m.getConfig().getBoolean("reward-by-percent"))
            {
              this.m.getConfig().set("reward-by-percent", Boolean.valueOf(false));
              this.m.saveConfig();
              this.m.reloadConfig();
              sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("reward-by-percent"));
            }
            else
            {
              this.m.getConfig().set("reward-by-percent", Boolean.valueOf(true));
              this.m.saveConfig();
              this.m.reloadConfig();
              sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("reward-by-percent"));
            }
        }
        else if (args[1].equalsIgnoreCase("percent"))
        {
          if (args.length == 2) {
            sender.sendMessage("§cKérlek adj meg egy értéket!");
          } else {
            try
            {
              this.m.getConfig().set("reward-percent", Integer.valueOf(args[2]));
              this.m.saveConfig();
              this.m.reloadConfig();
              sender.sendMessage("§6Az új beállítások: §a" + BountyListener.getRewardPercent() + " §aszázalék");
            }
            catch (InputMismatchException e)
            {
              sender.sendMessage("§cA beírt érték nem szám.");
            }
          }
        }
      }
      else if ((args[0].equalsIgnoreCase("lifeguard") || args[0].equalsIgnoreCase("lg")) && (sender.hasPermission("ghubpvp.command.lifeguard")))
      {
        if (args.length == 1)
        {
          sender.sendMessage("§6A jelenlegi életmentő beállítások:");
          sender.sendMessage("§6Prefix: §r" + conv(LifeguardListener.getPrefix()));
          sender.sendMessage("§6Név: §r" + conv(LifeguardListener.getName()));
          sender.sendMessage("§6Leírás: §r" + conv(LifeguardListener.getLore()));
          sender.sendMessage("§6Üzenet: §r" + conv(LifeguardListener.getMessage()));
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " +  args[0] + " get <játékos> [mennyiség]: §rÉletmentő tárgy adása egy játékosnak");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " +  args[0] + " prefix <szöveg>: §rPrefix változtatása");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " +  args[0] + " name <szöveg>: §rNév változtatása");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " +  args[0] + " lore <szöveg>: §rLeírás szerkesztése");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " +  args[0] + " message <szöveg>: §rMentés szövegének változtatása");
        }
        else if (args[1].equalsIgnoreCase("get"))
        {
          try
          {
            if (args.length == 2)
            {
              sender.sendMessage("§cKérem adjon meg a 2. argumentumban egy játékosnevet! (opcionális) 3. argumentumban adjon meg egy mennyiséget 1-től 64-ig!");
            }
            else if (args.length == 3)
            {
            	try{
            		Bukkit.getPlayerExact(args[2].toString()).getInventory().addItem(new ItemStack[] { saveRod(1) });
                    sender.sendMessage("§a1 db életmentő eszköz hozzá lett adva " + args[2] + " eszköztárjához.");
            	}catch(Exception e){
            		sender.sendMessage("§aIlyen játékost nem találtunk a szerveren!");
            	}
            }
            else
            {
            	try{
            		Bukkit.getPlayerExact(args[2].toString()).getInventory().addItem(new ItemStack[] { saveRod(Integer.valueOf(args[3]).intValue()) });
                    sender.sendMessage("§a" + args[3] + " db életmentő eszköz hozzá lett adva " + args[2] + " eszköztárjához.");
            	}catch(Exception e){
            		sender.sendMessage("§aIlyen játékost nem találtunk a szerveren!");
            	}
            }
          }
          catch (Exception e)
          {
            sender.sendMessage("§cHiba történt! Kérem ellenőrizze a beírt adatok pontosságát, és javítsa őket!");
          }
        }
        else if (args[1].equalsIgnoreCase("prefix"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem a következő argumentumban adja meg a kívánt prefixet!");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String prefix = sb.toString().trim();
            this.m.getConfig().set("lg-prefix", prefix);
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§6Az új prefix: §r" + conv(this.m.getConfig().getString("lg-prefix")));
          }
        }
        else if (args[1].equalsIgnoreCase("name"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem a következő argumentumban adja meg a kívánt tárgynevet!");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String name = sb.toString().trim();
            this.m.getConfig().set("name", name);
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§6Az új név: §r" + conv(this.m.getConfig().getString("name")));
          }
        }
        else if (args[1].equalsIgnoreCase("lore"))
        {
          try
          {
            if (args.length == 2)
            {
              sender.sendMessage("§cNem adott meg szöveget! Kérem a következő argumentumban írja be!");
            }
            else
            {
              StringBuilder sb = new StringBuilder();
              for (int i = 2; i < args.length; i++) {
                sb.append(args[i]).append(" ");
              }
              String loreEntry = sb.toString().trim();
              this.m.getConfig().set("lore", loreEntry);
              this.m.saveConfig();
              this.m.reloadConfig();
              sender.sendMessage("§6A frissített leírás-sor: §r" + conv(this.m.getConfig().getString("lore")));
            }
          }
          catch (Exception e)
          {
            sender.sendMessage("§cA szöveg hozzáadása nem sikerült! Kérem pontosítsa a beírt adatokat!");
          }
        }
        else if (args[1].equalsIgnoreCase("message"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cNem adott meg szöveget!");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String message = sb.toString().trim();
            this.m.getConfig().set("savemessage", message);
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§6Az új üzenet: §r" + conv(this.m.getConfig().getString("savemessage")));
          }
        }
      }
      else if ((args[0].equalsIgnoreCase("void")) && (sender.hasPermission("ghubpvp.command.void")))
      {
        if (args.length == 1)
        {
          double x = this.m.getConfig().getInt("x");
          double y = this.m.getConfig().getInt("y");
          double z = this.m.getConfig().getInt("z");
          double pitch = this.m.getConfig().getInt("pitch");
          double yaw = this.m.getConfig().getInt("yaw");
          sender.sendMessage("§6Spawnpoint: §r" + x + ", " + y + ", " + z + ", " + pitch + ", " + yaw);
          sender.sendMessage("§6Prefix: §r" + conv(LifeguardVoidListener.getPrefix()));
          sender.sendMessage("§6Mentö üzenet: §r" + conv(LifeguardVoidListener.getMessage()));
          sender.sendMessage("§6Üzenet: §r" + conv(LifeguardVoidListener.getDenyMessage()));
          sender.sendMessage("§6Void logikai értéke: §r" + LifeguardVoidListener.getState());
          sender.sendMessage("§6LifeGuard összeköttetés logikai értéke: §r" + LifeguardVoidListener.getStateLg());
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " set: §rVoid után respawnolás helyének beállítása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " prefix: §rVoid prefix beállítása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " savemsg: §rlg-hookup-state változó true megléte szükséges! Az életmentö tárgy esetén mentö üzenet beállítása.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " msg: §rEzt az üzenetet küldi ki a plugin, ha nincs életmentö tárgyad vagy az lg-hookup-state változó le van kapcsolva.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " state: §rLogikai változó. Ha be van kapcsolva, void elérésekor visszateleportálsz a spawn helyére.");
          sender.sendMessage("§8§l● §6/" + cmd.getName() + " " + args[0] + " hookup: §rLifeGuard eszközzel való összeköttetés. Ha nincs a játékosnak ilyen eszköze, tárgyait elveszíti.");
        }
        else if (args[1].equalsIgnoreCase("set"))
        {
          Player p = (Player)sender;
          Location pl = p.getLocation();
          double x = pl.getBlockX();
          double y = pl.getBlockY();
          double z = pl.getBlockZ();
          float pitch = pl.getPitch();
          float yaw = pl.getYaw();
          this.m.getConfig().set("x", Double.valueOf(x));
          this.m.getConfig().set("y", Double.valueOf(y));
          this.m.getConfig().set("z", Double.valueOf(z));
          this.m.getConfig().set("pitch", Float.valueOf(pitch));
          this.m.getConfig().set("yaw", Float.valueOf(yaw));
          this.m.saveConfig();
          this.m.reloadConfig();
          p.sendMessage("§aAz új spawnpoint sikeresen be lett állítva!");
        }
        else if (args[1].equalsIgnoreCase("prefix"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem adja meg a kívánt prefixet.");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String prefix = sb.toString().trim();
            this.m.getConfig().set("void-prefix", prefix);
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új prefix: " + conv(this.m.getConfig().getString("void-prefix")));
          }
        }
        else if (args[1].equalsIgnoreCase("savemsg"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem adja meg a kívánt mentö üzenetet.");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String message = sb.toString().trim();
            this.m.getConfig().set("void-savemessage", message);
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új mentö üzenet: " + conv(this.m.getConfig().getString("void-savemessage")));
          }
        }
        else if (args[1].equalsIgnoreCase("msg"))
        {
          if (args.length == 2)
          {
            sender.sendMessage("§cKérem adja meg a kívánt üzenetet.");
          }
          else
          {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
              sb.append(args[i]).append(" ");
            }
            String message = sb.toString().trim();
            this.m.getConfig().set("void-notool-message", message);
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új üzenet: " + conv(this.m.getConfig().getString("void-notool-message")));
          }
        }
        else if (args[1].equalsIgnoreCase("state"))
        {
          if (this.m.getConfig().getBoolean("void-enabled"))
          {
            this.m.getConfig().set("void-enabled", Boolean.valueOf(false));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("void-enabled"));
          }
          else
          {
            this.m.getConfig().set("void-enabled", Boolean.valueOf(true));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("void-enabled"));
          }
        }
        else if (args[1].equalsIgnoreCase("hookup"))
        {
          if (this.m.getConfig().getBoolean("lg-hookup-state"))
          {
            this.m.getConfig().set("lg-hookup-state", Boolean.valueOf(false));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("lg-hookup-state"));
          }
          else
          {
            this.m.getConfig().set("lg-hookup-state", Boolean.valueOf(true));
            this.m.saveConfig();
            this.m.reloadConfig();
            sender.sendMessage("§aAz új érték: §r" + this.m.getConfig().getBoolean("lg-hookup-state"));
          }
        }
      }else if (args[0].equalsIgnoreCase("cspy") && sender.hasPermission("ghub.command.cspy")){
    	  if (!CSPY.spying.contains((Player) sender)){
    		  for (Player p : CSPY.spying){
    			  p.sendMessage("§6[GHUB] [SPY] §a" + sender.getName() + " belépett a parancs-megfigyelö módba.");
    		  }
    		  CSPY.spying.add((Player) sender);
    		  sender.sendMessage("§6[GHUB] [SPY] §rBeléptél a parancs-megfigyelö módba.");
    	  }else{
    		  CSPY.spying.remove((Player) sender);
    		  for (Player p : CSPY.spying){
    			  p.sendMessage("§6[GHUB] [SPY] §a" + sender.getName() + " kilépett a parancs-megfigyelö módból.");
    		  }
    		  sender.sendMessage("§6[GHUB] [SPY] §rKiléptél a parancs-megfigyelö módból.");
    	  }
      }else if (args[0].equalsIgnoreCase("override")){
    	  if (!CSPY.override.contains((Player) sender)){
    		  CSPY.override.add((Player) sender);
    		  sender.sendMessage("§6[GHUB] [SPY] §rImmunis vagy a parancs-megfigyelésre.");
    	  }else{
    		  CSPY.override.remove((Player) sender);
    		  sender.sendMessage("§6[GHUB] [SPY] §rMost már újra láthatóak a parancsaid.");
    	  }
      }else{
    	  StringBuilder sb = new StringBuilder();
    	  for (int i = 0 ; i < args.length ; i++){
    		  sb.append(args[i]).append(" ");
    	  }
    	  String noArg = sb.toString().trim();
    	  sender.sendMessage("§6[GHUB] §rIlyen argumentum nem létezik. §7§l(" + "§r/ghub " + "§r§o" + noArg + "§7§l)");
      }
    }
    return false;
  }
}
