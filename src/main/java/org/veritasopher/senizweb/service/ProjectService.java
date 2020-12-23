package org.veritasopher.senizweb.service;

import org.veritasopher.seniz.core.model.SourceFile;
import org.veritasopher.senizweb.model.Project;
import org.veritasopher.senizweb.model.ProjectFile;

import java.util.List;
import java.util.Optional;

/**
 * Project Service
 *
 * @author Yepeng Ding
 * @date 12/22/2020
 */
public interface ProjectService {

    Project create(Project project);

    Optional<Project> findById(Long id);

    Optional<Project> findByName(String name);

    List<Project> findAllProject();

    List<ProjectFile> findFileByProjectId(Long projectId);

    List<SourceFile> findSourceFileByProjectId(Long projectId);

}
