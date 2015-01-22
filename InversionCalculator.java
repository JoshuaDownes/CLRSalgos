//The following program calculates the amount of inversions in an array in O(nlg(n)) time.
//It functions using a pseudo-mergesort. More specifically, it each element in the array into
//it's own array. Next, it combines arrays one pair at a time, ordering elements from greatest
//to least. For the array on the left, each instance of a higher element represents one instance
//of an inversion based on the amount of remaining elements on the right array, i.e. if there
//is a higher element on the left than the 3 remaining elements on the right, that counts
//as three inversions, and increments the counter as such.
//
//This program is an answer to problem 2-4d in Cormen's Introduction to Algorithms (CLRS).

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.ClassCastException;
import java.util.InputMismatchException;

public class InversionCalculator{

	private static int inversionCounter;
	
	public static void main(String[] args){
		ArrayList<Object> A = new ArrayList<Object>();

		//Prompt for numbers
		Scanner stdin = new Scanner(System.in);
		System.out.print("Enter numbers to be sorted. Enter any other character to end. ");
		int x = 4;
		boolean cont = true;
		while(cont){
			try{ 
				A.add(stdin.nextDouble());
			}
			catch(InputMismatchException ex){
				cont = false;
			}
		}

		System.out.print("Numbers: ");
		for(int i = 0; i < A.size()-1; i++){
			System.out.printf("%.0f, ", A.get(i));
		}
		System.out.printf("%.0f\n", A.get(A.size()-1));
	
		//Call recursive pseudomergesort method.
		pseudoMergeSort(A, 0, A.size()-1);
		
		//Print result
		System.out.println("Total Inversions: " + inversionCounter);
	}
	
	//Recursive pseudo-mergesort method, breaks down sub-arrays until they are length 1, 
	//and then merges and increments inversion counter
	public static void pseudoMergeSort(ArrayList<Object> A, int p, int r){
		if(p<r){
			int q = (p+r)/2;
			pseudoMergeSort(A, p, q);
			pseudoMergeSort(A, q + 1, r);
			mergeAndIncrement(A, p, q, r); 	
		}
	}

	public static void mergeAndIncrement(ArrayList<Object> A, int p, int q, int r){
		int n = q-p+1; 	//# el-ts in left array
		int m = r-q;	//# el-ts in right array
		double[] L = new double[n+1]; //1 extra for sentinel el-t
		double[] R = new double[m+1];
		for(int i = 0; i < n; i++){
			L[i] = (double)A.get(p+i);	
		}
		for(int j = 0; j < m; j++){
			R[j] = (double)A.get(q+1+j);
		}
		L[n] = -1000;
		R[m] = -1000;
		int i = 0, j = 0;
		for(int k = p; k <= r; k++){
			//If el-t on the lefthand side is larger than that on the right, 
			//we have additional inversions equal to the amount of remaining
			//elements on the left.
				
			if(R[j] >= L[i]){
				A.set(k, R[j]);
				j++;
			}
			else{	
				A.set(k, L[i]);
				i++;
				if(m-j>0){
					inversionCounter += m - j;
				}
			}
		}
		
	}

}
							
