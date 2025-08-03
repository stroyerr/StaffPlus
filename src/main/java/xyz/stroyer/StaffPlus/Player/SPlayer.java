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

package xyz.stroyer.StaffPlus.Player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.stroyer.StaffPlus.GUI.GUI;
import xyz.stroyer.StaffPlus.Tickets.Ticket;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondenceEditor;
import xyz.stroyer.StaffPlus.Tickets.TicketCreator;
import xyz.stroyer.StaffPlus.Util.Send;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SPlayer implements Serializable {
    private UUID UUID;
    private String username;
    private transient List<GUI> activeGUIs;
    private static List<SPlayer> SPlayerList;
    private boolean showBoard;
    private boolean isStaff;
    private List<PunishEvent> punishments;
    private int staffBoardSchedulerIndex;
    private long joinTimestamp;
    private transient TicketCreator ticketBeingCreated;
    private List<Ticket> tickets;
    private TicketCorrespondenceEditor ticketCorrespondenceEditor;
    private List<MailItem> mailItems;

    public static List<SPlayer> getSPlayerList(){
        return SPlayerList;
    }

    public static void setSPlayerList(List<SPlayer> spl){
        SPlayerList = spl;
    }

    public SPlayer(Player BukkitPlayer){
        this.UUID = BukkitPlayer.getUniqueId();
        this.activeGUIs = new ArrayList<>();
        SPlayerList.add(this);
        if(BukkitPlayer.hasPermission("StaffPlus.Staff")){
            this.showBoard = true;
            this.isStaff = true;
        }else{
            this.showBoard = false;
            this.isStaff = false;
            this.punishments = new ArrayList<>();
        }
        this.staffBoardSchedulerIndex = -1;
        this.tickets = new ArrayList<>();
        this.mailItems = new ArrayList<>();
    }

    public int getStaffBoardSchedulerIndex(){
        return this.staffBoardSchedulerIndex;
    }

    public void setStaffBoardSchedulerIndex(int i){
        this.staffBoardSchedulerIndex = i;
    }

    public SPlayer(UUID UUID){
        this.UUID = UUID;
        this.activeGUIs = new ArrayList<>();
        SPlayerList.add(this);
        if(Bukkit.getPlayer(UUID).hasPermission("StaffPlus.Staff")){
            this.showBoard = true;
            this.isStaff = true;
        }else{
            this.showBoard = false;
            this.isStaff = false;
            this.punishments = new ArrayList<>();
        }
        this.staffBoardSchedulerIndex = -1;
        this.tickets = new ArrayList<>();
        this.mailItems = new ArrayList<>();
    }

    public Player getBukkitPlayer(){
        return Bukkit.getPlayer(this.UUID);
    }

    public List<GUI> getActiveGUIs(){
        return this.activeGUIs;
    }

    public void closeCurrentGUI(){
        this.getBukkitPlayer().closeInventory();
        this.activeGUIs = new ArrayList<>();
    }

    public void setTicketBeingCreated(TicketCreator tc) {
        this.ticketBeingCreated = tc;
    }

    public void setTicketCorrespondenceEditor(TicketCorrespondenceEditor tce) {
        this.ticketCorrespondenceEditor = tce;
    }

    public TicketCorrespondenceEditor getTicketCorrespondenceEditor(){
        return this.ticketCorrespondenceEditor;
    }

    public TicketCreator getTicketBeingCreated() {
        return this.ticketBeingCreated;
    }

    public void openGUI(GUI gui){
        this.closeCurrentGUI();
        this.activeGUIs.add(gui);
        this.getBukkitPlayer().openInventory(gui.getInventory());
    }

    public static void generateSPlayers(){
        SPlayerList = new ArrayList<>();
    }

    public UUID getUUID(){
    return this.UUID;
    }

    public static SPlayer getSPlayer(Player player){
        for(SPlayer sp : SPlayerList){
            if(sp.getUUID().equals(player.getUniqueId())){
                return sp;
            }
        }
        return null;
    }

    public static boolean hasSPlayer(Player player){
        for (SPlayer sp : SPlayerList){
            if(sp.getUUID().equals(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public void inventoryClosed(){
        this.activeGUIs = new ArrayList<>();
    }

    public void generateGUIList() {
        this.activeGUIs = new ArrayList<>();
    }

    public boolean boardEnabled(){
        return this.showBoard;
    }

    public void setBoardEnabled(boolean b) {
        this.showBoard = b;
    }

    public void setStaff(boolean b) {
        this.isStaff = b;
    }

    public boolean isStaff(){
        return this.isStaff;
    }

    public void recordPunishment(String type, SPlayer staffMember, String reason){
        this.punishments.add(new PunishEvent(type, LocalDateTime.now(), staffMember, reason));
        Send.debug("Recorded punishment: TYPE - " + type + "; TIME - " + LocalDateTime.now().toString() + "; STAFF - " + staffMember + "; REASON - " + reason);
        Send.debug("Player " + this.getUUID() + " now has " + this.punishments.size() + " punishments recorded.");
    }

    public void updateUsername(){
        this.username = this.getBukkitPlayer().getName();
    }

    public String getUsername(){
        return this.username;
    }

    /* public void assignStaffBoardSchedulerIndex() {
        int id = 0;
        List<Integer> activeIds = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()){
            SPlayer sp = SPlayer.getSPlayer(p);
            if(sp.getStaffBoardSchedulerIndex() == id) {
                activeIds.add(id);
                id ++;
                if(activeIds.contains(id)){
                    id++;
                }
            }
        }
        if (!activeIds.contains(id)){
            this.setStaffBoardSchedulerIndex(id);
        }else{
            this.setStaffBoardSchedulerIndex(-2);
        }
    } */ //Redundant code

    public void setJoinTimestamp(long timestamp) {
        this.joinTimestamp = timestamp;
    }

    public long getJoinTimestamp() {
        return this.joinTimestamp;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public List<Ticket> getTickets(){
        return this.tickets;
    }

    public List<Ticket> getOpenTickets(){
        List<Ticket> ts = new ArrayList<>();
        for (Ticket t: this.tickets) {
            if (t.getStatus() == 0) {
                ts.add(t);
            }
        }
        return ts;
    }

    public List<Ticket> getClosedTickets(){
        List<Ticket> ts = new ArrayList<>();
        for (Ticket t: this.tickets) {
            if (t.getStatus() == 1) {
                ts.add(t);
            }
        }
        return ts;
    }
}
