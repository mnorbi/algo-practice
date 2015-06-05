public class Question_6_5{
	public static void main(String[]args){
//		find(new int[]{429, 334, 62, 711, 704, 763, 98, 733, 721, 995});
		find(new int[]{9,9,9,9,9,9,9,9,9,8});
		find(new int[]{2, 2, 3, 4, 1, 1});
	}
	private static void find(int[] arr){
		int N = arr.length;
		int[] pfxSum = new int[N];
		pfxSum[0] = arr[0]%N;
		for(int i = 1; i < N; ++i){
			pfxSum[i] = (pfxSum[i-1]+arr[i]%N)%N;
		}
		int[] map = new int[N];
		for(int i = 0; i < N; ++i){
			map[i] = -1;
		}
		for(int i = 0; i < N; ++i){
			if (pfxSum[i] == 0){
				print(arr, 0, i);
				return;
			} else if (map[pfxSum[i]] == -1){
				map[pfxSum[i]] = i;
			} else {
				print(arr, map[pfxSum[i]]+1, i);
				return;
			}
		}
	}
	private static void print(int[] arr, int from, int to){
		for(int i = from; i <= to; ++i){
			System.out.printf(" %d", arr[i]);
		}
		System.out.println();
	}
}
