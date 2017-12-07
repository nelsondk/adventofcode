import java.util.*;

public class DaySix {

    private static Integer[] banks = { 14, 0, 15, 12, 11, 11, 3, 5, 1, 6, 8, 4, 9, 1, 8, 4 };
    private static List<String> seenList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(Arrays.asList(banks));

        int inc = 0;
        while (!seenList.contains(createStringValue())) {
            seenList.add(createStringValue());
            redistMemBanks();
            System.out.println(Arrays.asList(banks));
            inc++;
        }

        int firstIndex = seenList.indexOf(createStringValue());
        System.out.println("First Index: " + firstIndex);
        System.out.println("Final answer: " + inc);
        
        System.out.println("Loop Size: " + (inc - firstIndex));
    }

    private static void redistMemBanks() {
        int highestIndex = 0;
        for (int i = 1; i < banks.length; i++) {
            if (banks[i] > banks[highestIndex]) {
                highestIndex = i;
            }
        }
        int numBlocks = banks[highestIndex];
        
        banks[highestIndex] = 0;

        int distr = numBlocks / 16;
        int remainder = numBlocks % 16;
        // If evenly divisable, the same number to everything
        for (int i = 0; i < banks.length; i++) {
            banks[i] = banks[i] + distr;
        }

        // If there is a remaind, add 1 more to following indexes as possible
        for (int i = 1; i < remainder + 1; i++) {
            int nextIndex = highestIndex + i;
            if (nextIndex > (banks.length - 1)) {
                nextIndex = (nextIndex - banks.length);
            }
            banks[nextIndex] = banks[nextIndex] + 1;
        }

    }

    private static String createStringValue() {
        String retVal = "";
        for (int i : banks) {
            retVal += (":" + i);
        }
        return retVal;
    }
}
