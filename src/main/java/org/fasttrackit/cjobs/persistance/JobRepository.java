package org.fasttrackit.cjobs.persistance;

import org.fasttrackit.cjobs.domain.Job;
import org.fasttrackit.cjobs.domain.Requiter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByNameContaining(String partialName, Pageable pageable);
    Page<Job> findByRequiterId(long jobId, Pageable pageable);


}
