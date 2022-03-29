package termiiapi.dtos;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VisitorDto {

    private Long visitorId;

    private String visitorName;

    private String phoneNumber;

    private String emailAddress;

    private String address;
}
