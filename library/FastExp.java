class FastExp{
  	public static void main(String[]args){
	    System.out.println(fastExp(123,4));
  	}
	
	public static int fastExp(int base, int exp){
  	int ret = 1;
  	while(exp > 0){
	    if ((exp & 1)==1) ret *= base;
	    exp = exp >> 1;
	    base *= base;
  	}
  	return ret;
	}
}
