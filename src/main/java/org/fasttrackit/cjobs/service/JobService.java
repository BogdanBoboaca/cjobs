package org.fasttrackit.cjobs.service;

import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.exception.ResourceNotFoundException;
import org.fasttrackit.cjobs.persistance.JobRepository;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    //Inversion of control (IoC)
    private final JobRepository jobRepository;

    //Dependency Injection (from IoC Container)
    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Transactional
    public JobResponse createJob(SaveJobRequest request){
        LOGGER.info("Creating job {}", request);
        Job job = new Job();
        job.setName(request.getName());
        job.setDescription(request.getDescription());

        Job savedJob = jobRepository.save(job);

        return mapJobResponse(savedJob);

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
    public Page<JobResponse> getJobs(long jobId, Pageable pageable) {
        LOGGER.info("Retrieving Jobs from Requiters {}", jobId);

        Page<Job> jobsPage = jobRepository.findByRequiterId(jobId, pageable);

        List<JobResponse> jobsDtos = new ArrayList<>();

        for (Job jobs : jobsPage.getContent()) {
            JobResponse dto = mapJobResponse(jobs);

            jobsDtos.add(dto);
        }

        return new PageImpl<>(jobsDtos, pageable, jobsPage.getTotalElements());
    }

//    public Page<Job> getJobs(GetJobsRequest request, Pageable pageable){
//        LOGGER.info("Search jobs: {}", request);
//
//        if (request != null){
//            if(request.getPartialName() != null){
//                return jobRepository.findByNameContaining(request.getPartialName(),pageable);
//            }
//        }
//
//        return jobRepository.findAll(pageable);
//    }

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
