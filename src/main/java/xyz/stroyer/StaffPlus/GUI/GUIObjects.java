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
import xyz.stroyer.StaffPlus.Tickets.Ticket;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondence;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondenceEditor;
import xyz.stroyer.StaffPlus.Tickets.TicketCreator;
import xyz.stroyer.StaffPlus.Util.*;

import java.util.ArrayList;
import java.util.List;

public class GUIObjects {

    // /staff page

    public static GUI StaffLandingPage(SPlayer sp) {
        List<GUISet> guiset = new ArrayList<>(45);
        ItemStack itemToAdd;
        for(int i = 0; i < 45; i++){
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "Staff+");
            if(i == 8){
                itemToAdd = NewItem.createGuiItem(Material.SLIME_BALL, "Toggle Sidebar");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui = new GUI("Staff+", sp, 45, false, guiset);
        return gui;
    }



    // /ticket landing page
    public static GUI TicketLandingPage(SPlayer sp) {
        List<GUISet> guiset = new ArrayList<>(45);
        ItemStack itemToAdd;
        for(int i = 0; i < 45; i++){
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + " ");
            if(i == 10){
                itemToAdd = NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.YELLOW + "" + ChatColor.BOLD + "My Tickets", "Manage and view my tickets");
            }
            if(i == 12){
                itemToAdd = NewItem.createGuiItem(Material.BOOK, ChatColor.YELLOW + "" + ChatColor.BOLD + "Command Help", "Show available commands");
            }
            if(i==14){
                itemToAdd = NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL,ChatColor.YELLOW + "" + ChatColor.BOLD + "OPEN NEW TICKET", "Open a ticket to receive direct assistance from Staff");
            }
            if(i == 16){
                itemToAdd = NewItem.createGuiItem(Material.NAME_TAG, ChatColor.YELLOW + "" + ChatColor.BOLD + "Staff Online", StaffData.getStaffOnline().size() + " staff currently online");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui = new GUI("Support Ticket", sp, 27, false, guiset);
        return gui;
    }

    // /ticket new ticket page
    public static GUI NewTicket(TicketCreator tc) {
        List<GUISet> guiset = new ArrayList<>(36);
        ItemStack itemToAdd;
        for(int i = 0; i < 36; i++){
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + " ");
            if(i == 10){
                if(tc.getType() == 0){
                    itemToAdd = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "" + ChatColor.BOLD + "REQUEST ASSISTANCE", "Selected");
                }else{
                    itemToAdd = NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.RED + "" + ChatColor.BOLD + "REQUEST ASSISTANCE", "Click to select");
                }
                  }
            if(i == 19){
                if(tc.getType() == 0) {
                    itemToAdd = NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.RED + "" + ChatColor.BOLD + "REPORT SOMETHING", "Click to select");

                }else{
                    itemToAdd = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "" + ChatColor.BOLD + "REPORT SOMETHING", "Selected");
                }
                   }
            if(i==12){
                if(tc.getSummary().length() < 31) {
                    itemToAdd = NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL,ChatColor.YELLOW + "" + ChatColor.BOLD + "BRIEF EXPLANATION", "Click to edit: " + tc.getSummary());
                }else{
                    itemToAdd = NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL,ChatColor.YELLOW + "" + ChatColor.BOLD + "BRIEF EXPLANATION", "Click to edit: " + tc.getSummary().substring(0, 30) + "...");
                }
            }
            if(i == 14){
                itemToAdd = NewItem.createGuiItem(Material.NAME_TAG, ChatColor.YELLOW + "" + ChatColor.BOLD + "Staff Online", StaffData.getStaffOnline().size() + " staff currently online");
            }
            if(i == 16){
                itemToAdd = NewItem.createGuiItem(Material.MAP, ChatColor.GREEN + "" + ChatColor.BOLD + "Submit Ticket", ChatColor. DARK_GREEN + "Our staff will respond as soon as possible.");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui = new GUI("Create New Ticket", tc.getSPlayer(), 36, false, guiset);
        return gui;
    }

    // /ticket my tickets menu
    public static GUI MyTicketsLanding(SPlayer sp) {
        List<GUISet> guiset = new ArrayList<>(27);
        ItemStack itemToAdd;
        for(int i = 0; i < 27; i++){
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "");
            if(i == 11){
                itemToAdd = NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.GREEN + "Open Tickets", ChatColor.WHITE + "" + sp.getOpenTickets().size() + " open tickets");
            }
            if(i == 15){
                itemToAdd = NewItem.createGuiItem(Material.MAP, ChatColor.YELLOW + "Closed Tickets", ChatColor.WHITE + "" + sp.getClosedTickets().size() + " closed tickets");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui = new GUI("My Tickets", sp, 27, false, guiset);
        return gui;
    }

    // /ticket open tickets page

    public static GUI MyOpenTickets(SPlayer sp, int page) {
        if(page <1){
            page = 1;
        }
        List<GUISet> guiset = new ArrayList<>(36);
        ItemStack itemToAdd;
        for(int i = 0; i < 36; i++){
            int tick = 27*(page-1) + i;
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "");
            if(i == 35){
                if(sp.getOpenTickets().size() > page*27) {
                    itemToAdd = NewItem.createGuiItem(Material.ARROW, ChatColor.YELLOW + "Next Page");
                }
            }
            if(i == 34){
                if(page>1){
                    itemToAdd = NewItem.createGuiItem(Material.ARROW, ChatColor.YELLOW + "Previous Page");
                }
            }
            if(i == 27){
                itemToAdd = NewItem.createGuiItem(Material.BARRIER, ChatColor.GRAY + "Back");
            }
            if((sp.getOpenTickets().size() > tick && (!(i % 27 == 0))) || i==0 && sp.getOpenTickets().size() > tick){
                itemToAdd = NewItem.createGuiItem(Material.WRITTEN_BOOK, ChatColor.GOLD + "Ticket " + ChatColor.YELLOW + "#" + sp.getOpenTickets().get(tick).getIdentifier(), ChatColor.WHITE + "" + SPTime.timeElapsedDHM(sp.getOpenTickets().get(i).getTimeCreated()) + " old." + ChatColor.GRAY + " Click for details.");
            }
            if(i == 0 && sp.getTickets().size() == 0){
                itemToAdd = NewItem.createGuiItem(Material.WRITTEN_BOOK, ChatColor.GOLD + "NO OPEN TICKETS", "You either have no tickets or your ticket is now closed. Check your closed tickets.");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui = new GUI("My Open Tickets", sp, 36, false, guiset, page);
        return gui;
    }

    // /ticket ticket viewer

    public static GUI TicketViewer(SPlayer sp, String ticketNo) {
        if (Ticket.getTicket(ticketNo) == null) {
            Send.debug("Ticket value returned null when attempting to open TicketViewer. Process cancelled.");
            return GUIObjects.TicketLandingPage(sp);
        }
        Ticket t = Ticket.getTicket(ticketNo);
        List<GUISet> guiset = new ArrayList<>(27);
        ItemStack itemToAdd;
        for(int i = 0; i < 27; i++){
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + " ");
            if(i == 10){
                if(t.getStatus() == 0){
                    itemToAdd = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.WHITE + "Status: " +ChatColor.GREEN + "" + ChatColor.BOLD + "OPEN", ChatColor.GRAY + "Outcome: " + t.getOutcomeStr());
                }
                if(t.getStatus() == 1){
                    itemToAdd = NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.WHITE + "Status: " +ChatColor.RED + "" + ChatColor.BOLD + "CLOSED", ChatColor.GRAY + "Outcome: " + t.getOutcomeStr());
                }
            }
            if(i == 12){
                itemToAdd = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.WHITE + "" + ChatColor.BOLD + "User: " + ChatColor.YELLOW + sp.getUsername(), ChatColor.GRAY + "Staff Member: " + t.getStaffMemberStr());
            }
            if(i==14){
                itemToAdd = NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL,ChatColor.WHITE + "" + ChatColor.BOLD + "Messages", ChatColor.GRAY + "Ticket opened: " +SPTime.formatDate(t.getTimeCreated()));
            }
            if(i == 16){
                itemToAdd = NewItem.createGuiItem(Material.ANVIL, ChatColor.WHITE + "" + ChatColor.BOLD + "Actions", ChatColor.GRAY + "Close, reopen or send a message on this ticket to the staff member.");
            }
            if(i == 18){
                itemToAdd = NewItem.createGuiItem(Material.BARRIER, ChatColor.YELLOW + "Back");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui;

        if(t.getType() == 0){
            gui = new GUI("G/Ticket #" + t.getIdentifier() + " - SUPPORT", sp, 27, false, guiset);
        }else{
            gui = new GUI("G/Ticket #" + t.getIdentifier() + " - REPORT", sp, 27, false, guiset);
        }
        return gui;
    }

    // /ticket message list
    public static GUI TicketMessageList(SPlayer sp, Ticket t, int page) {
        List<GUISet> guiset = new ArrayList<>(36);
        ItemStack itemToAdd;
        for(int i = 0; i < 35; i++){
            int correctedI = (page-1) * 27 + i;
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "");
            if(correctedI < t.getTicketCorrespondence().size()){
                TicketCorrespondence cor = t.getTicketCorrespondence().get(correctedI);
                itemToAdd = NewItem.createGuiItem(Material.PAPER, ChatColor.YELLOW + "MSG# " + ChatColor.GOLD + "" + cor.getIdentifier(), ChatColor.WHITE + "" + cor.getSender().getUsername() + ", " + ChatColor.GRAY + "" + SPTime.formatDate(cor.getTime()));
                guiset.add(new GUISet(i,itemToAdd));
            }
            if(i == 33 && page > 1){
                itemToAdd = NewItem.createGuiItem(Material.ARROW, ChatColor.GRAY + "Previous Page");
            }
            if(i == 26){
                itemToAdd = NewItem.createGuiItem(Material.BARRIER, ChatColor.GRAY + "Back");
            }
            if(i == 27){
                itemToAdd = NewItem.createGuiItem(Material.SNOWBALL, ChatColor.GRAY + "New Message");
            }
            if(i == 34 && t.getTicketCorrespondence().size() > page*27){
                itemToAdd = NewItem.createGuiItem(Material.ARROW, ChatColor.GRAY + "Next Page");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui = new GUI("" + t.getIdentifier() + " Messages", sp, 36, false, guiset);
        return gui;
    }

    //New Message Landing Page
    public static GUI NewMessageLandingPage(SPlayer sp, Ticket t) {
        List<GUISet> guiset = new ArrayList<>(27);
        ItemStack itemToAdd;
        TicketCorrespondenceEditor tce = sp.getTicketCorrespondenceEditor();
        for(int i = 0; i < 27; i++){
            itemToAdd = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.GRAY + "");
            if(i == 18){
                itemToAdd = NewItem.createGuiItem(Material.BARRIER, ChatColor.GRAY + "Back");
            }
            if(i == 11){
                itemToAdd = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.YELLOW + "Sender: " + sp.getUsername());
            }
            if(i == 13){
                if(tce.getMessage().length() < 30){
                    itemToAdd = NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL, ChatColor.YELLOW + "Click to edit message", ChatColor.GOLD + "" + tce.getMessage());
                }else{
                    itemToAdd = NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL, ChatColor.YELLOW + "Click to edit message", ChatColor.GOLD + "" + tce.getMessage().substring(0, 29) + "...");
                }
            }
            if(i == 15){
                itemToAdd = NewItem.createGuiItem(Material.PAPER, ChatColor.GREEN + "" + ChatColor.BOLD + "Send Message", ChatColor.WHITE + "This message will be sent to staff to review");
            }
            guiset.add(new GUISet(i,itemToAdd));
        }

        GUI gui = new GUI("New Message//" + t.getIdentifier(), sp, 27, false, guiset);
        return gui;
    }
}
