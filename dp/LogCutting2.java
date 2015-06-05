class LogCutting2 {
    public static void main(String[] args) {
        System.out.println(calculateLogCuttingCost(100, new int[]{23, 34, 35, 68, 71, 88, 93}));
        System.out.println(calculateLogCuttingCost(100, new int[]{1}));
    }

    public static int calculateLogCuttingCost(int n, int[] markings) {
        int m = markings.length;
        int[] newMarkings = new int[m+2];
        newMarkings[0] = 0;
        newMarkings[m+1] = n;

        for(int j=1; j<m+1; j++) {
            newMarkings[j] = markings[j-1];
        }
        m = newMarkings.length;

        int[][] cmatrix = new int[m][m];
        cmatrix[0][0] = 0;

        cmatrix[0][1] = newMarkings[1];
        cmatrix[m-2][m-1] = n - newMarkings[m-2];

        for(int i=1; i<m-2; i++) {
            cmatrix[i][i+1] = newMarkings[i+1] - newMarkings[i]; // sub-logs with no markings
        }

        for(int windowSize=1; windowSize<m; windowSize++) {
            for(int i=0; i+windowSize<m; i++) {
                int j = i + windowSize;
                int minCost = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++) {
                    int currCost = cmatrix[i][k] + cmatrix[k][j] + newMarkings[j] - newMarkings[i];
                    if(currCost < minCost) minCost = currCost;
                }
                if(j != i+1)cmatrix[i][j] = minCost;
            }
        }
        return cmatrix[0][m-1];
    }
}
