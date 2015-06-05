class CanoeCount_CareerCup_6303093824159744{
	public static void main(String[]args){
		System.out.println(count(new int[]{100, 145, 33, 80, 9, 60, 30, 20}));
	}
	public static int count(int[] weights){
	  int[] arr = new int[151];
	  for(int w : weights){
	    ++arr[w];
	  }
	  int i = 0;
	  int j = arr.length-1;
	  int ret = 0;
	  for(;;){
	    for(; i < j && arr[i] == 0; ++i);
	    for(; j > i && arr[j] == 0; --j);
	    if (i >= j) break;
	    if (i+j <= 150){
	      --arr[i];
	      --arr[j];
	    } else {
	      --arr[j];
	    }
	    ++ret;
	  }
	
        if (i == j) {
            if (i*2 <= 150){
                ret += arr[i]/2;
                ret += arr[i]%2;
            } else {
                ret += arr[i];
            }
        }
	
	  return ret;
	}

}