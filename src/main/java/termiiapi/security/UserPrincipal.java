package termiiapi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import termiiapi.data.models.Staff;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPrincipal implements UserDetails {

    private Long id;
    private String staffName;
    private String address;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetails create(Staff user){

        List<GrantedAuthority> authorities = user.getRoles().stream().map(role->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());
        return new UserPrincipal(
                user.getStaffId(),
                user.getStaffName(),
                user.getPhoneNumber(),
                user.getEmailAddress(),
                user.getPhoneNumber(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return format("%s ", emailAddress);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
