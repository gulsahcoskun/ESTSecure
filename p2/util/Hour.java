package p2.util;

import java.util.Calendar;

/**
 * @author fsergio
 *
 */
public class Hour implements Cloneable {
	private int hours;
	private int minutes;
	private int seconds;
	
	// some constants 
	public static final int SECS_PER_HOUR = 3600;
	public static final int SECS_PER_MINUTE = 60;
	public static final int MINS_PER_HOUR = 60;	
	public static final long SECS_PER_DAY = 24 * SECS_PER_HOUR;  
	
	
    public Hour( ){
		Calendar cal = Calendar.getInstance();
		hours = cal.get( Calendar.HOUR_OF_DAY );
		minutes = cal.get( Calendar.MINUTE );
		seconds = cal.get( Calendar.SECOND );    	
    }
    
    public Hour( int h, int m, int s){
    	setHours( h );
    	setMinutes( m );
    	setSeconds( s );
    }
    
    public Hour( long nDaySecs ){
        if( nDaySecs < 0 )
        	nDaySecs =0;
        if( nDaySecs >= SECS_PER_DAY )
        	nDaySecs = SECS_PER_DAY - 1; // SEGS_POR_DIA - 1 porque segundos começam em 0 
        
        hours = (int)(nDaySecs / SECS_PER_HOUR);
        minutes = (int)((nDaySecs % SECS_PER_HOUR) / SECS_PER_MINUTE);
        seconds = (int)((nDaySecs % SECS_PER_HOUR) % SECS_PER_MINUTE);
    }
    
    
    public Hour( String hStr ){
		String str[] = hStr.split(":");	    
    
        setHours( Integer.parseInt( str[0] ) );
	    setMinutes( Integer.parseInt( str[1] ) );
	    setSeconds( Integer.parseInt( str[2] ) );	        	
    }

    public int getHours( ) {
    	return hours;
    }
    
    public int getMinutes( ) {
    	return minutes;
    }
    
    public int getSeconds( ) {
    	return seconds;
    }
    
    public void setHours( int h ){
    	hours = h < 0? 0: h;
    	if( hours > 23 )
    		hours = 23;    	
    }
    
    public void setMinutes( int m ){
    	minutes = m < 0? 0: m;
    	if( minutes > 59 )
    		minutes = 59;    	
    }
    
    public void setSeconds( int s ){
    	seconds = s < 0? 0: s;
    	if( seconds > 59 )
    		seconds = 59;    	
    }
    
    
    public int addHours( int nHours ){
    	int totalHours = hours + nHours;
        hours = totalHours % 24;
        return totalHours / 24;    	
    }
    
    public int addMinutes( int nMinutes ){
        minutes += nMinutes;
        int nDias = addHours( minutes/SECS_PER_MINUTE );
        minutes = minutes % SECS_PER_MINUTE;
        return nDias;    	
    }
    
    
    public int addSeconds( long nSeconds ){
        long seg = seconds + nSeconds;
        int nDias = addMinutes( (int)(seg/SECS_PER_MINUTE) );  // conversão long para int, para o compilador nãos e queixar
        seconds = (int)(seg%SECS_PER_MINUTE);
        return nDias;     	
    }
    
    public int getSecondsOfDay( ) {
    	return hours * SECS_PER_HOUR + minutes * SECS_PER_MINUTE + seconds;
    }
    
    public int  compareTo( Hour h){
    	if( hours != h.hours )
    		return hours - h.hours;
    	if( minutes != h.minutes )
    		return minutes - h.minutes;
    	return seconds - h.seconds;
    }
    
    
    public long getDifferenceSecs( Hour h ){
        return (hours - h.hours) * SECS_PER_HOUR + (minutes - h.minutes) * SECS_PER_MINUTE +
        	   (seconds - h.seconds);    	
    }
    
    public String toString() {
    	return hours + ":" + minutes + ":" + seconds;
    }
    
	public Hour clone( ) {
		return new Hour( hours, minutes, seconds);
	}
}
