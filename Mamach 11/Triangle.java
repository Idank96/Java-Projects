/**
 * This program calculates the area and the perimeter
 * of a given triangle. 
 * @author Idan Kogan
 * @version 27.11.19
 */
import java.util.Scanner;
public class Triangle
{
    public static void main (String []args)
    {
        Scanner scan = new Scanner (System.in);

        System.out.println("This program calculates the area "
            + "and the perimeter of a given triangle.");
        //Ask for input from the user
        System.out.println ("Please enter the three lengths"
            + " of the triangle's sides:");

        //Declare variables - inputs from the user + perimeter + area.
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        double peri; //perimeter 
        double area; //area

        //Check if the inputs are valid and print.
        //By using 3 if statements (and not 1) I can specipid the problem to the user.
        if (a<=0 || b<=0 || c<=0 )
        {
            System.out.println("Your triangle is not valid (triangle's side can't be 0 or less)");
        }
        else if(a+b<=c || a+c<=b || b+c<=a)
        {
            System.out.println("Your triangle is not valid (every pair of sides have to be more then the third.)");
        }
        else
        {
            //Calculate perimeter and area:
            peri = a+b+c;
            area = Math.sqrt((peri/2)*((peri/2)-a)*((peri/2)-b)*((peri/2)-c));
            //Printing the values of the area and the perimeter:
            System.out.println( "The area of the triangle is: " + area);
            System.out.println("The perimeter of the triangle is: " + peri);
        }

    } //end of main method
} // end of class Triangle

