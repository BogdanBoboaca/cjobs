package org.fasttrackit.cjobs.transfer.job;

public class GetJobRequest {

    private String partialName;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    @Override
    public String toString() {
        return "GetJobRequest{" +
                "partialName='" + partialName + '\'' +
                '}';
    }
}
