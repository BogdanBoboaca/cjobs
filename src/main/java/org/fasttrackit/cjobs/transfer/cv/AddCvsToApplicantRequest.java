package org.fasttrackit.cjobs.transfer.cv;

import java.util.List;

public class AddCvsToApplicantRequest {

    private long applicantID;
    private List<Long> cvIds;

    public long getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(long applicantID) {
        this.applicantID = applicantID;
    }

    public List<Long> getCvIds() {
        return cvIds;
    }

    public void setCvIds(List<Long> cvId) {
        this.cvIds = cvId;
    }

    //    public Long getCvId() {
//        return cvId;
//    }
//
//    public void setCvIds(Long cvId) {
//        this.cvId = cvId;
//    }

    @Override
    public String toString() {
        return "AddCvToApplicant{" +
                "applicantID=" + applicantID +
                ", cvIds=" + cvIds +
                '}';
    }
}
