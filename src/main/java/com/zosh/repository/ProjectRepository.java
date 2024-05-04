package com.zosh.repository;

import com.zosh.modal.Project;
import com.zosh.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByName(String name);

//    List<Project> findByNameContaining(String name);
//
//    List<Project> findByNameContainingIgnoreCase(String name);
//
//    List<Project> findByNameLike(String name);
//
//    List<Project> findByNameLikeIgnoreCase(String name);
//
//    List<Project> findByNameStartingWith(String name);
//
//    List<Project> findByNameStartingWithIgnoreCase(String name);

    List<Project> findByOwner(User user);

    List<Project> findByNameContainingAndTeamContains(String partialName, User user);

    @Query("SELECT p From Project p join p.team t where t=:user")
    List<Project> findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user, User owner);
}
