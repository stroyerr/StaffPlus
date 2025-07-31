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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.GUI.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SPlayer {
    private UUID UUID;
    private List<GUI> activeGUIs;

    private static List<SPlayer> SPlayerList;

    public SPlayer(Player BukkitPlayer){
        this.UUID = BukkitPlayer.getUniqueId();
        this.activeGUIs = new ArrayList<>();
        SPlayerList.add(this);
    }

    public SPlayer(UUID UUID){
        this.UUID = UUID;
        this.activeGUIs = new ArrayList<>();
        SPlayerList.add(this);
    }

    public Player getBukkitPlayer(){
        return Bukkit.getPlayer(this.UUID);
    }

    public List<GUI> getActiveGUIs(){
        return this.activeGUIs;
    }

    public void closeCurrentGUI(){
        this.getBukkitPlayer().closeInventory();
        this.activeGUIs.clear();
    }

    public void openGUI(GUI gui){
        this.closeCurrentGUI();
        this.activeGUIs.add(gui);
        this.getBukkitPlayer().openInventory(gui.getInventory());
    }

    public static void generateSPlayers(){
        SPlayerList = new ArrayList<>();
    }

    public UUID getUUID(){
    return this.UUID;
    }

    public static SPlayer getSPlayer(Player player){
        for(SPlayer sp : SPlayerList){
            if(sp.getUUID().equals(player.getUniqueId())){
                return sp;
            }
        }
        return null;
    }

    public static boolean hasSPlayer(Player player){
        for (SPlayer sp : SPlayerList){
            if(sp.getUUID().equals(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public void inventoryClosed(){
        this.closeCurrentGUI();
        this.activeGUIs.clear();
    }
}
