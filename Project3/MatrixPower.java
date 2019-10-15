/*
 * Gregory Mann
 * E01457245
 * COSC 314-1
 * Project 3
 * Fall 2019
 *
 * Description:
 * TODO Write the description
 *
 */

import static java.lang.System.out;
import static java.lang.System.arraycopy;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MatrixPower
{
    public static void
    main(String[] args)
    {
        out.print("Please input the size of the matrix n: ");
        Scanner kbd = new Scanner(System.in);
        int n = 0;
        try
        {
            n = kbd.nextInt();
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects one integer n for the size of the matrix");
            System.exit(1);
        }

        boolean[] A = new boolean[n * n],
                  M = new boolean[n * n],
               Temp = new boolean[n * n];

        out.println("Please input the boolean matrix:");
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
        arraycopy(A, 0, Temp, 0, n * n);
        arraycopy(A, 0, M, 0, n * n);
        for (int i = 1; i < n; i++)
        {
            Temp = mult(Temp, A, n);

            out.println("Matrix Temp");
            matPrint(Temp, n);
            out.println();

            M = or(M, Temp, n);

            out.println();
            out.println("Matrix M");
            matPrint(M, n);
            out.println();
        }
        out.println();
        matPrint(M, n);
        out.println();
    }


    public static void
    matPrint(boolean[] arr, int matSize)
    {
        for (int i = 0; i < matSize * matSize; i++)
        {
            out.print(arr[i] ?  "1 " : "0 ");
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
