import java.util.ArrayList;
import java.util.Observable;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	private int grid_cnt;
	private int exp_cnt;
	private double[] obs;
	
	public PercolationStats(int N, int T){
		if(N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException("Both N and T must be greater than Zero");
		else {
			this.grid_cnt = N;
			this.exp_cnt  = T;
			this.obs = new double[T];
		}
	}
	
	public double mean(){
		return StdStats.mean(this.obs);
	}
	
	public double stddev(){
		return StdStats.stddev(this.obs);
		
	}

	public double confidenceLo(){
		return mean() - (1.96*stddev()/Math.sqrt(this.exp_cnt));
		
	}
	
	public double confidenceHi(){
		return mean() + (1.96*stddev()/Math.sqrt(this.exp_cnt));
		
	}
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		for(int k = 1;k<ps.exp_cnt+1;k++){
			Percolation p = new Percolation(ps.grid_cnt);
			int num = 0;
			while(!p.percolates()){
				int i = StdRandom.uniform(1, ps.grid_cnt+1);
				int j = StdRandom.uniform(1, ps.grid_cnt+1);
				if(!p.isOpen(i, j)){
					/* System.out.println("i value is " + i + " j value is " + j); */
					p.open(i, j);
					num  = num+1;
				}
			}
			ps.obs[k-1] = num/Math.pow(ps.grid_cnt, 2);
			/*  System.out.println("system now percolates with " + ps.obs[k-1] + " places opened" );  */
		}
		System.out.println(ps.mean());
		System.out.println(ps.stddev());
		System.out.println(ps.confidenceLo() + " " + ps.confidenceHi());
		
		
		
 }
}


	