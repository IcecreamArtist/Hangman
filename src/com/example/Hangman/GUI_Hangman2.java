package com.example.Hangman;

import java.awt.*;
import java.applet.*;

public class GUI_Hangman2 extends Applet implements Runnable {
    private static final long serialVersionUID = 1L;
    Thread thread = new Thread(this);
    boolean running = true;
    Player p;
    Image dbImage;
    Graphics dbg;


    @Override
    public void init() {

        setSize(450, 350);
        try {
            p = new Player(this);
        } catch (Exception e) {
            System.out.println("file not found");
        }
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public void destroy() {
        running = false;
    }

    // double buffering
    public void update(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paint(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }


    @Override
    public void run() {
        while (running) {
            repaint();
            p.update(this);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Error!");
            }
        }
    }

    public void paint(Graphics g) {
        p.paint(g, this);
    }
}
