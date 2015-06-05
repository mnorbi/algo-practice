public class Question5_6{
	public static void main(String[] args){
		SwapBits sb = new SwapBits();
		System.out.println(sb.swapBits(2+4));
	}
}
class SwapBits{
	public int swapBits(int i){
		return ((i & 0x55555555) << 1) | ((i & 0xaaaaaaaa) >> 1);
	}
}
