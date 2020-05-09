package org.fasttrackit.cjobs.service;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.domain.Cv;
import org.fasttrackit.cjobs.exception.ResourceNotFoundException;
import org.fasttrackit.cjobs.persistance.ApplicantRepository;
import org.fasttrackit.cjobs.persistance.CvRepository;
import org.fasttrackit.cjobs.transfer.cv.AddCvsToApplicantRequest;
import org.fasttrackit.cjobs.transfer.cv.CvResponse;
import org.fasttrackit.cjobs.transfer.cv.SaveCvRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    private final CvRepository cvRepository;
    private final ApplicantService applicantService;
    private final ApplicantRepository applicantRepository;

    @Autowired
    public CvService(CvRepository cvRepository, ApplicantService applicantService, ApplicantRepository applicantRepository) {
        this.cvRepository = cvRepository;
        this.applicantService = applicantService;
        this.applicantRepository = applicantRepository;
    }

    @Transactional
    public void addCvsToApplicant(AddCvsToApplicantRequest request) {
        LOGGER.info("Adding Cvs to Applicant: {}", request);

        Cv cv = cvRepository.findById(request.getApplicantID()).orElse(new Cv());

        if (cv.getApplicant() == null) {
            Applicant applicant = applicantService.getApplicant(request.getApplicantID());

            cv.setApplicant(applicant);
        }

        cvRepository.save(cv);

    }

    @Transactional
    public CvResponse createCv (SaveCvRequest request){
        LOGGER.info("Creating CV {}", request);

        Cv cv = cvRepository.findById(request.getApplicantID())
                .orElse(new Cv());

        if (cv.getApplicant() == null) {
            Applicant applicant = applicantService.getApplicant(request.getApplicantID());
            cv.setApplicant(applicant);
        }

        cv.setAboutMe(request.getAboutMe());
        cv.setWorkExperience(request.getWorkExperience());
        cv.setEducation(request.getEducation());
        cv.setSkills(request.getSkills());

        Cv saveCv = cvRepository.save(cv);

        return mapCvResponse(saveCv);
    }

    @Transactional
    public CvResponse getCv(long id){
        LOGGER.info("Retrieving CV {}", id);

        Cv cv = cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV " + id + "does not exist"));


        return mapCvResponse(cv);

    }

    private Cv findCv(long id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV " + id + " not found."));
    }
    
    private CvResponse mapCvResponse(Cv cv) {
        CvResponse cvDto = new CvResponse();
        cvDto.setId(cv.getId());
        cvDto.setAboutMe(cv.getAboutMe());
        cvDto.setWorkExperience(cv.getWorkExperience());
        cvDto.setEducation(cv.getEducation());
        cvDto.setSkills(cv.getSkills());

        return cvDto;
    }


    public CvResponse updateCv (long id, SaveCvRequest request){
        LOGGER.info("Updating CV {}: {}", id, request);

        Cv cv = findCv(id);

        BeanUtils.copyProperties(request, cv);

        Cv savedCv = cvRepository.save(cv);

        return mapCvResponse(savedCv);
    }


    public void deleteCv(long id){
        LOGGER.info("Deleting CV {}", id);
        cvRepository.deleteById(id);
    }
}
