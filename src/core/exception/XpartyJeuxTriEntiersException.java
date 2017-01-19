package core.exception;

public class XpartyJeuxTriEntiersException extends XpartyJeuxException {

	private static final long serialVersionUID = 1L;

	private String chaineInvalide;
	
	public XpartyJeuxTriEntiersException() {
		super();
	}

	public XpartyJeuxTriEntiersException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XpartyJeuxTriEntiersException(String message, Throwable cause) {
		super(message, cause);
	}

	public XpartyJeuxTriEntiersException(String message) {
		super(message);
	}

	public XpartyJeuxTriEntiersException(Throwable cause) {
		super(cause);
	}

	public String getChaineInvalide() {
		return chaineInvalide;
	}

	public void setChaineInvalide(String chaineInvalide) {
		this.chaineInvalide = chaineInvalide;
	}
}
