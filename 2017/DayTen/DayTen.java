import java.util.*;
import java.io.*;
import java.util.stream.*;

public class DayTen {
    private final static int LIST_SIZE = 256;

	public static void main(String[] args) {
        char[] input = getInput().toCharArray();
        
        List<Integer> lengthList = new ArrayList<>();
        for (char c : input) {
            lengthList.add((int) c);
        }
        lengthList.addAll(new ArrayList<Integer>(Arrays.asList(17, 31, 73, 47, 23)));
        System.out.println("LENGTHS: ");
        System.out.println(lengthList);
        System.out.println("");
        Integer[] lengths = lengthList.toArray(new Integer[0]);
        


        Integer[] list = initList(LIST_SIZE);
        
        int index = 0;
        int skipSize = 0;
        int round = 0;
        while (round < 64) {
            for (int length : lengths) {
                if (skipSize == LIST_SIZE) {
                    skipSize = 0;
                }

                Integer[] subList = null;
                if ((index + length) > list.length) {
                    // Handle the more complicated wrapping case
                    // Yes, I'm sure I can combine these, but I'm feeling lazy. It'll probably bite me in part 2
                    int sizeToEnd = list.length - index;
                    int sizeFromBegin = length - sizeToEnd;
                    List<Integer> tmpList = new ArrayList<>();
                    tmpList.addAll(Arrays.asList(Arrays.copyOfRange(list, index, index + sizeToEnd)));
                    tmpList.addAll(Arrays.asList(Arrays.copyOfRange(list, 0, sizeFromBegin)));
                    subList = reverse(tmpList.toArray(new Integer[0]));
                    
                    for (int i = 0; i < sizeToEnd; i++) {
                        list[index + i] = subList[i];
                    }
                    for (int i = 0; i < sizeFromBegin; i++) {
                        list[0 + i] = subList[sizeToEnd + i];
                    }
                } else {
                    // Handle the simple case where we don't wrap
                    subList = Arrays.copyOfRange(list, index, index + length);
                    subList = reverse(subList);
                    for (int i = 0; i < subList.length; i++) {
                        list[index + i] = subList[i];
                    }
                }
                
                index += length + skipSize;
                while (index > list.length) {
                    index -= list.length;
                }
                skipSize++;
            }
            round++;
        }
        System.out.println("FINAL LIST: ");
        System.out.println(Arrays.asList(list));
        System.out.println("");
    
        // Create dense hash
        List<Integer> denseHash = new ArrayList<>();
        for (int block = 0; block < 16; block++) {
            int num = list[16 * block];
            for (int i = 1; i < 16; i++) {
               num = num ^ list[i + (16 * block)]; 
            }
            denseHash.add(num);
        }
        System.out.println("DENSE HASH: " + denseHash);
        System.out.println("");

        // Create hex string
        String finalHash = "";
        for (int i : denseHash) {
            finalHash = finalHash.concat(Integer.toHexString(i));
        }
        System.out.println("FINAL HASH: " + finalHash);
    }
    
    private static Integer[] reverse(Integer[] subList) {
        Integer[] retVal = new Integer[subList.length];
        for (int i = (subList.length - 1), j = 0; i > -1; i--, j++) {
            retVal[j] = subList[i];
        }
        return retVal;
    }
    
    private static Integer[] initList(int size) {
        Integer[] list = new Integer[size];
        for (int i = 0; i < size; i++) {
            list[i] = i;
        }
        
        return list;
    }

    private static String getInput() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            return bufferedReader.readLine();
	} catch (IOException e) {
	    System.out.println("uh oh");
	}
	return "";
    }
}
