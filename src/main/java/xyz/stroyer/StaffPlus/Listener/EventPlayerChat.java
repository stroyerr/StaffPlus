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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.stroyer.StaffPlus.GUI.GUIObjects;
import xyz.stroyer.StaffPlus.Main;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondenceEditor;
import xyz.stroyer.StaffPlus.Tickets.TicketCreator;
import xyz.stroyer.StaffPlus.Util.Send;

public class EventPlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat (AsyncPlayerChatEvent e){

        SPlayer sp = SPlayer.getSPlayer(e.getPlayer());

        //Ticket creation input
        if(sp.getTicketBeingCreated() == null && sp.getTicketCorrespondenceEditor() == null){
            return;
        }

        if(sp.getTicketCorrespondenceEditor() == null){
            if(sp.getTicketBeingCreated().isAwaitingInput()){
                e.setCancelled(true);
                TicketCreator tc = sp.getTicketBeingCreated();
                tc.setSummary(ChatColor.stripColor(e.getMessage()));
                tc.setAwaitingInput(false);
                sp.setTicketBeingCreated(tc);
                //Manage inventory from main thread
                Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        sp.openGUI(GUIObjects.NewTicket(tc));
                    }
                });
                return;
            }
        }

        if(sp.getTicketBeingCreated() == null) {
            if (sp.getTicketCorrespondenceEditor().isAwaitingChat()) {
                e.setCancelled(true);
                TicketCorrespondenceEditor tce = sp.getTicketCorrespondenceEditor();
                tce.setMessage(e.getMessage());
                tce.setAwaitingChat(false);
                sp.setTicketCorrespondenceEditor(tce);
                Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        sp.openGUI(GUIObjects.NewMessageLandingPage(sp,tce.getTicket()));
                    }
                });
                return;
            }
        }


    }
}
