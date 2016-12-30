package com.chenhuan0103.UnionFind;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenhuan on 16/12/30.
 */
public class PercolationStatsTest {

    private PercolationStats percolationStats;

    public PercolationStatsTest() {
        percolationStats = new PercolationStats(5, 10);
    }

    @Test
    public void testMean() throws Exception {
        System.out.println(percolationStats.mean());
    }

    @Test
    public void testStddev() throws Exception {
        System.out.println(percolationStats.stddev());
    }

    @Test
    public void testConfidenceLo() throws Exception {
        System.out.println(percolationStats.confidenceLo());
    }

    @Test
    public void testConfidenceHi() throws Exception {
        System.out.println(percolationStats.confidenceHi());
    }
}