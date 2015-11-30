package p2.util;

public class Time implements Cloneable {
	
	private Data date;
	private Hour hour;

	public Time(){
		date =new Data();
		hour = new Hour();
	}
	
	public Time( Data d, Hour h ){
		date = d;
		hour = h;
	}
	
	public Data getDate( ) {
    	return date;
    }
    
    public Hour getHour( ) {
    	return hour;
    }
    
    
    public void setDate( Data d ) {
    	date = d;
    }
    
    
    public void setHour( Hour h ) {
    	hour = h;
    }

    public void addDays( int nDays )   {
    	date.addDays( nDays );
    }
    
    public void addMonths( int nMonths ) {
    	date.addMonths( nMonths );
    }
    
    public void addYears( int nYears ) {
    	date.addYears( nYears );
    }
        
    public void addHours( int nHours ){
    	date.addDays( hour.addHours( nHours ) );    	   
    }
        
    public void addMinutes( int nMinutes ){
    	date.addDays( hour.addMinutes( nMinutes ) );
    }
        
    public void addSeconds( long nSeconds ) {
    	date.addDays( hour.addSeconds( nSeconds ) );
    }
    
    
    public int compareTo( Time h ) {
        int difData = date.compareTo( h.date ); // compares dates
        
        if( difData != 0 )  // if dates differ no further comparison is needed
            return difData;          
            
        return hour.compareTo( h.hour );        // compares the hours    	
    }
    
    public long getDifferenceSecs( Time t ){
        long nDays = date.getDifferenceDays( t.date );       
        long nSecs = hour.getDifferenceSecs( t.hour );
        
        return nDays*Hour.SECS_PER_DAY + nSecs;     	
    }
    
    
    public long getDifferenceDays( Time t ){
        return getDifferenceSecs( t ) / Hour.SECS_PER_DAY;    	
    }
    
        
    public String toString(){
    	return date + " " + hour;
    }
	
	public Time clone( ) {
		return new Time( date.clone(), hour.clone() );
	}

}
