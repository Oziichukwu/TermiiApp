package termiiapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import termiiapi.data.models.Staff;
import termiiapi.data.models.Visitor;
import termiiapi.data.repositories.VisitorRepository;
import termiiapi.dtos.StaffDto;
import termiiapi.dtos.VisitorDto;
import termiiapi.web.ecxeptions.DuplicateEmailAddressException;
import termiiapi.web.ecxeptions.RunTimeExceptionPlaceholder;
import termiiapi.web.ecxeptions.VisitorNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService{

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public VisitorDto addVisitor(VisitorDto visitorDto) throws RunTimeExceptionPlaceholder, DuplicateEmailAddressException, VisitorNotFoundException {

        if (visitorDto == null){
            throw new IllegalArgumentException("Field cannot be null");
        }

        if (visitorRepository.existsByVisitorName(visitorDto.getVisitorName())){
            throw new RunTimeExceptionPlaceholder("Visitor name already exist");
        }

        if(visitorRepository.existsByEmailAddress(visitorDto.getEmailAddress())){
            throw new DuplicateEmailAddressException("Email already exist");
        }

        Visitor savedVisitor = Visitor.builder()
                .visitorName(visitorDto.getVisitorName())
                .emailAddress(visitorDto.getEmailAddress())
                .address(visitorDto.getAddress())
                .phoneNumber(visitorDto.getPhoneNumber())
                .build();

        saveVisitor(savedVisitor);

        return VisitorDto.builder()
                .visitorId(savedVisitor.getVisitorId())
                .emailAddress(savedVisitor.getEmailAddress())
                .build();

    }

    private Visitor saveVisitor(Visitor visitor) throws VisitorNotFoundException {

        if (visitor == null){
            throw new VisitorNotFoundException("Visitor cannot be null");
        }
        return visitorRepository.save(visitor);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor findByVisitor(Long visitorId) throws VisitorNotFoundException {

        Optional<Visitor> visitor = visitorRepository.findByVisitorId(visitorId);

        return visitor.orElseThrow(()->
                new VisitorNotFoundException("Visitor does not exist"));
    }

    @Override
    public void deleteVisitorById(Long visitorId) throws VisitorNotFoundException {

        Visitor visitorToDelete = findByVisitor(visitorId);
        visitorRepository.delete(visitorToDelete);
    }
}
