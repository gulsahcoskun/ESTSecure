package tp1.sistema;

import p2.util.Hour;

/**
 * This class represents a Period of time
 */
public class Period {
	
    private static final int end = 0;
	private Hour init;
	private Hour fin;
	/**
	 * a Period needs its initial and ending hour, it must
	 * @param ini initial hour
	 * @param end final hour
	 */
	public Period(Hour ini, Hour end) {
		init = ini;
		fin = end;
	}	
	
	/**
	 * indicates if an hour is inside this period of time
	 * @param h the hour to test
	 * @return true if the hour is inside the period, false otherwise
	 */
	public boolean isInside( Hour h ){
		
		if((init.compareTo( h )<0) && (fin.compareTo( h )>0 ))
	       return true;
		return false;
	}
	
	
	/**
	 * Checks if another period of time intersects this
	 * @param p time period to test for intersection
	 * @return true if it intersects this, false otherwise
	 */
	public boolean intersects( Period p ){
	
			if((init.compareTo(p.init) < 0) && (fin.compareTo(p.fin) < 0))
				return true;
			else if((init.compareTo(p.init) > 0) && (fin.compareTo(p.fin) > 0))
				return true;
			else if((init.compareTo(p.init) > 0) && (fin.compareTo(p.fin) < 0))
				return true;
			else if((init.compareTo(p.init) < 0) && (fin.compareTo(p.fin) > 0))
				return true;
			return false;
	}

	
	
	/**
	 * inidcates is this period is fully inside another period
	 * @param p period to test 
	 * @return true if this period is inside p, false otherwise  
	 */
	public boolean isInside( Period p ){
		
	     if((init.compareTo(p.init) > 0) && (fin.compareTo(p.fin) < 0))
			return true;
		return false;
	}
		
	 
	/**
	 * Checks is this period fully wraps another period
	 * @param p period to test
	 * @return true if p is inside this period, false otherwise
	 */
	public boolean wraps( Period p ){
		 if((init.compareTo(p.init) < 0) && (fin.compareTo(p.fin) > 0))
			return true;
		return false;
	}
		
	
	/**
	 * Joins this period with another period. Joining can be done only if both periods intersect. 
	 * @param p period to join
	 */
	public void join( Period p ){
		if( intersects(p) ){
			if( init.compareTo( p.init ) > 0 )
				init = p.init;
			if( fin.compareTo( p.fin ) < 0 )
				fin = p.fin;
		}
	}
	
	/**
	 * returns the union of this period with another period.
	 * If periods do not intesect there is no union. If they intersect the union
	 * represents the greatest possible period of time joining both periods.
	 * Both periods are left unchanged.
	 * @param p period to test
	 * @return the union of this period and p
	 */
	
		public Period getUnion( Period p){
			if( !intersects(p) )
				return null;
			
			Period result = new Period( init, fin );
			result.join( p );
			return result;
		}
		
	/**
	 * Returns the intersectiion between this perios and another.
	 * This is the period of time they have overlapping
	 * Both periods remain unchanged
	 * @param p period to test
	 * @return the period of time they have in common
	 */
	public Period getIntersection( Period p){
		if(intersects(p)){
			if((init.compareTo(p.init) < 0) && (fin.compareTo(p.fin) < 0)){
				return new Period ( p.init,fin);
				
			}
			else if((init.compareTo(p.init) > 0) && (fin.compareTo(p.fin) > 0)){
				Period pe = new Period ( init,p.fin);
				return pe;
			}
				
			else if((init.compareTo(p.init) > 0) && (fin.compareTo(p.fin) < 0)){
				Period pe = new Period ( init,fin);
				return pe;
			}
				
			else if((init.compareTo(p.init) < 0) && (fin.compareTo(p.fin) > 0)){
				Period pe = new Period ( p.init,p.fin);
				return pe;
			}
		}
		return null;	
	}
	/**
	 * Returns the difference between this period and another period.The differrence between 
	 * two periods are the, at most, 2 periods in whci this period does not contain the other period.
	 * This method always returns an array of 2 periods (before, after). If any of ghe periods
	 * does not exist then it return a null in its array position.
	 * Both periods remain unchanged
	 * @param p period to test
	 * @return an array of 2 periods containing the diffenrece between this period and the period
	 */
	public Period[] getDifference( Period p ){
		Period ps[] = new Period[2];
		if((init.compareTo(p.init) < 0) && (fin.compareTo(p.fin) < 0)){
			Period s = new Period(init,p.init);
			Period sp = new Period(fin,p.fin);
			return ps;
			
		}
		else if((init.compareTo(p.init) > 0) && (fin.compareTo(p.fin) > 0)){
			Period s = new Period(p.init,init);
			Period sp = new Period(p.fin,fin);
			return ps;
			
		}
			
		else if((init.compareTo(p.init) > 0) && (fin.compareTo(p.fin) < 0)){
			Period s = new Period(p.init,init);
			Period sp = new Period(fin,p.fin);
			return ps;
			
		}
			
		else if((init.compareTo(p.init) < 0) && (fin.compareTo(p.fin) > 0)){
			Period s = new Period(init,p.init);
			Period sp = new Period(p.fin,fin);
			return ps;
			
		}
		return null;
	}
	
}
