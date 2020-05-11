package org.fasttrackit.cjobs.transfer.job;

import javax.validation.constraints.NotNull;

public class SaveJobRequest {

    private long requiterId;
    @NotNull
    private String name;
    @NotNull
    private String description;

    public long getRequiterId() {
        return requiterId;
    }

    public void setRequiterId(long requiterId) {
        this.requiterId = requiterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SaveJobRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
