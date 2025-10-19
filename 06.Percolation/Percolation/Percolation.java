package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF w;
    private WeightedQuickUnionUF w2;
    private int numberOfOpenSites;
    private int t;
    private int b;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Out of Range");
        }

        this.w = new WeightedQuickUnionUF(N * N + 2);
        this.w2 = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        this.grid = new boolean[N][N];
        this.numberOfOpenSites = 0;

        this.t = N * N;
        this.b = N * N + 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }

    }               // create N-by-N grid, with all sites initially blocked


    public void open(int row, int col) {
        if (!(vF(row, col))) {
            throw new IndexOutOfBoundsException();
        }
        if (!(isOpen(row, col))) {
            grid[row][col] = true;
            numberOfOpenSites++;
        }
        if (row == 0) {
            w.union(t, col);
            w2.union(t, col);
        }
        if (row == N - 1) {
            w2.union(b, row * N + col);
        }
        if (vF(row - 1, col) && isOpen(row - 1, col)) {
            w.union((row * N + col), (row - 1) * N + col);
            w2.union((row * N + col), (row - 1) * N + col);
        }
        if (vF(row + 1, col) && isOpen(row + 1, col)) {
            w.union((row * N + col), (row + 1) * N + col);
            w2.union((row * N + col), (row + 1) * N + col);
        }
        if (vF(row, col - 1) && isOpen(row, col - 1)) {
            w.union((row * N + col), row * N + col - 1);
            w2.union((row * N + col), row * N + col - 1);
        }
        if (vF(row, col + 1) && isOpen(row, col + 1)) {
            w.union((row * N + col), row * N + col + 1);
            w2.union((row * N + col), row * N + col + 1);
        }
    }
    private boolean vF(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }


    public boolean isOpen(int row, int col) {
        if (vF(row, col)) {
            return grid[row][col];
        }
        throw new IndexOutOfBoundsException();

    }  // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (vF(row, col)) {
            return w.connected((row * N + col), t);
        }
        throw new IndexOutOfBoundsException();
    } // is the site (row, col) full?

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }          // number of open sites

    public boolean percolates() {
        return w2.connected(t, b);

    }

    private static void main(String[] args) {

    }


}
