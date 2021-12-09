package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;

public class Bomber extends Character {
    private KeyCode direction = null;
    public Bomber(int x, int y, Image image) {
        super(x, y, image);
        setSpeed(10);

    }
    @Override
    public void update() {
        if (direction == KeyCode.LEFT) {
            goLeft();
        }
        if (direction == KeyCode.RIGHT) {
            goRight();
        }
        if (direction == KeyCode.UP) {
            goUp();
        }
        if (direction == KeyCode.DOWN) {
            goDown();
        }
    }
    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            this.direction = keyCode;
            move();

        }
    }
    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();

            }
            if (direction == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();

            }
            if (direction == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();

            }
            if (direction == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();

            }
            direction = null;
        }

    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
    }
}
