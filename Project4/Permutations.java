/*
 * Gregory Mann
 * E01457245
 * COSC 314-1
 * Project 4
 * Fall 2019
 *
 * Description:
 * Lists the permutations of an integer list 1, 2, 3, 4, ... n in lexicographic order.
 *
 */

import static java.lang.System.out;
import java.util.InputMismatchException;
import java.util.Scanner;


public class
Permutations
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

        // Initialize the array 1..n
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i + 1;

        // Add a space between input and output
        out.println();

        // Get the number of permutations, which happens to be n!
        int permCount = n;
        for (int i = 1; i < n; i++)
            permCount *= i;

        // Run for each permutation
        int i = 0;
        do
        {
            // Print out the permutation
            for (int j = 0; j < n; j++)
                out.print(arr[j]);

            out.println();

            // Only do the permutation if were not on the last iteration
            if (i < permCount - 1)
                perm(arr);
        } while (i++ < permCount - 1);
    }

    // The implementation of Fischer-Krause algorithm from the handout
    public static void
    perm(int[] arr)
    {
        int j = arr.length - 2;

        while (arr[j] > arr[j + 1])
            j--;

        int k = arr.length-1;

        while(arr[j] > arr[k])
            k--;

        int temp;
        temp = arr[k];
        arr[k] = arr[j];
        arr[j] = temp;

        int right = arr.length - 1;
        int left = j + 1;

        while (right > left)
        {
            temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
            right--;
            left++;
        }
    }
}