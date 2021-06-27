package com.example.Hangman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/** this class is the core of the GUI hangman project
 * it implements keylistener, catching the input from the keyboard
 * and update the sentences in the interface according to the input */
public class Player implements KeyListener {

    // the position increments of the hangman
    private int x = 0;
    private int y = 20;
    private int velX = 3; // velocity of animation
    private int velY = 4;

    // IO stream
    private URL url;
    private BufferedReader read;

    // messages
    private String msg = "Guess a word:";
    private String msg2 = "To continue the game, Press ENTER";
    private String msg3 = "Missed letters: ";

    // detail
    private boolean finished = false;
    private String name; // string being guessed
    private String cur; // current string

    private int missCount = 0;
    private boolean flg = false;

    // for handling the keyboard event catcher
    private static Character lastKey = null;
    private String missLetters = "";
    private boolean[] hasLetters = new boolean[30];


    public Player() throws Exception {
        // open the hangman.txt file to obtain the names
        url = new URL("file:/C:/Users/Artis/IdeaProjects/Hangman/src/hangman.txt");
        read = new BufferedReader(new InputStreamReader(url.openStream()));

        init();
    }

    // initialize
    public void init() throws IOException {
        // read the name
        try {
            name = read.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // initialize the position and current string
        x = 0;
        y = 20;
        velX = 3;
        velY = 4;
        cur = "";
        for (int i = 0; i < name.length(); ++i) cur += "*";
        missCount = 0;
        for (int i = 0; i < 26; ++i) hasLetters[i] = false;
        missLetters = "";
    }

    // updating the graphics
    public void update(GUI_Hangman2 hm) {

        // listener for keyboard
        hm.addKeyListener(this);
        // if lose 7 times, finish
        if (missCount >= 7) {
            finished = true;
        }
        // if guessed, finish
        if (cur.equals(name)) {
            finished = true;
        }
        // hanging animation
        if (finished && missCount >= 7) {
            if (x <= -15 || x >= 15) velX = -velX;
            x += velX;
            if (y <= 0 || y >= 20) velY = -velY;
            y += velY;
        }
    }

    // painting the graphics
    public void paint(Graphics g, GUI_Hangman2 hm) {
        g.setColor(Color.BLACK);
        g.drawLine(100, 30, 100, 270);
        g.drawLine(100, 30, 240, 30);
        g.drawLine(240, 30, 240, 60);
        g.drawOval(215 + x, 40 + y, 50, 50);
        g.drawLine(240 + x, 90 + y, 240 + x, 150 + y);
        g.drawLine(170 + x, 130 + y, 220 + x, 80 + y);
        g.drawLine(260 + x, 80 + y, 310 + x, 130 + y);
        g.drawLine(190 + x, 200 + y, 240 + x, 150 + y);
        g.drawLine(240 + x, 150 + y, 290 + x, 200 + y);
        g.drawArc(60, 270, 80, 35, 0, 180);

        // message
        if (!finished) {
            g.drawString(msg, 200, 270);
            g.drawString(cur, 290, 270);
            if (missCount != 0) {
                g.drawString(msg3, 200, 300);
                g.drawString(missLetters, 290, 300);
            }
        } else {
            g.drawString(msg2, 200, 300);
        }
    }

    // keyboard event listening
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        // prevent processing one typed key for over one time
        if (lastKey == null || c != lastKey) {
            lastKey = c;
            // if it is enter
            if (finished && c == KeyEvent.VK_ENTER) {
                finished = false;

                // restart
                try {
                    init();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            // if it is a character
            if (c >= 'a' && c <= 'z') {

                flg = false;
                for (int i = 0; i < name.length(); ++i) {
                    if (name.charAt(i) == c
                            && cur.charAt(i) == '*') {
                        flg = true;
                        // updating the obtained answer
                        cur = cur.substring(0, i) + c + cur.substring(i + 1);
                    }
                }

                // if we did not guess correctly

                if (!flg) {
                    missCount++;
                    if (!hasLetters[c - 'a']) missLetters += c;
                }
                hasLetters[c - 'a'] = true;
                e.consume();
            }
        }
    }

    // these two methods we don't need to implement
    @Override
    public void keyPressed(KeyEvent e) {
        /*
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:{
                velX=5;
                break;
            }
            case KeyEvent.VK_LEFT:{
                velX=-5;
                break;
            }
            case KeyEvent.VK_UP:{
                velY=-5;
                break;
            }
            case KeyEvent.VK_DOWN:{
                velY=5;
                break;
            }
        }

         */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:{
                velX=0;
                break;
            }
            case KeyEvent.VK_LEFT:{
                velX=0;
                break;
            }
            case KeyEvent.VK_UP:{
                velY=0;
                break;
            }
            case KeyEvent.VK_DOWN:{
                velY=0;
                break;
            }
        }

         */
    }
}
