package com.anirudh.jobtrackerapi.Respository;


import com.anirudh.jobtrackerapi.model.ApplicationStatus;
import com.anirudh.jobtrackerapi.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(ApplicationStatus status);
    List<JobApplication> findByUserUsername(String username);
    List<JobApplication> findByUserUsernameAndStatus(String username, ApplicationStatus status);
    Optional<JobApplication> findByIdAndUserUsername(Long id, String username);
    boolean existsByIdAndUserUsername(Long id, String username);
    // Additional query methods can be defined here if needed
}
