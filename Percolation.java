import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {
	private WeightedQuickUnionUF WQ;
	private WeightedQuickUnionUF WQ1;
	private int[] flag;
	private int count;
	
	public Percolation(int N){
		if (N <= 0) throw new java.lang.IllegalArgumentException("N must be greater than Zero");
		else {
			count = N;
			flag = new int[N*N+2];
			WQ = new WeightedQuickUnionUF(N*N+2);
			WQ1 = new WeightedQuickUnionUF(N*N+1);
			for(int i=0; i<N*N+2; i++){
				if(i>=N*N) flag[i] = 1;
		        else flag[i] = 0;
			}
		}
	}
	
	
	public void open(int i, int j){
		if((i < 1 || i > count) || (j < 1 || j > count)) throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
		int array_pos = gridtolinear(i,j);
		if(array_pos > ((Math.pow(count, 2))-1)) throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
		else{
			if(flag[array_pos] != 1) flag[array_pos] = 1;
		}
		if(i == 1) {
			WQ.union(count*count, array_pos);
			WQ1.union(count*count, array_pos);
		}
		if(i == count) WQ.union(count*count+1, array_pos);
		if(i-1 > 0) {
			int array_pos1 = gridtolinear(i-1, j);
			if(flag[array_pos1] == 1) {
				WQ.union(array_pos, array_pos1);
				WQ1.union(array_pos, array_pos1);
			}
		}
		if(i+1 <= count ) {
			int array_pos2 = gridtolinear(i+1, j);
			if(flag[array_pos2] == 1) {
				WQ.union(array_pos, array_pos2);
				WQ1.union(array_pos, array_pos2);
			}
		}
		if(j-1 > 0) {
			int array_pos3 = gridtolinear(i, j-1);
			if(flag[array_pos3] == 1) {
				WQ.union(array_pos, array_pos3);
				WQ1.union(array_pos, array_pos3);
			}
		}
		if(j+1 <= count ) {
			int array_pos4 = gridtolinear(i, j+1);
			if(flag[array_pos4] == 1) {
				WQ.union(array_pos, array_pos4);
				WQ1.union(array_pos, array_pos4);
			}
		}
		
	}
	
	public boolean isOpen(int i, int j){
		if((i < 1 || i > count) || (j < 1 || j > count)) throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
		int array_pos = gridtolinear(i,j);
		if(array_pos > ((Math.pow(count, 2))-1)) throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
		else {
			return (flag[array_pos] == 1);
		}
	}
	
	private int gridtolinear(int i, int j){
		if((i < 1 || i > count) || (j < 1 || j > count)) throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
		return (count*(i-1)+j)-1;
	}
	
	public boolean isFull(int i, int j){
		if((i < 1 || i > count) || (j < 1 || j > count)) throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
		int array_pos = gridtolinear(i,j);
		return WQ1.connected(array_pos, count*count);
	}
	
	public boolean percolates(){
		return WQ.connected(count*count+1, count*count);
	}
	
	public static void main(String[] args) {
		Percolation p = new Percolation(1);
		int num = 0;
		/* while(!p.percolates()){
			int i = StdRandom.uniform(1, p.count+1);
			int j = StdRandom.uniform(1, p.count+1);
			if(!p.isOpen(i, j)){
			p.open(i, j);
			num  = num+1;
			}
		}  */
		System.out.println(p.isOpen(1, 1));
		p.open(1, 1);
		System.out.println(p.percolates());
		

	}

}

