package termiiapi.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import termiiapi.data.models.Staff;
import termiiapi.dtos.ApiResponse;
import termiiapi.dtos.StaffDto;
import termiiapi.services.StaffService;
import termiiapi.web.ecxeptions.DuplicateEmailAddressException;
import termiiapi.web.ecxeptions.RunTimeExceptionPlaceholder;
import termiiapi.web.ecxeptions.StaffNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping()
    public ResponseEntity<?> addStaff(@RequestBody StaffDto staffDto) {

        try {
            StaffDto staff = staffService.addStaff(staffDto);
            return new ResponseEntity<>(staff, HttpStatus.CREATED);
        }catch (DuplicateEmailAddressException | StaffNotFoundException | RunTimeExceptionPlaceholder e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllStaff(){

        List<Staff> staffs = staffService.getAllStaffs();
        return ResponseEntity.ok().body(staffs);
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<?> getStaff(@PathVariable Long staffId){

        try{
            return new ResponseEntity<>(staffService.findByStaff(staffId),HttpStatus.OK );
        }catch (StaffNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{staffId}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long staffId){
        try{
            staffService.deleteStaffById(staffId);
            return new ResponseEntity<>(new ApiResponse(true, "Staff Deleted Successfully"), HttpStatus.OK);
        }catch (StaffNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
