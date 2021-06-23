package com.example.Hangman;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class GUI_Hangman extends JApplet{

    String s;

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
        getContentPane().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( c != KeyEvent.CHAR_UNDEFINED ) {
                    s = s + c;
                    repaint();
                    e.consume();
                    System.out.println("test");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        DisplayGraphics m = new DisplayGraphics();
      //  getContentPane().add(m);

        JButton button = new JButton("test");
        button.setSize(50,50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // testNewWindow();
            }
        });

        JPanel panel = new JPanel();
        panel.add(m);
        setContentPane(panel);

    }

}

class DisplayGraphics extends Canvas{
    public void paint(Graphics g){
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