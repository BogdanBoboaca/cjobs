package org.fasttrackit.cjobs.persistance;

import org.fasttrackit.cjobs.domain.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<Cv, Long> {
}
