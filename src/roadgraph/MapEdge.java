package roadgraph;

public class MapEdge {
    private MapNode startNode;
    private MapNode endNode;
    private String roadName;
    private String roadType;
    private double roadLength;
    
    public MapEdge(MapNode s, MapNode e, String name, String type, double len){
        startNode = s;
        endNode = e;
        roadName = name;
        roadType = type;
        roadLength = len;
    }
    
    public MapNode getEndNode(){
    	return endNode;
    }
    public String getStreetName(){
    	return roadName;
    }
    
}
