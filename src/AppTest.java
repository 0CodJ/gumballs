import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.*;

public class AppTest {

    private App gum;

    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        gum = new App();
        App.balance = 0; // reset static state before every test

        // Capture System.out (optional but useful)
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private Scanner scannerWithInput(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    private String output() {
        return outContent.toString();
    }

    // -----------------------
    // Insert coin tests
    // -----------------------

    @Test
    void insertNickel_increasesBalanceBy5() {
        Scanner s = scannerWithInput("5\n");
        gum.insertCoin(s);

        assertEquals(5, App.balance);
        assertTrue(output().contains("Coin accepted"));
    }

    @Test
    void insertDime_increasesBalanceBy10() {
        Scanner s = scannerWithInput("10\n");
        gum.insertCoin(s);

        assertEquals(10, App.balance);
        assertTrue(output().contains("Coin accepted"));
    }

    @Test
    void insertInvalidCoin_doesNotChangeBalance() {
        Scanner s = scannerWithInput("7\n");
        gum.insertCoin(s);

        assertEquals(0, App.balance);
        assertTrue(output().contains("Invalid balance"));
    }

    // -----------------------
    // Dispense tests
    // -----------------------

    @Test
    void dispenseRed_withEnoughBalance_decreasesBy5() {
        App.balance = 10;

        gum.dispenseRedBall();

        assertEquals(5, App.balance);
        assertTrue(output().contains("red gumball dispensed"));
    }

    @Test
    void dispenseRed_withInsufficientBalance_doesNotChangeBalance() {
        App.balance = 0;

        gum.dispenseRedBall();

        assertEquals(0, App.balance);
        assertTrue(output().toLowerCase().contains("not enough"));
    }

    @Test
    void dispenseYellow_withEnoughBalance_decreasesBy10() {
        App.balance = 25;

        gum.dispenseYellowBall();

        assertEquals(15, App.balance);
        assertTrue(output().contains("yellow gumball dispensed"));
    }

    // -----------------------
    // Change return tests
    // -----------------------

    @Test
    void returnMyChange_withBalance_returnsToZero() {
        App.balance = 15;

        gum.returnMyChange();

        assertEquals(0, App.balance);
        assertTrue(output().contains("Returning 15"));
    }

    @Test
    void scenario_quarter_twoReds_returnChange_endsAtZero() {
        // simulate: insert 25, dispense red twice, return change
        // (insertCoin needs scanner input)
        Scanner s = scannerWithInput("25\n");
        gum.insertCoin(s);

        gum.dispenseRedBall();
        gum.dispenseRedBall();
        gum.returnMyChange();

        assertEquals(0, App.balance);
        // after two reds from 25, balance should be 15 before return
        assertTrue(output().contains("Returning 15"));
    }
}
