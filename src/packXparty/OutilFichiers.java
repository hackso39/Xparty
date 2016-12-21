package packXparty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

public class OutilFichiers {
	
	
	/**
	 * Constructeur par défaut
	 */
	public OutilFichiers() {
		super();
	}
	
	// Créer les méthodes :
	// - à partir d'un chemin de fichier, retourner la ligne lue
	// - à partir d'un chemin, retourner un tableau au format String de lignes
	
	// public static List<Jeux> creerJeuxDepuisFichier(String cheminFichier)
	
	// public static List<Jeux> creerJeuQuestionReponseDepuisFichier(String cheminFichier)
	
	// public static List<JeuTriEntiers> creerJeuTriEntiersDepuisFichier(String cheminFichier)
	
	// public static List<JeuFausseAnagramme> creerJeuFausseAnagrammeDepuisFichier(String cheminFichier)
	
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
}
