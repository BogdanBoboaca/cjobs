package org.fasttrackit.cjobs.transfer.cv;

import org.fasttrackit.cjobs.domain.Applicant;

import javax.validation.constraints.NotNull;

public class SaveCvRequest {

    private long applicantID;
    @NotNull
    private String aboutMe;
    @NotNull
    private String workExperience;
    @NotNull
    private String education;
    @NotNull
    private String skills;


    public long getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(long applicantID) {
        this.applicantID = applicantID;
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

    @Override
    public String toString() {
        return "SaveCvRequest{" +
                "aboutMe='" + aboutMe + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", education='" + education + '\'' +
                ", skills='" + skills + '\'' +
                '}';
    }
}
