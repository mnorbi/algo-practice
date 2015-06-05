import java.util.*;

class ProbabilityOfAlive {
  public static double aliveProb(int x, int y, int N, int n) {
    if (x < 0 || x > (N - 1) || y < 0 || y > (N - 1) || N < 1) return 0.0;
    return aliveProb(x, y, N, n, new HashMap<String, Double>());
  }

  private static double aliveProb(int x, int y, int n, int step, Map<String, Double> map) {
    if (0 == step) return 1.0;

    String key = x + "," + y + "," + step;
    if (map.containsKey(key)) return map.get(key);

    double p = 0.0;
    if (x > 0)     p += 0.25 * aliveProb(x - 1, y, n, step - 1, map);
    if (x < n - 1) p += 0.25 * aliveProb(x + 1, y, n, step - 1, map);
    if (y > 0)     p += 0.25 * aliveProb(x, y - 1, n, step - 1, map);
    if (y < n - 1) p += 0.25 * aliveProb(x, y + 1, n, step - 1, map);

    map.put(key, p);
    return p;
  }

  public static void main(String[] args) {
    System.out.println("Alive probability = " + aliveProb(0, 0, 1, 1));
    System.out.println("Alive probability = " + aliveProb(0, 0, 2, 1));
    System.out.println("Alive probability = " + aliveProb(0, 0, 3, 3));
    System.out.println("Alive probability = " + aliveProb(1, 1, 3, 1));
    System.out.println("Alive probability = " + aliveProb(2, 2, 10, 20));
  }
}
