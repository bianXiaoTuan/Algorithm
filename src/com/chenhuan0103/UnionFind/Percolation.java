package com.chenhuan0103.UnionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by chenhuan on 2016/12/29.
 */
public class Percolation {

    private int _size;
    private int[][] _grid;    // 0 = closed, 1 = open
    private int _open_sites_num;
    private int _virtual_top_pos;
    private int _virtual_bottom_pos;

    private WeightedQuickUnionUF _uf;

    public WeightedQuickUnionUF get_uf() {
        return _uf;
    }

    public void validate(int row, int col) {
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("row or col less than 1");
        }
    }

    /**
     * 将grid中row和col转成union_find中index
     *
     * @param row {int}
     * @param col {int}
     * @return {int}
     */
    public int uf_index(int row, int col) {
        validate(row, col);
        return (row - 1) * _size + col;
    }

    /**
     * Create n-by-n grid, with all sites blocked
     *
     * @param n {int}
     */
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("size n less than 0");
        }

        _size = n;
        _open_sites_num = 0;
        _virtual_top_pos = 0;
        _virtual_bottom_pos = n * n + 1;

        // Init grid
        _grid = new int[n][n];
        for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               _grid[i][j] = 0;
           }
        }

        // Init union-find, included virtul top and bottom
        _uf = new WeightedQuickUnionUF(n * n + 2);
    }

    /**
     * Open site (row, col) if it is not open already
     *
     * @param row {int}
     * @param col {int}
     */
    public void open(int row, int col) {
        validate(row, col);

        if (_grid[row - 1][col - 1] == 0) {
            // Set site open
            _grid[row - 1][col - 1] = 1;

            // Get uf index
            int uf_index = uf_index(row, col);

            // if site on top side
            if (row == 1) {
                _uf.union(uf_index, _virtual_top_pos);
            }

            // if site on bottom side
            if (row == _size) {
                _uf.union(uf_index, _virtual_bottom_pos);
            }

            // Union top
            if (row > 1 && isOpen(row - 1, col)) {
                _uf.union(uf_index, uf_index(row - 1, col));
            }

            // Union bottom
            if (row < _size && isOpen(row + 1, col)) {
                _uf.union(uf_index, uf_index(row + 1, col));
            }

            // Union left
            if (col > 1 && isOpen(row, col - 1)) {
                _uf.union(uf_index, uf_index(row, col - 1));
            }

            // Union right
            if (col < _size && isOpen(row, col + 1)) {
                _uf.union(uf_index, uf_index(row, col + 1));
            }

            _open_sites_num = _open_sites_num + 1;
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

        return _grid[row - 1][col - 1] == 1;
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

        return _uf.connected(_virtual_top_pos, uf_index(row, col));
    }

    /**
     * Number of open sites
     *
     * @return {int}
     */
    public int numberOfOpenSites() {
        return _open_sites_num;
    }

    /**
     * Does the system percolate?
     *
     * @return True = system percolate, False = not
     */
    public boolean percolates() {
        return _uf.connected(_virtual_top_pos, _virtual_bottom_pos);
    }

    public static void main(String[] args) {
    }
}
