package uet.oop.bomberman.entities;

public class AILow extends AI{
    @Override
    public int caculateDirection() {
        return random.nextInt(4);
    }
}
