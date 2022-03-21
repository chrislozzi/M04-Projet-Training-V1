import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

 /**Programme simple(sans la poo) de vente de formation. Les structures de données retenues pour représenter les listes de formations et le caddy sont des Map.
  *Liste des formation : clé=id et valeur=["Nom", "Durée", "Courte description", "Prix"]
  *Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "3000"]. 
  *Panier: clé=id et valeur=["Nom", "Durée", "Courte description", "Prix", "Quantité"] / 
  *Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "3000", "1"]   
  *@author Lozzi - 2022
  *@version 1.4
  */

public class FullTrainings {
	
	/**Flux d'E/S
	 *@since 1.1
	 */
	private static Scanner scan = new Scanner(System.in);//ouverture du flux 

	/** Liste des formations : id + data 
	 * @since 1.0*/
	protected static Map<Integer, ArrayList<String>> trainings = new HashMap<>();

	/**Contenue du panier: id + data(+quantité)
	   @since 1.1
	 */
	protected static Map<Integer, ArrayList<String>> caddy = new HashMap<>();

	/**Liste des formations à venir : id + data
	   @since 1.3
	 */
	protected static Map<Integer, ArrayList<String>> futurTrainings = new HashMap<>();
	
	private static int choice = 0;
	private static boolean firstDisplay = true;


	/**
	  *méthode main contient l'initialisation de la liste des formations et leur affichage dans un tableau.
	  *v1.3->Initialisation de la liste des formations à venir
	  *@since 1.0	   
	  *@param args pas d'arguments en ligne de commande
	 */
	
	public static void main(String[] args) {
	
		insertTrainings(); //initialise la liste des formations		
		insertFuturTrainings(); //initialise la liste des formations à venir		
		displayTrainings();//affiche le menu des formations
		scan.close();//fermeture du flux 
	}

	/**
	  * Méthode qui affiche la liste des formations. 
	  * v1.1 -> ajout d'items au menu : commande de formation et consultation du panier.
	  * v1.2 -> ajout d'intems au menu : récapitulatif de la commande. 
	  * v1.3 -> ajout d'items au menu : Formation à venir.
	  *@since 1.0
	  */
	public static void displayTrainings() {
		choice = 0;	
		//Message de bienvenue, ne s'affiche qu'au premier appel de la méthode displayTraining		
				
		if(firstDisplay) System.out.println("         Bonjour et bienvenue dans mon applications FullTrainings\n"
				+ "Nous allons vous proposer une liste de formation actuellement disponible");
		else {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("|                F O R M A T I O N S   D I S P O N I B L E S              |");}
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("|COURS          | NB/JOURS | DESCRIPTION                           | PRIX |");
			System.out.println("|---------------|----------|---------------------------------------|------|");
			trainings.forEach((key, value) -> {
				System.out.format("|%-15s| %-9s| %-38s| %-5s|\n",value.get(0), value.get(1), value.get(2),	value.get(3));
			});
			System.out.println("|-------------------------------------------------------------------------|");
			System.out.println("|1 : Commandez vos formations / 2 : Consultez votre panier                |");
			System.out.println("|-------------------------------------------------------------------------|");
			System.out.println("|3 : Récapitulatif de votre commande / 4 : Formations à venir             |");
			System.out.println("|-------------------------------------------------------------------------|");
			System.out.println("|5 : Quitter                                                              |");
			System.out.println("---------------------------------------------------------------------------");
			firstDisplay = false;
		while(choice < 1  || choice > 6) choice = getInfo(scan);		
		switch(choice){
		case 1 : displayOrderTrainings(); 
			break;
		case 2 : displayCaddy();
			break;
		case 3 : dsiplayInvoice();
			break;
		case 4 : displayFuturTrainnig();
			break;
		case 5 :System.out.println("Au revoir et merci de votre visite");
		System.exit(0);
			break;
		}
	}

	/** méthode qui affiche le menu d'achat de formation.
	   @since 1.1
	 */
	public static void displayOrderTrainings() {
		int quantityTraining = 0;
		int choiceTraining = 0 ;
		System.out.println("                    Commandez les formations de votre choix                   ");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("|ID |COURS          | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("|---|---------------|----------|---------------------------------------|----- |");

		trainings.forEach((key, value) -> {
			System.out.format("|%-3s|%-15s| %-9s| %-38s| %-5s|\n",key,value.get(0), value.get(1), value.get(2), value.get(3));
		});
		System.out.println("|-----------------------------------------------------------------------------|");
		System.out.println("|         Saisissez l'id de la formation souhaitée et tapez entrer            |");
		System.out.println("-------------------------------------------------------------------------------");


		while(!trainings.containsKey(choiceTraining)){choiceTraining = getInfo(scan);}

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("|              Saisissez le nombre de formation désirée                       |");
		System.out.println("-------------------------------------------------------------------------------");
		while(quantityTraining < 1) 
			quantityTraining = getInfo(scan);
		addTraining(choiceTraining,quantityTraining);
		displayCaddy();
	}

	/** méthode qui affiche le panier et son menu.
	 * v1.2->ajout d'item dans le menu : Retirer des formations.
	 * @since 1.1*/
	public static void displayCaddy() {
		choice = 0;
		int quantityTraining = 0;
		int choiceTraining = 0 ;
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("|                                 M O N     P A N I E R                                  |");
		System.out.println("|----------------------------------------------------------------------------------------|");

		if (!caddy.isEmpty()) {
			System.out.println("|ID |COURS           | NB/JOURS | DESCRIPTION                           | PRIX | Quantité|");
			System.out.println("|---|----------------|----------|---------------------------------------|------|---------|");
			//Contenu du panier si il n'est pas vide
			caddy.forEach((key, value) -> {
				System.out.format("|%-3s|%-16s| %-9s| %-38s| %-5s|   %-6s|\n",key, value.get(0), value.get(1),value.get(2), value.get(3), value.get(4));			
			});
			System.out.println("-----------------------------------------------------------------------------------------|");
			System.out.println("|1 : Retour au menu principal    /    2 : Retirez des formations                         |");
			System.out.println("------------------------------------------------------------------------------------------");
		}else {
			System.out.println("|                                                                                        |");	
			System.out.println("|                          Vous n'avez commandé aucune formation                         |");
			System.out.println("|                                                                                        |");
			System.out.println("|----------------------------------------------------------------------------------------|");
			System.out.println("|1 : Retour au menu principal  / 2 : Commandez vos formations                            |");
			System.out.println("------------------------------------------------------------------------------------------");
			//choix, cas panier vide
			while(choice > 2 || choice == 0) {choice = getInfo(scan);}
			if(choice == 1)displayTrainings();
			displayOrderTrainings();
		}
		while(choice > 2 || choice == 0) {choice = getInfo(scan);}		
		if(choice == 1)displayTrainings();		
		while(!caddy.containsKey(choiceTraining)) {
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("|--------------------Saisissez l'id de la formation à supprimer-------------------------|");
			System.out.println("-----------------------------------------------------------------------------------------");
			choiceTraining = getInfo(scan);			

		}
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("|---------------------Saisissez le nombre de formation à supprimer----------------------|");
		System.out.println("-----------------------------------------------------------------------------------------");
		
		//Vérifie que la quantité a supprimer est bien inférieur a la quantité présente dans le panier
		while(quantityTraining> Integer.parseInt(caddy.get(choiceTraining).get(4)));
			quantityTraining = getInfo(scan); 
		removeTraining(choiceTraining,quantityTraining);
		displayCaddy();
	}	

	/**Méthode qui affiche le menu récapitulatif de la commande avant le payement.
	  *@since 1.3 */
	public static void dsiplayInvoice(){
		choice =0;
		int amountTrainings = 0;
		if(caddy.isEmpty())displayCaddy();		
		System.out.println("------------------------------");
		System.out.println("|   M A   C O M M A N D E    |");
		System.out.println("------------------------------");
		System.out.format("|%-16s|   %-8s|\n","COURS","Quantité");
		System.out.println("|----------------------------|");
		caddy.forEach((key, value) -> {

			System.out.format("|%-16s|   %-8s|\n",value.get(0), value.get(4));
		});
		System.out.println("|----------------------------|");	
		System.out.format("|MONTANT TOTAL : %11d€|\n",invoiceAmount());
		System.out.println("------------------------------");
		System.out.println("| 1 : Retour / 2 : Valider   |");
		System.out.println("------------------------------");


		while(choice > 2 || choice == 0) { choice = getInfo(scan);} 
		if(choice == 1)displayTrainings(); //validation de l'achat et vidage du panier retour au menu principal
		else {			  
			for(ArrayList<String> training :caddy.values())	
				amountTrainings += (Integer.parseInt((String) training.get(3)));
			
			System.out.format("Votre achat de %s formations, pour un montant total de %d€ à bien été effectué\n\n"
				+ "M E R C I   P O U R   V O T R E   C O N F I A N C E\n\n\n", amountTrainings ,invoiceAmount());	
			caddy.clear();//suppression du contenu du panier
			displayTrainings();
		}	
	}

	/**Méthode pour calculer le montant de la facture.
	  *@since 1.2
	  */	
	private static int  invoiceAmount() {
		int amount = 0;
		for(ArrayList<String> training :caddy.values())	
			amount += (Integer.parseInt((String) training.get(3))*Integer.parseInt((String) training.get(4)));	
		return amount;

	}
	/**Méthode qui affiche les formation à venir.
	  *@since 1.3
	  */
	public static void displayFuturTrainnig() {
		System.out.println("|---------------------------------------------------------------------------");
		System.out.println("|                  F O R M A T I O N S   A   V E N I R                     |");
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|COURS           | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("|----------------|----------|---------------------------------------|------|");

		futurTrainings.forEach((key, value) -> {
			System.out.format("|%-16s| %-9s| %-38s| %-5s|\n",value.get(0), value.get(1),value.get(2), value.get(3));			
		});
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|1 : Retour au menu principal /                                            |");
		System.out.println("----------------------------------------------------------------------------");
		while(choice != 1)
			choice = getInfo(scan);
		displayTrainings();
							
		
	}

	/**
	  *Méthode d'ajout de formation dans le panier.
	  *@param idTraining identifiant de la formation à ajouter au panier.
	  *@param Quantity quantité de formation à ajouter au panier.
	  *@since 1.1
	  */
	private static void addTraining(int idTraining, int Quantity) {
		int intCaddyQuantity;
		String getStringTrainingsQuantity ="";

		if(caddy.containsKey(idTraining)) { //le panier contient déja la formation

			getStringTrainingsQuantity = caddy.get(idTraining).get(4);
			caddy.get(idTraining).remove(4);

			//conversion string to int et ajout d'une qunatité
			intCaddyQuantity = Quantity +Integer.parseInt(getStringTrainingsQuantity); 

			//conversion int to string et ajout  caddy
			caddy.get(idTraining).add(String.valueOf(intCaddyQuantity));	
		}else {			
			//initialisation d'un panier vide avec la première formation commandée et la quantité souhaitée
			caddy.put(idTraining, new ArrayList<String>());
			caddy.get(idTraining).add(trainings.get(idTraining).get(0));caddy.get(idTraining).add(trainings.get(idTraining).get(1));
			caddy.get(idTraining).add(trainings.get(idTraining).get(2));caddy.get(idTraining).add(trainings.get(idTraining).get(3));
			caddy.get(idTraining).add(String.valueOf(Quantity));
		}
	}

	private static int getInfo(Scanner scan) {
		while(!scan.hasNextInt())			
			scan.next();
		return scan.nextInt();		
	}

	/**
	 *Méthode de suppression de formation dans le panier.
	 *@param idTraining : identifiant de la formation à supprimer.
	 * @param subQuantity : quantité de formation à supprimer du panier.
	 * @since v1.2
	 * */
	public static void removeTraining(int idTraining, int quantityToSub) {
		int intCaddyQuantity;   		
		Integer intCaddyQuantityCaddy = Integer.parseInt(caddy.get(idTraining).get(4)); //quantité de formation présente dans le panier, conversion en int.
		if(intCaddyQuantityCaddy > quantityToSub){	
			caddy.get(idTraining).remove(4);		//supression du champs quantité de la formation			
			intCaddyQuantity = intCaddyQuantityCaddy - quantityToSub;		//soustraction de la quantité désirée			 
			caddy.get(idTraining).add(String.valueOf(intCaddyQuantity));		//conversion int to string et mise à jour du champ quantité 
		} else {   		//supprime la formation du panier
			caddy.remove(idTraining);
		}

	}

	/** Méthode qui initialise la liste des formations. 
	   @since 1.0
	 */
	private static final void insertTrainings() {
		trainings.put(1, new ArrayList<String>());trainings.get(1).add("Java");trainings.get(1).add("20");trainings.get(1).add("Java SE 8 : Syntaxe & Poo");trainings.get(1).add("3000");
		trainings.put(2, new ArrayList<String>());trainings.get(2).add("Java avancé");trainings.get(2).add("20");	trainings.get(2).add("Exceptions, fichiers, Jdbc, thread...");trainings.get(2).add("5000");	
		trainings.put(3, new ArrayList<String>());trainings.get(3).add("Spring");trainings.get(3).add("20");trainings.get(3).add("Spring Core/Mvc/Security");trainings.get(3).add("5000");
		trainings.put(4, new ArrayList<String>());trainings.get(4).add("Php frameworks");trainings.get(4).add("15");trainings.get(4).add("Symphony");trainings.get(4).add("2500");		
		trainings.put(5, new ArrayList<String>());trainings.get(5).add("C#");trainings.get(5).add("20");trainings.get(5).add("DotNet Core");trainings.get(5).add("5000");
	}
	/** Méthode qui initialise la liste des formations à venir.
	   @since 1.4
	 */
	private static final void insertFuturTrainings() {
		futurTrainings.put(1, new ArrayList<String>());futurTrainings.get(1).add("Web");futurTrainings.get(1).add("18");futurTrainings.get(1).add("HTML/CSS, HTTP/JS/DOM/EVENT");futurTrainings.get(1).add("3200");
		futurTrainings.put(2, new ArrayList<String>());futurTrainings.get(2).add("Git hub/flow");futurTrainings.get(2).add("35");futurTrainings.get(2).add("Console, commandes avancées, workflow");futurTrainings.get(2).add("7500");	
		futurTrainings.put(3, new ArrayList<String>());futurTrainings.get(3).add("UML");futurTrainings.get(3).add("15");futurTrainings.get(3).add("Modélisation Poo et système");futurTrainings.get(3).add("2500");
		futurTrainings.put(4, new ArrayList<String>());futurTrainings.get(4).add("Maven 3");futurTrainings.get(4).add("27");futurTrainings.get(4).add("Packaging, dépendance, POM");futurTrainings.get(4).add("6500");		
		futurTrainings.put(5, new ArrayList<String>());futurTrainings.get(5).add("JUnit4 framework");futurTrainings.get(5).add("20");futurTrainings.get(5).add("Cas de tests, Ant, eXtreme Programming");futurTrainings.get(5).add("5000");
	}
}







