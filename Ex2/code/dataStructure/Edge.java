package dataStructure;

public class Edge implements edge_data{
	private int src;
	private int dest;
	private double weight;
	private String info = "";
	private int tag;
	
	/**
	 * 
	 * @param src - the source {@link DNode}
	 * @param dest  - the destination {@link DNode}
	 * @param weight - used to calculate distance
	 */
	public Edge(int src, int dest, double weight) {
		this(src, dest, weight, "", 0);
	}
	/**
	 * 
	 * @param src - the source {@link DNode}
	 * @param dest  - the destination {@link DNode}
	 * @param weight - used to calculate distance
	 * @param info - for Algorithms use
	 * @param tag - for Algorithms use
	 */
	public Edge(int src, int dest, double weight, String info, int tag) {
		if(weight <= 0)
			throw new RuntimeException("Can't set negativ waight ("+weight+")");
		
		if(src == dest)
			throw new RuntimeException("Can't connect vertex to itself");
		
		this.src = src;
		this.dest = dest;
		this.weight = weight;
		this.info = info;
		this.tag = tag;
	}
	/**
	 * Construct from a String. Using for load graph from a text file
	 * @param s - String in toString() format
	 */
	public Edge(String s) {
		String[] arr = s.split(", ");
		this.src = Integer.parseInt(arr[0]);
		this.dest = Integer.parseInt(arr[1]);
		this.weight = Double.parseDouble(arr[2]);
		this.info = arr[3];
		this.tag = Integer.parseInt(arr[4]);
	}
	@Override
	public int getSrc() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDest() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		
	}

}
