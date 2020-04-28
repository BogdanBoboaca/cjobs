package org.fasttrackit.cjobs.steps;

import org.fasttrackit.cjobs.domain.Applicant;
import org.fasttrackit.cjobs.service.CvService;
import org.fasttrackit.cjobs.transfer.cv.CvResponse;
import org.fasttrackit.cjobs.transfer.cv.SaveCvRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class CvTestSteps {

    @Autowired
    private CvService cvService;

    public CvResponse createCv(Long id) {

        SaveCvRequest request = new SaveCvRequest();
        request.setAboutMe("A highly competent and enthusiastic university graduate ");
        request.setWorkExperience("Sales representative");
        request.setEducation("Bachelor's degree: BSc (Hons) Computer Science (Artificial Intelligence)");
        request.setSkills("Good understanding of Core Java");
        request.setApplicantID(id);

        CvResponse cv = cvService.createCv(request);

        assertThat(cv, notNullValue());
        assertThat(cv.getId(), greaterThan(0L));
        assertThat(cv.getAboutMe(), is(request.getAboutMe()));
        assertThat(cv.getWorkExperience(), is(request.getWorkExperience()));
        assertThat(cv.getEducation(), is(request.getEducation()));
        assertThat(cv.getSkills(), is(request.getSkills()));

        return cv;
    }
}
