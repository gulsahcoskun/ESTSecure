package tp1.sistema;

/**
 * This represents a User of the enterprise tht can or not enter a premise
 * Each user must have an id code that uniquelly represents her
 * Each user must also have an access level that will be used to determine
 * whether or not she can enter a premise
 */
public class User {
	
       int id;
       int level;
       
     
           public User(int userId,int alevel){
		   id = userId;
		   level = alevel;
	       }
	 /**
	 * To create a User it is needed an id and a access level
	 * @param id unique id of the user 
	 * @param level access level (1-5)
	 */
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
	 * tests if a user is equal to another user
	 * The criteria here is the id, two users are the same if they have the same id
	 * @param u user to test
	 * @return true if the user is the same
	 */
	public boolean equals( User u ){
		return id == u.id;
	}
		
}
