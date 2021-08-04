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
//11 is bad
//21 is good


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


