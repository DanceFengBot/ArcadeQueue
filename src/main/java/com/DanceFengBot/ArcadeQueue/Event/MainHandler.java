package com.DanceFengBot.ArcadeQueue.Event;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.friendgroup.FriendGroup;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;

import java.util.stream.Collectors;

public class MainHandler {
    @EventHandler
    public static void eventCenter(MessageEvent event) {
        MessageChain messageChain = event.getMessage();
        if(messageChain.size() - 1==messageChain.stream()
                .filter(msg -> msg instanceof At | msg instanceof PlainText)
                .collect(Collectors.toList()).size()) {
            PlainTextHandler.accept(event);
        } else return;

        String message = messageChain.contentToString();
        long qq = event.getSender().getId(); // qq发送者id 而非群聊id
        Contact contact = event.getSubject();
    }

    @EventHandler
    public static void NudgeHandler(NudgeEvent event) {
        if(event.getTarget() instanceof Bot) {
            event.getFrom().nudge().sendTo(event.getSubject());
        }
    }

    @EventHandler
    public static void addFriendHandler(NewFriendRequestEvent event) {
        event.accept();
        Friend friend = event.getBot().getFriend(event.getFromId());
        if(friend != null) {
            friend.sendMessage("🥰呐~ 现在我们是好朋友啦！\n请发送“help”查看功能哦！");
            FriendGroup friendGroup = event.getBot().getFriendGroups().get(0);
            if(friendGroup != null) {
                friendGroup.moveIn(friend);
            }
        }
    }

}
