/*
 * Copyright (c) 2025 Stroyer
 *
 * Permission is granted to use, modify, and distribute this plugin, provided that proper credit is given to the author, Stroyer.
 * This includes, but is not limited to, displaying a credit message within the plugin or in the plugin's documentation.
 *
 * This plugin is provided "as-is" without any warranties or guarantees. Use at your own risk.
 */


package xyz.stroyer.StaffPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.stroyer.StaffPlus.Command.PrimaryCommand;
import xyz.stroyer.StaffPlus.Listener.EventInventoryClick;
import xyz.stroyer.StaffPlus.Listener.EventInventoryClose;
import xyz.stroyer.StaffPlus.Listener.EventJoin;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Util.Send;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public static final boolean debugMode = true;

    @Override
    public void onEnable() {

        SPlayer.generateSPlayers();
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());

        getServer().getPluginManager().registerEvents(new EventJoin(), this);
        getServer().getPluginManager().registerEvents(new EventInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new EventInventoryClose(), this);

        this.getCommand("staff").setExecutor(new PrimaryCommand(this));

        Send.console("Thank you for choosing Staff+");
        Send.console("Report any bugs or suggestions at stroyer.xyz");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
