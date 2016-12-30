package com.chenhuan0103.UnionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by chenhuan on 2016/12/29.
 */
public class Percolation {

    private int _size;
    private int[][] _grid;
    private WeightedQuickUnionUF _uf;

    /**
     * Create n-by-n grid, with all sites blocked
     *
     * @param n {int}
     */
    public Percolation(int n) {
        _size = n;

        // Init grid
        _grid = new int[n][n];
        for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               _grid[i][j] = 0;    // 0 = site closed
           }
        }


    }

    /**
     * Open site (row, col) if it is not open already
     *
     * @param row {int}
     * @param col {int}
     */
    public void open(int row, int col) {

    }

    /**
     * Is site (row, col) open?
     *
     * @param row {int}
     * @param col {int}
     * @return True = open, False = closed
     */
    public boolean isOpen(int row, int col) {
        return false;
    }

    /**
     * Is site (row, col) full?
     *
     * @param row {int}
     * @param col {int}
     * @return True = full, False = not full
     */
    public boolean isFull(int row, int col) {
        return false;
    }

    /**
     * Number of open sites
     *
     * @return {int}
     */
    public int numberOfOpenSites() {
        return 0;
    }

    /**
     * Does the system percolate?
     *
     * @return True = system percolate, False = not
     */
    public boolean percolates() {
        return false;
    }

    public static void main(String[] args) {

    }
}
