package org.fasttrackit.cjobs.transfer.cv;

public class CvResponse {

    private long id;
    private String aboutMe;
    private String workExperience;
    private String education;
    private String skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "CvResponse{" +
                "id=" + id +
                ", aboutMe='" + aboutMe + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", education='" + education + '\'' +
                ", skills='" + skills + '\'' +
                '}';
    }
}
