import java.util.*;
import java.io.*;

public class DayEight {
    private static Map<String, Integer> registers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        List<String> input = getInput();

        for (String command : input) {
            String id = command.substring(0, command.indexOf(" ")).trim();
            if (!registers.containsKey(id)) {
                registers.put(id, 0);
            }

            String condition = command.substring(command.indexOf("if ") + 3).trim();
            if(!shouldModify(condition)) {
                continue;
            }

            String operation = command.substring (command.indexOf(" "), command.indexOf(" if")).trim();
            performOperation(id, operation);
        }
        System.out.println("M: " + registers);
        printAnswer();
    }

    private static void printAnswer() {
        int largestVal = registers.get(registers.keySet().toArray()[0]);
        for (int val : registers.values()) {
            if (val > largestVal) {
                largestVal = val;
            }
        }
        System.out.println("FINAL ANSWER: " + largestVal);
    }

    private static boolean shouldModify(String condition) throws Exception {
        String conditionKey = condition.substring(0, condition.indexOf(" ")).trim();
        if (!registers.containsKey(conditionKey)) {
            registers.put(conditionKey, 0);
        }
        String operator = condition.substring(condition.indexOf(" "), condition.lastIndexOf(" ")).trim();
        int conditionValue = Integer.parseInt(condition.substring(condition.lastIndexOf(" ")).trim());

        switch(operator) {
        case ">":
            return registers.get(conditionKey) > conditionValue;
        case "<":
            return registers.get(conditionKey) < conditionValue;
        case "==":
            return registers.get(conditionKey) == conditionValue;
        case "<=":
            return registers.get(conditionKey) <= conditionValue;
        case ">=":
            return registers.get(conditionKey) >= conditionValue;
        case "!=":
            return registers.get(conditionKey) != conditionValue;
        default:
            System.out.println("Unknown operator: " + operator);
            throw new Exception();
        }
    }

    private static void performOperation(String id, String operation) {
        int sign = 1;
        if (operation.substring(0, operation.indexOf(" ")).equals("dec")) {
            sign = -1;
        }

        int modValue = Integer.parseInt(operation.substring(operation.indexOf(" ")).trim());
        int newValue = registers.get(id) + (sign * modValue);
        registers.put(id, newValue);
    }

    private static List<String> getInput() {
        String line = null;
        List<String> maze = new ArrayList<>();

        //try (BufferedReader bufferedReader = new BufferedReader(new FileReader("testInput.txt"))) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                maze.add(line);
            }
        } catch (IOException e) {
            System.out.println("uh oh");
        }
        return maze;
    }
}
