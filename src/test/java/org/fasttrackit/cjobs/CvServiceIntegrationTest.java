package org.fasttrackit.cjobs;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.service.CvService;
import org.fasttrackit.cjobs.steps.ApplicantTestSteps;
import org.fasttrackit.cjobs.steps.CvTestSteps;
import org.fasttrackit.cjobs.transfer.cv.AddCvsToApplicantRequest;
import org.fasttrackit.cjobs.transfer.cv.CvResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class CvServiceIntegrationTest {

    @Autowired
    private CvService cvService;
    @Autowired
    private ApplicantTestSteps applicantTestSteps;
    @Autowired
    private CvTestSteps cvTestSteps;


    @Test
    void addCvToApplicant_whenNewCv_thenCvIsCreated() {
        Applicant applicant = applicantTestSteps.createApplicant();
        CvResponse cv = cvTestSteps.createCv(applicant.getId());

        AddCvsToApplicantRequest cvRequest = new AddCvsToApplicantRequest();
        cvRequest.setApplicantID(applicant.getId());
        cvRequest.setCvIds(cv.getId());

         cvService.addCvsToApplicant(cvRequest);
    }

//    @Test
//    void createCv_whenValidRequest_thenCvIsCreated() {
//        cvTestSteps.createCv(applicant.getId());
//   }
}
