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

import xyz.stroyer.StaffPlus.Player.MailItem;
import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Tickets.Ticket;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondence;
import xyz.stroyer.StaffPlus.Tickets.TicketCreator;

import java.io.Serializable;
import java.util.List;

//SPDat serialises and saves SPlayerList, Messages, Mail and Tickets

public class SPDat implements Serializable {
    private List<SPlayer> sPlayerList;
    private List<TicketCorrespondence> ticketCorrespondences;
    private List<Ticket> tickets;
    private List<MailItem> mail;

    public SPDat(){
        this.sPlayerList = SPlayer.getSPlayerList();
        this.ticketCorrespondences = TicketCorrespondence.getAllCorrespondence();
        this.tickets = Ticket.getTickets();
        this.mail = MailItem.getAllMail();
    }

    public void load(){
        SPlayer.setSPlayerList(this.sPlayerList);
        TicketCorrespondence.setAllCorrespondence(this.ticketCorrespondences);
        Ticket.setTickets(this.tickets);
        MailItem.setAllMail(this.mail);
    }
}
