/*
 * File:MySorts
 * Author:Richard Horvath
 * Date:4/13/19
 * Purpose: Mergesort methods using recursion and iterative. Algorithms used from Geeks for Geeks
 *          References below
 *          Counts are only added in comparisons, timing is done in nanoTime
 */
package mergesort;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author richh
 */
public class MySort implements SortInterface {
    private int count = 0;
    private long startTime = 0;
    private long endTime = 0;
    private long duration = 0;
    
    //helper method to start timer, count and check if sorted upon return
   @Override
    public void recursiveMergeSort(int[] arr, int low, int high) {
        count = 0;
        startTime = System.nanoTime();
        recursiveSort(arr,low,high);
        if(!isSorted(arr)){
            try {
                throw new Exception("Array not sorted - Recursion Sort Error");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }

    @Override
    public void recursiveSort(int[] arr, int low, int high) {
        
        if(low <high){
            
            int middle = (low +high)/2;
            recursiveSort(arr, low, middle);
            recursiveSort(arr, middle + 1, high);
            recursiveMerge(arr, low, middle, high);
           
        }
    }

    @Override
    public void recursiveMerge(int[] arr, int low, int middle, int high) { 
        // Find sizes of two subarrays to be merged 
        int n1 = middle - low + 1; 
        int n2 = high - middle; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i){
            L[i] = arr[low + i];
            
        }
           
        for (int j=0; j<n2; ++j) {
            R[j] = arr[middle + 1+ j];
            
        }
            
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = low; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i];
                i++; 
                count++;
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
                count++;
            } 
            k++; 
            count++;
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        {
            arr[k] = L[i]; 
            i++; 
            k++; 
            count++;
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        {
            arr[k] = R[j]; 
            j++; 
            k++; 
            count++;
        }
        endTime = System.nanoTime();
        
    }

    @Override
    public void iterativeSort(int[] arr, int n) { 
        count = 0;
        startTime = System.nanoTime();
        // For current size of subarrays to 
        // be merged curr_size varies from  
        // 1 to n/2 
        int curr_size;  
                      
        // For picking starting index of  
        // left subarray to be merged 
        int left_start; 
                          
          
        // Merge subarrays in bottom up  
        // manner. First merge subarrays  
        // of size 1 to create sorted  
        // subarrays of size 2, then merge 
        // subarrays of size 2 to create  
        // sorted subarrays of size 4, and 
        // so on. 
        for (curr_size = 1; curr_size <= n-1;  
                      curr_size = 2*curr_size) 
        { 
              
            // Pick starting point of different 
            // subarrays of current size 
            for (left_start = 0; left_start < n-1; 
                        left_start += 2*curr_size) 
            { 
                // Find ending point of left  
                // subarray. mid+1 is starting  
                // point of right 
                int mid = Math.min(left_start + curr_size - 1, n-1); 
          
                int right_end = Math.min(left_start  
                             + 2*curr_size - 1, n-1); 
          
                // Merge Subarrays arr[left_start...mid] 
                // & arr[mid+1...right_end] 
                iterativeMerge(arr, left_start, mid, right_end); 
            } 
        }
        if(!isSorted(arr)){
            try {
                throw new Exception("Array not sorted - Iterative Sort Error");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       
    } 

    @Override
    public void iterativeMerge(int arr[], int l, int m, int r)  { 
        int i, j, k; 
        int n1 = m - l + 1; 
        int n2 = r - m; 
      
        /* create temp arrays */
        int L[] = new int[n1]; 
        int R[] = new int[n2]; 
      
        /* Copy data to temp arrays L[] 
        and R[] */
        for (i = 0; i < n1; i++) 
            L[i] = arr[l + i]; 
        for (j = 0; j < n2; j++) 
            R[j] = arr[m + 1+ j]; 
      
        /* Merge the temp arrays back into 
        arr[l..r]*/
        i = 0; 
        j = 0; 
        k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
                count++;
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
                count++;
            } 
            k++; 
            count++;
        } 
      
        /* Copy the remaining elements of  
        L[], if there are any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
           
        } 
      
        /* Copy the remaining elements of 
        R[], if there are any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
           
        } 
        endTime = System.nanoTime();
         
    }
    
  

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public long getTime() {
        duration = (endTime - startTime);
        return duration;
    }
    public boolean isSorted(int[] arr){
        for(int i = 0;i<arr.length-1;i++){
            if(arr[i]> arr[i+1])
                return false;
        }
        return true;
    }
      
/*
    Iterative Merge Sort. (2019, January 10). Retrieved from https://www.geeksforgeeks.org/iterative-merge-sort/
    
*/
 
    
}
