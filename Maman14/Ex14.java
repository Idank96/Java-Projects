
/**
 * Maman 14  
 *
 * @author Idan Kogan
 * @version Jan 2020
 */

public class Ex14
{
    /**
     * this method run one loop and counting every time there is 'c'.
     * time efficiency: O(n) - one loop in the length of the string param.
     * space efficiency: O(3) = O(1) - three variables. 
     * @param s string to search on.
     * @param c the character need to be counted .
     * @return the number of substrings betweed 2 'c', in which there is one 'c'.
     */
    public static int subStrC (String s, char c)
    {

        int cCount=0; //number of 'c'
        int wordNumber=0; //number of words

        for(int i=0; i<s.length();i++)
        {
            if(s.charAt(i)==c) //if 'c' is found in the String
                cCount++;
            if (cCount==3) ////if there are 3 'c' in the String.
            {
                wordNumber++;
                cCount--;
            }
        }
        return wordNumber; // return the number of "words".
    }

    /**
     * This method counts the number of repetitions of a given character, if there is more than 1,
     * it starts counting how many substrings according to the k parameter.
     * time efficiency: O(n+k) = O(n). - two sperate loops.
     * space efficiency: O(3) = O(1) - three variables.
     * @param s string to search on.
     * @param c the character need to be counted. 
     * @param k the max of times the charcter can consider inside a substring.
     * @return the number of substrings according to the k and c parameters.
     */

    public static int subStrMaxC(String s, char c, int k)
    {
        int i;//the char place of the string.
        int cCount=0; // represents a start of a new word.
        int subString=0; //number of valid "words".

        if(s=="") //if the string is empty.
            return subString;
        for(i=0; i<s.length();i++) // loop to count the 'c'.
        {
            if(s.charAt(i)==c) //if 'c' is found in the String
            {
                cCount++;
            }   

        }
        if(cCount>=2)
        {
            subString = cCount-1; //there are at least one substring less then the c numbers.
            if(k==0)
            {
                return subString;//return the number of "cCount-1".
            }
            else if(cCount>2)
            {
                while (k>0 && cCount>1) //add to the subString the cCount according to the numbers of k.
                {
                    cCount-=1;
                    subString+=(cCount-1);
                    k--;
                }
            }
        }
        return subString;//return the number of substrings.

    }
    /**
     * This method changes every '1' in the array to his distance from the nearest zero (from left or right).
     * the method uses 2 seperate loops - the first from the beggining of the array, and the second from the end of the array. 
     * time efficiency: O(2n) = O(n) - one loop in the length of the string param.
     * space efficiency: O(5) = O(1) - 5 variables. 
     * @param a array of zeros and ones.
     */
    public static void zeroDistance(int[] a)
    {
        int i,j; //'i' for the beginning of array. 'j' for the end of the array.
        int zeroCount = 0; //count the zeros in the array.
        int oneCount = 0; //count the ones in the array.
        
        boolean betweenZero=false; // if we are between 2 '0'.

        for(i=0;i<a.length;i++) 
        // loop from the beginning of the array, that count how many '1' between the zero from the left only (from the smallest to the biggest). 
        {
            if(a[i]==0)
            {
                zeroCount++;
                oneCount=0; // start counting ones (again, if necessary=we found another zero).
                betweenZero=true;
            }
            if(betweenZero && a[i]==1)
            {
                oneCount++; //distanse from the left zero
                a[i]=oneCount; //store the distanse from the left zero.
            }  
        }
        oneCount=0;
        betweenZero=false; 
        
        for(j=a.length-1;j>=0;j--)
        // loop from the end of the array, that count how many '1' between the zero from the right only (from the smallest to the biggest).
        //it
        {
            if(a[j]==0)
            {
                zeroCount--; //found zero, subtract by one.
                oneCount=0; // start counting ones.
                betweenZero=true; //there are '0' from our right.
            }
            if(zeroCount==0 && a[j]==1)//if there is no more '0' so start count the distance from the right zero.
            {
                oneCount++;
                a[j]=oneCount;
            }
            else if(betweenZero && a[j]!=0) //if we are between two zeros.
            {  
                oneCount++;
                if(a[j]>oneCount) // if in this cell there is bigger number then our counter (which mean that we are in the middle distance between two '0'.)
                    a[j]=oneCount; ////store the distanse from the right zero
            }

        }

    }

    /**
     * This method check if the two strings has the same characters but in different number of repetitions of each character, in other words:
     * t has at least one character from the s string.   
     * @param s string to compare t to. 
     * @param t the string that transformed or not.
     * @return return true if the t string transformed from the string s, else return false. 
     */

    public static boolean isTrans(String s, String t) 
    {
        boolean lastCall = false; //boolean value to help follow the strings length.
        if(s.length()==1 && t.length()==1) //if its the last cell
        {
            if(s.charAt(0)==t.charAt(0))
                return true;
            else
                return false;
        }

        if(s.length()==0|| t.length()==0) //if one of the strings is empty.
            return false;

        if((s.length()>1 && t.length()>1) && s.charAt(0)==t.charAt(0) && s.charAt(1)==t.charAt(1)) 
        // if this char and next char are equal - substring (1) for both. (only if its not the last cell))
            return isTrans (s.substring(1), t.substring(1)); 
        if(s.charAt(0)==t.charAt(0)) 
        //if this char is equal in both strings - check if the next char is the same in "t" string
        {
            lastCall = true;
            return isTrans (s, t.substring(1));
        }
            
        if (lastCall && s.charAt(1)==t.charAt(0) )
        //ONLY if the last if statement occured - move on the s string for continue equal them.) 
        {
            lastCall = false;
            return isTrans (s.substring(1),t);
        }

        return false; //defult false.

    } 

    /**
     * This method calls the private countPaths method (overloading), with two parameters that represents the cell in the array.
     * The private method return the number of paths to the last cell in the matrix. 
     * @param mat the matrix
     * @return the number of valid pathes to the last cell in the matrix. (return the overload private method).
     */
    public static int countPaths(int[][] mat)
    {    
        return countPaths(mat, 0, 0);
    }

   
    /* 
    * This private method return the number of paths to the last cell in the matrix. 
    * if there is no path - return 0.
    * the method search for valid path by continue only in valid direction to the last cell.
    * param i the index of the row.
    * param j the index of the  coulmn.
    */
    private static int countPaths(int[][] mat, int i, int j)
    {
        //if both cells are valid. ((add the tens to the i and the ones to the j and the oppsite both valid):
        boolean bothCellsValid = i+(mat[i][j]/10)<=mat.length-1 && j+(mat[i][j]%10)<=mat[0].length-1 && (i+(mat[i][j]%10)<=mat.length-1 && j+(mat[i][j]/10)<=mat[0].length-1);
        
        // continue only with the path the valid (add the tens to the i and the ones to the j or the oppsite.):
        boolean firstOptionValidCell = i+(mat[i][j]/10)<=mat.length-1 && j+(mat[i][j]%10)<=mat[0].length-1; // when there is a cell in this direction. 
        boolean secondOptionValidCell = i+(mat[i][j]%10)<=mat.length-1 && j+(mat[i][j]/10)<=mat[0].length-1; // when there is a cell in this direction. 
        
        if(i==mat.length-1 && j==mat[0].length-1) //if we got to the last cell its a valid path and return 1.
            return 1;
        if (mat[i][j]==0) // if this cell has the number zero so its unvalid path.
            return 0;

        if (bothCellsValid)
            return countPaths(mat, i+(mat[i][j]/10),j+(mat[i][j]%10)) + countPaths(mat, i+(mat[i][j]%10), j+(mat[i][j]/10));
        else if(firstOptionValidCell)
            return countPaths(mat, i+(mat[i][j]/10),j+(mat[i][j]%10));
        else if(secondOptionValidCell)
            return countPaths(mat, i+(mat[i][j]%10), j+(mat[i][j]/10));
        
        return 0; //there is no valid path. 
    }

}

