package main;

import java.util.ArrayList;

public class Collector {

	/**
	 * Find the row, column coordinates of the best element (biggest or smallest)
	 * for the given matrix
	 * 
	 * @param matrix
	 *            : a 2D array of doubles
	 * @param smallestFirst
	 *            : a boolean, indicates if the smallest element is the best or not
	 *            (biggest is then the best)
	 * @return an array of two integer coordinates, row first and then column
	 */
	public static int[] findBest(double[][] matrix, boolean smallestFirst) {
		assert 1 <= matrix.length;
		assert 1 <= matrix[0].length;

		double mat = matrix[0][0];
		int[] smallest = new int[] { 0, 0 };
		int[] biggest = new int[] { 0, 0 };

		

				if (smallestFirst) {
					for (int a = 0; a < matrix.length; ++a) {
					for (int b = 0; b < matrix[0].length; ++b) {

					if (matrix[a][b] < mat) {
						smallest[0] = a;
						smallest[1] = b;
						mat = matrix[a][b];
					}

					 }}return smallest;
				} else {
					for (int a = 0; a < matrix.length; ++a) {
						for (int b = 0; b < matrix[0].length; ++b) {

					if (matrix[a][b] > mat) {

						biggest[0] = a;
						biggest[1] = b;
						mat = matrix[a][b];
					}

				}
			}

					return biggest;	}
		
	}

	/**
	 * Find the row, column coordinate-pairs of the n best (biggest or smallest)
	 * elements of the given matrix
	 * 
	 * @param n
	 *            : an integer, the number of best elements we want to find
	 * @param matrix
	 *            : an 2D array of doubles
	 * @param smallestFirst
	 *            : a boolean, indicates if the smallest element is the best or not
	 *            (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBest(int n, double[][] matrix, boolean smallestFirst) {
		assert 1 <= matrix.length;
		assert 1 <= matrix[0].length;

		int[][] BestN = new int[n][2];
		double[][] matrix_copy = new double[matrix.length][matrix[0].length];

		for (int a = 0; a < matrix.length; ++a) {
			for (int b = 0; b < matrix[0].length; ++b) {
				matrix_copy[a][b] = matrix[a][b];
			}
		}

		if (smallestFirst) {
			for (int a = 0; a < n; ++a) {
				BestN[a][0] = findBest(matrix_copy, smallestFirst)[0];
				BestN[a][1] = findBest(matrix_copy, smallestFirst)[1];
				matrix_copy[findBest(matrix_copy, smallestFirst)[0]][findBest(matrix_copy,
						smallestFirst)[1]] = Double.POSITIVE_INFINITY;
			}
		} else {
			for (int a = 0; a < n; ++a) {
				BestN[a][0] = findBest(matrix_copy, smallestFirst)[0];
				BestN[a][1] = findBest(matrix_copy, smallestFirst)[1];
				matrix_copy[findBest(matrix_copy, smallestFirst)[0]][findBest(matrix_copy,
						smallestFirst)[1]] = Double.NEGATIVE_INFINITY;
			}
		}

		return BestN;
	}

	
}
