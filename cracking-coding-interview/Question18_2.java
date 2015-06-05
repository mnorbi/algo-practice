import java.util.concurrent.*;
import java.util.*;
public class Question18_2{
	public static void main(String[] args){
		print(shuffledDeck());
	}
	
	private static void print(int[]arr){
		for(int i = 0; i< arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	private static int[] shuffledDeck(){
		int[] ret = new int[51];
		for(int i = 0; i < ret.length; i++){
			ret[i] = i;
		}
		for(int i = 1; i < ret.length; i++){
			swap(ret, i, rand(i+1));
		}
		return ret;
	}

	private static void swap(int[] arr, int i, int j){
		if (i == j) return;
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	private static int rand(int end){
		return ThreadLocalRandom.current().nextInt(end);
	}
}
