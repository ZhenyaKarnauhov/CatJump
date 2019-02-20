package ru.kev.catjump.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Класс анимации
 */

class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    /**
     * Метод проверки длительности отображения кадра
     */

    void update(float dt){

        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }

    /**
     * Метод получения текущего кадра анимации
     */
    TextureRegion getFrame(){
        return frames.get(frame);
    }
}