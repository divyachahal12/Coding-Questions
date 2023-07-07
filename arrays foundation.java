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

//
/*

*/








/*---------------------------------- 2D ARRAYS ----------------------------------*/
