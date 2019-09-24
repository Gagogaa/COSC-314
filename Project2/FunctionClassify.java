/*
 * Gregory Mann
 * E01457245
 * COSC 314-1
 * Project 2
 * Fall 2019
 *
 * Description:
 * This program determines if its inputs are a function and if that function is one to one, onto, or a bijection.
 *
 */

import static java.lang.System.out;
import java.util.InputMismatchException;
import java.util.Scanner;


public class
FunctionClassify
{
    public static void
    main(String[] args)
    {
        out.print("Please input the integers 'm' (Domain) and 'n' (Codomain) separated by a space: ");
        Scanner kbd = new Scanner(System.in);
        int m = 0 , n = 0;
        // Read in m and n
        try
        {
            m = kbd.nextInt();
            n = kbd.nextInt();
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects two int values one for m and one for n");
            System.exit(1);
        }
        out.println("Please enter in boolean matrix:");
        // Initialize the arrays for storing col totals
        int[] colTotals = new int[n + 1];
        boolean isFunction = true;
        int rowTotal = 0;
        // Loop through the true false matrix counting row totals and column totals
        try
        {
            for (int i = 0; i < ((m + 1) * (n + 1)); i++)
            {
                int in = kbd.nextInt();
                // If the input is one add this column to the column totals and row totals
                if (in = 1)
                {
                    colTotals[i % (n + 1)] += 1;
                    rowTotal += 1;
                }
                // If we're at the end of the line check the row total and reset rowCount
                if (i % (n + 1) == (n))
                {
                    if (rowTotal == 0 || rowTotal >= 2)
                        isFunction = false;
                    rowTotal = 0;
                }
            }
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects int values 0 or 1");
            System.exit(1);
        }

        if (isFunction)
            // If F is a function then print out if its one to one, onto, or a bijection
            out.println(classify(colTotals));
        else
            out.println("F is not a function.");
    }


    // Returns what kind a function F is by examining the row totals
    public static String
    classify(int[] totals)
    {
        boolean oneToOne = true, onto = true;
        // If any of the rows are >= 2 then the function is not one to one
        // If any of the rows are less than 1 then the function is not onto
        for (int x: totals)
        {
            if (x >= 2)
                oneToOne = false;
            if (x == 0)
                onto = false;
        }
        if (oneToOne && onto)
            return "F is a Bijection.";
        if (oneToOne)
            return "F is one to one.";
        if (onto)
            return "F is onto.";
        return "F is not one to one or onto.";
    }
}
