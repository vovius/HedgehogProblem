import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by sony on 10/10/2016.
 */
public class HedgehogWalkTest {

    @Test
    public void testEmptyArray() {
        Random rand = new Random();
        HedgehogWalk hedgehogWalk = new HedgehogWalk(new int[rand.nextInt(10)][rand.nextInt(10)]);
        int result = hedgehogWalk.walk();
        assertEquals(0, result);
    }

    @Test
    public void testConstantArray() {
        int constVal = 1;
        Random rand = new Random();
        int[][] array = new int[rand.nextInt(10)+1][rand.nextInt(10)+1];
        for (int i=0; i < array.length; i++)
            for (int j=0; j < array[i].length; j++)
                array[i][j] = constVal;
        HedgehogWalk hedgehogWalk = new HedgehogWalk(array);
        int result = hedgehogWalk.walk();
        assertEquals(constVal * (array.length + array[0].length - 1), result);
    }

    @Test
    public void testZeroLengthArray() {
        int[][] array = new int[0][0];
        HedgehogWalk hedgehogWalk = new HedgehogWalk(array);
        int result = hedgehogWalk.walk();
        assertEquals(0, result);
    }

    @Test
    public void compareWithFullWalk() {
        int[][] array = new int[4][4];
        Random rand = new Random();
        for (int i=0; i < array.length; i++)
            for (int j=0; j < array[i].length; j++)
                array[i][j] = rand.nextInt(100);

        HedgehogWalk hedgehogWalk = new HedgehogWalk(array);
        int resultWalk = hedgehogWalk.walk();
        int resultFullWalk = fullWalk(array);

        assertEquals(resultFullWalk, resultWalk);
    }

    private int fullWalk(int[][] array) {
        return goFullWalk(array, 0, 0, 0, 0);
    }

    private int goFullWalk(int[][] array, int i, int j, int curSum, int curMax) {
        if (i+1 < array.length)
            curMax = Math.max(curMax, goFullWalk(array, i+1, j, curSum+array[i][j], curMax));

        if (j+1 < array[i].length)
            curMax = Math.max(curMax, goFullWalk(array, i, j+1, curSum+array[i][j], curMax));

        if (i == array.length-1 && j == array[i].length-1)
            curMax = Math.max(curMax, curSum+array[i][j]);

        return curMax;
    }
}
