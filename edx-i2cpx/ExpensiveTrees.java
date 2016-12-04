import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
public class ExpensiveTrees{
    public static void main(String[]args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new FileReader("trees.in"));
                FileWriter fw = new FileWriter("trees.out");
        ){
            long[] aux = toArray(br.readLine());
            long N = aux[0];
            BigDecimal half = BigDecimal.valueOf(N / 2);
            BigDecimal ans = half.multiply(BigDecimal.valueOf(N).subtract(half));
            fw.write(""+ans);
        }
    }
    static long[] toArray(String s){
        return Arrays.stream(s.split(" ")).mapToLong(Long::valueOf).toArray();
    }
}
