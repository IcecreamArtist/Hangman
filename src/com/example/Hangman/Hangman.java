package com.example.Hangman;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Hangman {

    public static Scanner getName(){
        java.io.File file = new java.io.File("hangman.txt");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void main(String[] args) throws FileNotFoundException {

        // input: the input stream of the file
        Scanner input = getName();
        // input2: the input stream of the keyboard
        Scanner input2 = new Scanner(System.in);

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
