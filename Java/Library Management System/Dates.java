public class Dates
{
    int m,d,y;
        
    public Dates(int month, int day, int year) 
    {
        m=month;
        d=day;
        y=year;
    }

    
    public int getMonth()
    { return m; }

    public int getDay()
    { return d; }

    public int getYear()
    { return y; }


    public void setMonth(int month)
    { m=month;  }

    public void setDay(int day)
    { d=day;    }

    public void setYear(int year)
    { y=year;   }


    public String toString()
    {
        String s = m + "/" + d + "/" + y;
        return s;
    }


    public long toLong() 
    {

        long days=0;
            
        switch(m)
            {
            case 12: days+=30;
            case 11: days+=31;
            case 10: days+=30;
            case 9:  days+=31;
            case 8:  days+=31;
            case 7:  days+=30;
            case 6:  days+=31;
            case 5:  days+=30;
            case 4:  days+=31;
            case 3:  days+= isLeapYear(y) ? 29 : 28;
            case 2:  days+=31;
            case 1:  days+=d-1; 
            }

        
        if(y!=1900) {
            int inc=(1900-y)/Math.abs(1900-y);
            for(int i=y; i!=1900; i+=inc)
                days += (isLeapYear(i) ? 366 : 365);
        }
            
        return days;
    }


    private boolean isLeapYear(int y)
    {
        if((y%100)==0) return (y%400)==0;
        else return (y%4)==0;
    }

   
    public long getDifference(Dates date) 
    { return Math.abs(toLong()-date.toLong()); }
        
}