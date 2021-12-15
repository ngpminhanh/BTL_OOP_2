package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;

import java.util.Comparator;

public class Layer implements Comparator<Entity> {

    @Override
    public int compare(Entity e1, Entity e2) {
        return Integer.compare(e1.getLayer(),e2.getLayer());
    }
}