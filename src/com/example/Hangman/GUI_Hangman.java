package com.example.Hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class GUI_Hangman extends JFrame {

    static String name;
    static String cur;

    GUI_Hangman(String title,String pic){
        super(title);

        BgPanel root = new BgPanel(pic);
        this.setContentPane(root);

        root.setLayout(null);
    }




    public static void main(String[] args) {

        java.io.File file = new java.io.File("hangman.txt");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input2 = new Scanner(System.in);

        GUI_Hangman frame = new GUI_Hangman("Hangman","Olafur-Eliasson-The-Weather-Project.jpeg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 752, 491);
        myApplet applet = new myApplet();
        frame.add(applet);
        frame.setVisible(true);
        // whether current is guessing the first name in the text file
        boolean isFirstName = true;

        // while there are words in the given file
        while (input.hasNext()) {
            if (!isFirstName) {
                // if we are not guessing the first name
                // we ask before giving the next name
                System.out.print("Do you want to guess another "
                        + "word? Enter y or n > ");
                String choice = input2.next();
                if (choice.charAt(0) == 'n') break;
            }
            isFirstName = false;

            // getting the current name
            String Name = input.next();

            // partially guessed string
            String cur = new String();
            for (int i = 0; i < Name.length(); ++i) cur += "*";
            int missCount = 0;

            // guessing
            while (!cur.equals(Name)) {
                // enter the character
                // ans[0] is the input character
                System.out.print("(Guess) Enter a letter in word ");
                System.out.print(cur + " > ");
                String ans = input2.next();

                // flg: whether we guess correctly this time
                // vis: whether the character we guess has occurred in the string
                boolean flg = false;
                boolean vis = false;

                // checking whether we guess correctly
                for (int i = 0; i < Name.length(); ++i) {
                    if (Name.charAt(i) == ans.charAt(0)) vis = true;
                    if (Name.charAt(i) == ans.charAt(0)
                            && cur.charAt(i) == '*') {
                        flg = true;
                        // updating the obtained answer
                        cur = cur.substring(0, i) + ans.charAt(0) + cur.substring(i + 1);
                        break;
                    }
                }

                // if we did not guess correctly
                if (!flg) {
                    missCount++;
                    if (!vis) System.out.println(ans.charAt(0)
                            + " is not in the word");
                    else System.out.println(ans.charAt(0)
                            + " is already in the word");
                }
            }

            // output the answer and the miss count
            System.out.println("The word is " + Name
                    + ". You missed " + missCount + " time");
        }
    }
}

class myApplet extends JApplet {
    myApplet(){

    }

    public void paint(Graphics g){
        g.setColor(Color.white);
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

    @Override
    public void init() {
        super.init();
    }


}

class BgPanel extends JPanel{
    Image image = null;

    public BgPanel(String pic){
        URL imageUrl = GUI_Hangman.class.getResource(pic);
        try{
            image= ImageIO.read(imageUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        int width = this.getWidth();
        int height = this.getHeight();
        g.clearRect(0,0,width,height);

        g.drawImage(image,0,0,width,height,null);

    }
}