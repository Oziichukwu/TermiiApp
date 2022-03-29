package termiiapi.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import termiiapi.dtos.VisitDto;
import termiiapi.services.VisitService;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @PostMapping()
    public ResponseEntity<?> addVisit(@RequestBody VisitDto visitDto){

        try {
            VisitDto visit = visitService.addVisit(visitDto);
            return new ResponseEntity<>(visit, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
