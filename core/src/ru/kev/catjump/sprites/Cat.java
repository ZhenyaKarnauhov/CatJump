package ru.kev.catjump.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Класс котика, задаются позиция котика, его техтура, анимация и скорость прыжка
 */

public class Cat {
    private static final int MOVEMENT = 230;
    private static final int GRAVITY = -25;
    private Vector2 position;
    private Vector2 velosity;
    private Animation catAnimation;
    private Rectangle boundsCat;


    public Cat(int x, int y) {
        position = new Vector2(x,y);
        velosity = new Vector2(0,0);

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
            velosity.add(0 , GRAVITY );
        velosity.scl(dt);
        position.add(MOVEMENT * dt,velosity.y);
        if (position.y < 50 )
            position.y = 50;
        velosity.scl(1 / dt);
        boundsCat.setPosition(position.x , position.y);
    }

    public void jump() {
        velosity.y = 500;
    }

    public Rectangle getBoundsCat() {
        return boundsCat;
    }
}