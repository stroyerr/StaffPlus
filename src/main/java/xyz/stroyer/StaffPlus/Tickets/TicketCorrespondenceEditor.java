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

import xyz.stroyer.StaffPlus.Player.SPlayer;

import java.io.Serializable;

public class TicketCorrespondenceEditor implements Serializable {
    private Ticket ticket;
    private SPlayer sender;
    private String message;
    private boolean awaitingChat;

    public TicketCorrespondenceEditor(Ticket t, SPlayer sp){
        this.ticket = t;
        this.sender = sp;
        this.message = "No message content";
        this.awaitingChat = false;
    }

    public boolean isAwaitingChat() {
        return awaitingChat;
    }

    public void setAwaitingChat(boolean awaitingChat) {
        this.awaitingChat = awaitingChat;
    }

    public SPlayer getSender() {
        return sender;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setMessage(String s) {
        this.message = s;
    }

    public String getMessage(){
        return this.message;
    }
}
