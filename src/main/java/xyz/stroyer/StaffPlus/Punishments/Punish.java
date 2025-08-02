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

package xyz.stroyer.StaffPlus.Punishments;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import xyz.stroyer.StaffPlus.Main;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Util.Send;

public class Punish {
    public static void kick(SPlayer sender, SPlayer kicked, String reason){
        if(!Main.debugMode){
            if(sender.getUUID().equals(kicked.getUUID())){
                Send.message(sender.getBukkitPlayer(), "You cannot kick this player.");
                return;
            }
        }
        if(kicked.getBukkitPlayer().hasPermission("StaffPlus.Staff") && !sender.getBukkitPlayer().hasPermission("StaffPlus.Admin")){
            Send.message(sender.getBukkitPlayer(), "This player is a staff member. Permission \"StaffPlus.Admin\" required to kick this user.");
            return;
        }
        kicked.getBukkitPlayer().kickPlayer(ChatColor.RED + "You have been KICKED: " + ChatColor.GOLD + reason);
        kicked.recordPunishment("KICK", sender, reason);
    }
}
