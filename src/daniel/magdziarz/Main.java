/*                  THIS IS WRITTEN FOR JAVA 17
Author: Daniel Magdzirz
**This program is a test case between Math.random() and Random.
1.This program uses Math.random() to generate a random number and Random to generate another
 random number. Once it has both, it compares them.
2.It asks the user to enter a number for the amount of comparisons they would like to see
 and it asks for how big the compared numbers should be.
3. If the user won't enter any numbers but will just run the program then the default number of 10
 comparisons with numbers not grater than 9 will be set by default. */

package daniel.magdziarz;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int iteration = -1; //stores the amount of number drawings.
        //set to -1 so if user won't enter a number for 'iteration', it will get a 10 by default.
        int numberSize = -1; // stores the largest possible number.
        // -1 means the user did not set the value and a default value will be set.

        boolean quit = false;
        mainMenu();

        while (!quit) {
            int userChoice = inputValidationInt();

            switch (userChoice) {
                case 0 -> mainMenu();
                case 1 -> aboutTheProgram();
                case 2 -> iteration = setIteration();
                case 3 -> numberSize = setNumberSize();
                case 4 -> runNumbers(iteration, numberSize);
                case 5 -> quit = true;
                default -> System.out.println("Wrong Main Menu selection");
            }
        }
        System.out.println("Bye...");
        keyboard.close();
    }

    public static void mainMenu() {
        System.out.println("""
                \t\tMath.random vs Random Function MAIN MENU
                0. Main Menu.
                1. About the program...
                2. Set the number of comparisons.
                3. Set the number size.
                4. Run numbers.              
                5. Quit.""");
    }

    public static int inputValidationInt() {

        while (true) {
            System.out.print("     Enter a number: ");
            String intSTR = keyboard.nextLine();
            try {
                return Integer.parseInt(intSTR);
            } catch (Exception Error01) {
                System.out.println("\"" + intSTR + "\" is a wrong input. ");
            }
        }
    }

    public static int setIteration() {

        while (true) {
            System.out.println("   \nHow many drawings would you like to see?");
            int iterrations = inputValidationInt();
            if (iterrations >= 1 && iterrations <= 1000) {
                System.out.println("  " + iterrations + " different drawings will be displayed.");
                return iterrations;
            } else {
                System.out.println("Only numbers from 1 to 1000 are allowed. Try again.");
            }
        }
    }

    public static int setNumberSize() {
        while (true) {
            System.out.println("""
                    The lowest number that can be generated is 1.
                    Enter the value for the highest possible number to be generated.""");
            int numSize = inputValidationInt();
            if (numSize >=1 && numSize <= 1_000_000){
                System.out.printf("   The highest number that can be generated is set to: %,d%n ", numSize);
                return numSize;
            } else if (numSize < 0){
                System.out.println("Negative numbers are not allowed");
            } else if (numSize > 1_000_000){
                System.out.printf("Numbers greater than %,d are not allowed%n", 1000000);
            }
        }
    }

    public static void runNumbers(int iterations, int numberSize) {
        if (iterations == -1) {
            iterations = 10;
            System.out.println("  **The user did not set the number for the amount of comparisons\n" +
                    "(Main Menu - option 2). Default number of iterations is set to: " + iterations);
        }

        if (numberSize == -1) {
            numberSize = 9;
            System.out.println("  **The user did not set the greatest number allowance for this\n" +
                    "comparison, therefore, a default number of " + numberSize + " was set.");
        }

        System.out.printf("  Lowest possible number: %d%n  Highest Possible number: %,d%n", 1, numberSize);

        Random randomNumber = new Random();

        int mathRandomTotal = 0;
        int randomTotal = 0;

        int mathRandomGrater = 0;
        int randomGrater = 0;
        int bothEqual = 0;

        for (int i = 0; i < iterations; i++) {
            int num1 = (int) (Math.random() * numberSize) + 1;
            int num2 = randomNumber.nextInt(numberSize) + 1;

            mathRandomTotal += num1;
            randomTotal += num2;

            if(num1 > num2){
                mathRandomGrater += 1;
            } else if(num1 < num2){
                randomGrater += 1;
            } else {
                bothEqual += 1;
            }

            System.out.printf("%n   Comparison %d:%nMath.random() drawn: %,d%n" +
                            "      Random drawn: %,2d",(i+1), num1, num2);
            System.out.println("\n" + getGreaterResults(num1, num2));
            System.out.printf("Math.Random() sum = %,d %n", mathRandomTotal);
            System.out.printf("      Random sum = %,d %n", randomTotal);
            System.out.println("Math.random() was grater than Random " + mathRandomGrater + " times " +
                    "out of " + (i+1) + " trails.");
            System.out.println("Random was grater than Math.random() " + randomGrater + " times " +
                    "out of " + (i+1) + " trails.");
            System.out.println("Math.random() & Random were equal " + bothEqual + " times " +
                    "out of " + (i+1) + " trails.");

        }
        System.out.print("\n\t\tComparison complete. View results and press Enter when done.\n");
        keyboard.nextLine();
        mainMenu();
    }

    public static void aboutTheProgram(){
        System.out.println("""
                         THIS IS WRITTEN FOR JAVA 17
         1. This program uses Math.random() to generate a random number and Random
            to generate another random number. Once it has both, it compares them.
         2. It asks the user to enter a number for the amount of comparisons they
            would like to see and it asks for how big the compared numbers should be.
         3. If the user won't enter any numbers but will just run the program, then the default
            number of 10 comparisons, with numbers not grater than 9, will be set by default.""");
    }

    public static String getGreaterResults(int num1, int num2){
        if(num1 > num2){
            return String.format("Math.random() drawn a greater number. Difference: %,d", (num1 - num2));
        } else if (num2 > num1){
            return String.format("Random drawn a greater number. Difference: %,d", (num2 - num1));
        } else {
            return ("Math.random() & Random both drawn the same numbers. Difference: " + (num1 - num2));
        }
    }
}