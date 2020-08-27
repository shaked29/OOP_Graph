package dataStructure;

public class Edge implements edge_data{
	private int src;
	private int dest;
	private double weight;
	private String info = "";
	private int tag;
	
	 /**
     *The default constructor
     */
    public Edge(){
        this.src=0;
        this.dest=0;
        this.weight=0.0;
        this.info="";
        this.tag=0;
    }
	/**
	 * 
	 * @param src - the source {@link Node}
	 * @param dest  - the destination {@link Node}
	 * @param weight - used to calculate distance
	 */
	public Edge(int src, int dest, double weight) {
		this(src, dest, weight, "", 0);
	}
	/**
	 * 
	 * @param src - the source {@link Node}
	 * @param dest  - the destination {@link Node}
	 * @param weight - used to calculate distance
	 * @param info - for Algorithms use
	 * @param tag - for Algorithms use
	 */
	public Edge(int src, int dest, double weight, String info, int tag) {
		if(weight <= 0)
			throw new RuntimeException("Can't set negativ waight ("+weight+")");
		
		
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
	
	/**
	 * Deep copy Constructor
	 * @param e - the DEdge to copy
	 */
	public Edge(edge_data e) {
		this(e.getSrc(), e.getDest(), e.getWeight(), e.getInfo(), e.getTag());
	}
	/**
	 * @return - copy of this edge in the opposite direction
	 */
	public Edge getReversEdge() {
		Edge e = new Edge(this);
		int tmp = e.dest;
		e.dest = e.src;
		e.src = tmp;
		return e;
	}

	@Override
	public int getSrc() {
		return src;
	}

	@Override
	public int getDest() {
		return dest;
	}

	@Override
	public double getWeight() {
		return weight;
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
	
	
	@Override
	public String toString() {
		return src + ", " + dest + ", " + weight + ", " + info + ", " + tag;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Edge))
			return false;
		Edge e = (Edge) arg0;
		return getSrc() == e.getSrc()
				&& getDest() == e.getDest()
				&& getWeight() == e.getWeight();
	}
}
