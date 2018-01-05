package cn.edu.nju.cs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Huluwa extends Creature {
    public Huluwa(int order) {

        //TODO:icon decided by order
        URL loc = this.getClass().getClassLoader().getResource(order + 1 + ".png");
        ImageIcon imageIcon = new ImageIcon(loc);
        Image image = imageIcon.getImage();
        this.setImage(image);
        this.setName(String.valueOf(order + 1));
        this.setPower(7);
        this.setPosition(null, -1, -1);
        this.setCamp(true);
    }

}
