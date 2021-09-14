//print Z from *
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("*****\n   *\n  *\n *\n*****");

    }
}

//print equilateral triangle
import java.util.*;

public class Main{
    public static void main(String[] args){
        System.out.println("  *\n * *\n* * *");
    }
}

//grading system IF-ELSE 
import java.util.*;
  
  public class Main{
  
  public static void main(String[] args) {
      // input - don't change this code
      Scanner scn = new Scanner(System.in);
      int marks = scn.nextInt();
      // input - don't change this code
      
      if(marks > 90)
        System.out.println("excellent");
        
      else if(marks > 80 && marks <= 90)
        System.out.println("good");
        
      else if(marks > 70 && marks <= 80)
        System.out.println("fair");
        
      else if(marks > 60 && marks <= 70)
        System.out.println("meets expectations");
        
      else
        System.out.println("below par");
  
   }
  }

//Quiz Question *************************************************************************************************
public class Main{
    public static void main(String[] args){
    int i = 10;
    if(i++ == i)
 	 System.out.println(i + " is good");
    else
 	 System.out.println(i + " is bad");
 
    int j = 20;
    if(++j == j)
 	 System.out.println(j + " is good");
    else
 	 System.out.println(j + " is bad");
    } 
}

//OUTPUT
//11 is bad             i++ : increments value by 1 but still uses original value
//21 is good            ++i : first increments value of 'i' and then uses updated value of variable


//Quiz Question *******************************************************************************************************************
int main(){
    int a, b, c, d;
    cin>>a>>b>>c>>d;
    cout<<(max(a,b), max(c,d))<<endl;
    cout<<(min(a,b), min(c,d))<<endl;
    return 0;   
}

//OUTPUT
//max(c, d)
//min(c, d)            because commma separation m second expression is evaluated

//is a number prime
public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
  
       int t = scn.nextInt();
       for(int i = 0; i < t; i++){
           int n = scn.nextInt();
           int count = 0;
           for(int j = 2; j*j <= n; j++){
               if(n % j == 0){
                  count++;
                   break;
                   
               } 
                    
              
           }
           if(count == 0)
            System.out.println("prime"); 
           else
            System.out.println("not prime");
       }
  
   }

//print all primes till N
 public static void main(String[] args) {
        //input
        Scanner sc = new Scanner(System.in);
        int low = sc.nextInt();
        int high = sc.nextInt();
        //loop from low to high
        for(int i = low; i <= high; i++){
            //is prime condition for i
            int count = 0;
            for(int div = 2; div * div <= i; div++){
                if(i % div == 0){
                    count++;
                    break;
                }
            }
            
            if(count != 1)
                System.out.println(i);
        }
        
    }

//print Fibonacci Numbers till n
public static void main(String[] args) {
      //input
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      //loop till input term with condition
      int a = 0;
      int b = 1;
      System.out.println(a);
      System.out.println(b);
      for(int i = 2; i < n; i++){
          int c = a+b;
          a = b;
          b = c;
            
          System.out.println(b);
      }
      
   }

//Count Digits In A Number
public static void main(String[] args) {
    //divide the number by 10 till it is > 0 and update its value simultaneously and maintain a count for each division return count as number of digits
    
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int count = 0;
    while(n > 0){
        count++;
        n = n / 10;
    }
    
    System.out.println(count);
 }

//Digits Of A Number
public static void main(String[] args) {
        //print digits left to right
      // find total no of digits; make 10s number with no. of zeroes = digits -1, using math.pow by typecasting it to int, and use it as divisor; run a loop till divisor > 0 divide the number by obtained 10s no.i.e. divisor and print quotient; store remainder also for that divisor, and divide divisor after that by 10
      
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      int m = n;
      int d = n;
      
      //no. of digits
      int digits = 0;
      while(m > 0){
          m = m /10;
          digits++;
      }
      
      int div = (int)Math.pow(10, digits -1);
      
      while(div > 0){
        int ans = d/div;
        d = d % div;
        div = div/10;
        System.out.println(ans);
      }
     }

//Reverse A Number
public static void main(String[] args) {
     // run a loop, take modulo of no. to 10 and print the remainder, update the no. by dividing by 10, print the answer
     Scanner sc = new Scanner(System.in);
     int n = sc.nextInt();
   
     while(n > 0){
         int r = n % 10;
         n = n / 10;
       
         System.out.println(r);
     }
 }

//Inverse Of A Number
public static void main(String[] args) {
    //make a variable ans; run a loop from 1 to no of digits; take modulo of no. with 10 gives remainder; update value of ans by adding i*10^(remainder -1) to it, update the no.n by dividing by 10; return ans
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = n;
    
    int ans = 0;
    int digits = 0;
    while(m > 0){
        m = m/10;
        digits++;
    }
   
    for(int i = 1; i <= digits; i++){
        int r = n % 10;
        int mult = (int)Math.pow(10, r - 1);
        
        ans += i * mult;
        
        n = n/10;
        
    }
    System.out.println(ans);
 }

//Rotate A Number
public static void main(String[] args) {
        // if k > no. of digits -> k = k % digits
        // if k < 0 -> (k + digits) % digits
        
        //     find modulo of no. with 10/100/1000... no. of zeroes = k, store the remainder and update the no.
        //     first print the remainder and then the no.
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        int digits = 0;
        int m = n;
        while(m > 0){
            m /= 10;
            digits++;
        }
        
        
        if(k > digits){
            k = k % digits;
        }
        if(k < 0){
            k = -(Math.abs(k) % digits) + digits;
        }
        
            int div = (int)Math.pow(10, k);
            int r = n % div;
            n = n / div;
            if(r == 0){
                System.out.print(n);
                return;
            }
            if(n == 0){
                System.out.print(r);
                return;
            }
                
            System.out.print(r+""+n);
         
    }
// Another approach for this program is to multiply the remainder with remaining no.of digits of the no.
//     and then adding the quotient(remaining digits of number to updated value of remainder) ans = r*10^power + quotient


//Gcd And Lcm
public static void main(String[] args) {
        // for gcd -> we'll make one no. as divisor and 
        // another as dividend 
        // then we'll divide them and if remainder is left
        // we'll use that remainder as divisor for next calculation and previous divisor as dividend for further calculations
        // when remainder comes zero we make that divisor as gcd
        
        //for lcm we'll use the formula gcd*lcm=n1*n2
        
        Scanner sc = new Scanner(System.in);
        int n1= sc.nextInt();
        int n2= sc.nextInt();
        
        int m1 = n1;
        int m2 = n2;
        
        while(m1 % m2 != 0){
            int r = m1%m2;
            m1 = m2;
            m2 = r;
            
        }
        
        int gcd = m2;
        int lcm = (n1 * n2)/gcd;
        
        System.out.println(gcd);
        System.out.println(lcm);
        
     }


//Prime Factorisation Of A Number (see video on youtube for explanation)
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        for(int div = 2; div * div <= n; div++){
            while(n % div == 0){
                System.out.print(div + " ");
                n = n / div;
            }
            
        }
        if(n != 0)
            System.out.print(n);
 }


//Pythagorean Triplet
public static void main(String[] args) {
        // hypotenuese is the longest side also 
        // H2 = P2 + B2
        // we'll find max of a, b, c
        // and compare max^2 to remaining sides square ka sum
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        
        int hpt = Math.max(Math.max(a, b), c);
        
        //imp
        int a2 = (int)Math.pow(a, 2);
        int b2 = (int)Math.pow(b, 2);
        int c2 = (int)Math.pow(c, 2);
        int hpt2 = (int)Math.pow(hpt, 2);
        
        if(hpt2 == (a2 + b2 + c2 - hpt2)){
            System.out.println("true");
            return;
        }
        
        System.out.println("false");
        
   }


//The Curious Case Of Benjamin Bulbs
public static void main(String[] args) {
        // by seeing the pattern in Dry Run
        // we saw that odd no. of fluctuations are
        // giving on bulb
        // so numbers which have odd no. of factors 
        // will be on 
        // and only perfect squares have odd no. of 
        // factors and rest all have even factors
        // we also inferred this by looking at on no. bulbs 
        // in dry run
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 1; i*i <= n; i++){
            System.out.println(i*i);
        }
   }


//Digit Frequency
public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int d = scn.nextInt();
        int f = getDigitFrequency(n, d);
        System.out.println(f);
    }

    public static int getDigitFrequency(int n, int d) {
        //we'll find each digit by taking modulo
        // with 10
        // if d = remainder found count++ 
        // update n by dividing by 10
        
        int count = 0;
        while(n > 0){
            int r = n%10;
            if(r == d)
                count++;
            n = n / 10;
        }
        return count;
    }

//Decimal To Any Base
public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int n = scn.nextInt();
      int b = scn.nextInt();
      int dn = getValueInBase(n, b);
      System.out.println(dn);
   }
  
   public static int getValueInBase(int n, int b){
       //divide by that to which needs to be converted
       //multiply the remainder with given base^no. of times division -1 and add in ans
       int count = 0;
       int ans = 0;
       while(n > 0){
           int r = n % b;
           ans += r * ((int)Math.pow(10, count));
           n /= b;
           count++;
       }
       return ans;
   }

//Any Base To Decimal
public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int n = scn.nextInt();
      int b = scn.nextInt();
      int d = getValueIndecimal(n, b);
      System.out.println(d);
   }
  
   public static int getValueIndecimal(int n, int b){
      //divide by 10 (that to which needs to be converted)
       //multiply the remainder with given base^no. of times division -1 and add in ans
       int count = 0, ans = 0;
        while(n > 0){
            int r = n % 10;
            ans += r * ((int)Math.pow(b, count));
            n /= 10;
            count++;
        }
        return ans;
   }

//Any Base To Any Base
public class Main{
      //convert from base1 to decimal
      //decimal to base2
  
  public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int n = scn.nextInt();
      int sourceBase = scn.nextInt();
     int  destBase= scn.nextInt();
     int a = b2d(sourceBase, n);
     int ans = d2b(destBase, a);
     System.out.println(ans);
   }
   public static int b2d(int b1, int n){
      
       int sum = 0;
        int count = 0;
       while(n > 0){
           int r = n % 10;
           n = n/10;
           sum += r*((int)Math.pow(b1, count));
           count++;
           
       }
       return sum;
   }
   
   public static int d2b(int b2, int n){
       int sum = 0, count = 0;
       while(n > 0){
           int r = n % b2;
           n /= b2;
           sum += r * (int)Math.pow(10, count);
           count++;
       }
       return sum;
       
   }
  }
    
//Any Base Addition
public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int b = scn.nextInt();
      int n1 = scn.nextInt();
      int n2 = scn.nextInt();
  
      int d = getSum(b, n1, n2);
      System.out.println(d);
   }
  
   public static int getSum(int b, int n1, int n2){
      
       int ans = 0, count= 0, carry = 0;
       while(n1 > 0 || n2 > 0 || carry > 0){
           
           int r1 = n1 % 10;
           int r2 = n2 % 10;
           int s = r1 + r2 + carry;
           
           
          if(s < b){
              carry = 0;
              ans += s * ((int)Math.pow(10, count));
          }else{
              int s1 = s % b;
              carry = s / b;
              ans += s1 * ((int)Math.pow(10, count));
          }
           n1 /= 10;
           n2 /= 10;
           count++;
       }
       return ans;
   }
// editorial
public static int getSum(int b, int n1, int n2) {
    int rv = 0;
    int p = 1;
    int c = 0;
    while ( n1 > 0 || n2 > 0 || c > 0) //while numbers are not-nero or carry is non-zero
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

//Any Base Subtraction
public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int b = scn.nextInt();
      int n1 = scn.nextInt();
      int n2 = scn.nextInt();
  
      int d = getDifference(b, n1, n2);
      System.out.println(d);
   }
  
   public static int getDifference(int b, int n1, int n2){
        int ans = 0, carry = 0, count = 0;
        while(n1 > 0 || n2 > 0){
            int r1 = n1 % 10;
            int r2 = (n2 % 10)+carry;
            
            if((r2-r1) < 0){
                ans += (r2 - r1 +b)*((int)Math.pow(10, count));
                carry = -1;
            }else{
                carry = 0;
                ans += (r2 - r1)*((int)Math.pow(10, count));
                
            }
            n1 /= 10;
            n2 /= 10;
            count++;
        }
        return ans;
   }
//editorial
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int b = scn.nextInt();
    int n1 = scn.nextInt();
    int n2 = scn.nextInt();
    int d = getDifference(b, n1, n2);
    System.out.println(d);
  }
  public static int getDifference(int b, int n1, int n2) {
    int rv = 0;
    int p = 1;
    int c = 0;
    while ( n2 > 0 ) // we only need to run till minuend doesnt become zero
      //we know minuend is greater than subtrahend
    {
      int d1 = n1 % 10; //rightmost digit extraction
      int d2 = n2 % 10;
      d2 += c; //settling previous carries (or borrow)
      int d = 0;
      if ( d2 >= d1) //if minuend greater than subtrahend
      {
        c = 0;
        d = d2 - d1;
      }
      else // minuend less than subtrahend
      {
        c = -1; // set carry = -1 to be settled in next iteration
        d = d2 + b - d1; // add base
      }
      rv += d * p;
      p *= 10;
      n1 /= 10;
      n2 /= 10;
    }
    return rv;
  }
}

//Any Base Multiplication

    
    
    
    
    
    
    
    
    
