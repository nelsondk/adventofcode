public class DayThree {

    private static int input = 361527;
    
    private static int RIGHT = 0;
    private static int UP = 1;
    private static int LEFT = 2;
    private static int DOWN = 3;
    
    private static int xLoc, yLoc; // The position of the unit compared to a 0,0 origin

    public static void main(String[] args) {
        System.out.println("Final Answer: " + getAnswer());
    }
    
    private static int getAnswer() {
        int direction = RIGHT;  // Start by going right
        int stepLimit = 1;  // How many steps before changing direction
        int currentStep = 0;  // How many steps we've taken at the moment in any given direction
        boolean increaseStepLimit = false; // when true, increase the stepLimit by 1
        
        int i = 1;
        while (i < input) {
            i++;  // Update the number we are operating on
            currentStep++; // Update how many steps we've taken in this direction
            
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
        
        // Now add the absolute values of the x/y positions
        System.out.println("xLoc: " + xLoc);
        System.out.println("yLoc: " + yLoc);
        
        return Math.abs(xLoc) + Math.abs(yLoc);
    }

    
    
}