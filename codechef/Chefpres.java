import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chefpres {

  public static void main(String[] args) throws java.lang.Exception {
    try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
      int[] ints = parseInts(r.readLine());
      int N = ints[0], K = ints[1];
      int B = parseInts(r.readLine())[0];
      int[][] edges = parseEdges(r, N, K);
      int[] products = parseProducts(r, N);
      int[][] queries = parseQueries(r);
      int[][] binaryLift = createBinaryLift(N, B, edges);
      int[][] productMap = createProductMap(N, K, B, products, edges);
      int[] answers = answerQueries(queries, products, binaryLift, productMap);
      for (int answer : answers) {
        System.out.println(answer);
      }
    }
  }

  static int[] answerQueries(int[][] queries, int[] products, int[][] binaryLift, int[][] productMap) {
    int[] answers = new int[queries.length];
    for (int a = 0; a < queries.length; ++a) {
      answers[a] = -1;
      int[] query = queries[a];
      int source = query[0];
      int product = query[1];
      int b = -1;
      while (true) {
        if (productMap[source][product] != 0 && b == -1) {
          answers[a] = productMap[source][product];
          break;
        }
        b += 1;
        int par = binaryLift[b][source];
        if (par == 0 && b == 0) {
          break;
        }
        if (par == 0 || productMap[par][product] != 0 || b + 1 == binaryLift.length) {
          source = binaryLift[b > 0 ? b - 1 : 0][source];
          b = -1;
          continue;
        }
      }
    }
    return answers;
  }

  static int[][] createProductMap(int N, int K, int root, int[] products, int[][] edges) {
    int[][] productMap = new int[N + 1][K + 1];
    for (int product = 1; product <= K; ++product) {
      createProductMap(root, root, product, products, edges, productMap);
    }
    return productMap;
  }

  static void createProductMap(int current, int parent, int product, int[] products, int[][] edges, int[][] productMap) {
    if (products[current] == product) {
      productMap[current][product] = current;
    }
    int edgeId = edges[0][current];
    while (edgeId != 0) {
      int[] edge = edges[edgeId];
      int next = edge[0];
      if (next != parent) {
        createProductMap(next, current, product, products, edges, productMap);
        if (productMap[current][product] == 0 || productMap[next][product] != 0 && productMap[next][product] < productMap[current][product]) {
          productMap[current][product] = productMap[next][product];
        }
      }
      edgeId = edge[1];
    }
  }

  static int[][] createBinaryLift(int N, int root, int[][] edges) {
    int[][] binaryLift = new int[highestBitIndex(N)][N + 1];
    createBinaryLift(root, root, 1, edges, binaryLift);
    return binaryLift;
  }

  private static int highestBitIndex(int N) {
    return 32 - Integer.numberOfLeadingZeros(N);
  }

  static void createBinaryLift(int current, int parent, int depth, int[][] edges, int[][] binaryLift) {
    int edgeId = edges[0][current];
    while (edgeId != 0) {
      int[] edge = edges[edgeId];
      int next = edge[0];
      if (next != parent) {
        binaryLift[0][next] = current;
        for (int k = 1, j = 2; j <= depth; k += 1, j <<= 1) {
          binaryLift[k][next] = binaryLift[k - 1][binaryLift[k - 1][next]];
        }
        createBinaryLift(next, current, depth + 1, edges, binaryLift);
      }
      edgeId = edge[1];
    }
  }

  static int[][] parseQueries(BufferedReader r) throws IOException {
    int[][] queries = new int[parseInts(r.readLine())[0]][];
    for (int a = 0; a < queries.length; ++a) {
      queries[a] = parseInts(r.readLine());
    }
    return queries;
  }

  static int[] parseProducts(BufferedReader r, int N) throws IOException {
    int[] products = new int[N + 1];
    for (int a = 1; a <= N; ++a) {
      products[a] = parseInts(r.readLine())[0];
    }
    return products;
  }

  static int[][] parseEdges(BufferedReader r, int N, int K) throws IOException {
    int[][] edges = new int[2 * N][];
    edges[0] = new int[N + 1];
    for (int a = 0, b = 1; a < N - 1; ++a) {
      int[] ints = parseInts(r.readLine());
      edges[b] = new int[]{ints[1], edges[0][ints[0]]};
      edges[0][ints[0]] = b++;
      edges[b] = new int[]{ints[0], edges[0][ints[1]]};
      edges[0][ints[1]] = b++;
    }

    return edges;
  }

  static int[] parseInts(String s) {
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
class BruteForce{
  private static Random random = ThreadLocalRandom.current();
  
  static int[] bruteForce(int[][] queries, int[] products, int[][] edges, int N, int root) {
    int[] dist = new int[N + 1];
    dist(dist, edges, root, root);
    int[] answers = new int[queries.length];
    for (int a = 0; a < queries.length; ++a) {
      int[] query = queries[a];
      int source = query[0];
      int product = query[1];
      answers[a] = -1;
      int pathCost = 0, prevTarget = Integer.MAX_VALUE;
      if (products[source] == product) {
        answers[a] = source;
        pathCost = dist[source];
        prevTarget = source;
      }
      for (int target = 1; target < products.length; ++target) {
        if (product == products[target]) {
          int newPathCost = pathCost(dist, edges, source, source, target);
          if (newPathCost > pathCost || newPathCost == pathCost && target < prevTarget) {
            prevTarget = target;
            pathCost = newPathCost;
            answers[a] = target;
          }
        }
      }
    }
    return answers;
  }

  static Integer pathCost(int[] dist, int[][] edges, int current, int parent, int target) {
    if (current == target) {
      return dist[target];
    }
    int edgeId = edges[0][current];
    while (edgeId != 0) {
      int[] edge = edges[edgeId];
      int next = edge[0];
      if (next != parent) {
        Integer cost = pathCost(dist, edges, next, current, target);
        if (cost != null) {
          cost = Math.min(cost, dist[current]);
          return cost;
        }
      }
      edgeId = edge[1];
    }
    return null;
  }

  static void dist(int[] dist, int[][] edges, int current, int parent) {
    int edgeId = edges[0][current];
    while (edgeId != 0) {
      int[] edge = edges[edgeId];
      int next = edge[0];
      if (next != parent) {
        dist[next] = dist[current] + 1;
        dist(dist, edges, next, current);
      }
      edgeId = edge[1];
    }
  }

  static String generateInput(int maxN, int maxK, int maxQ) {
    StringBuilder sb = new StringBuilder();
    int N = 1 + random.nextInt(maxN);
    int K = 1 + random.nextInt(maxK);
    sb.append(String.format("%s %s\n", N, K));

    int B = 1 + random.nextInt(N);
    sb.append(String.format("%s\n", B));

    int[] points = createPermutation(N);
    int next = 2;
    while (next < points.length) {
      next = randomTree(points, 1, next, 0, sb);
    }

    for (int a = 1; a <= N; ++a) {
      sb.append(String.format("%s\n", 1 + random.nextInt(K)));
    }

    int Q = 1 + random.nextInt(maxQ);
    sb.append(String.format("%s\n", Q));
    for (int a = 1; a <= Q; ++a) {
      sb.append(
        String.format("%s %s\n",
          1 + random.nextInt(N),
          1 + random.nextInt(K)
        )
      );
    }

    return sb.toString();
  }

  static int randomTree(int[] points, int parent, int next, int depth, StringBuilder sb) {
    if (next >= points.length) {
      return next;
    }
    if (depth == 0 || random.nextBoolean()) {
      sb.append(String.format("%s %s\n", parent, next));
      next = randomTree(points, next, next + 1, depth + 1, sb);
    }
    return next;
  }

  static int[] createPermutation(int N) {
    int[] permutation = new int[N + 1];
    permutation[1] = 1;
    for (int a = 2; a < permutation.length; ++a) {
      permutation[a] = a;
      swap(permutation, 1 + random.nextInt(a), a);
    }
    return permutation;
  }

  static void swap(int[] points, int a, int b) {
    int tmp = points[a];
    points[a] = points[b];
    points[b] = tmp;
  }

}
