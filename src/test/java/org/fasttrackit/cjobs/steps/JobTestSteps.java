package org.fasttrackit.cjobs.steps;

import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.service.JobService;
import org.fasttrackit.cjobs.transfer.job.JobResponse;
import org.fasttrackit.cjobs.transfer.job.SaveJobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class JobTestSteps {

    @Autowired
    private JobService jobService;

    public JobResponse createJob() {
        SaveJobRequest request = new SaveJobRequest();
        request.setName("Java Developer");
        request.setDescription("Analyze requirements, design solutions and develop software artefact's");

        JobResponse job = jobService.createJob(request);

        assertThat(job, notNullValue());
        assertThat(job.getId(), greaterThan(0L));
        assertThat(job.getName(), is(request.getName()));
        assertThat(job.getDescription(), is(request.getDescription()));

        return job;
    }
}
