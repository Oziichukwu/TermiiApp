package termiiapi.services;

import termiiapi.data.models.Staff;
import termiiapi.data.models.Visitor;
import termiiapi.dtos.StaffDto;
import termiiapi.dtos.VisitorDto;
import termiiapi.web.ecxeptions.DuplicateEmailAddressException;
import termiiapi.web.ecxeptions.RunTimeExceptionPlaceholder;
import termiiapi.web.ecxeptions.VisitorNotFoundException;

import java.util.List;

public interface VisitorService {

    VisitorDto addVisitor(VisitorDto visitorDto) throws RunTimeExceptionPlaceholder, DuplicateEmailAddressException, VisitorNotFoundException;

    List<Visitor> getAllVisitors();

    Visitor findByVisitor(Long visitorId) throws VisitorNotFoundException;

    void deleteVisitorById(Long visitorId) throws VisitorNotFoundException;
}
