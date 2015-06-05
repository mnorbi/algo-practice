public class Question5_5{
	public static void main(String[] args){
		NumBits nb = new NumBits();
		System.out.println(nb.numBits(31, 14));
	}
}
class NumBits{
	public int numBits(int a, int b){
		int c = a^b;
		int ret = 0;
		while(c > 0){
			if ((c & 1) >= 1) ++ret;
			c = c >> 1;
		}
		return ret;
	}
}
