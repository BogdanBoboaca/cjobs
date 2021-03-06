package org.fasttrackit.cjobs.web;

import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.service.JobService;
import org.fasttrackit.cjobs.transfer.job.AddJobToRequiterRequest;
import org.fasttrackit.cjobs.transfer.job.GetJobsRequest;
import org.fasttrackit.cjobs.transfer.job.JobResponse;
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
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody SaveJobRequest request) {
        JobResponse job = jobService.createJob(request);

        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<JobResponse> addJobToRequiter(@Valid @RequestBody AddJobToRequiterRequest request) {
        jobService.addJobToRequiter(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJob(@PathVariable long id) {
        JobResponse job = jobService.getJob(id);

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<JobResponse>> getJobs(GetJobsRequest id, Pageable pageable) {
        Page<JobResponse> jobs = jobService.getJobs(id, pageable);

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable long id, @Valid @RequestBody SaveJobRequest request) {
        JobResponse job = jobService.updateJob(id, request);

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable long id) {
        jobService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
