package com.zosh.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "issue_tags",
//            joinColumns = @JoinColumn(name = "issue_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    // Getter for projectId
    public Long getProjectId() {
        return project != null ? project.getId() : null;
    }
}
