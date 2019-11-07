/*
 * Gregory Mann
 * E01457245
 * COSC 314-1
 * Project 5
 * Fall 2019
 *
 * Description:
 * This program classifies relations as reflexive, symmetric, anti-symmetric and transitive.
 *
 */

import static java.lang.System.out;
import static java.lang.System.arraycopy;
import java.util.InputMismatchException;
import java.util.Scanner;


public class
ClassifyRelation
{

    public static void
    main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        int n = 0;
        out.print("Please input a number for n: ");

        // Read in n
        try
        {
            n = kbd.nextInt();
            if (n < 0)
                throw new InputMismatchException();
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects a positive integer for n");
            System.exit(1);
        }

        boolean[] R = new boolean[n * n];

        // Read in the boolean matrix R
        try
        {
            for (int i = 0; i < (n * n); i++)
                R[i] = (kbd.nextInt() == 1);  // Reads in true if 1 and false if 0
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects int values 0 or 1");
            System.exit(1);
        }

        out.println();

        classifyReflexive(R, n);
        classifySymmetric(R, n);
        classifyAntiSymmetric(R, n);
        classifyTransitive(R, n);
    }


    public static void
    classifyReflexive(boolean[] R, int matSize)
    {
        for (int i = 0; i < matSize; i++)
            if (!R[(i * matSize) + i])
                return;

        out.println("Reflexive");
    }


    public static void
    classifySymmetric(boolean[] R, int matSize)
    {
        for (int i = 0; i < matSize; i++)
            for (int j = 0; j < matSize; j++)
                if (i != j
                    && R[(i * matSize) + j]
                    && !R[(j * matSize) + i])
                    return;

        out.println("Symmetric");
    }


    public static void
    classifyAntiSymmetric(boolean[] R, int matSize)
    {
        for (int i = 0; i < matSize; i++)
            for (int j = 0; j < matSize; j++)
                if (i != j
                    && R[(i * matSize) + j]
                    && R[(j * matSize) + i])
                    return;

        out.println("Anti-symmetric");
    }


    public static void
    classifyTransitive(boolean[] R, int matSize)
    {
        boolean[] R1 = new boolean[matSize * matSize];
        boolean[] R2 = new boolean[matSize * matSize];
        boolean[] R3 = new boolean[matSize * matSize];

        arraycopy(R , 0,              // Source and start location
                  R1, 0,              // Destination and start location
                  matSize * matSize); // Number of elements to copy over

        R2 = mult(R, R1, matSize);
        R3 = xor(R, R2, matSize);

        for (int i = 0; i < matSize * matSize; i++)
            if (R3[i])
                return;

        out.println("Transitive");
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
    xor(boolean[] arrA, boolean[] arrB, int matSize)
    {
        boolean[] res = new boolean[matSize * matSize];
        for (int i = 0; i < matSize * matSize; i++)
            res[i] = arrA[i] ^ arrB[i];

        return res;
    }
}
