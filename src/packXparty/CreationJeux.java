package packXparty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import packXparty.jeux.JeuFausseAnagramme;
import packXparty.jeux.JeuQuestionImageReponse;
import packXparty.jeux.JeuQuestionResponse;
import packXparty.jeux.JeuTriEntiers;
import packXparty.jeux.Jeux;

public abstract class CreationJeux {
	
	public static List<Jeux> creerJeuQuestionReponseDepuisFichier(String cheminFichier) {
		
		List<Jeux> listeJeux = new ArrayList<Jeux>();
		
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
	            
		            JeuQuestionResponse jqr = creerJeuQuestionResponseDepuisLigne(line);
		            listeJeux.add(jqr);
		            
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
	public static JeuQuestionResponse creerJeuQuestionResponseDepuisLigne(String line) {
		
		//JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		JeuQuestionResponse jqr = new JeuQuestionResponse();
    	System.out.println("La ligne contient : " + line);
    	
        String str[] = line.split(";");
        
        jqr.setQuestion(str[0]);
        jqr.setReponse(str[1]);
    	
        return jqr;
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
	 * Cette m�thode est utilis�e
	 * 
	 * @return jfa : retourne une ref�rence � l'objet de type JeuFausseAnagramme
	 */
	public static JeuFausseAnagramme creerJeuFausseAnagramme() {
		
		JeuFausseAnagramme jfa = new JeuFausseAnagramme();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez saisir le mot qui va �tre pr�sent� au joueur pour l'anagramme :");
		jfa.setMotFausseAnagramme(sc.nextLine());
		
		return jfa;
	}	
	
	/**
	 * Cette m�thode est utilis�e
	 * 
	 * @return JeuQuestionImage : retourne la r�f�rence de l'objet 
	 * contenant le chemin et le nom de l'image 
	 */
	public static JeuQuestionImageReponse creerJeuQuestionImage() {

		JeuQuestionImageReponse jeuQuestionImage = new JeuQuestionImageReponse();
		
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
	 * Cette m�thode permet de renseigner les informations en rapport 
	 * avec la question � poser au joueur pendant la partie
	 * @return QuestionsReponses : objet contenant le texte de la question, ainsi que la r�ponse � la question
	 */
	public static JeuQuestionResponse creerJeuQuestion() {

		Scanner sc = new Scanner(System.in);

		JeuQuestionResponse qr = new JeuQuestionResponse();

		System.out.println("Veuillez saisir une question : ");
		qr.setQuestion(sc.nextLine());

		System.out.println("Veuillez saisir la r�ponse � la question :");
		qr.setReponse(sc.nextLine());

		return qr;
	}
}
