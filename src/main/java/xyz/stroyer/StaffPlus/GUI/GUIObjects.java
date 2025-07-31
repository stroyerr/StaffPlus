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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Util.FillBlank;
import xyz.stroyer.StaffPlus.Util.NewItem;

import java.util.ArrayList;
import java.util.List;

public class GUIObjects {
    public GUI StaffLandingPage(SPlayer sp) {
        List<GUISet> guiset = new ArrayList<>();
        ItemStack itemToAdd;
        for(int i = 0; i < 27; i++){
            if(i == 8){
                itemToAdd = NewItem.createGuiItem(Material.SLIME_BALL, "Toggle Sidebar");
                guiset.set(i, new GUISet(i, itemToAdd));
            }


        }

        GUI gui = new GUI("Staff+", sp, 27, false, guiset);
        return gui;
    }
}
