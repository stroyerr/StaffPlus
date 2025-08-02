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
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Scoreboard.StaffBoard;

public class EventPlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        SPlayer sp = SPlayer.getSPlayer(e.getPlayer());
        if((!sp.isStaff() || !sp.boardEnabled())){
            return;
        }
        StaffBoard.cancelScoreboardUpdater(sp);
    }

    @EventHandler
    public void onPlayerKicked(PlayerKickEvent e){
        SPlayer sp = SPlayer.getSPlayer(e.getPlayer());
        if((!sp.isStaff() || !sp.boardEnabled())){
            return;
        }
        StaffBoard.cancelScoreboardUpdater(sp);
    }

}
