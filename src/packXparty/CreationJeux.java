package packXparty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import core.exception.CreationJeuxDepuisFichierException;
import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuQuestionImageReponse;
import packXparty.jeux.JeuQuestionResponse;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

/**
 * @author
 * 
 * 		Classe permettant de créer les jeux à partir d'un chemin de fichier
 *         texte ou JSON.
 */
public abstract class CreationJeux {

	/**
	 * Cette méthode permet de créé une liste de jeux après avoir lu un fichier
	 * comportant différents types de jeux
	 * 
	 * @param cheminFichier
	 *            chemin du fichier
	 * @return List de Jeux retourne une liste de jeux
	 */
	public static List<Jeux> creerJeuxDepuisFichier(String cheminFichier) throws CreationJeuxDepuisFichierException {

		List<Jeux> listeJeux = new ArrayList<Jeux>();

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
			 * dynamique, la liste est donc figée, il faut alors redéclarer une
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

			i++;
		}

		return listeJeux;
	}

	/**
	 * Cette méthode permet de créé une liste de jeux après avoir lu un fichier
	 * au format JSON comportant différents types de jeux.
	 * 
	 * @param cheminFichier
	 *            chemin du fichier au format JSON
	 * @return List<Jeux> retourne une liste de jeux
	 */
	public static List<Jeux> creerJeuxDepuisFichierJSON(String cheminFichier)
			throws CreationJeuxDepuisFichierException {

		List<Jeux> listeJeux = new ArrayList<Jeux>();

		JSONObject jsonObject = OutilFichiers.lectureLigneJson(cheminFichier);

		JSONArray jsonArray = (JSONArray) jsonObject.get("jeux");

		// On récupère les informations des jeux afin de les créer
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
					String reponse = (String) valeurs.get("réponse");
					System.out.println("Réponse : " + reponse);

					JeuQuestionResponse jqr = new JeuQuestionResponse();
					jqr.setQuestion(question);
					jqr.setReponse(reponse);
					listeJeux.add(jqr);

				}  else if (type != null && type.equals(Launcher.JEU_TYPE_QUESTION_IMAGE)) {

					JSONObject valeurs = (JSONObject) jsonObject.get("valeurs");
					String question = (String) valeurs.get("question");
					System.out.println("Question : " + question);
					String cheminImage = (String) valeurs.get("cheminImage");
					System.out.println("Question : " + cheminImage);
					String reponse = (String) valeurs.get("réponse");
					System.out.println("Réponse : " + reponse);

					JeuQuestionImageReponse jqir = new JeuQuestionImageReponse();
					jqir.setQuestion(question);
					jqir.setCheminImage(cheminImage);
					jqir.setReponse(reponse);
					listeJeux.add(jqir);

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

		// Ex ligne initiale : anagramme;mot // comme : anagramme a déjà été
		// retiré, on prend ce qui reste dans la liste
		jfa.setMotFausseAnagramme(liste.get(0));

		return jfa;
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
		// System.out.println("La ligne contient : " + line);

		String str[] = line.split(";");

		jqr.setQuestion(str[0]);
		jqr.setReponse(str[1]);

		return jqr;
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
	public static JeuTriEntiers creerJeuTriEntierDepuisLigne(String line) throws CreationJeuxDepuisFichierException {

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
	 * Cette méthode permet de creer un Jeu TriEntier depuis une Liste de String
	 * contenant des nombres
	 * 
	 * @param List
	 *            de String contient les valeurs à convertir en entier (Integer)
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
	 * Cette méthode est utilisée
	 * 
	 * @return JeuQuestionImage : retourne la référence de l'objet contenant le
	 *         chemin et le nom de l'image
	 */
	public static JeuQuestionImageReponse creerJeuQuestionImage() {

		JeuQuestionImageReponse jeuQuestionImage = new JeuQuestionImageReponse();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez saisir une question : ");
		jeuQuestionImage.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la réponse à la question :");
		jeuQuestionImage.setReponse(sc.nextLine());

		System.out.println("Veuillez saisir le chemin de l'image à afficher :");
		jeuQuestionImage.setCheminImage(sc.nextLine());

		System.out.println("Veuillez saisir le nom de l'image :");
		jeuQuestionImage.setNomImage(sc.nextLine());

		// sc.close(); // Ne pas mettre cette ligne, sinon :
		// NoSuchElementException
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

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		JeuQuestionResponse qr = new JeuQuestionResponse();

		System.out.println("Veuillez saisir une question : ");
		qr.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la réponse à la question :");
		qr.setReponse(sc.nextLine());

		return qr;
	}
}