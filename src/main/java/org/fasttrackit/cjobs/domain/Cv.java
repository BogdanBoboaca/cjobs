package org.fasttrackit.cjobs.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cv {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Applicant applicant;

    @NotNull
    private String aboutMe;
    @NotNull
    private String workExperience;
    @NotNull
    private String education;
    @NotNull
    private String skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @Override
    public String toString() {
        return "Cv{" +
                "id=" + id +
                ", applicant=" + applicant +
                ", aboutMe='" + aboutMe + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", education='" + education + '\'' +
                ", skills='" + skills + '\'' +
                '}';
    }
}
