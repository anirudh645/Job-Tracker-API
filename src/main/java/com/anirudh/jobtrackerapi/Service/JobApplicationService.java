package com.anirudh.jobtrackerapi.Service;

import com.anirudh.jobtrackerapi.model.ApplicationStatus;
import com.anirudh.jobtrackerapi.model.JobApplication;
import com.anirudh.jobtrackerapi.model.User;
import com.anirudh.jobtrackerapi.Respository.JobApplicationRepository;
import com.anirudh.jobtrackerapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository, UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }

    public List<JobApplication> getAllApplications(String username) {
        return jobApplicationRepository.findByUserUsername(username);
    }

    public JobApplication createApplication(String username, JobApplication jobApplication) {
        User currentUser = getRequiredUser(username);
        jobApplication.setId(null);
        jobApplication.setUser(currentUser);
        return jobApplicationRepository.save(jobApplication);
    }

    public JobApplication getApplicationById(String username, Long id) {
        return jobApplicationRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }

    public JobApplication updateApplication(String username, Long id, JobApplication jobApplication) {
        return jobApplicationRepository.findByIdAndUserUsername(id, username)
                .map(existingApplication -> {
                    existingApplication.setCompanyName(jobApplication.getCompanyName());
                    existingApplication.setJobTitle(jobApplication.getJobTitle());
                    existingApplication.setStatus(jobApplication.getStatus());
                    existingApplication.setDateApplied(jobApplication.getDateApplied());
                    existingApplication.setJobUrl(jobApplication.getJobUrl());
                    return jobApplicationRepository.save(existingApplication);
                }).orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }

    public boolean deleteApplication(String username, Long id) {
        if (!jobApplicationRepository.existsByIdAndUserUsername(id, username)) {
            return false;
        }
        jobApplicationRepository.deleteById(id);
        return true;
    }

    public List<JobApplication> getApplicationsByStatus(String username, ApplicationStatus status) {
        return jobApplicationRepository.findByUserUsernameAndStatus(username, status);
    }

    private User getRequiredUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}