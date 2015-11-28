import java.util.*;

class ShortestRangeCoveringAllLists{
    public static void main(String[]args){
        int[] sol = solve(
                new int[][]{
                        {17, 35, 63},
                        {18, 26, 66},
                        {42, 48, 90},
                        {26, 33, 39},
                        {36, 68, 72}
                }
        );
        System.out.println(Arrays.toString(sol));
        int[] sol2 = solve(
                new int[][]{
                        {15, 59, 70, 95},
                        {35, 41, 55, 91},
                        {22, 54, 57, 60}
                }
        );
        System.out.println(Arrays.toString(sol2));
    }
    static int[] solve(int[][] mat){
        Comparator<Item> comp = (i1, i2) -> Integer.compare(mat[i1.row][i1.col], mat[i2.row][i2.col]);
        Item rangeStart = findRangeStart(mat, comp);
        return createRange(mat, comp, rangeStart);
    }

    private static Item findRangeStart(int[][] mat, Comparator<Item> comp) {
        int ROWS = mat.length;
        int COLS = mat[0].length;
        PriorityQueue<Item> minPq = new PriorityQueue<>(comp);
        minPq.add(new Item(0, 0));
        Item max = minPq.peek();
        for(int i = 1; i < ROWS; ++i){
            Item item = new Item(i, 0);
            minPq.add(item);
            if (comp.compare(max, item) < 0){
                max = item;
            }
        }
        Item ret = null;
        int widthOpt = Integer.MAX_VALUE;
        while(true){
            Item min = minPq.remove();
            int width = mat[max.row][max.col]-mat[min.row][min.col];
            if (widthOpt > width){
                widthOpt = width;
                ret = min;
            }
            if (min.col == COLS-1) break;
            Item item = new Item(min.row, min.col +1);
            minPq.add(item);
            if (comp.compare(max, item) < 0){
                max = item;
            }
        }
        return ret;
    }

    private static int[] createRange(int[][] mat, Comparator<Item> comp, Item rangeStart) {
        int ROWS = mat.length;
        int[] ret = new int[ROWS];
        PriorityQueue<Item> minPq = new PriorityQueue<>(comp);
        for(int i = 0; i < ROWS; ++i){
            Item item = new Item(i, 0);
            minPq.add(item);
        }

        for(int i = 0;;){
            Item min = minPq.peek();
            if (min.row == rangeStart.row && min.col == rangeStart.col){
                while(!minPq.isEmpty() ){
                    Item item = minPq.remove();
                    ret[i++] = mat[item.row][item.col];
                }
                return ret;
            }
            minPq.remove();
            Item item = new Item(min.row, min.col+1);
            minPq.add(item);
        }
    }

}
class Item{
    int row, col;
    Item(int row, int col){
        this.row = row;
        this.col = col;
    }
}
