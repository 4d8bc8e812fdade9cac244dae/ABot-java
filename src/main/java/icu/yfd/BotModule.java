package icu.yfd;

public abstract class BotModule {
    protected Bot bot;

    public BotModule(Bot bot) {
        this.bot = bot;
    }

    public abstract void load();
}