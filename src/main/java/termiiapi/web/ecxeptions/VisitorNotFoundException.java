package termiiapi.web.ecxeptions;

public class VisitorNotFoundException extends TermiiBusinessException{

    public VisitorNotFoundException() {
    }

    public VisitorNotFoundException(String message) {
        super(message);
    }

    public VisitorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisitorNotFoundException(Throwable cause) {
        super(cause);
    }
}
