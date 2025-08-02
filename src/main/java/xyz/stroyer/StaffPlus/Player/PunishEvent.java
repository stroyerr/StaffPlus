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
import java.time.LocalDateTime;
import java.util.Date;

public class PunishEvent implements Serializable {
    public String type;
    public LocalDateTime dateTime;
    public SPlayer staffMember;
    public String reason;

    public PunishEvent(String type, LocalDateTime dateTime, SPlayer staffMember, String reason){
        this.type = type;
        this.dateTime =dateTime;
        this.staffMember = staffMember;
        this.reason = reason;
    }
}
