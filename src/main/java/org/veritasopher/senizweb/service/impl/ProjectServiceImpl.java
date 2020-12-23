package org.veritasopher.senizweb.service.impl;

import org.springframework.stereotype.Service;
import org.veritasopher.seniz.config.Info;
import org.veritasopher.seniz.core.model.SourceFile;
import org.veritasopher.senizweb.model.Project;
import org.veritasopher.senizweb.model.ProjectFile;
import org.veritasopher.senizweb.repository.ProjectFileRepository;
import org.veritasopher.senizweb.repository.ProjectRepository;
import org.veritasopher.senizweb.service.ProjectService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
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

    @Override
    public List<SourceFile> findSourceFileByProjectId(Long projectId) {
        List<ProjectFile> projectFiles = projectFileRepository.findByProjectId(projectId);
        return projectFiles.stream()
                .filter(file -> file.getName().endsWith(Info.SUFFIX))
                .map(file -> new SourceFile(file.getName(), file.getContent())).collect(Collectors.toList());
    }
}
