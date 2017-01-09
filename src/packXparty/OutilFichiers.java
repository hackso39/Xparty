package packXparty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author
 *
 * 		Cette classe permet de réaliser des opérations sur les fichiers comme
 *         par exemple la lecture.
 */
public class OutilFichiers {

	/**
	 * Constructeur par défaut
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
			System.out.println("Le fichier n'a pas été trouvé");
		}

		return listeLignes;
	}

	/**
	 * Fonctionnement JSON : when syntax is {}then this is JsonObject when
	 * syntax is [] then this is JsonArray
	 * 
	 * @param cheminFichier
	 * @return JSONObject contient toutes les informations présentes dans le
	 *         fichier JSON
	 */
	public static JSONObject lectureLigneJson(String cheminFichier) {

		JSONParser parser = new JSONParser();

		try {

			return (JSONObject)  parser.parse(new FileReader(cheminFichier));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new JSONObject();

	}
	
	/**
	 * 
	 * @param rd
	 * @return String
	 * @throws IOException
	 */
	public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


	public static JSONObject lectureFichierJSONdepuisURL(String urlHttp) {
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			URL url = new URL(urlHttp);   // URL à parser
			URLConnection uc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "iso-8859-1"), 8);
			
			String jsonText = OutilFichiers.readAll(in);
			
			jsonObject = (JSONObject) parser.parse(jsonText);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}