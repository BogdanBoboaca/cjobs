package org.fasttrackit.cjobs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.exception.ResourceNotFoundException;
import org.fasttrackit.cjobs.persistance.ApplicantRepository;
import org.fasttrackit.cjobs.transfer.applicant.ApplicantResponse;
import org.fasttrackit.cjobs.transfer.applicant.SaveApplicantRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    private final ApplicantRepository applicantRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository, ObjectMapper objectMapper) {
        this.applicantRepository = applicantRepository;
        this.objectMapper = objectMapper;
    }

    public Applicant createApplicant(SaveApplicantRequest request){
        LOGGER.info("Creating applicant {}", request);


        Applicant applicant = objectMapper.convertValue(request, Applicant.class);

        return applicantRepository.save(applicant);
    }

    public Applicant getApplicant(long id){
        LOGGER.info("Retrieving applicant {}", id);

        return applicantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant " + id + " not found."));

    }


    public Applicant updateApplicant (long id, SaveApplicantRequest request){
        LOGGER.info("Updating applicant {}: {}", id, request);

        Applicant applicant = getApplicant(id);

        BeanUtils.copyProperties(request, applicant);

        return applicantRepository.save(applicant);
    }

    public void deleteApplicant(long id){
        LOGGER.info("Deleting Applicant {}", id);
        applicantRepository.deleteById(id);
    }
}
