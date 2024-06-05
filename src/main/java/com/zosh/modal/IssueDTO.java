package com.zosh.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IssueDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private List<String> tags = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
    //private List<Comment> comments = new ArrayList<>();

    @Transient
    private String projectId;

}
