package com.caratage.lib;

import java.util.Map;

public class GraphUtil {
	
	/**
     * Converts an adjacency list to a graph object.
     * @param adjList	the int array representing an adjacency list
     * @return  			returns a Graph<Integer> object
     */
	public static Graph<Integer> convertAdjListToGraph(int[][] adjList) {
		Graph<Integer> result = new Graph<>();
		for (int i = 0; i < adjList.length; ++i) {
			result.addVertex(i);
		}
		for (int i = 0; i < adjList.length; ++i) {
			for (int j = 0; j < adjList[i].length; ++j) {
				result.addEdge(i, adjList[i][j]);
			}
		}
		return result;
	}
	
	/**
     * Converts an adjacency matrix to a graph object.
     * @param m	the adjacency matrix
     * @return returns a new graph
     */
	public static Graph<Integer> convertAdjMatrixToGraph(Matrix m) {
		Graph<Integer> result = new Graph<>();
		for (int i = 0; i < m.numRows(); ++i) {
			result.addVertex(i);
		}
		for (int i = 0; i < m.numRows(); ++i) {
			for (int j = 0; j < m.numCols(); ++j) {
				int k = m.getPos(i, j);
				if (k == 1) {
					result.addEdge(i, j);
				} else if (k != 0) {
					// k representing the weight of the egde
					result.addEdge(i, j, k);
				} else {
					// no edge, do nothing
				}
			}
		}
		return result;
	}
	
	/**
     * Converts a graph object to an adjacency list
     * @param g the graph to be converted
     * @return returns an int[][] representing the graphs adjacency list
     */
	public static int[][] convertGraphToAdjList(Graph<Integer> g) {
		int numV = g.countVertices();
		int[][] adjList = new int[numV][];
		
		for (Map.Entry<Integer, Vertex<Integer>> v : g.getVertexMap().entrySet()) {
			Vertex<Integer> current = v.getValue();
			int numE = current.getEdges().size();
			for (int i = 0; i < numV; ++i) {
				for (int j = 0; j < numE; ++j) {
					adjList[i][j] = current.getValue();
				}
			}
		}
		return adjList;
	}
	
	/**
     * Converts a graph object to an adjacency matrix
     * @param g the graph to be converted
     * @return returns a matrix representing the graphs adjacency matrix
     */
	public static Matrix convertGraphToAdjMatrix(Graph<Integer> g) {
		// TODO: incorrect logic
		int numV = g.countVertices();
		Matrix m = new Matrix(numV);
		for (int i = 0; i < numV; ++i) {
			for (int j = 0; j < numV; ++j) {
				int v = g.getVertex(i).hasEdge(g.getVertex(j)) ? 1 : 0;
				m.setPos(i, j, v);
			}
		}
		return m;
	}
}