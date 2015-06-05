public class Question77{
	public static void main(String[] args){
                System.out.println(kthNumber(1));
                System.out.println(kthNumber(2));
                System.out.println(kthNumber(3));
                System.out.println(kthNumber(4));
		System.out.println(kthNumber(5));
                System.out.println(kthNumber(6));
                System.out.println(kthNumber(7));
                System.out.println(kthNumber(8));
	}
	public static int kthNumber(int n){
		int[] factors = new int[]{3, 5, 7};
		int i = 2;
		for(int cnt = 0; cnt < n; ++i){
			int t = i; int factorCount = 0;
			for(int j = 0; j < factors.length && t%factors[j] == 0; ++j, ++factorCount){
				while(t%factors[j]==0){
					t/=factors[j];
				}
			}
			if (t==1 && factorCount == factors.length) ++cnt;
		}
		return i-1;
	}
}
