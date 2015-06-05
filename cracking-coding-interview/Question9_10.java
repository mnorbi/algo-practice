import java.util.*;

public class Question9_10{
	public static void main(String[]args){
		System.out.println(findMaxHeight(new Box[]{
			new Box(10,10,10),
			new Box(8,8,8),
			new Box(9,9,9)
		}));
	}
	private static int findMaxHeight(Box[] boxes){
		Arrays.sort(boxes, Box.DESCENDING_VOLUME_COMPARATOR);
		int[] dp = new int[boxes.length];
		int overallMax = Integer.MIN_VALUE;
		for(int i = 0; i < boxes.length; ++i){
			Box nextBox = boxes[i];
			int max = nextBox.height;
			for(int k = 0; k < i; ++k){
				if (boxes[k].isAllDimensionsStrictlyLargerThan(nextBox)){
					max = Math.max(max, dp[k]+nextBox.height);
				}
			}
			dp[i] = max;
			overallMax = Math.max(overallMax, max);
		}
		return overallMax; 
	}
}
class Box{
	static final Comparator<Box> DESCENDING_VOLUME_COMPARATOR = new Comparator<Box>(){
                public int compare(Box a, Box b){
                        return -1*Integer.compare(a.volume, b.volume);
                }
        };
	final int width, height, depth, volume;

	public Box(int width, int height, int depth){
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.volume = this.width*this.height*this.depth;
	}
	
	public boolean isAllDimensionsStrictlyLargerThan(Box other){
		if (other.width >= this.width){
			return false;
		}
		if (other.height >= this.height){
			return false;
		}
		if (other.depth >= this.depth){
			return false;
		}
		return true;
	}
}
