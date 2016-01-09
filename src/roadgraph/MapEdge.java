package roadgraph;

public class MapEdge {
    private MapNodeForDij startNode;
    private MapNodeForDij endNode;
    private String roadName;
    private String roadType;
    private double roadLength;
    
    // Constructor.
    public MapEdge(MapNodeForDij s, MapNodeForDij e, String name, String type, double len){
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
    public MapNodeForDij getEndNode(){
    	return endNode;
    }
    
    public double getLength(){
    	return roadLength;
    }
    
}
