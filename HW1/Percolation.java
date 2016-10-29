

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.*;


public class Percolation {
	private int[ ] [ ] grid = null;
	private static WeightedQuickUnionUF weightedUF;
	private int num;  // = n
	private int count; 
	

	
	   public Percolation(int n)               // create n-by-n grid, with all sites blocked
	   {
		   grid = new int [ n][n];
	   		num = n;
		   for (int i = 0; i<n; i++)
		   {
			   for ( int j = 0; j<n; j++)
			   {
				   grid[i][j] =0;        // "0" means that grid is blocked. 		   
			   }
		   }

		  weightedUF = new WeightedQuickUnionUF(n*n+2);  // from 0 - n*n+1,  and 0, n*n+1 are the bottom and top
		   
		  /*
		  for ( int i =1; i<= n ; i++)
		   {
			   weightedUF.union( 0 , i);
		   }
		   for ( int i = (n * (n - 1) +1) ; i <= n*n; i++)
		   {
		   	weightedUF.union ( i , n*n +1);
		   }
		   */
		   
	   }
	   
	   
	   public void open(int i, int j)          // open site (row i, column j) if it is not open already
	   {
		   if(isOpen(i, j) == false)
		   {
			   grid [ i-1 ] [ j-1 ] = 1;
			   count ++; 
			   
			   int row = i-1;
			   int col =j-1;
			   int tonum = toNum ( row , col);
			   
			 if ( row !=0 && row != num-1 && col != 0 && col != num-1)   //the middle 
			 {
				   if ( grid[ row-1] [col] == 1)
					   weightedUF.union(toNum(row-1,col), tonum);
				    if ( grid[ row+1] [col] == 1)
					   weightedUF.union(toNum(row+1,col), tonum);
				    if ( grid[ row] [col-1] == 1)
					   weightedUF.union(toNum(row,col-1), tonum);
				    if ( grid[ row] [col+1] == 1)
					   weightedUF.union(toNum(row,col+1), tonum);
			   }
			   
			   else if (row == 0 && row != num-1 && col != 0 && col != num-1) // the first row without corner
			   {
				   weightedUF.union( 0 , tonum);
				   if ( grid[ row+1] [col] == 1)
					   weightedUF.union(toNum(row+1,col), tonum);
				    if ( grid[ row] [col-1] == 1)
					   weightedUF.union(toNum(row,col-1), tonum);
				    if ( grid[ row] [col+1] == 1)
					   weightedUF.union(toNum(row,col+1), tonum);
			   }
			   
			   else if (row != 0 && row == num-1 && col != 0 && col != num-1) // the last row without corner
			   {
				  
				   if ( grid[ row -1] [col] == 1)
					   weightedUF.union(toNum(row-1,col), tonum);
				   if ( grid[ row] [col-1] == 1)
					   weightedUF.union(toNum(row,col-1), tonum);
				   if ( grid[ row] [col+1] == 1)
					   weightedUF.union(toNum(row,col+1), tonum);
				   
				   if(isFull(i,j) == true)
					   weightedUF.union ( tonum , num*num +1);
			   }
			   
			   else if ( row !=0 && row != num-1 && col == 0 && col != num-1)   //the first col
			   {
				   if ( grid[ row-1] [col] == 1)
					   weightedUF.union(toNum(row-1,col), tonum);
				    if ( grid[ row+1] [col] == 1)
					   weightedUF.union(toNum(row+1,col), tonum);
				    if ( grid[ row] [col+1] == 1)
					   weightedUF.union(toNum(row,col+1), tonum);
			   }
			   
			   else if ( row !=0 && row != num-1 && col != 0 && col == num-1)   //the last col
			   {
				   if ( grid[ row-1] [col] == 1)
					   weightedUF.union(toNum(row-1,col), tonum);
				    if ( grid[ row+1] [col] == 1)
					   weightedUF.union(toNum(row+1,col), tonum);
				    if ( grid[ row] [col-1] == 1)
					   weightedUF.union(toNum(row,col-1), tonum);
			   }
			   else if ( row == 0 && col == 0)  //left top
			   {
				   weightedUF.union( 0 , tonum);
				   if ( grid[ row+1] [col] == 1)
					   weightedUF.union(toNum(row+1,col), tonum);
				   if ( grid[ row] [col+1] == 1)
					   weightedUF.union(toNum(row,col+1), tonum);
			   }
			   else if (row ==0 && col == num - 1)  //right top
			   {
				   weightedUF.union( 0 , tonum);
				   if ( grid[ row+1] [col] == 1)
					   weightedUF.union(toNum(row+1,col), tonum);
				   if ( grid[ row] [col-1] == 1)
					   weightedUF.union(toNum(row,col-1), tonum);
			   }
			   else if (row == num-1 && col == 0)  // left bottom
			   {
				   if ( grid[ row-1] [col] == 1)
					   weightedUF.union(toNum(row-1,col), tonum);
				   if ( grid[ row] [col+1] == 1)
					   weightedUF.union(toNum(row,col+1), tonum);
				   
				   if(isFull(i,j) == true)
					   weightedUF.union ( tonum , num*num +1);
			   }
			   else if (row == num-1 && col ==num-1)
			   {
				   
				   if ( grid[ row-1] [col] == 1)
					   weightedUF.union(toNum(row-1,col), tonum);
				    if ( grid[ row] [col-1] == 1)
					   weightedUF.union(toNum(row,col-1), tonum);
				    
				    if(isFull(i,j) == true)
						   weightedUF.union ( tonum , num*num +1);
			   }
		   }
		   
		   
		   
		   
	   }
	   
	   
	   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
	   {
		   if ( grid[i -1] [j-1 ] == 1)
		   	return true;
		   else
		   	return false;
		   
	   }
	   
	   
	   public boolean isFull(int i, int j)     // is site (row i, column j) full?
	   {
		   int tonum = toNum ( i-1 , j-1);
		   return weightedUF.connected ( 0, tonum);
		   
	   }
	   
	   public boolean percolates()             // does the system percolate?
	   {
		   return weightedUF.connected ( 0, num*num +1);
		   
	   }

	   private int toNum ( int row, int col)   //transfrom the row (include0), column(include 0)to the number
	   {
	   		return   row* num + col +1;
	   }
	   
	   public int numberofOpenSites()
	   {
		   return count;
	   }

	   
	   public int numberOfOpenSites()
	   {
		   return count;
	   }
	   
	   
	   
	   public static void main(String[] args)  // test client (optional)
	   {
		   System.out.println("please enter the n: ");
		   Scanner in = new Scanner( System.in);
		   int n = in.nextInt();
		   Percolation perc = new Percolation(n);
		   while ( perc.percolates() == false)
		   {
			   int row = StdRandom.uniform( 1, n+1);
			   int col = StdRandom.uniform( 1, n+1);
			   perc.open(row,col);
		   }
		   
		   double result = (double)perc.count/(n*n);
		   System.out.println(" the result is:" + result);
		   
	   }
	   
	}
