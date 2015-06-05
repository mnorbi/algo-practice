import java.util.*;

class Question117{
	static class Size{ 
		int height;
		int weight;

		Size(int height, int weight){
			this.height = height;
			this.weight = weight;
			validate();
		}

		void validate(){
			if (height <=0 || height >= 300) throw new IllegalArgumentException("Invalid height " + height);
			if (weight <=0 || weight >= 500) throw new IllegalArgumentException("Invalid weight " + weight);
		}

		static Size of(int height, int weight){
			return new Size(height, weight);
		}
		
		public String toString(){
			return String.format("(%d,%d)", height, weight);
		}
	}

	public static void main(String[] args){
		Size[] maxTower = maxTowerSize(new Size[]{
			Size.of(1, 200),
			Size.of(65, 100),
			Size.of(70, 150),
			Size.of(56, 90),
			Size.of(75, 190),
			Size.of(60, 95),
			Size.of(68, 110),
			Size.of(74, 189)
		});
		for(Size s : maxTower){
			System.out.print(s);
			System.out.print(" ");
		}
		System.out.println();
	}

	static Size[] maxTowerSize(Size[] sizeArr){
		Arrays.sort(sizeArr, increasingWeightTiesByHeight);
		return maxIncreasingHeightSubseq(sizeArr);
	}

	static Size[] maxIncreasingHeightSubseq(Size[] sizeArr){
		if (sizeArr == null || sizeArr.length <= 1) return sizeArr;

		Size[] dp = new Size[sizeArr.length + 1];
		Map<Size, Size> parent= new HashMap<Size, Size>();
		int len = 0;
		Size tail = null;
		for(Size s : sizeArr){
			int idx = binSearchCeilingIdx(dp, len, s);
			dp[idx] = s;
                        if (len < idx) {
				len = idx;
				tail = s;
			}
			parent.put(s, dp[idx-1]);
 		}
		return chain(tail, len, parent);
	}

	static Size[] chain(Size tail, int len, Map<Size, Size> parent){
                Size[] ret = new Size[len];
		do {
			ret[--len] = tail;
			tail = parent.get(tail); 	
		} while(tail != null);

		return ret;
	}

	static int ceilingIdx(Size[] dp, Size s1){
		for(int i = 1; i < dp.length; i++){
			Size s2 = dp[i];
			if (s2 == null || increasingHeightTiesByWeight.compare(s2, s1) > 0){
				return i;
			}
		}
		return -1;
	}

	static int binSearchCeilingIdx(Size[] dp, int len, Size s1){
		int from = 1, to = from + len;
		int mid = from;
		while(from <= to){
			mid = (from+to) >>> 1;
			Size s2 = dp[mid];
			if (s2 == null || increasingHeightTiesByWeight.compare(s2, s1) > 0){
				to = mid-1;
			} else {
				from = mid+1;
			}
		}
		Size s2 = dp[mid];
		if (s2 == null || increasingHeightTiesByWeight.compare(s2, s1) > 0){
			return mid;
		}
		return mid+1;
	}

	static final Comparator<Size> increasingWeightTiesByHeight = new Comparator<Size>(){
		public int compare(Size s1, Size s2){
                	int ret = Integer.compare(s1.weight, s2.weight);
	                if (ret == 0){
		                ret = Integer.compare(s1.height, s2.height);
	                }
	                return ret;
                }
	};

	static final Comparator<Size> increasingHeightTiesByWeight = new Comparator<Size>(){
		public int compare(Size s1, Size s2){
			int ret = Integer.compare(s1.height, s2.height);
			if (ret == 0){
				ret = Integer.compare(s1.weight, s2.weight);
			}
			return ret;
		}
	};

}
