package termiiapi.services;

import termiiapi.data.models.Staff;
import termiiapi.dtos.JwtTokenResponse;
import termiiapi.dtos.LoginRequest;
import termiiapi.dtos.StaffDto;
import termiiapi.web.ecxeptions.DuplicateEmailAddressException;
import termiiapi.web.ecxeptions.RunTimeExceptionPlaceholder;
import termiiapi.web.ecxeptions.StaffNotFoundException;

import java.util.List;

public interface StaffService {

    StaffDto addStaff(StaffDto staffDto) throws RunTimeExceptionPlaceholder, DuplicateEmailAddressException, StaffNotFoundException;

    List<Staff> getAllStaffs();

    Staff findByStaff(Long staffId) throws StaffNotFoundException;

    void deleteStaffById(Long staffId) throws StaffNotFoundException;


    JwtTokenResponse login (LoginRequest loginRequest);
}
