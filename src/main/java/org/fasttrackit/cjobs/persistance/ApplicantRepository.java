package org.fasttrackit.cjobs.persistance;

import org.fasttrackit.cjobs.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
