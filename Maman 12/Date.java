/**
 * This class represents a Date object in the Gregorian Calendar.
 *
 * @author Idan Kogan
 * @version  2020a
 */
public class Date
{
    private int _day, _month, _year; // represent the 3 instanses of the Date class, which are the day,the  month and the year.
    private final int ZERO = 0, FIRST_DAY_IN_MONTH = 1, FIRST_MONTH = 1;
    private final int DAYS_IN_MONTH = 31; //how many days in a general month.
    private final int MONTH_IN_YEAR = 12; // how many month in year.
    private final int FIRST_YEAR = 1000,LAST_YEAR = 9999 ; // the first and the last year possible.
    private final int DEAFULT_DAY = 1, DEAFULT_MONTH = 1,DEAFULT_YEAR = 2000; // represent default day.
    private final int DIFFERENT_FEBRUARY = 29; //unique last day in february.
    private final int FEB = 2, APR = 4, JUN = 6, SEP = 9, NOV = 11; //represent the number of the month.
    private final int SINGLE_DIGIT = 9; //represents how many digits in a number

    //*The private method "validDate" is used in constructors and methods. "validDate" checks if the date is valid and set its value.

    /**
     * Constructor -  Creates a new Date object if the date is valid, otherwise creates the date 1/1/2000.
     * @param day the day in the month (1-31).
     * @param month the month in the year (1-12).
     * @param year the year (4 digits).
     */

    public Date(int day, int month, int year)
    {
        if (validDate(day, month, year))
        {
            _day=day;
            _month=month;
            _year=year;
        }
        else
        {
            _day=DEAFULT_DAY;
            _month=DEAFULT_MONTH;
            _year=DEAFULT_YEAR;
        }
    }

    /**
     * Copy Constructor
     * @param other the date to be copied
     */
    public Date (Date other) 
    {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    //Setters:
    /**
     * sets the day (only if date remains valid).
     * @param dayToSet the day value to be set.
     */
    public void setDay(int dayToSet) 
    { 
        if (validDate(dayToSet,_month,_year))
        {
            _day = dayToSet;
        }
    }

    /**
     * sets the month (only if date remains valid).
     * @param monthToSet the month value to be set.
     */
    public void setMonth(int monthToSet)
    {
        if (validDate(_day,monthToSet, _year))
        {
            _month = monthToSet;
        }

    }

    /**
     * sets the year (only if date remains valid).
     * @param yearToSet the year value to be set.
     */
    public void setYear(int yearToSet)
    {
        if (validDate(_day,_month, yearToSet))
        {
            _year = yearToSet;
        }
    }

    //Getters:
    /**
     * gets the day.
     * @return the day.
     */
    public int getDay() 
    {
        return _day;
    }

    /**
     * gets the month.
     * @return the month.
     */
    public int getMonth() 
    {
        return _month;
    }

    /**
     * gets the year.
     * @return the year.
     */
    public int getYear() 
    {
        return _year;
    }

    /**
     * Check if 2 dates are the same.
     * @param other the date to compare this date to.
     * return true if the dates are the same
     */
    public boolean equals (Date other)
    {
        return other._day==this._day && other._month==this._month && other._year==this._year;
    }

    /**
     * Check if this date is before other date
     * @param other date to compare this date to.
     * @return true if this date is before other date
     */
    public boolean before (Date other)
    {
        if (this._day<other._day)
        {
            if((this._month<=other._month&&this._year<=other._year) || (this._month>other._month&&this._year<other._year))
                return true;
        }
        if (this._day>=other._day)
        {
            if((this._month<other._month&&this._year<=other._year)||(this._month>=other._month&&this._year<other._year))
                return true;
        }
        return false;    
    }

    /**
     * Check if this date is after other date.
     * @param other date to compare this date to
     * @return if this date is after other date
     */
    public boolean after(Date other)
    {
        return other.before(this); //Uses the "before" method.
    }

    /**
     * Calculates the difference in days between two dates.
     * @param other the date to calculate the difference between.
     * @return the number of days between the dates.
     */
    public int difference(Date other)
    {
        if(this.before(other)) //// Uses the "before method to check which calculation to do.
            return (calculateDate(other._day,other._month,other._year))-(calculateDate(this._day,this._month,this._year)); 
        else
            return (calculateDate(this._day,this._month,this._year)-(calculateDate(other._day,other._month,other._year)));
            
    }

    // Computes the day number since the beginning of the Christian counting of years
    private int calculateDate (int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }

    /**
     * Calculate the date of tomorrow.
     * @return the date of tomorrow.
     */
    public Date tomorrow() // this method uses 3 temporal varibales to store temporarily the date without changing it. (3 variables to clarify to differences)
    {
        int tempDay =_day+1; 
        if (validDate(tempDay,_month,_year))
        {
            return new Date (tempDay, _month, _year); 
        }
        else
        {
            int tempMonth = _month+1; 
            if (validDate(DEAFULT_DAY,tempMonth,_year))
            {
                return new Date (DEAFULT_DAY, tempMonth, _year);
            }
            else
            {
                int tempYear = _year+1;
                if (validDate(DEAFULT_DAY,DEAFULT_MONTH,tempYear))
                {
                    return new Date (DEAFULT_DAY, DEAFULT_MONTH, tempYear);
                }
            }
        }
        return new Date (DEAFULT_DAY, DEAFULT_MONTH,DEAFULT_YEAR);
    }

    /**
     * Calculate the day of the week that this date occurs on: 0-Saturday 1-Sunday 2-Monday etc.
     * @return the day of the week that this date occurs on. 
     */
    public int dayInWeek()
    {
        int dayInWeek; //a digit which represents the day in a week
        final int lastYear=_year-1; //represents last year (for the calculation)
        final int lastJAN = 13, lastFEB = 14; // represents January and February from last year (for the calculation)
        int tempMonth=DEAFULT_MONTH; //temporal variable (to keep the instanse variable the same)

        if (_month==FIRST_MONTH) 
        {
            tempMonth=lastJAN;
            dayInWeek = (_day + ((26*(tempMonth+1))/10) + (lastYear%100) + ((lastYear%100)/4) + ((lastYear/100)/4) - (2*(lastYear/100)))%7;
        }
        else if (_month==FEB)
        {
            tempMonth=lastFEB;
            dayInWeek = (_day + ((26*(tempMonth+1))/10) + (lastYear%100) + ((lastYear%100)/4) + ((lastYear/100)/4) - (2*(lastYear/100)))%7;
        }
        else //If it's regular month.
            dayInWeek = (_day + ((26*(_month+1))/10) + (_year%100) + ((_year%100)/4) + ((_year/100)/4) - (2*(_year/100)))%7;

        return (dayInWeek<0) ? Math.floorMod(dayInWeek,7): dayInWeek;

    }

    /**
     * returns a String that represents this date.
     * @overrides toString in class java.lang.Object.
     * @return String that represents this date in the following format: day/month/year for example: 02/03/1998
     */
    public String toString()
    {
        if (_day<=SINGLE_DIGIT&&_month<=SINGLE_DIGIT) //check if the day and month are one digit.
            return "0" + _day + "/" + "0" + _month + "/" + _year ;
        else if (_day<=SINGLE_DIGIT&&_month>SINGLE_DIGIT) //check if  just the day is one digit.
            return "0" + _day + "/" + _month + "/" + _year ;
        else if(_day>SINGLE_DIGIT&&_month<=SINGLE_DIGIT) //check if just the month ay is one digit.
            return _day + "/" + "0" + _month + "/" + _year ;
        else //if the day and the month are two digit number.
            return _day + "/" + _month + "/" + _year; 
    }

     /**
     * validDate checks if the date is valid and return true.
     * @param day the day in the month (1-31).
     * @param month the month in the year (1-12) and if valid to the day.
     * @param year the year (4 digits) and if valid to the day and the month.
     * return true if the date is 
     */
    //private method:
    private boolean validDate (int day, int month, int year) ////valid date
    {
        if (ZERO<day && day<=DAYS_IN_MONTH)
            //*the next if statement checks if the date is not good*
            if ((month<=ZERO||MONTH_IN_YEAR<month)||(year<FIRST_YEAR||LAST_YEAR<year)|| 
                (day==DAYS_IN_MONTH &&(month==FEB||month==APR||month==JUN||month==SEP||month==NOV)) || 
                (day==DIFFERENT_FEBRUARY && month==FEB && year%4!=0) || (DIFFERENT_FEBRUARY<day&&month==FEB)) 
                return false;
            else //if the day exists.
                return true; 

        return false; //if the day is not in the range of days in month.
    }

} //end of class
