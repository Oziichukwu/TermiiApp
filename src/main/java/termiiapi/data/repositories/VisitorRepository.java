package termiiapi.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import termiiapi.data.models.Staff;
import termiiapi.data.models.Visitor;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    Optional<Visitor> findByVisitorId(Long id);

    Boolean existsByVisitorName(String name);

    Boolean existsByEmailAddress(String emailAddress);
}
