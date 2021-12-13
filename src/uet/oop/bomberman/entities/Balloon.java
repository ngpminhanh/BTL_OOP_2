package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enermy {
    protected boolean alive;
    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        //setLayer(1);
        setSpeed(1);
        _ai = new AILow();
        direction = _ai.caculateDirection();
        alive = true;
    }

    @Override
    public void update() {
        if (alive) {
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
            if (direction == 2) goUp();
            if (direction == 3) goDown();
        }
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, left++, 18).getFxImage();
        move();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 18).getFxImage();
        move();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, up++, 18).getFxImage();
        move();

    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, down++, 18).getFxImage();
        move();
    }

    @Override
    public void stay() {
        super.stay();
        direction = _ai.caculateDirection();
    }



}
