package org.fasttrackit.cjobs.service;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.domain.Cv;
import org.fasttrackit.cjobs.persistance.CvRepository;
import org.fasttrackit.cjobs.transfer.cv.AddCvsToApplicantRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class CvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    private final CvRepository cvRepository;
    private final ApplicantService applicantService;

    @Autowired
    public CvService(CvRepository cvRepository, ApplicantService applicantService) {
        this.cvRepository = cvRepository;
        this.applicantService = applicantService;
    }

    @Transactional
    public void addCvsToApplicant(AddCvsToApplicantRequest request) {
        LOGGER.info("Adding Cvs to Applicant: {}", request);

        Cv cv = cvRepository.findById(request.getApplicantID()).orElse(new Cv());

        if (cv.getApplicant() == null) {
            Applicant applicant = applicantService.getApplicant(request.getApplicantID());

            cv.setApplicant((Set<Applicant>) applicant);
        }



        cvRepository.save(cv);

    }
}
