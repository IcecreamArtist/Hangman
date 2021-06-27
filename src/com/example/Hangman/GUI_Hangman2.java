package com.example.Hangman;

import java.awt.*;
import java.applet.*;

/**
 the name is GUI_Hangman2 because GUI_Hangman is a failure...
 extends applet because the project is purely based on applet
 which means no jframe, no main function, no application
 implements runnable because this applet is a game
 it needs thread to execute
 */

public class GUI_Hangman2 extends Applet implements Runnable {
    // version id
    private static final long serialVersionUID = 1L;

    // thread
    Thread thread = new Thread(this);
    boolean running = true;

    // player object is for catching the keyboard event
    // and displaying the image according to event in the applet
    Player p;

    // for double buffering
    Image dbImage;
    Graphics dbg;


    // initialize
    @Override
    public void init() {
        setSize(450, 350);
        try {
            p = new Player();
        } catch (Exception e) {
            System.out.println("file not found");
        }
    }

    // start the thread
    @Override
    public void start() {
        thread.start();
    }

    // stop the thread
    @Override
    public void stop() {
        running = false;
    }

    // destroy the thread
    @Override
    public void destroy() {
        running = false;
    }

    // double buffering
    // which can make the animation fluent
    public void update(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paint(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }


    // run the thread
    @Override
    public void run() {
        while (running) {
            // update the image
            repaint();
            p.update(this);
            // every 100 milliseconds
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Error!");
            }
        }
    }


    // paint the graphs, invoking player's method
    public void paint(Graphics g) {
        p.paint(g, this);
    }
}
