package com.caratage.lib;
/*
 * @author uweish2s
 */

import java.util.Scanner;

public class MatrixStatic {
	// a
	public static int[][] matrix(int z, int s)  {
		int[][] matrix = new int[z][s];
		return matrix;
	} 
	// b
	public static void setAll(int[][] t, int e) throws Exception {
		pruefeMatrix(t); // throws Exception
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {
				t[i][j] = e;
			}
		}
	}
	// c
	public static void setAllRandom(int[][] t, int min, int max) throws Exception {
		pruefeMatrix(t); // throws Exception
		if (min >= max) {
			throw new Exception();
		}
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {
				t[i][j] = (int) ((Math.random() * (max - min + 1)) + min);
			}
		}
	}
	// d
	public static void setzeZeile(int[][] t, int i, int[] z) throws Exception {
		pruefeMatrix(t); // throws Exception
		if (t[i].length != z.length) {
			throw new Exception("Zeilen muessen gleich lang sein.");
		}
		for (int j = 0; j < z.length; ++j) {
			t[i][j] = z[j];
		}
	}
	// e
	public static void setzeSpalte(int[][] t, int j, int[] s) throws Exception {
		pruefeMatrix(t); // throws Exception
		for (int i = 0; i < s.length; ++i) {
			if (t[i].length != s.length) {
				throw new Exception("Anzahl Spalten und Zeilen ungleich!");
			}
		}		
		for (int i = 0; i < s.length; ++i) {
			t[i][j] = s[i];
		}
	}
	// f
	public static void eingabe(int[][] t, Scanner sc) throws Exception {
		pruefeMatrix(t); // throws Exception
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {
				t[i][j] = sc.nextInt();
			}
		}
	}
	// g
	public static String toString(int[][] t) throws Exception {
		pruefeMatrix(t); // throws Exception
		String erg = "";
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {
				erg += "" + t[i][j];
				if (j < t[i].length - 1) {
					erg += " ";
				}
				if (j == t[i].length - 1) {
					erg += "\n";
				}
			}
		}
		return erg;
	}
	// h
	public static boolean istGleich(int[][] t1, int[][] t2) throws Exception {
		return toString(t1).equals(toString(t2)) ? true : false;
	}
	// i
	public static int[][] kopie(int[][] t) throws Exception {
		pruefeMatrix(t); // throws Exception
		int[][] kopie = new int[t.length][t[0].length];
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {
				kopie[i][j] = t[i][j];
			}
		}
		return kopie;
	}
	// j
	public static int[][] punktProdukt(int[][] t1, int[][] t2) throws Exception {
		pruefeMatrix(t1); // throws Exception
		pruefeMatrix(t2); // throws Exception
		int[][] pp = new int[t1.length][t1[0].length];
		for (int i = 0; i < t1.length; ++i) {
			for (int j = 0; j < t1[i].length; ++j) {
				pp[i][j] = t1[i][j] * t2[i][j];
			}
		}
		return pp;
	}
	// k
	public static int[][] punktPotenz(int[][] t, int n) throws Exception {
		pruefeMatrix(t); // throws Exception
		int[][] ppot = new int[t.length][t[0].length];
		setAll(ppot, 1);
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {
				for (int k = 0; k < n; ++k) {
					ppot[i][j] = ppot[i][j] * t[i][j];
				}
			}
		}
		return ppot;
	}
	public static void pruefeMatrix(int[][] t) throws Exception {
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {
				if (t[0].length != t[i].length) {
					throw new Exception ("Keine Matrix uebergeben!");
				}
			}
		}
	}
}