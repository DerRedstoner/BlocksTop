package de.derredstoner.blockstop.handler;

import org.bukkit.ChatColor;

public class ChatHandler {

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
