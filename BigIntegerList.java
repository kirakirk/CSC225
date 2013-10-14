import java.util.*;
import java.io.*;
/* This class is for creating a singly linked class of big integer nodes. */
/* Hand this in together with your LinkedList.java. */
class BigIntegerList
{
   int n; // Number of items in the list.
   BigIntegerNode start;
   BigIntegerNode rear;

// You can set debug=true when you are debugging and want
// to print out what your program is doing.
// Please leave your debugging messages in your code when
// you submit it. Change debug back to false before
// submitting your code.
   static final boolean debug= false;

   public BigIntegerList()
   {
       n=0;
       start= null;
       rear= null;
   }
   
   //reads in an integer n followed by n big integers.
   //If the program has no more input when it tries to read in n, return null.
   //Otherwise, return a big integer list that contains the big integers in the same order as they appear in the input stream.
   //Code re-used from CSC 230 Fall 2013, Asignment 1B and 1A, assumed to have been written by Dr. Wendy Myrvold 
//For full marks, your code should run in O(n) time assuming that the big integers each have at most some constant number of digits (in contrast to the lab 1 readRear code which runs in O(n2) time).
   public static BigIntegerList readBigIntegerList(Scanner in){
      BigIntegerList problem;
      BigIntegerNode tmp, current;
      LinkedList data;
      int k, i;
      problem = new BigIntegerList();
      k = LinkedList.readInteger(in);
      if (k<0){
         System.err.println("Error: no more Big Integers to read.");
         problem = null;
         return(problem);
      }
      problem.n = k;
      for (i=0; i<k; i++){
         data = LinkedList.readBigInteger(in);
         tmp = new BigIntegerNode(data, null);
         if (i == 0){
            problem.start = tmp;
         }else{
            current = problem.start;
            while(current.next !=null){
               current = current.next;
            }
            current.next = tmp;
         }
         problem.rear = tmp;
      }
       return(problem);
   }
   
   
   //prints a big integer list. To print a big integer x, use x.printBigInteger(8)
   //so that the output is lined up nicely and easier to read for your quickSort routine.
   //Print the output so that only 10 values appear on each line except possibly the last line (which may have less than 10 values).
   //Ensure subsequent output starts on a new line. 
   
   public void printBigIntegerList(){
      
      LinkedList x;
      BigIntegerNode current;
      
      current = start;
      while (current != null){
         x = current.x;
         x.printBigInteger(8);
         current = current.next;
      }
      System.out.println();
      
   }
   
   
   
   
   public void quickSort(int level)
   {
       // Insert your code for Question 5 here.
   }
}
