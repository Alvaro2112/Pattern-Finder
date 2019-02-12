package main;

public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * 
	 * @param patternPixel
	 *            : a integer, the second RGB pixel.
	 * @param imagePixel
	 *            : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer
	 *         in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {

		double Red = Math.abs(ImageProcessing.getRed(patternPixel) - ImageProcessing.getRed(imagePixel));
		double Blue = Math.abs(ImageProcessing.getBlue(patternPixel) - ImageProcessing.getBlue(imagePixel));
		double Green = Math.abs(ImageProcessing.getGreen(patternPixel) - ImageProcessing.getGreen(imagePixel));
		double pixelAbsoluteError = (Red + Green + Blue) / 3;

		return pixelAbsoluteError;

	}

	/**
	 * Computes the mean absolute error loss of a RGB pattern if positioned at the
	 * provided row, column-coordinates in a RGB image
	 * 
	 * @param row
	 *            : a integer, the row-coordinate of the upper left corner of the
	 *            pattern in the image.
	 * @param column
	 *            : a integer, the column-coordinate of the upper left corner of the
	 *            pattern in the image.
	 * @param pattern
	 *            : an 2D array of integers, the RGB pattern to find
	 * @param image
	 *            : an 2D array of integers, the RGB image where to look for the
	 *            pattern
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between
	 *         the pattern and the part of the base image that is covered by the
	 *         pattern, if the pattern is shifted by x and y. should return -1 if
	 *         the denominator is -1
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {

		assert row >= 0 && col >= 0;

		assert image != null && image.length > 0 && image[0].length > 0;

		assert pattern != null && pattern.length > 0 && pattern[0].length > 0;

		int l = pattern.length;
		int h = pattern[0].length;

		double Adition = 0;
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < h; j++) {

				Adition = Adition + pixelAbsoluteError(pattern[i][j], image[i + row][j + col]);

			}

		}

		return Adition / (h * l);
	}

	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * 
	 * @param pattern
	 *            : an 2D array of integers, the RGB pattern to find
	 * @param image
	 *            : an 2D array of integers, the RGB image where to look for the
	 *            pattern
	 * @return a 2D array of doubles, containing for each pixel of a original RGB
	 *         image, the distance (meanAbsoluteError) between the image's window
	 *         and the pattern placed over this pixel (upper-left corner)
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image) {

		assert image != null && image.length > 0 && image[0].length > 0;

		assert pattern != null && pattern.length > 0 && pattern[0].length > 0;
		
		
		int img_l = image.length;
		int img_h = image[0].length;
		int pattern_l = pattern.length;
		int pattern_h = pattern[0].length;
		double[][] distance = new double[Math.abs(pattern_l - img_l)][Math.abs(pattern_h - img_h)];
		int Max_i = Math.abs(pattern_l - img_l);
		int Max_j = Math.abs(pattern_h - img_h);
		
		for (int i = 0; i < Max_i; i++) {
			for (int j = 0; j < Max_j; j++) {
				distance[i][j] = meanAbsoluteError(i, j, pattern, image);

			}

		}
		return distance;
	}
}