package org.fasttrackit.cjobs.persistance;

import org.fasttrackit.cjobs.domain.Requiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiterRepository extends JpaRepository<Requiter, Long> {
}
