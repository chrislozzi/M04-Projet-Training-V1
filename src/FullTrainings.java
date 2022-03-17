import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FullTrainings {
	/**
	 * Programme simple(sans la poo) de vente de formation. Les structures de données retenues pour représenter les listes
	 *  de formations et le caddy sont des HashMap. Liste des formation: clé=id et valeur=["Nom", "Durée", "Courte description", "Prix"] / 
	 *  Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "Prix"]. 
	 * 
	 * @author Lozzi - 2022
	 * @since 1.0
	 */
	/** Liste des noms de formations */
	public static final String[] trainingName = { "Java", "Java avancé", "Spring", "Php frameworks", "C#", " ", };

	/** Liste des durées des formations en nombre de jours */
	public static final String[] trainingDuration = { "20", "15", "0", };

	/** Liste des description des formation */
	public static final String[] trainingContent = { "Java SE 8 : Syntaxe & Poo",
			"Exceptions, fichiers, Jdbc, thread...", "Spring Core/Mvc/Security", "Symphony", "DotNet Core", " ", };

	/** Liste des prix des formations */
	public static final String[] trainingPrice = { "3000", "5000", "2500", " ", };

	/**
	 * Flux d'E/S
	 * 
	 * @since 2.0
	 */
	private static Scanner scan = new Scanner(System.in);

	/** Liste des formations : id + data */
	protected static Map<Integer, ArrayList<String>> trainings = new HashMap<>();

	/**
	 * Contenue du panier(commande en cours)
	 * 
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

		insertTraining();
		//inserCaddy();
		displayTrainings();
		scan.close();
	}

	/**
	 * méthode qui affiche la liste des formations
	 */
	public static void displayTrainings() {
		int choice = 0;

		System.out.println("         Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("COURS          | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("---------------|----------|---------------------------------------|------|");
		trainings.forEach((key, value) -> {
			System.out.format("%-15s| %-9s| %-38s| %-5s|",value.get(0), value.get(1), value.get(2),
					value.get(3));
			System.out.println();
		});
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("1 : Commande de formation / 2 : Consulter votre panier  / 3 : Quitter    |");
		System.out.println("--------------------------------------------------------------------------\n");
		while (scan.hasNext() == false)
			scan.next();
		choice = scan.nextInt();
		switch(choice){
		case 1 : displayBuyTrainings();
			break;
		case 2 : displayCaddy();
		case 3 :System.out.println("Au revoir et merci de votre visite");
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
		System.out.println("Saisissez l'id de la formation souhaitez suivi d'un espace puis tapez entré  |");
		System.out.println("-----------------------------------------------------------------------------|\n");
		//addTrainingToCaddy(scan);
		//displayCaddy();
	}

	/** méthode qui affiche un menu secondaire, le contenu du panier */
	public static void displayCaddy() {
		int choice;

		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("                                     MON PANIER                                          |");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("ID |COURS           | NB/JOURS | DESCRIPTION                           | PRIX | Quantité |");
		System.out.println("---|----------------|----------|-------------------------------------- | ---- |----------|");
		if (caddy.isEmpty() != false)
			for (int i = 0; i < caddy.size(); i++)
				if (caddy.get(i) != null) {

					System.out.format("%-9s| %-15s| %-9s| %-38s| %-5s|", caddy.get(i), trainings.get(1),
							trainings.get(2), trainings.get(3), trainings.get(4));
					System.out.println();
				}
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("1 : Retour à la liste des formations                                                     |");
		System.out.println("-----------------------------------------------------------------------------------------\n");

		choice = scan.nextInt();
		if (choice == 1)
			displayTrainings();
	}

	/**
	 * Méthode d'ajout de formation dans le panier
	 * 
	 * @param input des id de formation commandées*/
	 private static void addTraining(int id) {
		 
	 }
	
	/**Méthode qui initialise le panier*/
	private static void insertPanier() {
		
	}
												 

	/** Méthode qui initialise la liste des formations */
	private static void insertTraining() {

		trainings.put(1, new ArrayList<String>());
		trainings.get(1).add(trainingName[0]);
		trainings.get(1).add(trainingDuration[0]);
		trainings.get(1).add(trainingContent[0]);
		trainings.get(1).add(trainingPrice[0]);

		trainings.put(2, new ArrayList<String>());
		trainings.get(2).add(trainingName[1]);
		trainings.get(2).add(trainingDuration[0]);
		trainings.get(2).add(trainingContent[1]);
		trainings.get(2).add(trainingPrice[1]);

		trainings.put(3, new ArrayList<String>());
		trainings.get(3).add(trainingName[2]);
		trainings.get(3).add(trainingDuration[0]);
		trainings.get(3).add(trainingContent[2]);
		trainings.get(3).add(trainingPrice[1]);

		trainings.put(4, new ArrayList<String>());
		trainings.get(4).add(trainingName[3]);
		trainings.get(4).add(trainingDuration[1]);
		trainings.get(4).add(trainingContent[3]);
		trainings.get(4).add(trainingPrice[2]);

		trainings.put(5, new ArrayList<String>());
		trainings.get(5).add(trainingName[4]);
		trainings.get(5).add(trainingDuration[0]);
		trainings.get(5).add(trainingContent[4]);
		trainings.get(5).add(trainingPrice[1]);

	}
}
