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



