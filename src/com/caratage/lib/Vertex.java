package com.caratage.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Vertex<T> {
	
	public enum Status { UNVISITED, VISITED, COMPLETED; }
	
	private T value;
	private List<Edge<T>> adjacencyList;
	private Vertex<T> parent;
	
	public Vertex(T v) {
		this.value = v;
		this.adjacencyList = new ArrayList<>();
	}

	/**
	 * @return returns vertex value of type T
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value sets the vertex value
	 */
	public void setValue(T v) {
		this.value = v;
	}

	/**
	 * Method to add a new edge to the vertex.
	 * @param vertexTo 	destination vertex of the edge
	 * @param weight		weight of the edge to be added
	 * @return			returns true if the edge was added, false otherwise
	 */
	public boolean addEdge(Vertex<T> vertexTo, double weight) {
        if (hasEdge(vertexTo)) {
            return false;
        }
        Edge<T> newEdge = new Edge<>(this, vertexTo, weight);
        return adjacencyList.add(newEdge);
    }
	
	/**
	 * Method to check for outgoing edges of a vertex
	 * @return		returns true if the vertex has outgoing edges, false otherwise
	 */
	public boolean hasEdges() {
		if (adjacencyList.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return		returns the edges of this vertex as a list
	 */
	public List<Edge<T>> getEdges() {
		return adjacencyList;
	}
	
	public boolean hasEdge(Vertex<T> vertex) {
		return findEdge(vertex).isPresent();
	}
	
	

	 private Optional<Edge<T>> findEdge(Vertex<T> vertex) {
	        return adjacencyList.stream()
	                .filter(edge -> edge.isBetween(this, vertex))
	                .findFirst();
	    }

	public Vertex<T> getParent() {
		return parent;
	}

	public void setParent(Vertex<T> parent) {
		this.parent = parent;
	}

	public String toString() {
		return "" + value;	
	}
}