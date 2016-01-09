package roadgraph;

import geography.GeographicPoint;

public class MapNodeForDij extends MapNode implements Comparable<MapNodeForDij>{
	
	private double distanceFromStart;
	private double totalDistance;
	
    public MapNodeForDij(GeographicPoint loc) {
		super(loc);
		// TODO Auto-generated constructor stub
		distanceFromStart = Double.POSITIVE_INFINITY;
		totalDistance = Double.POSITIVE_INFINITY;
	}

	public double getDistanceFromStart(){
    	return distanceFromStart;
    }
    
    public void updateDistanceFromStart(double d){
    	distanceFromStart = d;
    }
    
    public void updateTotalDistance(double d, double p){
    	totalDistance = d + p;
    }
    
    public double getTotalDistance(){
    	return totalDistance;
    }

	@Override
	public int compareTo(MapNodeForDij o) {
		// TODO Auto-generated method stub
		if(this.totalDistance < o.totalDistance)
			return -1;
		if(this.totalDistance > o.totalDistance)
			return 1;
		return 0;
	}
}
