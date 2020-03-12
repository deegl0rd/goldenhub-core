package com.stimonz.pvpsystem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.stimonz.pvpsystem.Boosters.BoosterCmdExecutor;
import com.stimonz.pvpsystem.Bounty.BountyListener;
import com.stimonz.pvpsystem.Features.CSPY;
import com.stimonz.pvpsystem.Lifeguard.LifeguardListener;
import com.stimonz.pvpsystem.Lifeguard.LifeguardVoidListener;
import com.stimonz.pvpsystem.Terminal.Secret;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin{
	public static Main ins;
	
	@Override
	public void onEnable(){
			ins = this;
			setupEconomy();
			saveDefaultConfig();
			PluginDescriptionFile pdf = super.getDescription();
			getServer().getPluginManager().registerEvents(new BountyListener(Main.ins), this);
			getServer().getPluginManager().registerEvents(new LifeguardListener(Main.ins), this);
			getServer().getPluginManager().registerEvents(new LifeguardVoidListener(Main.ins), this);
			getServer().getPluginManager().registerEvents(new CSPY(Main.ins), this);
			getCommand("ghub").setExecutor(new MainCommand(this));
			getCommand("goldenhub").setExecutor(new MainCommand(this));
			getCommand("aldas").setExecutor(new BoosterCmdExecutor(this));
			getCommand("term").setExecutor(new Secret(this));
			this.addDefaultSpawnConfig();
			System.out.println("[" + pdf.getName()+ "]" + " elindult. | Verzio: " + pdf.getVersion() + " | Fejleszto: " + pdf.getAuthors());
	}
	@Override
	public void onDisable(){
		PluginDescriptionFile pdf = super.getDescription();
		System.out.println("[" + pdf.getName()+ "]" + " leallt. | Verzio: " + pdf.getVersion() + " | Fejleszto: " + pdf.getAuthors());
	}
	
	public boolean ifAnySpawnLocEmpty(){
		if (getConfig().getDouble("x") == 0.0F 
				|| getConfig().getDouble("y")  == 0.0F 
				|| getConfig().getDouble("z") == 0.0F
				|| (float) getConfig().getDouble("pitch")  == 0.0F
				|| (float) getConfig().getDouble("yaw") == 0.0F ){
			return true;
		}else{
			return false;
		}
	}
	
	  public void addDefaultSpawnConfig()
	  {
	    PluginDescriptionFile pdf = super.getDescription();
	    double x = getConfig().getDouble("x");
	    double y = getConfig().getDouble("y");
	    double z = getConfig().getDouble("z");
	    float pitch = (float)getConfig().getDouble("pitch");
	    float yaw = (float)getConfig().getDouble("yaw");
	    System.out.println("[" + pdf.getName() + "]" + " Alapertelmezett void spawnpoint ellenorzese...");
	    if (ifAnySpawnLocEmpty())
	    {
	      System.out.println("[" + pdf.getName() + "]" + " Nem talaltunk beallitott spawnpointot a config fileban!");
	      System.out.println("[" + pdf.getName() + "]" + " Automatikus spawnpoint beallitas megkezdese a world spawnpoint alapjan...");
	      getConfig().set("x", Integer.valueOf(Bukkit.getWorld("world").getSpawnLocation().getBlockX()));
	      getConfig().set("y", Integer.valueOf(Bukkit.getWorld("world").getSpawnLocation().getBlockY()));
	      getConfig().set("z", Integer.valueOf(Bukkit.getWorld("world").getSpawnLocation().getBlockZ()));
	      getConfig().set("pitch", Float.valueOf(Bukkit.getWorld("world").getSpawnLocation().getPitch()));
	      getConfig().set("yaw", Float.valueOf(Bukkit.getWorld("world").getSpawnLocation().getYaw()));
	      saveConfig();
	      reloadConfig();
	      System.out.println("[" + pdf.getName() + "]" + " Az eljaras befejezodott! Spawn: [" + x + ", " + y + ", " + z + ", " + pitch + ", " + yaw + "]");
	    }
	    else
	    {
	      System.out.println("[" + pdf.getName() + "]" + " Talaltunk beallitott spawnpointot! Spawn: [" + x + ", " + y + ", " + z + ", " + pitch + ", " + yaw + "]");
	    }
	  }
	
	public static Economy economy = null;
	private boolean setupEconomy()
	{
	    RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	    if (economyProvider != null) {
	        economy = economyProvider.getProvider();
	    }

	    return (economy != null);
	}
}
