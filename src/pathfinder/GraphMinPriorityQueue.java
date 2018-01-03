package pathfinder;


public class GraphMinPriorityQueue extends GraphMinHeap {
	//insert and contains methods are implemented in abstract GraphMinHeap class
	
	public GraphNode pullHighestPriorityElement(){
		return getMin();
	}
	
	public void rebalance(GraphNode g){
		heapify(g);
	}
	
	public boolean isEmpty(){
		if (size== 0){
			return true;
		}
		return false;
	}
}

