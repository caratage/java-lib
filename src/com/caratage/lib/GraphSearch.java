package com.caratage.lib;

import java.util.*;
import java.util.Map.Entry;

/**
 * Breath First Search Algorithm for a generic Graph
 * Adapted from Sedgewick and Wayne
 * @source https://algs4.cs.princeton.edu/42digraph/BreadthFirstDirectedPaths.java.html
 * @autor Urs Weishaupt
 * 
 */

public class GraphSearch<T> {
	
	private static final int INFINITY = Integer.MAX_VALUE;
	private Map<Vertex<T>, Boolean> marked;
	private Map<Vertex<T>, Vertex<T>> edgeTo;
	private Map<Vertex<T>, Integer> distTo;
	private Map<Vertex<T>, Integer> timeDiscovered; // d
	private Map<Vertex<T>, Integer> timeFinished; // f
	private Map<Vertex<T>, Integer> timeVisited; // b
	private int time;
	
	public void BreathFirstSearch(Graph<T> g, Vertex<T> start) {
		Set<Entry<T, Vertex<T>>> graph = g.getVertexMap().entrySet();
		marked = new HashMap<>();
		distTo = new HashMap<>();
		edgeTo = new HashMap<>();
		timeVisited = new HashMap<>();
		
		for (Map.Entry<T, Vertex<T>> vertex : graph) {
			distTo.put(vertex.getValue(), INFINITY);
			marked.put(vertex.getValue(), false);
		}
		validateVertex(g, start);
		for (Map.Entry<T, Vertex<T>> vertex : graph) {
			if (!marked.get(vertex.getValue())) {
				BreathFirstSearchVisit(start);
			}
		}
	}
	
	// BFS from single source vertex
	private void BreathFirstSearchVisit(Vertex<T> start) {
		LinkedList<Vertex<T>> q = new LinkedList<>();
		marked.put(start, true);
		distTo.replace(start, 0);
		q.addLast(start);
		while(!q.isEmpty()) {
			Vertex<T> current = q.removeFirst();
			timeVisited.put(current, ++time);
			for (Edge<T> edge : current.getEdges()) {
				Vertex<T> adjacent = edge.getDestination();
				if (!marked.get(adjacent)) {
					edgeTo.put(adjacent, current);
					Integer distToCurrent = distTo.get(current) + 1;
					distTo.replace(adjacent, distToCurrent);
					marked.replace(adjacent, true);
					q.addLast(adjacent);
				}
			}
		}
	}
	
	public void DepthFirstSearch(Graph<T> g) {
		Set<Entry<T, Vertex<T>>> graph = g.getVertexMap().entrySet();
		marked = new HashMap<>();
		timeDiscovered = new HashMap<>(); // d
		timeFinished = new HashMap<>(); // f
		// timeVisited = new HashMap<>();
		
		for (Map.Entry<T, Vertex<T>> vertex : graph) {
			marked.put(vertex.getValue(), false);
		}
		for (Map.Entry<T, Vertex<T>> vertex : graph) {
			Vertex<T> current = vertex.getValue();
			if (!marked.get(current)) {
				DepthFirstSearchVisit(current);
			}
		}
	}
	
	// DFS from single source vertex
	private void DepthFirstSearchVisit(Vertex<T> current) {
		marked.put(current, true);
		timeDiscovered.put(current, ++time);
		for (Edge<T> edge : current.getEdges()) {
			Vertex<T> adjacent = edge.getDestination();
			if (!marked.get(adjacent)) {
				adjacent.setParent(current);
				DepthFirstSearchVisit(adjacent);
			}
		}
		marked.put(current, true);
		timeFinished.put(current, ++time);
		// TODO: DFSAnnotate
		// DepthFirstSearchAnnotateEdges(current);
	}
	
	/*private void DepthFirstSearchAnnotateEdges(Vertex<T> w) {
		// TODO: implement annotation properly
		for (Edge<T> edge : w.getEdges()) {
			Vertex<T> u = edge.getOrigin();
			Vertex<T> v = edge.getDestination();
			if (v.getParent() == u) {
				edge.setType(Status.Edge.TREE);
			} else if (u.getD() > v.getD() && u.getF() < v.getF()) {
				edge.setType(Status.Edge.BACK);
			} else if (u.getD() < v.getD() && u.getF() > v.getF()) {
				edge.setType(Status.Edge.FORWARD);
			} else {
				edge.setType(Status.Edge.CROSS);
			}	
		}
	}*/
	
	public void topologicalSort(Graph<T> g) {
		Set<Entry<T, Vertex<T>>> graph = g.getVertexMap().entrySet();
		marked = new HashMap<>();
		Stack<Vertex<T>> stack = new Stack<>();
		Vertex<T> current = null;
		
		for (Map.Entry<T, Vertex<T>> vertex : graph) {
			marked.put(vertex.getValue(), false);
		}
		for (Map.Entry<T, Vertex<T>> vertex : graph) {
			current = vertex.getValue();
			if (!marked.get(current)) {
				topologicalSortVisit(current, stack);
			}
		}
		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}
		
	}
	
	private void topologicalSortVisit(Vertex<T> current, Stack<Vertex<T>> stack) {
		marked.put(current, true);
		for (Edge<T> edge : current.getEdges()) {
			Vertex<T> adjacent = edge.getDestination();
			if (!marked.get(adjacent)) {
				adjacent.setParent(current);
				topologicalSortVisit(adjacent, stack);
			}
		}
		stack.push(current);
	}
	
	public boolean hasPathTo(Graph<T> g, Vertex<T> destination) {
		validateVertex(g, destination);
		return marked.get(destination);
	}
	
	public int distTo(Vertex<T> destination) {
		//validateVertex(g, destination);
		return distTo.get(destination);
	}
	
	public int timeVisited(Vertex<T> vertex) {
		//validateVertex(g, destination);
		return timeVisited.get(vertex);
	}
	
	public int timeDiscovered(Vertex<T> vertex) {
		//validateVertex(g, destination);
		return timeDiscovered.get(vertex);
	}
	
	public int timeFinished(Vertex<T> vertex) {
		//validateVertex(g, destination);
		return timeFinished.get(vertex);
	}
	
	public Iterable<Vertex<T>> pathTo(Graph<T> g, Vertex<T> destination) {
		validateVertex(g, destination);
		if (!hasPathTo(g, destination)) {
			return null;
		}
		LinkedList<Vertex<T>> path = new LinkedList<>();
		Vertex<T> current;
		for (current = destination; distTo.get(current) != 0; current = edgeTo.get(current)) {
			path.push(current);
		}
		path.push(current);
		return path;
	}
	
	public void validateVertex(Graph<T> g, Vertex<T> vertex) {
		if (!g.containsVertex(vertex.getValue())) {
			throw new IllegalArgumentException("The vertex " + vertex + " seems not to be present in this graph.");
		}
	}
	
	public String toString() {
		String s = "";
		if (timeFinished != null ) {
			for (Entry<Vertex<T>, Integer> vertex : timeDiscovered.entrySet()) {
				String u = vertex.getKey().toString();
				s += "d[" + u + "] = " + vertex.getValue() + "\n";
			}
			s += "\n";
		}
		if (timeFinished != null ) {
			for (Entry<Vertex<T>, Integer> vertex : timeFinished.entrySet()) {
				String u = vertex.getKey().toString();
				s += "f[" + u + "] = " + vertex.getValue() + "\n";
			}
			s += "\n";
		}
		if (timeVisited != null ) {
			for (Entry<Vertex<T>, Integer> vertex : timeVisited.entrySet()) {
				String u = vertex.getKey().toString();
				s += "b[" + u + "] = " + vertex.getValue() + "\n";
			}
			s += "\n";
		}
		if (distTo != null ) {
			for (Entry<Vertex<T>, Integer> vertex : distTo.entrySet()) {
				String u = vertex.getKey().toString();
				s += "distTo[" + u + "] = " + vertex.getValue() + "\n";
			}
			s += "\n";
		}
		return s;
	}
	
}