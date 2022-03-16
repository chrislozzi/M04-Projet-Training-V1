import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullTrainings {
/**Programme simple(sans la poo) de vente de formation
 * Les structures de données retenues pour représenter les listes de formations sont des HashMap, 
 * clé=id et valeur=["Nom", "Durée", "Courte description", "Prix"] / Exemple 1=["Java", "20", "Java SE 8 : Syntaxe & Poo", "Prix"]
 * @author Lozzi - 2022
 * @since 1.0
 */
	/**Liste des noms de formations*/
	public static final String[] trainingName ={
		"Java",
		"Java avancé",
		"Spring",
		"Php frameworks",
		"C#",
	};
	
	/**Liste des durées des formations en nombre de jours*/
	public static final String[] trainingDuration ={
		"20",
		"15",
	};
	
	/**Liste des description des formation*/
	public static final String[] trainingContent ={
		"Java SE 8 : Syntaxe & Poo",
		"Exceptions, fichiers, Jdbc, thread...",
		"Spring Core/Mvc/Security",
		"Symphony",
		"DotNet Core",
	};
	
	/**Liste des prix des formations*/
	public static final String[] trainingPrice ={
		"3000",
		"5000",
		"2500",
	};
		
	/**Liste des formations : id + data */
	protected static  Map<Integer,ArrayList<String>> trainings = new HashMap<>();
	/**
	 * 
	 * méthode main contien l'initialisation de la liste des formations et leur affichage dans un tableau
	 * @param args pas d'arguments en ligne de commande
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertTraining();
		displayTrainings();
	}
	
	/** méthode qui affiche la liste des formations  */
	public static void displayTrainings() {
		System.out.println("         Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("COURS           | NB/JOURS | DESCRIPTION                           | PRIX |");
		System.out.println("----------------|----------|-------------------------------------- | ---- |");				
		trainings.forEach((key,value)->{			
				System.out.format("%-16s| %-9s| %-38s| %-5s|", value.get(0) ,  value.get(1)  ,value.get(2) , value.get(3));						
			System.out.println();
		});
		System.out.println("--------------------------------------------------------------------------");
	}
	
	/**Méthode qui initialise la liste des formations*/
	private static void insertTraining() {
		
			trainings.put(1, new ArrayList<String>());
			trainings.get(1).add(trainingName[0]); 	trainings.get(1).add(trainingDuration[0]);	
			trainings.get(1).add(trainingContent[0]); trainings.get(1).add(trainingPrice[0]);
			
			trainings.put(2, new ArrayList<String>());
			trainings.get(2).add(trainingName[1]); 	trainings.get(2).add(trainingDuration[0]);	
			trainings.get(2).add(trainingContent[1]); trainings.get(2).add(trainingPrice[1]);
			
			trainings.put(3, new ArrayList<String>());
			trainings.get(3).add(trainingName[2]); 	trainings.get(3).add(trainingDuration[0]);	
			trainings.get(3).add(trainingContent[2]); trainings.get(3).add(trainingPrice[1]);
			
			trainings.put(4, new ArrayList<String>());
			trainings.get(4).add(trainingName[3]); 	trainings.get(4).add(trainingDuration[1]);	
			trainings.get(4).add(trainingContent[3]); trainings.get(4).add(trainingPrice[2]);
			
			trainings.put(5, new ArrayList<String>());
			trainings.get(5).add(trainingName[4]); 	trainings.get(5).add(trainingDuration[0]);	
			trainings.get(5).add(trainingContent[4]); trainings.get(5).add(trainingPrice[1]);
						
		}		
	}


