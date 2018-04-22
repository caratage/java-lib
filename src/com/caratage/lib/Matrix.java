package com.caratage.lib;

public class Matrix {

	private int[][] matrix;

	/**
	 * Constructor for "n x n" matrix
	 * @param n	size of the matrix
	 */
	public Matrix(int n) {
		matrix = new int[n][n];
	}

	/**
	 * Constructor for "n x m" matrix
	 * @param n	row size of the matrix
	 * @param m 	columns size of the matrix
	 */
	public Matrix(int n, int m) {
		matrix = new int[n][m];
	}

	/**
	 * Constructor for array to matrix. The array is checked for validity
	 * @param a int[][] that represents the new matrix
	 */
	public Matrix(int[][] a) {
		setMatrix(a);
	}

	/**
	 * Matrix to array conversion
	 * @return returns matrix as int[][]
	 */
	public int[][] getArray() {
		return matrix;
	}

	/**
	 * Array to matrix setter. The array is checked for validity
	 * @param a int[][] representing a matrix
	 */
	public void setMatrix(int[][] a) {
		if (!isMatrix(a)) {
			arrayIsNoMatrix();
		}
		matrix = a;
	}

	/**
	 * Get rows
	 * @return returns the number of rows, 0 if matrix has not been initialized
	 */
	public int numRows() {
		return matrix == null ? 0 : matrix.length;
	}

	/**
	 * Get columns
	 * @return returns the number of columns, 0 if matrix has not been initialized
	 */
	public int numCols() {
		return matrix == null ? 0 : matrix[0].length;
	}

	/**
	 * Get the value at position i, j
	 * @param i the row number to get
	 * @param j the column number to get
	 * @return returns the value at position i, j
	 */
	public int getPos(int i, int j) {
		return matrix[i][j];
	}

	/**
	 * Set the value at position i, j
	 * @param i the row number to set
	 * @param j the column number to set
	 * @param v the value to set
	 */
	public void setPos(int i, int j, int v) {
		matrix[i][j] = v;
	}

	/**
	 * Fill all positions in the matrix with the same value
	 * @param v the value to set
	 */
	public void setAll(int v) {
		for (int i = 0; i < numRows(); ++i) {
			for (int j = 0; j < numCols(); ++j) {
				matrix[i][j] = v;
			}
		}
	}

	/**
	 * Fill all positions in the matrix with a random value
	 * @param min the lower boundary for random value generation
	 * @param max the upper boundary for random value generation
	 */
	public void setAllRandom(int min, int max) {
		for (int i = 0; i < numRows(); ++i) {
			for (int j = 0; j < numCols(); ++j) {
				matrix[i][j] = (int) (Math.random() * (max - min)) + min;
			}
		}
	}

	/**
	 * Update a row in the matrix with values from an array
	 * @param row the row to update
	 * @param a the array
	 */
	public void setRow(int row, int[] a) throws Exception {
		if (a == null || numCols() != a.length) {
			throw new Exception();
		}
		for (int j = 0; j < matrix[row].length; ++j) {
			matrix[row][j] = a[j];
		}
	}

	/**
	 * Update a column in the matrix with values from an array
	 * @param col the column to update
	 * @param a the array
	 */
	public void setCol(int col, int[] a) throws Exception {
		if (a == null || numRows() != a.length) {
			throw new Exception();
		}
		for (int i = 0; i < numRows(); ++i) {
			matrix[i][col] = a[i];
		}
	}

	/**
	 * Equals function for matrices based on value
	 * @param m the matrix to be compared with
	 * @return returns true if each value at position i,j is same, false otherwise
	 */
	public boolean equals(Matrix m) {
		for (int i = 0; i < numRows(); ++i) {
			for (int j = 0; j < numCols(); ++j) {
				if (this.getPos(i, j) != m.getPos(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Copies a matrix into a new matrix
	 * @return returns a new matrix
	 */
	public Matrix copy() {
		Matrix c = new Matrix(numRows(), numCols());
		for (int i = 0; i < numRows(); ++i) {
			for (int j = 0; j < numCols(); ++j) {
				c.setPos(i, j, this.getPos(i, j));
			}
		}
		return c;
	}

	public Matrix multiplyMatrix(Matrix m) {
		return multiplyMatrix(this, m);
	}

	public static Matrix multiplyMatrix(Matrix a, Matrix b) {
		if (!validMatrices(a, b)) {
			multiplicationImpossible();
		}
		Matrix c = new Matrix(a.numRows(), b.numCols());
		int v = 0;
		for (int i = 0; i < a.numRows(); ++i) {
			for (int j = 0; j < b.numCols(); ++j) {
				for (int k = 0; k < a.numCols(); ++k) {
					v += a.getPos(i, k) * b.getPos(k, j);
					c.setPos(i, j, v);
					
				}
				v = 0;
			}
		}
		return c;
	}

	public Matrix powerMatrix(int p) {
		return powerMatrix(this, p);
	}

	public static Matrix powerMatrix(Matrix m, int p) {
		if (!checkPower(p)) {
			invalidPower();
		}
		Matrix c = new Matrix(m.numRows());
		for (int n = 1; n <= p; ++n) {
			c = multiplyMatrix(m, m);
			
		}
		return c;
	}

	public Matrix pointMultiplication(Matrix t) {
		Matrix m = new Matrix(numRows(), numCols());
		int v = 0;
		for (int i = 0; i < numRows(); ++i) {
			for (int j = 0; j < numCols(); ++j) {
				v *= this.getPos(i ,j);
				m.setPos(i, j, v);
			}
		}
		return m;
	}

	public Matrix pointPower(int p) {
		if (!checkPower(p)) {
			invalidPower();
		}
		Matrix m = new Matrix(numRows(), numCols());
		m.setAll(1);
		int v = 0;
		for (int i = 0; i < numRows(); ++i) {
			for (int j = 0; j < numCols(); ++j) {
				for (int k = 0; k < p; ++k) {
					v = m.getPos(i, j) * this.getPos(i, j);
					m.setPos(i, j, v);
				}
			}
		}
		return m;
	}

	/**
	 * Checks if an array is in matrix format. 
	 * @param a the array to be checked
	 * @return returns true if all rows are equal, false otherwise
	 */
	static boolean isMatrix(int[][] a) {
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[i].length; ++j) {
				if(!equalRows(a, i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Helper function for isMatrix(). 
	 * @param a the array to be checked
	 * @param row the row number
	 * @param col the column number
	 * @return returns true if all rows are equal, false otherwise
	 */
	private static boolean equalRows(int[][] a, int row, int col) {
		for (int i = 0; i < a.length; i++) {
			if (a[row][i] != a[i][col])
				return false;
		}
		return true;
	}

	static boolean validMatrices(Matrix a, Matrix b) {
		if (a.numCols() != b.numRows()) {
			return false;
		}
		return true;
	}

	static boolean checkPower(int p) {
		return p < 0 ? false : true;
	}

	/**
	 * Exception if a given array does not comply with the matrix format
	 */
	public static void arrayIsNoMatrix() {
		throw new ArithmeticException("Supplied array is not a matrix.");
	}

	/**
	 * Exception if a matrix multiplication is not possible
	 */
	public static void multiplicationImpossible() {
		throw new ArithmeticException("Matrix multiplication impossible.");
	}

	/**
	 * Exception if a negative value for power is supplied
	 */
	public static void invalidPower() {
		throw new ArithmeticException("No negative power values allowed.");

	}

	/**
	 * String output of a given matrix. 
	 * @return returns the matrix as string
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(numRows());
		s.append("x");
		s.append(numCols());
		s.append(" Matrix\n\n");
		for (int i = 0; i < numRows(); ++i) {
			for (int j = 0; j < numCols(); ++j) {
				s.append(String.format("%8d", getPos(i, j)));
			}
			s.append("\n");
		}
		return s.toString();
	}
}