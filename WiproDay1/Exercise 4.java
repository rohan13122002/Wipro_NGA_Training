//4. Find whether given number is even or odd.

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        //write your answer here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        if( n%2 == 0 )
        {
            System.out.println("No is Even");
        }
        else 
        {
            System.out.println("No is odd");
        }
    }
}