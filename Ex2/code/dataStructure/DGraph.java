package dataStructure;

import utils.Point3D;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.Serializable;


public class DGraph implements graph,Serializable{
	private int nodesCount;
	private int edgesCount;
	private int modeCount;
	public HashMap<Integer,node_data> nodeGraph;
	public HashMap<Integer,HashMap<Integer,edge_data>> edgeGraph;
	
	/**
	 * Default constructor
	 */
	public DGraph(){
		this.nodeGraph = new HashMap<Integer,node_data>();
		this.edgeGraph = new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.nodesCount =0;
		this.edgesCount =0;
		this.modeCount =0;
	}
	@Override
	public node_data getNode(int key) {
		try{
			return this.nodeGraph.get(key);
		}
		catch (Exception e){
			throw new RuntimeException ("The Node " + key + " NOT exist");
		}
	}
	/**
	 * @param src First tip in the edge
	 * @param dest Second tip in the edge
	 * @return A Edge that starts at the src vertex and ends at the vertex dest
	 * We will check if the Edge is exsit, if yes - we return it, otherwise we will throw an exception
	 * that stops the program and notify the user that no such Edge exists.
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		try{
			return this.edgeGraph.get(src).get(dest);
		}
		catch (Exception e){
			System.out.println("The Edge " + src + " - " + dest + " NOT exist");
			return null;
		}
	}
	/**
	 * @param n The Node we want added to the Dgraph
	 * We add the Node to the graph, if this node already exists in the graph we will replace him with the new node.
	 * we advance nodesCount and modeCount
	 */
	@Override
	public void addNode(node_data n) {
		if (this.nodeGraph.get(n.getKey())!=null){ // if Node n exsist in the graph
			HashMap<Integer,edge_data> src = new HashMap<Integer, edge_data>();
			if (this.edgeGraph.get(n.getKey())!=null) { // if there is edge that start at Node n
				src = this.edgeGraph.get(n.getKey());
			}
			HashMap <Integer,Double> dest =new HashMap<Integer, Double>();
			Iterator iter = this.edgeGraph.entrySet().iterator();
			while (iter.hasNext()) { //add all the edge that look like x -> node
				Map.Entry mapElement = (Map.Entry) iter.next();
				int currSrc = ((int) mapElement.getKey());
				if (this.edgeGraph.get(currSrc).get(n.getKey()) != null)
					dest.put(currSrc,this.edgeGraph.get(currSrc).get(n.getKey()).getWeight());
			}
			removeNode(n.getKey());
			this.nodeGraph.put(n.getKey(),n);
			this.edgeGraph.put(n.getKey(),src);
			if (dest!= null){
				for (Integer i : dest.keySet()){
					connect(i,n.getKey(),dest.get(i));
				}
			}
		}
		else{
			this.nodesCount++;
			this.nodeGraph.put(n.getKey(),n);
		}
		this.modeCount++;
		
	}
	/**
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 * The function connects a new edge to the graph
	 */
	@Override
	public void connect(int src, int dest, double w) {
		if(w<0)
		{
			throw new RuntimeException("ERR: The weight is negative");
		}
		//Checks that both vertices exist
		if (this.nodeGraph.get(src)!=null && this.nodeGraph.get(dest)!=null) {
			edge_data newEdge = new Edge(src, dest, w);
			if (this.edgeGraph.get(src) != null) { //If src has neighbors
				if (this.edgeGraph.get(src).get(dest) == null) { //the edge not exists
					this.edgeGraph.get(src).put(dest, newEdge);
				} else {
					removeEdge(src, dest);
					this.edgeGraph.get(src).put(dest, newEdge);
				}
			} else { // no hash map meaning src has no neighbors
				HashMap<Integer, edge_data> toAdd = new HashMap<Integer, edge_data>();
				this.edgeGraph.put(src, toAdd);
				this.edgeGraph.get(src).put(dest, newEdge);
			}
			this.edgesCount++;
			this.modeCount++;
		}
		else System.out.println("ERR: One of the Nodes does not exist");
		
	}
	/**
	 * @return Collection that representing the Nodes in the graph
	 */
	@Override
	public Collection<node_data> getV() {
		return this.nodeGraph.values();

	}
	/**
	 * @param node_id
	 * @return Collection that representing the edges in the graph
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		try{
			return this.edgeGraph.get(node_id).values();
		}
		catch (NullPointerException e){
			return null;
		}
	}
	/**
	 * @param key
	 * @return the node we removed
	 * Deleting a node from the graph,
	 * note that When the node is deleted, all the edge containing the node are also deleted.
	 */
	@Override
	public node_data removeNode(int key) {
		node_data toReturn = this.nodeGraph.get(key);
		if (toReturn!=null) {
			this.nodeGraph.remove(key); //remove node from nodeGraph
			if (this.edgeGraph.get(key) != null) this.edgeGraph.remove(key); //remove all the edge that look like node -> x
			Iterator iter = this.edgeGraph.entrySet().iterator();
			while (iter.hasNext()) { //remove all the edge that look like x -> node
				Map.Entry mapElement = (Map.Entry) iter.next();
				int currSrc = ((int) mapElement.getKey());
				if (this.edgeGraph.get(currSrc).get(key) != null) removeEdge(currSrc, key);
			}
			this.nodesCount--;
			this.modeCount++;
		}
		return toReturn;
	}
	/**
	 * @param src
	 * @param dest
	 * @return the edge we removed
	 */
	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data toReturn = this.edgeGraph.get(src).get(dest);
		if (toReturn!=null) {
			this.edgeGraph.get(src).remove(dest);
			this.edgesCount--;
			this.modeCount++;
		}
		return toReturn;
	}

	@Override
	public int nodeSize() {
		return this.nodesCount;
	}

	@Override
	public int edgeSize() {
		return this.edgesCount;
	}

	@Override
	public int getMC() {
		return this.modeCount;
	}
	/**
	 * @return next valid key number that not used in this graph
	 * Proper use of the function will be done by immediate adding the node to the graph after it is created,
	 * otherwise a situation where double use of the function without instant adding will return the same value to
	 * 2 different nodes that override each other which can cause the entire graph to be disrupted
	 */
	public int findNextKey(){
		int i = 1;
		while (this.nodeGraph.get(i)!=null){
			i++;
		}
		return i;
	}
}
