package com.example.Hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class GUI_Hangman extends JApplet implements KeyListener {
    public void init() {
    //Execute a job on the event-dispatching thread:
    //creating this applet's GUI.
    try {
        javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    } catch (Exception e) {
        System.err.println("createGUI didn't successfully complete");
    }
}

    private void createGUI() {
        JLabel label = new JLabel(
                "You are successfully running a Swing applet!");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createMatteBorder(200,200,1,1,Color.black));

        addKeyListener(this);
        DisplayGraphics m = new DisplayGraphics();
        getContentPane().add(m);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

class DisplayGraphics extends Canvas{
    public void paint(Graphics g){
       // getGraphics().drawImage(image,0,0,getWidth(),getHeight(),null);
        // 下面的没用
        setBackground(Color.black);

        // basic part
        g.setColor(Color.RED);
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
        g.drawArc(60, 270, 80, 35, 0,180);
    }
}