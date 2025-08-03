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

import java.text.SimpleDateFormat;
import java.util.Date;

public class SPTime {
    public static String timeElapsedDHM (long timeStart, long timeFinish){
            long elapsedMillis = timeFinish - timeStart;
            long minutes = (elapsedMillis / (1000 * 60)) % 60;
            long hours = (elapsedMillis / (1000 * 60 * 60));
            long days = Math.round((hours / 24));
            String timeFormatted = String.format("%01dd, %02dh, %02dm", days, hours, minutes);
            return timeFormatted;
    }

    public static String timeElapsedDHM (long timeStart){
        long elapsedMillis = System.currentTimeMillis() - timeStart;
        long minutes = (elapsedMillis / (1000 * 60)) % 60;
        long hours = (elapsedMillis / (1000 * 60 * 60));
        long days = Math.round((hours / 24));
        String timeFormatted = String.format("%01dd, %02dh, %02dm", days, hours, minutes);
        return timeFormatted;
    }

    public static String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

}
