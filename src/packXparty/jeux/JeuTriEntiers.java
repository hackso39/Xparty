package packXparty.jeux;

import java.util.ArrayList;
import java.util.List;

import packXparty.Launcher;

/**
 * Principe du jeu Tri Entiers :
 * 
 * Demander à l'utilisateur de saisir le nombre d'entiers à trier par ordre croissant
 * puis l'utilisateur saisit les nombres entiers
 * Les nombres mélangés, puis le joueur doit les trier dans l'ordre croissant 
 */

public class JeuTriEntiers implements Jeux{

	List<Integer> listEntiers = new ArrayList<Integer>();
	
	
	public JeuTriEntiers() {
		super();
	}


	public int jouer(int compteurPoints){
		return Launcher.comparerEntiersTries(this, compteurPoints);
	}
	
	public void addEntierDansListe(Integer entier) {
		this.listEntiers.add(entier);
	}
	
	public List<Integer> getListEntiers() {
		return listEntiers;
	}


	public void setListEntiers(List<Integer> listEntiers) {
		this.listEntiers = listEntiers;
	}
}
