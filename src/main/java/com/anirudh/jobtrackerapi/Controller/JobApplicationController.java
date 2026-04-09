package com.anirudh.jobtrackerapi.Controller;

import com.anirudh.jobtrackerapi.Service.JobApplicationService;
import com.anirudh.jobtrackerapi.model.ApplicationStatus;
import com.anirudh.jobtrackerapi.model.JobApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping()
    public List<JobApplication> getAllApplications(Authentication authentication) {
        String username = authentication.getName();
        log.info("Getting all job applications for user: {}", username);
        return jobApplicationService.getAllApplications(username);
    }

    @GetMapping("/{id}")
    public JobApplication getApplicationById(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        log.info("Getting job application with id: {} for user: {}", id, username);
        return jobApplicationService.getApplicationById(username, id);
    }

    @PostMapping({"", "/create"})
    public ResponseEntity<Void> createApplication(@RequestBody JobApplication jobApplication, Authentication authentication) {
        String username = authentication.getName();
        log.info("Creating a new job application for company: {}", jobApplication.getCompanyName());
        jobApplicationService.createApplication(username, jobApplication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public JobApplication updateApplication(@PathVariable Long id, @RequestBody JobApplication updatedApplication, Authentication authentication) {
        String username = authentication.getName();
        log.info("Updating job application with id: {}", id);
        return jobApplicationService.updateApplication(username, id, updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        log.info("Deleting job application with id: {}", id);
        boolean wasDeleted = jobApplicationService.deleteApplication(username, id);
        if (wasDeleted) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No Application with the given ID found."); // HTTP 404 Not Found
        }
    }

    @GetMapping(params = "status")
    public List<JobApplication> getApplicationsByStatus(@RequestParam ApplicationStatus status, Authentication authentication) {
        String username = authentication.getName();
        return jobApplicationService.getApplicationsByStatus(username, status);
    }
}
