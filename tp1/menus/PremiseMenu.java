package tp1.menus;

import tp1.sistema.Premise;
import consola.SConsola;


/**
 * Class that represents an entrance control for the premise
 * Presents the enter and leave menus
 */
public class PremiseMenu {

	private Premise prem;                        // premise that this menu serves 
	private SConsola consola = new SConsola();   // consola to present the menu
	
	
	public PremiseMenu( Premise prem ){
		this.prem = prem;
	}
	
	
	/**
	 * This will start the premise operations
	 * DO NOT CHANGE THIS METHOD
	 */
	public void startOperacoes(){
		Thread t = new Thread () {			
			public void run() {				
				operationsMenu();
			}
		};
		t.start();
	}
		
	/**
	 * Shows the premise menu
	 */
	private void operationsMenu(){				
		String menu = "Entrance control - Premise <<premise id>>  level <<access level>>" +
		              "\n1- Entrance" + 
		              "\n2- Leave";
		// exemple: Entrance control - Premise 10  level 2 
				
		do {
			consola.clear();			
			consola.println( menu );
			char opcao = consola.readChar();
			switch( opcao ){
			case '1':
				entrance( );
				break;
			case '2':
				leave( );
				break;
			default:
				consola.println("Invalid option!");
				consola.readLine();					
			}
		} while( true );				
	}

	/**
	 * aks the user id that whiches to enter and responds
	 */
	private void entrance() {
		consola.clear();
		consola.println("user id? ");
		int id = consola.readInt();
		// test if the user can enter
		if( true ){
			consola.println("You may enter");
		}
		else {
			consola.println("Sorry, access denied");
		}
		consola.readLine();		
	}

	/**
	 * aks the user id that is leaving
	 */
	private void leave() {
		consola.println("user id? ");
		int id = consola.readInt();		
		// check out
	}

	
	/** 
	 * method to close the premise operations
	 */
	public void closeOperacoes() {
		consola.close();		
	}
	
}
