package org.fasttrackit.cjobs.transfer.job;

import java.util.List;

public class AddJobToRequiterRequest {

    private long requiterId;
    private List<Long> jobIds;

    public long getRequiterId() {
        return requiterId;
    }

    public void setRequiterId(long requiterId) {
        this.requiterId = requiterId;
    }

    public List<Long> getJobIds() {
        return jobIds;
    }

    public void setJobIds(List<Long> jobIds) {
        this.jobIds = jobIds;
    }

    @Override
    public String toString() {
        return "AddJobToRequiterRequest{" +
                "requiterId=" + requiterId +
                ", jobIds=" + jobIds +
                '}';
    }
}
