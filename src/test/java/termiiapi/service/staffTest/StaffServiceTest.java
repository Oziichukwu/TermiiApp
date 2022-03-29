package termiiapi.service.staffTest;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import termiiapi.data.models.Staff;
import termiiapi.data.repositories.StaffRepository;
import termiiapi.services.StaffService;
import termiiapi.web.ecxeptions.DuplicateEmailAddressException;
import termiiapi.web.ecxeptions.StaffNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@Slf4j
@SpringBootTest
@Sql(scripts = {"/db/insert.sql"})
public class StaffServiceTest {

    @Autowired
    private StaffRepository staffRepository;


    @Test
    @DisplayName("Save a Staff Test")
    void saveAStaffToDatabaseTest(){

        Staff staff = new Staff();

        staff.setStaffId(1L);
        staff.setStaffName("merlin isle");
        staff.setEmailAddress("merlin@gmail.com");
        staff.setAddress("45 adebowale street yaba");
        staff.setPhoneNumber("0999999999");


        staffRepository.save(staff);
        log.info("Staff Saved -> {}", staff);
        assertThat(staff.getStaffId()).isNotNull();
        assertThat(staff.getStaffName()).isEqualTo("merlin isle");
        assertThat(staff.getAddress()).isEqualTo("45 adebowale street yaba");
        assertThat(staff.getEmailAddress()).isEqualTo("merlin@gmail.com");

    }


    @Test
    @DisplayName("Get All Staffs")
    void getAllStaffs(){

        List<Staff> staffs = staffRepository.findAll();
        assertThat(staffs.size()).isEqualTo(4);
        assertThat(staffs).isNotNull();
    }

    @Test
    @DisplayName("Get A Specific Staff Test")
    void get_a_specificStaffTest(){

        Staff staff = staffRepository.findByStaffId(2L).orElse(null);

        assertThat(staff).isNotNull();
        assertThat(staff.getStaffId()).isEqualTo(2);
        assertThat(staff.getStaffName()).isEqualTo("micheal brown");
        assertThat(staff.getPhoneNumber()).isEqualTo("888888999");
        assertThat(staff.getEmailAddress()).isEqualTo("micheal@gmail.com");
        assertThat(staff.getAddress()).isEqualTo("5 wale street island");

        log.info("Staff retrieved -> {}", staff);
    }

    @Test
    @DisplayName("Delete Staff ")
    void DeleteStaff(){

        Staff staff = staffRepository.findByStaffId(2L).orElse(null);

        assertThat(staff).isNotNull();
        assertThat(staff.getStaffId()).isEqualTo(2);
        assertThat(staff.getStaffName()).isEqualTo("micheal brown");
        assertThat(staff.getPhoneNumber()).isEqualTo("888888999");

        staffRepository.deleteById(2L);

        List<Staff> staffs = staffRepository.findAll();
        assertThat(staffs.size()).isEqualTo(3);

    }

}
