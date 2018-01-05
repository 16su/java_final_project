package cn.edu.nju.cs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FormateTest {

    private Formatter<Creature> formatter;
    private ArrayList<ArrayList<Position<Creature>>> positions;
    @Before
    public void init() {
        positions = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ArrayList<Position<Creature>> temp = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                temp.add(new Position<>(i, j));
            positions.add(temp);
        }
        formatter = new Formatter<>(positions);

    }

    @Test
    public void formateTest() {
        ArrayList<Creature> justPower;
        ArrayList<Creature> evilPower;
        justPower = new ArrayList<>();
        justPower.add(new Grandpa());
        justPower.get(0).setCamp(true);
        for (int i = 0; i < 7; i++) {
            justPower.add(new Huluwa(i));
            justPower.get(i + 1).setCamp(true);
        }

        evilPower =  new ArrayList<>();
        evilPower.add(new Snake());
        evilPower.get(0).setCamp(false);
        evilPower.add(new Xiezi());
        evilPower.get(1).setCamp(false);
        for (int i = 0; i < 11; i++) {
            evilPower.add(new Louluo());
            evilPower.get(i + 2).setCamp(false);
        }
        try {
            formatter.yanYue(justPower);
            formatter.yanXing(evilPower);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < positions.size(); i++) {
            for (int j = 0; j < positions.get(0).size(); j++) {
                Creature temp = positions.get(i).get(j).getHolder();
                if (temp != null) {
                    System.out.println("(" + i + "," + j+ ")" + temp.getName());
                }
            }
        }
    }

}
