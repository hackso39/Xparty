package core.exception;

/**
 * @author
 * 
 * Classe permettant de gérer les exceptions suite à l'utilisation
 * de fichiers contenant les informations de jeux.
 */
public class JeuInvalideException extends Exception {

	private static final long serialVersionUID = 1L;

	public JeuInvalideException() {
		super();
	}

	public JeuInvalideException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JeuInvalideException(String message, Throwable cause) {
		super(message, cause);
	}

	public JeuInvalideException(String message) {
		super(message);
	}

	public JeuInvalideException(Throwable cause) {
		super(cause);
	}
}
