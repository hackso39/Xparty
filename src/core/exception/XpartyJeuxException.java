package core.exception;

public class XpartyJeuxException extends XpartyException {

	private static final long serialVersionUID = 1L;
	
	private String messageExplicationUtilisateur;
	
	public XpartyJeuxException() {
		super();
	}

	public XpartyJeuxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XpartyJeuxException(String message, Throwable cause) {
		super(message, cause);
	}

	public XpartyJeuxException(String message) {
		super(message);
	}

	public XpartyJeuxException(Throwable cause) {
		super(cause);
	}

	public String getMessageExplicationUtilisateur() {
		return messageExplicationUtilisateur;
	}

	public void setMessageExplicationUtilisateur(String messageExplicationUtilisateur) {
		this.messageExplicationUtilisateur = messageExplicationUtilisateur;
	}
}
