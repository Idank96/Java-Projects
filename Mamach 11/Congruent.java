/**
 * This program check if 2 triangles are congruent or not. 
 * @author Idan Kogan
 * @version 27.11.19
 */
import java.util.Scanner;
public class Congruent
{
    public static void main (String[]args)
    {
        Scanner scan = new Scanner (System.in);

        //Declare variables(Vertices)
        double x11, y11, x12, y12, x13, y13;
        double x21, y21, x22, y22, x23, y23;
        //Declare lenths
        double a1, b1, c1;
        double a2, b2, c2;

        //Describe the program
        System.out.println("This program calculates the side's lengths of \n" +
            " a given 2 triangles. Also, it checks if they congruent or not. \n");

        //Ask for input from the user
        System.out.println ("Please enter vertices of the first triangle - 3 pairs of (x,y):");
        x11 = scan.nextDouble(); y11 = scan.nextDouble();
        x12 = scan.nextDouble(); y12 = scan.nextDouble();
        x13 = scan.nextDouble(); y13 = scan.nextDouble();
        System.out.println ("Please enter vertices of the second triangle - 3 pairs of (x,y):");
        x21 = scan.nextDouble(); y21 = scan.nextDouble();
        x22 = scan.nextDouble(); y22 = scan.nextDouble();
        x23 = scan.nextDouble(); y23 = scan.nextDouble();

        //Distance calculate:
        //First triangle
        a1 = Math.sqrt(Math.pow((x12-x11),2)+Math.pow((y12-y11),2));
        b1 = Math.sqrt(Math.pow((x13-x11),2)+Math.pow((y13-y11),2));
        c1 = Math.sqrt(Math.pow((x13-x12),2)+Math.pow((y13-y12),2));
        //Second triangle
        a2 = Math.sqrt(Math.pow((x22-x21),2)+Math.pow((y22-y21),2));
        b2 = Math.sqrt(Math.pow((x23-x21),2)+Math.pow((y23-y21),2));
        c2 = Math.sqrt(Math.pow((x23-x22),2)+Math.pow((y23-y22),2));

        //printing triangles's vertices + distances
        System.out.println("The first triangle is: " + "("+x11+", "+y11+") "
                        +  "("+x12+", "+y12+") " + "("+x13+", "+y13+").  \n"
                        +  "Its lengths are: " + a1 + ", " + b1 + ", " + c1+ "."); 

        System.out.println("The second triangle is: " + "("+x21+", "+y21+") "
                        +  "("+x22+", "+y22+") " + "("+x23+", "+y23+"). \n"
                        +  "Its lengths are: " + a2 + ", " + b2 + ", " + c2 + ". \n");

        //Check if congruent and printing
        if (a1==a2 && (b1==b2 && c1==c2 || b1==c2 && c1==b2) ||
           (a1==b2 && (b1==a2 && c1==c2 || b1==c2 && c1==a2)) ||
           (a1==c2 && (b1==a2 && c1==b2 || b1==b2 && c1==a2)))
            System.out.println("The triangles are congruent.");

        else
            System.out.println("The triangles are not congruent. ");

    } //end of main method
} // end of class Triangle
