package org.fasttrackit.cjobs;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.service.ApplicantService;
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

    @Test
    void createApplicant(){
        SaveApplicantRequest request = new SaveApplicantRequest();
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPhoneNumber(0732775504);
        request.setEmail("Email");

        Applicant applicant = applicantService.createApplicant(request);

        assertThat(applicant, notNullValue());
        assertThat(applicant.getId(), greaterThan(0L));
        assertThat(applicant.getFirstName(), is(request.getFirstName()));
        assertThat(applicant.getLastName(), is(request.getLastName()));
        assertThat(applicant.getPhoneNumber(), is(request.getPhoneNumber()));
        assertThat(applicant.getEmail(), is(request.getEmail()));
    }

}
