package termiiapi.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long visitorId;

    @Column(name = "visitor_name")
    @NotBlank(message = "Name cannot be blank")
    private String visitorName;

    @Column(name = "phone_number")
    @NotBlank(message = "phone number is required")
    private String phoneNumber;

    @Column(name = "email_address")
    @NotBlank(message = "email address cannot be blank")
    private String emailAddress;

    @Column(name = "address")
    @NotBlank(message = "address is required")
    private String address;
}
