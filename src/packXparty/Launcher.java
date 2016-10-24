package packXparty;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.javafx.iio.ImageFrame;

import core.components.ImagePanel;

public class Launcher {

	public static final int NB_QUESTIONS = 2;

	/**
	 * Lanceur des jeux :
	 * - Question / R�ponse ==> (exercice1)
	 * - Question / r�ponse � propos d'une image ==> (exercice4)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// C:\Workspace\Xparty\images\ironCat.jpg
		// C:\Workspace\Xparty\images\marioCat.jpg
		// C:\Workspace\Xparty\images\Professortocat_v2.png

		// exercice1();
		// exercice2();
		// exercice3();
		//exercice4();
		exercice5();
	}

	/**
	 * Constituer une liste de jeux contenant 2 JeuQuestion et 2 JeuQuestionImage
	 * 
	 * Proposer les jeux � l'utilisateur dans un ordre al�atoire
	 */
	
	private static void exercice5() {
		
		
		List<Jeux> listJeux = new ArrayList<Jeux>();
		
		// On cr�� NB_QUESTIONS jeu question classique.
		for (int i = 0 ; i < NB_QUESTIONS ; i++) {
			listJeux.add(creerJeuQuestion());
		}
		
		// On cr�� NB_QUESTIONS jeu question image.
		for (int i = 0 ; i < NB_QUESTIONS ; i++) {
			listJeux.add(creerJeuQuestionImage());
		}
		
		// M�lange la liste des jeux al�atoirement.
		Collections.shuffle(listJeux);
		
		// Jouer avec tous les jeux de la liste m�lang�e.
		// TODO : � terminer car non fonctionnel pour l'instant !
		// la liste de jeux est maintenant charg�e, elle est m�lang�e, donc on va parcourir la liste
		// afin d'ex�cuter chaque jeux.
		int compteurPoints = 0;
		for (int i = 0 ; i < listJeux.size() ; i++) {
			listJeux.get(i);
		}
		
		System.out.println("Nombre de points : " + compteurPoints);
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Saisir la question, saisir la r�ponse, le chemin de l'image. 
	 * Lancer le jeux avec affichage de l'image, puis en console, la 
	 * question et saisir la r�ponse
	 * Cette op�rations sera r�p�t�e autant de fois qu'indiqu� dans 
	 * la variable NB_QUESTIONS.
	 */
	private static void exercice4() {

		List<JeuQuestion> listJQ = new ArrayList<JeuQuestion>();
		List<JeuQuestionImage> listJQI = new ArrayList<JeuQuestionImage>();
		
		for (int i = 0; i < NB_QUESTIONS; i++) {
			listJQ.add(creerJeuQuestion());
			listJQI.add(creerJeuQuestionImage());
		}
		
		//JFrame fen = afficherImage(gi.getCheminImage(), gi.getNomImage());

		int compteurPoints = 0;
		for (int i = 0; i < listJQ.size(); i++) {
			JFrame fen = afficherImage(listJQI.get(i).getCheminImage(), listJQI.get(i).getNomImage());
			compteurPoints = afficherQuestion(listJQ.get(i), compteurPoints);
			fermerFenetre(fen);
		}
		System.out.println("Nombre de points : " + compteurPoints);
	}

	/**
	 * Cette m�thode n'est plus utilis�e
	 * 
	 * Saisir la question, saisir la r�ponse, le chemin de l'image, lancer le
	 * jeux avec affichage de l'image, puis en console, la question et saisir la
	 * r�ponse
	 */
	private static void exercice3() {

		JeuQuestion qr = new JeuQuestion();
		qr = creerJeuQuestion();

		JeuQuestionImage gi = new JeuQuestionImage();
		gi = creerJeuQuestionImage();

		JFrame fen = afficherImage(gi.getCheminImage(), gi.getNomImage());

		int compteurPoints = 0;
		compteurPoints = afficherQuestion(qr, compteurPoints);
		System.out.println("Nombre de points : " + compteurPoints);
		fermerFenetre(fen);
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * @return JeuQuestionImage : retourne la r�f�rence de l'objet 
	 * contenant le chemin et le nom de l'image 
	 */
	private static JeuQuestionImage creerJeuQuestionImage() {

		JeuQuestionImage jeuQuestionImage = new JeuQuestionImage();
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez saisir une question : ");
		jeuQuestionImage.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la r�ponse � la question :");
		jeuQuestionImage.setReponse(sc.nextLine());
		
		System.out.println("Veuillez saisir le chemin de l'image � afficher :");
		jeuQuestionImage.setCheminImage(sc.nextLine());
		
		System.out.println("Veuillez saisir le nom de l'image :");
		jeuQuestionImage.setNomImage(sc.nextLine());

		return jeuQuestionImage;
	}

	/**
	 * Cette m�thode n'est plus utilis�e
	 * 
	 * afficher une image dans une fen�tre, poser une question en console,
	 * lorsque l'utilisateur r�pond � la question, passer � l'image suivante et
	 * ainsi de suite...
	 */
	private static void exercice2() {

		int compteurPoints = 0;

		JeuQuestion qr = new JeuQuestion();
		// C:\Workspace\Xparty\images\ironCat.jpg
		String cheminImage = "C:\\Workspace\\Xparty\\images\\ironCat.jpg";
		String nomImage = "IronCat";

		JFrame fen = afficherImage(cheminImage, nomImage);

		// questionReponse();
		qr.setQuestion("A quoi ressemble OctoCat ?");
		qr.setReponse("IronCat");
		compteurPoints = afficherQuestion(qr, compteurPoints);
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));

		/*------------ 2�me image / question ---------------------*/

		cheminImage = "C:\\Workspace\\Xparty\\images\\marioCat.jpg";
		nomImage = "MarioCat";

		fen = afficherImage(cheminImage, nomImage);
		// questionReponse();
		qr.setQuestion("A quoi ressemble OctoCat ?");
		qr.setReponse("MarioCat");
		compteurPoints = afficherQuestion(qr, compteurPoints);
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));

		System.out.println("Nombre de points : " + compteurPoints);
	}

	/**
	 * Cette m�thode n'est pas utilis�e
	 */
	private static void questionReponse() {
		int compteurPoints = 0;

		JeuQuestion qr = new JeuQuestion();
		qr.setReponse("IronCat");

		Scanner sc = new Scanner(System.in);
		System.out.println("A quoi ressemble OctoCat ?");
		qr.setQuestion(sc.nextLine());

		if (qr.getQuestion().equals(qr.getReponse())) {
			compteurPoints++;
		}
		System.out.println("Nombre de points : " + compteurPoints);
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Cette m�thode permet d'afficher une image en fonction du chemin qu'on lui indique
	 * Le nom de la fen�tre est �galement indiqu� 
	 *
	 * @param cheminImage : chemin de l'image afin de la charger
	 * @param nomImage : nom donn� � la fen�tre
	 * @return JFrame : objet contenant la r�f�rence de la fen�tre retourn�e
	 */
	private static JFrame afficherImage(String cheminImage, String nomImage) {
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
	 * @param fen : objet contenant la r�f�rence de la fen�tre � fermer
	 */
	private static void fermerFenetre(JFrame fen) {
		// Ferme la fen�tre
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));
	}

	
	/**
	 * Cette m�thode est utilis�e
	 * 
	 * exercice1 : saisir des questions avec les r�ponses que le joueur devra trouver, cette
	 * op�ration se r�p�tera autant de fois que l'indique la variable : NB_QUESTIONS.
	 */
	private static void exercice1() {
		int compteurPoints = 0;

		JeuQuestion qr = null;

		List<JeuQuestion> listJQ = new ArrayList<JeuQuestion>();

		for (int i = 0; i < NB_QUESTIONS; i++) {
			qr = creerJeuQuestion();
			listJQ.add(qr);
		}

		for (int i = 0; i < listJQ.size(); i++) {
			compteurPoints = afficherQuestion(listJQ.get(i), compteurPoints);
		}

		System.out.println("Nombre de points : " + compteurPoints);
	}
	
	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Cette m�thode permet de renseigner les informations en rapport 
	 * avec la question � poser au joueur pendant la partie
	 * @return QuestionsReponses : objet contenant le texte de la question, ainsi que la r�ponse � la question
	 */
	private static JeuQuestion creerJeuQuestion() {

		Scanner sc = new Scanner(System.in);

		JeuQuestion qr = new JeuQuestion();

		System.out.println("Veuillez saisir une question : ");
		qr.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la r�ponse � la question :");
		qr.setReponse(sc.nextLine());

		return qr;
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * @param qr : cet objet contient la question et la r�ponse � poser au joueur
	 * @param compteurPoints : contient le nombre de points en cours dans la partie
	 * @return int : retourne le nombre de points acquis pour la question r�ponse
	 */
	public static int afficherQuestion(JeuQuestion qr, int compteurPoints) {

		Scanner sc = new Scanner(System.in);

		System.out.println(qr.getQuestion());
		String repSaisie = sc.nextLine();
		if (qr.getReponse().equals(repSaisie)) {
			compteurPoints++;
		}
		return compteurPoints;
	}

}
