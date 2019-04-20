/*
 * File: SortInterFace.java
 * Author: Richard Horvath
 * Date: 4/13/19
 * Purpose: interface for mergesort methods
 */
package mergesort;

/**
 *
 * @author richh
 */
public interface SortInterface {
    public void recursiveMergeSort(int[] arr, int low, int high);
    public void recursiveSort(int[] arr, int low, int high);
    public void recursiveMerge(int[] arr, int low, int middle, int high);
    
    public void iterativeSort(int[] arr, int n);
    public void iterativeMerge(int arr[], int l, int m, int r);
    
    public int getCount();
    public long getTime();
    
    
}
