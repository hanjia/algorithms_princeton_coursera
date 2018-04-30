import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	private final WeightedQuickUnionUF uf;
	private int numOfOpenSites;
	private final int top;
	private final int bottom;
	
	public Percolation(int n) {
		if (n < 1) {
			throw new java.lang.IllegalArgumentException();
		}

		grid = new boolean[n][n];
		uf = new WeightedQuickUnionUF(n * n + 2);
		
		// Trick: use a top site and a bottom site to avoid check every site at top/bottom; 
		// The top site will be connected with all the sites at the first row.
		// The bottom site will be connected with all the sites at the bottom row.
		top = n * n;
		bottom = n * n + 1;
		for (int i = 0; i < n; i++) {
			uf.union(i, top);
			uf.union(n * (n-1) + i, bottom);
		}

	}

	public void open(int row, int col) {
		if (row < 1 || row > grid.length || col < 1 || col > grid.length) {
			throw new java.lang.IllegalArgumentException();
		}

		if (isOpen(row, col)) {
			return;
		}

		this.grid[row - 1][col - 1] = true;
		this.numOfOpenSites++;

		int index = (row - 1) * grid.length + col - 1;
		if (col > 1 && isOpen(row, col - 1))
			uf.union(index, index - 1);
		if (col < grid.length && isOpen(row, col + 1))
			uf.union(index, index + 1);
		if (row > 1 && isOpen(row - 1, col))
			uf.union(index, index - grid.length);
		if (row < grid.length && isOpen(row + 1, col))
			uf.union(index, index + grid.length);
	}

	public boolean isOpen(int row, int col) {
		if (row < 1 || row > grid.length || col < 1 || col > grid.length) {
			throw new java.lang.IllegalArgumentException();
		}

		return this.grid[row - 1][col - 1];
	}

	public boolean isFull(int row, int col) {
		if (row < 1 || row > grid.length || col < 1 || col > grid.length) {
			throw new java.lang.IllegalArgumentException();
		}
		
		if (!isOpen(row, col))
			return false;

		int index = (row - 1) * grid.length + col - 1;
		return uf.connected(index, grid.length * grid.length);
	}

	public int numberOfOpenSites() {
		return this.numOfOpenSites;
	}

	public boolean percolates() {
		return uf.connected(top, bottom);
	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		Percolation p = new Percolation(n);
		p.open(3, 2);
		p.open(2, 2);
		p.open(1, 2);
		StdOut.println(p.percolates());
		StdOut.println(p.numberOfOpenSites());
	}
}
