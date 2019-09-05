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
 *
 * NOTE that the implementation does not actually create the set A. It just prints the powersets of A.
 * It exploits the fact that the set is always sequential 1..N
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
        // Prompt the user
        out.print("Please input a positive Integer: ");
        Scanner kbd = new Scanner(System.in);
        boolean err = false;
        int input = 0;

        // Get user input and check if its valid
        try
        {
            input = kbd.nextInt();
        }
        catch (InputMismatchException e)
        {
            err = true;
        }

        // Make sure that the input is positive
        if (input < 0)
            err = true;

        // If the input is invalid print an error message and exit the program
        if (err)
        {
            out.println("Program input must be positive Integer.");
            System.exit(1);  // Exit with an error code
        }

        // If the input is valid start the algorithm
        out.println('{');

        // Bit shifting, in this case (1 << input), is a replacement for the power function when the base is two
        // e.g. (2 ^ 3) == (1 << 3) == 8
        int permutationCount = (1 << input);

        // Loop through all of the permutations of the set
        // Just counting from 0 to the number of permutations will do, albeit in a weird order
        for (int i = 0; i < permutationCount; i++)
        {
            out.print(genSetString(i));
            // Check if we need to output the trailing comma
            if ((i + 1) < (1 << input))
                out.println(',');
            else
                out.println("");
        }
        out.println("}");
    }

    /*
     * Given a bit pattern produce the corresponding set as a String
     *
     * E.g. If seq is 0001 0101
     * Bit Number |  8 7 6 5  4 3 2 1
     * -----------+-------------------
     * Seq        |  0 0 0 1  0 1 0 1
     * Output     | {      5,   3,  1}
     *
     */
    public static String
    genSetString(int seq)
    {
        String res = "  {";

        // Checks each bit of seq and add the corresponding bit number to the result
        // The stop condition is when the most significant bit of the test is larger than seq.
        for (int bit = 0; (1 << bit) <= seq; bit++)
        {
            // Use bit as a mask to check if the bit number should be printed out
            if ((seq & (1 << bit)) != 0)
            {
                // Append the BIT number to the sequence
                res += Integer.toString(bit + 1);

                // Check if we need to add in the trailing comma
                if ((1 << (bit + 1)) <= seq)
                    res += ',';
            }
        }

        return res + '}';
    }
}
