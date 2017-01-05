package packXparty;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import core.components.ImagePanel;
import core.exception.CreationJeuxDepuisFichierException;
import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuQuestionResponse;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

/**
 * @author
 * 
 * 		Classe permettant de lancer l'application.
 */
public class Launcher {

	// ***
	// création listes entiers -> le programme ne plante pas si le le fichier
	// FAIT
	// contient une lettre
	// création de question -> le programme ne plante pas si le fichier ne
	// possède pas de ";" (pas de réponse à la question) FAIT
	// => faire une gestion des exceptions sur tous les cas possibles
	// d'erreurs dans la formation du fichier
	// Transformer le fichier : data_jeux.txt en fichier au format : JSon FAIT
	// ***

	// Nombre de questions à poser par type de jeux
	public static final int NB_REPETITIONS = 1;

	// Constantes pour les différents types de jeux
	public static final String JEU_TYPE_ANAGRAMME = "anagramme";
	public static final String JEU_TYPE_QUESTION = "question";
	public static final String JEU_TYPE_QUESTION_IMAGE = "questionImage";
	public static final String JEU_TYPE_TRIENTIERS = "triEntiers";

	/**
	 * Lanceur des jeux : - Question / Réponse ==> (exercice1) - Question /
	 * réponse à propos d'une image ==> (exercice4) - Question / Réponse et
	 * Question / Réponse sur Image (Exercice5)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Images pour le jeu Question/Réponse sur Image
		// C:\Workspace\Xparty\images\ironCat.jpg
		// C:\Workspace\Xparty\images\marioCat.jpg
		// C:\Workspace\Xparty\images\Professortocat_v2.png

		// exercice5();
		exercice6();

		System.exit(0);
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * Question / Réponse, et Question / Réponse sur Image dans un ordre aléatoire
	 * 
	 * La création des jeux se fait à partir d'un fichier au format JSON
	 */
	private static void exercice6() {

		List<Jeux> listJeux = new ArrayList<Jeux>();

		try {

			listJeux.addAll(CreationJeux.creerJeuxDepuisFichierJSON("D:\\Workspace\\Xparty\\textFile\\data_jeux.json"));

			Collections.shuffle(listJeux); // Mélange aléatoire de la liste des
											// jeux.

			int compteurPoints = 0;

			/**
			 * La liste des questions contient des questions sans images et avec
			 * images. On parcours toute la liste des questions. Si une
			 * questionImage se présente, il faut afficher une fenêtre pour
			 * l'image.
			 */
			for (int i = 0; i < listJeux.size(); i++) {

				Jeux monjeu = listJeux.get(i);
				compteurPoints = monjeu.jouer(compteurPoints);
			}

			System.out.println("Nombre de points : " + compteurPoints);

		} catch (CreationJeuxDepuisFichierException e) {
			System.out.println("Le jeu ne s'est pas lancer car une erreur est présente dans le fichier.");
		}
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * Question / Réponse, et Question / Réponse sur Image dans un ordre
	 * aléatoire
	 */
	private static void exercice5() {

		// Création de la liste de jeux contenant soit des questions/réponses,
		// soit des questions/réponses sur image
		List<Jeux> listJeux = new ArrayList<Jeux>();

		try {
			listJeux.addAll(CreationJeux.creerJeuxDepuisFichier("D:\\Workspace\\Xparty\\textFile\\data_jeux.txt"));

			Collections.shuffle(listJeux); // Mélange aléatoire de la liste des
											// jeux.

			int compteurPoints = 0;

			/**
			 * La liste des questions contient des questions sans images et avec
			 * images On parcours toute la liste des questions Si une
			 * questionImage se présente, il faut afficher une fenêtre pour
			 * l'image.
			 */
			for (int i = 0; i < listJeux.size(); i++) {

				Jeux monjeu = listJeux.get(i);
				compteurPoints = monjeu.jouer(compteurPoints);
			}

			System.out.println("Nombre de points : " + compteurPoints);

		} catch (CreationJeuxDepuisFichierException e) {
			System.out.println("Le jeu ne s'est pas lancer car une erreur est présente dans le fichier.");
		}
	}

	public static int comparerEntiersTries(JeuTriEntiers jte, int compteurPoints) {

		// Collections.sort(jte);
		List<Integer> listTrie = new ArrayList<Integer>();

		// JeuTriEntiers jteNonTrie = jte;

		Collections.shuffle(jte.getListEntiers());

		System.out.println("Voici la liste des nombres entiers à saisir par ordre croissant : ");
		for (int i = 0; i < jte.getListEntiers().size(); i++) {
			System.out.print(jte.getListEntiers().get(i));
			
			if(i < jte.getListEntiers().size() - 1) {
				System.out.print(" ");
			}
		}
		System.out.println("");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		for (int i = 0 ; i < jte.getListEntiers().size() ; i++) {
			int a = i + 1;
			System.out.println("Veuillez saisir le nombre n° " + a + " sur " + jte.getListEntiers().size() + " : ");
			listTrie.add(sc.nextInt());
		}

		Collections.sort(jte.getListEntiers());

		int i = 0;
		while (i < jte.getListEntiers().size() && jte.getListEntiers().get(i).equals(listTrie.get(i))) {
			if (i == jte.getListEntiers().size() - 1) {
				compteurPoints++;
			}
			i++;
		}
		return compteurPoints;
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * Algo : ------ comparer la taille des deux mots qui doit être identique,
	 * les deux mots doivent être différents vérifier que toutes les lettres
	 * sont identiques entre les deux mots (2 boucles imbriquées) A chaque fois
	 * qu'une lettre est trouvée dans le mot de l'Anagramme, incrémentation du
	 * cptLettresTrouvees vérifier que le nombre de lettres indentiques trouvées
	 * correspond à la longueur du mot proposé dans l'anagramme, dans ce cas :
	 * +1 pour le compteur de points.
	 * 
	 * @param JeuFausseAnagramme
	 *            jfa : object contenant le mot de départ saisi pour jouer à
	 *            l'Anagramme et le mot proposé par le joueur
	 * @param int
	 *            compteurPoints : entier contenant le nombre de points du
	 *            joueur
	 * @return compteurPoints : compteur de points
	 */
	public static int comparerMot(JeuFausseAnagramme jfa, int compteurPoints) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une nouvelle fausse Anagramme à partir de celui proposé : "
				+ jfa.getMotFausseAnagramme() + " : ");
		jfa.setMotProposeParJoueur(sc.nextLine());

		if (jfa.getMotFausseAnagramme().length() == jfa.getMotProposeParJoueur().length()
				&& !jfa.getMotFausseAnagramme().equals(jfa.getMotProposeParJoueur())) {

			int cptLettresTrouvees = 0;
			for (int i = 0; i < jfa.getMotFausseAnagramme().length(); i++) {
				for (int j = 0; j < jfa.getMotProposeParJoueur().length(); j++) {
					if (jfa.getMotFausseAnagramme().charAt(i) == jfa.getMotProposeParJoueur().charAt(j)) {
						cptLettresTrouvees++;
					}
				}
			}
			if (cptLettresTrouvees == jfa.getMotFausseAnagramme().length()) {
				compteurPoints++;
			}
		}
		return compteurPoints;
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * Cette méthode permet d'afficher une image en fonction du chemin qu'on lui
	 * indique Le nom de la fenêtre est également indiqué
	 *
	 * @param cheminImage
	 *            : chemin de l'image afin de la charger
	 * @param nomImage
	 *            : nom donné à la fenêtre
	 * @return JFrame : objet contenant la référence de la fenêtre retournée
	 */
	public static JFrame afficherImage(String cheminImage, String nomImage) {
		// Création de la fenêtre
		JFrame fen = new JFrame(nomImage);
		fen.setSize(640, 480);

		// Ajout de l'image au centre
		ImagePanel imagePanel = new ImagePanel(cheminImage);
		fen.getContentPane().add(imagePanel, BorderLayout.CENTER);

		// Affichager la fenêtre
		fen.setVisible(true);
		return fen;
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * @param fen
	 *            : objet contenant la référence de la fenêtre à fermer
	 */
	@SuppressWarnings("unused")
	private static void fermerFenetre(JFrame fen) {
		// Ferme la fenêtre
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * @param qr
	 *            : cet objet contient la question et la réponse à poser au
	 *            joueur
	 * @param compteurPoints
	 *            : contient le nombre de points en cours dans la partie
	 * @return int : retourne le nombre de points acquis pour la question
	 *         réponse
	 */
	public static int afficherQuestion(JeuQuestionResponse qr, int compteurPoints) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println(qr.getQuestion());
		String repSaisie = sc.nextLine();
		if (qr.getReponse().equals(repSaisie)) {
			compteurPoints++;
		}
		return compteurPoints;
	}
}