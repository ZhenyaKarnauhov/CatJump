package ru.kev.catjump.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Класс котика, задаются позиция котика, его техтура, анимация и скорость прыжка
 */

public class Cat {
    private static final int MOVEMENT = 175;
    private static final int GRAVITY = -70;
    private Vector2 position;
    private Vector2 speedVector;
    private Animation catAnimation;
    private Rectangle boundsCat;


    public Cat(int x, int y) {
        position = new Vector2(x,y);
        speedVector = new Vector2(0,0);

        Texture cat = new Texture("catatlas.png");
        catAnimation = new Animation(new TextureRegion(cat), 5, 0.8f);

        boundsCat = new Rectangle(x,y, cat.getWidth()/5, cat.getHeight());
    }

    public TextureRegion getCat() {

        return catAnimation.getFrame();
    }

    public Vector2 getPosition() {
        return position;
    }



    /**
     * Метод установки нижней границы экрана
     * @param dt - определённые промежутки времени
     */
    public void update(float dt) {
        catAnimation.update(dt);
        if (position.y > 50 )
            speedVector.add(0 , GRAVITY );
        speedVector.scl(dt);
        position.add(MOVEMENT * dt, speedVector.y);
        if (position.y < 50 )
            position.y = 50;
        speedVector.scl(1 / dt);
        boundsCat.setPosition(position.x , position.y);
    }

    public void jump() {
        speedVector.y = 400;
    }

    public Rectangle getBoundsCat() {
        return boundsCat;
    }
}