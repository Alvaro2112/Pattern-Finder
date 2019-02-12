package main;

public class SimilarityBasedSearch {

	static double windowMean(double[][] matrix, int row, int col, int width, int height) {

		double moyenne = 0;
		for (int a = row; a < width + row; a++) {
			for (int b = col; b < col + height; b++) {
			     moyenne += matrix[a][b];
			} }
		moyenne = moyenne / (height * width);

		return moyenne;
	}

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array
	 * 
	 * @param image
	 *            : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double mean(double[][] image) {

		double moyenne = 0;
		double n = image.length * image[0].length;
		for (int a = 0; a < image.length; a++) {
			for (int b = 0; b < image[0].length; b++) {
				moyenne += image[a][b];
			}
		}
		moyenne = moyenne / n;

		return moyenne;
	}

	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if
	 * positioned at the provided row, column-coordinate in a gray-scale image
	 * 
	 * @param row
	 *            : a integer, the row-coordinate of the upper left corner of the
	 *            pattern in the image.
	 * @param column
	 *            : a integer, the column-coordinate of the upper left corner of the
	 *            pattern in the image.
	 * @param pattern
	 *            : an 2D array of doubles, the gray-scale pattern to find
	 * @param image
	 *            : an 2D array of double, the gray-scale image where to look for
	 *            the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row,
	 *         col) between the pattern and the part of the base image that is
	 *         covered by the pattern, if the pattern is shifted by x and y. should
	 *         return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		double numerateur = 0;
		double deno1 = 0;
		double deno2 = 0;
		double denocomplet = 0;
		double NCC = 0;

		double W = windowMean(image, row, col, pattern.length, pattern[0].length);
		double M = mean(pattern);

		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[0].length; j++) {

				numerateur += (image[row + i][col + j] - W) * (pattern[i][j] - M);
				deno1 += Math.pow((image[row + i][col + j] - W), 2);
				deno2 += Math.pow((pattern[i][j] - M), 2);
				
			}
		}

		denocomplet = deno1 * deno2;
		denocomplet = Math.sqrt(denocomplet);
		if (denocomplet == 0) {
			return -1;
		}
			NCC = numerateur / denocomplet;
			return NCC;
		}
	
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale
	 * pattern
	 * 
	 * @param pattern
	 *            : an 2D array of doubles, the gray-scale pattern to find
	 * @param image
	 *            : an 2D array of doubles, the gray-scale image where to look for
	 *            the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original
	 *         gray-scale image, the similarity (normalized cross-correlation)
	 *         between the image's window and the pattern placed over this pixel
	 *         (upper-left corner)
	 */

	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale
	 * pattern
	 * 
	 * @param pattern
	 *            : an 2D array of doubles, the gray-scale pattern to find
	 * @param image
	 *            : an 2D array of doubles, the gray-scale image where to look for
	 *            the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original
	 *         gray-scale image, the similarity (normalized cross-correlation)
	 *         between the image's window and the pattern placed over this pixel
	 *         (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {

		assert image != null && image.length > 0 && image[0].length > 0;

		assert pattern != null && pattern.length > 0 && pattern[0].length > 0;

		int l = image.length - pattern.length;
		int h = image[0].length - pattern[0].length;

		double[][] similarityMatrix = new double[l][h];

		for (int i = 0; i < l; i++) {
			for (int j = 0; j < h; j++) {

				similarityMatrix[i][j] = normalizedCrossCorrelation(i, j, pattern, image);
			}
		}

		return similarityMatrix;

	}

}
