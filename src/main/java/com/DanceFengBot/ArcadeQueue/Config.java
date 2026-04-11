package com.DanceFengBot.ArcadeQueue;

import java.io.File;
import java.io.IOException;

public class Config {

    public static String linuxRootPath;
    public static String windowsRootPath;
    private static final boolean windowsMark;
    public static String configPath;


    static {
        windowsMark = new File("./WINDOWS_MARK").exists();
        try {
            linuxRootPath = new File("..").getCanonicalPath();
            windowsRootPath = new File(".").getCanonicalPath();

            //在项目下创建 “WINDOWS_MARK” 文件，存在即使用Windows路径的配置，而Linux则不需要
            if(itIsAReeeeaaaalWindowsMark()) {
                configPath = windowsRootPath + "/ArcadeQueueConfig/";
            } else {
                configPath = linuxRootPath + "/ArcadeQueueConfig/";
            }
            new File(configPath).mkdirs();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 让我看看是不是Windows！
     *
     * @return 这是个Windows系统
     */
    public static boolean itIsAReeeeaaaalWindowsMark() {
        return windowsMark;
    }
}
