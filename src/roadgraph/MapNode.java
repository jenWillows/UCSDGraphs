package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {
    private GeographicPoint location;
    private List<MapEdge> outEdges;
    
    public MapNode(GeographicPoint loc){
    	location = loc;
    	outEdges = new ArrayList<MapEdge>();
    }
    
    public GeographicPoint getLocation(){
    	return location;
    }
    
    public List<MapEdge> getOutEdges(){
    	return outEdges;
    }
    
    public void addOutEdge(MapEdge edge){
    	outEdges.add(edge);
    }
}
