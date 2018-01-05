package cn.edu.nju.cs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public abstract class Creature implements Runnable{

    private static Space space;
    private static ArrayList<ArrayList<Position<Creature>>> map;
    private static int sleepTime = 300;

    private String name;    //Use for replaying
    private int x, y;   //Index of the position in the screen
    private int power;  //Use for fighting
    private boolean camp;   //Use to distinct creatures
    private Image image;
    private Position position;  //Position in the map

    public static void setMap(ArrayList<ArrayList<Position<Creature>>> positions) { map = positions; }

    public static void setSpace(Space sp) { space = sp; }

    public void setPosition(Position position, int posX, int posY) {    //(posX, posY) is the index of the position int the screen
        x = posX;
        y = posY;
        this.position = position;
        if (position != null)
            position.setHolder(this);
    }

    public void setPower(int p) {
        power = p;
        if (power <= 0) {
            power = 0;
            URL loc = this.getClass().getClassLoader().getResource("die.png");
            ImageIcon imageIcon = new ImageIcon(loc);
            Image image = imageIcon.getImage();
            this.setImage(image);
        }
    }

    public void setName(String n) {
        name = n;
    }

    public void setCamp(boolean justice) {
        camp = justice;
    }

    public void setImage(Image img) {

        image = img;
    }

    public Image getImage() {
        return this.image;
    }

    public int getPower() {
        return power;
    }

    public boolean getCamp() {
        return camp;
    }

    public String getName() {
        return name;
    }

    public static void setSleepTime(int time) {
        if (time == 50) {
            sleepTime += (sleepTime < 500) ? 50 : 0;
        }
        else
            sleepTime += (sleepTime > 100) ? -50 : 0;
    }

    private void move() {

        /*Init*/
        synchronized (map) {
             /*Compare power and decide the winner*/
            ArrayList<Position<Creature>> cmp = new ArrayList<>();
            boolean movable[] = {false, false, false, false};
            if (x - 1 >= 0) {
                if (map.get(x - 1).get(y).getHolder() == null)
                    movable[0] = true;
                cmp.add(map.get(x - 1).get(y));
            }
            if (x + 1 < map.size()) {
                if (map.get(x + 1).get(y).getHolder() == null)
                    movable[1] = true;
                cmp.add(map.get(x + 1).get(y));
            }
            if (y - 1 >= 0) {
                if (map.get(x).get(y - 1).getHolder() == null)
                    movable[2] = true;
                cmp.add(map.get(x).get(y - 1));
            }
            if (y + 1 < map.get(x).size()) {
                if (map.get(x).get(y + 1).getHolder() == null)
                    movable[3] = true;
                cmp.add(map.get(x).get(y + 1));
            }
            Random random = new Random();
            int myPower = power;
            myPower = (myPower > 0) ? random.nextInt(myPower) : 0;

            for (Position p : cmp) {
                if (p.getHolder() != null && p.getHolder().getCamp() != camp) {
                    p.getHolder().setPower(p.getHolder().getPower() - myPower);
                }
            }

            /*Decide next step*/
            int toMove = random.nextInt(4);
            if (!movable[toMove])
                return;
            position.setHolder(null);
            switch (toMove) {
                case 0 : setPosition(map.get(x - 1).get(y), x - 1, y); break;
                case 1 : setPosition(map.get(x + 1).get(y), x + 1, y); break;
                case 2 : setPosition(map.get(x).get(y - 1), x, y - 1); break;
                case 3 : setPosition(map.get(x).get(y + 1), x, y + 1); break;
            }
        }
    }

    public void run() {
        while (space.getStatus() == Space.Status.RUN || space.getStatus() == Space.Status.REPLAY) {
            if (position == null) {
                setPower(0);
                return;
            }
            if (getPower() <= 0) return;

            try {
                move();
                Thread.sleep(sleepTime);
                space.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
