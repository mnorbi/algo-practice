import java.io.*;
import java.util.*;

public class Template{
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("template.in")));
             BufferedWriter out = new BufferedWriter(new FileWriter("template.out"))){
            String[] strings = in.readLine().split(" ");
            int W = Integer.valueOf(strings[0]);
            int H = Integer.valueOf(strings[1]);
            Map<Character, Integer> KR = new HashMap<>();
            Map<Character, Integer> KC = new HashMap<>();
            for(int row = 0; row < H; ++row){
                String nextRow = in.readLine();
                for(int col = 0; col < W; ++col){
                    Character c = nextRow.charAt(col);
                    KR.put(c, H-row);
                    KC.put(c, col+1);
                }
            }
            in.readLine();
            int minCost = Integer.MAX_VALUE;
            String minCostLanguage = "";
            String line;
            while((line = in.readLine()) != null){
                String language = line;
                int cost = 0;
                Character prev = null;
                while((line = in.readLine()) != null && line.length() > 0){
                    System.out.println(Arrays.toString(line.toCharArray()));
                    for(int a = 0; a < line.length(); ++a){
                        Character next = line.charAt(a);
                        if (prev != null) {
                            cost += Math.max(Math.abs(KR.get(prev) - KR.get(next)), Math.abs(KC.get(prev) - KC.get(next)));
                        }
                        prev = next;
                    }
                }
                if (line == null) break;
                if (cost < minCost){
                    minCost = cost;
                    minCostLanguage = language;
                }
            }
            out.write(minCostLanguage); out.newLine();
            out.write(""+minCost); out.newLine();
        }
    }
}
