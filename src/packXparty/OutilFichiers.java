package packXparty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

/**
 * 
 * @author hackso39
 * 
 * Cette classe permet de réaliser des opérations sur les fichiers.
 *
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
	 * @param cheminFichier Chemin du fichier
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
	 * 
	 *	when syntax is {}then this is JsonObject
     *	when syntax is [] then this is JsonArray
	 * 
	 * @param cheminFichier
	 * @return
	 */
	public static List<String> lectureLigneJson(String cheminFichier) throws ParseException {
		
		List<String> listeLignes = new ArrayList<String>();
		
        JSONParser parser = new JSONParser();
        
        try {
 
            Object obj = parser.parse(new FileReader(cheminFichier));
            
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray jsonArray = (JSONArray)jsonObject.get("jeux");
            
            // Transformation de la JSONArray en ArrayList afin de la retourner à la fin de la méthode
            if (jsonArray != null) { 
            	   for (int i = 0 ; i < jsonArray.size() ; i++){ 
            		   System.out.println(jsonArray.get(i));
            		   jsonObject = (JSONObject) jsonArray.get(i);
            		   
            		   String type = (String ) jsonObject.get("type");
            		   System.out.println(type);
            		   String valeurs = (String ) jsonObject.get("valeurs");
            		   System.out.println(valeurs);
            		   
            		   
            		   listeLignes.add(jsonArray.get(i).toString());
            	   } 
            } 
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		//listeLignes = null;
		return listeLignes ;
	}
}
