package cn.edu.nju.cs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Louluo extends Creature{
    public Louluo() {

        URL loc = this.getClass().getClassLoader().getResource("frog.png");
        ImageIcon imageIcon = new ImageIcon(loc);
        Image image = imageIcon.getImage();
        this.setImage(image);
        this.setName("louluo");
        this.setPower(3);
        this.setPosition(null, -1, -1);
        this.setCamp(false);
    }
}
