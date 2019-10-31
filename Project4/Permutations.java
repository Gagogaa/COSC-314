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
        int n = -1;
        out.println("Please input a number for n");
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
        for (int i = 1; i < n + 1; i++)
            arr[i-1] = i;

        out.println();  // Add a space between input and output
        try
        {
            while (true)
            {
                for (int j = 0; j < n; j++)
                    out.print(arr[j]);
                out.println();
                perm(arr);
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {}
    }


    public static void
    perm(int[] arr)
    {
        int j = arr.length-2;

        while (arr[j] > arr[j + 1])
            j--;

        int k = arr.length-1;

        while(arr[j] > arr[k])
            k--;

        int temp;
        temp = arr[k];
        arr[k] = arr[j];
        arr[j] = temp;

        int right = arr.length-1;
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