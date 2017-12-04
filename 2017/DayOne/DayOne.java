import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.IntStream;


public class DayOne {

  public static void main(String[] args) {
    int[] input = getInput();

    int[] testInput = {1, 2, 1, 3, 1, 4, 1, 5};
    //input = testInput;

    int sum = solvePuzzle(input);
    System.out.println("Sum: " + sum);
  }

  private static int solvePuzzle(int[] input) {
    int stepSize = input.length/2;
    List<Integer> matchedInts = new ArrayList<>();
    for (int i = 0; i < input.length; i++) {
      // Account for circular list
      int secondIndex = i + stepSize;
      if (secondIndex > (input.length - 1)) {
        int circledIndex = stepSize - (input.length - i);
        secondIndex = circledIndex;
      }

      if (input[i] == input[secondIndex]) {
        matchedInts.add(input[i]);
      }
    }
    System.out.println("TEST: " + matchedInts); 
    return matchedInts.stream().mapToInt(Integer::intValue).sum();
  }

  /** Get a long String and convert to an int array */
  private static int[] getInput() {
    try(Stream<String> stream = Files.lines(Paths.get("input.txt"))) {
      String strInput = (String) stream.toArray()[0];
      String[] tmpAry = strInput.split("");
      int[] input = new int[tmpAry.length];
      for (int i = 0; i < tmpAry.length; i++) {
        input[i] = Integer.parseInt(tmpAry[i]);
      }
      return input;
    } catch (IOException e) {
      System.out.println("BROKEN");
    }
    return new int[0];
  }
}
