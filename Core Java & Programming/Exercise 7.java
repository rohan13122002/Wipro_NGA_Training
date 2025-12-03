//2.Multi Dimensional Arrays in java

import java.util.Scanner;

public class MultidimensionalArrayExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // TODO: Write your code here
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        
        int[][] arr = new int[row][col];

        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                arr[i][j]=scanner.nextInt();
            }
        }

        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        
        
        scanner.close();

    }
}