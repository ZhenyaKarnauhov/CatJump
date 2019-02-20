package ru.kev.catjump.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

    OrthographicCamera camera;
    GameStateManager gsm;

    State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}

