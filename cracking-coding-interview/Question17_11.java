import java.util.*;
import java.util.concurrent.*;

public class Question17_11{
	public static void main(String[] args){
		Rand rand = new Rand(5, 7);
		System.out.println(rand.nextInt());
	}
}
class Rand{
	private int[][] randTable;
	private int from;
	Rand(int from, int target){
                if (from*from < target) throw new IllegalArgumentException();
		this.from = from;
		randTable = genRandTable(from, target);
		print(randTable);
	}
	private void print(int[][] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j<arr[i].length; j++){
				System.out.print(String.format("%02d ",arr[i][j]));
			}
			System.out.println();
		}
	}
	private int[][] genRandTable(int from, int target){
		int[][] ret = new int[from][];
		for(int i = 0; i < from; ++i){
			ret[i] = new int[from];
			for(int j = 0; j < from; ++j){
				ret[i][j] = -1;
			}
		}
		for(int i = 0, j = 0, cnt = 0; i < from && j < target; ++i){
			for(int k = 0; k < from && j < target; ++k){
				ret[i][k] = j;
				++cnt;
				if (cnt == (from*from)/target){
					++j;
					cnt = 0;
				}
			}
		}
		return ret;
	}
	public int nextInt(){
		int ret = -1;
		while(ret < 0){
			ret = randTable[rand(from)][rand(from)];
		}
		return ret;
	}
	private int rand(int from){
		return ThreadLocalRandom.current().nextInt(from);
	}
}
