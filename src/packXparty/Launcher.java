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

public class Launcher {

	// Nombre de questions � poser par type de jeux
	public static final int NB_REPETITIONS = 1;
	
	/**
	 * Lanceur des jeux :
	 * - Question / R�ponse ==> (exercice1)
	 * - Question / r�ponse � propos d'une image ==> (exercice4)
	 * - Question / R�ponse et Question / R�ponse sur Image (Exercice5)
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Images pour le jeu Question/R�ponse sur Image
		// C:\Workspace\Xparty\images\ironCat.jpg
		// C:\Workspace\Xparty\images\marioCat.jpg
		// C:\Workspace\Xparty\images\Professortocat_v2.png

		// exercice1();
		// exercice4();
		exercice5();
		
		System.exit(0);  // �vite le plantage en console avec l'erreur : AGENT_ERROR_NO_JNI_ENV(183) 
		// voir : http://stackoverflow.com/questions/2225737/error-jdwp-unable-to-get-jni-1-2-environment 
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Question / R�ponse, et Question / R�ponse sur Image dans un ordre al�atoire
	 */
	
	private static void exercice5() {
		
		// Cr�ation de la liste de jeux contenant soit des questions/r�ponses, soit des questions/r�ponses sur image
		List<Jeux> listJeux = new ArrayList<Jeux>();
		
//		// On cr�� NB_REPETITIONS : jeu question/r�ponse.
//		for (int i = 0 ; i < NB_REPETITIONS ; i++) {
//			listJeux.add(creerJeuQuestion());
//		}
//		
//		// On cr�� NB_REPETITIONS : jeu question/r�ponse sur image.
//		for (int i = 0 ; i < NB_REPETITIONS ; i++) {
//			listJeux.add(creerJeuQuestionImage());
//		}
//		
//		// On cr�� NB_REPETITIONS : jeu fausse Anagramme.
//		for (int i = 0 ; i < NB_REPETITIONS ; i++) {
//			listJeux.add(creerJeuFausseAnagramme());
//		}
		
		// On cr�� autant de jeu tri entiers que de lignes pr�sentent dans le fichier.
		
//		List<JeuTriEntiers> listeJeuxTriEntiersFichier = creerJeuTriEntiersDepuisFichier("C:\\Workspace\\Xparty\\textFile\\data_triEntiers.txt");
//		listJeux.addAll(listeJeuxTriEntiersFichier);
		listJeux.addAll(creerJeuTriEntiersDepuisFichier("C:\\Workspace\\Xparty\\textFile\\data_triEntiers.txt"));
		
		// On cr�� autant de jeu fausse anagramme que de lignes pr�sentent dans le fichier.
		listJeux.addAll(creerJeuFausseAnagrammeDepuisFichier("C:\\Workspace\\Xparty\\textFile\\data_anagramme.txt"));
		
//		// On cr�� NB_REPETITIONS : jeu fausse Anagramme.
//		for (int i = 0 ; i < NB_REPETITIONS ; i++) {
//			listJeux.add(creerJeuTriEntiers());
//		}
		
		Collections.shuffle(listJeux);	// M�lange al�atoire de la liste des jeux.
		
		int compteurPoints = 0;
		
		/**
		 * La liste des questions contient des questions sans images et avec images
		 * On parcours toute la liste des questions 
		 * Si une questionImage se pr�sente, il faut afficher une fen�tre pour l'image.
		 */
		for (int i = 0 ; i < listJeux.size() ; i++) {
			
			compteurPoints = listJeux.get(i).jouer(compteurPoints);
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
		
		for (int i = 0; i < NB_REPETITIONS; i++) {
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


	public static List<JeuFausseAnagramme> creerJeuFausseAnagrammeDepuisFichier(String cheminFichier) {
		
		List<JeuFausseAnagramme> listeJeux = new ArrayList<JeuFausseAnagramme>();
		
		try
		{
		    File f = new File (cheminFichier);
		    FileReader fr = new FileReader (f);
		    BufferedReader br = new BufferedReader (fr);
		 
		    try
		    {
		        String line = br.readLine();
		        
		        // On boucle sur chaque ligne du fichier.
		        while (line != null)
		        {
	            
		            JeuFausseAnagramme jfa = creerFausseAnagrammeDepuisLigne(line);
		            listeJeux.add(jfa);
		            
		            line = br.readLine();
		        }
		        
		        br.close();
		        fr.close();
		    }
		    catch (IOException exception)
		    {
		        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		    }
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("Le fichier n'a pas �t� trouv�");
		}
		
		return listeJeux;
	}
	
	/**
	 * M�thode charg�e de cr�er un JeuFausseAnagramme depuis une ligne d'un fichier. <br>
	 * Il y a un mot par ligne.
	 * 
	 * @param line Ligne � traiter.
	 * @return Jeu cr�� � partir de la ligne.
	 */
	public static JeuFausseAnagramme creerFausseAnagrammeDepuisLigne(String line) {
		
		JeuFausseAnagramme jfa = new JeuFausseAnagramme();
    	System.out.println("La ligne contient : " + line);
        //String str[] = line.split(";");  // ligne � supprimer normalement
//        String str = line;
//    	
//        // On traite la ligne.
//        for (int i = 0 ; i < str.length ; i++) {
//        	//jte.addEntierDansListe(Integer.valueOf(str[i]));
//        	jfa.addEntierDansListe(Integer.valueOf(str[i]));
//        }

    	jfa.setMotFausseAnagramme(line);
    	
        return jfa;
	}	
	
	
	/**
	 * Cette m�thode cr��e un objet JeuTriEntiers depuis la console en demande � l'utilisateur les nombres qu'il veut utiliser.
	 * 
	 * @return Le jeu cr�� par l'utilisateur.
	 */
	public static JeuTriEntiers creerJeuTriEntiers() {
		
		Integer nb_entiers = 0;
		JeuTriEntiers jte = new JeuTriEntiers();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez entrer le nombre d'entiers que vous allez saisir : ");
		nb_entiers = sc.nextInt();
		
		for(int i = 0 ; i < nb_entiers ; i++) {
			int a = i + 1;
			System.out.println("Veuillez saisir le nombre entier n� " + a + "/"+ nb_entiers + " : ");
			jte.addEntierDansListe(sc.nextInt());
		}
		
		return jte;
	}
	
	/**
	 * Cette m�thode cr��e une liste de JeuTriEntiers depuis un fichier dont le chemin est pass� en param�tre. <br>
	 * Chaque ligne du fichier correspond � une ligne de jeu.
	 * @param cheminFichier Chemin du fichier.
	 * @return Jeu cr�� � partir du fichier.
	 */
	public static List<JeuTriEntiers> creerJeuTriEntiersDepuisFichier(String cheminFichier) {
		
		
		List<JeuTriEntiers> listeJeux = new ArrayList<JeuTriEntiers>();
				
		try
		{
		    File f = new File (cheminFichier);
		    FileReader fr = new FileReader (f);
		    BufferedReader br = new BufferedReader (fr);
		 
		    try
		    {
		        String line = br.readLine();
		        
		        // On boucle sur chaque ligne du fichier.
		        while (line != null)
		        {
	            
		            JeuTriEntiers jte = creerJeuTriEntierDepuisLigne(line);
		            listeJeux.add(jte);
		            
		            line = br.readLine();
		        }
		        
		        br.close();
		        fr.close();
		    }
		    catch (IOException exception)
		    {
		        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		    }
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("Le fichier n'a pas �t� trouv�");
		}
		
		return listeJeux;
		
	}
	
	
	/**
	 * M�thode charg�e de cr�er un JeuTriEntiers depuis une ligne d'un fichier. <br>
	 * La ligne correspond � une liste de nombres s�par�s par des ';'.
	 * 
	 * @param line Ligne � traiter.
	 * @return Jeu cr�� � partir de la ligne.d
	 */
	public static JeuTriEntiers creerJeuTriEntierDepuisLigne(String line) {
		
		JeuTriEntiers jte = new JeuTriEntiers();
    	System.out.println("La ligne contient : " + line);
        String str[] = line.split(";");
        
        // On traite la ligne.
        for (int i = 0 ; i < str.length ; i++) {
        	jte.addEntierDansListe(Integer.valueOf(str[i]));
        }
        
        return jte;
	}

	public static int comparerEntiersTries(JeuTriEntiers jte, int compteurPoints) {
		
//		Collections.sort(jte);
		List<Integer> listTrie = new ArrayList<Integer>();
		
		//JeuTriEntiers jteNonTrie = jte;
		
		Collections.shuffle(jte.getListEntiers());
		
		System.out.println("Voici la liste des nombres entiers � saisir par ordre croissant : ");
		for(int i = 0 ; i < jte.getListEntiers().size() ; i++) {
			System.out.print(" " + jte.getListEntiers().get(i));
		}
		System.out.println("");
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0 ; i < jte.getListEntiers().size() ; i++) {
			int a = i + 1;
			System.out.println("Veuillez saisir le nombre n� " + a + " / " + jte.getListEntiers().size() + " : ");
			listTrie.add(sc.nextInt());
		}
		
		Collections.sort(jte.getListEntiers());

		int i = 0;
		while(i < jte.getListEntiers().size() && jte.getListEntiers().get(i).equals(listTrie.get(i))) {
			if(i == jte.getListEntiers().size()-1) {
				compteurPoints++;
			}
			i++;
		}
		
		return compteurPoints;
	}

	/**
	 * M�thode qui prend en param�tre une ligne du fichier et qui retourne 1 jeu
	 */
	
	/**
	 * M�thode qui prend en param�tre un fichier texte et qui retourne une liste de jeux
	 */
	
	
	/**
	 * Cette m�thode est utilis�e
	 * 
	 * @return jfa : retourne une ref�rence � l'objet de type JeuFausseAnagramme
	 */
	private static JeuFausseAnagramme creerJeuFausseAnagramme() {
		
		JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez saisir le mot qui va �tre pr�sent� au joueur pour l'anagramme :");
		jfa.setMotFausseAnagramme(sc.nextLine());
		
		return jfa;
	}	
	
	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Algo :
	 * ------
	 * comparer la taille des deux mots qui doit �tre identique, les deux mots doivent �tre diff�rents
	 * 		v�rifier que toutes les lettres sont identiques entre les deux mots (2 boucles imbriqu�es)
	 * 			A chaque fois qu'une lettre est trouv�e dans le mot de l'Anagramme, incr�mentation du cptLettresTrouvees
	 *		v�rifier que le nombre de lettres indentiques trouv�es correspond � la longueur du mot propos� dans 
	 *      l'anagramme, dans ce cas : +1 pour le compteur de points.
	 * 
	 * @param JeuFausseAnagramme jfa : object contenant le mot de d�part saisi pour jouer � l'Anagramme et le mot 
	 * 								   propos� par le joueur
	 * @param int compteurPoints : entier contenant le nombre de points du joueur
	 * @return compteurPoints : compteur de points
	 */
	public static int comparerMot(JeuFausseAnagramme jfa, int compteurPoints) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une nouvelle fausse Anagramme � partir de celui propos� : "+ jfa.getMotFausseAnagramme() + " : ");
		jfa.setMotProposeParJoueur(sc.nextLine());

		if(jfa.getMotFausseAnagramme().length() == jfa.getMotProposeParJoueur().length() && !jfa.getMotFausseAnagramme().equals(jfa.getMotProposeParJoueur())) {
			
			int cptLettresTrouvees = 0;
			for(int i = 0 ; i < jfa.getMotFausseAnagramme().length() ; i++) {
				for (int j = 0 ; j < jfa.getMotProposeParJoueur().length() ; j++) {
					if(jfa.getMotFausseAnagramme().charAt(i) == jfa.getMotProposeParJoueur().charAt(j)){
						cptLettresTrouvees++;
					}
				}
			}
			if(cptLettresTrouvees == jfa.getMotFausseAnagramme().length()) {
				compteurPoints++;
			}
		}
		return compteurPoints;
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
	 * Cette m�thode est utilis�e
	 * 
	 * Cette m�thode permet d'afficher une image en fonction du chemin qu'on lui indique
	 * Le nom de la fen�tre est �galement indiqu� 
	 *
	 * @param cheminImage : chemin de l'image afin de la charger
	 * @param nomImage : nom donn� � la fen�tre
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

		for (int i = 0; i < NB_REPETITIONS; i++) {
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