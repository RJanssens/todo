package com.projectmanager.app.service;

import com.projectmanager.app.dto.CreateProjectRequest;
import com.projectmanager.app.model.Project;
import com.projectmanager.app.model.User;
import com.projectmanager.app.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    public Project getProjectByKey(String projectKey) {
        return projectRepository.findByProjectKey(projectKey)
            .orElseThrow(() -> new RuntimeException("Project not found with key: " + projectKey));
    }

    public Project createProject(CreateProjectRequest request) {
        if (projectRepository.existsByProjectKey(request.getProjectKey())) {
            throw new RuntimeException("Project key already exists: " + request.getProjectKey());
        }

        User owner = userService.getUserById(request.getOwnerId());

        Project project = new Project();
        project.setName(request.getName());
        project.setProjectKey(request.getProjectKey().toUpperCase());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, CreateProjectRequest request) {
        Project project = getProjectById(id);

        if (!project.getProjectKey().equals(request.getProjectKey()) &&
            projectRepository.existsByProjectKey(request.getProjectKey())) {
            throw new RuntimeException("Project key already exists: " + request.getProjectKey());
        }

        User owner = userService.getUserById(request.getOwnerId());

        project.setName(request.getName());
        project.setProjectKey(request.getProjectKey().toUpperCase());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }
}
