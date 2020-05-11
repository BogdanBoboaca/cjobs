package org.fasttrackit.cjobs.service;

import org.fasttrackit.cjobs.domain.Cv;
import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.domain.Requiter;
import org.fasttrackit.cjobs.exception.ResourceNotFoundException;
import org.fasttrackit.cjobs.persistance.JobRepository;
import org.fasttrackit.cjobs.transfer.job.AddJobToRequiterRequest;
import org.fasttrackit.cjobs.transfer.job.GetJobsRequest;
import org.fasttrackit.cjobs.transfer.job.JobResponse;
import org.fasttrackit.cjobs.transfer.job.SaveJobRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    //Inversion of control (IoC)
    private final JobRepository jobRepository;
    private final RequiterService requiterService;

    //Dependency Injection (from IoC Container)
    @Autowired
    public JobService(JobRepository jobRepository, RequiterService requiterService) {
        this.jobRepository = jobRepository;
        this.requiterService = requiterService;
    }

    @Transactional
    public JobResponse createJob(SaveJobRequest request){
        LOGGER.info("Creating job {}", request);
        Job job = new Job();
        job.setName(request.getName());
        job.setDescription(request.getDescription());

        Job savedJob = jobRepository.save(job);

        return mapJobResponse(savedJob);

//        Job job = jobRepository.findById(request.getRequiterId())
//                .orElse(new Job());
//
//        if (job.getRequiter() == null) {
//            Requiter requiter = requiterService.getRequiter(request.getRequiterId());
//            job.setRequiter(requiter);
//        }
//
//        job.setName(request.getName());
//        job.setDescription(request.getDescription());
//
//        for (Long id : jobToRequiterRequest.getJobIds()) {
//           Requiter requiter = requiterService.getRequiter(id);
//           job.setRequiter(requiter);
//       }
//
//        Job saveJob = jobRepository.save(job);
//
//        return mapJobResponse(saveJob);

    }

    @Transactional
    public void addJobToRequiter(AddJobToRequiterRequest request) {
        LOGGER.info("Adding Jobs to Requiter: {}", request);

        Job job = jobRepository.findById(request.getRequiterId())
                .orElse(new Job());

        if (job.getRequiter() == null) {
            Requiter requiter = requiterService.getRequiter(request.getRequiterId());
            job.setRequiter(requiter);
        }

        for (Long id : request.getJobIds()) {
            Requiter requiter = requiterService.getRequiter(id);
            job.setRequiter(requiter);
        }

        jobRepository.save(job);

    }

    private JobResponse mapJobResponse(Job job1) {
        JobResponse jobDto = new JobResponse();
        jobDto.setId(job1.getId());
        jobDto.setName(job1.getName());
        jobDto.setDescription(job1.getDescription());
        return jobDto;
    }

    @Transactional
    public JobResponse getJob(long id){
        LOGGER.info("Retrieving job {}", id);

        Job job = findJob(id);
        return mapJobResponse(job);

    }

    public Job findJob(long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job " + id + " not found."));
    }

    @Transactional
    public Page<JobResponse> getJobs(GetJobsRequest request, Pageable pageable){
        LOGGER.info("Search jobs: {}", request);

        Page<Job> jobsPage;

        if (request != null){
            if(request.getPartialName() != null){
                jobsPage = jobRepository.findByNameContaining(request.getPartialName(),pageable);
            }else {
                jobsPage = jobRepository.findAll(pageable);
            }
        }else {
            jobsPage = jobRepository.findAll(pageable);
        }

        List<JobResponse> jobDtos = new ArrayList<>();

        for (Job job : jobsPage.getContent()) {
            JobResponse jobDto = mapJobResponse(job);

            jobDtos.add(jobDto);
        }
        return new PageImpl<>(jobDtos, pageable, jobsPage.getTotalElements());

    }

    @Transactional
    public JobResponse updateJob (long id, SaveJobRequest request){
        LOGGER.info("Updating job {}: {}", id, request);

        Job job = findJob(id);

        BeanUtils.copyProperties(request, job);

        Job savedJob = jobRepository.save(job);
        return mapJobResponse(savedJob);
    }

    public void deleteJob(long id){
        LOGGER.info("Deleting Job {}", id);
        jobRepository.deleteById(id);
    }


}
