package demo;

import java.util.*;

public class Lab14 {
    public static void printBinary(int n) {
       // you fill in here
    }

    // Test function for binary-number printing
    public static void testBinaryNumbers() {
        System.out.println("========== BINARY NUMBER TESTS ==========");
        int[] testNs = { 1, 4, 10, 525 };
        for (int n : testNs) {
            System.out.println("n = " + n);
            printBinary(n);
        }
    }

    public static int[] dailyTemperatures(int[] temps) {
        // you fill in here
        return temps; // replace with proper answer array
    }

    public static void testDailyTemps() {
        System.out.println("========== DAILY TEMPERATURE TESTS ==========");
        int[][] cases = {
                { 60, 30, 40, 50, 70 },
                { 73, 74, 75, 71, 69, 72, 76, 73 },
        };

        for (int i = 0; i < cases.length; i++) {
            int[] temps = cases[i];
            int[] ans = dailyTemperatures(temps);
            System.out.println("temps: " + Arrays.toString(temps));
            System.out.println("  ans: " + Arrays.toString(ans));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        testBinaryNumbers();
        testDailyTemps();
    }
}
