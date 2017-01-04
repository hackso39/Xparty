package packXparty.jeux;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import packXparty.Launcher;

/**
 * @author
 * 
 * 		Cette classe POJO permet la création du jeu Question sur Image
 *         Reponse Elle contient les variables d'instance : <BR/>
 *         - cheminImage <BR/>
 *         - nomImage <BR/>
 */
public class JeuQuestionImageReponse extends JeuQuestionResponse {

	private String cheminImage = "";
	private String nomImage = "";

	/**
	 * Constructeur par défaut
	 */
	public JeuQuestionImageReponse() {
		super();

	}

	public int jouer(int compteurPoints) {

		JFrame fen = Launcher.afficherImage(this.getCheminImage(), this.getNomImage());
		compteurPoints = Launcher.afficherQuestion(this, compteurPoints);
		fermerFenetre(fen);

		return compteurPoints;
	}

	/**
	 * Cette méthode est utilisée
	 * 
	 * @param fen
	 *            : objet contenant la référence de la fenêtre à fermer
	 */
	private static void fermerFenetre(JFrame fen) {
		// Ferme la fenêtre
		fen.dispatchEvent(new WindowEvent(fen, WindowEvent.WINDOW_CLOSING));
	}

	public String getCheminImage() {
		return cheminImage;
	}

	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}
}
