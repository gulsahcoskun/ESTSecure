package p2.util;
/**
 * 
 */
import java.util.Calendar;

/**
 * @author fsergio
 *
 */
public class Data implements Cloneable {
	
    private int day, month, year;   
    
    
	public Data( ){
		Calendar cal = Calendar.getInstance();
		day = cal.get( Calendar.DAY_OF_MONTH );
		month = cal.get( Calendar.MONTH ) + 1;
		year = cal.get( Calendar.YEAR );
	}
	
	
	public Data( int d, int m, int a ){
		checkYear( a );
		checkMonth( m );
		checkDay( d );
	}
	
	
	public Data( int dayOfYear, int y ){
	    checkYear( y );
	    
	    if( dayOfYear < 1 )
	        dayOfYear = 1;

	    // check if days are between 1 and 365 (or 366 in leap years)
	    int maxDias = daysOfYear();
	    if( dayOfYear > maxDias )
	        dayOfYear = maxDias;
	    
	    month = 1; // start with January
	    while( dayOfYear > daysOfMonth( ) ) {
	        dayOfYear -= daysOfMonth( ); 
	        month++ ;                          
	    }
	    day = dayOfYear;		
	}
	
	public Data( String dStr ){
		String str[] = dStr.split("/");	    
    
        checkYear( Integer.valueOf( str[2] ).intValue() );
	    checkMonth( Integer.valueOf( str[1] ).intValue() );
	    checkDay( Integer.valueOf( str[0] ).intValue() );	    
	}
	
	private void checkDay( int d ){				
		day = d < 1? 1: d;
		day = day > daysOfMonth( )? daysOfMonth( ): day;
	}
	
	
	private void checkMonth( int m ){
		month = m < 1? 1: m;
		month = month >12? 12: month;
	}
	
	
	private void checkYear( int a ){
		year = a == 0 ? 1: a;		
	}

	// return the nuber of days of the current month
	public int daysOfMonth( ){
		return daysOfMonth( month );
	}
	

	// this method returns the days of a given month in the current year
	private int daysOfMonth( int m ){
		int nDiasMes[] = {0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if( isLeap( ))
			nDiasMes[2] = 29;
		return nDiasMes[ m ];
	}
	
		
	public String toString( ){
		return day + "/" + month + "/" + year;		
	}

	
	public void setDay( int d ) {
		checkDay( d );
	}

		
	public void setMonth( int m ) {
		checkMonth( m );
		checkDay( day );
	}

	
	
	public void setYear( int a ) {
		checkYear( a );
		checkDay( day );
	}
	
		
	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}


	public int compareTo( Data other ){
		if( year != other.year )
			return year - other.year;
		if( month != other.month )
			return month - other.month;
		return day - other.day;		
	}
	
	
	public Data clone( ) {
		return new Data( day, month , year );
	}
	
	
	
	public boolean isLeap( ) {
	    return ((year%4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}
	
		
    public int daysOfYear( ) {
    	return (isLeap() ? 366: 365);
    }
    
	
	public int getDayOfYear() {
	    int dayOfYear=day; 

	    for( int m = 1; m < month; m++)
	      dayOfYear += daysOfMonth( m );
	    
	    return dayOfYear;
	}
	
	
	public void addDays( int nDays )	{
		int totalDays = day + nDays;
		day = 1; // to avoid alteration on the current day, while updating the date
		
		while( totalDays > daysOfMonth() ){
			totalDays -= daysOfMonth();
			addMonths( 1 );			
		}
		setDay( totalDays );
	}



	public void addMonths( int nMonths ) {
	    int finalMonth = month + nMonths;
	    
	    // check to see if a year as gone by
	    if( finalMonth > 12 ) {
	    	// keep in ind that mionths start on 1 and not on zero
	    	// so we must add/subtract 1 for the divisions to work
	        int nYears = (finalMonth-1)/12;     // how many years have passed
	        addYears( nYears );               // add those years to the date
	        finalMonth = (finalMonth-1)%12 + 1;  // check the final month
	    }
	    setMonth( finalMonth );           // set the month, but checking the day	    
	 }
	
	
    public void addYears( int nYears ) {
    	setYear( year + nYears );
    }
	
    
    public int getDifferenceDays( Data other ) {   
        if( this.compareTo( other ) < 0 )
            return -other.getDifferenceDays( this );   
        
        if( year == other.year )
            return getDayOfYear() - other.getDayOfYear( );

        Data dAux = (Data)other.clone();
        
        int nDaysDif = dAux.daysOfYear( ) - dAux.getDayOfYear( );
        
        dAux.addYears( 1 );              
        
        while( dAux.year < year )
        {
            nDaysDif += dAux.daysOfYear();
            dAux.addYears( 1 );
        }
        
        nDaysDif += getDayOfYear( );
        
        return nDaysDif;
    }



    public long getDifferenceMonths( Data other ) {   
        if( this.compareTo( other ) < 0 )
            return -other.getDifferenceMonths( this );   
        
        if( year == other.year )
            return month - other.month;

        long nMonthDif = 12 - other.month;

        nMonthDif += 12 * (year - other.year - 1);
           
        nMonthDif += month;
        
        return nMonthDif;
    }

    
    public enum WeekDay {
    	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
    	
    	WeekDay addDays( int n ){
    		int fim = (this.ordinal() + n) % 7;
    		return WeekDay.values()[ fim ];
    	}
    	
    }

    public WeekDay getDayOfWeek( ) {    
        // 1/1/1970 is the refeence date in many systems and it was a thursday
        Data ref = new Data( 1, 1, 1970);
        
        int nDias = getDifferenceDays( ref );                 
        return WeekDay.THURSDAY.addDays( nDias );
    }        
}
