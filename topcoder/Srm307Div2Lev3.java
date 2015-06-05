public class Srm307Div2Lev3{
	public static void main(String[] args){
		PreprimeNumbers ppm = new PreprimeNumbers();
		System.out.println(ppm.nthNumber(2));
                System.out.println(ppm.nthNumber(4));
                System.out.println(ppm.nthNumber(24));
                System.out.println(ppm.nthNumber(43765));
	}
}
class PreprimeNumbers{
	int nthNumber(int n){
		int[] div = new int[6*1000000];
		int i = 2;
		for(int cnt = 0; cnt < n; ++i){
			if (div[i] == 0){//if i is prime
				for(int j = 2*i; j < div.length; j+=i){//sieve
					int t = j;
					while(t%i == 0){
						++div[j];
						t/=i;
					}
					if (t == 1){//i has form prime^x
						--div[j];//this way prime^3 will have div[i] = 2, that matches prime1*prime2 form
					}
				}
			} else if (div[i] == 2){
				++cnt;
                        }
 
		}
		return i-1;
	}
}
