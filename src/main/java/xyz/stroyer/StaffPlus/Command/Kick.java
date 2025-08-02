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
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.GUI.GUIObjects;
import xyz.stroyer.StaffPlus.Main;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Punishments.Punish;
import xyz.stroyer.StaffPlus.Util.Send;

import java.util.Arrays;

public class Kick implements CommandExecutor {
    private Main main;
    public Kick(Main main){this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("Command useable in game only.");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("StaffPlus.Staff")) {
            Send.message(p, "Insufficient permissions. Use /ticket if assistance is required.");
            return true;
        }

        SPlayer staffName = SPlayer.getSPlayer(p);

        if (args.length < 1) {
            Send.message(p, ChatColor.DARK_RED + "Invalid syntax. /kick [player] [reason]");
            return true;
        }

        Player kicked = null;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (pl.getName().equalsIgnoreCase(args[0])) {
                kicked = pl;
            }
        }

        if (kicked == null) {
            Send.message(p, "Could not find player " + args[0]);
            return true;
        }

        SPlayer kickedSP = SPlayer.getSPlayer(kicked);

        String reason;

        if(args.length > 1){
             reason = Arrays.toString(Arrays.copyOfRange(args, 1, args.length - 1));
        }else{
             reason = "Violation of rules";
        }

        Punish.kick(staffName, SPlayer.getSPlayer(kicked), reason);
        Send.message(p, "Player " + args[0] + " kicked for reason: " + args);
        return true;
    }
}
