package core.exception;

/**
 * @author
 * 
 * Classe permettant de gérer les exceptions suite à l'utilisation
 * de fichiers contenant les informations de jeux.
 */
public class CreationJeuxDepuisFichierException extends Exception {

	private static final long serialVersionUID = 1L;

	public CreationJeuxDepuisFichierException() {
		super();
	}

	public CreationJeuxDepuisFichierException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CreationJeuxDepuisFichierException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreationJeuxDepuisFichierException(String message) {
		super(message);
	}

	public CreationJeuxDepuisFichierException(Throwable cause) {
		super(cause);
	}
}
