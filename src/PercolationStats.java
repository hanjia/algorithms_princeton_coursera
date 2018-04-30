import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final int numOfTrials;
	private final double[] results;
	private final double mean;
	private final double stddev;

	private static final double CONFIDENCE_95 = 1.96;

	public PercolationStats(int n, int trials) {
		if (n < 1 || trials < 1) {
			throw new java.lang.IllegalArgumentException();
		}

		this.numOfTrials = trials;
		results = new double[trials];

		for (int trial = 0; trial < numOfTrials; trial++) {
			Percolation p = new Percolation(n);
			while (!p.percolates()) {
				int row = StdRandom.uniform(n);
				int col = StdRandom.uniform(n);
				p.open(row + 1, col + 1);
			}
			results[trial] = (double) p.numberOfOpenSites() / (double) (n * n);
		}
		this.mean = StdStats.mean(results);
		this.stddev = StdStats.stddev(results);
	}

	public double mean() {
		return this.mean;
	}

	public double stddev() {
		return this.stddev;
	}

	public double confidenceLo() {
		return this.mean - CONFIDENCE_95 * this.stddev / Math.sqrt(numOfTrials);
	}

	public double confidenceHi() {
		return this.mean + CONFIDENCE_95 * this.stddev / Math.sqrt(numOfTrials);
	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats ps = new PercolationStats(n, T);
		StdOut.println("mean = " + ps.mean());
		StdOut.println("stddev = " + ps.stddev());
		StdOut.println("95% confidence interval = [" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
	}
}
