import java.io.*;
import java.util.*;

public class DayFive {
    
    public static void main(String[] args) {
        List<Integer> maze = getInput();
        int index = 0;
        int numSteps = 0;
        try {
            while (true) {
                int tmp = maze.get(index);
                if (tmp >= 3) {
                    maze.set(index, tmp - 1);
                } else {
                    maze.set(index, tmp + 1);
                }
                index += tmp;
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
