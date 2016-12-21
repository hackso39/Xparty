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
	 * Constructeur par d�faut
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
        	System.out.println("Le fichier n'a pas �t� trouv�");
        }
        		
		return listeLignes;
	}
}
