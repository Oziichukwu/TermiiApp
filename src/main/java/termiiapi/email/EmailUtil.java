package termiiapi.email;


public interface EmailUtil {

    void sendEmail(String toAddress, String subject, String body);
}