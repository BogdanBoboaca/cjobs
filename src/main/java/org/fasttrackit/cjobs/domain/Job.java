package org.fasttrackit.cjobs.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Job {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Requiter requiter;
    @NotNull
    private String name;
    @NotNull
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Requiter getRequiter() {
        return requiter;
    }

    public void setRequiter(Requiter requiter) {
        this.requiter = requiter;
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
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
