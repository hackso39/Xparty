package packXparty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuQuestionImageReponse;
import packXparty.jeux.JeuQuestionResponse;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

public abstract class CreationJeux {

	// TODO : supporter fichier mal formé avec les exceptions

	/**
	 * Cette méthode permet de créé une liste de jeux après avoir lu un fichier
	 * comportant différents types de jeux 
	 * 
	 * @param cheminFichier chemin du fichier
	 * @return List de Jeux retourne une liste de jeux
	 */
	public static List<Jeux> creerJeuxDepuisFichier(String cheminFichier) {

		List<Jeux> listeJeux = new ArrayList<Jeux>();
		
		final String JEU_TYPE_ANAGRAMME = "anagramme";
		final String JEU_TYPE_QUESTION = "question";
		final String JEU_TYPE_TRIENTIERS = "triEntiers";

		List<String> listeLignes = OutilFichiers.lectureLigne(cheminFichier);

		int i = 0;
		String line;

		// On boucle sur chaque ligne du fichier.
		while (i < listeLignes.size()) {

			System.out.println("La ligne contient : " + listeLignes.get(i));
			line = listeLignes.get(i);
			String str[] = line.split(";");
			
			/*  Arrays.asList(str) <== Transforme un tableau en liste non dynamique,
			 *  la liste est donc figée, il faut alors redéclarer une nouvelle ArrayList 
			 *  sinon il est impossible de faire : liste.remove(0) !!!
			 */
			List<String> liste = new ArrayList<String>(Arrays.asList(str));
			liste.remove(0);
			System.out.println("La ligne contient maintenant : " + liste);
			
			if (str[0].equals(JEU_TYPE_ANAGRAMME)) {

				JeuFausseAnagramme jeuFausseAnagramme = creerFausseAnagrammeDepuisListe(liste);
				listeJeux.add(jeuFausseAnagramme);

			} else if (str[0].equals(JEU_TYPE_QUESTION)) {

				JeuQuestionResponse jeuQuestionResponse = creerJeuQuestionDepuisListe(liste);
				listeJeux.add(jeuQuestionResponse);

			} else if (str[0].equals(JEU_TYPE_TRIENTIERS)) {

				JeuTriEntiers jeuTriEntier = creerJeuTriEntierDepuisListe(liste);
				listeJeux.add(jeuTriEntier);

			} else {
				
				System.out.println("Jeu inconnu !");
				
			}

//			JeuQuestionResponse jqr = creerJeuQuestionResponseDepuisLigne(line);
//			listeJeux.add(jqr);
			
			i++;
		}

		return listeJeux;
	}

	private static JeuQuestionResponse creerJeuQuestionDepuisListe(List<String> liste) {

		JeuQuestionResponse jqr = new JeuQuestionResponse();

		jqr.setQuestion(liste.get(0));
		jqr.setReponse(liste.get(1));

		return jqr;
	}

	private static JeuFausseAnagramme creerFausseAnagrammeDepuisListe(List<String> liste) {

		JeuFausseAnagramme jfa = new JeuFausseAnagramme();

		// Ex ligne initiale : anagramme;mot // comme : anagramme a déjà été
		// retiré, on prend ce qui reste dans la liste
		jfa.setMotFausseAnagramme(liste.get(0));

		return jfa;
	}

	/**
	 * Méthode qui prend en paramètre le chemin du fichier contenant les
	 * questions et les réponses
	 * 
	 * @param cheminFichier
	 * @return une liste de jeux
	 */
	public static List<Jeux> creerJeuQuestionReponseDepuisFichier(String cheminFichier) {

		List<Jeux> listeJeux = new ArrayList<Jeux>();

		try {
			File f = new File(cheminFichier);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				String line = br.readLine();

				// On boucle sur chaque ligne du fichier.
				while (line != null) {

					JeuQuestionResponse jqr = creerJeuQuestionResponseDepuisLigne(line);
					listeJeux.add(jqr);

					line = br.readLine();
				}

				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas été trouvé");
		}

		return listeJeux;
	}

	/**
	 * Méthode chargée de créer un JeuFausseAnagramme depuis une ligne d'un
	 * fichier. <br>
	 * Il y a un mot par ligne.
	 * 
	 * @param line
	 *            Ligne à traiter.
	 * @return Jeu créé à partir de la ligne.
	 */
	public static JeuQuestionResponse creerJeuQuestionResponseDepuisLigne(String line) {

		// JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		JeuQuestionResponse jqr = new JeuQuestionResponse();
		//System.out.println("La ligne contient : " + line);

		String str[] = line.split(";");

		jqr.setQuestion(str[0]);
		jqr.setReponse(str[1]);

		return jqr;
	}

	/**
	 * Cette méthode créée une liste de JeuTriEntiers depuis un fichier dont le
	 * chemin est passé en paramètre. <br>
	 * Chaque ligne du fichier correspond à une ligne de jeu.
	 * 
	 * @param cheminFichier
	 *            Chemin du fichier.
	 * @return Jeu créé à partir du fichier.
	 */
	public static List<JeuTriEntiers> creerJeuTriEntiersDepuisFichier(String cheminFichier) {

		List<JeuTriEntiers> listeJeux = new ArrayList<JeuTriEntiers>();

		try {
			File f = new File(cheminFichier);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				String line = br.readLine();

				// On boucle sur chaque ligne du fichier.
				while (line != null) {

					JeuTriEntiers jte = creerJeuTriEntierDepuisLigne(line);
					listeJeux.add(jte);

					line = br.readLine();
				}

				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas été trouvé");
		}

		return listeJeux;
	}

	/**
	 * Méthode chargée de créer un JeuTriEntiers depuis une ligne d'un fichier.
	 * <br>
	 * La ligne correspond à une liste de nombres séparés par des ';'.
	 * 
	 * @param line
	 *            Ligne à traiter.
	 * @return Jeu créé à partir de la ligne.d
	 */
	public static JeuTriEntiers creerJeuTriEntierDepuisLigne(String line) {

		JeuTriEntiers jte = new JeuTriEntiers();
		System.out.println("La ligne contient : " + line);

		String str[] = line.split(";");

		List<String> liste = Arrays.asList(str);

		return creerJeuTriEntierDepuisListe(liste);
	}

	/**
	 * Cette méthode permet de creer un Jeu TriEntier depuis une Liste de String contenant des nombres
	 * 
	 * @param List de String contient les valeurs à convertir en entier (Integer)
	 * @return JeuTriEntiers qui contient une liste de Integer
	 */
	public static JeuTriEntiers creerJeuTriEntierDepuisListe(List<String> tab)  {

		JeuTriEntiers jte = new JeuTriEntiers();

		// On traite la ligne.
		boolean err = false;
		boolean auMoins1err = false;
		for (int i = 0; i < tab.size(); i++) {
			err = false;
			Integer nbr = null;

			try {
				 nbr = Integer.valueOf(tab.get(i));
				 //nbr = Integer.parseInt(tab.get(i));
			}
			catch (NumberFormatException e) {
				System.out.println("Fichier mal formé, veuillez corriger le fichier de données : ");
				System.out.println("le jeu TriEntiers contient des valeurs alphabétiques !");
				err = true;
				auMoins1err = err;
				e.printStackTrace();
			}
			if(!err) {
				jte.addEntierDansListe(nbr);
			}
		}
		
		if(auMoins1err) {
			System.out.println("Suite à une erreur dans le fichier de données, la liste contient : " + jte.getListEntiers());
		}
		
		return jte;
	}

	/**
	 * 
	 * @param cheminFichier
	 * @return
	 */
	public static List<JeuFausseAnagramme> creerJeuFausseAnagrammeDepuisFichier(String cheminFichier) {

		List<JeuFausseAnagramme> listeJeux = new ArrayList<JeuFausseAnagramme>();

		try {
			File f = new File(cheminFichier);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				String line = br.readLine();

				// On boucle sur chaque ligne du fichier.
				while (line != null) {

					JeuFausseAnagramme jfa = creerFausseAnagrammeDepuisLigne(line);
					listeJeux.add(jfa);

					line = br.readLine();
				}

				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas été trouvé");
		}

		return listeJeux;
	}

	/**
	 * Méthode chargée de créer un JeuFausseAnagramme depuis une ligne d'un
	 * fichier. <br>
	 * Il y a un mot par ligne.
	 * 
	 * @param line
	 *            Ligne à traiter.
	 * @return Jeu créé à partir de la ligne.
	 */
	public static JeuFausseAnagramme creerFausseAnagrammeDepuisLigne(String line) {

		JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		System.out.println("La ligne contient : " + line);

		jfa.setMotFausseAnagramme(line);

		return jfa;
	}

	/**
	 * Cette méthode créée un objet JeuTriEntiers depuis la console en demande à
	 * l'utilisateur les nombres qu'il veut utiliser.
	 * 
	 * @return Le jeu créé par l'utilisateur.
	 */
	public static JeuTriEntiers creerJeuTriEntiers() {

		Integer nb_entiers = 0;
		JeuTriEntiers jte = new JeuTriEntiers();
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez entrer le nombre d'entiers que vous allez saisir : ");
		nb_entiers = sc.nextInt();

		for (int i = 0; i < nb_entiers; i++) {
			int a = i + 1;
			System.out.println("Veuillez saisir le nombre entier n° " + a + "/" + nb_entiers + " : ");
			jte.addEntierDansListe(sc.nextInt());
		}

		return jte;
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * @return jfa : retourne une reférence à l'objet de type JeuFausseAnagramme
	 */
	public static JeuFausseAnagramme creerJeuFausseAnagramme() {

		JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez saisir le mot qui va être présenté au joueur pour l'anagramme :");
		jfa.setMotFausseAnagramme(sc.nextLine());

		return jfa;
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * @return JeuQuestionImage : retourne la référence de l'objet contenant le
	 *         chemin et le nom de l'image
	 */
	public static JeuQuestionImageReponse creerJeuQuestionImage() {

		JeuQuestionImageReponse jeuQuestionImage = new JeuQuestionImageReponse();

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
	 * Cette méthode est utilisée
	 * 
	 * Cette méthode permet de renseigner les informations en rapport avec la
	 * question à poser au joueur pendant la partie
	 * 
	 * @return QuestionsReponses : objet contenant le texte de la question,
	 *         ainsi que la réponse à la question
	 */
	public static JeuQuestionResponse creerJeuQuestion() {

		Scanner sc = new Scanner(System.in);

		JeuQuestionResponse qr = new JeuQuestionResponse();

		System.out.println("Veuillez saisir une question : ");
		qr.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la réponse à la question :");
		qr.setReponse(sc.nextLine());

		return qr;
	}
}
