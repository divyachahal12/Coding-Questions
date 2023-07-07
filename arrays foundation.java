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

//
/*

*/









/*---------------------------------- 2D ARRAYS ----------------------------------*/
