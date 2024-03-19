package icu.yfd.modules;

import com.github.steveice10.mc.protocol.data.game.entity.EntityEvent;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.data.game.level.notify.GameEvent;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundLoginPacket;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.entity.ClientboundEntityEventPacket;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.level.ClientboundGameEventPacket;
import com.github.steveice10.packetlib.packet.Packet;
import icu.yfd.Bot;
import icu.yfd.BotModule;
import icu.yfd.listener.Events;
import icu.yfd.utils.ComponentUtilities;
import net.kyori.adventure.text.Component;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Selfcare extends BotModule {
    // Interval
    private ScheduledFuture<?> check;
    public Integer permissionLevel = 4;
    public GameMode gamemode = GameMode.CREATIVE;
    public Integer entityId;
    public boolean cspy = false;
    public Selfcare(Bot bot) {
        super(bot);
    }

    public void load() {
        bot.eventManager.add("systemMessage", (Events<Component>) (Component message) -> {
            String stringMessage = ComponentUtilities.stringify(message).replaceAll("§.", "");

            if (stringMessage.equals("Successfully disabled CommandSpy")) cspy = false;
            else if (stringMessage.equals("Successfully enabled CommandSpy")) cspy = true;
        });

        bot.eventManager.add("packet", (Events<Packet>) (com.github.steveice10.packetlib.packet.Packet packet) -> {
            switch (packet.getClass().getSimpleName()) {
                case "ClientboundLoginPacket":
                    gamemode = ((ClientboundLoginPacket) packet).getGameMode();
                    entityId = ((ClientboundLoginPacket) packet).getEntityId();

                    // Start self-care interval

                    if (check == null) check = bot.executor.scheduleAtFixedRate(() -> {
                        if (!bot.loggedIn) {
                            check.cancel(true);
                            check = null;
                            return;
                        }

                        if (bot.chat != null) {
                            if (permissionLevel < 2) bot.chat.command("op @s[type=player]");
                            else if (gamemode != GameMode.CREATIVE) bot.chat.command("gamemode creative");
                            else if (!cspy) bot.chat.command("c on");
                        }
                    }, 100, 100, TimeUnit.MILLISECONDS);
                    break;
                case "ClientboundEntityEventPacket":
                    if (((ClientboundEntityEventPacket) packet).getEntityId() != entityId) return;

                    final EntityEvent event = ((ClientboundEntityEventPacket) packet).getEvent();

                    if (event == EntityEvent.PLAYER_OP_PERMISSION_LEVEL_0) permissionLevel = 0;
                    else if (event == EntityEvent.PLAYER_OP_PERMISSION_LEVEL_1) permissionLevel = 1;
                    else if (event == EntityEvent.PLAYER_OP_PERMISSION_LEVEL_2) permissionLevel = 2;
                    else if (event == EntityEvent.PLAYER_OP_PERMISSION_LEVEL_3) permissionLevel = 3;
                    else if (event == EntityEvent.PLAYER_OP_PERMISSION_LEVEL_4) permissionLevel = 4;
                    break;
                case "ClientboundGameEventPacket":
                    if (((ClientboundGameEventPacket) packet).getNotification() == GameEvent.CHANGE_GAMEMODE) {
                        gamemode = (GameMode) ((ClientboundGameEventPacket) packet).getValue();
                    }
                    break;
            }
        });
    }
}