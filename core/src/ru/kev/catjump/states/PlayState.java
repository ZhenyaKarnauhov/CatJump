package ru.kev.catjump.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import ru.kev.catjump.CatJump;
import ru.kev.catjump.sprites.Block;
import ru.kev.catjump.sprites.Cat;
import ru.kev.catjump.sprites.Award;
import ru.kev.catjump.sprites.TransparentBlock;

/**
 * Класс игрового экрана
 */

public class PlayState extends State {

    private static final int AWARD_SPACING = 300;
    private static final int BLOCK_WIDTH = 300;
    private static final int BLOCK_COUNT = 200;
    private static final int AWARD_COUNT = 200;
    private static final int  TRANSPARENT_COUNT = 200;
    private static final int TRANSPARENT_SPACING = 300;

    private GameStateManager gsm;

    private Cat cat;
    private Texture bg;
    private BitmapFont font;
    private Array<Award> awards;
    private Array<TransparentBlock> transparentBlocks;
    private Array<Block> blocks;
    private int countCoins;

    /**
     * Задаётся фон приложения, создаётся условия award's для cat , которые он собирает, block's по которым cat бежит.
     * Создаются условия transparentBlock's (Прозрачные блоки) с которыми если cat соприкасается он умирает , этот блок заменяет пропасть.
     */

    PlayState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        cat = new Cat(0, 0);
        camera.setToOrtho(false, CatJump.WIDTH, CatJump.HEIGHT);
        bg = new Texture("picture1.png");
        font = new BitmapFont();
        blocks = new Array<Block>();
        transparentBlocks = new Array<TransparentBlock>();
        awards = new Array<Award>();
        for (int i = 0; i < AWARD_COUNT; i++) {
            awards.add(new Award(i * (AWARD_SPACING + Award.AWARD_WIDTH) + 100 ));
        }
        for (int i = 0; i < BLOCK_COUNT; i++) {
            blocks.add(new Block(i * (BLOCK_WIDTH + Block.BLOCK_SPACING) ));
        }
        for (int i = 0; i < TRANSPARENT_COUNT; i++) {
            transparentBlocks.add(new TransparentBlock(i * (TransparentBlock.TRANSPARENT_WIDTH + TRANSPARENT_SPACING) + 740));
        }


    }

    /**
     * При прикосновении на экран cat делает прыжок.
     */

    protected void handleInput() {
        if (Gdx.input.justTouched())
            cat.jump();

    }
    /**
     *
     * @param dt промежуток времени
     */
    @Override
    public void update(float dt) {
        handleInput();
        cat.update(dt);
        camera.position.x = cat.getPosition().x + 300;
        camera.update();
    }

    /**
     * Происходит исполнение методов drawing , check.
     */

    @Override
    public void render(SpriteBatch sb) {
        drawing(sb);
        check();
    }

    /**
     * Отрисовка счётчика , фона, и кота.
     * @param sb упаковщик текстур
     */

    private void drawing(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(cat.getCat(), cat.getPosition().x, cat.getPosition().y);
        broadCast(sb);
        font.draw(sb, "Score: " + countCoins, camera.position.x - 400, 480);
        sb.end();
    }

    /**
     * Отрисовывает блоки.
     * @param sb упаковщик текстур
     */

    private void broadCast(SpriteBatch sb) {
        block(sb);
        transParentBlock(sb);
        award(sb);
    }

    /**
     * Отрисовывает блоки по которым бежит кот.
     * @param sb упаковщик текстур
     */

    private void block(SpriteBatch sb) {
        for (Block block : blocks) {
            sb.draw(block.getBlock(), block.getPosBlock().x, block.getPosBlock().y);
        }
    }

    /**
     * Отрисовывает прозрачные блоки, которые являются пропастью для кота.
     *
     * @param sb упаковщик текстур
     */

    private void transParentBlock(SpriteBatch sb){
        for (TransparentBlock transparentBlock : transparentBlocks) {
            sb.draw(transparentBlock.getTransparent(), transparentBlock.getPosTransparent().x, transparentBlock.getPosTransparent().y);
        }
    }

    /**
     * Отрисовывает блоки лакомств которые собирает кот.
     * @param sb упаковщик текстур
     */

    private void award(SpriteBatch sb) {
        for (Award award : awards) {
            sb.draw(award.getAward(), award.getPosAward().x, award.getPosAward().y);
        }
    }

    /**
     * Проверка условий различных столкновений.
     */

    private void check() {

        /*
          Условия столкновения кота с лакомством.
         */
        Iterator<Award> iterator = awards.iterator();
        while (iterator.hasNext()) {
            Award coin = iterator.next();
            if (cat.getBoundsCat().overlaps(coin.getRectangleCoinBot())) {
                countCoins++;
                iterator.remove();
            }
        }

        /*
          Проверка столкновения кота с прозрачным блоком (пропастью)
         */

        Iterator<TransparentBlock> iterator1 = transparentBlocks.iterator();
        while (iterator1.hasNext()) {
            TransparentBlock transparentBlock = iterator1.next();
            if (cat.getBoundsCat().overlaps(transparentBlock.getBoundTransparent())) {
                gsm.set(new Gameover(gsm));
                iterator1.remove();
            }
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}