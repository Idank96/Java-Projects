
/**
 * Represents a matrix of two dimensional array
 *
 * @author Idan Kogan
 * @version 26.12.19
 */
public class Matrix
{
    private int[][] _matrix; 
    private final int minWhite = 0; 
    private final int maxBlack = 255;
    private final int DEFAULT_VAL = 0;
    private final int FIRST_CELL = 0;

    /**
     * Constructs a Matrix from a two-dimensional array
     * The dimensions as well as the values of the Matrix will be the same as
     * the dimensions and values of the two-dimensional array.
     * @param array int array[][] for Matrix
     */
    public Matrix(int[][] array)
    {
        _matrix = new int[array.length][array[0].length];
        for (int i=0; i<array.length; i++)
        {
            for (int j=0; j<array[i].length; j++)
            {
                _matrix[i][j] = array[i][j]; 
            }
        }

    }

    /**
     * Constructs a size1 by size2 Matrix of DEFAULT_VAL(0).
     * @param size1 size of arrey [size1][]
     * @param size2 size of arrey [][size2]
     */
    public Matrix(int size1, int size2)
    {  
        _matrix = new int[size1][size2];
        for(int i=0 ; i<_matrix.length ; i++)
        {
            for(int j=0 ; j<_matrix[i].length ; j++){
                _matrix[i][j] = DEFAULT_VAL;
            }
        }
    }


    /**
     *return the negative image of the matrix
     *every cell\point become the opposite of himself (250 become 5, 100 become 155 etc...)
     * @return new Matrix that have been vertically flipped
     */

    public Matrix makeNegative() 
    {
        int i,j;
        int [][] negativeMatrix = new int[_matrix.length][_matrix[0].length]; //new matrix to store the values
        for (i=0; i<_matrix.length; i++)
        {
            for (j=0; j<_matrix[i].length; j++)
            {
                if(maxBlack-_matrix[i][j]>minWhite)
                    negativeMatrix[i][j] = maxBlack-_matrix[i][j]; 
            }  
        }
        return new Matrix (negativeMatrix); 
    }

    /**
     * return the image filter average.
     * Calculating the average number of the cells around a specific cell (including itself) 
     * the method checks if there is a cell around a specific cell.
     * @return new Matrix which is the image filter average.
     */
    public Matrix imageFilterAverage()
    {
        int[][] averageMatrix = new int[_matrix.length][_matrix[0].length];
        int i,j;

        for (i=0 ; i<_matrix.length ; i++)
        {
            for(j=0 ; j<_matrix[0].length ; j++)
            {
                int temp = 0;
                temp = _matrix[i][j];
                int numOfCells = 1;
                if (i+1<_matrix.length) // down
                {
                    temp += _matrix[i+1][j];
                    numOfCells++;
                }
                if (j+1<_matrix[i].length) // right
                {
                    temp += _matrix[i][j+1];
                    numOfCells++;
                }
                if (FIRST_CELL<=j-1) // left
                {
                    temp += _matrix[i][j-1];
                    numOfCells++;
                }
                if (FIRST_CELL<=i-1) // up
                {
                    temp += _matrix[i-1][j];
                    numOfCells++;
                }
                if  (i+1<_matrix.length && j+1<_matrix[i].length )  // right down corner
                {    
                    temp += _matrix[i+1][j+1];
                    numOfCells++;
                }
                if  (FIRST_CELL<=i-1 && j+1<_matrix[i].length )  // right up corner
                {    
                    temp += _matrix[i-1][j+1];
                    numOfCells++;
                }
                if  (FIRST_CELL<=i-1 && FIRST_CELL<=j-1)  // left up corner
                {    
                    temp += _matrix[i-1][j-1];
                    numOfCells++;
                }
                if  (i+1<_matrix.length && FIRST_CELL<=j-1 )  // left down corner
                {
                    temp += _matrix[i+1][j-1];
                    numOfCells++;
                }

                averageMatrix[i][j]= (temp/numOfCells); 
            }
        }
        return new Matrix (averageMatrix);
    }

    /**
     * rotate the matrix clockwize.
     * it uses 2 nested loops to rotate the matrix.
     * @return new Matrix rotated clockwise form original Matrix
     */

    public Matrix rotateClockwise()
    {
        int i,j; // row and col of this matrix.
        int iRotete, jRotete; // row and column of the new rotated matrix
        int[][] rotatedMatrix = new int[_matrix[0].length][_matrix.length]; // (switching the lengths)

        for(i=0, jRotete = _matrix.length-1 ;i<_matrix.length;i++,jRotete--)
        {
            for(j=0,iRotete=0 ; iRotete<_matrix[0].length; iRotete++,j++)
            {
                rotatedMatrix[iRotete][jRotete] = _matrix[i][j];
            }
        }

        return new Matrix (rotatedMatrix);  
    }
    
    /**
     * rotate the matrix counter clockwize.
     * it uses 2 nested loops to rotate the matrix.
     * @return new Matrix rotated clockwise form original Matrix
     */
    public Matrix rotateCounterClockwise() 
    {
        int i,j; // row and column of this matrix.
        int iRotete, jRotete; // row and column of the new rotated matrix
        int [][] rotateCounterClockwise = new int [_matrix[0].length][_matrix.length]; // (switching the lengths)

        for(i=0, jRotete = 0 ; i<_matrix.length ;i++,jRotete++)
        {
            for(j=0,iRotete=_matrix[0].length-1 ; j<_matrix[0].length ; j++, iRotete--)
            {
                rotateCounterClockwise[iRotete][jRotete] = _matrix[i][j];
            }
        }

        return new Matrix (rotateCounterClockwise);  
    }

    /**
     * toString in class java.lang.Object
     * return the string of the Matrix in format: [0][0]+tab+[0][1]...new line[1][0]+tab+[1][1]...
     * @return Matrix String
     */
   
    public String toString()
    {
        String stringMatrix = "";
        int i,j;
        for (i=0; i<_matrix.length; i++)
        {
            for (j=0; j<_matrix[i].length; j++)
            {
                stringMatrix += Integer.toString(_matrix[i][j]);
                stringMatrix += "\t";

            }  
            stringMatrix += "\n";
        }

        return stringMatrix;
    }

}//end of Matrix class
