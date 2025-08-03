/*
 *
 *  * Copyright (c) 2025 Stroyer
 *  *
 *  * Permission is granted to use, modify, and distribute this plugin, provided that proper credit is given to the author, Stroyer.
 *  * This includes, but is not limited to, displaying a credit message within the plugin or in the plugin's documentation.
 *  *
 *  * This plugin is provided "as-is" without any warranties or guarantees. Use at your own risk.
 *
 *
 */

package xyz.stroyer.StaffPlus.Util;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.Main;

public class Send {

    public static String prefix = ChatColor.GOLD + "Staff+" + ChatColor.RED + "//" + ChatColor.GRAY + " ";

    public static void message(Player player, String message){
        player.sendMessage(prefix + message);
    }

    public static void console(String message){
        Bukkit.getLogger().info("STAFF+: " + message);
    }

    public static void debug(String message){
        if(Main.debugMode) {
            Bukkit.getLogger().warning("[DEBUG] STAFF+: " + message);
        }
    }

    public static void discrete(Player player, String message){
        player.sendMessage(message);
    }

    public static void clickable(Player player, String ms, String command, boolean prefixREQ){
        if(prefixREQ){
            ms = prefix + ms;
        }
        ComponentBuilder message = new ComponentBuilder(ms);
        BaseComponent[] msg = message.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command)).create();

        player.spigot().sendMessage(msg);
    }

//    public static void clickablePlayerSendChat(Player player, String ms, String toSay){
//        if(prefixREQ){
//            ms = prefix + ms;
//        }
//        ComponentBuilder message = new ComponentBuilder(ms);
//        BaseComponent[] msg = message.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command)).create();
//
//        player.spigot().sendMessage(msg);
//    }
}
