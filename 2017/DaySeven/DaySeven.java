import java.util.*;
import java.io.*;

public class DaySeven {
    private static Map<String, TreeNode> nodeMap = new HashMap<>();
    
    public static void main(String[] args) throws Exception{
        List<String> input = getInput();
        createTreeNodes(input);
        assignParents();
        String rootNodeName = getRootNodeName();
        System.out.println(getStackWeight(nodeMap.get(rootNodeName)));
    }

    /** 
        Creates individual TreeNodes that have values for:
        Name, Weight, Children, and Parent (the last of which will be set in the next step)
    */
    private static void createTreeNodes(List<String> input) {
        for (String item : input) {
            String name = (item.substring(0, item.indexOf('(')).trim());
            Integer weight = Integer.parseInt(item.substring(item.indexOf('(')+1, item.indexOf(')')));
            String[] children = null;
            if (item.contains(">")) {
                children = item.substring(item.indexOf('>')+1).trim().split(", ");
            }
            nodeMap.put(name, new TreeNode(name, weight, children));
        }
    }

    private static void assignParents() {
        for (TreeNode node : nodeMap.values()) {
            if (node.getChildren() != null) {
                for (String childName : node.getChildren()) {
                    nodeMap.get(childName).setParent(node.getName());
                }
            }
        }
    }

    private static String getRootNodeName() {
        for (TreeNode node : nodeMap.values()) {
            if (node.getParent() == null) {
                return node.getName();
            }
        }
        return "Root not found";
    }

    private static int getStackWeight(TreeNode node) throws Exception{
        if (node.getChildren() == null) {
            return node.getWeight();
        }
        
        int stackWeight = node.getWeight();

        List<TreeNode> childNodes = new ArrayList<>();
        for (String childName : node.getChildren()) {
            TreeNode childNode = nodeMap.get(childName);
            childNodes.add(childNode);
            childNode.setStackWeight(getStackWeight(childNode));
            stackWeight += childNode.getStackWeight();
        }

        // Check for inconsistencies
        int firstWeight = childNodes.get(0).getStackWeight();
        for (TreeNode childNode : childNodes) {
            if (childNode.getStackWeight() != firstWeight) {
                System.out.println("we've found our man");
                for (TreeNode child : childNodes) { 
                    System.out.println("Child: " + child);
                }
                // Anddd.. end the program
                throw new Exception();
            }
        }

        return stackWeight;
    }

    private static List<String> getInput() {
        String line = null;
        List<String> maze = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                maze.add(line);
            }
        } catch (IOException e) {
            System.out.println("uh oh");
        }
        return maze;
    }

    private static class TreeNode {
        String name, parent;
        int weight;
        int stackWeight;
        String[] children;

        public TreeNode(String name, int weight, String[] children) {
            this.name = name;
            this.weight = weight;
            this.children = children;
        }

        public String getName() {
            return this.name;
        }

        public int getWeight() {
            return this.weight;
        }

        public int getStackWeight() {
            return this.stackWeight;
        }

        public void setStackWeight(int stackWeight) {
            this.stackWeight = stackWeight;
        }

        public String[] getChildren() {
            return this.children;
        }

        public String getParent() {
            return this.parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String toString() {
            String childrenString = "";
            if (this.children == null) {
                childrenString = "NONE";
            } else {
                childrenString = Arrays.asList(this.children).toString();
            }
            return "Name: " + this.name + " - Parent: " + this.parent + " - Weight: " + this.weight + " - StackWeight: " + this.stackWeight + " - Children: " + childrenString;
        }
    }
}
