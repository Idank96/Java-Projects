
/**
 * This class represents a FoodItem object in a SuperMarket.
 *
 * @author Idan Kogan
 * @version 2020a
 */
public class FoodItem
{
    private String _name; // name of the product
    private long _catalogueNumber; //number of catalogue
    private int _quantity; // quantity in stock
    private Date _productionDate; // date of the production day
    private Date _expiryDate; // date of the expiry of the product
    private int _minTemperature; //minimum temperature of the product
    private int _maxTemperature; //maximum temperature of the product
    private int _price; // the price of the product
    private final int FOUR_DIGIT_NUM = 1000,FIVE_DIGIT_NUM = 10000; //represents how many digits in a number
    private final int DEFAULT_CATALOGUE_NUM = 9999, DEFAULT_PRICE = 1; //default catalogue number and default price.
    private final int ZERO=0; // represents the integer: '0'.
    
    //When the code is clearly reflected in API, I preferred to not write comments.

    /**
     * Constructor - Creates a new FoodItem object if the parameters are valid.
     * otherwise creates default variables;
     * @param name  name of food item
     * @param catalogueNumber  catalogue number of food item
     * @param quantity  quantity of food item
     * @param productionDate  production date
     * @param expiryDate  expiry date
     * @param minTemperature  minimum storage temperature
     * @param maxTemperature  maximum storage temperature
     * @param price  unit price
     */
    public FoodItem(String name,long catalogueNumber,int quantity,Date productionDate, Date expiryDate,
    int minTemperature, int maxTemperature, int price)
    {
        if(name=="") //if the name of the food item is an empty string.
            _name = new String ("item");
        else
            _name = new String (name);

        if(FOUR_DIGIT_NUM<=catalogueNumber&&catalogueNumber<FIVE_DIGIT_NUM) //check that the catalogue number is four digit number.  
            _catalogueNumber = catalogueNumber;
        else
            _catalogueNumber=DEFAULT_CATALOGUE_NUM;

        if(validQuantity(quantity)) //check that the quantity is positive with the private "validQuantity" method, if not - set it to 0.
            _quantity=quantity;
        else
            _quantity = ZERO;

        _productionDate = new Date(productionDate); //set the productionDate (Date class checks if valid) 

        if (expiryDate.before(productionDate))  //if the expiry date is before the production day so it is invalid - and set it to one day after the production date.
            _expiryDate = new Date(productionDate.tomorrow());
        else    
            _expiryDate = new Date(expiryDate);

        if(maxTemperature<minTemperature) //if the max temperature is smaller the the min temperature then it switch them.
        {
            _minTemperature = maxTemperature;
            _maxTemperature = minTemperature;
        }
        else
        {
            _minTemperature = minTemperature;
            _maxTemperature = maxTemperature;
        }

        if (ZERO<price) //check if the price is positive and set it's value.
            _price = price;
        else
            _price=DEFAULT_PRICE;
    }

    /**
     * Copy Constructor - construct a food item using another food item.
     * @param other the food item to be copied
     */

    public FoodItem(FoodItem other)
    {
        _name = new String (other._name);
        _catalogueNumber = other._catalogueNumber;
        _quantity = other._quantity;
        _productionDate = new Date(other._productionDate);
        _expiryDate = new Date(other._expiryDate);
        _minTemperature = other._minTemperature;
        _maxTemperature = other._maxTemperature;
        _price = other._price;   
    }

    /**
     * gets gets the name
     * @return the name
     */

    public String getName()
    {
        return new String (_name);
    }

    /**
     * gets the catalogue number
     * @return the catalogue number
     */

    public long getCatalogueNumber()
    {
        return _catalogueNumber;
    }

    /**
     * gets the quantity
     * @return the quantity
     */
    public int getQuantity()
    {
        return _quantity;
    }

    /**
     * gets the production date
     * @return the production date
     */
    public Date getProductionDate()
    {
        return new Date (_productionDate);
    }

    /**
     * gets the expiry date
     * @return the expiry date
     */

    public Date getExpiryDate()
    {
        return new Date (_expiryDate);
    }

    /**
     * gets the minimum storage temperature
     * @return the minimum storage temperature
     */

    public int getMinTemperature()
    {
        return _minTemperature;
    }

    /**
     * gets the maximum storage temperature
     * @return the maximum storage temperature
     */
    public int getMaxTemperature()
    {
        return _maxTemperature;
    }

    /**
     * gets the unit price
     * @return the unit price
     */
    public int getPrice()
    {
        return _price;
    }

    //Setters:

    /**
     * set the quantity (only if not negative)
     * @param n the quantity value to be set
     */
    public void setQuantity(int n)
    { 
        if (validQuantity(n)) //check if the quantity using the private "validQuantity" method, if not - do nothing.
            _quantity = n;
    }

    /**
     * set the production date (only if not after expiry date)
     * @param d production date value to be set
     */
    public void setProductionDate(Date d)
    { 
        if(!d.after(_expiryDate)) 
            _productionDate = new Date (d);
    }

    /**
     * set the expiry date (only if not before production date)
     * @param d expiry date value to be set.
     */
    public void setExpiryDate(Date d)
    { 
        if(!d.before(_productionDate)) 
            _expiryDate = new Date (d);
    }

    /**
     * set the price (only if positive)
     * @param n price value to be set
     */
    public void setPrice(int n)
    {
        if(n>ZERO)
            _price=n;
    }

    /**
     * check if 2 food items are the same (excluding the quantity values)
     * @param other the food item to compare this food item to
     * @return true if the food items are the same
     */

    public boolean equals (FoodItem other)
    {
        return ((this._name.equals(other._name) && this._catalogueNumber == other._catalogueNumber
            && this._minTemperature == other._minTemperature && this._maxTemperature == other._maxTemperature && this._price == other._price &&
            _productionDate.equals(other._productionDate) && _expiryDate.equals(other._expiryDate)));
    }

    /**
     * check if this food item is older than other food item
     * @param other food item to compare this food item to
     * @return true if this food item is older than other date
     */
    public boolean olderFoodItem (FoodItem other)
    {
        return ((other._productionDate.after(_productionDate)) ? true : false);
    }

    /**
     * check if this food item is fresh on the date d
     * @param d - date to check
     * @return true if this food item is fresh on the date d
     */
    public boolean isFresh (Date d)
    {
        return ((d.after(_productionDate) && d.before(_expiryDate)) || d.equals(_productionDate) || d.equals(_expiryDate)); 
    }

    /**
     * returns the number of units of products that can be purchased for a given amount
     * @param amount amount to purchase
     * @return the number of units can be purchased
     */
    public int howManyItems (int amount)
    {
        return((amount/_price)>_quantity) ? _quantity:(amount/_price);
    }

    /**
     * check if this food item is cheaper than other food item
     * @param other food item to compare this food item to
     * @return true if this food item is cheaper than other date
     */
    public boolean isCheaper (FoodItem other)
    {
        if(_price<other._price)
            return true;
        else
            return false;
    }

    /**
     * toString in class java.lang.Object
     * @return String that represents this food item in the following format:
     * FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3
     */

    public String toString()
    {
        return "FoodItem: " + _name + "\t" + "CatalogueNumber: " + _catalogueNumber +
        "\t" + "ProductionDate: " + _productionDate + "\t" + "ExpiryDate: " + _expiryDate +
        "\t" + "Quantity: " + _quantity;      
    }

    //private method
    /**
     * check if the quantity is valid.
     * @param q the quantity input.
     * @return true if this quantity is a positive number.
     */
    private boolean validQuantity(int q)
    {
        if(q<ZERO)
            return false;
        else
            return true;
    }
} //end of class
