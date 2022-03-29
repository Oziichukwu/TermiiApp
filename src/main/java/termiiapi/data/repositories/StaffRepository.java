package termiiapi.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import termiiapi.data.models.Staff;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByEmailAddress(String email);

    Optional<Staff> findByStaffId(Long id);

    Boolean existsByStaffName(String name);

    Boolean existsByEmailAddress(String emailAddress);
}
