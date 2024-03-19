package icu.yfd.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    String message;
    String prefix = "";
    int brackets = 90;
    int time = 37;
    int color = 0;

    public Logger(String message) {
        this.message = message;
    }

    public Logger(String message, String prefix) {
        this.message = message;
        this.prefix = prefix;
    }

    public void log() {
        if (color == 0) color = 92; // Default
        System.out.println("\u001b[" + brackets + "m[\u001b[" + time + "m" + (prefix.isEmpty() ? "" : prefix + " ") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\u001b[" + color + "m" + " LOGS" + "\u001b[" + brackets + "m] \u001b[0m" + message + "\u001b[0m");
    }
}