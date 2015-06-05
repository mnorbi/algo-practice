public class Srm169Div1Lev2BinSearch{
        public static void main(String[] args){
                FairWorkload fw = new FairWorkload();
		System.out.println(fw.getMostWork(new int[] { 1, 1 }, 1));
                System.out.println(fw.getMostWork(new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90 }, 3));
		System.out.println(fw.getMostWork(new int[] { 568, 712, 412, 231, 241, 393, 865, 287, 128, 457, 238, 98, 980, 23, 782 }, 4));
		System.out.println(fw.getMostWork(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1000}, 2));
		System.out.println(fw.getMostWork(new int[] { 50, 50, 50, 50, 50, 50, 50 }, 2));
		System.out.println(fw.getMostWork(new int[] { 1, 1, 1, 1, 100}, 5));
		System.out.println(fw.getMostWork(new int[] { 950, 650, 250, 250, 350, 100, 650, 150, 150, 700 }, 6));
        }
}
class FairWorkload{
	public int getMostWork(int[] folders, int workers){
		int lo = max(folders);//workers = folders.length
		int hi = sum(folders);//workers = 1
		while(lo < hi){
			int mid = (hi+lo) >>> 1;
			int w = minWorkerCount(folders, mid);
			if (w > workers){
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		return lo;
	}

	int max(int[] in){
		int ret = in[0];
		for(int i : in){
			ret = Math.max(ret, i);
		}
		return ret;
	}

	int sum(int[] in){
		int ret = 0;
		for(int i : in){
			ret+=i;
		}
		return ret;
	}

	int minWorkerCount(int[] folders, int maxWork){
		int ret = 1;
		for(int i = 0, batch = 0; i < folders.length; i++){
			batch += folders[i];
			if (batch > maxWork){
				ret++;
				batch = folders[i];
			}
		}
		return ret;
	}
}
