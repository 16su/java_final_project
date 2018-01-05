package cn.edu.nju.cs;

import javax.swing.*;

public class Main extends JFrame{


    public Main() {
        initUI();
    }

    public void initUI() {
        Space bf = new Space(1536, 768, 77);
        add(bf);
        bf.setVisible(true);

        //System.out.println("中文");
        setTitle("葫芦娃大战妖精");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1536, 802);
        setResizable(false);
    }

    public static void main(String []args) {
        Main main = new Main();
        main.setVisible(true);
    }
}
