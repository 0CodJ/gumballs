import java.util.*;

public class App {

    final static int NICKLE = 5;
    final static int DIME = 10;
    final static int QUARTER = 25;

    final static int RED_BALL_COST = 5;
    final static int YELLOW_BALL_COST = 10;

    static int balance = 0;

    public static void clearScreen() {
        // Source - https://stackoverflow.com/a/10241460
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("");
        System.out.println("");

    }

    public static void wait(int milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}



   
    public void insertCoin(Scanner s) { //case 1 in the menu
        System.out.print("Insert a nickel, dime or quarter. (Type either 5, 10 or 25): ");

        int coin = s.nextInt();  

        if (coin == NICKLE || coin == DIME || coin == QUARTER) { //Checks if user inserted 5, 10 or 25. If the value is correct, it gets added to the balance. 
            System.out.println("Coin accepted!\nTotal balance: " + coin + "¢");
            wait(2000);
            balance += coin;
        } else { //If the user inputs a invalid value for their balance, it will be rejected. 
            System.out.println("Invalid balance: " + coin + "¢ (will be returned)");
            wait(2000);
        }
    }


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

    public static void main(String[] args) {
        

        Scanner s = new Scanner(System.in);
        App gum = new App();

        while (true) {
            clearScreen(); //uses heleper method to make the terminal more user friendly 
            System.out.println("--Endless Gumball Machine--");
            System.out.println("Red balls cost 5¢. Yellow balls cost 10¢. ");
            System.out.println("\nCurrent balance: " + balance + "¢");
            System.out.println("1. Insert coin");
            System.out.println("2. Dispense Red Ball");
            System.out.println("3. Dispense Yellow Ball");
            System.out.println("4. Return My Change"); 
            System.out.println("0. Exit the System");
            
            System.out.print("> ");


            int choice; 

            if (s.hasNextInt()) {
                choice = s.nextInt();
            } else {
                System.out.println("Invalid option. Try again");
                s.next(); // discard bad input
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
