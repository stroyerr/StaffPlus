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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Scoreboard.StaffBoard;
import xyz.stroyer.StaffPlus.Util.NewItem;
import xyz.stroyer.StaffPlus.Util.Send;

public class EventInventoryClick implements Listener {

    @EventHandler
    public void onClickEvent(InventoryClickEvent e){
        if(!(SPlayer.getSPlayer((Player) e.getWhoClicked()).getActiveGUIs().size() > 0)) {
            return;
        }
            e.setCancelled(true);
        SPlayer sp = SPlayer.getSPlayer((Player) e.getWhoClicked());
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem().equals(NewItem.createGuiItem(Material.SLIME_BALL, "Toggle Sidebar"))){
            if(sp.boardEnabled()){
                sp.setBoardEnabled(false);
                StaffBoard.hideScoreboard(sp);
                Send.message(p, "Sidebar disabled.");
            }else{
                sp.setBoardEnabled(true);
                StaffBoard.showScoreboard(sp);
                Send.message(p,"Sidebar enabled.");
            }
        }
    }
}
