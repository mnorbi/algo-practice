import java.util.*;

public class Srm404Div2Lev3 {

	public static void main(String[] args){
		Srm404Div2Lev3 solver =  new Srm404Div2Lev3();
		int ret = solver.collectSweets(
			2,
			new int[]{2, 9, 9, 1, 9, 9, 8},
			new int[]{10, 8, 6, 6, 8, 7, 3},
			new int[]{6, 7, 5, 4, 5, 2, 5},
			new int[]{1, 1, 1, 1, 1, 1, 1});
		//11 09 29 30 29 39 17 
		//47 38 37 37 37 11 09 
		//11 09 37 38 37 47 37
		print(solver);
		System.out.println();
	}

	private static void print(Srm404Div2Lev3 solver){
		for(int i = 0; i < solver.stair.length; i++){
			System.out.print(solver.stair[i].idx + "["+solver.dp[i]+"]");
		}
	}

int sqr(int val){
    return val * val;
}

boolean reachable(int idx1, int idx2){
    // the first case
    if ((stair[idx1].x <= stair[idx2].x && stair[idx2].x <= stair[idx1].x + stair[idx1].len) ||
        (stair[idx2].x <= stair[idx1].x && stair[idx1].x <= stair[idx2].x + stair[idx2].len))
        return Math.abs(stair[idx1].y - stair[idx2].y) <= K;
    // the second case
    return (sqr(stair[idx2].x - stair[idx1].x - stair[idx1].len) + 
        sqr(stair[idx2].y - stair[idx1].y) <= sqr(K)) || (sqr(stair[idx1].x - 
        stair[idx2].x - stair[idx2].len) + sqr(stair[idx1].y - stair[idx2].y) <= sqr(K));
}

private class Stair implements Comparable{
    int x, y, len, sw, idx;
    Stair(int x, int y, int len, int sw, int idx){
        this.x = x;
        this.y = y;
        this.len = len;
        this.sw = sw;
	this.idx = idx;
    }
    
    public int compareTo(Object stairo){
        Stair stair = (Stair)(stairo);
        if (y < stair.y)
            return -1;
        else if (y > stair.y)
            return 1;
        else{
            if (x < stair.x)
                return -1;
            else
                return 1;
        }
    }
}

Stair stair[];
int n, K;
int dp[];

int memo(int idx){
    // if it is already calculated, return it
    if (dp[idx] != -1)
        return dp[idx];
    
    // sum sweets that are reachable from current stair and they are placed on same y line
    int sum = stair[idx].sw, st1, st2, i;
    for (i = idx - 1; i > -1; i--)
        if ((stair[i].y == stair[i + 1].y) && reachable(i, i + 1))
            sum += stair[i].sw;
        else
            break;
    st1 = i + 1;
    for (i = idx + 1; i < n; i++)
        if ((stair[i].y == stair[i - 1].y) && reachable(i, i - 1))
            sum += stair[i].sw;
        else
            break;
    st2 = i - 1;
    // now we have the sum. let's go through all stairs and try to jump on any reachable
    System.out.println(stair[idx].idx + ":" + stair[idx].y + ":" + sum);

    dp[idx] = 0;

    for (i = 0; i < n; i++)
        // try reachable stairs
        if (i != idx && stair[i].y >= stair[idx].y && reachable(i, idx)){
            if (stair[i].y == stair[idx].y)
                dp[idx] = Math.max(dp[idx], memo(i));
            else
                dp[idx] = Math.max(dp[idx], memo(i) + sum);
        }
    // now we should update stairs optimal solutions
    // suppose for some group of reachable stairs with the same y-coord, optimal solution
    // exists for stair with index idx. if we move left from idx to check stair on the same line
    // then we can't move from idx-1 to idx (note that dp[idx] = 0 in that time - if it is not, we
    // could get infinite recursion), but we should update dp[idx-1] to dp[idx] later
    for (i = st1; i <= st2; i++){
        // don't update stair that wasn't visited yet, because latter you
        // won't be able to examine that            
        if (dp[i] == -1){
            memo(i);
		//System.out.println(st1+"++"+i+"++"+st2);
	}	
        dp[i] = Math.max(dp[i], dp[idx]);
    }
    
    // if can't reach any stair, then maximum is collected sweets on the y-coord
    dp[idx] = Math.max(dp[idx], sum);
    return dp[idx];
}

int collectSweets(int K, int[] sweets, 
        int[] x, int[] y, int[] stairLength){
    this.K = K;
    n = sweets.length;
    dp = new int[n];
    Arrays.fill(dp, -1);
    stair = new Stair[n];
    for (int i = 0; i < n; i++)
        stair[i] = new Stair(x[i], y[i], stairLength[i], sweets[i], i);
    Arrays.sort(stair);
    int ret = 0;
    for (int i = 0; i < n; i++)
        if (stair[i].y <= K)
            // for each stair, check if John starting from 
            // it can collect maximum number of sweets.
            ret = Math.max(memo(i), ret);
    return ret;
}
}
