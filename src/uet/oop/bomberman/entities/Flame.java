package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Flame extends Entity {
    private int xf;
    private int yf;
    private int left;
    private int right;
    private int top;
    private int down;
    public int radius;
    private int size = Sprite.SCALED_SIZE;
    private int direction;
    private int time = 0;

    public Flame(int x, int y) {
        super(x, y);
        this.xf = x;
        this.yf = y;
    }

    public Flame(int x, int y, Image img) {
        super(x, y, img);
        this.radius = 2;
    }

    public Flame(int x, int y, Image img, int direction) {
        super(x, y, img);

        this.direction = direction;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void update() {

        if (time < 20) {
            time++;
            setImg();
        } else {
            BombermanGame.flames.remove(this);
        }

    }

    public void render_explosion() {
       /* right();
        left();
        top();
        down(); */
        right = 2;
        left = 2;
        top = 2;
        down = 2;
        create_explosion();
    }

    public void create_explosion() {
        //img = Sprite.explosion_horizontal_right_last.getFxImage();
        BombermanGame.flames.add(new Flame(xf, yf, Sprite.bomb_exploded.getFxImage(), 0));

        for (int i = 0; i < 2; i++) {
            Flame flame = new Flame(xf + (i + 1), yf);
            if (i == 2 - 1) {
                flame.img = Sprite.explosion_horizontal_right_last.getFxImage();
               // img = Sprite.explosion_horizontal_right_last.getFxImage();
                flame.direction = 2;
            } else {
                flame.img = Sprite.explosion_horizontal.getFxImage();
               // img = Sprite.explosion_horizontal.getFxImage();
                flame.direction = 1;
            }
            BombermanGame.flames.add(flame);
        }

        for (int i = 0; i < 2; i++) {
            Flame flame = new Flame(xf - (i + 1), yf);
            if (i == 2 - 1) {
                flame.img = Sprite.explosion_horizontal_left_last.getFxImage();
                flame.direction = 3;
            } else {
                flame.img = Sprite.explosion_horizontal.getFxImage();
                flame.direction = 1;
            }
            BombermanGame.flames.add(flame);
        }

        for (int i = 0; i < 2; i++) {
            Flame flame = new Flame(xf, yf - (i + 1));
            if (i == 2 - 1) {
                flame.img = Sprite.explosion_vertical_top_last.getFxImage();
                flame.direction = 5;
            } else {
                flame.img = Sprite.explosion_vertical.getFxImage();
                flame.direction = 4;
            }
            BombermanGame.flames.add(flame);
        }

        for (int i = 0; i < 2; i++) {
            Flame flame = new Flame(xf, yf + (i + 1));
            if (i == 2 - 1) {
                flame.img = Sprite.explosion_vertical_down_last.getFxImage();
                flame.direction = 6;
            } else {
                flame.img = Sprite.explosion_vertical.getFxImage();
                flame.direction = 4;
            }
            BombermanGame.flames.add(flame);
        }
    }

    private void right() {
        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(xf + size * (i + 1), yf, size, size);
            if (collisionType(rectangle) instanceof Wall) {
                right = i;
                return;
            } else if (collisionType(rectangle) instanceof Brick) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private void left() {
        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(xf - size * (i + 1), yf, size, size);
            if (collisionType(rectangle) instanceof Wall) {
                left = i;
                return;
            } else if (collisionType(rectangle) instanceof Brick) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void top() {
        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(xf, yf - size * (i + 1), size, size);
            if (collisionType(rectangle) instanceof Wall) {
                top = i;
                return;
            } else if (collisionType(rectangle) instanceof Brick) {
                top = i + 1;
                return;
            }
            top = i + 1;
        }
    }

    private void down() {
        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(xf, yf + size * (i + 1), size, size);
            if (collisionType(rectangle) instanceof Wall) {
                down = i;
                return;
            } else if (collisionType(rectangle) instanceof Brick) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    private static Object collisionType(Rectangle rectangle) {
        for (Entity e : BombermanGame.stillObjects) {
            Rectangle rectangle1 = e.getBounds();
            if (rectangle.intersects(rectangle1)) {
                return e;
            }
        }
        return rectangle;
    }

    private static boolean collisionWall(Rectangle rectangle) {
        for (Entity e : BombermanGame.stillObjects) {
            Rectangle rectangle1 = e.getBounds();
            if (rectangle1.intersects(rectangle) && e instanceof Wall) {
                return true;
            }
        }
        return false;
    }

    private void setImg() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, time, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                        , Sprite.explosion_horizontal2, time, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        , Sprite.explosion_horizontal_right_last2, time, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        , Sprite.explosion_horizontal_left_last2, time, 20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                        , Sprite.explosion_vertical2, time, 20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                        , Sprite.explosion_vertical_top_last2, time, 20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                        , Sprite.explosion_vertical_down_last2, time, 20).getFxImage();
                break;
        }
    }
}
