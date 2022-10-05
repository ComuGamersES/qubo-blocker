package com.comugamers.quboblocker.util;

import org.bukkit.ChatColor;

import java.util.List;

public class TextUtils {

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> colorize(List<String> text) {
        text.forEach(TextUtils::colorize);
        return text;
    }
}
