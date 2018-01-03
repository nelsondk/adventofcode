import java.io.*;
import java.util.*;

public class DayThirteen {
	private static Map<Integer, Layer> layerMap;
	private static List<Layer> layers;

	public static void main(String[] args) {
		String[] input = getInput();
		String finalLayer = input[input.length-1];
		int size = Integer.parseInt(finalLayer.substring(0, finalLayer.indexOf(':'))) + 1;
		
		// init map
		layerMap = new HashMap<Integer, Layer>();
		int mapIndex = 0;
		while (mapIndex < size) {
			layerMap.put(mapIndex, null);
			mapIndex++;
		}

		// init layer objs & add to map
		layers = new ArrayList<Layer>();
		for (String i : input) {
			Layer layer = new Layer(i);
			layers.add(layer);
			layerMap.put(layer.getDepth(), layer);
		}
		
		System.out.println("Severity: " + getTripSeverity(size));
	}
	
	private static int getTripSeverity(int numLayers) {
		int playerLocation = 0;
		int tripSeverity = 0;
		
		while (playerLocation < numLayers) {
			Layer currentLayer = layerMap.get(playerLocation);
			if (currentLayer != null && currentLayer.getScannerLoc() == 0) {
				tripSeverity += currentLayer.getSeverity();
			}
			for (Layer layer : layers) {
				layer.progressScanner();
			}
			playerLocation++;
		}
		
		return tripSeverity;
	}

	private static String[] getInput() {
		List<String> input = new ArrayList<String>();
	    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"))) {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				input.add(line);
			}
		} catch (IOException e) {
			System.out.println("uh oh");
		}
		return input.toArray(new String[0]);
	}
	
	
	// ** LAYER CLASS ** //
	private static class Layer {
		int depth;
		int range;
		
		int scanDir = 1;
		int scanLoc = 0;
		
		public Layer(String definition) {
			this.depth = Integer.parseInt(definition.substring(0, definition.indexOf(':')));
			this.range = Integer.parseInt(definition.substring(definition.indexOf(' ') + 1));
		}
		
		public void progressScanner() {
			scanLoc += scanDir;
			
			// Reverse the scanner at the top/bottom of the range
			if (scanLoc == 0 || scanLoc == (range-1)) {
				scanDir *= -1;
			}
		}
		
		public int getSeverity() {
			return (this.depth * this.range);
		}
		
		public int getDepth () {
			return this.depth;
		}
		
		public int getScannerLoc() {
			return this.scanLoc;
		}
		
		public String toString() {
			return "Depth: " + depth + "  Range: " + range + "  ScannerLoc: " + scanLoc;
		}
	}
}