package ru.kev.catjump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.kev.catjump.states.GameStateManager;
import ru.kev.catjump.states.MenuState;


/*
Класс CatJump. Где задаются размеры экрана, название заголовка приложения.
 */

public class CatJump extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public static final String TITLE = "Cat jump";
	private SpriteBatch batch;
	private GameStateManager gsm;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));

	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//возвращает время между прошедшим и текущим кадром в секундах
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}