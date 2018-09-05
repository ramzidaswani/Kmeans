import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class KMeans {

	private ArrayList<Point> points;
	private ArrayList<Cluster> clusters;
	
	public KMeans() {
		this.points = new ArrayList<Point>();
		
		this.clusters = new ArrayList<Cluster>();
	}
	
	public static void main(String[] args) throws IOException {
	
	
		String input = args[0]; 
		int k = Integer.parseInt(args[1]); 
		String output = args[2];
		System.setOut(new PrintStream(new FileOutputStream(output)));
		
		TreeMap<Double, KMeans> tree = new TreeMap<Double, KMeans>(); 
		
		int count = 0; 

		while(count<50) { 
			
			KMeans kmeans = new KMeans();
			kmeans.proc(input);
			
			kmeans.Cluster(k);
			
			kmeans.Points();

			double quality;
			
			int[] centroids1 = kmeans.Centroid(k);
			kmeans.Points();
			
			
			while(true) {
				
				int[] centroids2 = kmeans.Centroid(k);
				kmeans.Points();
				
				if(Arrays.equals(centroids2,centroids1)) 
					break;
				else {
					centroids1 = centroids2;
				}
				
			}
			quality = kmeans.calc(); 
			tree.put(quality, kmeans); 
			count++;
		}
		
		
		KMeans kmeans = tree.get(tree.firstKey()); 
		int i=0;
		while(i!=k) {
			
			kmeans.clusters.get(i).plot();
			i++;
		}
		
		System.out.println("SSE equals " + kmeans.calc());
		

	}

	
	private double calc() {
		
		double SSE = 0;
		for(Cluster cluster: clusters) {
			for(Point p : cluster.getPoints()) {
				SSE += Math.pow(Point.distance(p, cluster.getCentroid()), 2);
			}
		}
		return SSE;
	}

	
	private int[] Centroid(int k) {
		
		int attribute = points.get(0).getCordinate().length;
		int[] centroid = new int[k];
		int j = 0;
		for(Cluster cluster : clusters) {
			
			double[] cord = new double[attribute];
			for(Point p : cluster.getPoints()) {
				for(int i = 0; i<attribute; i++) {
					cord[i] += p.getCordinate()[i];
				}
			}
			
			if(cluster.getPoints().size()!=0) {
				for(int i = 0; i<attribute; i++) {
					cord[i] = cord[i] / ((double) cluster.getPoints().size());
				}
				cluster.setCentroid(new Point(cord));
			}
			centroid[j] = cluster.getPoints().size();
			j++;
		}
		
		return centroid;
	}

	
	
	private void Points() {
		
		for(Cluster cluster : clusters) {
			cluster.setPoints(new ArrayList<Point>());
		}
		
		
		for(Point p : points) {
			double distance = Double.MAX_VALUE;
			int k=0;
			for(Cluster cluster : clusters) {
				double candidate = Point.distance(p, cluster.getCentroid());
				if(candidate<distance) {
					distance = candidate;
					k = cluster.getId();
				}
			}
			clusters.get(k).addPoint(p);
		}
		
	}

	
	private void Cluster(int k) {
		
		HashSet<Integer> set = new HashSet<Integer>();
		
		int numAttr = points.get(0).getCordinate().length;
		
		int cluster = k;
		
		k=0;
		
		while(k!=cluster) {
			
			int rand = (int) (Math.random() * points.size());
			
			if(set.contains(rand))
				continue;
			else
				set.add(rand);
			
			double[] coord = points.get(rand).getCordinate().clone();
			
			Cluster newCluster = new Cluster(k);
			
			newCluster.setCentroid(new Point(coord));
			
			clusters.add(newCluster);
			
			k++;
		}
		
	}
	
	private void proc(String input) throws IOException {
		Scanner scan = new Scanner(new File(input));
		
		while(scan.hasNextLine()) {
			
			String line = scan.nextLine();
			
			String record[]  = line.split(",");
			
			double[] cordinate = new double[record.length-1]; 
			
			for(int i=0; i<record.length-1; i++) {
				cordinate[i] = Double.parseDouble(record[i]);
			}
			
			points.add(new Point(cordinate));
			
		}
		
	}

}