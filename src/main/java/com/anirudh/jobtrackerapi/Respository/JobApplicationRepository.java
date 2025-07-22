package com.anirudh.jobtrackerapi.Respository;


import com.anirudh.jobtrackerapi.model.ApplicationStatus;
import com.anirudh.jobtrackerapi.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(ApplicationStatus status);
    // Additional query methods can be defined here if needed
}
