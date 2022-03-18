import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FullTrainings {
	/**
	 * Programme simple(sans la poo) de vente de formation. Les structures de données retenues pour représenter les listes
	 *  de formations et le caddy sont des HashMap. Liste des formation: clé=id et valeur=["Nom", "Durée", "Courte description", "Prix"] / 
	 *  Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "3000"]. Panier: clé=id et valeur=["Nom", "Durée", "Courte description", "Prix", "Quantité"] / 
	 *  Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "3000", "1"]. 
	 * 
	 * @author Lozzi - 2022
	 * @since 1.0
	 */


	/** Méthode qui initialise la liste des formations */
	private static final void insertTraining() {

		trainings.put(1, new ArrayList<String>());trainings.get(1).add("Java");trainings.get(1).add("20");trainings.get(1).add("Java SE 8 : Syntaxe & Poo");trainings.get(1).add("3000");
		trainings.put(2, new ArrayList<String>());trainings.get(2).add("Java avancé");trainings.get(2).add("20");	trainings.get(2).add("Exceptions, fichiers, Jdbc, thread...");trainings.get(2).add("5000");		
		trainings.put(3, new ArrayList<String>());trainings.get(3).add("Spring");trainings.get(3).add("20");trainings.get(3).add("Spring Core/Mvc/Security");trainings.get(3).add("5000");
		trainings.put(4, new ArrayList<String>());trainings.get(4).add("Php frameworks");trainings.get(4).add("15");trainings.get(4).add("Symphony");trainings.get(4).add("2500");		
		trainings.put(5, new ArrayList<String>());trainings.get(5).add("C#");trainings.get(5).add("20");trainings.get(5).add("DotNet Core");trainings.get(5).add("5000");
	}

	/**
	 * Flux d'E/S
	 * @since 2.0
	 */
	private static Scanner scan = new Scanner(System.in);//ouverture du flux 

	/** Liste des formations : id + data */
	protected static Map<Integer, ArrayList<String>> trainings = new HashMap<>();

	/**
	 * Contenue du panier(commande en cours)
	 * @since 2.0
	 */
	protected static Map<Integer, ArrayList<String>> caddy = new HashMap<>();

	/**
	 * méthode main contien l'initialisation de la liste des formations et leur
	 * affichage dans un tableau
	 * 
	 * @param args pas d'arguments en ligne de commande
	 */
	public static void main(String[] args) {

		insertTraining(); //initialise la liste des formation		
		displayTrainings();//affiche la liste des formation
		scan.close();//fermeture du flux 
	}

	/**
	 * méthode qui affiche la liste des formations
	 */
	public static void displayTrainings() {
		int choice;
		System.out.println("         Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("COURS          | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("---------------|----------|---------------------------------------|------|");
		trainings.forEach((key, value) -> {
			System.out.format("%-15s| %-9s| %-38s| %-5s|",value.get(0), value.get(1), value.get(2),	value.get(3));
			System.out.println();
		});
		System.out.println("-------------------------------------------------------------------------|");
		System.out.println("1 : Commande de formation / 2 : Consulter votre panier  / 3 : Quitter    |");
		System.out.println("--------------------------------------------------------------------------");
		choice = getInfo(scan);
		switch(choice){
		case 1 : displayBuyTrainings();
		break;
		case 2 : displayCaddy();
		break;
		case 3 :System.out.println("Au revoir et merci de votre visite");
		break;
		default:
			displayWrongInput();
			displayTrainings();
			break;
		}
	}

	/** méthode qui affiche le menu d'achat de formation */
	public static void displayBuyTrainings() {

		System.out.println("                    Commandez les formations de votre choix                   ");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("ID |COURS          | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("---|---------------|----------|---------------------------------------|----- |");
		trainings.forEach((key, value) -> {
			System.out.format("%-3s|%-15s| %-9s| %-38s| %-5s|",key,value.get(0), value.get(1), value.get(2), value.get(3));
			System.out.println();
		});
		System.out.println("-----------------------------------------------------------------------------|");
		System.out.println("Saisissez l'id de la formation souhaitez et tapez entrer                     |");
		System.out.println("------------------------------------------------------------------------------");

		addTraining(scan);
		displayTrainings();
	}

	/** méthode qui affiche un menu secondaire, le contenu du panier */
	public static void displayCaddy() {
		int choice;

		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("                                     MON PANIER                                         |");
		System.out.println("----------------------------------------------------------------------------------------|");

		if (!caddy.isEmpty()) {
			System.out.println("ID |COURS           | NB/JOURS | DESCRIPTION                           | PRIX | Quantité|");
			System.out.println("---|----------------|----------|---------------------------------------|------|---------|");
			caddy.forEach((key, value) -> {

				System.out.format("%-3s|%-16s| %-9s| %-38s| %-5s|   %-6s|",key, value.get(0), value.get(1),value.get(2), value.get(3), value.get(4));
				System.out.println();				
			});
			System.out.println("----------------------------------------------------------------------------------------|");
			System.out.println("1 : Retour à la liste des formations    /    2 : Modifier votre panier                  |");
			System.out.println("----------------------------------------------------------------------------------------|");
		}else {
			System.out.println("                                                                                        |");	
			System.out.println("                          Vous n'avez commandé aucune formation                         |");
			System.out.println("                                                                                        |");
			System.out.println("----------------------------------------------------------------------------------------|");
			System.out.println("1 : Retour à la liste des formations                                                    |");
			System.out.println("----------------------------------------------------------------------------------------|");
		}


		choice = getInfo(scan);
		if(choice > 2) {
			displayWrongInput();
			displayTrainings();}
		if (choice == 1) displayTrainings();
		if (choice == 2) {			
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("---------------------Saisissez l'id de la formation à supprimer-------------------------|");
			System.out.println("-----------------------------------------------------------------------------------------");
			removeTraining(scan);
			displayCaddy();
		}
	}
	//affichage en cas d'erreur de saisi
	private static void displayWrongInput() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("                     S A S I E      I N C C O R E C T E                  |");
		System.out.println("--------------------------------------------------------------------------");
	}


	/**
	 * Méthode d'ajout de formation dans le panier
	 * @param input des id de formation commandées*/
	private static void addTraining(Scanner scan) {
		Integer choiceId;
		int intQuantityTraining;
		String stringQuantityTraining;
		choiceId = getInfo(scan);
		if(!trainings.containsKey(choiceId))  {
			displayWrongInput();
			displayBuyTrainings();}
		//initialisation d'un panier vide avec la première formation commandée
		if(caddy.isEmpty()) {			
			caddy.put(choiceId, new ArrayList<String>());
			caddy.get(choiceId).add(trainings.get(choiceId).get(0));caddy.get(choiceId).add(trainings.get(choiceId).get(1));
			caddy.get(choiceId).add(trainings.get(choiceId).get(2));caddy.get(choiceId).add(trainings.get(choiceId).get(3));
			caddy.get(choiceId).add("1");
		}else{
			if(!caddy.containsKey(choiceId)) {	

				//ajoute une nouvelle formation au panier, quantité 1
				caddy.put(choiceId, new ArrayList<String>());
				caddy.get(choiceId).add(trainings.get(choiceId).get(0));caddy.get(choiceId).add(trainings.get(choiceId).get(1));
				caddy.get(choiceId).add(trainings.get(choiceId).get(2));caddy.get(choiceId).add(trainings.get(choiceId).get(3));
				caddy.get(choiceId).add("1");
			}else {

				//incrémente de 1 une formation déjà présente dans le panier
				stringQuantityTraining = caddy.get(choiceId).get(4);
				caddy.get(choiceId).remove(4);

				//conversion string to int pour incrémenté la quantité
				intQuantityTraining = 1 +Integer.parseInt(stringQuantityTraining); 
				//conversion int to string et ajout au caddy
				caddy.get(choiceId).add(String.valueOf(intQuantityTraining));		
			}
		}

	}
	//gestion des erreur de saisies, string a la place d'un integer
	private static int getInfo(Scanner scan) {
		while(!scan.hasNextInt() )			
			scan.next();
		return scan.nextInt();		
	}

	/**
	 * Méthode de suppression de formation dans le panier
	 * @param input des id de formation commandées*/
	private static void removeTraining(Scanner scan) {
		Integer choiceId;
		int intQuantityTraining;
		String stringQuantityTraining ="";
		choiceId = getInfo(scan);

		//test la présence de la formation dans le panier
		if(!caddy.containsKey(choiceId)) 
		{displayWrongInput();displayCaddy();
		//test sur la quantité de la formation selectionnée
		}else if(!caddy.get(choiceId).get(4).equals("1"))
		{
			//décrémente de 1 une formation déjà présente dans le panier
			stringQuantityTraining = caddy.get(choiceId).get(4);
			caddy.get(choiceId).remove(4);

			//conversion string to int pour incrémenté la quantité
			intQuantityTraining = Integer.parseInt(stringQuantityTraining) - 1;

			//conversion int to string et ajout au caddy
			caddy.get(choiceId).add(String.valueOf(intQuantityTraining));
			
		} else {   //supprime la formation <k,v> du panier  si quantité = 0
			caddy.remove(choiceId);
		}


	}
}







