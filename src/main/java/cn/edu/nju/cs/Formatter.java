package cn.edu.nju.cs;

import java.util.ArrayList;
import java.util.Random;

public class Formatter<T extends Creature> {

    private ArrayList<ArrayList<Position<T>>> map;

    public Formatter(ArrayList<ArrayList<Position<T>>> positions) {
        map = positions;
    }

    public void randomFormat(ArrayList<T> creatures) {
        try {
            Random random = new Random();
            int num = random.nextInt(8);
            switch (num) {
                case 0:
                    changShe(creatures);
                    break;
                case 1:
                    heYi(creatures);
                    break;
                case 2:
                    yanXing(creatures);
                    break;
                case 3:
                    hengE(creatures);
                    break;
                case 4:
                    yuLin(creatures);
                    break;
                case 5:
                    fangDou(creatures);
                    break;
                case 6:
                    yanYue(creatures);
                    break;
                case 7:
                    fengShi(creatures);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changShe(ArrayList<T> creatures) throws Exception{
        boolean camp = creatures.get(0).getCamp();
        int col = camp ? 0 : map.size() - 1, row = 0;
        for (; row < map.get(col).size() && row < creatures.size(); row++)
            creatures.get(row).setPosition(map.get(col).get(row), col, row);
    }

    public void heYi(ArrayList<T> creatures) throws Exception{
        boolean camp = creatures.get(0).getCamp();
        if (camp) {
            creatures.get(0).setPosition(map.get(0).get(5), 0, 5);
            creatures.get(1).setPosition(map.get(3).get(5), 3, 5);
            for (int i = 0; i < 3; i++) {
                creatures.get(2 + 2 * i).setPosition(map.get(2 - i).get(4 - i), 2 - i, 4 - i);
                creatures.get(3 + 2 * i).setPosition(map.get(2 - i).get(6 + i), 2 - i, 6 + i);
            }
        }
        else {
            creatures.get(0).setPosition(map.get(map.size() - 1).get(5), map.size() - 1, 5);
            creatures.get(1).setPosition(map.get(map.size() - 4).get(5), map.size() - 4, 5);
            for (int i = 0; i < 3; i++) {
                creatures.get(2 + 2 * i).setPosition(map.get(map.size() - 1 - i).get(2 + i), map.size() - 1 - i, 2 + i);
                creatures.get(3 + 2 * i).setPosition(map.get(map.size() - 1 - i).get(8 - i), map.size() - 1 - i, 8 - i);
            }
        }
    }

    public void yanXing(ArrayList<T> creatures) throws Exception{
        boolean camp = creatures.get(0).getCamp();
        int col = camp ? 0 : map.size() - 1, row = 0;
        for (; row < map.get(col).size() && row < creatures.size(); row++, col += camp ? 1 : -1)
            creatures.get(row).setPosition(map.get(col).get(row), col, row);
    }

    public void hengE(ArrayList<T> creatures) throws Exception{ //NullPointerException
        boolean camp = creatures.get(0).getCamp();
        int col = camp ? 0 : map.size() - 1, row = 0;
        creatures.get(0).setPosition(map.get(col).get(row), col, row);
        for (int i = creatures.size() - 1; i > 0; i--) {
            row += 2;
            if (row > map.get(col).size() - 1) {
                col += camp ? 1 : -1;
                row = camp ? col % 2 : col % 2 + 1;
            }
            if (col == 3) break;
            creatures.get(i).setPosition(map.get(col).get(row), col, row);
        }
    }

    public void yuLin(ArrayList<T> creatures) throws Exception{ //NullPointerException
        boolean camp = creatures.get(0).getCamp();
        int col = camp ? 0 : map.size() - 1;
        creatures.get(0).setPosition(map.get(col).get(5), col, 5);
        if (camp) {
            for (int i = 1; i < 3; i++)
                creatures.get(i).setPosition(map.get(4 - i).get(4 + i), 4 - i, 4 + i);
            for (int i = 3; i < 8; i++)
                creatures.get(i).setPosition(map.get(1).get(i), 1, i);
        }
        else {
            col -= 4;
            creatures.get(1).setPosition(map.get(col).get(5), col, 5);
            col++;
            creatures.get(2).setPosition(map.get(col).get(6), col, 6);
            col++;
            for (int i = 3; i < 6; i++)
                creatures.get(i).setPosition(map.get(col).get(i + 1), col, i + 1);
            col++;
            for (int i = 6; i < 11; i++)
                creatures.get(i).setPosition(map.get(col).get(i - 3), col, i - 3);
        }
    }

    public void fangDou(ArrayList<T> creatures) throws Exception{
        boolean camp = creatures.get(0).getCamp();
        int col = camp ? 0 : map.size() - 1;
        creatures.get(0).setPosition(map.get(col).get(5), col, 5);
        col = camp ? col + 4 : col - 4;
        creatures.get(1).setPosition(map.get(col).get(5), col, 5);
        col += camp ? -1 : 1;
        for (int i = 0; i < 3; i++, col += camp ? -1 : 1) {
            creatures.get(2 + 2 * i).setPosition(map.get(col).get(7 - Math.abs(i - 1)), col, 7 - Math.abs(i - 1));
            creatures.get(3 + 2 * i).setPosition(map.get(col).get(3 + Math.abs(i - 1)), col, 3 + Math.abs(i - 1));
        }
    }

    public void yanYue(ArrayList<T> creatures) throws Exception{
        boolean camp = creatures.get(0).getCamp();
        if (camp) {
            for (int i = 0; i < 5; i++)
                creatures.get(i).setPosition(map.get(0).get(3 + i), 0, 3 + i);
            for (int i = 5; i < 8; i++)
                creatures.get(i).setPosition(map.get(1).get(i - 1), 1, i - 1);
        }
        else {
            int col = map.size() - 1;
            creatures.get(0).setPosition(map.get(col).get(5), col, 5);
            col -= 3;
            creatures.get(1).setPosition(map.get(col).get(5), col, 5);
            creatures.get(2).setPosition(map.get(col).get(4), col, 4);
            creatures.get(3).setPosition(map.get(col).get(6), col, 6);
            col++;
            for (int i = 4; i < 9; i++)
                creatures.get(i).setPosition(map.get(col).get(i - 1), col, i - 1);
            for (int i = 0; i < 2; i++) {
                col++;
                creatures.get(9 + 2 * i).setPosition(map.get(col).get(3 - i), col, 3 - i);
                creatures.get(10 + 2 * i).setPosition(map.get(col).get(7 + i), col, 7 + i);
            }
        }
    }

    public void fengShi(ArrayList<T> creatures) throws Exception{
        boolean camp = creatures.get(0).getCamp();
        int col = camp ? 0 : map.size() - 1;
        creatures.get(0).setPosition(map.get(col).get(5), col, 5);
        if (camp) {
            col += 3;
            creatures.get(1).setPosition(map.get(col).get(5), col, 5);
            for (int i = 0; i < 2; i++) {
                col--;
                creatures.get(2 + 3 * i).setPosition(map.get(col).get(5), col, 5);
                creatures.get(3 + 3 * i).setPosition(map.get(col).get(4 - i), col, 4 - i);
                creatures.get(4 + 3 * i).setPosition(map.get(col).get(6 + i), col, 6 + i);
            }
        } else {
            col -= 5;
            creatures.get(1).setPosition(map.get(col).get(5), col, 5);
            for (int i = 0; i < 3; i++) {
                col++;
                creatures.get(2 + 3 * i).setPosition(map.get(col).get(5), col, 5);
                creatures.get(3 + 3 * i).setPosition(map.get(col).get(4 - i), col, 4 - i);
                creatures.get(4 + 3 * i).setPosition(map.get(col).get(6 + i), col, 6 + i);
            }
            col++;
            creatures.get(11).setPosition(map.get(col).get(5), col, 5);
        }
    }
}
