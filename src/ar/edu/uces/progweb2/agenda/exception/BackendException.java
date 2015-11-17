package ar.edu.uces.progweb2.agenda.exception;

public class BackendException extends Exception {

	private static final long serialVersionUID = -3077055141672034173L;

	public BackendException() {
		super();
	}

	public BackendException(String message) {
		super(message);
	}

	public BackendException(String message, Throwable cause) {
		super(message, cause);
	}
	
    public BackendException(Throwable cause) {
        super(cause);
    }
}
