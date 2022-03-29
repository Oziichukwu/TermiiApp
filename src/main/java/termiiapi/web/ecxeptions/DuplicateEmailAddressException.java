package termiiapi.web.ecxeptions;

public class DuplicateEmailAddressException extends TermiiBusinessException {
    public DuplicateEmailAddressException() {
    }

    public DuplicateEmailAddressException(String message) {
        super(message);
    }

    public DuplicateEmailAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEmailAddressException(Throwable cause) {
        super(cause);
    }
}
