package com.example.Hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanSketch extends JApplet {

    public void paint(Graphics g) {
        // set the color
        g.setColor(Color.pink);
        // draw the vertical pillar
        g.drawLine(100, 30, 100, 270);
        // draw the horizontal bar
        g.drawLine(100, 30, 240, 30);
        // draw the rope
        g.drawLine(240, 30, 240, 60);
        // draw the head
        g.drawOval(215, 60, 50, 50);
        // draw the body
        g.drawLine(240, 110, 240, 170);
        // draw the left hand
        g.drawLine(170, 150, 220, 100);
        // draw the right hand
        g.drawLine(260, 100, 310, 150);
        // draw the left foot
        g.drawLine(190, 220, 240, 170);
        // draw the right foot
        g.drawLine(240, 170, 290, 220);
        // draw the base
        g.drawArc(60, 270, 80, 35, 0, 180);
    }
}
