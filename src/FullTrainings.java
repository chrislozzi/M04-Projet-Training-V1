import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
   Programme simple(sans la poo) de vente de formation. Les structures de données retenues pour représenter les listes de formations et le caddy sont des Map.
   Liste des formation : clé=id et valeur=["Nom", "Durée", "Courte description", "Prix"]
   Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "3000"]. 
   Panier: clé=id et valeur=["Nom", "Durée", "Courte description", "Prix", "Quantité"] / 
   Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "3000", "1"]   
   @author Lozzi - 2022
   @version 1.3
 */

public class FullTrainings {

	/**Flux d'E/S
	   @since 1.1
	 */
	private static Scanner scan = new Scanner(System.in);//ouverture du flux 

	/** Liste des formations : id + data */
	protected static Map<Integer, ArrayList<String>> trainings = new HashMap<>();

	/**Contenue du panier: id + data(+quantité)
	   @since 1.1
	 */
	protected static Map<Integer, ArrayList<String>> caddy = new HashMap<>();
	
	/**
	   méthode main contien l'initialisation de la liste des formations et leur
	   affichage dans un tableau

	   @param args pas d'arguments en ligne de commande
	   @since 1.0
	 */
	public static void main(String[] args) {

		insertTraining(); //initialise la liste des formations		
		displayTrainings();//affiche le menu des formations
		scan.close();//fermeture du flux 
	}

	/**
	   méthode qui affiche la liste des formations
	   @since 1.0
	 */
	public static void displayTrainings() {
		int choice;
		System.out.println("         Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("COURS          | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("---------------|----------|---------------------------------------|------|");
		trainings.forEach((key, value) -> {
			System.out.format("%-15s| %-9s| %-38s| %-5s|\n",value.get(0), value.get(1), value.get(2),	value.get(3));
		});
		System.out.println("-------------------------------------------------------------------------|");
		System.out.println("1 : Commander des formations / 2 : Consulter votre panier                |");
		System.out.println("-------------------------------------------------------------------------|");
		System.out.println("3 : Récapitulatif de vôtre commande / 4 : Quitter                        |");
		System.out.println("--------------------------------------------------------------------------");
		choice = getInfo(scan);
		switch(choice){
		case 1 : displayBuyTrainings(); 
		break;
		case 2 : displayCaddy();
		break;
		case 3 : dsiplayInvoice();
		break;
		case 4 :System.out.println("Au revoir et merci de votre visite");
		System.exit(0);
		break;

		default:
			displayWrongInput();
			displayTrainings();
			break;
		}
	}

	/** méthode qui affiche le menu d'achat de formation 
	   @since 1.1
	 */
	public static void displayBuyTrainings() {
		int choiceTraining, quantityTraining;

		System.out.println("                    Commandez les formations de votre choix                   ");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("|ID |COURS          | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("|---|---------------|----------|---------------------------------------|----- |");

		trainings.forEach((key, value) -> {
			System.out.format("|%-3s|%-15s| %-9s| %-38s| %-5s|\n",key,value.get(0), value.get(1), value.get(2), value.get(3));
		});
		System.out.println("|-----------------------------------------------------------------------------|");
		System.out.println("|         Saisissez l'id de la formation souhaité et tapez entrer             |");
		System.out.println("-------------------------------------------------------------------------------");
		choiceTraining = getInfo(scan);
		if(!trainings.containsKey(choiceTraining)){
			displayWrongInput();
			displayBuyTrainings();}

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("|              Saisissez le nombre de formation désiré                        |");
		System.out.println("-------------------------------------------------------------------------------");
		quantityTraining = getInfo(scan);
		addTraining(choiceTraining,quantityTraining);
		displayTrainings();
	}

	/** méthode qui affiche le menu du panier */
	public static void displayCaddy() {
		int choice, quantityTraining;
		int choiceTraining =0;
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
			System.out.println("|1 : Retour à la liste des formations    /    2 : Modifiez votre panier                  |");
			System.out.println("-----------------------------------------------------------------------------------------|");
		}else {//contenu du panier vide
			System.out.println("|                                                                                        |");	
			System.out.println("|                          Vous n'avez commandé aucune formation                         |");
			System.out.println("|                                                                                        |");
			System.out.println("|----------------------------------------------------------------------------------------|");
			System.out.println("|1 : Retour à la liste des formations                                                    |");
			System.out.println("------------------------------------------------------------------------------------------");
		}


		choice = getInfo(scan);
		if(choice > 2) {//erreur de saisi
			displayWrongInput();
			displayCaddy();}
		if (choice == 1) displayTrainings();//retour
		if (choice == 2) {	
			while(!caddy.containsKey(choiceTraining)) {
				System.out.println("-----------------------------------------------------------------------------------------");
				System.out.println("|--------------------Saisissez l'id de la formation à supprimer-------------------------|");
				System.out.println("-----------------------------------------------------------------------------------------");
				choiceTraining = getInfo(scan);			

			}
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("|                     Saisissez le nombre de formation à supprimer                      |");
			System.out.println("-----------------------------------------------------------------------------------------");
			quantityTraining = getInfo(scan);
			removeTraining(choiceTraining,quantityTraining);
			displayTrainings();}

	}

	private static void displayWrongInput() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("|                     S A S I E      I N C C O R E C T E                  |");
		System.out.println("---------------------------------------------------------------------------");
	}
	
	
	private static void dsiplayInvoice(){
		int choice ;
		System.out.println("------------------------------");
		System.out.println("|    M A   C O M M A N D E   |");
		System.out.println("-----------------------------|");	
		                  
		System.out.println("| COURS          | Quantité  |");
		System.out.println("|----------------------------|");
		caddy.forEach((key, value) -> {
			
			System.out.format("|%-16s|   %-8s|\n",value.get(0), value.get(4));
		});
		System.out.println("------------------------------");	
		System.out.format("|TOTAL : %-4s%-14s|\n",invoiceAmount(), "€");
		System.out.println("------------------------------");
		System.out.println("| 1 : Retour / 2 : Valider   |");
		System.out.println("------------------------------");
		
		/*
		 * while(choice > 2) { choice = getInfo(scan);} if(choice ==
		 * 1)displayTrainings(); else pay;
		 */
				
	}
	/**Méthode pour calculer le montant de la facture*/
	private static int  invoiceAmount() {
		int amount = 0;
		for(ArrayList<String> training :caddy.values())	
			amount += (Integer.parseInt((String) training.get(3))*Integer.parseInt((String) training.get(4)));	
		return amount;
		
	}

	/**
	   Méthode d'ajout de formation dans le panier
	   @param idTraining identifiant de la formation à ajouter au panier
	   @param Quantity quantité de formation à ajouter au panier
	   @since 1.1
	 */
	private static void addTraining(Integer idTraining, int Quantity) {
		int intQuantityTraining;
		String getStringQuantityTraining ="";
		String addStringQuantityTraining = String.valueOf(Quantity);
			
			if(caddy.containsKey(idTraining)) { //le panier contient déja la formation
				
				getStringQuantityTraining = caddy.get(idTraining).get(4);caddy.get(idTraining).remove(4);

				//conversion string to int et ajout d'une qunatité
				intQuantityTraining = Quantity +Integer.parseInt(getStringQuantityTraining); 
				
				//conversion int to string et ajout  caddy
				caddy.get(idTraining).add(String.valueOf(intQuantityTraining));	
			}else {			
			//initialisation d'un panier vide avec la première formation commandée et la quantité souhaitée
			caddy.put(idTraining, new ArrayList<String>());
			caddy.get(idTraining).add(trainings.get(idTraining).get(0));caddy.get(idTraining).add(trainings.get(idTraining).get(1));
			caddy.get(idTraining).add(trainings.get(idTraining).get(2));caddy.get(idTraining).add(trainings.get(idTraining).get(3));
			caddy.get(idTraining).add(addStringQuantityTraining);
			}
	}
	
	private static int getInfo(Scanner scan) {
		while(!scan.hasNextInt())			
			scan.next();
		return scan.nextInt();		
	}

	/**
	   Méthode de suppression de formation dans le panier
	   @param idTraining identifiant de la formation à supprimer
	   @param subQuantity quantité de formation selectionnée a supprimer du panier
	 * @since v2.0*/
	private static void removeTraining(Integer idTraining, int subQuantity) {
		int intQuantityTraining;   		
		Integer intQuantityTrainingCaddy = Integer.parseInt(caddy.get(idTraining).get(4)); //On récupère la formation dans le panier, conversion en int.	

		if(intQuantityTrainingCaddy < subQuantity) { displayWrongInput();displayCaddy();} //retour au menu du panier

		if(intQuantityTrainingCaddy > subQuantity){	

			caddy.get(idTraining).remove(4);//supression du champs quantité de la formation

			//suppression de la quantité désirée
			intQuantityTraining = intQuantityTrainingCaddy - subQuantity;

			//conversion int to string et ajout d'un nouveau champ quantité au caddy 
			caddy.get(idTraining).add(String.valueOf(intQuantityTraining));

		} else {   //supprime la formation du panier car, (quantié dans le panier) = (quantité à supprimer)
			caddy.remove(idTraining);
		}

	}

	/** Méthode qui initialise la liste des formations 
	   @since 1.0
	 */
	private static final void insertTraining() {
		trainings.put(1, new ArrayList<String>());trainings.get(1).add("Java");trainings.get(1).add("20");trainings.get(1).add("Java SE 8 : Syntaxe & Poo");trainings.get(1).add("3000");
		trainings.put(2, new ArrayList<String>());trainings.get(2).add("Java avancé");trainings.get(2).add("20");	trainings.get(2).add("Exceptions, fichiers, Jdbc, thread...");trainings.get(2).add("5000");	
		trainings.put(3, new ArrayList<String>());trainings.get(3).add("Spring");trainings.get(3).add("20");trainings.get(3).add("Spring Core/Mvc/Security");trainings.get(3).add("5000");
		trainings.put(4, new ArrayList<String>());trainings.get(4).add("Php frameworks");trainings.get(4).add("15");trainings.get(4).add("Symphony");trainings.get(4).add("2500");		
		trainings.put(5, new ArrayList<String>());trainings.get(5).add("C#");trainings.get(5).add("20");trainings.get(5).add("DotNet Core");trainings.get(5).add("5000");
	}
}







