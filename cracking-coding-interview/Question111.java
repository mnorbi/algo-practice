class Question111{
	public static void main(String[] args){
		int[] ret = merge(
			new int[] { 1, 3, 5, 7, 9, 0, 0, 0, 0, 0}, 
			5,
		     	new int[] { 2, 4, 6, 8, 10});
		for(int i : ret){
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	private static int[] merge(int[] a, int aLength, int[] b){
		if (a == null || b == null || b.length == 0) return a;
		if (a.length - aLength < b.length) throw new IllegalArgumentException("...");
		int targetIdx = aLength + b.length - 1, aIdx = aLength -1, bIdx = b.length -1; 
		while(aIdx >=0 && bIdx >=0){
			if (a[aIdx] > b[bIdx]){
				a[targetIdx--] = a[aIdx--];
			} else {
				a[targetIdx--] = b[bIdx--];
			}
		}
		while(aIdx >=0){
			a[targetIdx--] = a[aIdx--];
		}
		while(bIdx >=0){
			a[targetIdx--] = b[bIdx--];
		}
		return a;
	}
}
