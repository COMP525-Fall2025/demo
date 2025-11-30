package demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class Lab14Test {

    // -------------------------------------------------------------
    // Helper: capture output from Lab14.printBinary(n)
    // -------------------------------------------------------------
    private String capturePrintedBinary(int n) {
        PrintStream originalOut = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        Lab14.printBinary(n);          // call the method under test

        System.setOut(originalOut);    // restore System.out

        return baos.toString().trim(); // get printed text (single line)
    }

    // -------------------------------------------------------------
    // Tests for printBinary (queue-based binary of n)
    // -------------------------------------------------------------

    @Test
    public void testPrintBinarySmallValues() {
        assertEquals("1", capturePrintedBinary(1));
        assertEquals("10", capturePrintedBinary(2));
        assertEquals("11", capturePrintedBinary(3));
        assertEquals("100", capturePrintedBinary(4));
        assertEquals("101", capturePrintedBinary(5));
        assertEquals("110", capturePrintedBinary(6));
        assertEquals("111", capturePrintedBinary(7));
    }

    @Test
    public void testPrintBinaryMediumValues() {
        assertEquals("1000", capturePrintedBinary(8));          // 8
        assertEquals("1001", capturePrintedBinary(9));          // 9
        assertEquals("1010", capturePrintedBinary(10));         // 10
        assertEquals("1111", capturePrintedBinary(15));         // 15
        assertEquals("10000", capturePrintedBinary(16));        // 16
        assertEquals("1000001101", capturePrintedBinary(525));  // 525
    }

    @Test
    public void testPrintBinaryLargerPowersOfTwo() {
        assertEquals("11111111", capturePrintedBinary(255));      // 255
        assertEquals("100000000", capturePrintedBinary(256));     // 256
        assertEquals("1111111111", capturePrintedBinary(1023));   // 1023
        assertEquals("10000000000", capturePrintedBinary(1024));  // 1024
    }

    @Test
    public void testPrintBinaryMatchesJavaForRange() {
        // Systematically check a range of values against Java's built-in conversion
        for (int n = 1; n <= 200; n++) {
            String expected = Integer.toBinaryString(n);
            String actual = capturePrintedBinary(n);
            assertEquals(expected, actual, "Mismatch at n = " + n);
        }
    }

    // -------------------------------------------------------------
    // Tests for dailyTemperatures (deque-based)
    // -------------------------------------------------------------

    @Test
    public void testDailyTemperaturesCase1() {
        int[] temps = { 60, 30, 40, 50, 70 };
        int[] expected = { 4, 1, 1, 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesCase2() {
        int[] temps = { 73, 74, 75, 71, 69, 72, 76, 73 };
        int[] expected = { 1, 1, 4, 2, 1, 1, 0, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesIncreasing() {
        int[] temps = { 50, 55, 60, 65, 70 };
        int[] expected = { 1, 1, 1, 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesDecreasing() {
        int[] temps = { 80, 79, 78, 77 };
        int[] expected = { 0, 0, 0, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesAllEqual() {
        int[] temps = { 60, 60, 60, 60 };
        int[] expected = { 0, 0, 0, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesWarmSpike() {
        int[] temps = { 65, 60, 62, 61, 70 };
        int[] expected = { 4, 1, 2, 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesTwoDaysUp() {
        int[] temps = { 60, 65 };
        int[] expected = { 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesTwoDaysDown() {
        int[] temps = { 65, 60 };
        int[] expected = { 0, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesSingleDay() {
        int[] temps = { 70 };
        int[] expected = { 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesFarFutureWarmer() {
        int[] temps = { 60, 50, 40, 30, 80 };
        int[] expected = { 4, 3, 2, 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesUpDownPattern() {
        int[] temps = { 30, 60, 90, 60, 30, 40, 50 };
        // For each day:
        // 0: 30 -> next warmer is 60 at day 1 -> 1
        // 1: 60 -> next warmer is 90 at day 2 -> 1
        // 2: 90 -> none warmer -> 0
        // 3: 60 -> none warmer (later temps are 30,40,50) -> 0
        // 4: 30 -> next warmer is 40 at day 5 -> 1
        // 5: 40 -> next warmer is 50 at day 6 -> 1
        // 6: 50 -> none -> 0
        int[] expected = { 1, 1, 0, 0, 1, 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesRepeatedSmallUps() {
        int[] temps = { 50, 51, 50, 52, 51, 53 };
        // 0: 50 -> 51 at 1 -> 1
        // 1: 51 -> 52 at 3 -> 2
        // 2: 50 -> 52 at 3 -> 1
        // 3: 52 -> 53 at 5 -> 2
        // 4: 51 -> 53 at 5 -> 1
        // 5: 53 -> none -> 0
        int[] expected = { 1, 2, 1, 2, 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }

    @Test
    public void testDailyTemperaturesLongerMixed() {
        int[] temps = { 70, 71, 70, 72, 69, 75, 74, 80, 60, 90 };
        // Manually computed:
        // 0: 70 -> 71 (1) -> 1
        // 1: 71 -> 72 (3) -> 2
        // 2: 70 -> 72 (3) -> 1
        // 3: 72 -> 75 (5) -> 2
        // 4: 69 -> 75 (5) -> 1
        // 5: 75 -> 80 (7) -> 2
        // 6: 74 -> 80 (7) -> 1
        // 7: 80 -> 90 (9) -> 2
        // 8: 60 -> 90 (9) -> 1
        // 9: 90 -> none -> 0
        int[] expected = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 0 };
        assertArrayEquals(expected, Lab14.dailyTemperatures(temps));
    }
}
