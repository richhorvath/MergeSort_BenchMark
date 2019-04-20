/*
 * File: BenchmarkSorts.java
 * Author: Richard Horvath
 * Date:4/13/19
 * Purpose: Generates random arrays of 10 different sizes, runs both recursive and 
 *          iterative mergesorts, calculates mean, standard deviation and coefficient of variance
 *          displays all information in formated system output. 
 */
package mergesort;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author richh
 */
public class BenchmarkSorts {
    //one of each used separately later
    private MySort recSort;
    private MySort iterSort;
    //arrays to store counting and time information for each input size and run
    private long[][] timesRec = new long[10][50];
    private int[][] countsRec = new int[10][50];
    private int[][] countsIter = new int[10][50];
    private long[][] timesIter = new long[10][50];
    
    //array of input sizes
    private int[] sizes = {10,50,100,500,1000,5000,10000,15000,30000,90000};
    
    
    //method for creating random int inputs
    public int[] seedInputs(int[] arr){
        Random random = new Random();
        for(int i = 0; i<arr.length;i++){
            arr[i] = (int) (Math.random()* 10000)+1;
        }
        return arr;
    }

    /*Runs mergesort recursive iterative and stores infomration in respective arrays*/
    public void runSorts(){
        
        for(int i = 0; i<sizes.length;i++){
            //new recursive and iterative objects created for each data size
            recSort = new MySort();
            iterSort = new MySort();
            //runs each data size 50 times with new data seeds 
            for(int j = 0; j<50; j++){
                 int[] arr1 = new int[sizes[i]];
                 seedInputs(arr1);
                 //copy of seeded array for iterative mergesort
                 int[] arr2 = Arrays.copyOf(arr1, arr1.length);
                 //calls recursive sort
                 recSort.recursiveMergeSort(arr1, 0, arr1.length-1);
                 //stores counts and time stamps
                 countsRec[i][j]=recSort.getCount();
                 timesRec[i][j]=recSort.getTime();
                
                iterSort.iterativeSort(arr2, arr2.length);
                //stores counts and time stamps
                countsIter[i][j]=iterSort.getCount();
                timesIter[i][j]=iterSort.getTime();
            }
            
        }
        
    }//end of runsorts
    
    public void displayReport(){
        //average count rec
        double[] averageCountsRec =findMean(countsRec);
        //average count iter
        double[] averageCountsIter =findMean(countsIter);
        //average time rec
        double[] averageTimeRec = findMean(timesRec);
        //average time iter
        double[] averageTimeIter = findMean(timesIter);
        //COVs each recursive and iterative data points
        double[] countRecCOV = COV(standardDeviation(averageCountsRec,countsRec),averageCountsRec);
        double[] countIterCOV = COV(standardDeviation(averageCountsIter,countsIter),averageCountsIter);
        double[] timeRecCOV = COV(standardDeviation(averageTimeRec,timesRec),averageTimeRec);
        double[] timeIterCOV = COV(standardDeviation(averageTimeIter,timesIter),averageTimeIter);
        //prints out data using system out format
        System.out.println("****************Recursive**********************************************************************************      Iterative ****************************************************************");
         System.out.println("Data Size\t"+"Average Op Count\t" + "COV of count\t"+ "Avg Execution Time(nanoseconds)\t\t" + "COV of time\t"+"Average Op Count\t" + "COV of count\t"+ "Avg Execution Time(nanoseconds)\t\t" + "COV of time");
        for(int i = 0; i< 10;i++){
            System.out.printf("%-10d\t%-10.2f\t\t%-10.6f\t\t%-10.2f\t\t\t%-10.8f\t%-10.2f\t\t%-10.6f\t\t%-10.2f\t\t\t%-10.8f\n", sizes[i],averageCountsRec[i],countRecCOV[i],averageTimeRec[i], timeRecCOV[i],averageCountsIter[i], countIterCOV[i],averageTimeIter[i],timeIterCOV[i]);

        }
        
        
        
    }
    //calcultes coefficient of variance
    private double[] COV(double[] standardDev, double[] mean){
        double[] cov = new double[10];
        for(int i = 0; i <10;i++){
            //standard deviated divided by the mean 
            cov[i] = standardDev[i]/mean[i];
        }
        return cov;
    }
    
    //standard deviation method for int or count
    private double[] standardDeviation(double[] mean, int[][] arr){
        double[][] subArr = new double[10][50];
        double[] standardDev = new double[10];
        for(int i = 0;i<10;i++){
            for(int j = 0; j<50; j++){
                subArr[i][j]= Math.pow((arr[i][j]-mean[i]),2);
            }
        }
        
        double[] subAvg = findMean(subArr);
        
        for(int i = 0;i<10;i++){
            standardDev[i] = Math.sqrt(subAvg[i]);
        }
        return standardDev;
    }
    
    //standard deviation method for time
    private double[] standardDeviation(double[] mean, long[][] arr){
        double[][] subArr = new double[10][50];
        double[] standardDev = new double[10];
        for(int i = 0;i<10;i++){
            for(int j = 0; j<50; j++){
                subArr[i][j]= Math.pow((arr[i][j]-mean[i]),2);
            }
        }
        
        double[] subAvg = findMean(subArr);
        
        for(int i = 0;i<10;i++){
            standardDev[i] = Math.sqrt(subAvg[i]);
        }
        return standardDev;
    }
    
    
    //find mean methods
    private double[] findMean(int[][]arr){
        double[] average = new double[10];
        
        
        for(int i = 0;i<average.length;i++){
            double sum = 0;
             for(int j = 0;j<50;j++){
              sum = sum +arr[i][j];   
             }
            
             average[i] = (sum/50);
        }
        return average;
    }
    private double[] findMean(double[][]arr){
        double[] average = new double[10];
        
        
        for(int i = 0;i<average.length;i++){
            double sum = 0;
             for(int j = 0;j<50;j++){
              sum = sum +arr[i][j];   
             }
            
             average[i] = (sum/50);
        }
        return average;
    }
   private double[] findMean(long[][]arr){
        double[] average = new double[10];
        
        
        for(int i = 0;i<average.length;i++){
            double sum = 0;
             for(int j = 0;j<50;j++){
              sum = sum +arr[i][j];   
             }
            
             average[i] = (sum/50);
        }
        return average;
    }
    
    /**References 
     * https://ncalculators.com/statistics/coefficient-of-variance-calculator.htm
     **/
    
}

