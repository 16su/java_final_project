package cn.edu.nju.cs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Snake extends Creature{
    public Snake() {

        URL loc = this.getClass().getClassLoader().getResource("snake.png");
        ImageIcon imageIcon = new ImageIcon(loc);
        Image image = imageIcon.getImage();
        this.setImage(image);
        this.setName("snake");
        this.setPower(8);
        this.setPosition(null, -1, -1);
        this.setCamp(false);
    }
}
