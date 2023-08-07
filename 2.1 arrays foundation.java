/*---------------------------------- INTRO TO ARRAYS ----------------------------------*/

/*
ARRAYS

First, we should understand a little bit of memory. Memory has two parts. Do you know them?
Stack
Heap
When we just write this ->  int[] arr;
It only creates a variable in the stack and has value null by default.

But after that when we allocate memory like this ->  arr = new int[5];
Then to store 5 ints a memory gets allocated in the heap. 
They will have their own address. 
For the time being let's assume the addresses start from 4000. 
Now, these allocated memory units are contiguous, hence they will be spaced out by the same distance. 
So, the next memory location will have 4004 (assuming 4 bytes for int storage) and then 4008, 4012, and so on. 
It doesn't stop there, now arr will store the address of the first memory block/unit among these contiguous memory blocks/units i.e. 4000.

This has 3 major implications:

1.Performance: 
Let's say you have a 1000 long integer array. 
Now if you do arr[500]=7, arr[700]=7. 
Which one do you think will take more time?
Both will take the same time because in both cases we know the exact memory location. 
Let's assume the starting address of arr is 4000 then arr[500] is just 500*4 = 2000 bytes away from 4000 i.e 6000 and arr[700] is just 700*4 = 2800 bytes away from 4000 i.e 6800.

2.Assignment: 
Let's give you a context first. 
Let's say these steps are already performed.

int arr[] one = new int[3];
one [0] = 10;
one [1] = 20;
one [2] = 30;

int [] two = one;
two[1] = 200;

Now if we try to print both one and two, what do you think would be the output? 
If you guessed that both one and two will print 10, 200, 30, then you are absolutely correct. 
Heap will only allocate new memory space only if you use the new keyword. 
Otherwise, a simple shallow copy will take place.

3.Functions: 
When we pass arrays as a parameter to a function only shallow copy takes place. 
And if any changes occur inside the function then the changes will be reflected everywhere. 
*/

//Span of Array
/*
1. You are given a number n, representing the count of elements.
2. You are given n numbers.
3. You are required to find the span of input. Span is defined as difference of maximum value and minimum value.
*/
 public static int span(int n, int[] a){
     int min = a[0];
     int max = a[0];
     
     for(int i = 0; i < n; i++){
         if(a[i] < min){
             min = a[i];
         }
         if(a[i] > max){
             max = a[i];
         }
     }
     int diff = max - min;
     return diff;
 }

//Find Element In An Array
/*
1.You are given a number n, representing the size of array a.
2.You are given n distinct numbers, representing elements of array a.
3. You are given another number d.
4. You are required to check if d number exists in the array a and at what index (0 based). If found print the index, otherwise print -1.
*/
 public static int findIdx(int n, int[] a, int d){
     int res = -1;
     for(int i = 0; i < n; i++){
         if(a[i] == d){
             res = i;
             break;
         }
     }
     return res;
 }

//Bar Chart
/*
We have to create visual bars made of asterisks with the number of asterisks equal to the numeric value of the array contents in the increasing order of indices.

We need to print an asterisk only if it is at the particular required index. 
For that, we first find the maximum element in the array.

We can imagine these asterisk bars as a building of asterisks, comprising of the number of floors as the number of asterisks required. 
Suppose, index 0 has 3, so there needs to be a building of 3 floors (or 3 asterisks). 
The maximum element in the array dictates the number of rows be printed. 
Then we create an inner loop to traverse the array and check if the required row is reached for printing an asterisk 
The if condition in the 'j' iterator loop [ if( max - arr[j] <=i)] checks if the current row for which we are traversing the array is the correct one for printing the asterisk. 
If it satisfies this condition, we print an asterisk with tab space, 
else we just print a tab space.
At the end of the j loop, we print a new line to start printing the next row.

*/
 public static void barChart(int n, int[] a){
    int max = a[0];
    for(int i = 1; i < n; i++){
        if(a[i] > max){
            max = a[i];
        }
    }
    for(int i = 0; i < max; i++){
        for(int j = 0; j < n; j++){
            if(max - a[i] <= i){
                System.out.print("* ");
            }else{
                System.out.print("  ");
            }
        }
        System.out.println();
    }
 }

//Sum Of Two Arrays
/*
1. You are given a number n1, representing the size of array a1.
2. You are given n1 numbers, representing elements of array a1.
3. You are given a number n2, representing the size of array a2.
4. You are given n2 numbers, representing elements of array a2.
5. The two arrays represent digits of two numbers.
6. You are required to add the numbers represented by two arrays and print the
arrays.

We declare and allocate space to a 'sum' array which will store our result. 
The size of this array should be set to whatever is greater among the sizes of the given arrays.
After that, we assign three integer variables to hold the end indices of the three arrays. 
We also initialize a carry variable to store our carry. 
Using these variables, we add the contents of the arrays, separate out carry and then place the resulting required digit at the correct index of the 'sum' array. 
The variables are then decremented till the size of 'sum' is exhausted, with carry added in each step.

We determine the size assignment of the 'sum' array using ternary operators '?' and ':'. '?' denotes the value or statement to be returned if the compared condition is satisfied. 
':' Tells the one to execute when the comparison is false.

But we are only assuming that the size of 'sum' would be equal to one of the arrays and the answer would not exceed this size of array. 
This is wrong as the answer could be greater than both of the given input array sizes. 
To handle this, we will print the leftover carry (if greater than 0), before printing the 'sum' array.
Moving on to how to place values in the 'sum' array, we check for the pointer variables if they are within the scope of their array size. 
If they are, we add them to a digit variable. 
We add the carry to our digit too. 
We then set our carry to the quotient of division of digit by 10 (since our addition is taking place in base -10). 
We set the digit to the remainder of this division, and assign the current index of 'sum' array this digit. 
This process continues till the variable pointing to indices of 'sum' array is within the scope of the 'sum' array size.

//for printing o/p
We print the 'sum' array using a 'for-each' loop. 
In this loop, the iterating variable, rather than accessing the indices of the array elements, assumes the values of the array contents.
It works till the end of the array is reached. So, each time it assumes the value of an array element, we print it and then print a new line.
*/
 public int[] sumOf2Arrays(int n1, int[] a1, int n2, int[] a2){
     int n = (n1 > n2) ? n1 : n2;
     int[] sum = new int[n];
     int c = 0;
     int i = n1 -1;
     int j = n2 - 1;
     int k = n - 1;
     
     while(k >= 0){
         int d = c;
         
         if(i >= 0){//means something is present on that place
             d += a1[i];
         }
         if(j >= 0){
             d += a2[j];
         }
         
         c = d / 10;
         d = d % 10;
         
         sum[k] = d;
         
         i--;
         j--;
         k--;
     }
     if(c > 0){
         int[] newSum = new int[n + 1];
         newSum[0] = c;
         for(int i = 0; i < n; i++){
             newSum[i + 1] = sum[i];
             return newSum;
         }
     }
     return sum;
 }

//Difference Of Two Arrays
/*
1. You are given a number n1, representing the size of array a1.
2. You are given n1 numbers, representing elements of array a1.
3. You are given a number n2, representing the size of array a2.
4. You are given n2 numbers, representing elements of array a2.
5. The two arrays represent digits of two numbers.
6. You are required to find the difference of two numbers represented by two arrays and print the arrays. a2 - a1
Assumption - number represented by a2 is greater.

We choose the size of the 'diff' array to be equal to the size of array a2, the larger array out of the given two arrays.
But this may result in the problem of accommodating a leading zero in our result array.
And we do not have to print the leading zeroes in resulting answers.

So, in this answer, only 9 9 9 should be printed line-by-line and not the leading zero.
Let us move forward to formulating the solution.
WE follow the same principle as we did in the addition of two arrays. 
We assign tail pointers to the three arrays and we update these pointers as we perform subtraction. 
We will use negative carry to indicate borrow and perform addition of 10 when the carry is taken.
This negative carry is then added to the next index subtraction of arrays to adjust the previous borrow.
If the shorter array (if there is a shorter array) exhausts before the complete subtraction, 
we handle the subtraction process by placing 0 for them and then subtract as usual.

There is also the issue of outputting numbers line-by-line as we do not have to print the leading zeroes in the answer, 
and as shown in the case above, there could leading zeroes in answers. 
So, we will find the index where the first non-zero entry occurs from the starting index of the array, 
and then from that index print our whole array ('diff') line-by-line.

"we CAN omit the third variable 'k' because it had the same use as the variable j, since the array 'diff' and 'a2' has the same size, they could use the same pointer variable."
*/
public static int[] diffOf2Arr(int n1, int[] a1, int n2, int[] a2){
    int[] diff = new int[n2];//given n2 > n1
    int c = 0;
    int i = n1 - 1;
    int j = n2 - 1;
    int k = diff.length - 1;
    
    while(k >= 0){
        int d = 0;
        int a1val = (i >= 0) ? a[i] : 0;//if a1 is smaller then a2, fill 0 in front of a1 places
        if(a2[j] + c >= a1[i]){//normal subtraction
            d = a2[j] + c - a1[i];
            c = 0;
        }else{//need to have carry
           d = a2[j] + c + 10 - a1[i]; 
           c = -1;//for change in next value
        }
        diff[k] = d;
        i--;
        j--;
        k--;
    }
    //to handle preceeding 0's in diff ans 
    int idx = 0;
    while(idx < diff.length){
        if(diff[idx] == 0){
            idx++;
        }else{
            break;
        }
    }
    int[] ans = new int[diff.length - idx];
    int anslen = diff.length - idx;
    for(int i = 0; i < anslen; i++){
        ans[i] = diff[idx];
        idx++;
    }
    return ans;
}

//Reverse An Array
/*
We are given an array and we have to write the method definition for the void type function reverse which takes in a single parameter, which is the array to be reversed.

We reverse the array by assigning integer variables to point at the start and end of the array, 
swapping the contents of the indices pointed by them, then incrementing and decrementing the start pointer and end pointer. 
This process goes on till the start pointer does not exceed the end pointer, which is when we know, the given array has been reversed.
*/
  public static void reverse(int[] a){
    int i = 0;
    int j = a.length - 1;
    while(i < a.length && j > 0 && i < j){
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
        i++;
        j--;
        
    }
  }

//or
public static void reverse (int[]a){
    for (int i = 0; i < a.length / 2; i++)
    {
      int temp = a[i];
      a[i] = a[a.length - i - 1];
      a[a.length - i - 1] = temp;
    }
}

//
/*

*/












/*---------------------------------- 2D ARRAYS ----------------------------------*/
