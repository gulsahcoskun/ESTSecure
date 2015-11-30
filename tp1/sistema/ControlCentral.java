package tp1.sistema;

import java.util.HashMap;
import java.util.Map;


/**
 * This represents the control class of the system
 * It has the users and premises lists
 * We recommend a map to speed user and premise search by id 
 */
public class ControlCentral {

	private Map<Integer,Premise> premises = new HashMap<Integer,Premise>();
	private Map<Integer,User> users = new HashMap<Integer,User>();

	public ControlCentral(){			
	}		

	/**
	 * adds an user to the central
	 * @param u user to add
	 */
	public void addUser(User u) {
		
	}
	
	/**
	 * return the user with the specified id
	 * @param id user id
	 * @return user with the specified id, null if there is no such user
	 */
	public User getUser(int id) {		
		return null;
	}

	/**
	 * removes the user from the system
	 * @param id id of the user to remove
	 */
	public void removeUser( int id ){	
	}
	
	/**
	 * deturns an array with all users of the system
	 * @return an array with all users of the system
	 */
	public User[] getUsers(){
		return users.values().toArray( new User[users.values().size()] );
	}	

	/**
	 * adds a premise tothe system
	 * @param p premise to add
	 */
	public void addPremise(Premise p) {			
	}
	
	/**
	 * return the premise with the specified id
	 * @param id premise id
	 * @return the premise with the specified id, null if no such premise exists
	 */
	public Premise getPremise(int id) {
		return null;
	}
	
	
	/**
	 * removes a  premise from the system
	 * @param id id of the premise to remove
	 */
	public void removeIPremise( int id ){
	}

	/**
	 * return an array with allpremises of the system
	 * @return an array with allpremises of the system
	 */
	public Premise[] getPremises(){
		return premises.values().toArray( new Premise[premises.values().size()] );
	}
}
