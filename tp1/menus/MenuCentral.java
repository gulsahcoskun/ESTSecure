package tp1.menus;

import java.util.Vector;

import consola.SConsola;
import p2.util.Hour;
import tp1.sistema.*;


/**
 * Class that deals with the menus of the Command Central
 * Most menus are implemented but some must be changed as the
 * informations displayed between <<>> must be replaced with dynamic content 
 */
public class MenuCentral {
	
	private SConsola consola = new SConsola();								// console for the menus
	private Vector<PremiseMenu> menus = new Vector<PremiseMenu>();    // menus for the premises
	private ControlCentral central = new ControlCentral();                // central uses by this menu 
	
	public MenuCentral(){
		/**
		 * In order to facilitate testing we reccomend the creation of some premises here,
		 * so that you can start testing withou having to create premises every time 
		 * para isso devem chamar o método addInstalação para criar automaticamente os menus
		 * 
		 * 	addPremise( new Premise()...) );
		 */		
		
		// and alsoto create some users: as an example we create 5 users
		central.addUser( new User(1, 1));
		central.addUser( new User(2, 2));
		central.addUser( new User(3, 3));
		central.addUser( new User(4, 4));
		central.addUser( new User(5, 5));
	}

	/**
	 * main menu
	 */
	public void mainMenu(){
		String menu = "Command Central \n" + 
		              "1- Add premise\n" +
		              "2- Change premise\n"+
		              "3- Add user\n"+
		              "4- Change user\n"+
		              "5- List presences\n"+
		              "\n0- exit";
		
		char option = 0;
		do {
			consola.clear();
			consola.println( menu );
			option = consola.readChar();
			switch( option ){
			case '1':
				addPremise();
				break;
			case '2':
				changePremise();
				break;
			case '3':
				addUser();
				break;
			case '4':
				changeUser();
				break;
			case '5':
				listPresences();
				consola.readLine();
				break;
			case '0': break;
			default:
				consola.println( "Invalid option");
				consola.readLine();
			}
		} while( option != '0' );
		
		consola.close();					// desligar o menu da central		
		for( PremiseMenu m: menus )      // desligar o menu de todas as instalações
			m.closeOperacoes();
	}

	
	/**
	 * asks for the info to crfeate a new premise
	 */
	private void addPremise() {
		Premise inst;
		int id;
		
		consola.clear();
		do {
			consola.print("Premise code? ");
			id = consola.readInt();
			inst = central.getPremise( id );			
		} while( inst != null );			
			
		int level = askLevel( "Access level? ");
				
		// create the premise
		// create the premise
		// create the premise
		
		changePremise( inst );  	// call the method to change the premise configurations		
		                            // before addin it tothe system
		addPremise( inst );         // add the premise to the system (it also creates a specific menu)
	}
	
	
	/**
	 * add a premise to the system and creates an premise menu
	 * @param p ipremise to add
	 */
	private void addPremise( Premise p ){
		central.addPremise( p );		
		PremiseMenu menu = new PremiseMenu( p );  // creates a premise menu
		menu.startOperacoes();		                    // starts the operations of the menu
		menus.add( menu );
	}
	
	
	/**
	 * method that asks for the premise to chage
	 */
	private void changePremise() {
		consola.clear();
		Premise p = null;
		do {
			consola.print("Id of the premise to change? ");
			int id = consola.readInt();
			p = central.getPremise( id );
		} while( p == null );
		changePremise( p );
	}
	

	/**
	 * Menu to change the premise
	 * @param prem
	 */
	private void changePremise(Premise prem ) {
		String menu = "1- change Level\n" +
					  "2- add period tothe schedule\n"+
					  "3- remove period tothe schedule\n"+
					  "4- add user to clearence list\n"+
					  "5- remove user from clearence\n"+
					  "6- remove premise\n"+
					  "\n0- exit";

		char option = 0;
		do {
			consola.clear();
			print( prem );
			consola.println(menu);
			option = consola.readChar();
			switch (option) {
			case '1':
				changePremiseLevel( prem );
				break;
			case '2':
				//addPeriod( premise schedule );
				break;
			case '3':
				//removePeriod( premise schedule );
				break;
			case '4':
				addClearenceUser( prem );
				break;
			case '5':
				removeClearenceUser( prem );
				break;
			case '6':
				break;
			case '0':
				break;
			default:
				consola.println("opção inválida");
				consola.readLine();
			}
		} while (option != '0');
	}

	/**
	 * prints the info of a premise 
	 * @param prem premise to print
	 */
	private void print(Premise prem) {
		consola.println("id: <<premise id>>" );
		consola.println("acces level:  <<acess level>>"  );
		consola.println("Schedule");
		//print( schedule );				
	}
	
	/**
	 * prints a schedule in the console
	 * @param s schedule to print
	 */
	private void print(Schedule s) {
		Period ps[] = s.getPeriods();
		for( Period p: ps ){
			consola.println( "<<period begin>>  -  <<period end>>" );
			// exemple: 9:00  -  14:00
		}
		consola.println("----------------------------");
	}
	
	
	/**
	 * asks the new access level for a premise
	 * @param prem premise to change
	 */
	private void changePremiseLevel(Premise prem) {
		int level = askLevel("New access level? ");		
	}

	/**
	 * aux methods to aks for a level access and checks if its between 1-5
	 * @param msg message to show in the console
	 * @return the chosen level
	 */
	private int askLevel( String msg ){
		int nivel;
		do {
			consola.print( msg );
			nivel = consola.readInt();
		} while( nivel < 1 || nivel > 5 );		
		return nivel;
	}
	

	/** 
	 * asks for the period information that will be added to the schedule
	 * @param s schedulle to be changed
	 */
	private void addPeriod(Schedule s) {
		print( s );
		Period p = askPeriod();
		
		
		 Hour init = new Hour( pih );
         Hour fin= new Hour( pfh );
         Period pe =new Period( init,fin );
         periods.add(pe);
		
	}

	/**
	 * asks for the period information that will be removed to the schedule
	 * @param s schedule fro which to remove the period
	 */
	private void removePeriod(Schedule hor) {
		print( hor );
		Period p = askPeriod();
		
	}

	/**
	 * asks for the period information
	 * @return the period crearedwith the introduced info 
	 */
	private Period askPeriod() {
		Hour ini, fim;
		do{
			ini = getHour( "Enter the begin hour (h:m): " );
			fim = getHour( "Enter the end hour (h:m): ");
		} while( ini.compareTo( fim ) > 0 );
		return new Period( ini, fim );
	}


	/**
	 * Asks the data to create an hour
	 * @param msg massage to show in the console
	 * @return the hour
	 */
	private Hour getHour( String msg ) {
		consola.print( msg );
		String horaS = consola.readLine()+":00"; // :00 to enter the seconds
		return new Hour( horaS );		
	}

	/**
	 * asks for a user id and returns the user
	 * @param msg message to show in the console 
	 */
	private User askUser( String msg ) {
		int id;
		User u = null;
		do {
			consola.print( msg );
			id = consola.readInt();
			// determine the user with the id
			// determine the user with the id
			// determine the user with the id			
		} while( u == null );
		return u;
	}
	
	/**
	 * asks the data of the user to be added to the clearance list 
	 * @param prem premise to which the user will be added
	 */
	private void addClearenceUser(Premise prem) {
		int id;
		User u = askUser( "User id to clear?" );		
		// add the user to the premise
		// add the user to the premise
		// add the user to the premise
	}


	/**
	 * asks the data for a user to be removed from the clearence list of a premise 
	 * @param prem premise to remove the user from
	 */
	private void removeClearenceUser(Premise inst) {
		int id;
		User u = askUser( "User id to removefrom clearence?");
		
		// remove the user from the clearence list of the premise
		// remove the user from the clearence list of the premise
		// remove the user from the clearence list of the premise
	}


	/**
	 * Adds a new user to the system, ensuring that the id is unique
	 */
	private void addUser() {
		int id;
		
		do {
			consola.print("User id to use? ");
			id = consola.readInt();
		} while( false /* check if the id is already used */ );
		
		int level = askLevel( "user access level? ");
		
		//create and add the user to the system 
	}

	
	/**
	 * menu that aksks the user to change and the changes
	 */
	private void changeUser() {
		consola.clear();
		User u = askUser( "user id to change?" );

		String menu = "1- change level\n" +
		  			  "2- remove user\n"+
		  			  "\n0- exit";
		
		char option = 0;
		do {
			consola.clear();		
			consola.println("Changing user menu  - user <<ID>>  level: <<level>>");
			// exemple: Changing user menu  - user 10  level: 5 
			consola.println(menu);
			option = consola.readChar();
			switch (option) {
			case '1':
				changeUserLevel( u );
				break;
			case '2':				
				break;
			case '0':
				break;
			default:
				consola.println("Invalid option");
				consola.readLine();
			}
		} while (option != '0');
		
	}

	/**
	 * aks the new level for the user
	 * @param u the user to change
	 */
	private void changeUserLevel(User u) {
		int level = askLevel("New user access level? ");
		// change the user level
		// change the user level
		// change the user level		
	}
	
	
	/**
	 * lists all presences in the premise 
	 */
	private void listPresences() {
		consola.clear();
		for( Premise inst : central.getPremises() ){
			consola.println( "user present in the premise <<premise id>>" );
			for( User u : inst.getUsersPresent() )
				consola.println( "<<user id>>  (<<<user acces level>>)" ); // exemple: 12  (2)
			consola.println( "-----------------" );
		}		
	}
	
	
	/**
	 * initiates the application
	 */
	public static void main( String []args ){
		MenuCentral central = new MenuCentral();
		central.mainMenu();
	}
	
	
	
}
