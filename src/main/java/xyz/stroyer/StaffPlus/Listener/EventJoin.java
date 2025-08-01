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

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.stroyer.StaffPlus.Main;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Scoreboard.StaffBoard;
import xyz.stroyer.StaffPlus.Util.Send;

public class EventJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(SPlayer.hasSPlayer(e.getPlayer())){
            Send.debug("Player " + e.getPlayer().getName() + " with UUID " + e.getPlayer().getUniqueId() + " already has an SPlayer profile.");
        }else{
            SPlayer sp = new SPlayer(e.getPlayer());
            Send.debug("Player " + e.getPlayer().getName() + " with UUID " + e.getPlayer().getUniqueId() + " does not have an SPlayer. One has been generated.");
        }

        SPlayer.getSPlayer(e.getPlayer()).updateUsername();

        SPlayer.getSPlayer(e.getPlayer()).generateGUIList();

        SPlayer.getSPlayer(e.getPlayer()).setJoinTimestamp(System.currentTimeMillis());

        if (SPlayer.getSPlayer(e.getPlayer()).boardEnabled() && e.getPlayer().hasPermission("StaffPlus.Staff")){
            StaffBoard.showScoreboard(SPlayer.getSPlayer(e.getPlayer()));
        }

        SPlayer sp = SPlayer.getSPlayer(e.getPlayer());
        if(sp.getBukkitPlayer().hasPermission("StaffPlus.Staff")){
            sp.setStaff(true);
        }else{
            sp.setStaff(false);
        }
    }
}