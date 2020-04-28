package org.fasttrackit.cjobs.web;

import org.fasttrackit.cjobs.domain.Cv;
import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.service.CvService;
import org.fasttrackit.cjobs.transfer.cv.CvResponse;
import org.fasttrackit.cjobs.transfer.cv.SaveCvRequest;
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
@RequestMapping("/cvs")
public class CvController {

    private final CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping()
    public ResponseEntity<Void> createCv(@Valid @RequestBody SaveCvRequest request) {
        cvService.createCv(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getCv(@PathVariable long id) {
        cvService.getCv(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CvResponse> updateCv(@PathVariable long id, @Valid @RequestBody SaveCvRequest request) {
        CvResponse cvResponse = cvService.updateCv(id, request);

        return new ResponseEntity<>(cvResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCv(@PathVariable long id) {
        cvService.deleteCv(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
