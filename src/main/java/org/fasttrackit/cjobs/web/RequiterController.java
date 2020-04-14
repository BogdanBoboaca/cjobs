package org.fasttrackit.cjobs.web;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.domain.Requiter;
import org.fasttrackit.cjobs.service.ApplicantService;
import org.fasttrackit.cjobs.service.RequiterService;
import org.fasttrackit.cjobs.transfer.applicant.SaveApplicantRequest;
import org.fasttrackit.cjobs.transfer.requiter.SaveRequiterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/requiters")
public class RequiterController {

    private final RequiterService requiterService;

    @Autowired
    public RequiterController(RequiterService requiterService) {
        this.requiterService = requiterService;
    }

    @PostMapping
    public ResponseEntity<Requiter> createRequiter (@Valid @RequestBody SaveRequiterRequest request) {
        Requiter requiter = requiterService.createRequiter(request);

        return new ResponseEntity<>(requiter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requiter> getRequiter(@PathVariable long id) {
        Requiter requiter = requiterService.getRequiter(id);

        return new ResponseEntity<>(requiter, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requiter> updateRequiter(@PathVariable long id, @Valid @RequestBody SaveRequiterRequest request) {
        Requiter requiter = requiterService.updateRequiter(id, request);

        return new ResponseEntity<>(requiter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequiter(@PathVariable long id) {
        requiterService.deleteRequiter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
