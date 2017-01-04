package packXparty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import core.exception.CreationJeuxDepuisFichierException;
import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuQuestionImageReponse;
import packXparty.jeux.JeuQuestionResponse;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

/**
 * @author hackso39
 */
public abstract class CreationJeux {

	/**
	 * Cette m�thode permet de cr�� une liste de jeux apr�s avoir lu un fichier
	 * comportant diff�rents types de jeux
	 * 
	 * @param cheminFichier
	 *            chemin du fichier
	 * @return List de Jeux retourne une liste de jeux
	 */
	public static List<Jeux> creerJeuxDepuisFichier(String cheminFichier) throws CreationJeuxDepuisFichierException {

		List<Jeux> listeJeux = new ArrayList<Jeux>();

		// final String JEU_TYPE_ANAGRAMME = "anagramme";
		// final String JEU_TYPE_QUESTION = "question";
		// final String JEU_TYPE_TRIENTIERS = "triEntiers";

		List<String> listeLignes = OutilFichiers.lectureLigne(cheminFichier);

		int i = 0;
		String line;

		// On boucle sur chaque ligne du fichier.
		while (i < listeLignes.size()) {

			System.out.println("La ligne contient : " + listeLignes.get(i));
			line = listeLignes.get(i);
			String str[] = line.split(";");

			/*
			 * Arrays.asList(str) <== Transforme un tableau en liste non
			 * dynamique, la liste est donc fig�e, il faut alors red�clarer une
			 * nouvelle ArrayList sinon il est impossible de faire :
			 * liste.remove(0) !!!
			 */
			List<String> liste = new ArrayList<String>(Arrays.asList(str));
			liste.remove(0);
			System.out.println("La ligne contient maintenant : " + liste);

			if (str[0].equals(Launcher.JEU_TYPE_ANAGRAMME)) {

				JeuFausseAnagramme jeuFausseAnagramme = creerFausseAnagrammeDepuisListe(liste);
				listeJeux.add(jeuFausseAnagramme);

			} else if (str[0].equals(Launcher.JEU_TYPE_QUESTION)) {

				JeuQuestionResponse jeuQuestionResponse;

				try {
					creerJeuQuestionDepuisListe(liste);
				} catch (CreationJeuxDepuisFichierException e) {
					throw e;
				}
				jeuQuestionResponse = creerJeuQuestionDepuisListe(liste);
				listeJeux.add(jeuQuestionResponse);

			} else if (str[0].equals(Launcher.JEU_TYPE_TRIENTIERS)) {

				JeuTriEntiers jeuTriEntier;
				try {
					jeuTriEntier = creerJeuTriEntierDepuisListe(liste);
				} catch (CreationJeuxDepuisFichierException e) {
					throw e;
				}
				listeJeux.add(jeuTriEntier);

			} else {

				System.out.println("Jeu inconnu !");

			}

			// JeuQuestionResponse jqr =
			// creerJeuQuestionResponseDepuisLigne(line);
			// listeJeux.add(jqr);

			i++;
		}

		return listeJeux;
	}

	/**
	 * Cette m�thode permet de cr�� une liste de jeux apr�s avoir lu un fichier
	 * au format JSON comportant diff�rents types de jeux
	 * 
	 * @param cheminFichier
	 *            chemin du fichier
	 * @return List<Jeux> retourne une liste de jeux
	 * @throws ParseException
	 */
	public static List<Jeux> creerJeuxDepuisFichierJSON(String cheminFichier)
			throws CreationJeuxDepuisFichierException, ParseException {

		List<Jeux> listeJeux = new ArrayList<Jeux>();

		JSONObject jsonObject = OutilFichiers.lectureLigneJson(cheminFichier);

		JSONArray jsonArray = (JSONArray) jsonObject.get("jeux");

		// On r�cup�re les informations des jeux afin de les cr�er
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				System.out.println(jsonArray.get(i));
				jsonObject = (JSONObject) jsonArray.get(i);

				String type = (String) jsonObject.get("type");
				System.out.println("Type de jeu : " + type);

				if (type != null && type.equals(Launcher.JEU_TYPE_ANAGRAMME)) {
					JSONObject valeurs = (JSONObject) jsonObject.get("valeurs");
					String mot = (String) valeurs.get("mot");
					System.out.println("Valeurs : " + mot);

					JeuFausseAnagramme jfa = new JeuFausseAnagramme();
					jfa.setMotFausseAnagramme(mot);
					listeJeux.add(jfa);

				} else if (type != null && type.equals(Launcher.JEU_TYPE_QUESTION)) {

					JSONObject valeurs = (JSONObject) jsonObject.get("valeurs");
					String question = (String) valeurs.get("question");
					System.out.println("Question : " + question);
					String reponse = (String) valeurs.get("r�ponse");
					System.out.println("R�ponse : " + reponse);

					JeuQuestionResponse jqr = new JeuQuestionResponse();
					jqr.setQuestion(question);
					jqr.setReponse(reponse);
					listeJeux.add(jqr);

				} else if (type != null && type.equals(Launcher.JEU_TYPE_TRIENTIERS)) {

					JSONObject valeurs = (JSONObject) jsonObject.get("valeurs");
					JSONArray nombres = (JSONArray) valeurs.get("nombres");
					System.out.print("Nombres : ");

					JeuTriEntiers jte = new JeuTriEntiers();
					for (int j = 0; j < nombres.size(); j++) {
						Integer nbr = null;
						System.out.print(nombres.get(j));
						if (j < nombres.size() - 1) {
							System.out.print(", ");
						}

						nbr = Integer.valueOf(nombres.get(j).toString());
						jte.addEntierDansListe(nbr);
					}
					System.out.println("");

					listeJeux.add(jte);
				}
			}
		}
		return listeJeux;
	}

	/**
	 * 
	 * @param liste
	 * @return
	 */
	private static JeuQuestionResponse creerJeuQuestionDepuisListe(List<String> liste)
			throws CreationJeuxDepuisFichierException {

		JeuQuestionResponse jqr = new JeuQuestionResponse();

		try {
			jqr.setQuestion(liste.get(0));
			jqr.setReponse(liste.get(1));
		} catch (IndexOutOfBoundsException e) {
			throw new CreationJeuxDepuisFichierException(e);
		}
		return jqr;
	}

	/**
	 * 
	 * @param liste
	 * @return
	 */
	private static JeuFausseAnagramme creerFausseAnagrammeDepuisListe(List<String> liste) {

		JeuFausseAnagramme jfa = new JeuFausseAnagramme();

		// Ex ligne initiale : anagramme;mot // comme : anagramme a d�j� �t�
		// retir�, on prend ce qui reste dans la liste
		jfa.setMotFausseAnagramme(liste.get(0));

		return jfa;
	}

	// /**
	// * M�thode qui prend en param�tre le chemin du fichier contenant les
	// * questions et les r�ponses
	// *
	// * @param cheminFichier
	// * @return une liste de jeux
	// */
	// public static List<Jeux> creerJeuQuestionReponseDepuisFichier(String
	// cheminFichier) {
	//
	// List<Jeux> listeJeux = new ArrayList<Jeux>();
	//
	// try {
	// File f = new File(cheminFichier);
	// FileReader fr = new FileReader(f);
	// BufferedReader br = new BufferedReader(fr);
	//
	// try {
	// String line = br.readLine();
	//
	// // On boucle sur chaque ligne du fichier.
	// while (line != null) {
	//
	// JeuQuestionResponse jqr = creerJeuQuestionResponseDepuisLigne(line);
	// listeJeux.add(jqr);
	//
	// line = br.readLine();
	// }
	//
	// br.close();
	// fr.close();
	// } catch (IOException exception) {
	// System.out.println("Erreur lors de la lecture : " +
	// exception.getMessage());
	// }
	// } catch (FileNotFoundException exception) {
	// System.out.println("Le fichier n'a pas �t� trouv�");
	// }
	//
	// return listeJeux;
	// }

	/**
	 * M�thode charg�e de cr�er un JeuFausseAnagramme depuis une ligne d'un
	 * fichier. <br>
	 * Il y a un mot par ligne.
	 * 
	 * @param line
	 *            Ligne � traiter.
	 * @return Jeu cr�� � partir de la ligne.
	 */
	public static JeuQuestionResponse creerJeuQuestionResponseDepuisLigne(String line) {

		// JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		JeuQuestionResponse jqr = new JeuQuestionResponse();
		// System.out.println("La ligne contient : " + line);

		String str[] = line.split(";");

		jqr.setQuestion(str[0]);
		jqr.setReponse(str[1]);

		return jqr;
	}

	/**
	 * Cette m�thode cr��e une liste de JeuTriEntiers depuis un fichier dont le
	 * chemin est pass� en param�tre. <br>
	 * Chaque ligne du fichier correspond � une ligne de jeu.
	 * 
	 * @param cheminFichier
	 *            Chemin du fichier.
	 * @return Jeu cr�� � partir du fichier.
	 */
	// public static List<JeuTriEntiers> creerJeuTriEntiersDepuisFichier(String
	// cheminFichier) {
	//
	// List<JeuTriEntiers> listeJeux = new ArrayList<JeuTriEntiers>();
	//
	// try {
	// File f = new File(cheminFichier);
	// FileReader fr = new FileReader(f);
	// BufferedReader br = new BufferedReader(fr);
	//
	// try {
	// String line = br.readLine();
	//
	// // On boucle sur chaque ligne du fichier.
	// while (line != null) {
	//
	// JeuTriEntiers jte = creerJeuTriEntierDepuisLigne(line);
	// listeJeux.add(jte);
	//
	// line = br.readLine();
	// }
	//
	// br.close();
	// fr.close();
	// } catch (IOException exception) {
	// System.out.println("Erreur lors de la lecture : " +
	// exception.getMessage());
	// }
	// } catch (FileNotFoundException exception) {
	// System.out.println("Le fichier n'a pas �t� trouv�");
	// }
	//
	// return listeJeux;
	// }

	/**
	 * M�thode charg�e de cr�er un JeuTriEntiers depuis une ligne d'un fichier.
	 * <br>
	 * La ligne correspond � une liste de nombres s�par�s par des ';'.
	 * 
	 * @param line
	 *            Ligne � traiter.
	 * @return Jeu cr�� � partir de la ligne.d
	 */
	public static JeuTriEntiers creerJeuTriEntierDepuisLigne(String line) throws CreationJeuxDepuisFichierException {

		// JeuTriEntiers jte = new JeuTriEntiers();
		System.out.println("La ligne contient : " + line);

		String str[] = line.split(";");

		List<String> liste = Arrays.asList(str);

		JeuTriEntiers jeuTriEntier;
		try {
			jeuTriEntier = creerJeuTriEntierDepuisListe(liste);
		} catch (CreationJeuxDepuisFichierException e) {
			throw new CreationJeuxDepuisFichierException();
		}

		return jeuTriEntier;
	}

	/**
	 * Cette m�thode permet de creer un Jeu TriEntier depuis une Liste de String
	 * contenant des nombres
	 * 
	 * @param List
	 *            de String contient les valeurs � convertir en entier (Integer)
	 * @return JeuTriEntiers qui contient une liste de Integer
	 */
	public static JeuTriEntiers creerJeuTriEntierDepuisListe(List<String> tab)
			throws CreationJeuxDepuisFichierException {

		JeuTriEntiers jte = new JeuTriEntiers();

		// On traite la ligne.
		for (int i = 0; i < tab.size(); i++) {
			Integer nbr = null;

			try {
				nbr = Integer.valueOf(tab.get(i));
				jte.addEntierDansListe(nbr);
			} catch (NumberFormatException e) {
				throw new CreationJeuxDepuisFichierException();
			}
		}

		return jte;
	}

	// /**
	// *
	// * @param cheminFichier
	// * @return
	// */
	// public static List<JeuFausseAnagramme>
	// creerJeuFausseAnagrammeDepuisFichier(String cheminFichier) {
	//
	// List<JeuFausseAnagramme> listeJeux = new ArrayList<JeuFausseAnagramme>();
	//
	// try {
	// File f = new File(cheminFichier);
	// FileReader fr = new FileReader(f);
	// BufferedReader br = new BufferedReader(fr);
	//
	// try {
	// String line = br.readLine();
	//
	// // On boucle sur chaque ligne du fichier.
	// while (line != null) {
	//
	// JeuFausseAnagramme jfa = creerFausseAnagrammeDepuisLigne(line);
	// listeJeux.add(jfa);
	//
	// line = br.readLine();
	// }
	//
	// br.close();
	// fr.close();
	// } catch (IOException exception) {
	// System.out.println("Erreur lors de la lecture : " +
	// exception.getMessage());
	// }
	// } catch (FileNotFoundException exception) {
	// System.out.println("Le fichier n'a pas �t� trouv�");
	// }
	//
	// return listeJeux;
	// }

	/**
	 * M�thode charg�e de cr�er un JeuFausseAnagramme depuis une ligne d'un
	 * fichier. <br>
	 * Il y a un mot par ligne.
	 * 
	 * @param line
	 *            Ligne � traiter.
	 * @return Jeu cr�� � partir de la ligne.
	 */
	public static JeuFausseAnagramme creerFausseAnagrammeDepuisLigne(String line) {

		JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		System.out.println("La ligne contient : " + line);

		jfa.setMotFausseAnagramme(line);

		return jfa;
	}

	// /**
	// * Cette m�thode cr��e un objet JeuTriEntiers depuis la console en
	// * demandant � l'utilisateur les nombres qu'il veut utiliser.
	// *
	// * @return Le jeu cr�� par l'utilisateur.
	// */
	// public static JeuTriEntiers creerJeuTriEntiers() {
	//
	// Integer nb_entiers = 0;
	// JeuTriEntiers jte = new JeuTriEntiers();
	// Scanner sc = new Scanner(System.in);
	//
	// System.out.println("Veuillez entrer le nombre d'entiers que vous allez
	// saisir : ");
	// nb_entiers = sc.nextInt();
	//
	// for (int i = 0; i < nb_entiers; i++) {
	// int a = i + 1;
	// System.out.println("Veuillez saisir le nombre entier n� " + a + "/" +
	// nb_entiers + " : ");
	// jte.addEntierDansListe(sc.nextInt());
	// }
	//
	// sc.close(); // Ne pas mettre cette ligne, sinon : NoSuchElementException
	// return jte;
	// }

	// /**
	// * Cette m�thode est utilis�e
	// *
	// * @return jfa : retourne une ref�rence � l'objet de type
	// JeuFausseAnagramme
	// */
	// public static JeuFausseAnagramme creerJeuFausseAnagramme() {
	//
	// JeuFausseAnagramme jfa = new JeuFausseAnagramme();
	// Scanner sc = new Scanner(System.in);
	//
	// System.out.println("Veuillez saisir le mot qui va �tre pr�sent� au joueur
	// pour l'anagramme :");
	// jfa.setMotFausseAnagramme(sc.nextLine());
	//
	// sc.close(); // Ne pas mettre cette ligne, sinon : NoSuchElementException
	// return jfa;
	// }

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * @return JeuQuestionImage : retourne la r�f�rence de l'objet contenant le
	 *         chemin et le nom de l'image
	 */
	public static JeuQuestionImageReponse creerJeuQuestionImage() {

		JeuQuestionImageReponse jeuQuestionImage = new JeuQuestionImageReponse();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez saisir une question : ");
		jeuQuestionImage.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la r�ponse � la question :");
		jeuQuestionImage.setReponse(sc.nextLine());

		System.out.println("Veuillez saisir le chemin de l'image � afficher :");
		jeuQuestionImage.setCheminImage(sc.nextLine());

		System.out.println("Veuillez saisir le nom de l'image :");
		jeuQuestionImage.setNomImage(sc.nextLine());

		// sc.close(); // Ne pas mettre cette ligne, sinon :
		// NoSuchElementException
		return jeuQuestionImage;
	}

	/**
	 * Cette m�thode est utilis�e
	 * 
	 * Cette m�thode permet de renseigner les informations en rapport avec la
	 * question � poser au joueur pendant la partie
	 * 
	 * @return QuestionsReponses : objet contenant le texte de la question,
	 *         ainsi que la r�ponse � la question
	 */
	public static JeuQuestionResponse creerJeuQuestion() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		JeuQuestionResponse qr = new JeuQuestionResponse();

		System.out.println("Veuillez saisir une question : ");
		qr.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la r�ponse � la question :");
		qr.setReponse(sc.nextLine());

		// sc.close(); // Ne pas mettre cette ligne, sinon :
		// NoSuchElementException

		return qr;
	}
}