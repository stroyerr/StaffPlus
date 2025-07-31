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

package xyz.stroyer.StaffPlus.Player;

import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.GUI.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SPlayer {
    private Player BukkitPlayer;
    private List<GUI> activeGUIs;

    public SPlayer(Player BukkitPlayer){
        this.BukkitPlayer = BukkitPlayer;
        this.activeGUIs = new ArrayList<>();
    }

    public Player getBukkitPlayer(){
        return this.BukkitPlayer;
    }

    public List<GUI> getActiveGUIs(){
        return this.activeGUIs;
    }

    public void closeCurrentGUI(){
        this.BukkitPlayer.closeInventory();
        this.activeGUIs.clear();
    }

    public void openGUI(GUI gui){
        this.closeCurrentGUI();
        this.activeGUIs.add(gui);
        this.getBukkitPlayer().openInventory(gui.getInventory());
    }
}
