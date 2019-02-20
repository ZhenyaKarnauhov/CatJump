package ru.kev.catjump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Block {
    public static final int BLOCK_SPACING = 100;

    private Texture block;
    private Vector2 posBlock;


    public Texture getBlock() {
        return block;
    }

    public Vector2 getPosBlock() {
        return posBlock;
    }

    public Block (float x){
        block = new Texture("block1.png");
        posBlock = new Vector2(x, 0);
    }
}

