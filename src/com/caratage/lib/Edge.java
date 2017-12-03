package com.caratage.lib;

public class Edge<T> {
	
	public enum Type { TREE, BACK, FORWARD, CROSS, UNKNOWN; }
	
	private Vertex<T> origin;
	private Vertex<T> destination;
	private double weight = 1.0;
	private Type type = Type.UNKNOWN;
	
	public Edge(Vertex<T> from, Vertex<T> to, double weight) {
		this.origin = from;
		this.destination = to;
		this.weight = weight;
	}
	
	public Vertex<T> getOrigin() {
		return origin;
	}
	
	public Vertex<T> getDestination() {
		return destination;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public boolean isBetween(Vertex<T> v1, Vertex<T> v2) {
		return (this.origin == v1 && this.destination == v2);
	}
}
