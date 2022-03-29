package termiiapi.web.ecxeptions;

public class RunTimeExceptionPlaceholder extends TermiiBusinessException {
    public RunTimeExceptionPlaceholder() {
    }

    public RunTimeExceptionPlaceholder(String message) {
        super(message);
    }

    public RunTimeExceptionPlaceholder(String message, Throwable cause) {
        super(message, cause);
    }

    public RunTimeExceptionPlaceholder(Throwable cause) {
        super(cause);
    }
}
