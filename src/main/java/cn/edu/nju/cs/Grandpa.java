package cn.edu.nju.cs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Grandpa extends Creature {
    public Grandpa() {

        URL loc = this.getClass().getClassLoader().getResource("grandpa.png");
        ImageIcon imageIcon = new ImageIcon(loc);
        Image image = imageIcon.getImage();
        this.setImage(image);
        this.setName("grandpa");
        this.setPower(2);
        this.setPosition(null, -1, -1);
        this.setCamp(true);
    }
}
