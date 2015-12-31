/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 2
	private HashMap<GeographicPoint, MapNode> vertices;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		vertices = new HashMap<GeographicPoint, MapNode>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 2
		Set<GeographicPoint> points = getVertices();
		
		return points.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
		Set<GeographicPoint> result = new HashSet<GeographicPoint>();
		
		for(GeographicPoint key: vertices.keySet()){
			result.add(key);
		}
		return result;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		int total = 0;
		//TODO: Implement this method in WEEK 2
		for(GeographicPoint key: vertices.keySet()){
			MapNode vertex = vertices.get(key);
			List<MapEdge> outEdges = vertex.getOutEdges();
			total += outEdges.size();
		}
		return total;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		if(location == null) return false;
		if(vertices.containsKey(location)) return false;
		
		MapNode newNode = new MapNode(location);
		vertices.put(location, newNode);
		
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 2
		if(from == null || to == null || roadName == null || roadType == null){
			throw new IllegalArgumentException();
		}
		if(length < 0){
			throw new IllegalArgumentException();
		}
		if(!vertices.containsKey(from) || !vertices.containsKey(to)){
			throw new IllegalArgumentException();
		}
		
		MapNode fromVertex = vertices.get(from);
		MapNode toVertex = vertices.get(to);
		
		MapEdge newEdge = new MapEdge(fromVertex, toVertex, roadName, roadType, length);
		fromVertex.addOutEdge(newEdge);
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		LinkedList<MapNode> nextVistNodeList = new LinkedList<MapNode>();
		List<MapNode> visitedNodeList = new ArrayList<MapNode>();
		HashMap<GeographicPoint, GeographicPoint> parentPair = new HashMap<GeographicPoint, GeographicPoint>();	

		MapNode current = vertices.get(start);
		visitedNodeList.add(current);
		
		while(!current.getLocation().equals(goal)){
			List<MapEdge> outEdges = current.getOutEdges();
			for(MapEdge edge: outEdges){
				MapNode toNeighbor = edge.getEndNode();
				
				if(!visitedNodeList.contains(toNeighbor)){
					visitedNodeList.add(toNeighbor);
					nextVistNodeList.add(toNeighbor);
					parentPair.put(toNeighbor.getLocation(), current.getLocation());
					
					nodeSearched.accept(toNeighbor.getLocation());
				}			
			}		
			current = nextVistNodeList.remove();			
		}
		
		List<GeographicPoint> path = restorePath(parentPair, start, goal);
        
		return path;
	}
	
	private List<GeographicPoint> restorePath(HashMap<GeographicPoint, GeographicPoint> pair, GeographicPoint start, 
		     GeographicPoint goal){
		List<GeographicPoint> path = new ArrayList<GeographicPoint>();
		
		GeographicPoint child = goal;
		path.add(child);
		
		if(pair.size() == 0) return path;
		
		GeographicPoint parent = pair.get(child);		
        while (!parent.equals(start)){
        	path.add(parent);
        	child = parent;
        	parent = pair.get(child);
        }
        path.add(start);
	    Collections.reverse(path);
	    
		return path;
	}
	
	public void printMap(){
		String map = "";
		
		for(GeographicPoint key: vertices.keySet()){
		    MapNode node = vertices.get(key);
		    map = key + "--->";
		    List<MapEdge> outEdges = node.getOutEdges();
		    for(MapEdge edge: outEdges){
		    	map += "(" + edge.getEndNode().getLocation() + ") ";
		    }
		    System.out.println(map);
		}
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		theMap.printMap();
		System.out.println("DONE.");
		
		System.out.println("Testing basic get methods:");
		System.out.println("This map has " + theMap.getNumVertices() + " vertices, and " + theMap.getNumEdges() + " edges.");
		
		System.out.println("Testing breadth first search:");
		GeographicPoint start = new GeographicPoint(4,2);
		GeographicPoint goal = new GeographicPoint(6.5,0);
		System.out.println(theMap.bfs(start, goal));
		
		// You can use this method for testing.  
		
		/* Use this code in Week 3 End of Week Quiz
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
