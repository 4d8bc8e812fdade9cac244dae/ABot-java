package icu.yfd.modules;

import com.github.steveice10.mc.protocol.data.game.entity.player.HandPreference;
import com.github.steveice10.mc.protocol.data.game.setting.ChatVisibility;
import com.github.steveice10.mc.protocol.data.game.setting.SkinPart;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundDisguisedChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundLoginPacket;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundPlayerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.ClientboundSystemChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.serverbound.ServerboundClientInformationPacket;
import com.github.steveice10.mc.protocol.packet.login.clientbound.ClientboundGameProfilePacket;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.*;
import icu.yfd.Bot;
import icu.yfd.BotModule;

import java.util.ArrayList;
import java.util.List;

public class Packet extends BotModule {
    public Packet(Bot bot) {
        super(bot);
    }

    public void load() {
        bot.session.addListener(new SessionListener() {
            @Override
            public void packetReceived(Session session, com.github.steveice10.packetlib.packet.Packet packet) {
                switch (packet.getClass().getSimpleName()) {
                    case "ClientboundLoginPacket":
                        bot.loggedIn = true;
                        bot.eventManager.emit("login", 1);
                        break;
                    case "ClientboundGameProfilePacket":
                        bot.profile = ((ClientboundGameProfilePacket) packet).getProfile();
                        break;
                    case "ClientboundSystemChatPacket":
                        bot.eventManager.emit("systemMessage", ((ClientboundSystemChatPacket) packet).getContent());
                        break;
                    case "ClientboundPlayerChatPacket":
                        bot.eventManager.emit("playerMessage", ((ClientboundPlayerChatPacket) packet).getUnsignedContent());
                        break;
                    case "ClientboundDisguisedChatPacket":
                        bot.eventManager.emit("playerMessage", ((ClientboundDisguisedChatPacket) packet).getMessage());
                        break;
                }

                bot.eventManager.emit("packet", packet);
            }

            @Override
            public void packetSending(PacketSendingEvent packetSendingEvent) {}

            @Override
            public void packetSent(Session session, com.github.steveice10.packetlib.packet.Packet packet) {}

            @Override
            public void packetError(PacketErrorEvent packetErrorEvent) {
                packetErrorEvent.setSuppress(true);
            }

            @Override
            public void connected(ConnectedEvent connectedEvent) {
                bot.eventManager.emit("connected");
            }

            @Override
            public void disconnecting(DisconnectingEvent disconnectingEvent) {
                bot.eventManager.emit("disconnecting", disconnectingEvent);
            }

            @Override
            public void disconnected(DisconnectedEvent disconnectedEvent) {
                bot.eventManager.emit("disconnect", disconnectedEvent);
            }
        });
    }
}