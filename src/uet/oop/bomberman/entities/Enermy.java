package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Enermy extends Character{
    protected AI _ai;
    public int direction;
    public Enermy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
       // setLayer(1);
    }


}
