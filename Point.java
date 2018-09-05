import java.util.Arrays;

 
public class Point {
 
    private double[] cordinate;
    private int clusterno = 0;
    
    public Point(double[] cordinate) {
    	this.cordinate = cordinate;
    }
 
    public void setCordinate(double[] cordinate) {
    	this.cordinate = cordinate;
    }
    
    public double[] getCordinate() {
    	return cordinate;
    }
    
    public void setCluster(int c) {
        this.clusterno = c;
    }
    
    public int getCluster() {
        return this.clusterno;
    }
    
    
    protected static double distance(Point point, Point centroid) {
    	
    	double distance = 0;
    	
    	for(int i=0; i<point.getCordinate().length; i++) {
    		distance += Math.pow(point.getCordinate()[i]-centroid.getCordinate()[i], 2.0);
    	}
 
    	return Math.sqrt(distance);
    }
    
    public String toString() {
    	return Arrays.toString(cordinate);
    }
}