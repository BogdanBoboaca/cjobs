package org.fasttrackit.cjobs;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.domain.Cv;
import org.fasttrackit.cjobs.persistance.CvRepository;
import org.fasttrackit.cjobs.service.ApplicantService;
import org.fasttrackit.cjobs.service.CvService;
import org.fasttrackit.cjobs.transfer.cv.AddCvsToApplicantRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CvServiceUnitTests {


    private CvService cvService;

    @Mock
    private CvRepository cvRepository;
    @Mock
    private ApplicantService applicantService;

    @Before
    public void setup() {
        cvService = new CvService(cvRepository, applicantService);
    }

    @Test
    public void addCvsToApplicant_whenNewApplicant_thenNoErrorIsFound() {
        when(cvRepository.findById(anyLong())).thenReturn(Optional.empty());

        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setFirstName("First Name");
        applicant.setLastName("Last Name");
        applicant.setPhoneNumber(732775504);
        applicant.setEmail("bogdan.m.boboca@gmail.com");

        when(applicantService.getApplicant(anyLong())).thenReturn(applicant);

        Cv cv = new Cv();
        cv.setId(5);
        cv.setAboutMe("About Me");
        cv.setWorkExperience("Work Experience");
        cv.setSkills("Skills");
        cv.setEducation("Education");

        when(cvRepository.save(any(Cv.class))).thenReturn(null);

        AddCvsToApplicantRequest request = new AddCvsToApplicantRequest();
        request.setCvIds(Collections.singletonList(cv.getId()));
        request.setApplicantID(applicant.getId());


        cvService.addCvsToApplicant(request);

        verify(cvRepository).findById(anyLong());
        verify(applicantService).getApplicant(anyLong());
        verify(cvRepository).save(any(Cv.class));
    }
}
