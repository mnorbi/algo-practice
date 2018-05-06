class DominoAndTrominoTiling{


    public int numTilings(int N) {
        long[] smooth = new long[]{1,1,2};
        long[] rough = new long[] {0,2,4};

        long ns, nr, MOD = 1_000_000_007;
        for(int a = 3; a <= N; ++a){
            ns = smooth[(a-1)%3]+smooth[(a-2)%3]+rough[(a-2)%3];
            nr = 2*smooth[(a-1)%3]+rough[(a-1)%3];
            
            smooth[a%3] = (ns%MOD);
            rough[a%3] = (nr%MOD);
        }
        
        return (int)smooth[N%3];
    }


}
