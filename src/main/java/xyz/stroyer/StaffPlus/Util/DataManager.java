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

import xyz.stroyer.StaffPlus.Player.SPlayer;
import xyz.stroyer.StaffPlus.Tickets.Ticket;
import xyz.stroyer.StaffPlus.Tickets.TicketCorrespondence;
import xyz.stroyer.StaffPlus.Tickets.TicketCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static void saveSPData(){

        //Check and create directory

        File dirE = new File("./plugins/Staff+");
        if (!dirE.exists()){
            dirE.mkdir();
            Send.console("Generating directory for Staff+. If this is not your first time running the problem, please report this error at stroyer.xyz");
            Send.console("If you have never used Staff+ before, ignore this message.");
        }else{
            Send.console("Saving Staff+ Data...");
        }

        File dir = new File("./plugins/Staff+/data");
        if (!dir.exists()){
            dir.mkdir();
        }else{}

        //Serialise and write data

        File spdat = new File(dir, "StaffPlus.data");

        if(!spdat.exists()){
            try {
                spdat.createNewFile();
            } catch (IOException e) {}
        }

        try (FileOutputStream fos = new FileOutputStream(spdat);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(new SPDat());
            Send.console("Staff+ data saved.");

        } catch (FileNotFoundException e) {
            Send.console("File not found: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            Send.console("Error while writing data: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public static void loadSPData(){
        SPDat spl;

        File dir = new File("./plugins/Staff+/data");

        if (!dir.exists()){
            dir.mkdir();
            Send.console("Generating directory for Staff+. If this is not your first time running the problem, please report this error at stroyer.xyz");
            Send.console("If you have never used Staff+ before, ignore this message.");
        }else{
            Send.console("Loading Staff+ Data...");
        }

        File spdat = new File(dir, "StaffPlus.data");

        if(!spdat.exists()){
            try {
                spdat.createNewFile();
            } catch (IOException e) {}
        }

        try (FileInputStream fis = new FileInputStream(spdat);
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            spl = (SPDat) ois.readObject();
            spl.load();
            Send.console("Loaded Staff+ data.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }
}
