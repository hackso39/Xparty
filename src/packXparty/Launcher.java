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
	 * - Question / Réponse ==> (exercice1)
	 * - Question / réponse à propos d'une image ==> (exercice4)
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
	 * Proposer les jeux à l'utilisateur dans un ordre aléatoire
	 */
	
	private static void exercice5() {
		
		
		List<Jeux> listJeux = new ArrayList<Jeux>();
		
		// On créé NB_QUESTIONS jeu question classique.
		for (int i = 0 ; i < NB_QUESTIONS ; i++) {
			listJeux.add(creerJeuQuestion());
		}
		
		// On créé NB_QUESTIONS jeu question image.
		for (int i = 0 ; i < NB_QUESTIONS ; i++) {
			listJeux.add(creerJeuQuestionImage());
		}
		
		// Mélange la liste des jeux aléatoirement.
		Collections.shuffle(listJeux);
		
		// Jouer avec tous les jeux de la liste mélangée.
		// TODO : à terminer car non fonctionnel pour l'instant !
		// la liste de jeux est maintenant chargée, elle est mélangée, donc on va parcourir la liste
		// afin d'exécuter chaque jeux.
		int compteurPoints = 0;
		for (int i = 0 ; i < listJeux.size() ; i++) {
			listJeux.get(i);
		}
		
		System.out.println("Nombre de points : " + compteurPoints);
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * Saisir la question, saisir la réponse, le chemin de l'image. 
	 * Lancer le jeux avec affichage de l'image, puis en console, la 
	 * question et saisir la réponse
	 * Cette opérations sera répétée autant de fois qu'indiqué dans 
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
	 * Cette méthode n'est plus utilisée
	 * 
	 * Saisir la question, saisir la réponse, le chemin de l'image, lancer le
	 * jeux avec affichage de l'image, puis en console, la question et saisir la
	 * réponse
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
	 * Cette méthode est utilisée
	 * 
	 * @return JeuQuestionImage : retourne la référence de l'objet 
	 * contenant le chemin et le nom de l'image 
	 */
	private static JeuQuestionImage creerJeuQuestionImage() {

		JeuQuestionImage jeuQuestionImage = new JeuQuestionImage();
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez saisir une question : ");
		jeuQuestionImage.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la réponse à la question :");
		jeuQuestionImage.setReponse(sc.nextLine());
		
		System.out.println("Veuillez saisir le chemin de l'image à afficher :");
		jeuQuestionImage.setCheminImage(sc.nextLine());
		
		System.out.println("Veuillez saisir le nom de l'image :");
		jeuQuestionImage.setNomImage(sc.nextLine());

		return jeuQuestionImage;
	}

	/**
	 * Cette méthode n'est plus utilisée
	 * 
	 * afficher une image dans une fenêtre, poser une question en console,
	 * lorsque l'utilisateur répond à la question, passer à l'image suivante et
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

		/*------------ 2ème image / question ---------------------*/

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
	 * Cette méthode n'est pas utilisée
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
	 * Cette méthode est utilisée
	 * 
	 * Cette méthode permet d'afficher une image en fonction du chemin qu'on lui indique
	 * Le nom de la fenêtre est également indiqué 
	 *
	 * @param cheminImage : chemin de l'image afin de la charger
	 * @param nomImage : nom donné à la fenêtre
	 * @return JFrame : objet contenant la référence de la fenêtre retournée
	 */
	private static JFrame afficherImage(String cheminImage, String nomImage) {
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
	 * @param fen : objet contenant la référence de la fenêtre à fermer
	 */
	private static void fermerFenetre(JFrame fen) {
		// Ferme la fenêtre
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));
	}

	
	/**
	 * Cette méthode est utilisée
	 * 
	 * exercice1 : saisir des questions avec les réponses que le joueur devra trouver, cette
	 * opération se répétera autant de fois que l'indique la variable : NB_QUESTIONS.
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
	 * Cette méthode est utilisée
	 * 
	 * Cette méthode permet de renseigner les informations en rapport 
	 * avec la question à poser au joueur pendant la partie
	 * @return QuestionsReponses : objet contenant le texte de la question, ainsi que la réponse à la question
	 */
	private static JeuQuestion creerJeuQuestion() {

		Scanner sc = new Scanner(System.in);

		JeuQuestion qr = new JeuQuestion();

		System.out.println("Veuillez saisir une question : ");
		qr.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la réponse à la question :");
		qr.setReponse(sc.nextLine());

		return qr;
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * @param qr : cet objet contient la question et la réponse à poser au joueur
	 * @param compteurPoints : contient le nombre de points en cours dans la partie
	 * @return int : retourne le nombre de points acquis pour la question réponse
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
