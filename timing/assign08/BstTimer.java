package assign08;


import codeTimer.CodeTimer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * @author Tanner Holladay
 * @version March 16, 2020
 */
@CodeTimer.Settings(title = "Add Randomized", titleX = "Randomized", titleY = "Step (Items Added To Tree)", start = 1000)
public class BstTimer extends CodeTimer {

    static BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    static Random random = new Random();
    static ArrayList<Integer> numbers;

    public static void main(String[] args) {
        new BstTimer();
    }

    @Override
    public void before(int steps) {
    }

    /**
     * Code that is being timed
     *
     * @param steps Current steps
     */
    @Override
    public void timedCode(int steps) {
        tree = new BinarySearchTree<>();
        for (int i = 0; i < steps; i++) {
            tree.add(i);
        }
    }

    @Override
    public void extra(int steps) {
    }
}
