package termiiapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import termiiapi.data.models.Staff;
import termiiapi.data.repositories.StaffRepository;
import termiiapi.dtos.JwtTokenResponse;
import termiiapi.dtos.LoginRequest;
import termiiapi.dtos.StaffDto;
import termiiapi.security.CustomUserDetailService;
import termiiapi.security.JwtTokenProvider;
import termiiapi.security.UserPrincipal;
import termiiapi.web.ecxeptions.DuplicateEmailAddressException;
import termiiapi.web.ecxeptions.RunTimeExceptionPlaceholder;
import termiiapi.web.ecxeptions.StaffNotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StaffRepository staffRepository;


    @Override
    public StaffDto addStaff(StaffDto staffDto) throws RunTimeExceptionPlaceholder, DuplicateEmailAddressException, StaffNotFoundException {

        if (staffDto == null){
            throw new IllegalArgumentException("Field cannot be null");
        }

        if (staffRepository.existsByStaffName(staffDto.getStaffName())){
            throw new RunTimeExceptionPlaceholder("Username already exist");
        }

        if (staffRepository.existsByEmailAddress(staffDto.getEmailAddress())){
            throw new DuplicateEmailAddressException("Email already exist");
        }

        Staff savedStaff = Staff.builder()
                .staffName(staffDto.getStaffName())
                .emailAddress(staffDto.getEmailAddress())
                .phoneNumber(staffDto.getPhoneNumber())
                .address(staffDto.getAddress())
                .build();

        saveStaff(savedStaff);

        return StaffDto.builder()
                .staffId(savedStaff.getStaffId())
                .emailAddress(savedStaff.getEmailAddress())
                .build();
    }

    private Staff saveStaff(Staff staff) throws StaffNotFoundException {

        if (staff == null){
            throw new StaffNotFoundException("Staff cannot be null");
        }

        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> getAllStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findByStaff(Long staffId) throws StaffNotFoundException {

        Optional<Staff> staff = staffRepository.findByStaffId(staffId);

        return staff.orElseThrow(()->
                new StaffNotFoundException("Staff does not exist"));
    }

    @Override
    public void deleteStaffById(Long staffId) throws StaffNotFoundException {
        Staff staffToDelete = findByStaff(staffId);
        staffRepository.delete(staffToDelete);
    }

    @Override
    public JwtTokenResponse login(LoginRequest loginRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserPrincipal userDetails = (UserPrincipal) customUserDetailService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenProvider.generateToken(userDetails);
        Staff user = internalDatabaseFindUserByEmail(loginRequest.getEmail());
        return new JwtTokenResponse(token , user.getEmailAddress());
    }

    private Staff internalDatabaseFindUserByEmail(String email) {
        return staffRepository.findByEmailAddress(email).orElse(null);
    }
}
