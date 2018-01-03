package pathfinder;

public abstract class GraphMinHeap {
	private GraphNode[] nodes = new GraphNode[10000];
	public int size = 0;
	private GraphIndexHashMap hm = new GraphIndexHashMap();
	
	public GraphNode getMin(){
		
		if (size == 0){
			return null;
		}
		
		GraphNode min = nodes[0];
		hm.set(min, -1);
		nodes[0] = nodes[size-1];
		size--;
		
		heapifyDown(0);
		
		return min;
	}
	
	public boolean contains(GraphNode g){
		if (hm.getValue(g) == -1){
			return false;
		}
		return true;
	}
	
	private void heapifyDown(int index){
		int left = left(index);
		int right = right(index);
		int smallest;
		
		if (left < size && nodes[left].priority < nodes[index].priority){
			smallest = left;
		}
		else{
			smallest = index;
		}
		if (right < size && nodes[right].priority < nodes[smallest].priority){
			smallest = right;
		}
		
		if (smallest != index){
			GraphNode temp = nodes[smallest];
			nodes[smallest] = nodes[index];
			nodes[index] = temp;
			
			hm.set(nodes[index], index);
			hm.set(nodes[smallest], smallest);
			
			heapifyDown(smallest);
		}
	}
	
	public void insert(GraphNode g){
		if (size >= nodes.length){
			reheap();
		}
		nodes[size] = g;
		hm.set(g, size);
		heapifyUp(size);
		size++;
	}
	
	private void heapifyUp(int index){
		
		while (index >= 1 && nodes[index].priority < nodes[parent(index)].priority){
			GraphNode temp = nodes[parent(index)];
			nodes[parent(index)] = nodes[index];
			nodes[index] = temp;
			
			//reset values in HashMap
			hm.set(nodes[index], index);
			hm.set(nodes[parent(index)], parent(index));
			
			index = parent(index);
			
		}
		
	}
	
	public void heapify(GraphNode g){
		int index = hm.getValue(g);
		int left = left(index);
		int right = right(index);
		int parent = parent(index);
		
		if (index < 0){
			return;
		}
		
		if ((left < size && nodes[index].priority > nodes[left].priority) || 
				(right < size && nodes[index].priority > nodes[right].priority)){
			heapifyDown(hm.getValue(g));
		}
		else if (parent >= 0 && nodes[parent].priority > nodes[index].priority){
			heapifyUp(hm.getValue(g));
		}
		
	}
	
	private void reheap(){
		GraphNode[] newNodes = new GraphNode[nodes.length*2];
		for (int i = 0; i < size; i++){
			newNodes[i] = nodes[i];
		}
		nodes = newNodes;
	}
	
	public int parent(int index){
		return index/2;
	}
	
	public int left(int index){
		return 2*index+1;
	}
	
	public int right(int index){
		return 2*index+2;
	}
}
