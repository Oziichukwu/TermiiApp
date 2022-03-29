package termiiapi.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import termiiapi.data.models.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
