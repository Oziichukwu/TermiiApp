package termiiapi.data.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long visitId;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "visitor_id")
    private Long visitorId;

    @Column(name = "date_of_visit")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfVisit;

    @Column(name = "reason_for_visit")
    @NotBlank(message = "field cannot be blank")
    private String reasonForVisit;
}
