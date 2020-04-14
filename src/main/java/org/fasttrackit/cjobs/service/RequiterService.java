package org.fasttrackit.cjobs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.domain.Requiter;
import org.fasttrackit.cjobs.exception.ResourceNotFoundException;
import org.fasttrackit.cjobs.persistance.RequiterRepository;
import org.fasttrackit.cjobs.transfer.applicant.SaveApplicantRequest;
import org.fasttrackit.cjobs.transfer.requiter.SaveRequiterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequiterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    private final RequiterRepository requiterRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public RequiterService(RequiterRepository requiterRepository, ObjectMapper objectMapper) {
        this.requiterRepository = requiterRepository;
        this.objectMapper = objectMapper;
    }


    public Requiter createRequiter (SaveRequiterRequest request){
        LOGGER.info("Creating Requiter {}", request);


        Requiter requiter = objectMapper.convertValue(request, Requiter.class);

        return requiterRepository.save(requiter);
    }

    public Requiter getRequiter(long id){
        LOGGER.info("Retrieving Requiter {}", id);

        return requiterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requiter " + id + " not found."));

    }


    public Requiter updateRequiter (long id, SaveRequiterRequest request){
        LOGGER.info("Updating Requiter {}: {}", id, request);

        Requiter requiter = getRequiter(id);

        BeanUtils.copyProperties(request, request);

        return requiterRepository.save(requiter);
    }

    public void deleteRequiter(long id){
        LOGGER.info("Deleting Requiter {}", id);
        requiterRepository.deleteById(id);
    }
}
