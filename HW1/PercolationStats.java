
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.*;


public class PercolationStats {
	private int num, tri;
	private double[] result;
	
	   public PercolationStats(int n, int t)    // perform trials independent experiments on an n-by-n grid
	   {
		   num = n;
		   tri =t;
		   result = new double [t];
		   for (int i =0; i<tri; i++)
		   {
			   result [i] = run(n);
		   } 
		   
	   }
	   
	   
	   private double run ( int n)
	   {
		   Percolation perc = new Percolation(n);
		   while ( perc.percolates() == false)
		   {
			   int row = StdRandom.uniform( 1, n+1);
			   int col = StdRandom.uniform( 1, n+1);
			   perc.open(row,col);
		   }
		   
		   double result = (double) perc.numberOfOpenSites()/(n*n);
		   return result;
	   }
	   
	   
	   public double mean()                          // sample mean of percolation threshold
	   {
		   return StdStats.mean(result);
		   
	   }
	   
	   
	   public double stddev()                        // sample standard deviation of percolation threshold
	   {
		   return StdStats.stddev(result);
	   }
	   
	   
	   public double confidenceLo()                  // low  endpoint of 95% confidence interval
	   {
		   double conlo = mean() - 1.96*stddev() / Math.sqrt(tri);
		   return conlo;
	   }
	   
	   
	   public double confidenceHi()                  // high endpoint of 95% confidence interval
	   {
		   double conhi = mean() + 1.96*stddev() / Math.sqrt(tri);
		   return conhi;
	   }

	   
	   public static void main(String[] args)    // test client (described below)
	   {
		   System.out.print("please enter the n: ");
		   Scanner in = new Scanner( System.in);
		   int n = in.nextInt();

		   System.out.print("please enter the t: ");
		   int t = in.nextInt();
		   
		   PercolationStats stats = new PercolationStats(n, t);
		   System.out.println("mean( )                  =  "+ stats.mean());
		   System.out.println("stddev( )                 =  "+ stats.stddev());
		   System.out.println("confidenceLow( )   =  "+ stats.confidenceLo());
		   System.out.println("confidenceHigh( )  =  "+ stats.confidenceHi() );

		   
		   
	   }
	   
	}