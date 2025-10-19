package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int L;
    private double[] d;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("");
        }
        L = T;
        d = new double[L];

        for (int i = 0; i < L; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            d[i] = (double) p.numberOfOpenSites() / (N * N);
        }


    }  // perform T independent experiments on an N-by-N grid
    public double mean(){
        return StdStats.mean(d);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(d);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(L));
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(L));
    }

}
