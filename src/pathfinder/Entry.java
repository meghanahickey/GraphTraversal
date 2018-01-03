package pathfinder;

public class Entry {
	public GraphNode key;
	public int value;
	
	public Entry(GraphNode keyIn, int valueIn){
		key = keyIn;
		value = valueIn;
	}
	
	public void changeValue(int newValue){
		value = newValue;
	}
	
}
