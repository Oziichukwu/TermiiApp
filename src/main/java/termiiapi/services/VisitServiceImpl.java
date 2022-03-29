package termiiapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import termiiapi.data.models.Visit;
import termiiapi.data.repositories.VisitRepository;
import termiiapi.dtos.VisitDto;
import termiiapi.email.EmailUtil;

@Service
public class VisitServiceImpl implements VisitService{

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public VisitDto addVisit(VisitDto visitDto) {
        if (visitDto == null){
            throw new IllegalArgumentException("Field cannot be null");
        }

        Visit savedVisit = Visit.builder()
                .visitorId(visitDto.getVisitorId())
                .staffId(visitDto.getStaffId())
                .dateOfVisit(visitDto.getDateOfVisit())
                .reasonForVisit(visitDto.getReasonForVisit())
                .build();

        visitRepository.save(savedVisit);

        emailUtil.sendEmail("oziichukwu1@gmail.com", "New Visit","Visit Saved Successfully With details below "
                + visitDto.getVisitorId() + "\n"
                + visitDto.getStaffId() + "\n"
                + visitDto.getReasonForVisit() + "\n"
                + visitDto.getDateOfVisit() );



        return VisitDto.builder()
                .visitorId(savedVisit.getVisitorId())
                .staffId(savedVisit.getStaffId())
                .dateOfVisit(savedVisit.getDateOfVisit())
                .reasonForVisit(savedVisit.getReasonForVisit())
                .build();
    }
}
