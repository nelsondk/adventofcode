import java.io.*;
import java.util.*;

public class DayFour {

    public static void main(String[] args) {
        System.out.println("Answer: " + getAnswer());
    }

    /** Get a long String and convert to an int array */
    private static int getAnswer() {
        int numPassPhrases = 0;
        try(BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            String line = null;

   out: while ((line = in.readLine()) != null) {
            Set<String> aSet = new HashSet<>();

            // Get a String array of all elements on this line
            String[] arr = line.split(" ");

            // For each element, conver to a character array, order, and then apply to the set
            for (String element : arr) {
                char[] charArr = element.toCharArray();
                Arrays.sort(charArr);
                if(!aSet.add(new String(charArr))) {
                    continue out;
                }
            }
            numPassPhrases++;
        }
    } catch (IOException e) {
        System.out.println("Couldn't read file");
    }

        return numPassPhrases;
    }
}
