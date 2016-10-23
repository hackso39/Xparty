package packXparty;

public class JeuQuestionImage extends JeuQuestion {
	
	private String cheminImage = "";
	private String nomImage = "";
	
	/**
	 * Constructeur par défaut
	 */
	public JeuQuestionImage() {
		super();
		
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
