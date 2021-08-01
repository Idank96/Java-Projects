/**
 * This class represents a Stock in a Supermarket.
 *
 * @author Idan Kogan
 * @version  26.12.19
 */
public class Stock
{
    private FoodItem[] _stock;
    private int _noOfItems;
    private final int MAX_LENGTH = 100;

    /**
     * Stock Constructor - initializing the max length of the stock array and the number of items in the array.
     */
    public Stock() 
    {
        _stock = new FoodItem[MAX_LENGTH];
        _noOfItems = 0;
    } 

    /**
     * gets the number of items in the stock
     * @return the name
     */
    public int getNumOfItems()
    {
        return _noOfItems;
    }

    /**
     * this method adding new item to the stock after checking if possible.
     * If the stock is full it do nothing and returns false.
     * If the new item is existing, only the quantity is added.
     * if the name and the catalogue Number equals but the production and expiry dates are different,
     * the new item is added before the current item.
     * If the catalogue number is less then any catalogue number in the array - add it sorted.
     * This method uses the private method "addSpace" which add free cell in the array to store new item.
     * @param newItem The new item to add
     * @return true if the item added successfully, else (if the stock is full) returns false
     */
    public boolean addItem(FoodItem newItem)
    {
        int i; 
        for(i=0; i<_noOfItems;i++)
        {
            if(newItem.getName().equals(_stock[i].getName()) && newItem.getCatalogueNumber()==_stock[i].getCatalogueNumber()) 
            //if name and catalogue number equals
            {
                if(anyEqual(i, newItem)>=0)
                //if there is any equal items add the quantity.
                {
                    _stock[anyEqual(i, newItem)].setQuantity(newItem.getQuantity()+_stock[anyEqual(i, newItem)].getQuantity());
                    return true;
                }

                if(_noOfItems == MAX_LENGTH)// if the array is full and the fooditem is not equals to any item.
                    return false;

                addSpace(i); 
                _stock[i]= new FoodItem(newItem);
                _noOfItems++;
                return true;
            }
        }
        if(_noOfItems == MAX_LENGTH)// if the array is full and the fooditem is not equals to any item.
            return false;
        //if needed to add completly new item - add it before first the fooditem that has bigger catalogue number
        for(i=0; i<_noOfItems;i++)
        {
            if (_stock[i].getCatalogueNumber()>newItem.getCatalogueNumber())
            {
                addSpace(i);
                _stock[i]= new FoodItem(newItem);
                _noOfItems++;
                return true;
            }
        }
        _stock[_noOfItems++] = new FoodItem(newItem); //if the item isn't in the array and it has the bigger catalogue number or the array is empty
        return true;
    }

    /**
     * returns a list of items to be ordered
     * only if the quantity of item is less the the amount param.
     * @param amount amount of order
     * @return String list of items to be ordered
     */

    public String order(int amount) 
    {
        String orderString = ""; // value to stored the list

        for (int i=0 ; i<_noOfItems-1; i++)
        {
            if(namePlusCatalogueNumber(i) && _stock[i].getQuantity()+_stock[i+1].getQuantity()<amount)
            //check this cell and the next one: 1) if the name and catalogue number are equals and 2) if the sum of them is less then the amount parameter.
            {
                orderString += _stock[i].getName() + ", ";
                if(_noOfItems!=MAX_LENGTH)
                    i++; // skip the next one which is equal in name and catalogue number.
            }
            else if(namePlusCatalogueNumber(i) && _stock[i].getQuantity()+_stock[i+1].getQuantity()>=amount)
            //check if the name and catalogue number of two closed cells are equals and if the sum of them is equal or more the amount parameter.
            {
                if(_noOfItems!=MAX_LENGTH)
                    i++; // skip the next one which is equal in name and catalogue number.
            }
            else if(_stock[i].getQuantity()<amount)
            {

                orderString += _stock[i].getName() + ", ";   
            }
        }

        if(_stock[_noOfItems-1].getQuantity()<amount)
            orderString += _stock[_noOfItems-1].getName(); // check if the last item in the list need to be added - end without: ","

        return orderString;
    }
    
    /**
     * returns how many items can move to the fridge with this temprature.
     * @param temp temprature in fridge
     * @return number of items which matches the fridge.
     */
    public int howMany(int temp)
    {
        int itemsInFridge = 0; // value to store the number of items
        for(int i=0; i<_noOfItems; i++)
        {
            if(_stock[i].getMinTemperature()<=temp && temp<=_stock[i].getMaxTemperature())
                itemsInFridge += _stock[i].getQuantity();
        }

        return (0<itemsInFridge) ? itemsInFridge : 0;

    }

    /**
     * updates the stock after the sale.
     * if the quantity of item is 0, delete it from the array/stock.
     * @param itemsList array of strings, which is the items that was sold.
     */

    public void updateStock(String[] itemsList)
    {
        int i; // place of itemsList
        int j; // place of _stock
        int p; // variable for inner loop,
        for(i=0; i<itemsList.length; i++)
        {
            for(j=0; j<_noOfItems; j++)
            {
                if(itemsList[i].equals(_stock[j].getName())) // if the names are equal, delete 1 from the quantity.
                {
                    _stock[j].setQuantity(_stock[j].getQuantity()-1);

                    if(_stock[j].getQuantity() == 0 ) //if the quantity is equal to 0
                    {
                        for(p=j;p<(_noOfItems-1); p++)// delete the place in the array
                        {
                            _stock[p] = _stock[p+1];
                        }
                        _stock[p] = null; // store null in the last cell
                        _noOfItems--;
                        if(_noOfItems==0)
                            break;
                        j--; //for checkinig again this cell in the array
                        break;

                    }
                }
                if(namePlusCatalogueNumber(j))
                    j++;
            }

        }
    }

    /**
     * remove items that their expiry date has passed. 
     * if the expiry date of item is before the parameter day - delete it  
     * @param d day to compare the expiry date with.
     */

    public void removeAfterDate (Date d)
    {
        int i=0;
        while (i<_noOfItems) //"While loop" because dont know how many items need to be removed
        {
            if(_stock[i].getExpiryDate().before(d))
            {
                int j;
                for(j=i;j<_noOfItems-1; j++)
                {
                    _stock[j] = _stock[j+1];
                }
                _stock[j] =null;
                _noOfItems--;
            }
            else
                i++;

        }
    }

    /**
     * returns the most expensive item in the stock
     * for loop, that stored the bigger price in the items at a time.
     * @return FoodItem object, which has the most expensive price in the stock
     */
    public FoodItem mostExpensive()
    {
        int i;
        int tempPrice = 0;
        FoodItem[] tempArr = new FoodItem[1];

        if(_noOfItems==0)
            return null;

        for(i=0; i<_noOfItems; i++)
        {  
            if(tempPrice<_stock[i].getPrice())
            {
                tempPrice = _stock[i].getPrice();
                tempArr[0] = _stock[i];
            }
        }
        return tempArr[0];
    }

    /**
     * returns how many items in the stock including the quantity.
     * for loop, which stored the sum of the quantities of all items.
     * @return how many items in the stock including the quantity.
     */
    public int howManyPieces()
    {
        int i;
        int sumOfQuantity = 0; 

        if (_noOfItems ==0)
        //if the array is empty
            return sumOfQuantity;

        for(i=0; i<_noOfItems; i++) //sum and store the quantity of all items.
        {  
            sumOfQuantity+=_stock[i].getQuantity();
        }
        return sumOfQuantity;

    }

    /**
     * returns the temprature of the fridge can that stored all the items.
     * If there is no fridge that can store all the items - return default max integer value.
     * @return the temprature of the fridge that can stored all the items.
     */

    public int getTempOfStock()
    {
        int minTemp;
        int maxTemp;
        int i = 0;
        minTemp = _stock[i].getMinTemperature();
        maxTemp = _stock[i].getMaxTemperature();
        for (i=1;i<_noOfItems; i++)
        {
            
            if (maxTemp<_stock[i].getMinTemperature() || _stock[i].getMaxTemperature()<minTemp || (_noOfItems==0) ) 
            {
                return Integer.MAX_VALUE;
            }

            if(minTemp<_stock[i].getMinTemperature() && maxTemp<_stock[i].getMinTemperature()) 
            {
                minTemp = _stock[i].getMinTemperature();
            }

            if(_stock[i].getMaxTemperature()<maxTemp && maxTemp!=_stock[i].getMinTemperature()) 
            {
                maxTemp = _stock[i].getMaxTemperature();
            }

        }
        return minTemp;
    }

    /**
     * toString in class java.lang.Object
     * @return String of all items in the stock
     */

    public String toString()
    {
        String toString= "";
        for(int i=0 ; i<_noOfItems; i++)
            toString += _stock[i].toString() + "\n";
        return toString;    
    } 

    //////private methods://////

    /**
     * private method, which check if the name and the catalogue number of the near next item is the same as this one.
     * @param cell the cell to compare
     * @return true if the equal, else - false
     */ 

    private boolean namePlusCatalogueNumber(int cell)
    {
        if(0<_noOfItems && cell<_noOfItems-1 && _stock[cell].getName().equals(_stock[cell+1].getName()) && _stock[cell].getCatalogueNumber()==_stock[cell+1].getCatalogueNumber())
        //check if the cell parameter is not the last in the array (if its the last one so there is no item after it). Also, if the name and catalogue number
        // after the current name item is the same return true.
            return true;
        return false;
    }
    
    /**
     * private method, which "create free space" inside the array by moving each cell to the next one. 
     * @param cell the cell to become "empty" (it's not really empty, only ready to be stored into)
     */ 
    private void addSpace(int cell)
    {
        for(int j=_noOfItems; cell<j ; j--)
        {
            _stock[j] = _stock[j-1]; // _noOfItems(which is null) is after the last item in the array, so put "_noOfItems-1"(last item) inside.
        }
    }
    
    /**
     * This private method check if there are any equals food items in the stock.
     * @param cell the cell to start the check.
     * @return j the cell in the array where the items are equals.
     */ 
    private int anyEqual(int cell, FoodItem newItem)
    {
        for(int j = cell; j<_noOfItems;j++)
        {
            if(newItem.equals(_stock[j])) //if the items equals return where in the array
                return j;
        }
        return -1;
    }

} //end of class
