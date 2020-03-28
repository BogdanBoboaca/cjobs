package org.fasttrackit.cjobs;

import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.service.JobService;
import org.fasttrackit.cjobs.transfer.job.SaveJobRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class JobServiceIntegrationTests {

    @Autowired
    private JobService jobService;

    @Test
    void createJob_whenValidRequest_thenJobIsCreated() {
        SaveJobRequest request = new SaveJobRequest();
        request.setName("Java Developer");
        request.setDescription("Analyze requirements, design solutions and develop software artefact's");

        Job job = jobService.createJob(request);

        assertThat(job, notNullValue());
        assertThat(job.getId(), greaterThan(0L));
        assertThat(job.getName(), is(request.getName()));
        assertThat(job.getDescription(), is(request.getDescription()));
    }

    @Test
    void createJob_whenMissingName_thenExceptionIsThrown() {
        SaveJobRequest request = new SaveJobRequest();
        request.setDescription("Analyze requirements, design solutions and develop software artefact's");

        try {
            jobService.createJob(request);
        } catch (Exception e) {
            assertThat(e, notNullValue());
            assertThat("Unexpected exception type. ", e instanceof TransactionSystemException);
        }


    }
}
