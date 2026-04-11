package com.DanceFengBot.ArcadeQueue.Commands;

import com.DanceFengBot.ArcadeQueue.Manager.ArcadeManager;
import com.DanceFengBot.ArcadeQueue.Manager.GroupManager;

import java.lang.reflect.Field;
import java.util.HashSet;

public class AllCommands {
    public static HashSet<RegexCommand> regexCommands = new HashSet<>();  //所有正则指令
    public static HashSet<ArgsCommand> argsCommands = new HashSet<>();  //所有参数指令
    public static void init() {
        for (Field field : AllCommands.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(DeclaredCommand.class)) {
                try {
                    if (field.getType() == RegexCommand.class)
                        regexCommands.add((RegexCommand) field.get(null)); // 获取并保存所有指令
                    else if (field.getType() == ArgsCommand.class) {
                        argsCommands.add(((ArgsCommand) field.get(null)));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @DeclaredCommand("查看帮助")
    public static final RegexCommand msgMenu = new RegexCommandBuilder()
            .multiStrings("菜单", "help")
            .onCall(Scope.GLOBAL, (event, contact, qq, args) -> {
                contact.sendMessage("详见https://www.kdocs.cn/l/ccLRaUCuNMX0。");
            }).build();

    @DeclaredCommand("添加群聊")
    public static final RegexCommand addGroup = new RegexCommandBuilder()
            .regex("添加群聊")
            .onCall(Scope.ADMIN, (event, contact, qq, args) -> {
                GroupManager.addGroup(contact.getId());
                contact.sendMessage("已添加当前群聊到名单中");
                if (!GroupManager.addGroup(contact.getId())) {
                    contact.sendMessage("当前群聊已在名单中");
                }
            }).build();

    @DeclaredCommand("删除群聊")
    public static final RegexCommand delGroup = new RegexCommandBuilder()
            .regex("删除群聊")
            .onCall(Scope.ADMIN, (event, contact, qq, args) -> {
                GroupManager.deleteGroup(contact.getId());
                contact.sendMessage("已从名单中删除当前群聊");
                if (!GroupManager.deleteGroup(contact.getId())) {
                    contact.sendMessage("当前群聊不在名单中，无法删除");
                }
            }).build();

    @DeclaredCommand("添加机厅")
    public static final ArgsCommand addArcade = new ArgsCommandBuilder()
            .prefix("添加机厅")
            .onCall(Scope.ADMIN, (event, contact, qq, args) -> {
                // TODO 添加机厅
                if (args != null) {
                    ArcadeManager.addArcade(contact.getId(), args[0]);
                }
            }).build();

    @DeclaredCommand("删除机厅")
    public static final ArgsCommand delArcade = new ArgsCommandBuilder()
            .prefix("删除机厅")
            .onCall(Scope.ADMIN, (event, contact, qq, args) -> {
                // TODO 添加机厅
                if (args != null) {
                    ArcadeManager.deleteArcade(contact.getId(), args[0]);
                }
            }).build();
}
