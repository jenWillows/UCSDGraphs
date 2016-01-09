package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {
    public GeographicPoint location;
    private List<MapEdge> outEdges;
    
    // Constructor.
    public MapNode(GeographicPoint loc){
    	location = loc;
    	outEdges = new ArrayList<MapEdge>();
    }
    
    
    /**
     * Get geographic location of this map node. 
     * @return
     */
    public GeographicPoint getLocation(){
    	return location;
    }
    
    
    /**
     * Get all our edges of this map node.
     * @return
     */
    public List<MapEdge> getOutEdges(){
    	return outEdges;
    }
    
    /**
     * Add one out edge to this map node.
     * @param edge
     */
    public void addOutEdge(MapEdge edge){
    	outEdges.add(edge);
    }

}
