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
import xyz.stroyer.StaffPlus.Player.SPlayer;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        SPlayerList = new ArrayList<>();
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }
}
