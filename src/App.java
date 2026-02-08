/**
 *  Endless Gumball Machine 

    Simulates a vending machine that accepts nickles, dimes, and quarters.
    Dispenses red or yellow gumballs 
    Allows users to have their balancereturned to them. 

    CMPE 187 
    @authors Allen Le, Cody Ambrosio, Fleix Vo, Mark Haiko
    @date 02-07-2026
 */

import java.util.*;

public class App {

    //Coin values that are accepted by the machine (in cents). 
    final static int NICKLE = 5;
    final static int DIME = 10;
    final static int QUARTER = 25;

    //Cost for the gumball types an 
    final static int RED_BALL_COST = 5;
    final static int YELLOW_BALL_COST = 10;

    //Current user balance 
    static int balance = 0;

    /**
     * Helper method that "clears" terminal screen using ANSI escape codes. 
     * Makes it easier for UI and readability. 
     * Source - https://stackoverflow.com/a/10241460
     */

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("");
        System.out.println("");

    }

    /**
     * Helper method that pauses program exuection for a specific amount of time. 
     * 
     * @param milliseconds The amount of time to pause 
     */
    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } 
        
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Method that handles user coin insertion. 
     * Accepts only values of 5, 10 and 25 which is the value of a nickle, dime, and quarter.
     * Validates if user input matches the specified values. 
     * 
     * @param s Scanner used to read user input 
     */
    public void insertCoin(Scanner s) { //case 1 in the menu
        System.out.print("Insert a nickel, dime or quarter. (Type either 5, 10 or 25): ");

        int coin = s.nextInt();  

        if (coin == NICKLE || coin == DIME || coin == QUARTER) { 
            System.out.println("Coin accepted!\nTotal balance: " + coin + "¢");
            wait(2000);
            balance += coin;
        } else {  
            System.out.println("Invalid balance: " + coin + "¢ (will be returned)");
            wait(2000);
        }
    }

    /**
     * Dispenses one red gumball if balance is sufficient.
     * Subtract the cost from the user's balance. 
     */
    public void dispenseRedBall() {
        if (balance >= RED_BALL_COST) {
            balance -= RED_BALL_COST; 
            System.out.println("1 red gumball dispensed.");
            System.out.println("Current balance: " + balance + "¢");
            wait(2000); 
        }
        else {
            System.out.println("Balance is not enough to dispense a red gumball. Please insert more coins and try again."); 
            wait(2000);
        }
    }

    /**
     * Dispenses one yellow gumball if balance is sufficient.  
     * Sutract the cost from the user's balance. 
     */
    public void dispenseYellowBall() {
        if (balance >= YELLOW_BALL_COST) {
            balance -= YELLOW_BALL_COST; 
            System.out.println("1 yellow gumball dispensed.");
            System.out.println("Current balance: " + balance + "¢");
            wait (2000); 
        }
        else {
            System.out.println("Balance is not enough to dispense a yellow gumball. Please insert more coins and try again."); 
            wait(2000);
        }
    }

    /**
     * Returns all remaining balance to the user. 
     * If balance is 0, there is no action needed. 
     */
    public void returnMyChange() {
        if (balance == 0) {
            System.out.println("There is nothing to return.");
            wait(2000); 
        }
        else {
            System.out.println("Returning " + balance + "¢ to you.");
            balance -= balance; 
            wait(2000); 

        }
    }

    /**
     * Displays menu and uses switch cases for user choices.
     * Allows user to interact with the gumball machine. 
     * 
     * @param args Unused parametet for command line arguments
     */
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        App gum = new App();

        while (true) {
            clearScreen(); 
            System.out.println("--Endless Gumball Machine--");
            System.out.println("Red balls cost 5¢. Yellow balls cost 10¢. ");
            System.out.println("\nCurrent balance: " + balance + "¢");
            System.out.println("1. Insert coin");
            System.out.println("2. Dispense Red Ball (Pull Red Lever)");
            System.out.println("3. Dispense Yellow Ball (Pull Yellow lever)");
            System.out.println("4. Return My Change"); 
            System.out.println("0. Exit the System");
            System.out.print("> ");

            int choice; 

            if (s.hasNextInt()) {
                choice = s.nextInt();
            } else {
                System.out.println("Invalid option. Try again");
                s.next(); 
                wait(2000);
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println(); 
                    gum.insertCoin(s); 
                    break;
                
                case 2: 
                    System.out.println();
                    gum.dispenseRedBall();
                    break; 

                case 3: 
                    System.out.println();
                    gum.dispenseYellowBall();
                    break;
                
                case 4: 
                    System.out.println();
                    gum.returnMyChange();
                    break;

                case 0:
                    System.out.println("Exiting System");
                    s.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again");
                    wait(2000); 
            }
        }
    }
}
