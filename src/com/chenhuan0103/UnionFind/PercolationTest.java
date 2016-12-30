package com.chenhuan0103.UnionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by chenhuan on 16/12/30.
 */
public class PercolationTest {
    private Percolation _percolation;
    private WeightedQuickUnionUF _uf;

    public PercolationTest() {
        _percolation = new Percolation(3);
        _uf = _percolation.get_uf();
    }

    @Test
    public void testOpen() throws Exception {
        _percolation.open(1, 1);

        assertEquals(_uf.connected(0, _percolation.uf_index(1, 1)), true);
        assertEquals(_uf.connected(0, _percolation.uf_index(3, 1)), false);
    }

    @Test
    public void testIsOpen() throws Exception {
        _percolation.open(1, 2);

        assertEquals(_percolation.isOpen(1, 1), false);
        assertEquals(_percolation.isOpen(1, 2), true);
        assertEquals(_percolation.isOpen(1, 3), false);
    }

    @Test
    public void testIsFull() throws Exception {
        _percolation.open(1, 1);

        assertEquals(_percolation.isFull(1, 1), true);
        assertEquals(_percolation.isFull(2, 1), false);

        _percolation.open(2, 1);

        assertEquals(_percolation.isFull(2, 1), true);

        _percolation.open(2, 3);

        assertEquals(_percolation.isFull(2, 3), false);

        _percolation.open(1, 3);

        assertEquals(_percolation.isFull(2, 3), true);
    }

    @Test
    public void testNumberOfOpenSites() throws Exception {
        assertEquals(_percolation.numberOfOpenSites(), 0);

        _percolation.open(1, 1);
        _percolation.open(1, 2);
        _percolation.open(1, 3);
        assertEquals(_percolation.numberOfOpenSites(), 3);

        _percolation.open(2, 3);
        assertEquals(_percolation.numberOfOpenSites(), 4);
    }

    @Test
    public void testPercolates() throws Exception {
        assertEquals(_percolation.percolates(), false);

        _percolation.open(1, 1);
        _percolation.open(2, 1);
        _percolation.open(3, 1);
        assertEquals(_percolation.percolates(), true);
    }
}