package icu.yfd.modules;

import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import icu.yfd.Bot;
import icu.yfd.BotModule;
import icu.yfd.listener.Events;
import icu.yfd.utils.ComponentUtilities;
import icu.yfd.utils.Logger;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DisconnectManager extends BotModule {
    public DisconnectManager(Bot bot) {
        super(bot);
    }

    public void load() {
        bot.eventManager.add("disconnect", (Events<DisconnectedEvent>) (DisconnectedEvent disconnectEvent) -> {
            bot.loggedIn = false;

            final Throwable cause = disconnectEvent.getCause();

            if (cause != null) {
                if (cause instanceof OutOfMemoryError) {
                    System.exit(0);
                }
            }

            int reconnectDelay = 1000;

            final String stringMessage = ComponentUtilities.stringify(disconnectEvent.getReason());

            new Logger("Disconnected from " + bot.host + ":" + bot.port + " with reason " + stringMessage).log();

            if (
                    stringMessage.equals("Wait 5 seconds before connecting, thanks! :)") ||
                            stringMessage.equals("Connection throttled! Please wait before reconnecting.")
            ) reconnectDelay = 1000 * 6;

            ScheduledExecutorService executorCopy = bot.executor;

            executorCopy.schedule(() -> {
                bot.create();
            }, reconnectDelay, TimeUnit.MILLISECONDS);
        });
    }
}
