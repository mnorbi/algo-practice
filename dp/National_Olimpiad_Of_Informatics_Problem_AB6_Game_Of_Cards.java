/**
XXX NATIONAL OLIMPIAD IN INFORMATICS

National round

Varna, 26 - 27 April, 2014 

Groups A and B, 9-12 grade, day 2

Problem AB6. Game of cards

There are n cards and each one of them has a positive integer that is less than 1000 written on it. The 

cards are aligned next to each other. Martin has come up with a new game. There are n - 2 number of turns 

to be exetuted in it. In each turn a card from the card sequence is removed excluding the first and the last 

card. For every removed card there are points given, which are calculated by multiplying the number written 

on the card with the sum of the numbers written on the 2 cards next to it. Eventually there are only 2 cards 

left(the first and the last). Write a program game which finds the maximum amount of points which can be 

recieved by playing the game.

Input

On the first line of the standard input the number n (3 ≤ n ≤ 700) is given. On the next line are the 

numbers written on the cards with the same arrangement as in the cards sequence.

Output

On the standard output print the maximal amount of points which can be recieved by playing the game.

Example

Input

4 

4 5 6 2 

Output

86

Author: Mladen Manev
**/
public class National_Olimpiad_Of_Informatics_Problem_AB6_Game_Of_Cards{
    public static void main(String[]args){
        System.out.println(maxProfitRec(new int[] {4, 5, 6, 2}));
        System.out.println(maxProfitIter(new int[]{4, 5, 6, 2}));
    }
    static int maxProfitIter(int[] arr){
        int N = arr.length;
        int[][] dp = new int[N][N];
        /* omitted java specific init:
        for(int lo = 0; lo+1 < N; ++lo){
          dp[lo][lo+1] = 0;
        }
        */
        for(int gap = 1; gap <= N-2; ++gap){
            for(int lo = 0, hi = lo+gap+1; hi < N; ++lo, hi=lo+gap+1){
                int lastIdx = -1;
                for(int i = lo+1; i < hi; ++i){
                    int profit = dp[lo][i]+dp[i][hi];
                    if (dp[lo][hi] <= profit){
                        dp[lo][hi] = profit;
                        lastIdx = i;
                    }
                }
                dp[lo][hi] += profitFunc(arr, lo, lastIdx, hi);
            }
        }
        return dp[0][N-1];
    }
    static int maxProfitRec(int[] arr){
        return maxProfitRec(arr, 0, arr.length-1);
    }
    static int maxProfitRec(int[] arr, int lo, int hi){
        if (hi-lo < 2) return 0;
        int ret = 0;
        int lastIdx = -1;
        for(int i = lo+1; i < hi; ++i){
            int profit = maxProfitRec(arr, lo, i) + maxProfitRec(arr, i, hi);
            if (ret <= profit){
                ret = profit;
                lastIdx = i;
            }
        }
        ret += profitFunc(arr, lo, lastIdx, hi);

        return ret;
    }
    static int profitFunc(int[] arr, int left, int mid, int right){
        return (arr[left]+arr[right])*arr[mid];
    }
}
