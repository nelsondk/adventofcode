import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.util.stream.*;
import java.util.OptionalInt;
import java.util.IntSummaryStatistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayTwo {
  public static void main(String[] args) {
    int answer = getAnswer(getInput());
    System.out.println("Answer: " + answer);
  }
  
  private static List<String> getInput() {
    String line = null;
    List<String> records = new ArrayList<>();
    
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
        while ((line = bufferedReader.readLine()) != null) {
          records.add(line);
        }
    } catch (IOException e) {
      System.out.println("uh oh");
    }
    
    return records;
  }
  
  private static int getAnswer(List<String> input) {
    int answer = 0;
    for (String line : input) {
      int[] arr = Stream.of(line.split("\t")).mapToInt(Integer::parseInt).toArray();
      out: for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
          if (i != j && (arr[i] % arr[j] == 0)) {
            System.out.println("Index(" + i + ", " + j + ")  I: " + arr[i] + " --> J: " + arr[j] + " = " + (arr[i] / arr[j]));
            answer += arr[i]/arr[j];
            continue out;
          }
        }
      }
    }
    
    return answer;
  }
}

