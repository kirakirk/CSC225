//Submisson for Assignment 2B in CSC 225 Fall 2013
//Created by: Dr. Wendy Myrvold 
//Modified by: Kira Kirk, V00705087
//Note: Credit has been given when original code is not used

import java.util.*;
import java.io.*;
public class LinkedList
{
   int n;
   ListNode start;
   ListNode rear;

// You can set debug=true when you are debugging and want
// to print out what your program is doing.
// Please leave your debugging messages in your code when
// you submit it. Change debug back to false before
// submitting your code.
   static final boolean debug= false;

   public LinkedList()
   {
       n= 0;
       start= null;
       rear= null;
   }
   public LinkedList(int size, ListNode first, ListNode last)
   {
       n= size;
       start= first;
       rear= last;
   }

   //reads in one big integer and returns its linked list representation. Returns null if there is no further input remaining.
   //A big integer is given for input in the following manner: first, an integer n_digit representing the number of digits is specified
   //followed by n_digit digits which should be in the range 0 to 9. For example, the integer 2385 is input as 4 2 3 8 5 
   public static LinkedList readBigInteger(Scanner in){
      LinkedList x;
      ListNode tmp;
      int k, data, i;
      x = new LinkedList();
      k = readInteger(in);
      if (k<0){
         System.err.println("Error: no more integers to read.");
         x = null;
         return(x);
      }
      x.n = k;
      for (i=0; i<k; i++){
         data = readInteger(in);
         tmp = new ListNode(data, null);
         if (i == 0){
            x.rear = tmp;
         }else{
            tmp.next = x.start;
         }
         x.start = tmp;
      }
       return(x);
   }
   
   //Operates recursively to reverse the list by first splitting the list into two lists, one with the first floor of n/2 entries and the other with the rest.
   //The variable level is for debugging and represents the level of the recursive call. The initial call should be at level 0.
   //If reverse is called from level k then the resulting invocation is at level k+1.
   public void reverse(int level){
      Test.checkList(this); // Do not remove or move this statement.
      int floor, ceiling, i;
      if (n == 1){
         return;
      } else {
         floor = n/2;
         ceiling = n-floor;
         LinkedList list1 = new LinkedList(floor, start, start);
         LinkedList list2 = new LinkedList(ceiling, start, rear);
         for (i=1; i<floor; i++){
            list1.rear = list1.rear.next;
         }
         list2.start = list1.rear.next;
         list1.rear.next = null;
         
         list1.reverse (level+1);
         list2.reverse (level+1);

         start = list2.start;
         
         rear = list1.rear;

         list2.rear.next = list1.start;

         rear.next = null;
      }
   }

/*
    This method returns:
    -1 if the bigInteger associated with the method is less than y
     0 if the bigInteger associated with the method is equal to y
    +1 if the bigInteger associated with the method is greater than y
   Big integers can have leading zeroes.
Be careful when programming this to ensure that it still works correctly if a bigInteger is compared to itself. 
*/

//Code used in the compare method is taken and slightly modified from code given in CSC 225 written Assignment #2 Fall 2013,
//assumed to have been written by Dr. Wendy Myrvold 
   public int compare(LinkedList y){
      int mostSig = n;
      
      if (n>y.n){
         //compare for leading zeros
         if (leadingZeros(y.n)){
            return (1);
         }
         mostSig = y.n;
      }else if (y.n>n){
         //compare for leading zeros
         if (y.leadingZeros(n)){
            return (-1);
         }
      }
      
      //they have an equal number of significant digits
      return(sigFig(mostSig,y));
   }
   
   //check if the linkedlist number has any leading zeros.
   //will return true if the leading numbers checked are not zero
   //will return flase if the leading numbers are zero
   public boolean leadingZeros(int startChecking){
      
      ListNode current = start;
      
      //to move the pointer to the correct position to start checking for zeros
      for (int i=0; i<startChecking; i++){
         current = current.next;
      }
      //check all remaining digits for zero
      while (current != null){
         if (current.data != 0){
            return (true);
         }
         current = current.next;
      }
      return (false);
   }

   //compares the digits of two linkedlists at the position of mostSig
   public int sigFig(int mostSig, LinkedList y){
      
      ListNode yCurrent, xCurrent;
      
      yCurrent = y.start;
      xCurrent = start;
      
      //base case, if they are equal
      if(mostSig == 0){
         return(0);
      }
      
      //move the pointers to the correct posistions
      for (int i=1; i<mostSig; i++){
         yCurrent = yCurrent.next;
         xCurrent = xCurrent.next;
      }
      
      //check if one digit is larger
      if (xCurrent.data>yCurrent.data){
         return (1);
      }
      if (yCurrent.data>xCurrent.data){
         return (-1);
      }
      
      //else they are equal and the next digit must be checked
      return(sigFig (mostSig-1, y)); 
   }
   
   
   
   
// Tries to read in a non-negative integer from the input stream.
// If it succeeds, the integer read in is returned. 
// Otherwise the method returns -1.
   public static int readInteger(Scanner in)
   {
       int n;

       try{
           n= in.nextInt();
           if (n >=0) return(n);
           else return(-1);
       }
       catch(Exception e)
       {
//        We are assuming legal integer input values are >= zero.
          return(-1);
       }
   }

// You can use this in order to get nicer output
// (lined up in columns).

   public void printBigInteger(int nDigit)
   {
        boolean leadingZero;
        ListNode current;
        int i;
        int n_extra;

        reverse(0);
        leadingZero= true;
        if (n < nDigit)
        {
            for (i=n; i < nDigit; i++)
                System.out.print(" ");
        }
        n_extra= n- nDigit;
        current= start;
        while (current != null)
        {
            if (leadingZero)
            {
                if (current.data != 0)
                   leadingZero= false;
            }
            if (leadingZero)
            {
               if (current.next == null) // value is 0.
                   System.out.print(current.data);
               else if (n_extra <= 0)
                   System.out.print(" ");
            }
            else
            {
                System.out.print(current.data);
            }
            n_extra--;
            current= current.next;
        }
        reverse(0);
   }
} 
