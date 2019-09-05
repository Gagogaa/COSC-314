/*
 * Gregory Mann
 * COSC 314
 * Section 1
 * Fall 2019
 *
 * Description:
 * This program takes an Integer N and 'creates' a set A.
 * e.g. {1, 2, 3, ..., N}
 * This program then prints the powersets of A.
 * NOTE that the implementation does not actually create the set A. It just prints the powersets of A.
 *
 */

import static java.lang.System.out;
import java.util.InputMismatchException;
import java.util.Scanner;


class PowerSetPrinter
{

    public static void
    main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        boolean err = false;
        int input = 0;

        try
        {
            input = kbd.nextInt();
        }
        catch (InputMismatchException e)
        {
            err = true;
        }

        if (input < 0)
            err = true;

        if (err)
        {
            out.println("Program input must be positive Integer");
            System.exit(1);  // Exit with an error code
        }


        out.println('{');
        for (int i = 0; i < (1 << input); i++)
        {
            out.print(genSeq(i));
            if ((i + 1) < (1 << input))
                out.println(',');
            else
                out.println("");
        }
        out.println("}");
    }

    public static String
    genSeq(int seq)
    {
        String res = "  {";

        for (int shift = 0; (1 << shift) <= seq; shift++)
        {
            if ((seq & (1 << shift)) != 0)
            {
                res += Integer.toString(shift + 1);
                if ((1 << (shift + 1)) <= seq)
                    res += ',';
            }
        }

        return res + '}';
    }
}
