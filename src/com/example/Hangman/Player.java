package com.example.Hangman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;

public class Player implements KeyListener {

    private int x=200;
    private int y=200;
    private int radius = 20;
    private int velX = 0;
    private int velY = 0;
    private URL url;
    private Image player;

    public Player(GUI_Hangman2 hm) throws MalformedURLException {
        url = new URL("file:/C:/Users/Artis/IdeaProjects/Hangman/src");
        player = hm.getImage(url, "file:/C:/Users/Artis/IdeaProjects/Hangman/src/man.png");
    }

    public void update(GUI_Hangman2 hm){
        hm.addKeyListener(this);
        x+=velX;
        y+=velY;
    }


    public void paint(Graphics g,GUI_Hangman2 hm){
//        g.setColor(Color.RED);
//       g.fillOval(x,y,radius*2,radius*2);
        g.drawImage(player,x,y,hm);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
    }
}
