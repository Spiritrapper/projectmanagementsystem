package com.zosh.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRequest {

    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String priority;
    private LocalDate dueDate;

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for projectId
    public Long getProjectId() {
        return projectId;
    }
}
