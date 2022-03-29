package termiiapi.service.visitorTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import termiiapi.data.models.Visitor;
import termiiapi.data.repositories.VisitorRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
public class VisitorServiceTest {

    @Autowired
    private VisitorRepository visitorRepository;


    @Test
    @DisplayName("save a visitor Test")
    void saveAVisitorTest(){

        Visitor visitor = new Visitor();

        visitor.setVisitorId(28L);
        visitor.setVisitorName("sam smith");
        visitor.setEmailAddress("sam@gmail.com");
        visitor.setPhoneNumber("09178363989");
        visitor.setAddress("4 adebom street palmgroove");

        visitorRepository.save(visitor);

        assertThat(visitor.getVisitorId()).isNotNull();
        assertThat(visitor.getVisitorId()).isEqualTo(28L);
        assertThat(visitor.getVisitorName()).isEqualTo("sam smith");
        assertThat(visitor.getPhoneNumber()).isEqualTo("09178363989");
        assertThat(visitor.getAddress()).isEqualTo("4 adebom street palmgroove");

    }

    @Test
    @DisplayName("Get All Visitors Test")
    void getAllVisitorsTest(){

        List<Visitor> visitors = visitorRepository.findAll();

        assertThat(visitors.size()).isEqualTo(4);
        assertThat(visitors).isNotNull();
        log.info("These are all visitors -> {}", visitors);
    }

    @Test
    @DisplayName("Get A Specific Visitor Test")
    void getASpecificvisitorTest(){

        Visitor visitor = visitorRepository.findByVisitorId(28L).orElse(null);

        assertThat(visitor).isNotNull();
        assertThat(visitor.getVisitorId()).isEqualTo(28);
        assertThat(visitor.getVisitorName()).isEqualTo("sam smith");
        assertThat(visitor.getPhoneNumber()).isEqualTo("09178363989");
        assertThat(visitor.getEmailAddress()).isEqualTo("sam@gmail.com");
        assertThat(visitor.getAddress()).isEqualTo("4 adebom street palmgroove");
    }

    @Test
    @DisplayName("Delete A Visitor Test")
    void deleteAVisitorTest(){

        Visitor visitor = visitorRepository.findByVisitorId(28L).orElse(null);

        assertThat(visitor.getVisitorId()).isNotNull();
        assertThat(visitor.getVisitorName()).isEqualTo("sam smith");
        assertThat(visitor.getPhoneNumber()).isEqualTo("09178363989");

        visitorRepository.deleteById(28L);

        List<Visitor> visitors = visitorRepository.findAll();
        assertThat(visitors.size()).isEqualTo(3);

    }

}
