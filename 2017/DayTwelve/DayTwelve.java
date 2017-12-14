import java.util.*;
import java.io.*;

public class DayTwelve {
    private static Map<Integer, Set<Integer>> groupMap = new HashMap<>();

    public static void main(String[] args) {
        List<String> input = getInput();
       
        for (int i = 0; i < 2; i++) { 
            for (String line : input) {
                int key = getKey(line);
                Set<Integer> valueSet = getValues(line);

                Set<Integer> mapValue = getMapValue(valueSet, new HashSet<Integer>());
                groupMap.put(key, mapValue);

                //Set<Integer> newSet = getValues(values);
            }
        }
        
        int groupsWithZero = 0;
        for (Set<Integer> valueSet : groupMap.values()) {
            if (valueSet.contains(0)) {
                groupsWithZero++;
            }
        }
        System.out.println("FINAL ANSWER: " + groupsWithZero);
    }

    private static Set<Integer> getMapValue(Set<Integer> values, Set<Integer> mapValue) {
        if (values == null) {
            return mapValue;
        }
        for (int value : values) {
            if (!mapValue.add(value)) {
                continue; 
            };
            mapValue.addAll(getMapValue(groupMap.get(value), mapValue));
        }
        return mapValue;
    }

    private static int getKey(String line) {
        return Integer.parseInt(line.substring(0, line.indexOf("<")).trim());
    }

    private static Set<Integer> getValues(String line) {
        Set<Integer> values = new HashSet<>();
        String[] stringValues = line.substring(line.indexOf(">") + 1).trim().split(", ");
        for (String value : stringValues) {
            values.add(Integer.parseInt(value));
        }
        return values;
    }

    private static List<String> getInput() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) { 
            List<String> retVal = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                retVal.add(line);
            }

            return retVal;
        } catch (IOException e) {
            System.out.println("uh oh");
        }
        return new ArrayList<String>();
    }
}
