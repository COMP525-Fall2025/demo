package demo;

import java.util.Arrays;
import java.util.HashMap;

public class Lab5 {
    public static int[] twoSum(int[] nums, int target) {
        // you fill in here
    
        return new int[0];
    
    }

    private static void assertTwoSum(int[] nums, int target) {
        int[] ans = twoSum(Arrays.copyOf(nums, nums.length), target);

        assert ans != null && ans.length == 2 : "Expected two indices";
        int i = ans[0], j = ans[1];
        assert i != j : "Indices must be distinct";
        assert i >= 0 && i < nums.length && j >= 0 && j < nums.length : "Indices out of bounds";
        assert nums[i] + nums[j] == target : "Values do not sum to target";
    }

    public static void main(String[] args) {
        assertTwoSum(new int[] { 2, 7, 11, 15 }, 9); // 0+1
        assertTwoSum(new int[] { 3, 2, 4 }, 6); // 1+2
        assertTwoSum(new int[] { 3, 3 }, 6); // 0+1

        assertTwoSum(new int[] { 0, 4, 3, 0 }, 0); // uses two zeros
        assertTwoSum(new int[] { -4, -1, -9, 5, 10 }, 1); // negatives + positives
        assertTwoSum(new int[] { 1, 5, 3, 7 }, 10); // later match

        System.out.println("All asserts passed.");
    }

}

// MagicalCreatureDemo.java
// The class can not be public in this file since we already have one public
// class.
class MagicalCreatureDemo {

    // ----- Creature Class -----
    public static class Creature {
        // Private fields
        private String name;
        private int health;
        private int magicPower;

        // Constructor
        public Creature(String name, int health, int magicPower) {
            this.name = name;
            this.health = health;
            this.magicPower = magicPower;
        }

        // Public method: cast a spell on another creature
        public void castSpell(Creature target) {
            if (magicPower > 0) {
                int damage = calculateDamage(); // private method
                target.takeDamage(damage); // private method of target
                magicPower--; // spend magic
                System.out.println(name + " casts a spell on " + target.name + " for " + damage + " damage!");
            } else {
                System.out.println(name + " has no magic left!");
            }
        }

        // Public method: heal self
        public void heal() {
            int healAmount = 10;
            health += healAmount;
            System.out.println(name + " heals for " + healAmount + " health!");
        }

        // Public method: display creature stats
        public void displayStats() {
            System.out.println("Creature: " + name + " | Health: " + health + " | Magic: " + magicPower);
        }

        // Private method: calculate spell damage
        private int calculateDamage() {
            return (int) (Math.random() * 20) + 5; // random 5-24
        }

        // Private method: apply damage
        private void takeDamage(int damage) {
            health -= damage;
            if (health < 0)
                health = 0;
        }

        // Public method: check if creature is alive
        public boolean isAlive() {
            return health > 0;
        }
    }

    private static void testGame() {
        Creature dragon = new Creature("Draco the Dragon", 100, 5);
        Creature unicorn = new Creature("Sparkle the Unicorn", 80, 8);

        System.out.println("--- Initial Stats ---");
        dragon.displayStats();
        unicorn.displayStats();

        System.out.println("\n--- Battle Begins ---\n");

        // Using public methods - works fine
        dragon.castSpell(unicorn);
        unicorn.castSpell(dragon);

        dragon.heal();
        unicorn.heal();

        System.out.println("\n--- Final Stats ---\n");
        dragon.displayStats();
        unicorn.displayStats();

        System.out.println("\n--- Attempting to call private methods from main ---");

        // compile-time errors:
        // dragon.takeDamage(10); // Error: takeDamage() has private access
        // int dmg = unicorn.calculateDamage(); // Error: calculateDamage() has private
        // access
    }

    public static void main(String[] args) {
        testGame();
    }

}
