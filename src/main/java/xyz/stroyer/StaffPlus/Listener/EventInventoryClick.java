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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.stroyer.StaffPlus.GUI.GUIObjects;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Scoreboard.StaffBoard;
import xyz.stroyer.StaffPlus.Tickets.Ticket;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondence;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondenceEditor;
import xyz.stroyer.StaffPlus.Tickets.TicketCreator;
import xyz.stroyer.StaffPlus.Util.NewItem;
import xyz.stroyer.StaffPlus.Util.Send;

public class EventInventoryClick implements Listener {

    @EventHandler
    public void onClickEvent(InventoryClickEvent e){

        //Check if player has an active GUI, cancel if so
        if(!(SPlayer.getSPlayer((Player) e.getWhoClicked()).getActiveGUIs().size() > 0)) {
            return;
        }
            e.setCancelled(true);

        //Only executes with a GUI active
        SPlayer sp = SPlayer.getSPlayer((Player) e.getWhoClicked());
        Player p = (Player) e.getWhoClicked();

        // Cancel if PlaceHolder
        if(e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)){
            e.setCancelled(true);
            return;
        }

        //GUI: Staff menu

        //Toggle sidebar

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
            return;
        }

        // GUI: Ticket Menu Interactables

        // Open New Ticket
        if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL, ChatColor.YELLOW + "" + ChatColor.BOLD + "OPEN NEW TICKET", "Open a ticket to receive direct assistance from Staff"))){
            TicketCreator tc = new TicketCreator(sp);
            sp.openGUI(GUIObjects.NewTicket(tc));
            return;
        }

        // View My Tickets

        if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.YELLOW + "" + ChatColor.BOLD + "My Tickets", "Manage and view my tickets"))){
            sp.openGUI(GUIObjects.MyTicketsLanding(sp));
            return;
        }

        // View Open Tickets

        if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.GREEN + "Open Tickets", ChatColor.WHITE + "" + sp.getOpenTickets().size() + " open tickets"))){
            sp.openGUI(GUIObjects.MyOpenTickets(sp, 1));
            sp.getActiveGUIs().get(0).page = 1;
            return;
        }

        // Open Ticket Page Menu

        if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.ARROW, ChatColor.YELLOW + "Next Page"))){
            sp.openGUI(GUIObjects.MyOpenTickets(sp, sp.getActiveGUIs().get(0).page + 1));
            return;
        }

        if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.ARROW, ChatColor.YELLOW + "Previous Page"))){
            sp.openGUI(GUIObjects.MyOpenTickets(sp, sp.getActiveGUIs().get(0).page - 1));
            return;
        }

        // Open Individual Ticket from Page Menu

        if(sp.getActiveGUIs().get(0).getName().equalsIgnoreCase("My Open Tickets") || sp.getActiveGUIs().get(0).getName().equalsIgnoreCase("My Closed Tickets")){
            if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).length() < 14){
                if(e.getCurrentItem().getType().equals(Material.BARRIER)){
                    sp.openGUI(GUIObjects.MyTicketsLanding(sp));
                    return;
                }
            }
            String ticketId = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).substring(8,15);
            sp.openGUI(GUIObjects.TicketViewer(sp, ticketId));
            return;
        }

        // Open Messages Landing from Ticket Page
        if(sp.getActiveGUIs().get(0).getName().length() >11){
            if(sp.getActiveGUIs().get(0).getName().substring(0, 12).equalsIgnoreCase("G/Ticket #TK")){
                if(e.getCurrentItem().getType().equals(Material.BARRIER)){
                    sp.openGUI(GUIObjects.MyOpenTickets(sp, 1));
                    return;
                }
                String ticketId = sp.getActiveGUIs().get(0).getName().substring(10,17);
                sp.openGUI(GUIObjects.TicketMessageList(sp, Ticket.getTicket(ticketId), 1));
                return;
            }
        }


        // Open Individual Message from Landing Page
        if(e.getCurrentItem().getItemMeta().getDisplayName().length() > 6){
            if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().substring(0,7)).equalsIgnoreCase("MSG# ")){
                String msId = "" + ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().substring(9,16));
                TicketCorrespondence cor = TicketCorrespondence.getTicketCorrespondence(msId);
                cor.readCorrespondence(sp);
                sp.closeCurrentGUI();
                return;
            }
        }

        // Go back from Message List
        if(sp.getActiveGUIs().get(0).getName().length() == 16){
            if(sp.getActiveGUIs().get(0).getName().substring(8, 16).equalsIgnoreCase("Messages")){
                //Player is on Message List
                if(e.getCurrentItem().getType().equals(Material.BARRIER)){
                    sp.openGUI(GUIObjects.TicketViewer(sp, sp.getActiveGUIs().get(0).getName().substring(0,7)));
                    return;
                }
                if(e.getCurrentItem().getType().equals(Material.SNOWBALL)){
                    TicketCorrespondenceEditor tce = new TicketCorrespondenceEditor(Ticket.getTicket(sp.getActiveGUIs().get(0).getName().substring(0,7)), sp);
                    sp.setTicketCorrespondenceEditor(tce);
                    sp.openGUI(GUIObjects.NewMessageLandingPage(sp, Ticket.getTicket(sp.getActiveGUIs().get(0).getName().substring(0,7))));
                }
            }
        }

        // GUI: New message w/i ticket
        //name format "New Message//" + t.getIdentifier()
        if(sp.getActiveGUIs().get(0).getName().length() == 20){
            if(sp.getActiveGUIs().get(0).getName().substring(0,13).equalsIgnoreCase("New Message//")){
                //Player is in a New Message GUI
                String id = sp.getActiveGUIs().get(0).getName().substring(13,20);
                Send.debug(id);
                if(e.getCurrentItem().getType().equals(Material.BARRIER)){
                    sp.openGUI(GUIObjects.TicketViewer(sp,id));
                    return;
                }
                if(e.getCurrentItem().getType().equals(Material.WRITABLE_BOOK)){
                    sp.getTicketCorrespondenceEditor().setAwaitingChat(true);
                    sp.closeCurrentGUI();
                    Send.discrete(p, ChatColor.GOLD + "Type out your message for the ticket and then press enter.");
                    return;
                }
                if(e.getCurrentItem().getType().equals(Material.PAPER)){
                    Ticket.getTicket(id).newCorrespondence(new TicketCorrespondence(sp.getTicketCorrespondenceEditor()));
                }
            }
        }


        // GUI: Ticket creator

        // Ticket type
        if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.RED + "" + ChatColor.BOLD + "REPORT SOMETHING", "Click to select")) || e.getCurrentItem().equals(NewItem.createGuiItem(Material.REDSTONE_BLOCK, ChatColor.RED + "" + ChatColor.BOLD + "REQUEST ASSISTANCE", "Click to select"))){
            TicketCreator tcCurrent = sp.getTicketBeingCreated();
            if(tcCurrent.getType() == 1) {
                tcCurrent.setType(0);
            }else{
                tcCurrent.setType(1);
            }

            sp.setTicketBeingCreated(tcCurrent);
            sp.openGUI(GUIObjects.NewTicket(tcCurrent));
            return;
        }

        // Ticket summary edit
        if(sp.getTicketBeingCreated() != null){
            TicketCreator tc = sp.getTicketBeingCreated();
            if(tc.getSummary().length() < 31) {
                if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL,ChatColor.YELLOW + "" + ChatColor.BOLD + "BRIEF EXPLANATION", "Click to edit: " + tc.getSummary()))){
                    tc.setAwaitingInput(true);
                    sp.setTicketBeingCreated(tc);
                    sp.closeCurrentGUI();
                    Send.discrete(p,ChatColor.RED + "" + ChatColor.BOLD + "Enter a brief summary regarding your ticket. You can include more information once connected to a staff member. Press enter when complete. Press enter to cancel.");
                    return;
                }
            }else{
                if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.LEGACY_BOOK_AND_QUILL,ChatColor.YELLOW + "" + ChatColor.BOLD + "BRIEF EXPLANATION", "Click to edit: " + tc.getSummary().substring(0, 30) + "..."))){
                    tc.setAwaitingInput(true);
                    sp.setTicketBeingCreated(tc);
                    sp.closeCurrentGUI();
                    Send.discrete(p,ChatColor.RED + "" + ChatColor.BOLD + "Enter a brief summary regarding your ticket. You can include more information once connected to a staff member. Press enter when complete. Press enter to cancel.");
                    return;
                }
            }
        }

        // Create ticket
        if(e.getCurrentItem().equals(NewItem.createGuiItem(Material.MAP, ChatColor.GREEN + "" + ChatColor.BOLD + "Submit Ticket", ChatColor. DARK_GREEN + "Our staff will respond as soon as possible."))){
            sp.addTicket(new Ticket (sp, sp.getTicketBeingCreated().getType(), sp.getTicketBeingCreated().getSummary()));
            Send.discrete(sp.getBukkitPlayer(), ChatColor.GREEN + "Your ticket has been submitted and will be actioned by a staff member as soon as possible. Track your progress via /ticket.");
            sp.closeCurrentGUI();
            return;
        }


    }
}
