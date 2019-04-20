/*
 * File: MergeSort.java
 * Author: Richard Horvath
 * Date:4/13/19
 * Purpose: Main class used to benchmark recursive and iterative mergesort algorithms.
 *          Has a warmup which runs 10 cycles of the runSorts method. Total time is typically 30-40seconds
 *          
 */
package mergesort;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author richh
 */
public class MergeSort {

    /**
     * @param args the command line arguments
     */
    private static BenchmarkSorts bench = new BenchmarkSorts();
    //warmup code
    //Size of 10 
    private static int WARMUP_SIZE = 10;
    //runs bench.sorts 10 times before main is executed
    static {
	for (int i = 0; i < WARMUP_SIZE; i++) {
            bench.runSorts();
	}
    }//end of warmup
    
    public static void main(String[] args) {

        bench.runSorts();
        bench.displayReport();
        
        
    }
    
}
