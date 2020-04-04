package org.fasttrackit.cjobs.service;

import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.exception.ResourceNotFoundException;
import org.fasttrackit.cjobs.persistance.JobRepository;
import org.fasttrackit.cjobs.transfer.job.GetJobsRequest;
import org.fasttrackit.cjobs.transfer.job.SaveJobRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Job createJob(SaveJobRequest request){
        LOGGER.info("Creating job {}", request);
        Job job = new Job();
        job.setName(request.getName());
        job.setDescription(request.getDescription());

       return jobRepository.save(job);
    }

    public Job getJob(long id){
        LOGGER.info("Retrieving job {}", id);

        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job " + id + " not found."));

    }

    public Page<Job> getJobs(GetJobsRequest request, Pageable pageable){
        LOGGER.info("Search jobs: {}", request);

        if (request != null){
            if(request.getPartialName() != null){
                return jobRepository.findByNameContaining(request.getPartialName(),pageable);
            }
        }

        return jobRepository.findAll(pageable);
    }

    public Job updateJob (long id, SaveJobRequest request){
        LOGGER.info("Updating job {}: {}", id, request);

        Job job = getJob(id);

        BeanUtils.copyProperties(request, job);

        return jobRepository.save(job);
    }

    public void deleteJob(long id){
        LOGGER.info("Deleting Job {}", id);
        jobRepository.deleteById(id);
    }


}
