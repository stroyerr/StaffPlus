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

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class MailItem implements Serializable {
    private String identifier;
    private SPlayer owner;
    private String message;
    private boolean read;
    private static List<MailItem> allMail;

    public MailItem(SPlayer owner, String message){
        this.owner = owner;
        this.message = message;
        this.read = false;
        this.generateIdentifier();
        allMail.add(this);
    }

    public static List<MailItem> getAllMail() {
        return allMail;
    }

    public static void setAllMail(List<MailItem> mi) {
        allMail = mi;
    }

    private void generateIdentifier() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder ticketID = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            ticketID.append(characters.charAt(randomIndex));
        }
        this.identifier = "ML" + ticketID.toString();
    }
}
