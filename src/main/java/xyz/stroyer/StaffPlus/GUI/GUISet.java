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

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class GUISet {
    private int index;
    private ItemStack item;

    public GUISet(int index, ItemStack item){
        this.index = index;
        this.item = item;
    }

    public int getInt(){
        return this.index;
    }

    public ItemStack getItem(){
        return this.item;
    }
}
