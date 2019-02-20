package ru.kev.catjump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Award {

    public static final int AWARD_WIDTH = 100;
    private Texture award;
    private Vector2 posAward;
    private Rectangle botCoins;

    public Texture getAward() {
        return award;
    }


    public Vector2 getPosAward() {
        return posAward;
    }

    public Award(float x){
        award = new Texture("lac.png");
        posAward = new Vector2(x, 70);
        botCoins = new Rectangle(posAward.x , posAward.y , award.getWidth() , award.getHeight());
    }

    public Rectangle getRectangleCoinBot () {
        return botCoins;
    }

}
