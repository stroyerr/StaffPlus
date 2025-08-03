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
import xyz.stroyer.StaffPlus.Command.CommandKick;
import xyz.stroyer.StaffPlus.Command.CommandStaff;
import xyz.stroyer.StaffPlus.Command.CommandTicket;
import xyz.stroyer.StaffPlus.Listener.*;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Server.Core;
import xyz.stroyer.StaffPlus.Tickets.Ticket;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondence;
import xyz.stroyer.StaffPlus.Util.DataManager;
import xyz.stroyer.StaffPlus.Util.Send;
import xyz.stroyer.StaffPlus.Util.StaffData;

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

        //Load Data
        Ticket.init();
        TicketCorrespondence.init();
        SPlayer.generateSPlayers();

        DataManager.loadSPData();

        //Register event listeners
        getServer().getPluginManager().registerEvents(new EventJoin(), this);
        getServer().getPluginManager().registerEvents(new EventInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new EventInventoryClose(), this);
        getServer().getPluginManager().registerEvents(new EventPlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new EventPlayerChat(), this);

        //Register commands
        this.getCommand("staff").setExecutor(new CommandStaff(this));
        this.getCommand("ticket").setExecutor(new CommandTicket(this));

        //Register priority commands
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            getCommand("kick").setExecutor(new CommandKick(this));
        }, 40L);

        //Start complete
        Send.console("Thank you for choosing Staff+, report any bugs at stroyer.xyz");
    }

    @Override
    public void onDisable() {

        //Save Data
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
