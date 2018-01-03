
package pathfinder;

import java.io.*;

public class FindMinPath {

	public static void main(String[] args) {
		GraphWrapper gw = new GraphWrapper(true);
		GraphMinPriorityQueue pq = new GraphMinPriorityQueue();
		GraphNode home = gw.getHome();
		home.priority = 0;
		pq.insert(home);
		GraphNode curr = null;
		while(!pq.isEmpty()){
			curr = pq.pullHighestPriorityElement();
			if (curr.isGoalNode()){
				break;
			}
			else{
				if (curr.hasEast()){
					GraphNode east = curr.getEast();
					int x = curr.priority + curr.getEastWeight();
					if (x > 0 && !pq.contains(east) && east.priority > x){
						east.priority = x;
						east.previousNode = curr;
						east.previousDirection = "East";
						pq.insert(east);
					}
					if (x > 0 && pq.contains(east) && east.priority > x){
						east.priority = x;
						pq.rebalance(curr);
						east.previousNode = curr;
						east.previousDirection = "East";
					}
				}
				if (curr.hasWest()){
					GraphNode west = curr.getWest();
					int x = curr.priority + curr.getWestWeight();
					if (x > 0 && !pq.contains(west) && west.priority > x){
						west.priority = x;
						west.previousNode = curr;
						west.previousDirection = "West";
						pq.insert(west);
					}
					if (x > 0 && pq.contains(west) && west.priority > x){
						west.priority = x;
						pq.rebalance(curr);
						west.previousNode = curr;
						west.previousDirection = "West";
					}
				}
				if (curr.hasNorth()){
					GraphNode north = curr.getNorth();
					int x = curr.priority + curr.getNorthWeight();
					if (x > 0 && !pq.contains(north) && north.priority > x){
						north.priority = x;
						north.previousNode = curr;
						north.previousDirection = "North";
						pq.insert(north);
					}
					if (x > 0 && pq.contains(north) && north.priority > x){
						north.priority = x;
						pq.rebalance(curr);
						north.previousNode = curr;
						north.previousDirection = "North";
					}
				}
				if (curr.hasSouth()){
					GraphNode south = curr.getSouth();
					int x = curr.priority + curr.getSouthWeight();
					if ( x > 0 && !pq.contains(south) && south.priority > x){
						south.priority = x;
						south.previousNode = curr;
						south.previousDirection = "South";
						pq.insert(south);
					}
					if (x > 0 && pq.contains(south) && south.priority > x){
						south.priority = x;
						pq.rebalance(curr);
						south.previousNode = curr;
						south.previousDirection = "South";
					}
				}
			}
		}
		GraphNode[] path = new GraphNode[100];
		int top = -1;
		while (curr != home){
			top++;
			path[top] = curr;
			curr = curr.previousNode;
		}
		
		try{
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("answers.txt"), "utf-8"));
			while (top > -1){
				writer.write(path[top].previousDirection + "\n");
				System.out.println(path[top].previousDirection);
				top--;
			}
			writer.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
	}

}
