package core.exception;

public class XpartyException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public XpartyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XpartyException(String message, Throwable cause) {
		super(message, cause);
	}

	public XpartyException(String message) {
		super(message);
	}

	public XpartyException(Throwable cause) {
		super(cause);
	}

	public XpartyException() {
		super();
	}

	
}
