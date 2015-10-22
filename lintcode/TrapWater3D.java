import java.util.Comparator;
import java.util.PriorityQueue;
/**
  Problem solved with the disjoint set datastructure.
  http://www.lintcode.com/en/problem/trapping-rain-water-ii/
**/
class Solution {
    static final int[][] dir = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(final int[][] heights) {
        if (heights == null || heights.length == 0) return 0;
        int ROWS = heights.length;
        int COLS = heights[0].length;

        PriorityQueue<DisjointSet> pq = new PriorityQueue<>(ROWS*COLS, new Comparator<DisjointSet>() {
            public int compare(DisjointSet p1, DisjointSet p2) {
                int cmp = Integer.compare(heights[p1.row][p1.col], heights[p2.row][p2.col]);
                return cmp;
            }
        });
        DisjointSet[][] disjointSets = new DisjointSet[ROWS][COLS];
        for(int i = 0; i < ROWS; ++i){
            for(int j = 0; j < COLS; ++j){
                disjointSets[i][j] = new DisjointSet(i,j, heights[i][j]);
                pq.add(disjointSets[i][j]);
            }
        }
        int ret = 0;
        while (!pq.isEmpty()) {
            DisjointSet disjointSet = pq.remove();
            for (int d = 0; d < dir.length; ++d) {
                int r = disjointSet.row + dir[d][0];
                int c = disjointSet.col + dir[d][1];
                if (r < 0 || r >= ROWS || c < 0 || c >= COLS) {
                    disjointSet.setLeak();
                    continue;
                }
                DisjointSet other = disjointSets[r][c];
                int heightDiff = disjointSet.getHeight() - other.getHeight();
                if (heightDiff > 0) {
                    if (!other.isLeak()) {
                        ret += other.getSize() * heightDiff;
                    }
                    DisjointSet.union(disjointSet, other, disjointSet.height);
                } else if (heightDiff == 0) {
                    DisjointSet.union(disjointSet, other);
                }
            }
        }
        return ret;
    }
}

class DisjointSet {
    int row;
    int col;
    int height;
    int size;
    boolean leak;
    DisjointSet boss;
    DisjointSet(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
        this.size = 1;
        this.leak = false;
        this.boss = this;
    }
    public static void union(DisjointSet a, DisjointSet b) {
        DisjointSet aBoss = a.getBoss();
        DisjointSet bBoss = b.getBoss();

        if (aBoss == bBoss) return;

        if (aBoss.getSize() > bBoss.getSize()) {
            aBoss.accept(bBoss);
        }else{
            bBoss.accept(aBoss);
        }
    }
    public static void union(DisjointSet a, DisjointSet b, int height) {
        union(a, b);
        a.getBoss().height = height;
        b.getBoss().height = height;
    }
    private void accept(DisjointSet other) {
        other.boss = this;
        this.size += other.size;
        this.leak |= other.leak;
    }
    private DisjointSet getBoss() {
        while (this.boss != this.boss.boss) {
            this.boss = this.boss.boss;
        }
        return this.boss;
    }
    public boolean isLeak() {
        DisjointSet boss = getBoss();
        return boss.leak;
    }
    public void setLeak() {
        DisjointSet boss = getBoss();
        boss.leak = true;
    }
    public int getSize() {
        DisjointSet boss = getBoss();
        return boss.size;
    }
    public int getHeight() {
        DisjointSet boss = getBoss();
        return boss.height;
    }
}
