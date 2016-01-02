package roadgraph;

public class MapEdge {
    private MapNode startNode;
    private MapNode endNode;
    private String roadName;
    private String roadType;
    private double roadLength;
    
    // Constructor.
    public MapEdge(MapNode s, MapNode e, String name, String type, double len){
        startNode = s;
        endNode = e;
        roadName = name;
        roadType = type;
        roadLength = len;
    }
    
    /**
     * Get the end node of this map edge.
     * @return
     */
    public MapNode getEndNode(){
    	return endNode;
    }
    
}
