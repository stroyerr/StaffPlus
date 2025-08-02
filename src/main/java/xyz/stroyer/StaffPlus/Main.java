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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.stroyer.StaffPlus.Command.Kick;
import xyz.stroyer.StaffPlus.Command.PrimaryCommand;
import xyz.stroyer.StaffPlus.Listener.EventInventoryClick;
import xyz.stroyer.StaffPlus.Listener.EventInventoryClose;
import xyz.stroyer.StaffPlus.Listener.EventJoin;
import xyz.stroyer.StaffPlus.Listener.EventPlayerLeave;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Server.Core;
import xyz.stroyer.StaffPlus.Util.DataManager;
import xyz.stroyer.StaffPlus.Util.Send;
import xyz.stroyer.StaffPlus.Util.StaffData;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    // DebugMode must be FALSE for any and all releases
    public static final boolean debugMode = true;

    private static Plugin instance;
    private static boolean isReload = false;

    public static Plugin getInstance() {
        return instance;
    }

    public void onLoad() {
        isReload = true;
    }

    @Override
    public void onEnable() {

        instance = this;

        isReload = false;

        //Set start time
        StaffData.serverStartTime = (System.currentTimeMillis());

        //Load SPlayer Data

        SPlayer.generateSPlayers();
        DataManager.loadSPData();

        //Register events

        getServer().getPluginManager().registerEvents(new EventJoin(), this);
        getServer().getPluginManager().registerEvents(new EventInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new EventInventoryClose(), this);
        getServer().getPluginManager().registerEvents(new EventPlayerLeave(), this);

        //Register commands

        this.getCommand("staff").setExecutor(new PrimaryCommand(this));

        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            // Dekayed Registration
            getCommand("kick").setExecutor(new Kick(this));
        }, 40L); // Delay by 1 tick

        //Start complete

        Send.console("Thank you for choosing Staff+");
        Send.console("Report any bugs or suggestions at stroyer.xyz");
    }

    @Override
    public void onDisable() {

        //Save SP Data

        DataManager.saveSPData();

        //Check if server is being reloaded. Certain code must be executed if so.
        if (isReload) {
            // This is a reload
            Bukkit.getLogger().info("Plugin is being reloaded.");
            Core.Reload();
        }
        //Shut down complete

        Bukkit.getLogger().info(ChatColor.RED + "Shut down complete. Thanks for choosing Staff+");
    }
}
