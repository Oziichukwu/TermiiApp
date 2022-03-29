package termiiapi.service.staffTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import termiiapi.data.models.Role;
import termiiapi.data.models.RoleName;
import termiiapi.data.models.Staff;
import termiiapi.data.repositories.StaffRepository;
import termiiapi.dtos.JwtTokenResponse;
import termiiapi.dtos.LoginRequest;
import termiiapi.security.CustomUserDetailService;
import termiiapi.security.JwtTokenProvider;
import termiiapi.security.UserPrincipal;
import termiiapi.services.StaffService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StaffLoginTest {


    @Mock
    private StaffRepository staffRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private StaffService staffService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailService customUserDetailService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private Staff mockedUser;


    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        mockedUser = new Staff();
        mockedUser.setStaffId(1L);
        mockedUser.setEmailAddress("goodnews@gmail.com");
        mockedUser.setPassword("123Ugc@@@");
        mockedUser.setPhoneNumber("08100841169");

        Role role = new Role(RoleName.ROLE_STAFF);
        mockedUser.getRoles().add(role);
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void whenLoginMethodIsCalled_FindUserByEmailIsCalled(){

        //Given
        LoginRequest loginRequest = new LoginRequest("goodnews@gmail.com", "123Ugc@@@");
        when(staffRepository.findByEmailAddress("goodnews@gmail.com")).thenReturn(Optional.of(mockedUser));

        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()
        );

        testingAuthenticationToken.setAuthenticated(true);
        testingAuthenticationToken.setDetails(loginRequest);

        //When
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()
        ))).thenReturn(testingAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);

        UserPrincipal userPrincipal = modelMapper.map(mockedUser, UserPrincipal.class);

        when(staffRepository.findByEmailAddress(anyString())).thenReturn(Optional.of(mockedUser));
        UserPrincipal foundUser = (UserPrincipal) customUserDetailService.loadUserByUsername(loginRequest.getEmail());
        String actualToken = jwtTokenProvider.generateToken(foundUser);

        when(customUserDetailService.loadUserByUsername(anyString())).thenReturn(foundUser);
        when(jwtTokenProvider.generateToken(any(UserPrincipal.class))).thenReturn(actualToken);

        //Assert

        JwtTokenResponse jwtTokenResponse = staffService.login(loginRequest);
        verify(customUserDetailService, times(2)).loadUserByUsername(loginRequest.getEmail());
        verify(jwtTokenProvider, times(2)).generateToken(userPrincipal);
        verify(staffRepository, times(1)).findByEmailAddress(loginRequest.getEmail());

        assertNotNull(jwtTokenResponse);
        assertEquals(jwtTokenResponse.getJwtToken(), actualToken);
        assertEquals(jwtTokenResponse.getEmail(), loginRequest.getEmail());

    }
}
