public class Question116{
	public static void main(String[] args){
		System.out.println(
			matrixSearch(new int[][]{
				{1,    10,  100,  1000,  10000},
				{9,    90,  900,  9000,  90000},
				{81,  729, 7290, 72900, 729000},
				{810,8100,81000,810000,7290000}
			}, 1001));
	}

	private static class Pair{
		private static final Pair NOT_FOUND = new Pair(-1,-1);
		private final int x, y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}

		public String toString(){
			return String.format("(%d, %d)", x, y); 
		}
	}

	public static Pair matrixSearch(int[][] matrix, int val){
		if (matrix == null || matrix.length == 0) return Pair.NOT_FOUND;
		int n = matrix.length;
		int m = matrix[0].length;
		for(int i = n-1, j = 0; i >= 0 && j < m;){
			System.out.println(String.format("(%d, %d): %d", i, j, matrix[i][j]));
			if (matrix[i][j] < val){
				j++;
			} else if (matrix[i][j] > val){
				i--;
			} else {
				return new Pair(i, j);
			}
		}
		return Pair.NOT_FOUND;
	}
}
