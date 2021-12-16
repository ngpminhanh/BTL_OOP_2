package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.bomber;
import static uet.oop.bomberman.Sound.*;

public class Bomber extends Character {
    private boolean pressBomb = false;
    private int numberofBomb;
    private final List<Bomb> bombs = new ArrayList<>();
    private KeyCode direction = null;
    private int radius;
    private int timeAfterDie = 0;
    private int power;
    public static int live = 3;

    public Bomber(int x, int y, Image image) {
        super(x, y, image);
        setSpeed(2);
        setNumberofBomb(1);
        setPower(1);
        setRadius(1);
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
            numberofBomb--;
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
            }
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isAlive() {
        return alive;
    }

    public Rectangle getBounds() {
        return new Rectangle(desX + 2, desY +5, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE * 3/4);
    }

    public void die() {
        if (timeAfterDie == 20) live --;
        if(timeAfterDie <= 45) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, timeAfterDie, 20).getFxImage();
        }
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void handleCollisions(){
        List<Bomb> bombs = bomber.getBombs();
        Rectangle r1 = bomber.getBounds();
        //Bomber vs stillObject
        for (Entity stillObject : stillObjects) {
            Rectangle r2 = stillObject.getBounds();
            if (r1.intersects(r2)) {
                if (bomber.getLayer() == stillObject.getLayer() && stillObject instanceof Item) {
                    if(stillObject instanceof BombItem) {
                        an_item.play();
                        an_item.seek(an_item.getStartTime());
                        startBomb ++;
                        bomber.setNumberofBomb(startBomb);
                        stillObjects.remove(stillObject);
                    } else if(stillObject instanceof SpeedItem) {
                        an_item.play();
                        an_item.seek(an_item.getStartTime());
                        startSpeed += 1;
                        bomber.setSpeed(startSpeed);
                        stillObjects.remove(stillObject);
                    } else if(stillObject instanceof FlameItem) {
                        an_item.play();
                        an_item.seek(an_item.getStartTime());
                        startFlame ++;
                        System.out.println(startFlame);
                        bomber.setRadius(startFlame);
                        stillObjects.remove(stillObject);
                    }
                    bomber.stay();
                } else if(bomber.getLayer() == stillObject.getLayer() && stillObject instanceof Portal) {
                    if(enemies.size() == 0) {
                        // giet het tat ca enemy
                        _level++;
                        qua_man.play();
                        qua_man.seek(qua_man.getStartTime());
                        check = true;
                    }
                } else if(bomber.getLayer() >= stillObject.getLayer()) {
                    bomber.move();
                }
                else {
                    bomber.stay();
                }
                break;
            }
        }
        //Bomber vs Enemies
        for (Enermy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            if (r1.intersects(r2)) {

                bomber.setAlive(false);
                nv_chet.play();
                nv_chet.seek(nv_chet.getStartTime());
                startBomb = 1;
                startFlame = 1;
                startSpeed = 1;

                if(bomber.isAlive() == false){
                    if(live>=0) {
                        Timer rebirth = new Timer();
                        rebirth.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
                                rebirth.cancel();
                            }
                        }, 500, 1);

                        //System.out.println("ddd");
                    }


                }
            }
        }
        //Enemies vs Bombs
        for (Enermy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            for (Bomb bomb : bombs) {
                Rectangle r3 = bomb.getBounds();
                if (!bomb.isAllowedToPassThrough(enemy) && r2.intersects(r3)) {
                    enemy.stay();
                    break;
                }
            }
        }
        //Enemies vs StillObjects
        for (Enermy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r3 = stillObject.getBounds();
                if (r2.intersects(r3)) {
                    if (enemy.getLayer() >= stillObject.getLayer()) {
                        enemy.move();
                    } else {
                        enemy.stay();
                    }
                    break;
                }
            }
        }
    }

    public void checkCollisionFlame() {
        //if(explosionList != null){
        for (int i = 0; i < flames.size(); i++) {
            Rectangle r1 = flames.get(i).getBounds();
            for (int j = 0; j < stillObjects.size(); j++) {
                Rectangle r2 = stillObjects.get(j).getBounds();
                if (r1.intersects(r2) && !(stillObjects.get(j) instanceof Item))
                    stillObjects.get(j).setAlive(false);
            }
            for (int j = 0; j < enemies.size(); j++) {
                Rectangle r2 = enemies.get(j).getBounds();
                if (r1.intersects(r2)){
                    if(enemies.get(j) instanceof Kondoria ||enemies.get(j) instanceof Doll) score+=5;
                    else if(enemies.get(j) instanceof Oneal) score+=2;
                    else score+=1;
                    quai_chet.play();
                    quai_chet.seek(qua_man.getStartTime());
                    enemies.get(j).setAlive(false);
                }
            }
            Rectangle r2 = bomber.getBounds();
            if (r1.intersects(r2)) {
                bomber.setAlive(false);
                quai_chet.play();
                quai_chet.seek(quai_chet.getStartTime());
                startBomb = 1;
                startFlame = 1;
                startSpeed = 1;
                if (bomber.isAlive() == false) {
                    if (live >= 0) {
                        Timer count = new Timer();
                        count.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
                                count.cancel();
                            }
                        }, 800, 1);
                    }

                }
            }
        }
    }
}
