import java.io.*;
import java.util.*;
public class Underground {
    static int[][] edges = new int[2*100000+1][6];
    static int[] vToE = new int[100000+1];
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("underground.in"));
                FileWriter fw = new FileWriter("underground.out");
        ) {
            int[] aux = toArray(br.readLine());
            int n = aux[0], m = aux[1], k = aux[2];
            for(int a = 1; a <= 2*m; ++a){
                aux = toArray(br.readLine());
                int next = vToE[aux[0]];
                edges[a] = new int[]{
                    aux[0], aux[1], aux[2], next, -1, a+1
                };
                vToE[aux[0]] = a;
                ++a;
                next = vToE[aux[1]];
                edges[a] = new int[] {
                    aux[1], aux[0], aux[2], next, -1, a-1
                };
                vToE[aux[1]] = a;
            }
            int[] cnts = new int[k+1];
            for(int a = 1; a < 2*m+1; ++a){
                LinkedList<int[]> stack = new LinkedList<>();
                int[] edge = edges[a];
                if (edge[4] != -1) { continue; }
                ++cnts[edge[2]];
                edge[4] = a;
                edges[edge[5]][4] = a;
                stack.push(edge);
                while(!stack.isEmpty()){
                    edge = stack.pop();
                    for(int b = 0; b <= 1; ++b){
                        int nextEdgeId = vToE[edge[b]];
                        while(nextEdgeId != 0){
                            int[] nextEdge = edges[nextEdgeId];
                            if (nextEdge[2] == edge[2] && nextEdge[4] == -1){
                                nextEdge[4] = a;
                                edges[nextEdge[5]][4] = a;
                                stack.push(nextEdge);
                            }
                            nextEdgeId = nextEdge[3];
                        }
                    }
                }
            }
            fw.write(""+cnts[1]);
            for(int a = 2; a < cnts.length; ++a){
                fw.write("\n"+cnts[a]);
            }
        }
    }
    static int[] toArray (String s){
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }
}
