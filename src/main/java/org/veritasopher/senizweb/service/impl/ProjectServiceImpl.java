package org.veritasopher.senizweb.service.impl;

import org.springframework.stereotype.Service;
import org.veritasopher.senizweb.model.Project;
import org.veritasopher.senizweb.model.ProjectFile;
import org.veritasopher.senizweb.repository.ProjectFileRepository;
import org.veritasopher.senizweb.repository.ProjectRepository;
import org.veritasopher.senizweb.service.ProjectService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Project Service Implementation
 *
 * @author Yepeng Ding
 * @date 12/22/2020
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectRepository projectRepository;

    @Resource
    private ProjectFileRepository projectFileRepository;


    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public List<Project> findAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public List<ProjectFile> findFileByProjectId(Long projectId) {
        return projectFileRepository.findByProjectId(projectId);
    }
}
