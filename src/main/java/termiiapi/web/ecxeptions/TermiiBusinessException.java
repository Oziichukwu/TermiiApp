package termiiapi.web.ecxeptions;

public class TermiiBusinessException extends Exception{

    public TermiiBusinessException() {
    }

    public TermiiBusinessException(String message) {
        super(message);
    }

    public TermiiBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public TermiiBusinessException(Throwable cause) {
        super(cause);
    }
}
