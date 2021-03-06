package packXparty.jeux;

import packXparty.Launcher;

/**
 * @author
 *
 * 		Classe POJO pour la cr�ation de jeux Fausse Anagramme.<BR/>
 *         Contient les variables d'instance : <BR/>
 *         - motFausseAnagramme <BR/>
 *         - motProposeParJoueur <BR/>
 */
public class JeuFausseAnagramme implements Jeux {

	private String motFausseAnagramme = ""; // Mot de d�part saisi pour jouer �
											// l'Anagramme (Ex : mot)
	private String motProposeParJoueur = ""; // Mot propos� par le joueur (Ex :
												// tom)

	/**
	 * Constructeur par d�faut
	 */
	public JeuFausseAnagramme() {
		super();
	}

	/*
	 * @Override /* g�n�re une erreur depuis la conversion du projet en Maven si
	 * cette annotation n'est pas commentaire !
	 */
	public int jouer(int compteurPoints) {
		return Launcher.comparerMot(this, compteurPoints);
	}

	public String getMotFausseAnagramme() {
		return motFausseAnagramme;
	}

	public void setMotFausseAnagramme(String motFausseAnagramme) {
		this.motFausseAnagramme = motFausseAnagramme;
	}

	public String getMotProposeParJoueur() {
		return motProposeParJoueur;
	}

	public void setMotProposeParJoueur(String motProposeParJoueur) {
		this.motProposeParJoueur = motProposeParJoueur;
	}
}
