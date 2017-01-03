package packXparty;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import core.components.ImagePanel;
import core.exception.CreationJeuxDepuisFichierException;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.runtime.JSONFunctions;
import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuQuestionImageReponse;
import packXparty.jeux.JeuQuestionResponse;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

/**
 * 
 * @author hackso39
 * 
 * Classe permettant de lancer l'application.
 *
 */
public class Launcher {
		
	// ***
	// création listes entiers -> le programme ne plante pas si le le fichier		FAIT
	// contient une lettre
	// création de question -> le programme ne plante pas si le fichier ne
	// possède pas de ";" (pas de réponse à la question)							FAIT
	// => faire une gestion des exceptions sur tous les cas possibles
	// d'erreurs dans la formation du fichier
	// Transformer le fichier : data_jeux.txt en fichier au format : JSon 
	// ***

	// Nombre de questions à poser par type de jeux
	public static final int NB_REPETITIONS = 1;

	/**
	 * Lanceur des jeux : - Question / Réponse ==> (exercice1) - Question /
	 * réponse à propos d'une image ==> (exercice4) - Question / Réponse et
	 * Question / Réponse sur Image (Exercice5)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		//JSONObject jO = new JSONObject();
		
		// Images pour le jeu Question/Réponse sur Image
		// C:\Workspace\Xparty\images\ironCat.jpg
		// C:\Workspace\Xparty\images\marioCat.jpg
		// C:\Workspace\Xparty\images\Professortocat_v2.png

		// exercice1();
		// exercice4();
		//exercice5();
		exercice6();

		System.exit(0); // évite le plantage en console avec l'erreur :
						// AGENT_ERROR_NO_JNI_ENV(183)
		// voir :
		// http://stackoverflow.com/questions/2225737/error-jdwp-unable-to-get-jni-1-2-environment
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * Question / Réponse, et Question / Réponse sur Image dans un ordre
	 * aléatoire
	 * 
	 * La création des jeux se fait à partir d'un fichier au format JSON
	 */
	private static void exercice6() {


		// Création de la liste de jeux contenant soit des questions/réponses,
		// soit des questions/réponses sur image
		List<Jeux> listJeux = new ArrayList<Jeux>();

		try {
			
			listJeux.addAll(CreationJeux.creerJeuxDepuisFichierJSON("D:\\Workspace\\Xparty\\textFile\\data_jeux.json"));
			
			Collections.shuffle(listJeux); // Mélange aléatoire de la liste des jeux.

			int compteurPoints = 0;

			/**
			 * La liste des questions contient des questions sans images et avec
			 * images. On parcours toute la liste des questions. Si une questionImage
			 * se présente, il faut afficher une fenêtre pour l'image.
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

		// // On créé NB_REPETITIONS : jeu question/réponse.
		// for (int i = 0 ; i < NB_REPETITIONS ; i++) {
		// listJeux.add(creerJeuQuestion());
		// }
		//
		// // On créé NB_REPETITIONS : jeu question/réponse sur image.
		// for (int i = 0 ; i < NB_REPETITIONS ; i++) {
		// listJeux.add(creerJeuQuestionImage());
		// }
		//
		// // On créé NB_REPETITIONS : jeu fausse Anagramme.
		// for (int i = 0 ; i < NB_REPETITIONS ; i++) {
		// listJeux.add(creerJeuFausseAnagramme());
		// }

		// On créé autant de jeu tri entiers que de lignes présentent dans le
		// fichier.

		// List<JeuTriEntiers> listeJeuxTriEntiersFichier =
		// creerJeuTriEntiersDepuisFichier("C:\\Workspace\\Xparty\\textFile\\data_triEntiers.txt");
		// listJeux.addAll(listeJeuxTriEntiersFichier);
//		listJeux.addAll(CreationJeux.creerJeuTriEntiersDepuisFichier("C:\\Workspace\\Xparty\\textFile\\data_triEntiers.txt"));

		// On créé autant de jeu fausse anagramme que de lignes présentent dans
		// le fichier.
//		listJeux.addAll(CreationJeux
//				.creerJeuFausseAnagrammeDepuisFichier("C:\\Workspace\\Xparty\\textFile\\data_anagramme.txt"));

		// On créé autant de jeu Question/réponse que de ligne présente dans le
		// fichier. 1 lignee = question;réponse
//		listJeux.addAll(CreationJeux.creerJeuQuestionReponseDepuisFichier("C:\\Workspace\\Xparty\\textFile\\data_question.txt"));

		// // On créé NB_REPETITIONS : jeu fausse Anagramme.
		// for (int i = 0 ; i < NB_REPETITIONS ; i++) {
		// listJeux.add(creerJeuTriEntiers());
		// }

		try {

			listJeux.addAll(CreationJeux.creerJeuxDepuisFichier("D:\\Workspace\\Xparty\\textFile\\data_jeux.txt"));
			
			Collections.shuffle(listJeux); // Mélange aléatoire de la liste des jeux.

			int compteurPoints = 0;

			/**
			 * La liste des questions contient des questions sans images et avec
			 * images On parcours toute la liste des questions Si une questionImage
			 * se présente, il faut afficher une fenêtre pour l'image.
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

//	/**
//	 * Cette méthode est utilisée
//	 * 
//	 * Saisir la question, saisir la réponse, le chemin de l'image. Lancer le
//	 * jeux avec affichage de l'image, puis en console, la question et saisir la
//	 * réponse Cette opérations sera répétée autant de fois qu'indiqué dans la
//	 * variable NB_QUESTIONS.
//	 */
//	private static void exercice4() {
//
//		List<JeuQuestionResponse> listJQ = new ArrayList<JeuQuestionResponse>();
//		List<JeuQuestionImageReponse> listJQI = new ArrayList<JeuQuestionImageReponse>();
//
//		for (int i = 0; i < NB_REPETITIONS; i++) {
//			listJQ.add(CreationJeux.creerJeuQuestion());
//			listJQI.add(CreationJeux.creerJeuQuestionImage());
//		}
//
//		// JFrame fen = afficherImage(gi.getCheminImage(), gi.getNomImage());
//
//		int compteurPoints = 0;
//		for (int i = 0; i < listJQ.size(); i++) {
//			JFrame fen = afficherImage(listJQI.get(i).getCheminImage(), listJQI.get(i).getNomImage());
//			compteurPoints = afficherQuestion(listJQ.get(i), compteurPoints);
//			fermerFenetre(fen);
//		}
//		System.out.println("Nombre de points : " + compteurPoints);
//	}

	public static int comparerEntiersTries(JeuTriEntiers jte, int compteurPoints) {

		// Collections.sort(jte);
		List<Integer> listTrie = new ArrayList<Integer>();

		// JeuTriEntiers jteNonTrie = jte;

		Collections.shuffle(jte.getListEntiers());

		System.out.println("Voici la liste des nombres entiers à saisir par ordre croissant : ");
		for (int i = 0; i < jte.getListEntiers().size(); i++) {
			System.out.print(" " + jte.getListEntiers().get(i));
		}
		System.out.println("");

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < jte.getListEntiers().size(); i++) {
			int a = i + 1;
			System.out.println("Veuillez saisir le nombre n° " + a + " / " + jte.getListEntiers().size() + " : ");
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

//		sc.close();	// Ne pas mettre cette ligne, sinon : NoSuchElementException
		return compteurPoints;
	}

	/**
	 * Méthode qui prend en paramètre une ligne du fichier et qui retourne 1 jeu
	 */

	/**
	 * Méthode qui prend en paramètre un fichier texte et qui retourne une liste
	 * de jeux
	 */

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
		
//		sc.close();	// Ne pas mettre cette ligne, sinon : NoSuchElementException
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
	private static void fermerFenetre(JFrame fen) {
		// Ferme la fenêtre
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));
	}

//	/**
//	 * Cette méthode est utilisée
//	 * 
//	 * exercice1 : saisir des questions avec les réponses que le joueur devra
//	 * trouver, cette opération se répétera autant de fois que l'indique la
//	 * variable : NB_QUESTIONS.
//	 */
//	private static void exercice1() {
//		int compteurPoints = 0;
//
//		JeuQuestionResponse qr = null;
//
//		List<JeuQuestionResponse> listJQ = new ArrayList<JeuQuestionResponse>();
//
//		for (int i = 0; i < NB_REPETITIONS; i++) {
//			qr = CreationJeux.creerJeuQuestion();
//			listJQ.add(qr);
//		}
//
//		for (int i = 0; i < listJQ.size(); i++) {
//			compteurPoints = afficherQuestion(listJQ.get(i), compteurPoints);
//		}
//
//		System.out.println("Nombre de points : " + compteurPoints);
//	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * @param qr : cet objet contient la question et la réponse à poser au joueur
	 * @param compteurPoints : contient le nombre de points en cours dans la partie
	 * @return int : retourne le nombre de points acquis pour la question réponse
	 */
	public static int afficherQuestion(JeuQuestionResponse qr, int compteurPoints) {

		Scanner sc = new Scanner(System.in);

		System.out.println(qr.getQuestion());
		String repSaisie = sc.nextLine();
		if (qr.getReponse().equals(repSaisie)) {
			compteurPoints++;
		}
		
//		sc.close();	// Ne pas mettre cette ligne, sinon : NoSuchElementException
		return compteurPoints;
	}

}