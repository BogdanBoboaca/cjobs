package org.fasttrackit.cjobs.persistance;

import org.fasttrackit.cjobs.domain.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByNameContaining(String partialName, Pageable pageable);

    @Query(value = "SELECT * FROM job WHERE `name` LIKE '%?%'", nativeQuery = true)
    Page<Job> findByPartialName(String partialName, Pageable pageable);
}
