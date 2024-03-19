package icu.yfd.modules;

import com.github.steveice10.mc.protocol.data.game.entity.player.HandPreference;
import com.github.steveice10.mc.protocol.data.game.setting.ChatVisibility;
import com.github.steveice10.mc.protocol.data.game.setting.SkinPart;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundClientInformationPacket;
import icu.yfd.Bot;
import icu.yfd.BotModule;
import icu.yfd.listener.Events;
import icu.yfd.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class Login extends BotModule {
    public Login(Bot bot) {
        super(bot);
    }

    public void load() {
        bot.eventManager.add("login", (Events<Integer>) (Integer i) -> {
            final List<SkinPart> skinParts = new ArrayList<>();

            skinParts.add(SkinPart.CAPE);
            skinParts.add(SkinPart.JACKET);
            skinParts.add(SkinPart.LEFT_SLEEVE);
            skinParts.add(SkinPart.RIGHT_SLEEVE);
            skinParts.add(SkinPart.LEFT_PANTS_LEG);
            skinParts.add(SkinPart.RIGHT_PANTS_LEG);
            skinParts.add(SkinPart.HAT);

            bot.session.send(
                    new ServerboundClientInformationPacket(
                            "en_us",
                            16,
                            ChatVisibility.FULL,
                            true,
                            skinParts,
                            HandPreference.RIGHT_HAND,
                            false,
                            true
                    )
            );

            new Logger("Logged into " + bot.host + ":" + bot.port + "...").log();
            if (bot.chat != null) bot.chat.chat("&6&lABot &7- made by &6&l5rz");
        });
    }
}