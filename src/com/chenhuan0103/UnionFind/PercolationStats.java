import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by chenhuan on 2016/12/29.
 */
public class PercolationStats {

    private int trialsNum;
    private int[] thresholds;

    /**
     * Perform trials independent experiments on an n-by-n grid
     *
     * @param n {int} n-by-n grid
     * @param trials {int} performs trials independent computational experiments
     */
    public PercolationStats(int n, int trials) {
        trialsNum = trials;
        thresholds = new int[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (percolation.percolates()) {
                int row = (int) (StdRandom.uniform() * n);
                int col = (int) (StdRandom.uniform() * n);

                percolation.open(row, col);
            }
            thresholds[i] = percolation.numberOfOpenSites();
        }
    }

    /**
     * Sample mean of percolation threshold
     *
     * @return {double}
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * Sample standard deviation of percolation threshold
     *
     * @return {double}
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * Low endpoint of 95% confidence interval
     *
     * @return {double}
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trialsNum);
    }

    /**
     * High endpoint of 95% confidence interval
     *
     * @return {double}
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trialsNum);
    }

    public static void main(String[] args) {
    }
}
