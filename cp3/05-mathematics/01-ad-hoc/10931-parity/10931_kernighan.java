import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb2 = new StringBuilder();
    while(true){
      int next = Integer.parseInt(in.readLine().trim());
      if (next == 0) break;
      sb2.append(kernighanParity(next)).append('\n');
    }
    System.out.print(sb2);
  }
  private static final int[] hammingArr = new int[]{
  	0b01010101010101010101010101010101, 
  	0b00110011001100110011001100110011,
  	0b00001111000011110000111100001111,
  	0b00000000111111110000000011111111,
  	0b00000000000000001111111111111111
  };
  private static int kernighanParity(int val){
    int ret = 0;
    while(val != 0){
      ++ret;
      val -= val & (-val);
    }
    return ret;
  }
  private static int hammingParity(int val){
    for(int i = 0; i < hammingArr.length; ++i){
      val = (val & hammingArr[i]) + (val & (hammingArr[i] << (1<<i)));
    }
    return val;
  }
}
