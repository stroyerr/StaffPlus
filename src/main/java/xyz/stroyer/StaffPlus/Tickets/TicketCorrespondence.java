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

package xyz.stroyer.StaffPlus.Tickets;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Util.SPTime;
import xyz.stroyer.StaffPlus.Util.Send;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketCorrespondence implements Serializable {
    private long time;
    private SPlayer sender;
    private String message;
    private Ticket ticket;
    private String identifier;

    private static List<TicketCorrespondence> allCorrespondence;

    public TicketCorrespondence (SPlayer sender, Ticket ticket, String message){
        this.time = System.currentTimeMillis();
        this.ticket = ticket;
        this.message = message;
        this.sender = sender;
        this.generateIdentifier();
        if(!this.alreadyExists()){
            allCorrespondence.add(this);
        }
    }

    public TicketCorrespondence(TicketCorrespondenceEditor tce) {
        this.time = System.currentTimeMillis();
        this.ticket = tce.getTicket();
        this.message = tce.getMessage();
        this.sender = tce.getSender();
        this.generateIdentifier();
        if(!this.alreadyExists()){
            allCorrespondence.add(this);
        }
    }

    private boolean alreadyExists() {
        for (TicketCorrespondence tc : allCorrespondence){
            if (tc.getIdentifier().equalsIgnoreCase(this.getIdentifier())){
                return true;
            }
        }
        return false;
    }

    public static void init(){
        allCorrespondence = new ArrayList<>();
    }

    public static List<TicketCorrespondence> getAllCorrespondence(){
        return allCorrespondence;
    }

    public static void setAllCorrespondence(List<TicketCorrespondence> cor){
        allCorrespondence = cor;
    }

    public static TicketCorrespondence getTicketCorrespondence(String msId) {
        for (TicketCorrespondence tc: allCorrespondence){
            if(tc.getIdentifier().equalsIgnoreCase(msId)){
                return tc;
            }
        }
        return null;
    }

    public void readCorrespondence(SPlayer sp) {
        Player p = sp.getBukkitPlayer();
        p.sendMessage(ChatColor.RED + "=========================================");
        p.sendMessage(ChatColor.WHITE + "Ticket #" + this.getTicket().getIdentifier());
        p.sendMessage(ChatColor.GRAY + "Message #" + this.getIdentifier());
        p.sendMessage(ChatColor.GRAY + "From: " + this.getSender().getUsername() + ", Time: " + SPTime.formatDate(this.getTime()));
        p.sendMessage(ChatColor.YELLOW + this.message);
        Send.clickable(p, ChatColor.GOLD + "" + ChatColor.BOLD + "Click here to return to this ticket", "/ticket execDir " + this.getTicket().getIdentifier(), false);
        p.sendMessage(ChatColor.RED + "=========================================");
    }


    private void generateIdentifier() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder ticketID = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            ticketID.append(characters.charAt(randomIndex));
        }
        this.identifier = "MS" + ticketID.toString();
    }

    public long getTime() {
        return time;
    }

    public SPlayer getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
