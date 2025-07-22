package com.anirudh.jobtrackerapi.Controller;

import com.anirudh.jobtrackerapi.Respository.JobApplicationRepository;
import com.anirudh.jobtrackerapi.Service.JobApplicationService;
import com.anirudh.jobtrackerapi.model.ApplicationStatus;
import com.anirudh.jobtrackerapi.model.JobApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping
    public List<JobApplication> getAllApplications() {
        log.info("Getting all job applications");
        return jobApplicationService.getAllApplications();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobApplication createApplication(@RequestBody JobApplication jobApplication) {
        log.info("Creating a new job application for company: {}", jobApplication.getCompanyName());
        return jobApplicationService.createApplication(jobApplication);
    }
    @GetMapping("/{id}")
    public JobApplication getApplicationById(@PathVariable Long id) {
        log.info("Getting job application with id: {}", id);
        return jobApplicationService.getApplicationById(id);
    }
    @PutMapping("/{id}")
    public JobApplication updateApplication(@PathVariable Long id, @RequestBody JobApplication updatedApplication) {
        log.info("Updating job application with id: {}", id);
        return jobApplicationService.updateApplication(
                new JobApplication(id, updatedApplication.getCompanyName(), updatedApplication.getJobTitle(),
                        updatedApplication.getStatus(), updatedApplication.getDateApplied(), updatedApplication.getJobUrl()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        log.info("Deleting job application with id: {}", id);
        boolean wasDeleted = jobApplicationService.deleteApplication(id);
        if (wasDeleted) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No Application with the given ID found."); // HTTP 404 Not Found
        }
    }
    @GetMapping(params = "status")
    public List<JobApplication> getApplicationsByStatus(@RequestParam ApplicationStatus status) {
        return  jobApplicationService.getApplicationsByStatus(status);
    }
}
