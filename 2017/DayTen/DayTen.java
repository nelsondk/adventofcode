import java.util.*;
import java.io.*;
import java.util.stream.*;

public class DayTen {
    private final static int LIST_SIZE = 256;

	public static void main(String[] args) {
        Integer[] lengths = getInput();
        Integer[] list = initList(LIST_SIZE);
        
        int index = 0;
        int skipSize = 0;
        for (int length : lengths) {
            System.out.println("Before: " + Arrays.asList(list));
        
        
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
            
            System.out.println("After: " + Arrays.asList(list));
            System.out.println("");
            
            index += length + skipSize;
            skipSize++;
            if (index > list.length) {
                index -= list.length;
            }
        }
        
        System.out.println("Answer: " + (list[0] * list[1]));
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

	private static Integer[] getInput() {
	    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            String line = bufferedReader.readLine();
            int[] intArr = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            Integer[] input = new Integer[intArr.length];
            for (int i = 0; i < intArr.length; i++) {
                input[i] = Integer.valueOf(intArr[i]);
            }
            return input;
		} catch (IOException e) {
			System.out.println("uh oh");
		}
		return new Integer[0];
	}
}
