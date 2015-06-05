public class Question5_2{
	public static void main(String[] args){
		printBinary(0.5);
	}
	private static void printBinary(double d){
		for(int i = 0; i < 32 && d > 0; i++){
			d = d*2;
			if (d >= 1){
				System.out.print(1);
				d-=1;
			} else {
				System.out.print(0);
			}
		}
		System.out.println();
		System.out.println(d);
		if (Double.compare(d, 0.0) != 0){
			System.out.println("ERROR");
		}
	}
}
