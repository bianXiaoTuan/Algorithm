package com.chenhuan0103.UnionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by chenhuan on 2016/12/29.
 */
public class Percolation {

    private int size;
    private int[][] grid;    // 0 = closed, 1 = open
    private int openSitesNum;
    private int virtualTopPos;
    private int virtualBottomPos;

    private WeightedQuickUnionUF uf;


    /**
     * Create n-by-n grid, with all sites blocked
     *
     * @param n {int}
     */
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("size n less than 0");
        }

        size = n;
        openSitesNum = 0;
        virtualTopPos = 0;
        virtualBottomPos = n * n + 1;

        // Init grid
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               grid[i][j] = 0;
           }
        }

        // Init union-find, included virtul top and bottom
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    /**
     * Check if row and col validate
     *
     * @param row {int}
     * @param col {int}
     */
    private void validate(int row, int col) {
        if (row < 1 || col < 1) {
            throw new IndexOutOfBoundsException("row or col less than 1");
        }
    }

    /**
     * 将grid中row和col转成union_find中index
     *
     * @param row {int}
     * @param col {int}
     * @return {int}
     */
    private int ufIndex(int row, int col) {
        validate(row, col);
        return (row - 1) * size + col;
    }


    /**
     * Open site (row, col) if it is not open already
     *
     * @param row {int}
     * @param col {int}
     */
    public void open(int row, int col) {
        validate(row, col);

        if (grid[row - 1][col - 1] == 0) {
            // Set site open
            grid[row - 1][col - 1] = 1;

            // Get uf index
            int ufIndex = ufIndex(row, col);

            // if site on top side
            if (row == 1) {
                uf.union(ufIndex, virtualTopPos);
            }

            // if site on bottom side
            if (row == size) {
                uf.union(ufIndex, virtualBottomPos);
            }

            // Union top
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(ufIndex, ufIndex(row - 1, col));
            }

            // Union bottom
            if (row < size && isOpen(row + 1, col)) {
                uf.union(ufIndex, ufIndex(row + 1, col));
            }

            // Union left
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(ufIndex, ufIndex(row, col - 1));
            }

            // Union right
            if (col < size && isOpen(row, col + 1)) {
                uf.union(ufIndex, ufIndex(row, col + 1));
            }

            openSitesNum = openSitesNum + 1;
        }
    }

    /**
     * Is site (row, col) open?
     *
     * @param row {int}
     * @param col {int}
     * @return True = open, False = closed
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);

        return grid[row - 1][col - 1] == 1;
    }

    /**
     * Is site (row, col) full?
     *
     * @param row {int}
     * @param col {int}
     * @return True = full, False = not full
     */
    public boolean isFull(int row, int col) {
        validate(row, col);

        return uf.connected(virtualTopPos, ufIndex(row, col));
    }

    /**
     * Number of open sites
     *
     * @return {int}
     */
    public int numberOfOpenSites() {
        return openSitesNum;
    }

    /**
     * Does the system percolate?
     *
     * @return True = system percolate, False = not
     */
    public boolean percolates() {
        return uf.connected(virtualTopPos, virtualBottomPos);
    }

    public static void main(String[] args) {
    }
}
