import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by sony on 10/8/2016.
 */
public class HedgehogWalkHelper {

    public static void printArray(int[][] array, String header) {
        System.out.println(header);
        for (int i=0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }

    public static void printArrayWithFootprints(int[][] array, String header, boolean[][] footprints) {
        System.out.println(header);
        for (int i=0; i < array.length; i++) {
            System.out.print("[");
            for (int j = 0; j < array[i].length; j++) {
                if (footprints[i][j])
                    System.out.print("(" + array[i][j] + ")");
                else
                    System.out.print(array[i][j]);
                if (j < array[i].length-1)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    public static void randomizeArray(int[][] array) {
        Random random = new Random();
        for (int i=0; i < array.length; i++)
            for (int j=0; j < array[i].length; j++)
                array[i][j] = random.nextInt(9);
    }

    public static int[][] createArrayWithRandomData(int sizeI, int sizeJ) {
        int[][] array = new int[sizeI][sizeJ];
        randomizeArray(array);
        return array;
    }

    public static boolean[][] fillFootprints(int[][] walkField) {
        boolean[][] walkFootprints = new boolean[walkField.length][walkField[0].length];
        walkAndFillFootprints(walkField, walkFootprints, 0, 0);
        return walkFootprints;
    }

    private static void walkAndFillFootprints(int[][] walkField, boolean[][] footprints, int i, int j) {
        FieldCell currentCell = new FieldCell(i, j, walkField[i][j]);
        footprints[i][j] = true;

        FieldCell rightCell = i+1 < walkField.length-1 ? new FieldCell(i+1, j, walkField[i+1][j]) : null;
        FieldCell bottomCell = j+1 < walkField[i].length-1 ? new FieldCell(i, j+1, walkField[i][j+1]) : null;

        FieldCell maxCell = FieldCell.max(rightCell, bottomCell);
        if (maxCell != null) {
            walkAndFillFootprints(walkField, footprints, maxCell.getI(), maxCell.getJ());
        }
    }

    private static class FieldCell {
        int i;
        int j;
        int value;

        public FieldCell(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        public static FieldCell max(FieldCell o1, FieldCell o2) {
            if (o1 == null && o2 == null)
                return null;
            else if (o1 != null && o2 == null)
                return o1;
            else if (o1 == null && o2 != null)
                return o2;
            else if (o1.value < o2.value)
                return o2;
            else
                return o1;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }
}
