package dataStructure;
import java.util.HashMap;
import java.util.Iterator;

import utils.Point3D;

public class Node extends HashMap<Integer, edge_data> implements node_data , Comparable<node_data>{
	private int key;
	private Point3D location;
	private double weight;
	private String info = "";
	private int tag; // for algorithms
	private Node father; // for shortest path algo
	///////// constructors  /////////
	
	/**
	 * @param key - the Node number
	 * @param location - for GUI 
	 * @param weight - for algorithms
	 * @param info - for algorithms
	 * @param tag - for algorithms
	 */
	public Node(int key,Point3D location,double weight,String info,int tag) {
		this.key = key;
		this.location = new Point3D(location);
		this.weight = weight;
		this.info = info;
		this.tag = tag;
	}
	
	/**
	 * @param key - the Node number
	 * @param location - for GUI 
	 * @param weight - for algorithms
	 */
	public Node(int key,Point3D location) {
		this.key = key;
		this.location = new Point3D(location);
	}
	/**
	 * Deep copy Constructor
	 * @param n - the orgNode to copy
	 */
	public Node(node_data n) {
		this.key = n.getKey();
		this.location = new Point3D(n.getLocation());
		this.weight = n.getWeight();
		this.info = n.getInfo();
		this.tag = n.getTag();
	}
	/**
	 * Construct from a String. Using for load graph from a text file
	 * @param s - String in toString() format
	 */
	public Node(String s) {
		String [] parts=s.split(" @ ");
		String[] params=parts[0].split(", ");
		this.key = Integer.parseInt(params[0]);
		this.location = new Point3D(params[1]);
		this.weight = Double.parseDouble(params[2]);
		this.info = params[3];
		this.tag = Integer.parseInt(params[4]);

		if (parts.length > 1) {
			String[] edges = parts[1].split(" # ");
			for (String string : edges) {
				Edge e = new Edge(string);
				if (e.getSrc() == getKey())
					put(e.getDest(), e);
				else
					throw new RuntimeException("Edge src is " + e.getSrc() + " but source node key is " + getKey());
			}
		}
	}
	
	@Override
	public int compareTo(node_data o) {
		Double comp = getWeight();
		return comp.compareTo(o.getWeight());

	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Point3D getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point3D p) {
		location = new Point3D(p);
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		weight = w;
		
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		info = s;
		
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		tag = t;
	}
	/**
	 * for algorithms such as "shortest path", to trace back the path
	 */
	public Node getFather() {
		return father;
	}
	/**
	 * for algorithms such as "shortest path", to trace back the path
	 */
	public void setFather(node_data father) {
		this.father = (Node)father;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Node))
			return false;
		Node n = (Node) arg0;
		
		return n.getKey() == getKey()
				&& n.getLocation().equalsXY(getLocation())
				&& super.equals(arg0);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(key + ", " + location + ", "
		+ weight + ", " + info + ", " + tag + " @ ");
		for (Iterator<edge_data> it = values().iterator(); it.hasNext();) {
			sb.append(it.next());
			sb.append(" # ");
		}
		if (size() > 0)
			sb.delete(sb.length() - 3, sb.length());
		return sb.toString();
	}

}
