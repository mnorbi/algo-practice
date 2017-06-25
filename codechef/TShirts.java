import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TShirts {
    private static int MOD = 1_000_000_007;

    public static void main(String[] args) throws java.lang.Exception {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            int T = parseInts(r.readLine())[0];
            while (T-- > 0) {
                int N = parseInts(r.readLine())[0];
                boolean[][] canHave = new boolean[N + 1][101];
                for (int person = 0; person < N; ++person) {
                    for (int tshirt : parseInts(r.readLine())) {
                        canHave[person][tshirt] = true;
                    }
                }
                System.out.println(solveRec(canHave, 1, 0, new Integer[101][1<<N]));
            }
        }
    }

    private static int solveRec(boolean[][] assignment, int tshirt, int mask, Integer[][] memo) {
        int N = assignment.length - 1;
        if (((1 << N) - 1) == mask) {
            return 1;
        }
        if (tshirt > 100) {
            return 0;
        }
        if (memo[tshirt][mask] != null){
            return memo[tshirt][mask];
        }
        int res = 0;
        for (int person = 0; person < N; person += 1) {
            if ((((1 << person) & mask) == 0) && assignment[person][tshirt]) {
                res += solveRec(assignment, tshirt + 1, mask | (1 << person), memo);
                res %= MOD;
            }
        }
        res += solveRec(assignment, tshirt + 1, mask, memo);
        res %= MOD;
        return memo[tshirt][mask] = res;
    }

    private static int[] parseInts(String s) {
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }
}
