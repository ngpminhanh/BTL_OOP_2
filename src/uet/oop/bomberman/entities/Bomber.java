package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private boolean pressBomb = false;
    private int numberofBomb;
    private final List<Bomb> bombs = new ArrayList<>();
    private KeyCode direction = null;
    private int radius;
    private int xbomber;
    private int ybomber;

    public Bomber(int x, int y, Image image) {
        super(x, y, image);
        this.xbomber = x;
        this.ybomber = y;
        setSpeed(10);
        setNumberofBomb(1);
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
        if (direction == KeyCode.SPACE) {
            placeBomb();
        }
    }

    public void placeBomb() {
        if (numberofBomb > 0) {

            int xB = (int) Math.round((x) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((y) / (double) Sprite.SCALED_SIZE);
            for (Bomb bomb : bombs) {
                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY()) return;
            }
            Bomb abomb = new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius);
            bombs.add(abomb);

            //abomb.exploded();
          //  numberofBomb -= 1;
        }
    }

    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            this.direction = keyCode;
            move();
        }
        if (keyCode == KeyCode.SPACE) {
            this.direction = keyCode;
            pressBomb = true;
           // placeBomb();
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
        if (keyCode == KeyCode.SPACE) {
            pressBomb = false;
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

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getNumberofBomb() {
        return numberofBomb;
    }

    public void setNumberofBomb(int numberofBomb) {
        this.numberofBomb = numberofBomb;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

}
