package core.exception;

public class XpartyJeuxAnagrammeException extends XpartyJeuxException {

	private static final long serialVersionUID = 1L;
	
	public XpartyJeuxAnagrammeException() {
		super();
	}

	public XpartyJeuxAnagrammeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XpartyJeuxAnagrammeException(String message, Throwable cause) {
		super(message, cause);
	}

	public XpartyJeuxAnagrammeException(String message) {
		super(message);
	}

	public XpartyJeuxAnagrammeException(Throwable cause) {
		super(cause);
	}
}
