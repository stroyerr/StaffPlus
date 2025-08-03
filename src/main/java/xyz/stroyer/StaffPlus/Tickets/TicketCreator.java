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

public class TicketCreator {
    private int type; // 0 == request, 1 == report
    private SPlayer user; // user filing ticket
    private String summary; // summary of reason for ticket
    private boolean awaitingInput; // awaiting user to enter summary in chat

    public static void initiate(SPlayer sp){
        TicketCreator tc = new TicketCreator(sp);
    }

    public TicketCreator(SPlayer sp){
        this.user = sp;
        this.type = 0;
        this.summary = "Please enter a summary.";
        this.awaitingInput = false;
        sp.setTicketBeingCreated(this);
    }

    public TicketCreator (int type, SPlayer user, String summary){
        this.type = type;
        this.user = user;
        this.summary = summary;
        this.awaitingInput = true;
        user.setTicketBeingCreated(this);
    }

    public boolean isAwaitingInput(){
        return this.awaitingInput;
    }

    public void setAwaitingInput(boolean awaitingInput) {
        this.awaitingInput = awaitingInput;
    }

    public SPlayer getSPlayer() {
        return this.user;
    }

    public int getType() {
        return this.type;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setSummary (String summary){
        this.summary = summary;
    }
}
