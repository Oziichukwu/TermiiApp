package termiiapi.web.ecxeptions;

public class StaffNotFoundException extends TermiiBusinessException {
    public StaffNotFoundException() {
    }

    public StaffNotFoundException(String message) {
        super(message);
    }

    public StaffNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StaffNotFoundException(Throwable cause) {
        super(cause);
    }
}
