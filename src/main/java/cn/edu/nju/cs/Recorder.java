package cn.edu.nju.cs;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Recorder {
    private PrintWriter printer;
    private ArrayList<ArrayList<Position<Creature>>> map;

    public Recorder(PrintWriter p, ArrayList<ArrayList<Position<Creature>>> pos) {
        printer = p;
        map = pos;
    }

    public void record() {
        printer.println("--------");
        for (int i = 0; i < map.size(); i++)
            for (int j = 0; j < map.get(i).size(); j++) {
                Creature temp = map.get(i).get(j).getHolder();
                if (temp != null) {
                    printer.println(i + " " + j + " " + temp.getCamp() +" "+ temp.getPower() + " " + temp.getName());
                }
            }
    }

    public void closePrinter() {
        printer.close();
    }
}
