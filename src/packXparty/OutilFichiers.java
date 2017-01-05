package packXparty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author
 *
 * 		Cette classe permet de r�aliser des op�rations sur les fichiers comme
 *         par exemple la lecture.
 */
public class OutilFichiers {

	/**
	 * Constructeur par d�faut
	 */
	public OutilFichiers() {
		super();
	}

	/**
	 * On charge les infos du fichier et on retourne les lignes dans une liste.
	 * 
	 * @param cheminFichier
	 *            Chemin du fichier
	 * @return List de String Liste des lignes lues dans le fichier
	 */
	public static List<String> lectureLigne(String cheminFichier) {

		List<String> listeLignes = new ArrayList<String>();

		try {

			File f = new File(cheminFichier);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {

				String line = br.readLine();

				// On boucle sur chaque ligne du fichier.
				while (line != null) {
					listeLignes.add(line);
					line = br.readLine();
				}

				br.close();
				fr.close();

			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}

		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas �t� trouv�");
		}

		return listeLignes;
	}

	/**
	 * Fonctionnement JSON : when syntax is {}then this is JsonObject when
	 * syntax is [] then this is JsonArray
	 * 
	 * @param cheminFichier
	 * @return JSONObject contient toutes les informations pr�sentes dans le
	 *         fichier JSON
	 */
	public static JSONObject lectureLigneJson(String cheminFichier) {

		JSONParser parser = new JSONParser();

		JSONObject jsonObject = new JSONObject();

		try {

			Object obj = parser.parse(new FileReader(cheminFichier));

			jsonObject = (JSONObject) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
