import java.util.ArrayList;
import java.util.List;
 
public class Cluster {
	
	private ArrayList<Point> points;
	private Point centroid;
	private int id;
	

	public Cluster(int id) {
		this.id = id;
		this.points = new ArrayList<Point>();
		this.centroid = null;
	}
 
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}
 
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
 
	public Point getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}
 
	public int getId() {
		return id;
	}
	
	public void clear() {
		points.clear();
	}
	
	public void plot() {
		System.out.println("[Cluster: " + id+"]");
		System.out.println("[Centroid: " + centroid.toString() + "]");
		System.out.println("Number of points in this centroid: "+points.size());
		System.out.println("Points:");
		for(Point p : points) {
			System.out.println(p.toString());
		}
		System.out.println();
	}
 
}