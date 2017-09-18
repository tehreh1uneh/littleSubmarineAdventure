package com.tehreh1uneh.littlesubmarineadventure.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tehreh1uneh.littlesubmarineadventure.SubmarineGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // 16:9
        config.width = 1280 / 2;
        config.height = 720 / 2;

        new LwjglApplication(new SubmarineGame(), config);
    }
}
