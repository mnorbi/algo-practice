//- array +, - integers
//- contiguous seq with largest sum
import java.util.*;
public class Question7_2{
	public static void main(String[] args){
		LargestContSum solver = new LargestContSum();
		int[] arr;
		Pair pair = solver.find(arr = new int[]{2, -8, 3, -2, 4, -10});
		print(arr, pair);
	}
	private static void print(int[] arr, Pair range){
		for(int i = range.s; i <= range.e; i++){
			System.out.print(arr[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
}
class LargestContSum{
	Pair find(int[] arr){
		if (arr == null || arr.length == 0) throw new IllegalArgumentException();
		int maxSum = arr[0]; int maxSeqStart = 0, maxSeqEnd = 0;
		for(int i = 0, sum = 0, seqS = 0; i < arr.length; i++){
			sum += arr[i];
			if (sum > maxSum){
				maxSum = sum;
				maxSeqStart = seqS;
				maxSeqEnd = i;
			}
			if (sum < 0){
				sum = 0;
				seqS = i+1;
			}
		}
		return new Pair(maxSeqStart, maxSeqEnd); 
	}
}
class Pair{
	final Integer s;
	final Integer e;
	Pair(Integer s, Integer e){
		this.s = s;
		this.e = e;
	}
}
