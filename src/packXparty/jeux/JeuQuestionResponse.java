package packXparty.jeux;

import packXparty.Launcher;

/**
 * @author
 * 
 * 		Classe POJO permettant de créer le jeu Question Reponse. <BR/>
 *         Elle contient les variables de classe : <BR/>
 *         - question <BR/>
 *         - reponse <BR/>
 */
public class JeuQuestionResponse implements Jeux {

	/**
	 * Pojo pour les questions/réponses
	 */
	protected String question = "";
	protected String reponse = "";

	/**
	 * Constructeur par défaut
	 */
	public JeuQuestionResponse() {
		super();
	}

	public int jouer(int compteurPoints) {
		return Launcher.afficherQuestion(this, compteurPoints);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

}
