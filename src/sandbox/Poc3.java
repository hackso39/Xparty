package sandbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Poc3 {

	static final String FIN = "q";
	
	public static void main(String[] args) {

		List<String> l1 = saisirListeJoueurs();
		
		try {
			
			String nomJoueur = trouverJoueurAuHasard(l1); // lève potentiellement une ListeVideException
			
			// Le code suivant ne s'exécute que si trouverJoueurAuHasard n'a pas levé d'exception.
			System.out.println("Le nom du joueur gagnant est : " + nomJoueur);
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Veuillez saisir un premier nombre : ");
			int nbr1 = sc.nextInt();
			
			System.out.println("Veuillez saisir un deuxième nombre : ");
			int nbr2 = sc.nextInt();
			
			int result = nbr1 / nbr2; // lève potentiellement une DivisionParZeroException
			System.out.println("Le résultat de : " + nbr1 + " divisé par : "+ nbr2 + " est : " + result);
			
		}
		catch (ListeVideException e) {
			System.out.println("Programme arrêté, liste vide");
			e.printStackTrace();
		}
		catch (ArithmeticException ae) {
			System.out.println("Une erreur est survenue lors d'un calcul : " + ae.getMessage());
		}
		catch (Exception e) {
			System.out.println("Une erreur est survenue lors de l'execution du programme.");
		}
		

    }

	private static List<String> saisirListeJoueurs() {
		
		List<String> liste = new ArrayList<String>();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		String motSaisi = null;
		
		while(!FIN.equals(motSaisi)) {
			System.out.println("veuillez saisir une liste de joueurs : ");
			motSaisi = sc.nextLine();
			if(!FIN.equals(motSaisi)) {
				liste.add(motSaisi);
			}
		}
		return liste;
	}

	private static String trouverJoueurAuHasard(List<String> l1) throws ListeVideException {
		
		Collections.shuffle(l1);  // on brasse la liste pour la désordonner
		
//		if (!l1.isEmpty()) {
//			return l1.get(0);
//		}
//		throw new ListeVideException();
		
		try {
			return l1.get(0); // On retourne le premier élément de la liste 
		}
		// Peut lever une exception si la liste est vide et que l'on demande le premier élément
		catch(IndexOutOfBoundsException ai) {
			throw new ListeVideException(ai);
		}
	}
}