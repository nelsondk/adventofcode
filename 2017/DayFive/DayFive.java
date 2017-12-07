import java.io.*;
import java.util.*;

public class DayFive {
    
    public static void main(String[] args) {
        List<Integer> maze = getInput();
        int index = 0;
        int numSteps = 0;
        try {
            while (true) {
//                System.out.println("Getting from index(" + index + ")");
                int tmp = maze.get(index);
//                System.out.println("Got value: " + tmp);
//                System.out.println("Replacing index(" + index + ") with value" + (tmp + 1));
                if (tmp >= 3) {
                    maze.set(index, tmp - 1);
                } else {
                    maze.set(index, tmp + 1);
                }
//                System.out.println("Next index is: " + (index + tmp));
                index += tmp;

//                System.out.println("LIST: " + maze);
//                System.out.println("");
                numSteps++;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + numSteps);
        }
    }

    private static List<Integer> getInput() {
        String line = null;
        List<Integer> maze = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                maze.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("uh oh");
        }
        return maze;
    }
}
