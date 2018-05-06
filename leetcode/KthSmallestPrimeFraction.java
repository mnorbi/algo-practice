class KthSmallestPrimeFraction{
    class Solution {
        private static final boolean[] primes = new boolean[30_001];
        static {
            Arrays.fill(primes, true);
            primes[0] = primes[1] = false;
            for(int a = 2; a*a < primes.length; ++a){
                if (primes[a]){
                    for(int b = a*a; b < primes.length; b+= a){
                        primes[b] = false;
                    }
                }
            }            
        }
        public int[] kthSmallestPrimeFraction(int[] A, int K) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
                return Integer.compare(A[a[0]]*A[b[1]],A[a[1]]*A[b[0]]);
            });

            int[] prevPrimeIdx = new int[A.length];
            int prevIdx = -1;
            for(int a = 1; a < A.length; ++a){
                if (primes[A[a]]){
                    prevPrimeIdx[a] = prevIdx;
                    prevIdx = a;
                }
            }

            int lastPrimeIdx = prevIdx;//<0?
            for(int a = 0; a < lastPrimeIdx; ++a){
                pq.add(new int[]{a,lastPrimeIdx});
            }

            while(K > 1){
                --K;
                int[] v = pq.remove();
                v[1] = prevPrimeIdx[v[1]];
                if (v[1] > 0 && v[1] > v[0]){
                    pq.add(v);
                }
            }

            int[] v = pq.remove();
            return new int[]{A[v[0]],A[v[1]]};
        }
    }
}
