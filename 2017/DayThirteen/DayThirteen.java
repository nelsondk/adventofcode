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
		for (int mapIndex = 0; mapIndex < size; mapIndex++) {
			layerMap.put(mapIndex, null);
		}

		// init layer objs & add to map
		layers = new ArrayList<Layer>();
		for (String i : input) {
			Layer layer = new Layer(i);
			layers.add(layer);
			layerMap.put(layer.getDepth(), layer);
		}
		
		System.out.println("Required Delay: " + getMinDelay(size));
	}
	
	private static int getMinDelay(int numLayers) {
		int delay = 0;

		out: while (true) {
			// Reset player location
			int playerLocation = 0;
			
			// Makes a deep copy of the layers list, with each layer's scanner progressed to the next step
			List<Layer> layersCopy = copyLayers();
				
			while (playerLocation < numLayers) {
				Layer currentLayer = layerMap.get(playerLocation);
				if (currentLayer != null && currentLayer.getScannerLoc() == 0) {
					delay++;
					layers = layersCopy;
					for (Layer layer : layers) {
						layerMap.put(layer.getDepth(), layer);
					}
					continue out;
				}
				for (Layer layer : layers) {
					layer.progressScanner();
				}
				playerLocation++;
			}
			break;
		}
		
		return delay;
	}
	
	private static List<Layer> copyLayers() {
		List<Layer> copy = new ArrayList<Layer>();
		for (Layer layer : layers) {
			Layer layerCopy = new Layer(layer.getDepth(), layer.getRange(), layer.getScannerLoc(), layer.getDir());
			layerCopy.progressScanner();
			copy.add(layerCopy);
		}
		return copy;
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
		
		public Layer(int depth, int range, int scanLoc, int scanDir) {
			this.depth = depth;
			this.range = range;
			this.scanLoc = scanLoc;
			this.scanDir = scanDir;
		}
		
		public void progressScanner() {
			if (scanLoc == 0) {
				scanDir = 1;
			} else if (scanLoc == (range -1)) {
				scanDir = -1;
			}
			scanLoc += scanDir;
		}
		
		public int getSeverity() {
			return (this.depth * this.range);
		}
		
		public int getRange() {
			return this.range;
		}
		
		public int getDepth () {
			return this.depth;
		}
		
		public int getScannerLoc() {
			return this.scanLoc;
		}
		
		public void setScannerLoc(int newLoc) {
			this.scanLoc = newLoc;
		}
		
		public int getDir() {
			return this.scanDir;
		}
		
		public String toString() {
			return "Depth: " + depth + "  Range: " + range + "  ScannerLoc: " + scanLoc;
		}
	}
}