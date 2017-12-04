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
            String arr[] = line.split(" ");
            for (String element : arr) {
                if(!aSet.add(element)) {
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
