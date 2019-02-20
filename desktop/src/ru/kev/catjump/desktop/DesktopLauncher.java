package ru.kev.catjump.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.kev.catjump.CatJump;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = CatJump.WIDTH;
		config.height = CatJump.HEIGHT;
		config.title = CatJump.TITLE;
		new LwjglApplication(new CatJump(), config);
	}
}