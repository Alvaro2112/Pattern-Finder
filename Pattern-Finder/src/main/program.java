package main;

public class program {
	public static void main(String[] args) {

		int[][] image = Helper.read("images/charlie_beach.png");
		int[][] head = Helper.read("images/charlie.png");

	}

	public static void distancemat(int[][] tete, int[][] fond) {
		
		
		double[][] distance = DistanceBasedSearch.distanceMatrix(tete, fond);
		
		int[] mat = Collector.findBest(distance, true);
		
		Helper.drawBox(mat[0], mat[1], tete[0].length, tete.length, fond);
		
		Helper.show(fond, "the red rectangle is where he is");
	}

}


