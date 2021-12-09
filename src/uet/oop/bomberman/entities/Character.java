package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Character extends Entity{
    protected int desX = x;
    protected int desY = y;
    protected int speed;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;
    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void goLeft() {
        desX = x - speed;
    }

    public void goRight() {
        desX = x + speed;
    }
    public void goUp() {
        desY = y - speed;
    }

    public void goDown() {
        desY = y + speed;
    }

    public void move() {
        x = desX;
        y = desY;
    }

    public void stay() {
        desX = x;
        desY = y;
    }
    @Override
    public void update() {

    }

}
