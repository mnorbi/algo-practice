class FairBonuses{
    public static void main(String[]args){
        print(fairBonus(new int[]{300,400,500,200}));
        print(fairBonus(new int[]{10,20,30,30,25,20,15,15,20}));
        print(fairBonus(new int[]{10,20,30,30,25,20,15,15,10}));
        print(fairBonus(new int[]{10,20,30,10,20,30}));
        print(fairBonus(new int[]{10,20,30,40,30,30,20,20,10,5,5,10,15,20,10,9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
    }
    static void print(int[]arr){
        for(int i : arr){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }
    static int[] fairBonus(int[] arr){
        if (arr == null || arr.length == 0) return arr;
        int[] ret = new int[arr.length];
        for(int i = 0; i < arr.length; ++i){
            ret[i] = 1;
        }
        for(int i = 1; i < arr.length; ++i){
            if (arr[i-1] < arr[i]) ret[i] = ret[i-1]+1;
            else if (arr[i-1] == arr[i]) ret[i] = ret[i-1];
        }
        for(int i = arr.length-2; i >= 0; --i){
            int val = ret[i];
            if (arr[i] > arr[i+1]) val = ret[i+1]+1;
            else if (arr[i] == arr[i+1]) val = ret[i+1];
            ret[i] = Math.max(ret[i], val);
        }
        return ret;
    }
}
