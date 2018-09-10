import java.util.*;
import java.io.*;

import static java.util.Collections.emptyList;

class Solution {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.valueOf(br.readLine());
            for (int i = 1; i <= t; ++i) {
                int N = Integer.valueOf(br.readLine());
                Map<String, List<Edge>> adj = new HashMap<>();
                for (int a = 0; a < N; ++a) {
                    String line = br.readLine();
                    int plusPos = line.indexOf("+");
                    int eqPos = line.indexOf("=");
                    String left = line.substring(0, plusPos);
                    String right = line.substring(plusPos + 1, eqPos);
                    int val = Integer.valueOf(line.substring(eqPos + 1));
                    final Edge edge = new Edge(a, val, left, right);
                    adj
                          .computeIfAbsent(left, v -> new ArrayList<>())
                          .add(edge);
                    adj
                          .computeIfAbsent(right, v -> new ArrayList<>())
                          .add(edge);
                }
                StringBuilder ans = new StringBuilder();
                int Q = Integer.valueOf(br.readLine());
                for (int a = 0; a < Q; ++a) {
                    String line = br.readLine();
                    int plusPos = line.indexOf("+");
                    String left = line.substring(0, plusPos);
                    String right = line.substring(plusPos + 1);
                    Long result = search(left, right, adj);
                    if (result != null) {
                        ans
                              .append(line)
                              .append("=")
                              .append(result)
                              .append("\n");
                    }
                }
                System.out.println("Case #" + i + ":");
                System.out.print(ans);
            }
        }
    }

    private static Long search(String left,
                                  String right,
                                  Map<String, List<Edge>> adj) {
        Set<String> oddVis = new HashSet<>();
        Set<String> evenVis = new HashSet<>();
        return oddDfs(left, right, 0, oddVis, evenVis, adj);
    }

    private static Long oddDfs(String left,
                                  String right,
                                  long cost,
                                  Set<String> oddVis,
                                  Set<String> evenVis,
                                  Map<String, List<Edge>> adj) {
        if (oddVis.contains(right)) {
		return null;
        }
	oddVis.add(right);
        for (final Edge edge : adj.getOrDefault(right, emptyList())) {
            Long ans = evenDfs(left, edge.other(right), cost + edge.val, oddVis, evenVis, adj);
            if (ans != null){
                return ans;
            }
        }
        return null;
    }

    private static Long evenDfs(final String left,
                                final String right,
                                final long cost,
                                final Set<String> oddVis,
                                final Set<String> evenVis,
                                final Map<String,List<Edge>> adj) {
        if (Objects.equals(left, right)){
            return cost;
        }
	if (evenVis.contains(right)){
		return null;
	}
	evenVis.add(right);
        for (final Edge edge : adj.getOrDefault(right, emptyList())) {
            Long ans = oddDfs(left, edge.other(right), cost - edge.val, oddVis, evenVis, adj);
            if (ans != null){
                return ans;
            }
        }
        return null;
    }

    static class Edge {
        String id;
        int val;
        String left;
        String right;

        Edge(final int id,
             final int val,
             final String left,
             final String right) {
            this.id = Integer.toString(id);
            this.val = val;
            this.left = left;
            this.right = right;
        }

        String other(String vertex) {
            if (Objects.equals(vertex, left)) {
                return right;
            }
            if (Objects.equals(vertex, right)) {
                return left;
            }
            throw new IllegalArgumentException();
        }
    }
}
