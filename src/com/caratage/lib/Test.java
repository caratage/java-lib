package com.caratage.lib;

public class Test {
	public static void main(String[] args) {
		int[][] a = {{1,1},{1,1}};
		Matrix m = new Matrix(a);
		System.out.println(m);
		System.out.println(m.multiplyMatrix(m));
		System.out.println(m.powerMatrix(1));
		System.out.println(m.powerMatrix(2));
		System.out.println(m.powerMatrix(3));
	}

}
