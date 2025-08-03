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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ticket implements Serializable {
    private SPlayer user; // user creating ticket
    private int type; // 0 = request, 1 = report
    private List<TicketCorrespondence> ticketCorrespondence; // list of each message sent
    private long timeCreated; // time the ticket was created by the user
    private SPlayer staffMember; // staff member who accepted and is responsible for the ticket
    private int status; // 0 = open, 1 = closed
    private int outcome; // 0 = awaiting allocation, 1 = under review, 2 = resolved, 3 = duplicate, 4 = junk
    private String ticketId; // identifier

    private static List<Ticket> allTickets;

    //Special use case, created without summary
    public Ticket (SPlayer user, int type) {
        this.user = user;
        this.type = type;
        this.ticketCorrespondence = new ArrayList<>();
        this.timeCreated = System.currentTimeMillis();
        this.staffMember = null;
        this.status = 0;
        this.outcome = 0;
        this.generateIdentifier();
        this.addTicketCorrespondence(new TicketCorrespondence(user, this, "Ticket opened by " + user.getUsername()));
        allTickets.add(this);
    }

    //Standard use case, created with brief summary
    public Ticket (SPlayer user, int type, String initSummary) {
        this.user = user;
        this.type = type;
        this.ticketCorrespondence = new ArrayList<>();
        this.timeCreated = System.currentTimeMillis();
        this.staffMember = null;
        this.status = 0;
        this.outcome = 0;
        this.generateIdentifier();
        this.addTicketCorrespondence(new TicketCorrespondence(user, this, initSummary));
        allTickets.add(this);
    }

    public static List<Ticket> getTickets() {
        return allTickets;
    }

    public static void setTickets(List<Ticket> tickets){
        allTickets = tickets;
    }

    public static Ticket getTicket(String ticketNo) {
        for (Ticket t: allTickets){
            if(ticketNo.equalsIgnoreCase(t.getIdentifier())){
                return t;
            }
        }
        return null;
    }

    private void generateIdentifier() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder ticketID = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            ticketID.append(characters.charAt(randomIndex));
        }
        this.ticketId = "TK" + ticketID.toString();
    }

    public String getIdentifier() {
        return this.ticketId;
    }

    public static void init(){
        allTickets = new ArrayList<>();
    }

    // Getter and Setter for user
    public SPlayer getUser() {
        return user;
    }

    public void setUser(SPlayer user) {
        this.user = user;
    }

    // Getter and Setter for type
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    // Getter and Setter for ticketCorrespondence
    public List<TicketCorrespondence> getTicketCorrespondence() {
        return ticketCorrespondence;
    }

    public void setTicketCorrespondence(List<TicketCorrespondence> ticketCorrespondence) {
        this.ticketCorrespondence = ticketCorrespondence;
    }

    public void addTicketCorrespondence(TicketCorrespondence tc){
        if(this.containsAlreadyCor(tc)){
            return;
        }
        this.ticketCorrespondence.add(tc);
    }

    private boolean containsAlreadyCor(TicketCorrespondence tcc) {
        for (TicketCorrespondence tc : this.getTicketCorrespondence()) {
            if(tc.getIdentifier().equalsIgnoreCase(tcc.getIdentifier())){
                return true;
            }
        }
        return false;
    }

    // Getter and Setter for timeCreated
    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    // Getter and Setter for staffMember
    public SPlayer getStaffMember() {
        return staffMember;
    }

    public void setStaffMember(SPlayer staffMember) {
        this.staffMember = staffMember;
    }

    // Getter and Setter for status
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Getter and Setter for outcome
    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public String getOutcomeStr() {
        return switch (this.getOutcome()) {
            case 0 -> "Awaiting Allocation";
            case 1 -> "Under Review";
            case 2 -> "Resolved";
            case 3 -> "Duplicate";
            case 4 -> "Junk";
            default -> "Invalid Outcome Identifier";
        };
    }

    public String getStaffMemberStr() {
        if (this.getStaffMember() == null) {
            return "Awaiting Allocation";
        }
        return this.getStaffMember().getUsername();
    }

    public void newCorrespondence(TicketCorrespondence ticketCorrespondence) {

    }
}
