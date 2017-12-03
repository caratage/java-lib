package com.caratage.lib;

/**
 * @author Urs Weishaupt
 * @source https://codereview.stackexchange.com/questions/67970/graph-implementation-in-java-8
 */

import java.util.HashMap;
import java.util.Map;

public class Graph<T> {
	
    private final Map<T, Vertex<T>> vertexMap = new HashMap<>();
    
    public Map<T, Vertex<T>> getVertexMap() {
		return vertexMap;
	}

	/**
     * Adds a vertex to the graph.
     * @param v		vertex to add
     * @return  		returns true if the vertex has been added, false otherwise
     */
    public boolean addVertex(T v) {
    		if (containsVertex(v)) {
    			return false;
    		}
    		getVertexMap().put(v, new Vertex<>(v));
    		return true;
    }
    
    /**
     * Adds a directed edge between two vertices in the graph.
     * @param from		vertex where the (directed) edge begins
     * @param to			vertex where the (directed) edge ends
     * @return  			returns true if the egde has been added, false otherwise
     */
    public boolean addEdge(T from, T to) {
        return addEdge(from, to, 1);
    }
    
    /**
     * Adds a weighted directed edge between two vertices in the graph.
     * @param from		vertex where the (directed) edge begins
     * @param to			vertex where the (directed) edge ends
     * @param weight		weight of the edge
     * @return  			returns true if the egde has been added, false otherwise
     * @throws			RuntimeException
     */
    public boolean addEdge(T from, T to, double weight) {
        if (!containsVertex(from) || !containsVertex(to)) {
            throw new RuntimeException("Vertex does not exist");
        }
        Vertex<T> v1 = getVertex(from);
        Vertex<T> v2 = getVertex(to);
        return v1.addEdge(v2, weight);
    }
    
    /**
     * Method to check if a vertex exists in the graph.
     * @param v		vertex which is to be checked
     * @return  		returns true if the graph contains the vertex, false otherwise
     */
    public boolean containsVertex(T v) {
        return getVertexMap().containsKey(v);
    }
    
    /**
     * Method to get a vertex based on its value
     * @param v		vertex which is to be checked
     * @return  		returns the vertex object
     */
    public Vertex<T> getVertex(T v) {
        return getVertexMap().get(v);
    }
    
    /**
     * Method to get the number of vertices present in the graph
     * @return  		returns the number as int
     */
    public int countVertices() {
    		return getVertexMap().keySet().size();
    }
    
    public String toString() {
    		String s = "";
    		for (Map.Entry<T, Vertex<T>> v : getVertexMap().entrySet()) {
    			Vertex<T> current = v.getValue();
    			s += current;
    			s += ":";
    			for (Edge<T> e : current.getEdges()) {
    				if (e.getOrigin() == current) {
    					s += " ";
    					s += e.getDestination();
    					
    					// s += " Type: " + e.getType();
    					//s += " Parent: " + e.toVertex();
    					
    				}
    			}
    			s += "\n";
    		}
    		return s;
    }
    
    // TODO: output graph as adjacency matrix
    /*public byte[][] toAdjacencyMatrix() {
    		int n = countVertices();
    		byte[][] adjacencyMatrix = new byte[n][n];
    		for (int i = 0; i < n; ++i) {
    			for (int j = 0; j < n; ++j) {
    				adjacencyMatrix[i][j] = getVertex(i).hasEdgeTo(j) ? 1 : 0;
    			}
    		}
    		return adjacencyMatrix;
    }*/
}
