package com.zosh.repository;

import com.zosh.modal.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    public List<Issue> findByProject_Id(Long projectId);
}
