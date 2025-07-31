/*
 * Copyright (c) 2025 Stroyer
 *
 * Permission is granted to use, modify, and distribute this plugin, provided that proper credit is given to the author, Stroyer.
 * This includes, but is not limited to, displaying a credit message within the plugin or in the plugin's documentation.
 *
 * This plugin is provided "as-is" without any warranties or guarantees. Use at your own risk.
 */


package xyz.stroyer.StaffPlus.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.GUI.GUIObjects;
import xyz.stroyer.StaffPlus.Main;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Util.Send;

public class PrimaryCommand implements CommandExecutor {
    private Main main;
    public PrimaryCommand(Main main){this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            Bukkit.getLogger().info("Command useable in game only.");
                    return true;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("StaffPlus.Staff")){
            Send.message(p, "Insufficient permissions. Use /ticket if assistance is required.");
            return true;
        }

        SPlayer.getSPlayer(p).openGUI(GUIObjects.StaffLandingPage(SPlayer.getSPlayer(p)));
            return true;
        }
}
