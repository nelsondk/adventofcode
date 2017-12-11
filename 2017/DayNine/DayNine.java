import java.util.*;
import java.io.*;

public class DayNine {

    public static void main(String[] args) {
        char[] input  = getInput().toCharArray();
        
        int score = 0;
        int groupVal = 0;
        int numDeleted = 0;


        boolean inGarbage = false;
        boolean cancelNext = false;
        for (char c : input) {
            System.out.print(c);
            if (inGarbage) {
                if (c == '!') {
                    cancelNext = !cancelNext;
                    continue;
                } else if (c == '>' && !cancelNext) { 
                    inGarbage = false;
                } else if (!cancelNext) {
                    numDeleted++;
                }
                cancelNext = false;
                continue;
            }

            if (c == '{') {
                groupVal++;
            } else if (c == '}') {
                score += groupVal;
                groupVal--;
            } else if (c == '<') {
                inGarbage = true;
            }
        }
        System.out.println("");
        System.out.println("SCORE: " + score);
        System.out.println("NUM DELTED: " + numDeleted);
    }

    private static String getInput() {
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            System.out.println("uh oh");
        }
        return "";
    }

}
