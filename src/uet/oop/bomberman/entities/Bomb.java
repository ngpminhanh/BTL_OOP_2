package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.bomber;

public class Bomb extends Character {
    public static int currentBomb = 0;
    private int timeCounter = 0;
    int radius;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        setLayer(2);
        this.radius = 1;
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = radius;
    }

    @Override
    public void update() {
        if (timeCounter++ == 120) {
            exploded();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCounter, 60).getFxImage();
        //exploded(xx, yy);


    }

    public void exploded() {
        // BombermanGame.flames.add(new Flame(x_bom, y_bom, Sprite.bomb_exploded.getFxImage(), 0));
        //img = Sprite.explosion_horizontal_right_last.getFxImage();
        //flameList.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0));
        // BombermanGame.flames.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0));

        Flame flame = new Flame(x, y);
        flame.setRadius(radius);
        flame.render_explosion();
    }

    public boolean isAllowedToPassThrough(Character e) {
        Rectangle r1 = getBounds();
        Rectangle r2;
        if (e instanceof Bomber) {
            Bomber bomber = (Bomber) e;
            r2 = new Rectangle(bomber.getX() + 4, bomber.getY() + 4, Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
        } else {
            r2 = new Rectangle(e.getX(), e.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        }
        return r1.intersects(r2);
    }

}