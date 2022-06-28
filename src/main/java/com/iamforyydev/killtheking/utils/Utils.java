package com.iamforyydev.killtheking.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class Utils {

    public static String toLegacyColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendBroadcast(String message){
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(toLegacyColor(message)));
    }

    public static void sendSound(Sound sound, int volume, int pitch){
        Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), sound, volume, pitch));

    }

    public static String getTime(int time){
        int hours = time / 3600;
        int i = time - hours * 3600;
        int minutes = i / 60;
        int seconds = i - minutes * 60;
        String secondsMsg = "", minutesMsg = "", hoursMsg = "";
        if(seconds >= 0 && seconds <= 9){
            secondsMsg = "0"+seconds+"s";
        }else{
            secondsMsg = seconds+"s";
        }
        if(minutes >= 0 && minutes <= 9){
            minutesMsg = "0"+minutes+"m";
        }else{
            minutesMsg = minutes+"m";
        }
        if(hours >= 0 && hours <= 9){
            hoursMsg = "0"+hours+"h";
        }else{
            hoursMsg = hours+"h";
        }
        if(hours != 0) {
            return hoursMsg + " " + minutesMsg + " " + secondsMsg;
        }else if(minutes != 0) {
            return minutesMsg + " " + secondsMsg;
        }
        return secondsMsg;
    }


}
