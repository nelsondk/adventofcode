import java.awt.Point;
import java.util.*;

public class DayThree {

    private static int input = 361527;
    
    private static int RIGHT = 0;
    private static int UP = 1;
    private static int LEFT = 2;
    private static int DOWN = 3;
    
    private static int xLoc, yLoc; // The position of the unit compared to a 0,0 origin

    private static Map<Point, Integer> valueMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        System.out.println("Final Answer: " + getAnswer());
    }
    
    private static int getAnswer() {
        int direction = RIGHT;  // Start by going right
        int stepLimit = 1;  // How many steps before changing direction
        int currentStep = 0;  // How many steps we've taken at the moment in any given direction
        boolean increaseStepLimit = false; // when true, increase the stepLimit by 1
        
        int value = 1;

        valueMap.put(new Point(0,0), value);  // Starting value
       
        int i = 0; 
        while (value < input) {
            // Now update our distance from center horiztonally or vertically
            if (direction == RIGHT) {
              // increase horizonal distance from center
              xLoc++;
            } else if (direction == LEFT) {
              // decrease horizontal distance from center
              xLoc--;
            } else if (direction == UP) {
              // increase vertical distance from center
              yLoc++;
            } else {
              // decrease vertical distance from center
              yLoc--;
            }

            value = getNewValue(xLoc, yLoc);
            valueMap.put(new Point(xLoc, yLoc), value);
            currentStep++; // Update how many steps we've taken in this direction

            
            // If this new step causes us to hit our limit, do a whole host of things
            if (currentStep == stepLimit) {
                // First we need to update our direction
                if (direction < 3) {
                  direction ++;
                } else {
                  direction = RIGHT;
                }
                currentStep = 0;  // Reset the step count for the new direction
                
                // Next check if we need to increase the step count size
                if (!increaseStepLimit) {
                    increaseStepLimit = true;  // We will need to do it next time...
                } else {
                    stepLimit++;
                    increaseStepLimit = false;
                }
            }
        }
        
        return value;
    }

    public static int getNewValue(int x, int y) {
        int retVal = 0;
        
        Point topLeftPoint = new Point(x-1, y+1);
        Point topPoint = new Point(x, y+1);
        Point topRightPoint = new Point(x+1, y+1);
        Point leftPoint = new Point(x-1, y);
        Point rightPoint = new Point(x+1, y);
        Point bottomLeftPoint = new Point(x-1, y-1);
        Point bottomPoint = new Point (x, y-1);
        Point bottomRightPoint = new Point(x+1, y-1);

        // Check top-left
        if (valueMap.containsKey(topLeftPoint)) {
            retVal += valueMap.get(topLeftPoint);
        }
        // Check top
        if (valueMap.containsKey(topPoint)) {
            retVal += valueMap.get(topPoint);
        }
        // Check top-right
        if (valueMap.containsKey(topRightPoint)) {
            retVal += valueMap.get(topRightPoint);
        }
        // Check left
        if (valueMap.containsKey(leftPoint)) {
            retVal += valueMap.get(leftPoint);
        }
        // Check right
        if (valueMap.containsKey(rightPoint)) {
            retVal += valueMap.get(rightPoint);
        }
        // Check bottom-left
        if (valueMap.containsKey(bottomLeftPoint)) {
            retVal += valueMap.get(bottomLeftPoint);
        }
        // Check bottom
        if (valueMap.containsKey(bottomPoint)) {
            retVal += valueMap.get(bottomPoint);
        }
        // Check bottom-right
        if (valueMap.containsKey(bottomRightPoint)) {
            retVal += valueMap.get(bottomRightPoint);
        }

        return retVal;
    }
}
