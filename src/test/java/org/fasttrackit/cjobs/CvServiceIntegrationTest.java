package org.fasttrackit.cjobs;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.service.CvService;
import org.fasttrackit.cjobs.steps.ApplicantTestSteps;
import org.fasttrackit.cjobs.transfer.cv.AddCvsToApplicantRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CvServiceIntegrationTest {

    @Autowired
    private CvService cvService;
    @Autowired
    private ApplicantTestSteps applicantTestSteps;


    @Test
    void addCvToApplicant_whenNewCv_thenCvIsCreated() {
        Applicant applicant = applicantTestSteps.createApplicant();

        AddCvsToApplicantRequest cvRequest = new AddCvsToApplicantRequest();
        cvRequest.setApplicantID(applicant.getId());

        cvService.addCvsToApplicant(cvRequest);
    }
}
