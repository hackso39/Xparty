package sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class Poc1 {

	/**
	 * Point d'entrée de mon application POC1.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 1
		new Joueur("Christophe", "Virot");

		// 2
		String prenomFred1 = "Frédéric"; // = new String("Frederic")
		Joueur fred = new Joueur(prenomFred1, "Spade");
		fred.setPseudo("HackSo");
		System.out.println(fred.getNom() + " " + fred.getPrenom() + " également appelé " + fred.getPseudo());
		// fred -> référence vers instance de fred
		
		// 3 
		Joueur mailys1 = new Joueur("Maïlys", "Ahmed"); 
		mailys1.setPseudo("Petit chat");
		// mailys1 -> référence vers instance de mailys
		
		Joueur mailys2 = mailys1;
		mailys2.setPseudo("Petite chatte");
		// mailys2-> référence vers instance de mailys
		
		// -> affichage de mailys1
		System.out.println(mailys1.getNom() + " " + mailys1.getPrenom() + " également appelé " + mailys1.getPseudo());
		// -> affichage mailys2
		System.out.println(mailys2.getNom() + " " + mailys2.getPrenom() + " également appelé " + mailys2.getPseudo());
		
		// 4 
		Joueur jeanpierre = new Joueur("Jean-Pierre", "Virot");
		jeanpierre.setPseudo("JP");
		// jeanpierre -> référence vers instance de jean pierre
		System.out.println(jeanpierre.getNom() + " " + jeanpierre.getPrenom() + " également appelé " + jeanpierre.getPseudo());
		
		List<Joueur> listeJoueurs = new ArrayList<Joueur>();
		listeJoueurs.add(fred);
		listeJoueurs.add(mailys1);
		listeJoueurs.add(jeanpierre);
		
		// -> listeJoueur[0] -> référence vers instance de fred
		// -> listeJoueur[1] -> référence vers instance de mailys
		// -> listeJoueur[2] -> référence vers instance de jean pierre
		
		// Donc, ces deux accès sont équalivents listeJoueur[0]  / fred
		
		fred.getPrenom();
		String prenomFred2 = listeJoueurs.get(0).getPrenom();

	}

}
