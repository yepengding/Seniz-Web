package org.veritasopher.senizweb.service;

import org.veritasopher.senizweb.model.ProjectFile;

import java.util.List;
import java.util.Optional;

/**
 * File Service
 *
 * @author Yepeng Ding
 * @date 12/21/2020
 */
public interface ProjectFileService {

    ProjectFile create(ProjectFile record);

    Optional<ProjectFile> findById(Long id);

    ProjectFile update(ProjectFile record);

    void delete(Long id);

    List<ProjectFile> findAll();

    Optional<ProjectFile> findByNameAndProject(String name, Long projectId);

}
