import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static int[][] createArrayFromFile(String fileName) {
        int[][] array = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            if (line != null) {
                // first line should contain the sizes of the matrix
                String[] fieldSize = line.split(" ");
                if (fieldSize != null && fieldSize.length == 2)
                    array = new int[Integer.valueOf(fieldSize[0])][Integer.valueOf(fieldSize[1])];
            }

            if (array != null) {
                int i=0;
                while ((line = br.readLine()) != null) {
                    String[] cells = line.trim().split(" ");
                    if (cells != null) {
                        for (int j = 0; j < cells.length; j++) {
                            array[i][j] = Integer.valueOf(cells[j]);
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Array loading from file has failed!");
            e.printStackTrace();
        }

        return array;
    }

    public static void writeResultOfWalking(int[][] array, String fileName) {
        int result = array[array.length-1][array[0].length-1];
        try (PrintWriter writer = new PrintWriter((fileName))) {
            writer.print(result);
        } catch (IOException e) {
            System.err.println("Failed to write the result to file!");
            e.printStackTrace();
        }
    }

}
