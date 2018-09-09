import java.util.*;
import java.io.*;
public class A {
    static int[] mask = new int[]{
            0b1111110,
            0b0110000,
            0b1101101,
            0b1111001,
            0b0110011,
            0b1011011,
            0b1011111,
            0b1110000,
            0b1111111,
            0b1111011
    };

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.valueOf(br.readLine());
            for (int i = 1; i <= t; ++i) {
                String[] sarr = br.readLine().split("\\s+");
                int n = Integer.valueOf(sarr[0]);
                int[] state = new int[n];
                for (int a = 0; a < n; ++a) {
                    state[a] = Integer.valueOf(sarr[a + 1], 2);
                }
                String ans = null;
                String nextAns = null;
                for (int a = 9; a >= 0; --a) {
                    int notDisabled = 0;
                    int disabled = 0;
                    int ambiguous = 0b1111111;
                    boolean ok = true;
                    for (int b = 0; b < state.length; ++b) {
                        if ((disabled & state[b]) > 0) {
                            ok = false;
                            break;
                        }
                        if ((state[b] & mask[Math.floorMod((a - b), 10)]) != state[b]) {
                            ok = false;
                            break;
                        }
                        notDisabled |= state[b];
                        disabled |= (state[b] ^ mask[Math.floorMod((a - b), 10)]);
                        if ((notDisabled & disabled) > 0){
                            ok = false;
                            break;
                        }
                        ambiguous &= ~disabled&~notDisabled;
                    }
                    if (ok) {
                        int foundMask = ~disabled & mask[Math.floorMod((a - state.length), 10)];
                        if ((foundMask & ambiguous) != 0) {
                            ans = "ERROR!";
                            break;
                        }
                        nextAns = String.format("%7s", Integer.toBinaryString(foundMask)).replace(' ', '0');
                        if (ans == null) {
                            ans = nextAns;
                        } else if (!ans.equals(nextAns)) {
                            ans = "ERROR!";
                            break;
                        }
                    }
                }
                if (ans == null) ans = "ERROR!";
                System.out.println("Case #" + i + ": " + ans);
            }
        }
    }

    static int[] toIntArr(String s) {
        return Arrays.stream(s.split("\\s+")).mapToInt(Integer::valueOf).toArray();
    }
}


