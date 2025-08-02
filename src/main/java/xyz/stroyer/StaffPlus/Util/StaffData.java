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

package xyz.stroyer.StaffPlus.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import xyz.stroyer.StaffPlus.Player.SPlayer;

import java.util.ArrayList;
import java.util.List;

public class StaffData {
    public static long serverStartTime;
    public static List<SPlayer> getStaffOnline(){
        List<SPlayer> staffOnline = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()){
            SPlayer sp = SPlayer.getSPlayer(p);
            if(sp.isStaff()){
                staffOnline.add(sp);
            }
        }
        return staffOnline;
    }

    public static int getTPS(){
        return 20;
    }

    public static List<SPlayer> getAdminsOnline(){
        List<SPlayer> staffOnline = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()){
            SPlayer sp = SPlayer.getSPlayer(p);
            if(sp.isStaff()){
                staffOnline.add(sp);
            }
        }
        return staffOnline;
    }

    //Get highest supervisor returns an integer representing the highest level of staff member online. 0 = no staff, 1 = StaffPlus.Staff, 2 = StaffPlus.Admin

    public static int getHighestSupervisor(){
        if(getAdminsOnline().size() > 0){
            return 2;
        }
        if(getStaffOnline().size() > 0){
            return 1;
        }
        return 0;
    }

    public static String getSupervisorText() {
        String supervisorText = "";
        if(getHighestSupervisor() == 0) {
            return "No staff online";
        }
        if(getHighestSupervisor() == 1){
            supervisorText = "Staff: ";
            for (SPlayer sp : getStaffOnline()){
                supervisorText = supervisorText + sp.getUsername() + " // ";
            }
        }

        if(getHighestSupervisor() == 2){
            supervisorText = "Admins: ";
            for (SPlayer sp : getAdminsOnline()){
                supervisorText = supervisorText + sp.getUsername() + " // ";
            }
        }

        return supervisorText;
    }

    public static String timeElapsedCurrentSessionForPlayer(SPlayer sp) {
        long elapsedMillis = System.currentTimeMillis() - sp.getJoinTimestamp();
        long seconds = (elapsedMillis / 1000) % 60;
        long minutes = (elapsedMillis / (1000 * 60)) % 60;
        long hours = (elapsedMillis / (1000 * 60 * 60));
        String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeFormatted;
    }

    public static String serverUptime() {
        long elapsedMillis = System.currentTimeMillis() - serverStartTime;
        long seconds = (elapsedMillis / 1000) % 60;
        long minutes = (elapsedMillis / (1000 * 60)) % 60;
        long hours = (elapsedMillis / (1000 * 60 * 60));
        String timeFormatted = String.format("%02d:%02d h", hours, minutes);
        return timeFormatted;
    }
}
