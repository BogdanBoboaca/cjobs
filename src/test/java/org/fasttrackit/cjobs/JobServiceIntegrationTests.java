package org.fasttrackit.cjobs;

import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.exception.ResourceNotFoundException;
import org.fasttrackit.cjobs.service.JobService;
import org.fasttrackit.cjobs.steps.JobTestSteps;
import org.fasttrackit.cjobs.transfer.job.JobResponse;
import org.fasttrackit.cjobs.transfer.job.SaveJobRequest;
import org.junit.jupiter.api.Assertions;
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
    @Autowired
    private JobTestSteps jobTestSteps;

    @Test
    void createJob_whenValidRequest_thenJobIsCreated() {
        jobTestSteps.createJob();
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

    @Test
    void getJob_whenExistingJob_thenReturnJob (){
        JobResponse job = jobTestSteps.createJob();

        JobResponse response = jobService.getJob(job.getId());

        assertThat(response, notNullValue());
        assertThat(response.getId(), is(job.getId()));
        assertThat(response.getName(),is(job.getName()));
        assertThat(response.getDescription(),is(job.getDescription()));

    }

    @Test
    void getJob_whenNonExistingJob_thenThrowResourcesNotFoundException(){
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> jobService.getJob(99999));
    }

//    @Test
//    void getJobsByPartialName_whenExistingJob_thenReturnJob() {
//        Job partialNameJob = createJob();
//        Job response = jobService.getJobs(partialNameJob.getId(),)
//    }

    @Test
    void updateJob_whenValidRequest_thenReturnUpdatedJob() {
        JobResponse job = jobTestSteps.createJob();

        SaveJobRequest request = new SaveJobRequest();
        request.setName(job.getName() + "updated");
        request.setDescription(job.getDescription() + " updated");

        JobResponse updatedJob = jobService.updateJob(job.getId(), request);

        assertThat(updatedJob, notNullValue());
        assertThat(updatedJob.getId(), is(job.getId()));
        assertThat(updatedJob.getName(), is(request.getName()));
        assertThat(updatedJob.getDescription(), is(request.getDescription()));

    }

    @Test
    void deleteJob_whenExistingJob_thenJobDoesNotExistAnymore() {
        JobResponse job = jobTestSteps.createJob();

        jobService.deleteJob(job.getId());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> jobService.getJob(job.getId()));
    }


}
