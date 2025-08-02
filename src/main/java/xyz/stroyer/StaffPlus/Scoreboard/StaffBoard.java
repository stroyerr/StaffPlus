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

package xyz.stroyer.StaffPlus.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;
import xyz.stroyer.StaffPlus.Main;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Util.StaffData;

public class StaffBoard {
    public static void showScoreboard(SPlayer player) {

        // Set up scoreboard
        StaffBoard.updateScoreboard(player);

        //Reset player scheduler id
        player.setStaffBoardSchedulerIndex(-1);

        //Start scheduler
        StaffBoard.startScoreboardUpdater(player);
    }

    public static void updateScoreboard(SPlayer player){

        // Get the scoreboard manager
        ScoreboardManager manager = Bukkit.getScoreboardManager();

        // Create or get the scoreboard
        Scoreboard scoreboard = manager.getNewScoreboard();

        // Create an objective (the name is for internal use, the display name is visible to players)
        Objective title = scoreboard.registerNewObjective("title", "dummy", ChatColor.GREEN + "Staff Board");
        title.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Add scores to the objective
        Score staffOnline = title.getScore(ChatColor.YELLOW + "Staff Online: " + ChatColor.GOLD + StaffData.getStaffOnline().size());
        staffOnline.setScore(5); // Example score

        Score playersOnline = title.getScore(ChatColor.YELLOW + "Players Online: " + ChatColor.GOLD + Bukkit.getOnlinePlayers().size());
        playersOnline.setScore(6);

        Score openTickets = title.getScore(ChatColor.YELLOW + "Open Tickets: " + ChatColor.GOLD + "DUMMY");
        openTickets.setScore(4); // Example score

        Score onlineFor = title.getScore(ChatColor.YELLOW + "Server Uptime: " + ChatColor.GOLD + StaffData.serverUptime());
        onlineFor.setScore(7);

        Score mutedPlayers = title.getScore(ChatColor.YELLOW + "Muted Players Online: " + ChatColor.GOLD + "DUMMY");
        mutedPlayers.setScore(2);

        Score reports = title.getScore(ChatColor.YELLOW + "Pending Reports: " + ChatColor.GOLD + "DUMMY");
        reports.setScore(3);

        Score TPS = title.getScore(ChatColor.YELLOW + "Server TPS: " + ChatColor.GOLD + "DUMMY");
        TPS.setScore(1);

        //Unhappy with below field, may revisit
        /*Score sessionSupervisor = title.getScore(ChatColor.GOLD + "Session Supervisors: ");
        sessionSupervisor.setScore(-1);

        Score sessionSupervisorText = title.getScore(ChatColor.GOLD + StaffData.getSupervisorText() );
        sessionSupervisorText.setScore(-2);*/

        // Set the player's scoreboard
        player.getBukkitPlayer().setScoreboard(scoreboard);


    }

    public static void hideScoreboard(SPlayer sp){
        sp.getBukkitPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        //cancelScoreboardUpdater(sp); - Not working
    }

    public static void cancelScoreboardUpdater(SPlayer sp) {
        if(sp.getStaffBoardSchedulerIndex() == -1){return;}
        Bukkit.getScheduler().cancelTask(sp.getStaffBoardSchedulerIndex());
        sp.setStaffBoardSchedulerIndex(-1);
    }

    public static void startScoreboardUpdater(final SPlayer sPlayer) {
        BukkitTask br = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                updateScoreboard(sPlayer);// Call the method to update the scoreboard
            }
        }, 0L, 200L);
        sPlayer.setStaffBoardSchedulerIndex(br.getTaskId());
    }

    public static void reloadProtect() {

    }
}
