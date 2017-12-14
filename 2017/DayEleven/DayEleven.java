import java.util.*;
import java.io.*;

public class DayEleven {
    private static int x,y,z;
    private static int maxDistance = 0;

    public static void main(String[] args) {
        String[] input = getInput().split(",");
        for (String dir : input) {
            switch(dir) {
            case "nw": 
                x-=1;
                y+=1;
                calcDistance();
                break;
            case "n":
                y+=1;
                z-=1;
                calcDistance();
                break;
            case "ne":
                x+=1;
                z-=1;
                calcDistance();
                break;
            case "se":
                x+=1;
                y-=1;
                calcDistance();
                break;
            case "s":
                y-=1;
                z+=1;
                calcDistance();
                break;
            case "sw":
                x-=1;
                z+=1;
                calcDistance();
                break;
            }
        }
        System.out.println("X: " + x + " -- Y: " + y + " -- Z: " + z);
        System.out.println((Math.abs(x) + Math.abs(y) + Math.abs(z))/2);
        System.out.println(maxDistance);
    }

    private static void calcDistance() {
        int dist = (Math.abs(x) + Math.abs(y) + Math.abs(z))/2;
        maxDistance = Math.max(maxDistance, dist);
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
