package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Portal extends StillEntity{
    public Portal(int x, int y, Image image) {
        super(x, y, image);
        setLayer(1);
    }

    @Override
    public void update() {

    }
}