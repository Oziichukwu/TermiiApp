package termiiapi.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffDto {

    private Long staffId;

    private String staffName;

    private String phoneNumber;

    private String emailAddress;

    private String address;
}
