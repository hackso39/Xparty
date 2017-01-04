package sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class Poc1 {

	/**
	 * Point d'entr�e de mon application POC1.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 1
		new Joueur("Christophe", "Virot");

		// 2
		String prenomFred1 = "Fr�d�ric"; // = new String("Frederic")
		Joueur fred = new Joueur(prenomFred1, "Spade");
		fred.setPseudo("HackSo");
		System.out.println(fred.getNom() + " " + fred.getPrenom() + " �galement appel� " + fred.getPseudo());
		// fred -> r�f�rence vers instance de fred
		
		// 3 
		Joueur mailys1 = new Joueur("Ma�lys", "Ahmed"); 
		mailys1.setPseudo("Petit chat");
		// mailys1 -> r�f�rence vers instance de mailys
		
		Joueur mailys2 = mailys1;
		mailys2.setPseudo("Petite chatte");
		// mailys2-> r�f�rence vers instance de mailys
		
		// -> affichage de mailys1
		System.out.println(mailys1.getNom() + " " + mailys1.getPrenom() + " �galement appel� " + mailys1.getPseudo());
		// -> affichage mailys2
		System.out.println(mailys2.getNom() + " " + mailys2.getPrenom() + " �galement appel� " + mailys2.getPseudo());
		
		// 4 
		Joueur jeanpierre = new Joueur("Jean-Pierre", "Virot");
		jeanpierre.setPseudo("JP");
		// jeanpierre -> r�f�rence vers instance de jean pierre
		System.out.println(jeanpierre.getNom() + " " + jeanpierre.getPrenom() + " �galement appel� " + jeanpierre.getPseudo());
		
		List<Joueur> listeJoueurs = new ArrayList<Joueur>();
		listeJoueurs.add(fred);
		listeJoueurs.add(mailys1);
		listeJoueurs.add(jeanpierre);
		
		// -> listeJoueur[0] -> r�f�rence vers instance de fred
		// -> listeJoueur[1] -> r�f�rence vers instance de mailys
		// -> listeJoueur[2] -> r�f�rence vers instance de jean pierre
		
		// Donc, ces deux acc�s sont �qualivents listeJoueur[0]  / fred
		
		fred.getPrenom();
		String prenomFred2 = listeJoueurs.get(0).getPrenom();

	}

}
