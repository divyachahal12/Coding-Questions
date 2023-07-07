//Digit Frequency
/*
1. You are given a number n.
2. You are given a digit d.
3. You are required to calculate the frequency of digit d in number n.

We already have the knowledge of extracting digits out of a number and printing them one-by-one. 
We apply the same concept here, keep dividing the number by 10, and check the remainder digit with the given digit. 
If they match, we increase a counter variable initialized from zero. In the end, we return the counter variable.

For example, 994543234 is given to us with digit 4

First, divide 994543234 by 10; we get remainder as 4 and quotient 9954323.

As the remainder is equal to the given digit, we increase the counter by 1. 
Continuing this process, we will get a total of 3 4s in the given number.
*/
public static int getDigitFrequency(int n, int d) {
        int count = 0;
        while(n > 0){
            int r = n % 10;
            n = n/10;
            if(d == r){
                count++;
            }
        }
        return count;
    }

//Decimal To Any Base
/*
You are given a decimal number n.
2. You are given a base b.
3. You are required to convert the number n into its corresponding value in base b.

Initialize the variables:
ans is set to 0, which will store the converted value.
i is set to 0, which will be used as an exponent for the base.
Enter a while loop that continues until n becomes 0:
Inside the loop, the remainder r is calculated by performing the modulo operation n % b. 
This gives us the remainder when n is divided by b, representing the least significant digit in the base b representation.
n is updated by performing integer division n / b, which effectively removes the least significant digit from n.
The expression r * (int)Math.pow(10, i) calculates,
the decimal value of the digit obtained in the previous step (r) multiplied by the corresponding power of 10 based on its position (i).
The calculated value is added to ans using the += operator.
i is incremented by 1 to move to the next digit's position.
Once the loop ends, the converted value ans is returned.
*/
public static int getValueInBase(int n, int b){
       int ans = 0;
       int i = 0;
       while(n > 0){
           int r = n % b;
           n = n / b;
           ans += r * (int)Math.pow(10, i);
           i++;
       }
       return ans;
   }

//Any Base To Decimal
/*
Time Complexity :
We are extracting digits of number n and performing some minute calculations, which will take O(log10 n) time as there can be maximum floor(log10 n) digits in a number n.
*/
public static int getValueIndecimal(int n, int b){
      int ans = 0;
      int i = 0;
      while(n > 0){
          int r = n % 10;
          ans += r * (int)Math.pow(b, i);
          n = n/10;
          i++;
      }
      return ans;
   }

//Any Base To Any Base
/*
O(log10 n)
*/
   public static int srctodestbase(int n, int src, int dest){
       int ans = 0;
       int i = 0;
       while(n > 0){
           int r = n % dest;
           n = n / dest;
           ans += r * (int)Math.pow(src, i);
           i++;
       }
       return ans;
   }

//Any Base Addition
/*
1. You are given a base b.
2. You are given two numbers n1 and n2 of base b.
3. You are required to add the two numbes and print their value in base b.

(after adding the last digits of the no.s we'll take into account to convert decimal to base b function, as sum calc is in decimal base, but we need ans in given base b)

we can see that we are extracting the rightmost digit of the numbers at a time, 
adding these digits up,
and if the sum of these digits exceeds the base digit, 
we divide it by the base number.
Upon division, the quotient is sent over to the next higher place value addition as "carry" a
nd the remainder is written as the final sum in that particular place value. 
This process takes place until any of the two numbers does not become zero or the "carry" does not have zero in it.
*/
   public static int getSum(int b, int n1, int n2){
       int ans = 0;
       int i = 0;
       int carry = 0;
       while(n1 == 0 && n2 == 0 && carry == 0){
           int r1 = n1 % 10;
           int r2 = n2 % 10;
           int sum = r1 + r2 + carry;
           if(sum >= b){
               carry = sum / b;
               sum = sum % b;
           }
           ans += sum * (int)Math.pow(10, i);
           i++;
           n1 = n1 /10;
           n2 = n2/ 10;
       }
       return ans;
   }

//Any base Subtraction
/*
We are given three numbers in this problem, a base number 'b' and two numbers belonging to that base 'n1' and 'n2' where n2 is the greater number. 
We have to find the difference between the two while adhering to the laws of subtractions.

The process is a bit similar to the any base addition. 
We extract right most digits, but here, we instead of adding them up, we subtract them. 
If the minuend (the number to be subtracted from) is smaller than the subtrahend (the number to be subtracted), 
we take a borrow from the next higher place value and reduce its value by 1.

But we are not taking the number that is in the higher placer value, but a single instance of occurrence of the base number, 
so, we add the base number to our current minuend and then carry on with the subtraction process. 
We make the carry = -1, and when the subtraction of next extracted digits take place, 
we first settle the previous carry by adding this carry to the current minuend (had we written borrow, 
we would have subtracted borrow from the current minuend, but instead we are working with negative valued carries).

After that, we again check if the remaining minuend is greater than the subtrahend or not. 
If it is greater than the subtrahend, we follow normal subtraction and set carry = 0, 
else we change the state of carry = -1 and add the base number to the minuend and then subtract the subtrahend.
*/
   public static int getDifference(int b, int n1, int n2){
       int ans = 0;
       int c = 0;
       int i = 0;
       while(n2 > 0){// we only need to run till minuend doesnt become zero
      //we know minuend is greater than subtrahend
          int r2 = n2 % 10;
          int r1 = n1 % 10;
          r2 += c;//settling previous carries (or borrow)
          int diff = 0;
          
          if(r2 < r1){
              diff = (r2 + b) - r1;
              c = -1;
          }else{
              diff = r2 - r1;
              c = 0;
          }
          ans += diff*(int)Math.pow(10, i);
          n2 = (n2 / 10);
          n1 = n1 / 10;
          i++;
       }
       return ans;
   }

//Any Base Multiplication
/*
In this problem we are given a base number 'b', with two numbers 'n1' and 'n2', which would act as two parameters for the operation of multiplication. 
We will try and break this problem modularly, because, we know multiplication is repeated addition. 
Since we already know how to add any base numbers, we will implement it in our solution for any base multiplication. 
We need to remember the purpose for breaking down our problem into modules, 
that is, we should write maintainable, clean code and most importantly, not repeat ourselves (DRY rule: Do not Repeat Yourself).

We know in general multiplication, as we are taught in grade 2 or 3, we start with the rightmost digit of the multiplier and multiply the whole multiplicand with it. 
We write this product down and move on to the digits towards the left, one-by-one. 
As we write product for the succeeding digits, we first put as many crosses in the product line, as the number of the digits we have multiplied with the multiplicand yet. 
This is to accommodate the place value updates in the number, and this is why we get the correct answer.

We will repeat the same procedure in this solution, 
extracting the rightmost digit of the multiplier one-by-one, getting the products, and then adding them to our result value, 
with, of course, one place value added (the accommodation of cross(es) for solution).

Since we have to add the products of every single digit multiplication, we will use the getSum() function we wrote for any base addition.

We will also make use of a helper function (call it getProductWithSingleDigit) to multiply the digits extracted from the multiplier and multiplying it with the multiplicand.

Now that we have successfully built the helper method and have the knowledge of any base addition method 'getSum()',
we are ready to build our final solution method, getProduct(), which will return the final answer in our variable 'result', for printing purpose.
*/
 public static int getSingleDigitProduct(int b, int d2, int n1){
     int ans = 0;
     int c = 0;
     int i = 0;
     while(n1 > 0 || c > 0){
         int d1 = n1 % 10;
         n1 = n1 / 10;
         
         int prod = d1 * d2 + c;
         c = prod / b;
         prod = prod % b;
         ans += prod*(int)Math.pow(10, i);
         i++;
     }
     return ans;
 }
 public static int getSum(int b, int n1, int n2) {
    int rv = 0;
    int p = 1;
    int c = 0;
    while ( n1 > 0 || n2 > 0 || c > 0) //while numbers are not-zero or carry is non-zero
    {
      int d1 = n1 % 10; //extracting digits
      int d2 = n2 % 10;
      int d = d1 + d2 + c; //adding up extracted digits
      c = d / b; //storing carry for next addition
      d = d % b;
      rv += d * p;
      p *= 10;
      n1 /= 10;
      n2 /= 10;
    }
    return rv;
  }
 public static int getProduct(int b, int n1, int n2){
     int ans = 0;
     int i = 0;
     while(n2 > 0){
         d2 = n2 % 10;
         n2 = n2 / 10;
         int singleProd = getSingleDigitProduct(b, d2, n1);
         singleProd = singleProd * (int)Math.pow(10, i);
         ans = getSum(b, ans, singleProd);
         i++;
     }
     return ans;
 }






