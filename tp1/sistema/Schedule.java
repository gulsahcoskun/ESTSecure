package tp1.sistema; 

import java.util.Vector;

import p2.util.Hour;



/**
 * A Schedule is a set of periods of time.
 * New periods can be added and removed to the schedule
 */
public class Schedule {

	private Vector<Period> periods = new Vector<Period>();
	
	public Schedule(){		
	}
	
	/**
	 * adds a period to the schedule.
	 * There can be no overlapping periods. When adding periods the final periods must reflect
	 * the change but they cannot have overlapping periods. The Schedule must have the smallest
	 * possible number of periods
	 * @param p period to add
	 */
	public void addPeriod( Period p ){
       for(int i=periods.capacity(); i>=0; i--){
        
         if(!periods.get(i).intersects(p))
	       periods.add(p);
	       else if( periods.get(i).intersects(p)){
	    	   periods.get(i).join(p);
	    	   removePeriod(periods.get(i));
	       }
        }   
  }
        

	
	
	/**
	 * Remove a perio from the schedule
	 * When removing a period, all periods must be changed to reflect the removal,
	 * and the least number of periods possible must be used
	 * @param p period to remove
	 */
	public void removePeriod( Period p ){	
		for(int i=periods.capacity();  i>=0;  i--){
			if(!periods.get(i).intersects(p))
				periods.remove(p);
			else if(periods.get(i).intersects(p)){
				periods.get(i).getDifference(p);
				removePeriod(p);
			}
		 }
	
		}
		  
	
	/**
	 * Tests if the given hour is inside the schedule
	 * @param h hour to test
	 * @return true if the hour is inside a schedule
	 */
	public boolean isInside( Hour h ){
		for(int i=0; i<periods.capacity(); i++){
			if(periods.get(i).isInside(h))
				return true;
		}
		return false;
	}
	
	/**
	 * returns an array with all the periods of this schedule 
	 * @return an array with all the periods of this schedule
	 */
	public Period[] getPeriods(){
		return periods.toArray( new Period[ periods.size() ] );
	}
}
