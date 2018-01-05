package cn.edu.nju.cs;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Replayer implements Runnable {

    private static ArrayList<ArrayList<Position<Creature>>> map;
    private Space space;
    private int sleepTime;

    public Replayer(ArrayList<ArrayList<Position<Creature>>> pos, Space s) {
        map = pos;
        space = s;
        sleepTime = 200;
    }

    public void setSleepTime(int time) {
        if (time == 50) {
            sleepTime += (sleepTime < 350) ? 50 : 0;
        }
        else
            sleepTime += (sleepTime > 50) ? -50 : 0;
        //System.out.println(sleepTime);
    }

    @Override
    public void run() {
        JFileChooser fileChooser = new JFileChooser(new File("."));
        int ret = fileChooser.showOpenDialog(null);
        File file;
        if (ret == JFileChooser.APPROVE_OPTION)
            file = fileChooser.getSelectedFile();
        else {
            space.setStatus(Space.Status.FINISH);
            return;
        }

        /*Reset field*/
        for (int i = 0; i < map.size(); i++)
            for (int j = 0; j < map.get(i).size(); j++) {
                map.get(i).get(j).setHolder(null);
            }
        space.repaint();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String lineTxt;

            while ((lineTxt = bufferedReader.readLine()) != null) {

                if (lineTxt.equals("--------")) {
                    space.repaint();
                    Thread.sleep(sleepTime);
                    for (int i = 0; i < map.size(); i++)
                        for (int j = 0; j < map.get(i).size(); j++) {
                            map.get(i).get(j).setHolder(null);
                        }
                }
                else {
                    String[] temp = lineTxt.split(" ");
                    int x = Integer.valueOf(temp[0]), y = Integer.valueOf(temp[1]);
                    int power = Integer.valueOf(temp[3]);
                    Creature creature = null;
                    switch (temp[4]) {
                        case "1":
                            creature = new Huluwa(0);
                            break;
                        case "2":
                            creature = new Huluwa(1);
                            break;
                        case "3":
                            creature = new Huluwa(2);
                            break;
                        case "4":
                            creature = new Huluwa(3);
                            break;
                        case "5":
                            creature = new Huluwa(4);
                            break;
                        case "6":
                            creature = new Huluwa(5);
                            break;
                        case "7":
                            creature = new Huluwa(6);
                            break;
                        case "grandpa":
                            creature = new Grandpa();
                            break;
                        case "snake":
                            creature = new Snake();
                            break;
                        case "louluo":
                            creature = new Louluo();
                            break;
                        case "xiezi":
                            creature = new Xiezi();
                            break;
                    }
                    creature.setPosition(map.get(x).get(y), x, y);
                    if (power == 0) creature.setPower(0);
                }
            }
            space.repaint();
            reader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
