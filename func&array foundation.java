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





