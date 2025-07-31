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

package xyz.stroyer.StaffPlus.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.stroyer.StaffPlus.Player.SPlayer;

public class EventInventoryClick implements Listener {

    @EventHandler
    public void onClickEvent(InventoryClickEvent e){
        if(SPlayer.getSPlayer((Player) e.getWhoClicked()).getActiveGUIs().size() > 0){
            e.setCancelled(true);
        }
    }
}
