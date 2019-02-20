package ru.kev.catjump.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.kev.catjump.CatJump;

public class Gameover extends State {

    private Texture background;
    private Texture gameover;

    Gameover(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, CatJump.WIDTH / 2, CatJump.HEIGHT / 2);
        background = new Texture("picture1.png");
        gameover = new Texture("gameover.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();

        System.out.println("GameOver Disposed");

    }
}
