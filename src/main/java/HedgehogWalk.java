import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by sony on 10/4/2016.
 */
public class HedgehogWalk {
    private int[][] apples;
    private int[][] walkField;

    private final static String INPUT_FILE = System.getProperty("user.dir") + "\\input.txt";
    private final static String OUTPUT_FILE = System.getProperty("user.dir") + "\\output.txt";

    public HedgehogWalk(int[][] apples) {
        this.apples = apples;
    }

    public int walk() {
        if (initForWalk()) {
            fillWalkField();
            return walkField[walkField.length - 1][walkField[0].length - 1];
        }

        return 0;
    }

    private boolean initForWalk() {
        if (apples.length > 0 && apples[0].length > 0) {
            walkField = new int[apples.length][apples[0].length];
            return true;
        }

        return false;
    }

    private void fillWalkField() {
        if (apples.length > 0 && apples[0].length > 0) {
            walkField[0][0] = apples[0][0];
            for (int i = 0; i < apples.length; i++)
                for (int j = 0; j < apples[i].length; j++) {
                    if (i + 1 <= apples.length - 1)
                        fillWalkFieldCell(i, j, i + 1, j);
                    if (j + 1 <= apples[i].length - 1)
                        fillWalkFieldCell(i, j, i, j + 1);
                }
        }
    }

    private void fillWalkFieldCell(int fromI, int fromJ, int toI, int toJ) {
        int stepValue = walkField[fromI][fromJ] + apples[toI][toJ];
        if (walkField[toI][toJ] < stepValue) {
            walkField[toI][toJ] = stepValue;
        }
    }

    public int[][] getWalkField() {
        return walkField;
    }

    public static void main(String[] args) {
        //int[][] array = HedgehogWalkHelper.createArrayWithRandomData(4,4);
        int[][] array = HedgehogWalkHelper.createArrayFromFile(INPUT_FILE);
        if (array != null) {
            HedgehogWalkHelper.printArray(array, "Before:");

            HedgehogWalk hedgehogWalk = new HedgehogWalk(array);
            hedgehogWalk.walk();

            HedgehogWalkHelper.printArray(hedgehogWalk.getWalkField(), "After:");
            HedgehogWalkHelper.writeResultOfWalking(hedgehogWalk.getWalkField(), OUTPUT_FILE);
        }

    }
}
