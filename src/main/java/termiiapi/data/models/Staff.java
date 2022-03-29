package termiiapi.data.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "staff")
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long staffId;

    @Column(name = "staff_name")
    @NotBlank(message = "Name cannot be blank")
    private String staffName;

    @Column(name = "phone_number")
    @NotBlank(message = "phoneNumber cannot be blank")
    private String phoneNumber;

    @Column(name = "email_address")
    @NotBlank(message = "emailAddress cannot be blank")
    private String emailAddress;

    @Column(name = "address")
    @NotBlank(message = "address cannot be blank")
    private String address;


    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
