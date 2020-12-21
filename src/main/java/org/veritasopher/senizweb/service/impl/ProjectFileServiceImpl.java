package org.veritasopher.senizweb.service.impl;

import org.springframework.stereotype.Service;
import org.veritasopher.senizweb.model.ProjectFile;
import org.veritasopher.senizweb.repository.ProjectFileRepository;
import org.veritasopher.senizweb.service.ProjectFileService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * File Service Implementation
 *
 * @author Yepeng Ding
 * @date 12/21/2020
 */
@Service("projectFileService")
public class ProjectFileServiceImpl implements ProjectFileService {

    @Resource
    private ProjectFileRepository projectFileRepository;

    @Override
    public ProjectFile create(ProjectFile record) {
        return projectFileRepository.save(record);
    }

    @Override
    public Optional<ProjectFile> findById(Long id) {
        return projectFileRepository.findById(id);
    }

    @Override
    public ProjectFile update(ProjectFile record) {
        return projectFileRepository.save(record);
    }

    @Override
    public void delete(Long id) {
        projectFileRepository.deleteById(id);
    }

    @Override
    public List<ProjectFile> findAll() {
        return projectFileRepository.findAll();
    }

    @Override
    public Optional<ProjectFile> findByNameAndProject(String name, Long projectId) {
        return projectFileRepository.findByNameAndProjectId(name, projectId);
    }
}
