package com.stimonz.pvpsystem;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R1.PlayerConnection;

public class TitleAPI {
    public static void sendPacket(Player player, Object packet) {
        try {
                Object handle = player.getClass().getMethod("getHandle").invoke(player);
                Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
                playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
       
        catch (Exception e) {
                e.printStackTrace();
        }
}

public static Class<?> getNMSClass(String name) {
        // org.bukkit.craftbukkit.v1_8_R3...
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
                return Class.forName("net.minecraft.server." + version + "." + name);
        }
       
        catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
        }
}
    
    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
    	try{
	    	fadeIn = fadeIn * 20;
	    	stay = stay * 20;
	    	fadeOut = fadeOut * 20;
	        CraftPlayer craftplayer = (CraftPlayer) player;
	        PlayerConnection connection = craftplayer.getHandle().playerConnection;
	        IChatBaseComponent titleJSON = ChatSerializer.a("{'text': '" + title + "'}");
	        IChatBaseComponent subtitleJSON = ChatSerializer.a("{'text': '" + subtitle + "'}");
	        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleJSON, fadeIn, stay, fadeOut);
	        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleJSON);
	        connection.sendPacket(titlePacket);
	        connection.sendPacket(subtitlePacket);
    	}catch(Exception e){}
    }
}
