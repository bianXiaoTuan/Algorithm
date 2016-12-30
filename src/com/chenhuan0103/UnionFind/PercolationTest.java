package com.chenhuan0103.UnionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by chenhuan on 16/12/30.
 */
public class PercolationTest {
    private Percolation percolation;
    private WeightedQuickUnionUF uf;

    public PercolationTest() {
        percolation = new Percolation(3);
        uf = percolation.uf;
    }

    @Test
    public void testOpen() throws Exception {
        percolation.open(1, 1);

        assertEquals(uf.connected(0, percolation.ufIndex(1, 1)), true);
        assertEquals(uf.connected(0, percolation.ufIndex(3, 1)), false);
    }

    @Test
    public void testIsOpen() throws Exception {
        percolation.open(1, 2);

        assertEquals(percolation.isOpen(1, 1), false);
        assertEquals(percolation.isOpen(1, 2), true);
        assertEquals(percolation.isOpen(1, 3), false);
    }

    @Test
    public void testIsFull() throws Exception {
        percolation.open(1, 1);

        assertEquals(percolation.isFull(1, 1), true);
        assertEquals(percolation.isFull(2, 1), false);

        percolation.open(2, 1);

        assertEquals(percolation.isFull(2, 1), true);

        percolation.open(2, 3);

        assertEquals(percolation.isFull(2, 3), false);

        percolation.open(1, 3);

        assertEquals(percolation.isFull(2, 3), true);
    }

    @Test
    public void testNumberOfOpenSites() throws Exception {
        assertEquals(percolation.numberOfOpenSites(), 0);

        percolation.open(1, 1);
        percolation.open(1, 2);
        percolation.open(1, 3);

        assertEquals(percolation.numberOfOpenSites(), 3);

        percolation.open(2, 3);

        assertEquals(percolation.numberOfOpenSites(), 4);
    }

    @Test
    public void testPercolates() throws Exception {
        assertEquals(percolation.percolates(), false);

        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        assertEquals(percolation.percolates(), true);
    }
}