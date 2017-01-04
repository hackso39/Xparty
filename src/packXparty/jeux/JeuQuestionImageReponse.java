package packXparty.jeux;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import packXparty.Launcher;

/**
 * @author
 * 
 * 		Cette classe POJO permet la cr�ation du jeu Question sur Image
 *         Reponse Elle contient les variables d'instance : <BR/>
 *         - cheminImage <BR/>
 *         - nomImage <BR/>
 */
public class JeuQuestionImageReponse extends JeuQuestionResponse {

	private String cheminImage = "";
	private String nomImage = "";

	/**
	 * Constructeur par d�faut
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
	 * Cette m�thode est utilis�e
	 * 
	 * @param fen
	 *            : objet contenant la r�f�rence de la fen�tre � fermer
	 */
	private static void fermerFenetre(JFrame fen) {
		// Ferme la fen�tre
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
