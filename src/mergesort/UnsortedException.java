/*
 * File: UnsortedException.java
 * Author: Richard Horvath
 * Date:4/13/19
 * Purpose: User defined exception for unsorted exception
 */
package mergesort;

/**
 *
 * @author richh
 */
public class UnsortedException extends Exception {
    
    public UnsortedException(String s){
        super(s);
    }
    
}
