class MergeIntervals{
	public static void main(String[] args){
		java.util.List<Interval> ivals = new MergeIntervals().merge(new int[]{2,4,1,7,8,10,9,13});
		print(ivals);
	}

	static void print(java.util.List<Interval> arr){
		for(Interval o: arr){
			System.out.println(o);
		}
	}

	static void print(Interval[] arr){
                for(Interval o: arr){
                        System.out.println(o);
                }
        }
	
	static class Interval{
		int start;
		int end;
		static Interval of(int start, int end){
			Interval ret = new Interval();
			ret.start = start;
			ret.end = end;
			return ret;
		}
		public String toString(){
			return String.format("[%d %d]", start, end);
		}
	}

	java.util.List<Interval> merge(int[] in){
		Interval[] ivals = new Interval[in.length/2];
		for(int i = 0; i<ivals.length;i++){
			ivals[i] = Interval.of(in[2*i], in[2*i+1]);
		}
		java.util.Arrays.sort(ivals, new java.util.Comparator<Interval>(){
			public int compare(Interval a, Interval b){
				return Integer.compare(a.start, b.start);
			}
		});

		int start = ivals[0].start;
		int end = ivals[0].end;
		java.util.List<Interval> merged = new java.util.ArrayList<Interval>();
		for(int i = 1; i<ivals.length; i++){
			Interval ival = ivals[i];
			if (ival.start <= end){
				end = Math.max(end, ival.end);
			} else {
				merged.add(Interval.of(start,end));
				start = ival.start;
				end = ival.end;
			}
		}
		merged.add(Interval.of(start, end));
		return merged;
	}
}
