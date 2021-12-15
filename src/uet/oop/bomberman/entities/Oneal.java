package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enermy{
    protected boolean alive;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(2);
        _ai = new AIHigh(new Bomber(1, 1, Sprite.player_right.getFxImage()),this);
        direction = _ai.caculateDirection();
        alive = true;
    }
    int n = 0;

    @Override
    public void update() {
        n++;
        if (alive) {
            if (direction == 3) goLeft();
            if (direction == 1) goRight();
            if (direction == 0) goUp();
            if (direction == 2) goDown();
            if (n == 32) {
                direction = _ai.caculateDirection();
                n = 0;
            }
            //
        }
    }

        public void goLeft() {
            super.goLeft();
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 18).getFxImage();
            move();
        }

        public void goRight() {
            super.goRight();
            img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 18).getFxImage();
            move();
        }

        public void goUp() {

            super.goUp();
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, up++, 18).getFxImage();
            move();
        }

        public void goDown() {

            super.goDown();
            img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, down++, 18).getFxImage();
            move();
        }

        @Override
        public void stay() {
            super.stay();
            direction = _ai.caculateDirection();
        }
}
