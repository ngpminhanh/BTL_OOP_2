package uet.oop.bomberman.entities;

public class AIHigh extends AI{
    Bomber _bomber;
    Enermy _e;
    public AIHigh(Bomber bomber, Enermy e) {
        this._bomber = bomber;
        this._e = e;
    }

    protected int calculateColDirection() {
        if(_bomber.getX() < _e.getX())
            return 3;
        else if(_bomber.getX() > _e.getX())
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {
        if (_bomber.getY() < _e.getY())
            return 0;
        else if (_bomber.getY() > _e.getY())
            return 2;
        return -1;
    }

    @Override
    public int caculateDirection() {
        return random.nextInt(4);
    }
}
