/*
 * Gregory Mann
 * E01457245
 * COSC 314-1
 * Project 3
 * Fall 2019
 *
 * Description:
 * Prints the transitive closure of a matrix given the size of the matrix (n) and the matrix (A)
 */


import static java.lang.System.out;
import static java.lang.System.arraycopy;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TransitiveClosure
{
    public static void
    main(String[] args)
    {
        out.print("Please input the size of the matrix (n): ");
        Scanner kbd = new Scanner(System.in);
        int n = 0;
        // Read in n from the keyboard
        try
        {
            n = kbd.nextInt();
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects one integer n for the size of the matrix");
            System.exit(1);
        }
        // Setup the matrices
        boolean[] A = new boolean[n * n],
                  M = new boolean[n * n],
               Temp = new boolean[n * n];
        out.println("Please input the boolean matrix (A):");
        // Read in the boolean matrix A
        try
        {
            for (int i = 0; i < (n * n); i++)
            {
                int in = kbd.nextInt();
                A[i] = (in == 1);  // Reads in true if 1 and false if 0
            }
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects int values 0 or 1");
            System.exit(1);
        }
        // Set Temp and M to A
        // Temp is used for calculating A^n
        // M is used for the "rolling total" e.g. (A) | (A^n-1) | (A^n)
        arraycopy(A   , 0, // Source and start location
                  Temp, 0, // Destination and start location
                  n * n);  // Number of elements to copy over
        arraycopy(A, 0,
                  M, 0,
                  n * n);
        for (int i = 1; i < n; i++)
        {
            // Compute A^i
            Temp = mult(Temp, A, n);
            // Or A^i with the 'rolling total'
            M = or(M, Temp, n);
        }
        out.println();
        out.println("The transitive closure of A is:");
        matPrint(M, n);
        out.println();
    }


    public static void
    matPrint(boolean[] arr, int matSize)
    {
        for (int i = 0; i < matSize * matSize; i++)
        {
            out.print(arr[i] ?  "1 " : "0 ");
            // Print a newline character if this iteration is the end of the line
            if (i != 0 && ((i + 1) % matSize == 0))
                out.println();
        }
    }


    public static boolean[]
    mult(boolean[] arrA, boolean[] arrB, int matSize)
    {
        boolean[] res = new boolean[matSize * matSize];
        for (int i = 0; i < matSize; i++)
            for (int j = 0; j < matSize; j++)
                for (int k = 0; k < matSize; k++)
                    // The indexing calculations are of the format `(row * row length) + col` because I'm using flat matrices
                    res[(i * matSize) + j] |= arrA[(i * matSize) + k] && arrB[(k * matSize) + j];
        return res;
    }


    public static boolean[]
    or(boolean[] arrA, boolean[] arrB, int matSize)
    {
        boolean[] res = new boolean[matSize * matSize];
        for (int i = 0; i < matSize * matSize; i++)
            res[i] = arrA[i] | arrB[i];
        return res;
    }
}
