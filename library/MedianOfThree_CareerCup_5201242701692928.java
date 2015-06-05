class MedianOfThree_CareerCup_5201242701692928{
    public static void main(String[]args){
        System.out.println(median(10, 1, 5, 0, 11));//...min(0)...1...5...10...max(11)
        System.out.println(median(10, 1, 5, 2, 4));//...1...min(2)...max(4)...5...10...
        System.out.println(median(10, 1, 5, 2, 11));//...1...min(2)...5...10...max(11)
    }
    public static Integer median(int a, int b, int c, int min, int max) {
        if (min > max) return null;

        int[] arr = new int[3];

        int i = 0;

        if (min <= a && a <= max) {
            arr[i] = a;
            ++i;
        }
        if (min <= b && b <= max) {
            arr[i] = b;
            ++i;
        }
        if (min <= c && c <= max) {
            arr[i] = c;
            ++i;
        }

        if (i == 0) return null;

        if (i == 1) return arr[0];

        if (i == 2) return arr[0];

        return median(a, b, c);
    }

    public static Integer median(int a, int b, int c) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b < c) {
            int tmp = b;
            b = c;
            c = tmp;
        }
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        return b;
    }
}