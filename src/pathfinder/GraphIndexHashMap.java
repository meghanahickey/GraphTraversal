package pathfinder;

public class GraphIndexHashMap {
	private int length = 100;
	private Entry[] array = new Entry[length];
	private int numEntries = 0;
	
	public void set(GraphNode key, int value){
		
		Entry curr = new Entry(key, value);
		int index = hash(key.getId(), length);
		int i = 0;
		while (array[(int) ((index + Math.pow(i, 2)) % length)] != null){
			
			//If the key already exists in the hashmap, replace the value
			if (array[(int) ((index + Math.pow(i, 2)) % length)].key == key){
				
				//since I add one every time a new value is added to the hashmap,
				//need to subtract 1 when the value is associated to a key that's already in the Map
				numEntries--;
				break;
			}
			
			else{
				i++;
				if (i == length){
					rehash();
					i = 0;
				}
			}
		}
		array[(int) ((index + Math.pow(i, 2)) % length)] = curr;
		numEntries++;
		
		if ((double)numEntries/(double)length > 0.5){
			rehash();
		}
		
	}
	
	public int getValue(GraphNode g){
		int index = hash(g.getId(), length);
		int i = 0;
		while (i < length){
			int arr_index = (int)((index + Math.pow(i, 2))%length);
			if (array[arr_index] != null && array[arr_index].key.getId().equals(g.getId())){
				break;
			}
			else{
				i++;
			}
		}
		if (i == length){
			return -1;
		}
		return array[(int)((index + Math.pow(i, 2))%length)].value;
	}
	
	private int hash(String s, int currLength){
		
		long hashValue = 0;
		for (int i = 0; i < s.length(); i++){
			hashValue = hashValue * 31 + (int) s.charAt(i);
		}
		return Math.abs((int) hashValue%currLength);
	}
	
	public boolean hasKey(GraphNode g){
		if (getValue(g) == -1){
			return false;
		}
		return true;
		
	}
	
	private void rehash(){

		int newLength = length * 2;
		Entry[] newArray = new Entry[newLength];
		Entry currEntry;
		int newHash;
		
		for (int i = 0; i < length; i++){
			if (array[i] != null){
				currEntry = array[i];
				newHash = hash(currEntry.key.getId(), newLength);
				int j = 0;
				while (newArray[(int) ((newHash + Math.pow(j, 2)) % newLength)] != null){
						j++;
				}
				newArray[(int) ((newHash + Math.pow(j, 2)) % newLength)] = currEntry;
			}
		}
		
		array = newArray;
		length = newLength;
		
	}
	
	public String arrayToString(){
		//this method is mostly used in debugging
		String output = "";
		for (int i = 0; i < length; i++){
			output = output + i + " " + array[i] + "\n";
		}
		return output;
	}
}
