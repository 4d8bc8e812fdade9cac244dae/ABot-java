package icu.yfd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static List<Bot> botList = new ArrayList<>();
    public static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        botList.add(new Bot("play.kaboom.pw", 25565, ""));
        botList.add(new Bot("yfd.icu", 25565, ""));
        botList.add(new Bot("chipmunk.land", 25565, ""));
        botList.add(new Bot("24.69.170.26", 25565, ""));
        botList.add(new Bot("168.100.225.224", 25565, ""));

        botList.forEach(Bot::create);
    }
}