import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;

public class DayOne {
  
  private static String testInput = "(()(()(";
  
  public static void main(String[] args) {
    String input = getInput();
    
    int floor= 0;
    for (int i = 0; i < input.length(); i++) {
      if ('(' == input.charAt(i)) {
        floor++;
      } else if (')' == input.charAt(i)) {
        floor--;
      }
      if (floor < 0) {
        System.out.println("Position for basement entry: " + (i + 1));
        break;
      }
    }

    System.out.println("Floor: " + floor);
  }

  private static String getInput() {
    try(Stream<String> stream = Files.lines(Paths.get("input.txt"))) {
      //stream.forEach(System.out::println);
      Object[] inputStream = stream.toArray();
      //System.out.println("Size: " + inputStream.length);
      //System.out.println("Type: " + inputStream[0].getClass());
      return (String) inputStream[0];
    } catch (IOException e) {
      System.out.println("BROKEN");
    }
    return testInput;
  }

}
