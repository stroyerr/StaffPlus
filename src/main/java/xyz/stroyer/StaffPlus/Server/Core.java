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

package xyz.stroyer.StaffPlus.Server;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Scoreboard.StaffBoard;
import xyz.stroyer.StaffPlus.Util.Send;

public class Core {
    public static void Reload() {
        Send.console("");
        Send.console("");
        Send.console("It is highly likely the server is being /reload(ed), why do you hate yourself?");
        Send.console("Staff+ may not function correctly after a reload. We recommend you restart the server.");
        Send.console("");
        Send.console("");

        for(Player p : Bukkit.getOnlinePlayers()){
            StaffBoard.cancelScoreboardUpdater(SPlayer.getSPlayer(p));
        }
    }
}
