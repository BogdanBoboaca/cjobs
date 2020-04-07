package org.fasttrackit.cjobs;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.service.ApplicantService;
import org.fasttrackit.cjobs.steps.ApplicantTestSteps;
import org.fasttrackit.cjobs.transfer.applicant.SaveApplicantRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class ApplicantServiceIntegrationTests {

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private ApplicantTestSteps applicantTestSteps;

    @Test
    void createApplicant_whenValidRequest_ThenApplicantIsCreated(){
        applicantTestSteps.createApplicant();
    }

}
