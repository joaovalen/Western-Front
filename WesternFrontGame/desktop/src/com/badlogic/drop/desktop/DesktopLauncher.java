package com.badlogic.drop.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import scenes.WesternFront;

public class DesktopLauncher {
     
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Western Front";
		config.width = 800;
		config.height = 480;
                config.forceExit = true;
		new LwjglApplication(new WesternFront(), config);
	}
}
