package ru.kev.catjump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TransparentBlock {

    public static final int TRANSPARENT_WIDTH = 100;


    private Texture transparent;
    private Vector2 posTransparent;
    private Rectangle boundTransparent;

    public TransparentBlock (float x){
        transparent = new Texture("transparentBlock1.png");
        posTransparent = new Vector2(x , 0 );
        boundTransparent = new Rectangle(0, 0 , 300 , 1000);
    }


    public Texture getTransparent () {
        return transparent;

    }

    public Vector2 getPosTransparent () {
        return posTransparent;
    }

    public Rectangle getBoundTransparent () {
        return boundTransparent;
    }
}
