package termiiapi.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import termiiapi.data.models.Visitor;
import termiiapi.dtos.ApiResponse;
import termiiapi.dtos.VisitorDto;
import termiiapi.services.VisitorService;
import termiiapi.web.ecxeptions.DuplicateEmailAddressException;
import termiiapi.web.ecxeptions.RunTimeExceptionPlaceholder;
import termiiapi.web.ecxeptions.VisitorNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping()
    public ResponseEntity<?> addVisitor(@RequestBody VisitorDto visitorDto) {

        try {
            VisitorDto visitor = visitorService.addVisitor(visitorDto);
            return new ResponseEntity<>(visitor, HttpStatus.CREATED);
        }catch (DuplicateEmailAddressException |  RunTimeExceptionPlaceholder | VisitorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllVisitors(){
        List<Visitor> visitors = visitorService.getAllVisitors();
        return ResponseEntity.ok().body(visitors);

    }

    @GetMapping("/{visitorId}")
    public ResponseEntity<?> getVisitor(@PathVariable Long visitorId){

        try{
            return new ResponseEntity<>(visitorService.findByVisitor(visitorId), HttpStatus.OK);
        }catch (VisitorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{visitorId}")
    public ResponseEntity<?> deleteVisitor(@PathVariable Long visitorId){
        try{
            visitorService.deleteVisitorById(visitorId);
            return new ResponseEntity<>(new ApiResponse(true, "Visitor Deleted Successfully"), HttpStatus.OK);
        }catch (VisitorNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
