package org.fasttrackit.cjobs.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cv {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    private Set <Applicant> applicant = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set <Applicant> getApplicant() {
        return applicant;
    }

    public void setApplicant(Set<Applicant> applicant) {
        this.applicant = applicant;
    }
}
