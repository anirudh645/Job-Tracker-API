package com.anirudh.jobtrackerapi.Service;

import com.anirudh.jobtrackerapi.model.ApplicationStatus;
import com.anirudh.jobtrackerapi.model.JobApplication;
import com.anirudh.jobtrackerapi.Respository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }
    public JobApplication createApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }
    public JobApplication getApplicationById(Long id) {
        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }
    public JobApplication updateApplication(JobApplication jobApplication) {
        return jobApplicationRepository.findById(jobApplication.getId())
                .map(existingApplication -> {
                    existingApplication.setCompanyName(jobApplication.getCompanyName());
                    existingApplication.setJobTitle(jobApplication.getJobTitle());
                    existingApplication.setStatus(jobApplication.getStatus());
                    existingApplication.setDateApplied(jobApplication.getDateApplied());
                    existingApplication.setJobUrl(jobApplication.getJobUrl());
                    return jobApplicationRepository.save(existingApplication);
                }).orElseThrow(() -> new RuntimeException("Application not found with id: " + jobApplication.getId()));
    }
    public boolean deleteApplication(Long id) {
        if (!jobApplicationRepository.existsById(id)) {
            return false;
        }
        jobApplicationRepository.deleteById(id);
        return true;
    }
    public List<JobApplication> getApplicationsByStatus(ApplicationStatus status) {
        return jobApplicationRepository.findByStatus(status);
    }
}