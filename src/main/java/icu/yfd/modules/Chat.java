package icu.yfd.modules;

import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatCommandPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundChatPacket;
import icu.yfd.Bot;
import icu.yfd.BotModule;

import java.time.Instant;
import java.util.BitSet;
import java.util.Collections;

public class Chat extends BotModule {
    public Chat(Bot bot) {
        super(bot);
    }

    public void load() {
        bot.chat = this;

        /*
        bot.eventManager.add("systemMessage", (EventAction<Component>) (Component message) -> {
            new Logger(ComponentUtilities.stringifyAnsi(message).replaceAll("§.", ""), bot.host + ":" + bot.port + " ||").log();
        });

        bot.eventManager.add("playerMessage", (EventAction<Component>) (Component message) -> {
            new Logger(ComponentUtilities.stringifyAnsi(message).replaceAll("§.", ""), bot.host + ":" + bot.port + " ||").log();
        });
         */
    }

    public void chat(String message) {
        this.bot.session.send(new ServerboundChatPacket(
                message,
                Instant.now().toEpochMilli(),
                0L,
                null,
                0,
                new BitSet()
        ));
    }

    public void command(String command) {
        this.bot.session.send(new ServerboundChatCommandPacket(
                command,
                Instant.now().toEpochMilli(),
                0L,
                Collections.emptyList(),
                0,
                new BitSet()
        ));
    }
}