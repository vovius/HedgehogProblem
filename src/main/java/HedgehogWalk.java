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

    public HedgehogWalk(int[][] apples) {
        this.apples = apples;
    }

    public int walk() {
        initForWalk();
        fillWalkField();
        return walkField[walkField.length-1][walkField[0].length-1];
    }

    private void initForWalk() {
        walkField = new int[apples.length][apples[0].length];
    }

    private void fillWalkField() {
        walkField[0][0] = apples[0][0];
        for (int i=0; i < apples.length; i++)
            for(int j=0; j < apples[i].length; j++) {
                if (i+1 <= apples.length-1)
                    fillWalkFieldCell(i, j, i+1, j);
                if (j+1 <= apples[i].length-1)
                    fillWalkFieldCell(i, j, i, j+1);
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
        int[][] array = HedgehogWalkHelper.createArrayWithRandomData(4,4);
        HedgehogWalkHelper.printArray(array, "Before:");

        HedgehogWalk hedgehogWalk = new HedgehogWalk(array);
        hedgehogWalk.walk();

        boolean[][] walkFootprints = HedgehogWalkHelper.fillFootprints(hedgehogWalk.getWalkField());
        HedgehogWalkHelper.printArrayWithFootprints(hedgehogWalk.getWalkField(), "After:", walkFootprints);

    }
}
