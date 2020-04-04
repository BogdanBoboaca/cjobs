package org.fasttrackit.cjobs.web;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.service.ApplicantService;
import org.fasttrackit.cjobs.transfer.applicant.GetApplicantRequest;
import org.fasttrackit.cjobs.transfer.applicant.SaveApplicantRequest;
import org.fasttrackit.cjobs.transfer.job.GetJobsRequest;
import org.fasttrackit.cjobs.transfer.job.SaveJobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping ("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping
    public ResponseEntity<Applicant> createApplicant (@Valid @RequestBody SaveApplicantRequest request) {
        Applicant applicant = applicantService.createApplicant(request);

        return new ResponseEntity<>(applicant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicant(@PathVariable long id) {
        Applicant applicant = applicantService.getApplicant(id);

        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplicant(@PathVariable long id, @Valid @RequestBody SaveApplicantRequest request) {
        Applicant applicant = applicantService.updateApplicant(id, request);

        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable long id) {
        applicantService.deleteApplicant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


