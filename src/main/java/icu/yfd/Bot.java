package icu.yfd;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.tcp.TcpClientSession;

import icu.yfd.listener.EventManager;
import icu.yfd.modules.*;
import icu.yfd.utils.Logger;
import icu.yfd.utils.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class Bot {
    // Options
    public Session session;
    public String host;
    public int port;
    public String channelId;
    public boolean discordEnabled;
    public String username;
    public String customUsername;

    // Define information
    public List<BotModule> modules;
    public GameProfile profile;
    public boolean loggedIn = false;
    public final ExecutorService executorService = Main.executorService;
    public final ScheduledExecutorService executor = Main.executor;

    // Modules
    public Chat chat;
    // Events
    public EventManager eventManager = new EventManager();

    public Bot(String host, int port, String channelId) {
        this.host = host;
        this.port = port;
        this.channelId = channelId;
        this.discordEnabled = !channelId.isEmpty();
        this.modules = new ArrayList<>();
    }

    public void create() {
        new Logger("Connecting to " + host + ":" + port + "...").log();
        if (customUsername == null) username = "ABot_" + new Random(3).generate();
        else username = customUsername;
        if (session != null) session = null;

        eventManager.clear();
        modules.clear();
        profile = null;
        chat = null;

        session = new TcpClientSession(host, port, new MinecraftProtocol(username), null);

        ready();
    }

    public void ready() {
        session.connect(false);

        addModules();

        modules.forEach(BotModule::load);
    }

    private void addModules() {
        modules.add(new Chat(this));
        modules.add(new Packet(this));
        modules.add(new DisconnectManager(this));
        modules.add(new Login(this));
        modules.add(new Selfcare(this));
    }
}