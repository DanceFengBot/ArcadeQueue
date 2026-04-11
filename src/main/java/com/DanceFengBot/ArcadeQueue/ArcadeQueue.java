package com.DanceFengBot.ArcadeQueue;

import com.DanceFengBot.ArcadeQueue.Event.MainHandler;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;

public final class ArcadeQueue extends JavaPlugin {
    public static final ArcadeQueue INSTANCE = new ArcadeQueue();

    private ArcadeQueue() {
        super(new JvmPluginDescriptionBuilder("com.DaceFengBot.ArcadeQueue", "0.1.0")
                .name("ArcadeQueue")
                .author("Jingsong2008")
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        super.onLoad($this$onLoad);
    }
    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
        EventChannel<Event> channel = GlobalEventChannel.INSTANCE
                .parentScope(ArcadeQueue.INSTANCE)
                .context(this.getCoroutineContext());

        // 监听器
        channel.subscribeAlways(MessageEvent.class, MainHandler::eventCenter);
        channel.subscribeAlways(NudgeEvent.class, MainHandler::NudgeHandler);
        channel.subscribeAlways(NewFriendRequestEvent.class, MainHandler::addFriendHandler);
    }
}