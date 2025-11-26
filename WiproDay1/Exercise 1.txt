//EXERCISE 1 

import java.util.Scanner;

public class ArithmeticOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int num1 = scanner.nextInt();

        System.out.println("Enter the second number: ");
        int num2 = scanner.nextInt();

        int sum = num1 + num2;
        int difference = num1-num2;
        int product = num1 * num2;
       
        if(num2 != 0)
        {
        int quotient = num1 / num2;
        int remainder = num1 % num2;
        }
        else 
        {
            System.out.println("Cannot divide by zero");
        }


        System.out.println("Sum: "+sum);
        System.out.println("Difference: " +difference);
        System.out.println("Product: " +product);
        System.out.println("Quotient: " +quotient);
        System.out.println("Remainder: " +remainder);

        scanner.close();

        


        
    }
}