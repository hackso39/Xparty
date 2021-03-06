package packXparty;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import core.components.ImagePanel;
import core.exception.JeuInvalideException;
import core.exception.XpartyJeuxException;
import core.exception.XpartyJeuxQuestionException;
import core.exception.XpartyJeuxTriEntiersException;
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

	/**
	 * SPEC :
	 * - le programme ne doit jamais planter.
	 * - le programme doit dire � l'utilisateur quelles erreurs sont pr�sentent dans le 
	 * fichier JSON :
	 * 	les erreurs � tester :
	 * 		- jeuQuestionReponse mais sans question : l'attribut question n'est pas pr�sent dans le fichier JSON
	 * 		- jeuQuestionReponse mais sans question : l'attribut r�ponse n'est pas pr�sent dans le fichier JSON
	 * 		- g�rer les erreurs pour tous les attributs manquants du fichier JSON
	 * 		- chemin erronn� pour un jeu Question Image ==> "image non trouv�e"
	 * 		- triEntiers : contient des lettres � la place des chiffres ==> "un caract�re diff�rent d'un nombre est pr�sent dans le jeu Tri Entiers"
	 * 		- fichier JSON introuvable
	 * Si une de ces erreurs arrive, le programme indique � l'utilisateur le probl�me rencontr� 
	 * et s'arr�te et le nombre de points n'appara�t pas dans ce cas ! 
	 * 
	 */
	
	/**
	 * mutualiser le code quand c'est possible (lecture fichier depuis hd ou nURL)
	 */
	
	/**
	 * Mettre en place le design pattern Factory afin de de ne plus avoir � modifier le code
	 * lorsque l'on ajoute un nouveau jeu (voir m�thode : CreationJeux.creerJeuxDepuisFichierJSONparURL
	 * et les if imbriqu�s).
	 */
	
	// Nombre de questions � poser par type de jeux
	public static final int NB_REPETITIONS = 1;

	// Constantes pour les diff�rents types de jeux
	public static final String JEU_TYPE_ANAGRAMME = "anagramme";
	public static final String JEU_TYPE_QUESTION = "question";
	public static final String JEU_TYPE_QUESTION_IMAGE = "questionImage";
	public static final String JEU_TYPE_TRIENTIERS = "triEntiers";

	/**
	 * Lanceur des jeux : - Question / R�ponse ==> (exercice1) - Question /
	 * r�ponse � propos d'une image ==> (exercice4) - Question / R�ponse et
	 * Question / R�ponse sur Image (Exercice5)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Images pour le jeu Question/R�ponse sur Image
		// C:\Workspace\Xparty\images\ironCat.jpg
		// C:\Workspace\Xparty\images\marioCat.jpg
		// C:\Workspace\Xparty\images\Professortocat_v2.png

		// exercice5();
		exercice6();
//		exercice7();
		

		System.exit(0);
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Question / R�ponse, et Question / R�ponse sur Image dans un ordre al�atoire
	 * 
	 * La cr�ation des jeux se fait � partir d'un fichier au format JSON depuis une URL
	 */
	private static void exercice7() {

		List<Jeux> listJeux = new ArrayList<Jeux>();

		try {

//			listJeux.addAll(CreationJeux.creerJeuxDepuisFichierJSONparURL("http://www.christophevirot.com/data_jeux.json"));
//			listJeux.addAll(CreationJeux.creerJeuxDepuisFichierJSONparURL("http://eddy-spade.monsite-orange.fr/file/e5f7c678373425867a5b0482d7d5bb80.txt"));
			listJeux.addAll(CreationJeux.creerJeuxDepuisFichierJSONparURL("http://tests-json.pagesperso-orange.fr/data_jeux.json"));

			Collections.shuffle(listJeux); // M�lange al�atoire de la liste des
											// jeux.

			int compteurPoints = 0;

			/**
			 * La liste des questions contient des questions sans images et avec
			 * images. On parcours toute la liste des questions. Si une
			 * questionImage se pr�sente, il faut afficher une fen�tre pour
			 * l'image.
			 */
			for (int i = 0; i < listJeux.size(); i++) {

				Jeux monjeu = listJeux.get(i);
				compteurPoints = monjeu.jouer(compteurPoints);
			}

			System.out.println("Nombre de points : " + compteurPoints);

		} catch (JeuInvalideException e) {
			System.out.println("Le jeu ne s'est pas lancer car une erreur est pr�sente dans le fichier.");
		} catch (XpartyJeuxTriEntiersException e) {
			System.out.println("Jeu Tri Entiers invalide : " + e.getChaineInvalide());
		} catch (XpartyJeuxException e) {
			System.out.println("Probl�me de construction du fichier de jeux !");
		}
		
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Question / R�ponse, et Question / R�ponse sur Image dans un ordre al�atoire
	 * 
	 * La cr�ation des jeux se fait � partir d'un fichier au format JSON
	 */
	private static void exercice6() {

		List<Jeux> listJeux = new ArrayList<Jeux>();

		try {

			listJeux.addAll(CreationJeux.creerJeuxDepuisFichierJSON("C:\\Workspace\\Xparty\\textFile\\data_jeux.json"));

			Collections.shuffle(listJeux); // M�lange al�atoire de la liste des
											// jeux.

			int compteurPoints = 0;

			/**
			 * La liste des questions contient des questions sans images et avec
			 * images. On parcours toute la liste des questions. Si une
			 * questionImage se pr�sente, il faut afficher une fen�tre pour
			 * l'image.
			 */
			for (int i = 0; i < listJeux.size(); i++) {

				Jeux monjeu = listJeux.get(i);
				compteurPoints = monjeu.jouer(compteurPoints);
			}

			System.out.println("Nombre de points : " + compteurPoints);

		} catch (JeuInvalideException e) {
			System.out.println("Le jeu ne s'est pas lancer car une erreur est pr�sente dans le fichier.");
		} catch (XpartyJeuxQuestionException e) { 
			System.out.println("Jeu Question invalide : " + e.getMessage());
		} catch (XpartyJeuxTriEntiersException e) {
			System.out.println("Jeu Tri Entiers invalide : " + e.getChaineInvalide());
		} catch (XpartyJeuxException e) {
			System.out.println("Probl�me de construction du fichier de jeux !");
		}
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Question / R�ponse, et Question / R�ponse sur Image dans un ordre
	 * al�atoire
	 */
	private static void exercice5() {

		// Cr�ation de la liste de jeux contenant soit des questions/r�ponses,
		// soit des questions/r�ponses sur image
		List<Jeux> listJeux = new ArrayList<Jeux>();

		try {
			listJeux.addAll(CreationJeux.creerJeuxDepuisFichier("D:\\Workspace\\Xparty\\textFile\\data_jeux.txt"));

			Collections.shuffle(listJeux); // M�lange al�atoire de la liste des
											// jeux.

			int compteurPoints = 0;

			/**
			 * La liste des questions contient des questions sans images et avec
			 * images On parcours toute la liste des questions Si une
			 * questionImage se pr�sente, il faut afficher une fen�tre pour
			 * l'image.
			 */
			for (int i = 0; i < listJeux.size(); i++) {

				Jeux monjeu = listJeux.get(i);
				compteurPoints = monjeu.jouer(compteurPoints);
			}

			System.out.println("Nombre de points : " + compteurPoints);

		} catch (JeuInvalideException e) {
			System.out.println("Le jeu ne s'est pas lancer car une erreur est pr�sente dans le fichier.");
		}
	}

	public static int comparerEntiersTries(JeuTriEntiers jte, int compteurPoints) {

		// Collections.sort(jte);
		List<Integer> listTrie = new ArrayList<Integer>();

		// JeuTriEntiers jteNonTrie = jte;

		Collections.shuffle(jte.getListEntiers());

		System.out.println("Voici la liste des nombres entiers � saisir par ordre croissant : ");
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
			System.out.println("Veuillez saisir le nombre n� " + a + " sur " + jte.getListEntiers().size() + " : ");
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
	 * Cette m�thode est utilis�e
	 * 
	 * Algo : ------ comparer la taille des deux mots qui doit �tre identique,
	 * les deux mots doivent �tre diff�rents v�rifier que toutes les lettres
	 * sont identiques entre les deux mots (2 boucles imbriqu�es) A chaque fois
	 * qu'une lettre est trouv�e dans le mot de l'Anagramme, incr�mentation du
	 * cptLettresTrouvees v�rifier que le nombre de lettres indentiques trouv�es
	 * correspond � la longueur du mot propos� dans l'anagramme, dans ce cas :
	 * +1 pour le compteur de points.
	 * 
	 * @param JeuFausseAnagramme
	 *            jfa : object contenant le mot de d�part saisi pour jouer �
	 *            l'Anagramme et le mot propos� par le joueur
	 * @param int
	 *            compteurPoints : entier contenant le nombre de points du
	 *            joueur
	 * @return compteurPoints : compteur de points
	 */
	public static int comparerMot(JeuFausseAnagramme jfa, int compteurPoints) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une nouvelle fausse Anagramme � partir de celui propos� : "
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
	 * Cette m�thode est utilis�e
	 * 
	 * Cette m�thode permet d'afficher une image en fonction du chemin qu'on lui
	 * indique Le nom de la fen�tre est �galement indiqu�
	 *
	 * @param cheminImage
	 *            : chemin de l'image afin de la charger
	 * @param nomImage
	 *            : nom donn� � la fen�tre
	 * @return JFrame : objet contenant la r�f�rence de la fen�tre retourn�e
	 */
	public static JFrame afficherImage(String cheminImage, String nomImage) {
		// Cr�ation de la fen�tre
		JFrame fen = new JFrame(nomImage);
		fen.setSize(640, 480);

		// Ajout de l'image au centre
		ImagePanel imagePanel = new ImagePanel(cheminImage);
		fen.getContentPane().add(imagePanel, BorderLayout.CENTER);

		// Affichager la fen�tre
		fen.setVisible(true);
		return fen;
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * @param fen
	 *            : objet contenant la r�f�rence de la fen�tre � fermer
	 */
	@SuppressWarnings("unused")
	private static void fermerFenetre(JFrame fen) {
		// Ferme la fen�tre
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * @param qr
	 *            : cet objet contient la question et la r�ponse � poser au
	 *            joueur
	 * @param compteurPoints
	 *            : contient le nombre de points en cours dans la partie
	 * @return int : retourne le nombre de points acquis pour la question
	 *         r�ponse
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