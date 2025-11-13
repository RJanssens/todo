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

    public Project getProjectByKey(String key) {
        return projectRepository.findByKey(key)
            .orElseThrow(() -> new RuntimeException("Project not found with key: " + key));
    }

    public Project createProject(CreateProjectRequest request) {
        if (projectRepository.existsByKey(request.getKey())) {
            throw new RuntimeException("Project key already exists: " + request.getKey());
        }

        User owner = userService.getUserById(request.getOwnerId());

        Project project = new Project();
        project.setName(request.getName());
        project.setKey(request.getKey().toUpperCase());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, CreateProjectRequest request) {
        Project project = getProjectById(id);

        if (!project.getKey().equals(request.getKey()) &&
            projectRepository.existsByKey(request.getKey())) {
            throw new RuntimeException("Project key already exists: " + request.getKey());
        }

        User owner = userService.getUserById(request.getOwnerId());

        project.setName(request.getName());
        project.setKey(request.getKey().toUpperCase());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = getProjectById(id);
        projectRepository.delete(project);
    }
}
