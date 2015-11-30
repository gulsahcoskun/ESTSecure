package tp1.sistema;

import p2.util.Hour;

import java.util.Vector;


/**
 * This class represents a safe premise
 * It must have an unique identifier and a level access
 * It must also have a working schedule and a clearance list of users 
 */
public class Premise {
	
	private Schedule availability; 
    private ControlCentral occupant;
    private Vector<User> clrList = new Vector<User>();
    
    int id;
    int level;
    
    
  
        public Premise(int premiseId,int aclevel){
		   id = premiseId;
		   level = aclevel;
	    }
        
        public Premise(){
        	availability.getPeriods();
            clrList.elements();
        	
        }
    
        public int getId() {
      		return id;
      	}

        public void setId(int id) {
      		this.id = id;
      	}
        
        public int getLevel() {
   		return level;
        }

        public void setLevel(int lev) {
   		if (level >= 1 && level <= 5)
   			level = lev;
        }

    
	/**
	 * Checks if the user with the id can enter the premise at the specified hour
	 * @param id user identifier
	 * @param h entrance hour
	 * @return true if the user can enter the premise 
	 */
	public boolean canEnter( int id, Hour h ){
		if(availability.isInside(h)){
			User u = occupant.getUser(id);
			if(u.getLevel() >= getLevel())
				return true;
				
			else if(clrList.contains(id))
				return true;
		
		}
		return false;
	}
	
	/**
	 * If the user can enter the premise this method processes hher entry
	 * @param id user id
	 * @param h entrance hour
	 * @return true if the user entered the premise
	 */
	public boolean enter( int id, Hour h ){	
		if(canEnter(id,h))
			User u = occupant.getUser(id);
			return true;
		return false;
	}

	
	/**
	 * Process the leave of the premise by an user
	 * @param id user id
	 */
	public void leave( int id ){  			
	}
	
	
	/**
	 * return an array with the users present in the premise
	 * @return an array with the users present in the premise
	 */
	public User[] getUsersPresent(){
		int id;
		Hour h;
		if(enter(id,h))
		return occupant.toArray(  new User[occupant.getUsers(id)]);
			
		return null;
	}
	
	/**
	 * Checks if two premises are the same. They are the same if they have the same id
	 * @param p premise to test
	 * @return true if they have the same id
	 */
	public boolean equals( Premise p){
		if(id == p.id)
			return true;
		return false;
	}
	
	/**
	 * adds an user to the clearance list
	 * @param u user to be added
	 */
	public void addClearedUser( User u ){	
		clrList.add(u);
	}
	
	/**
	 * removes an user from the clearance list
	 * @param u user to be removed
	 */
	public void removeClearedUser( User u ){
		clrList.remove(u);
	}
}
