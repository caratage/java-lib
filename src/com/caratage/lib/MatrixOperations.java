package com.caratage.lib;
/**
 * <h1>Matrix Operations</h1>
 * Collection of methods from EidiP for BIS at HBRS.
 * <p>
 * <b>Note:</b> These are exemplary methods for n x n matrix operations. 
 * We only look at the logic of swapping cells here.
 * A new array is produced and returned.
 *
 * @author  Urs Weishaupt (uweish2s)
 * @version 1.0
 * @since   2017-01-24
 */
public class MatrixOperations {
	/**
	 * Mirror a matrix using point reflection in the middle and return matrix in a new array.
	 *
	 * @param f 	Input matrix (array)
	 * @return int[][] 	New array
	 */
	public static int[][] pointReflection(int[][] f) {
		int[][] ps = new int[f.length][f[0].length];
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				ps[i][j] = f[f.length - i - 1][f[0].length - j - 1]; 
			}
		}
		return ps;
	}
	/**
	 * Mirror a matrix at diagonale left-bottom to right-top and return matrix in a new array.
	 *
	 * @param f 	Input matrix (array)
	 * @return int[][] 	New array
	 */
	public static int[][] diagonalUp(int[][] f) {
		int[][] ps = new int[f.length][f[0].length];
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				ps[i][j] = f[f.length - j - 1][f[0].length - i - 1]; 
			}
		}
		return ps;
	}
	/**
	 * Mirror a matrix at diagonale left-top to right-bottom (transpose) and return matrix in a new array.
	 *
	 * @param f 	Input matrix (array)
	 * @return int[][] 	New array
	 */
	public static int[][] diagonalDown(int[][] f) {
		int[][] ps = new int[f.length][f[0].length];
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				ps[i][j] = f[j][i]; 
			}
		}
		return ps;
	}
	/**
	 * Flip matrix horizontally and return matrix in a new array.
	 *
	 * @param f 	Input matrix (array)
	 * @return int[][] 	New array
	 */
	public static int[][] flipHorizontal(int[][] f) {
		int[][] ps = new int[f.length][f[0].length];
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				ps[i][j] = f[f[0].length - (i + 1)][j]; 
			}
		}
		return ps;
	}
	/**
	 * Flip matrix vertically and return matrix in a new array.
	 *
	 * @param f 	Input matrix (array)
	 * @return int[][] 	New array
	 */
	public static int[][] flipVertical(int[][] f) {
		int[][] ps = new int[f.length][f[0].length];
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				ps[i][j] = f[i][f[0].length - j - 1]; 
			}
		}
		return ps;
	}
	/**
	 * Rotate matrix 90 degrees to the right and return matrix in a new array.
	 *
	 * @param f 	Input matrix (array)
	 * @return int[][] 	New array
	 */
	public static int[][] rotateRight(int[][] f) {
		int[][] ps = new int[f.length][f[0].length];
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				ps[i][j] = f[f.length - j - 1][i]; 
			}
		}
		return ps;
	}
	/**
	 * Rotate matrix 90 degrees to the left and return matrix in a new array.
	 *
	 * @param f 	Input matrix (array)
	 * @return int[][] 	New array
	 */
	public static int[][] rotateLeft(int[][] f) {
		int[][] ps = new int[f.length][f[0].length];
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				ps[i][j] = f[j][f.length - i - 1]; 
			}
		}
		return ps;
	}
	/**
	 * Print array to screen
	 *
	 * @param f 	Input matrix (array)
	 */
	public static void toString(int[][] f) {
		for (int i = 0; i < f.length; ++i) {
			for (int j = 0; j < f[0].length; ++j) {
				System.out.print("" + f[i][j]);
				if (j < f[0].length - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}