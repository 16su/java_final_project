package cn.edu.nju.cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Space extends JPanel {

    public enum Status {BEFORE, RUN, REPLAY, FINISH};

    private Status status;
    private int width, height;
    private int size;   //Location size
    private ExecutorService exec;
    private ArrayList<Creature> justPower;
    private ArrayList<Creature> evilPower;
    private ArrayList<ArrayList<Position<Creature>>> positions;
    private Recorder recorder;
    private Replayer replayer;
    private Formatter formatter;

    public Space(int w, int h, int sz) {
        width = w;
        height = h;
        size = sz;
        exec = Executors.newCachedThreadPool();
        status = Status.BEFORE;
        recorder = null;

        setFocusable(true);
        addKeyListener(new TAdapter());
        initField();
    }

    private void initField() {
        //Init Position and related object
        positions = new ArrayList<>();
        for (int i = 0; i < width; i += size) {
            ArrayList<Position<Creature>> temp = new ArrayList<>();
            for (int j = 0; j < height; j += size)
                temp.add(new Position<>(i, j));
            positions.add(temp);
        }
        formatter = new Formatter(positions);
        replayer = new Replayer(positions, this);
        Creature.setMap(positions);
        Creature.setSpace(this);

        //Init Creatures
        justPower = new ArrayList<>();
        justPower.add(new Grandpa());
        justPower.get(0).setCamp(true);
        for (int i = 0; i < 7; i++) {
            justPower.add(new Huluwa(i));
            justPower.get(i + 1).setCamp(true);
        }
        formatter.randomFormat(justPower);


        evilPower =  new ArrayList<>();
        evilPower.add(new Snake());
        evilPower.get(0).setCamp(false);
        evilPower.add(new Xiezi());
        evilPower.get(1).setCamp(false);
        for (int i = 0; i < 11; i++) {
            evilPower.add(new Louluo());
            evilPower.get(i + 2).setCamp(false);
        }
        formatter.randomFormat(evilPower);
    }

    private synchronized void showBG(Graphics g) {
        /* Draw background */
        URL loc = this.getClass().getClassLoader().getResource("background.png");
        ImageIcon imageIcon = new ImageIcon(loc);
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, 1536, 768, this);
        //g.drawImage(image, 768, 0, 768, 768, this);
    }

    private synchronized void showField(Graphics g) {
        if (recorder != null) {
            recorder.record();
            if (status == Status.FINISH)
                recorder.closePrinter();
        }

        int justAlive = 0, evilAlive = 0, creatures = 0;
        /* Draw creatures */
        for (int i = 0; i < positions.size(); i++)
            for (int j = 0; j < positions.get(i).size(); j++) {
                Position temp = positions.get(i).get(j);
                if (temp.getHolder() != null) {
                    if (temp.getHolder().getPower() != 0) {
                        creatures++;
                        if (temp.getHolder().getCamp())
                            justAlive++;
                        else
                            evilAlive++;
                    }
                    try {
                        g.drawImage(temp.getHolder().getImage(), temp.getX(), temp.getY(), 74, 74, this);
                    } catch (Exception e) {
                    }
                }
            }

        /* Check alive creatures */
        if (creatures != 0 && (justAlive == 0 || evilAlive == 0)) {
            if (status == Status.FINISH)
                return;
            status = Status.FINISH;
        }
    }


    public void setStatus(Status s) { status = s; }

    public Status getStatus() { return status; }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                if (status == Status.RUN || status == Status.REPLAY)
                    return;
                else if (status == Status.FINISH)
                    initField();
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                    File file = new File("record/" + df.format(new Date()) + ".txt");
                    if (!file.exists()) {
                        File dir = new File(file.getParent());
                        dir.mkdirs();
                        file.createNewFile();
                    }

                    PrintWriter printer = new PrintWriter(file);
                    recorder = new Recorder(printer, positions);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                status = Status.RUN;
                for (Creature aJustPower : justPower) exec.execute(aJustPower);
                for (Creature anEvilPower : evilPower) exec.execute(anEvilPower);


            }
            else if (key == KeyEvent.VK_L) {
                if (status == Status.RUN || status == Status.REPLAY)
                    return;
                status = Status.REPLAY;
               Thread thread = new Thread(replayer);
               thread.start();
            }
            else if (key == KeyEvent.VK_EQUALS) {
                if (status == Status.RUN)
                    Creature.setSleepTime(50);
                else if (status == Status.REPLAY)
                    replayer.setSleepTime(50);
            }
            else if (key == KeyEvent.VK_MINUS) {
                if (status == Status.RUN)
                    Creature.setSleepTime(-50);
                else if (status == Status.REPLAY)
                    replayer.setSleepTime(-50);
            }
            else if (key == KeyEvent.VK_R) {
                initField();
                repaint();
            }
            else if (key == KeyEvent.VK_P) {

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //remember to call the superclass method
        super.paintComponent(g);
        showBG(g);
        showField(g);
    }
}
