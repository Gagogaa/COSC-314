/*
 * Gregory Mann
 * E01457245
 * Project 2
 * Fall 2019
 *
 * Description:
 * TODO
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
        out.print("Please input the integers 'm' and 'n' separated by a space: ");
        Scanner kbd = new Scanner(System.in);
        int m = 0 , n = 0;
        try
        {
            m = kbd.nextInt();
            n = kbd.nextInt();
        }
        catch (InputMismatchException e)
        {
            out.println("The program expects two int values");
        }
        out.println("Please enter in boolean matrix:");
        int[] colTotals = new int[n + 1];
        boolean isFunction = true;
        int rowTotal = 0;
        for (int i = 0; i < ((m + 1) * (n + 1)); i++)
        {
            try
            {
                int in = kbd.nextInt();
                if (in != 0)
                {
                    colTotals[i % (n + 1)] += 1;
                    rowTotal += 1;
                }
                if (i % (n + 1) == (n))
                {
                    if (rowTotal == 0 || rowTotal >= 2)
                        isFunction = false;
                    rowTotal = 0;
                }
            }
            catch (InputMismatchException e)
            {
                out.println("The program expects int values 0 or 1");
            }
        }

        if (isFunction)
            out.println(classify(colTotals));
        else
            out.println("F is not a function.");
    }


    public static String
    classify(int[] totals)
    {
        boolean oneToOne = true, onto = true;
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
