package cn.edu.nju.cs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Xiezi extends Creature {

    public Xiezi() {

        URL loc = this.getClass().getClassLoader().getResource("xiezi.png");
        ImageIcon imageIcon = new ImageIcon(loc);
        Image image = imageIcon.getImage();
        this.setImage(image);
        this.setName("xiezi");
        this.setPower(10);
        this.setPosition(null, -1, -1);
        this.setCamp(false);
    }
}
