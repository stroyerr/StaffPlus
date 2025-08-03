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

package xyz.stroyer.StaffPlus.GUI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Util.FillBlank;

import java.util.ArrayList;
import java.util.List;

public class GUI {
    public  String name; // name of GUI for reference
    public  SPlayer owner; // owner of active GUI;
    public  int size; // number of inventory slots
    public  boolean adminRequired; // whether StaffPlus.admin is required to access
    public  List<GUISet> GUISet = new ArrayList<>(); // set of all GUI icons and their positions
    public Inventory inventory; // inventory to be displayed and interacted with
    public int page; // for menus with multiple pages, 0 by default

    public GUI(String name, SPlayer owner, int size, boolean adminRequired, List<GUISet> GUISet){
        this.name = name;
        this.owner = owner;
        this.size = size;
        this.adminRequired = adminRequired;
        this.GUISet = GUISet;
        this.generateInventory();
        this.page = 0;
    }

    public GUI(String name, SPlayer owner, int size, boolean adminRequired, List<GUISet> GUISet, int page){
        this.name = name;
        this.owner = owner;
        this.size = size;
        this.adminRequired = adminRequired;
        this.GUISet = GUISet;
        this.generateInventory();
        this.page = page;
    }

    private void generateInventory(){
        Inventory inv = Bukkit.createInventory(null, this.size, this.name);
        for(int i = 0; i < size; i++){
            inv.setItem(i, this.GUISet.get(i).getItem());
        }
        inv = FillBlank.updateInventory(inv);
        this.inventory = inv;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public String getName() {
        return this.name;
    }
}
