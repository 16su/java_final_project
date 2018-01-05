package cn.edu.nju.cs;

public class Position<T extends Creature> {

    private T holder;
    private int x, y;   //Real coordinate in the screen

    Position(int inputX, int inputY) {
        x = inputX;
        y = inputY;
        holder = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public T getHolder() {
        return holder;
    }

    public void setHolder(T creature) {
        holder = creature;
    }
}
