package packXparty;

public class JeuQuestion implements Jeux {
	
	/**
	 * Pojo pour les questions/réponses
	 */
	protected String question = "";
	protected String reponse = "";
	
	public JeuQuestion() {
		super();
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
