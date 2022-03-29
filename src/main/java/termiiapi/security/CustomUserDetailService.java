package termiiapi.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import termiiapi.data.models.Staff;
import termiiapi.data.repositories.StaffRepository;

import static java.lang.String.format;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Staff user = staffRepository.findByEmailAddress(email).orElseThrow(()->
           new UsernameNotFoundException(format("User not found with email %s", email)));
        return UserPrincipal.create(user);
    }
}
