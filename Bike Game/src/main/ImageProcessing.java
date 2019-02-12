package main;

public final class ImageProcessing {

	/**
	 * Returns red component from given packed color.
	 * 
	 * @param rgb
	 *            : a 32-bits RGB color
	 * @return an integer, between 0 and 255
	 * @see #getGreen
	 * @see #getBlue
	 * @see #getRGB(int, int, int)
	 */
	public static int getRed(int rgb) {

		int red = (rgb >> 16) & 0b11111111;

		// TODO implement me !
		return red;
	}

	/**
	 * Returns green component from given packed color.
	 * 
	 * @param rgb
	 *            : a 32-bits RGB color
	 * @return an integer between 0 and 255
	 * @see #getRed
	 * @see #getBlue
	 * @see #getRGB(int, int, int)
	 */
	public static int getGreen(int rgb) {

		int green = (rgb >> 8) & 0b00001111;

		// TODO implement me !
		return green;
	}

	/**
	 * Returns blue component from given packed color.
	 * 
	 * @param rgb
	 *            : a 32-bits RGB color
	 * @return an integer between 0 and 255
	 * @see #getRed
	 * @see #getGreen
	 * @see #getRGB(int, int, int)
	 */
	public static int getBlue(int rgb) {

		int blue = rgb  & 0b01010101;
		// TODO implement me !
		return blue;
	}

	/**
	 * Returns the average of red, green and blue components from given packed
	 * color.
	 * 
	 * @param rgb
	 *            : 32-bits RGB color
	 * @return a double between 0 and 255
	 * @see #getRed
	 * @see #getGreen
	 * @see #getBlue
	 * @see #getRGB(int)
	 */
	public static double getGray(int rgb) {

		double gray = (getGreen(rgb) + getBlue(rgb) + getRed(rgb)) / 3;
		// TODO implement me !
		return gray;
	}

	/**
	 * Returns packed RGB components from given red, green and blue components.
	 * 
	 * @param red
	 *            : an integer
	 * @param green
	 *            : an integer
	 * @param blue
	 *            : an integer
	 * @return a 32-bits RGB color
	 * @see #getRed
	 * @see #getGreen
	 * @see #getBlue
	 */
	public static int getRGB(int red, int green, int blue) {
	 
		int rgb = red + (green << 8) +( blue << 16);
		return rgb;
	}

	/**
	 * Returns packed RGB components from given gray-scale value.
	 * 
	 * @param gray
	 *            : a double
	 * @return a 32-bits RGB color
	 * @see #getGray
	 */
	public static int getRGB(double gray) {

		int rgb = getRGB((int) gray, (int) gray, (int) gray);
		// TODO implement me !
		return rgb;
	}

	/**
	 * Converts packed RGB image to gray-scale image.
	 * 
	 * @param image
	 *            : a HxW integer array
	 * @return a HxW double array
	 * @see #encode
	 * @see #getGray
	 */
	public static double[][] toGray(int[][] image) {
		assert image != null;

		int l = image.length;
		int h = image[0].length;
		double[][] imagegrey = new double[l][h];
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < h; j++) {

				imagegrey[i][j] = getGray(image[i][j]);
			}
	}

		// TODO implement me !
		return imagegrey;
	}

	/**
	 * Converts gray-scale image to packed RGB image.
	 * 
	 * @param channels
	 *            : a HxW double array
	 * @return a HxW integer array
	 * @see #decode
	 * @see #getRGB(double)
	 */
	public static int[][] toRGB(double[][] gray) {
		assert gray != null && gray.length > 0 && gray[0].length > 0;

		int l = gray.length;
		int h = gray[0].length;
		int[][] imageRGB = new int[l][h];
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < h; j++) {

				imageRGB[i][j] = getRGB(gray[i][j]);
			}
			// TODO implement me !

		}
		return imageRGB;
	}

	/**
	 * Convert an arbitrary 2D double matrix into a 2D integer matrix which can be
	 * used as RGB image
	 * 
	 * @param matrix
	 *            : the arbitrary 2D double array to convert into integer
	 * @param min
	 *            : a double, the minimum value the matrix could theoretically
	 *            contains
	 * @param max
	 *            : a double, the maximum value the matrix could theoretically
	 *            contains
	 * @return an 2D integer array, containing a RGB mapping of the matrix
	 */
	public static int[][] matrixToRGBImage(double[][] matrix, double m, double M) {
		int[][] RGB = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length ; j++) {
				double g = ((matrix[i][j] - m) / (M - m)) * 255;
				RGB[i][j] = getRGB(g);
			}
		}

		// TODO implement me !
		return RGB;
	}
}
